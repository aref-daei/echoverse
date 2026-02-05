import java.util.Scanner;

import model.Channel;
import model.Crew;
import model.Episode;
import model.ProductionTeam;
import service.ChannelApprovalManagerService;
import service.EpisodeRecommenderService;
import service.EpisodeReleaseQueueService;
import service.ProductionTeamManagerService;
import util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ChannelApprovalManagerService channelService = new ChannelApprovalManagerService();
        LinkedList<Episode> recommendationPool = new LinkedList<>();
        EpisodeReleaseQueueService releaseQueueService = new EpisodeReleaseQueueService();
        ProductionTeamManagerService teamService = new ProductionTeamManagerService(
                new ProductionTeam("PT-1", "Default Team")
        );

        System.out.printf(
                "%s\n%s\n\n%s ",
                "...::..::: Welcome to EchoVerse :::..::...",
                "    © 2026 - Aref Daei, Shakiba Ahrari    ",
                "Do you want the menu to open? [y/N]"
        );

        boolean isMenu = input.nextLine().equalsIgnoreCase("y");
        if (isMenu) {
            menu(input, channelService, recommendationPool, teamService, releaseQueueService);
        } else {
            command(input, channelService, recommendationPool, teamService, releaseQueueService);
        }
    }

    static void menu(
            Scanner input,
            ChannelApprovalManagerService channelService,
            LinkedList<Episode> recommendationPool,
            ProductionTeamManagerService teamService,
            EpisodeReleaseQueueService releaseQueueService
    ) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== EchoVerse Main Menu ===");
            System.out.println("1) Phase 1 - Channel Management (AVL)");
            System.out.println("2) Phase 2 - Episode Recommendations (Graph)");
            System.out.println("3) Phase 3 - Production Team Hierarchy (MemberTree)");
            System.out.println("4) Phase 4 - Publishing Queue (MinHeap)");
            System.out.println("0) Exit");

            int choice = readInt(input, "Select an option: ");
            switch (choice) {
                case 1 -> channelMenu(input, channelService);
                case 2 -> recommendationMenu(input, recommendationPool);
                case 3 -> teamMenu(input, teamService);
                case 4 -> queueMenu(input, releaseQueueService);
                case 0 -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        System.out.println("Goodbye.");
    }

    static void command(
            Scanner input,
            ChannelApprovalManagerService channelService,
            LinkedList<Episode> recommendationPool,
            ProductionTeamManagerService teamService,
            EpisodeReleaseQueueService releaseQueueService
    ) {
        menu(input, channelService, recommendationPool, teamService, releaseQueueService);
    }

    private static void channelMenu(Scanner input, ChannelApprovalManagerService channelService) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Phase 1: Channel Management ---");
            System.out.println("1) Insert channel");
            System.out.println("2) Delete channel");
            System.out.println("3) Search channel by id");
            System.out.println("4) Display channels");
            System.out.println("0) Back");

            int choice = readInt(input, "Select an option: ");
            switch (choice) {
                case 1 -> {
                    String id = readText(input, "Channel id: ");
                    int title = readInt(input, "Channel title (number): ");
                    channelService.insert(new Channel(id, title));
                }
                case 2 -> {
                    String id = readText(input, "Channel id to delete: ");
                    channelService.delete(new Channel(id, 0));
                }
                case 3 -> {
                    String id = readText(input, "Channel id to search: ");
                    Channel channel = channelService.search(id);
                    System.out.println(channel == null ? "Not found." : channel);
                }
                case 4 -> System.out.println(channelService.display());
                case 0 -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void recommendationMenu(Scanner input, LinkedList<Episode> recommendationPool) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Phase 2: Episode Recommendations ---");
            System.out.println("1) Add episode to pool");
            System.out.println("2) Recommend episodes");
            System.out.println("3) Display pool");
            System.out.println("0) Back");

            int choice = readInt(input, "Select an option: ");
            switch (choice) {
                case 1 -> recommendationPool.addLast(buildEpisode(input));
                case 2 -> {
                    if (recommendationPool.isEmpty()) {
                        System.out.println("Recommendation pool is empty.");
                        break;
                    }
                    String id = readText(input, "Episode id to base recommendations on: ");
                    int count = readInt(input, "How many recommendations? ");
                    Episode seed = findEpisodeById(recommendationPool, id);
                    if (seed == null) {
                        System.out.println("Episode not found.");
                        break;
                    }
                    EpisodeRecommenderService recommender = new EpisodeRecommenderService(recommendationPool);
                    // Note: recommend() currently ignores n internally; passing count for future compatibility.
                    LinkedList<Episode> recs = recommender.recommend(seed, count);
                    System.out.println(recs.isEmpty() ? "No recommendations." : recs);
                }
                case 3 -> System.out.println(recommendationPool.isEmpty() ? "No episodes." : recommendationPool);
                case 0 -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void teamMenu(Scanner input, ProductionTeamManagerService teamService) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Phase 3: Production Team Hierarchy ---");
            System.out.println("1) Add supervisor (root)");
            System.out.println("2) Add crew under supervisor");
            System.out.println("3) Remove crew");
            System.out.println("4) Search crew (BFS/DFS)");
            System.out.println("5) Display team tree");
            System.out.println("0) Back");

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
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Invalid search type.");
                    }
                }
                case 5 -> System.out.println(teamService.displayTeamTree());
                case 0 -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void queueMenu(Scanner input, EpisodeReleaseQueueService releaseQueueService) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Phase 4: Publishing Queue ---");
            System.out.println("1) Insert episode into queue");
            System.out.println("2) Extract next episode (min)");
            System.out.println("3) Delete episode by id");
            System.out.println("4) Display queue");
            System.out.println("5) Heap sort (info)\n");
            System.out.println("0) Back");

            int choice = readInt(input, "Select an option: ");
            switch (choice) {
                case 1 -> releaseQueueService.insert(buildEpisode(input));
                case 2 -> {
                    Episode episode = releaseQueueService.extractMin();
                    System.out.println(episode == null ? "Queue is empty." : episode);
                }
                case 3 -> {
                    String id = readText(input, "Episode id to delete: ");
                    Episode removed = releaseQueueService.delete(buildEpisodeStub(id));
                    System.out.println(removed == null ? "Episode not found." : removed);
                }
                case 4 -> System.out.println(releaseQueueService.display());
                case 5 -> releaseQueueService.HeapSort();
                case 0 -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static Episode buildEpisode(Scanner input) {
        String id = readText(input, "Episode id: ");
        String title = readText(input, "Title: ");
        String genre = readText(input, "Genre: ");
        int year = readInt(input, "Year: ");
        String teamId = readText(input, "Production team id: ");
        String teamName = readText(input, "Production team name: ");
        String language = readText(input, "Language: ");
        int duration = readInt(input, "Duration (minutes): ");
        int priority = readInt(input, "Priority (lower is earlier): ");
        return new Episode(
                id,
                title,
                genre,
                year,
                new ProductionTeam(teamId, teamName),
                language,
                duration,
                priority
        );
    }

    private static Episode buildEpisodeStub(String id) {
        // Minimal stub for lookup/removal based on Model.equals(id).
        return new Episode(id, "", "", 0, new ProductionTeam("", ""), "", 0, 0);
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
}
