package service;

import model.Channel;
import util.AvlTree;

public class ChannelApprovalManagerService {
    private final AvlTree<Channel> channelTree;

    public ChannelApprovalManagerService() {
        this(new AvlTree<>());
    }

    public ChannelApprovalManagerService(AvlTree<Channel> channelTree) {
        this.channelTree = channelTree;
    }

    public void insert(Channel channel) {
        channelTree.add(channel);
    }

    public void delete(Channel channel) {
        channelTree.remove(channel);
    }

    public Channel search(String channelId) {
        return channelTree.findById(channelId);
    }

    public String display() {
        return channelTree.toString();
    }
}
