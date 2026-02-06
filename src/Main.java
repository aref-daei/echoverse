import model.Channel;
import model.Crew;
import model.Episode;
import model.ProductionTeam;
import service.ChannelApprovalManagerService;
import service.EpisodeRecommenderService;
import service.EpisodeReleaseQueueService;
import service.ProductionTeamManagerService;
import util.LinkedList;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ChannelApprovalManagerService channelService = new ChannelApprovalManagerService();
        LinkedList<Episode> recommendationPool = new LinkedList<>();
        EpisodeReleaseQueueService releaseQueueService = new EpisodeReleaseQueueService();
        ProductionTeamManagerService teamService = new ProductionTeamManagerService(
                new ProductionTeam("PT1", "Default Team")
        );

        String welcomeMessage = String.format(
                "%s%n%s%n%n%n",
                "..::.::: Welcome to EchoVerse :::.::..",
                " (C) 2026 - Aref Daei, Shakiba Ahrari "
        );

        menu(welcomeMessage, input, channelService, recommendationPool, teamService, releaseQueueService);
    }

    static void menu(
            String welcomeMessage,
            Scanner input,
            ChannelApprovalManagerService channelService,
            LinkedList<Episode> recommendationPool,
            ProductionTeamManagerService teamService,
            EpisodeReleaseQueueService releaseQueueService
    ) {
        boolean running = true;
        while (running) {
            clear();
            System.out.print(welcomeMessage);
            System.out.printf(
                    "%s%n%s%n%s%n%s%n%s%n%s%n%n",
                    "=== EchoVerse Main Menu ===",
                    "1) Phase 1 - Channel Management (AVL)",
                    "2) Phase 2 - Episode Recommendations (Graph)",
                    "3) Phase 3 - Production Team Hierarchy (MemberTree)",
                    "4) Phase 4 - Publishing Queue (MinHeap)",
                    "0) Exit"
            );

            int choice = readInt(input, "Select an option: ");
            switch (choice) {
                case 1 -> channelMenu(welcomeMessage, input, channelService);
                case 2 -> recommendationMenu(welcomeMessage, input, recommendationPool);
                case 3 -> teamMenu(welcomeMessage, input, teamService);
                case 4 -> queueMenu(welcomeMessage, input, releaseQueueService);
                case 0 -> running = false;
                default -> {
                    System.out.println("Invalid option. Try again.");
                    okey(input);
                }
            }
        }
        System.out.println("Exited.");
    }

    private static void channelMenu(String welcomeMessage, Scanner input, ChannelApprovalManagerService channelService) {
        boolean back = false;
        while (!back) {
            clear();
            System.out.print(welcomeMessage);
            System.out.printf(
                    "%s%n%s%n%s%n%s%n%s%n%s%n%n",
                    "--- Phase 1: Channel Management ---",
                    "1) Insert channel",
                    "2) Delete channel",
                    "3) Search channel by id",
                    "4) Display channels",
                    "0) Back"
            );

            int choice = readInt(input, "Select an option: ");
            switch (choice) {
                case 1 -> {
                    String id = readText(input, "Channel id: ");
                    String title = readText(input, "Channel title: ");
                    channelService.insert(new Channel(id, title));
                }
                case 2 -> {
                    String id = readText(input, "Channel id to delete: ");
                    channelService.delete(new Channel(id, ""));
                }
                case 3 -> {
                    String id = readText(input, "Channel id to search: ");
                    Channel channel = channelService.search(id);
                    System.out.println(channel == null ? "Not found." : channel);
                    okey(input);
                }
                case 4 -> {
                    System.out.println(channelService.display());
                    okey(input);
                }
                case 0 -> back = true;
                default -> {
                    System.out.println("Invalid option. Try again.");
                    okey(input);
                }
            }
        }
    }

    private static void recommendationMenu(String welcomeMessage, Scanner input, LinkedList<Episode> recommendationPool) {
        boolean back = false;
        while (!back) {
            clear();
            System.out.print(welcomeMessage);
            System.out.printf(
                    "%s%n%s%n%s%n%s%n%s%n%n",
                    "--- Phase 2: Episode Recommendations ---",
                    "1) Add episode to pool",
                    "2) Recommend episodes",
                    "3) Display pool",
                    "0) Back"
            );

            int choice = readInt(input, "Select an option: ");
            switch (choice) {
                case 1 -> recommendationPool.addLast(buildEpisode(input));
                case 2 -> {
                    if (recommendationPool.isEmpty()) {
                        System.out.println("Recommendation pool is empty.");
                        System.out.print("Press Enter to return to menu...");
                        input.nextLine();
                        break;
                    }
                    String id = readText(input, "Episode id to base recommendations on: ");
                    int count = readInt(input, "How many recommendations? ");
                    Episode seed = findEpisodeById(recommendationPool, id);
                    if (seed == null) {
                        System.out.println("Episode not found.");
                        System.out.print("Press Enter to return to menu...");
                        input.nextLine();
                        break;
                    }
                    EpisodeRecommenderService recommender = new EpisodeRecommenderService(recommendationPool);
                    LinkedList<Episode> recs = recommender.recommend(seed, count);
                    System.out.println(recs.isEmpty() ? "No recommendations." : recs);
                    okey(input);
                }
                case 3 -> {
                    System.out.println(recommendationPool.isEmpty() ? "No episodes." : recommendationPool);
                    okey(input);
                }
                case 0 -> back = true;
                default -> {
                    System.out.println("Invalid option. Try again.");
                    okey(input);
                }
            }
        }
    }

    private static void teamMenu(String welcomeMessage, Scanner input, ProductionTeamManagerService teamService) {
        boolean back = false;
        while (!back) {
            clear();
            System.out.print(welcomeMessage);
            System.out.printf(
                    "%s%n%s%n%s%n%s%n%s%n%s%n%s%n%n",
                    "--- Phase 3: Production Team Hierarchy ---",
                    "1) Add supervisor (root)",
                    "2) Add crew under supervisor",
                    "3) Remove crew",
                    "4) Search crew (BFS/DFS)",
                    "5) Display team tree",
                    "0) Back"
            );

            int choice = readInt(input, "Select an option: ");
            switch (choice) {
                case 1 -> teamService.addSupervisor(buildCrew(input));
                case 2 -> {
                    String parentId = readText(input, "Supervisor id: ");
                    Crew parent = new Crew(parentId);
                    Crew crew = buildCrew(input);
                    teamService.addCrew(parent, crew);
                }
                case 3 -> {
                    String id = readText(input, "Crew id to remove: ");
                    teamService.removeCrew(new Crew(id));
                }
                case 4 -> {
                    String mode = readText(input, "Search type [B]FS or [D]FS: ");
                    String id = readText(input, "Crew id to search: ");
                    try {
                        Crew crew = teamService.searchById(mode.charAt(0), id);
                        System.out.println(crew == null ? "Not found." : crew);
                        okey(input);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Invalid search type.");
                        okey(input);
                    }
                }
                case 5 -> {
                    System.out.println(teamService.displayTeamTree());
                    okey(input);
                }
                case 0 -> back = true;
                default -> {
                    System.out.println("Invalid option. Try again.");
                    okey(input);
                }
            }
        }
    }

    private static void queueMenu(String welcomeMessage, Scanner input, EpisodeReleaseQueueService releaseQueueService) {
        boolean back = false;
        while (!back) {
            clear();
            System.out.print(welcomeMessage);
            System.out.printf(
                    "%s%n%s%n%s%n%s%n%s%n%s%n%s%n%n",
                    "--- Phase 4: Publishing Queue ---",
                    "1) Insert episode into queue",
                    "2) Extract next episode (min)",
                    "3) Delete episode by id",
                    "4) Display queue",
                    "5) Heap sort (info)",
                    "0) Back"
            );

            int choice = readInt(input, "Select an option: ");
            switch (choice) {
                case 1 -> releaseQueueService.insert(buildEpisode(input));
                case 2 -> {
                    Episode episode = releaseQueueService.extractMin();
                    System.out.println(episode == null ? "Queue is empty." : episode);
                    okey(input);
                }
                case 3 -> {
                    String id = readText(input, "Episode id to delete: ");
                    Episode removed = releaseQueueService.delete(buildEpisodeStub(id));
                    System.out.println(removed == null ? "Episode not found." : removed);
                    okey(input);
                }
                case 4 -> {
                    System.out.println(releaseQueueService.display());
                    okey(input);
                }
                case 5 -> {
                    releaseQueueService.HeapSort();
                    okey(input);
                }
                case 0 -> back = true;
                default -> {
                    System.out.println("Invalid option. Try again.");
                    okey(input);
                }
            }
        }
    }

    private static Episode buildEpisode(Scanner input) {
        String id = readText(input, "Episode id: ");
        String title = readText(input, "Title: ");
        String genre = readText(input, "Genre: ");
        String teamId = readText(input, "Production team id: ");
        String teamName = readText(input, "Production team name: ");
        String language = readText(input, "Language: ");
        int priority = readInt(input, "Priority (lower is earlier): ");
        return new Episode(
                id,
                title,
                genre,
                new ProductionTeam(teamId, teamName),
                language,
                priority
        );
    }

    private static Episode buildEpisodeStub(String id) {
        // Minimal stub for lookup/removal based on Model.equals(id).
        return new Episode(id, "");
    }

    private static Crew buildCrew(Scanner input) {
        String id = readText(input, "Crew id: ");
        String name = readText(input, "Name: ");
        int age = readInt(input, "Age: ");
        return new Crew(id, name, age);
    }

    private static Episode findEpisodeById(LinkedList<Episode> episodes, String id) {
        for (int i = 0; i < episodes.size(); i++) {
            Episode episode = episodes.get(i);
            if (episode != null && id.equals(episode.getId())) {
                return episode;
            }
        }
        return null;
    }

    private static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = input.nextLine();
            try {
                return Integer.parseInt(raw.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static String readText(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = input.nextLine();
            if (!raw.trim().isEmpty()) {
                return raw.trim();
            }
            System.out.println("Input cannot be empty.");
        }
    }

    public static void clear() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing the console: " + e.getMessage());
        }
    }

    public static void okey(Scanner input) {
        System.out.print("Press Enter to return to menu...");
        input.nextLine();
    }
}
