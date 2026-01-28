package service;

import model.Channel;
import util.AvlTree;
import util.TreeNode;

public class ChannelApprovalManagerService {
    private final AvlTree<Channel> channelTree;

    public ChannelApprovalManagerService() {
        this(new AvlTree<>());
    }

    public ChannelApprovalManagerService(AvlTree<Channel> channelTree) {
        this.channelTree = channelTree;
    }

    public void insert(Channel channel) {
        channelTree.insert(channel);
    }

    public void delete(Channel channel) {
        channelTree.delete(channel);
    }

    public Channel search(String channelId) {
        TreeNode<Channel> n = channelTree.root();
        while (n != null) {
            int cmp = channelId.compareTo(n.element.getId());
            if (cmp == 0) return n.element;
            n = (cmp < 0) ? n.left : n.right;
        }
        return null;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        inorder(channelTree.root(), sb);
        return sb.toString().trim();
    }

    private void inorder(TreeNode<Channel> n, StringBuilder sb) {
        if (n == null) return;
        inorder(n.left, sb);
        sb.append(n.element).append(" ");
        inorder(n.right, sb);
    }
}
