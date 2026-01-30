package service;

import model.Crew;
import model.ProductionTeam;
import util.LinkedBinaryTree;
import util.Queue;
import util.TNode;

public class ProductionTeamManagerService {
    ProductionTeam prodTeam;
    LinkedBinaryTree<Crew> crews;

    public ProductionTeamManagerService(ProductionTeam prodTeam) {
        this(prodTeam, new LinkedBinaryTree<>());
    }

    public ProductionTeamManagerService(ProductionTeam prodTeam, LinkedBinaryTree<Crew> crews) {
        this.prodTeam = prodTeam;
        this.crews = crews;
    }

    public void addSupervisor(Crew crew) {
        if (crews.root() == null) {
            crews.addRoot(crew);
        } else {
            throw new IllegalStateException("The team already has a supervisor");
        }
    }

    public void addCrew(String parentId, Crew crew) {
        if (crews.root() == null) {
            throw new IllegalStateException("The team has not a supervisor");
        }

        TNode<Crew> parentNode = findNodeDFS(crews.root(), parentId);
        if (parentNode == null) {
            throw new IllegalStateException("The parent crew does not exist");
        }
        if (parentNode.left == null) {
            crews.addLeft(parentNode, crew);
        } else if (parentNode.right == null) {
            crews.addRight(parentNode, crew);
        } else {
            throw new IllegalStateException("The parent crew cannot add new subordinates");
        }
    }

    public Crew removeCrew(String id) {
        TNode<Crew> nodeToRemove = findNodeDFS(crews.root(), id);
        if (nodeToRemove != null) {
            if (crews.isRoot(nodeToRemove)) {
                crews = new LinkedBinaryTree<>();
            } else {
                // The delete operation deletes the entire subtree along with the node.
                TNode<Crew> parent = crews.parent(nodeToRemove);
                if (crews.left(parent).equals(nodeToRemove)) {
                    parent.left = null;
                } else if (crews.right(parent).equals(nodeToRemove)) {
                    parent.right = null;
                }
            }
            return nodeToRemove.element;
        }
        return null;
    }

    public String displayTeamTree() {
        if (crews.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        printTree(crews.root(), 0, sb);
        return sb.toString();
    }

    // Helper method for indented display
    private void printTree(TNode<Crew> node, int level, StringBuilder sb) {
        if (node == null) return;

        sb.append("   ".repeat(Math.max(0, level)));
        sb.append("|-- ").append(node.element.getName()).append(" (ID: ").append(node.element.getId()).append(")");

        printTree(crews.left(node), level + 1, sb);
        printTree(crews.right(node), level + 1, sb);
    }

    public Crew breadthFirstSearchById(String id) {
        if (crews.isEmpty()) return null;

        Queue<TNode<Crew>> queue = new Queue<>();
        queue.offer(crews.root());

        while (!queue.isEmpty()) {
            TNode<Crew> current = queue.poll();

            if (current.element.getId().equals(id)) return current.element;

            if (crews.left(current) != null) {
                queue.add(crews.left(current));
            }
            if (crews.right(current) != null) {
                queue.add(crews.right(current));
            }
        }
        return null;
    }

    public Crew depthFirstSearchById(String id) {
        TNode<Crew> result = findNodeDFS(crews.root(), id);
        if (result != null) {
            return result.element;
        }
        return null;
    }

    // Helper recursive method for DFS
    private TNode<Crew> findNodeDFS(TNode<Crew> node, String id) {
        if (node == null) return null;

        // Check current node
        if (node.element.getId().equals(id)) return node;

        // Search in the left subtree
        TNode<Crew> leftResult = findNodeDFS(crews.left(node), id);
        if (leftResult != null) return leftResult;

        // Search in the right subtree
        return findNodeDFS(crews.right(node), id);
    }
}
