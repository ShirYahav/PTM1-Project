package Configs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Graph.Agent;
import Graph.Topic;
import Graph.TopicManagerSingleton;

public class Graph extends ArrayList<Node> {

    public boolean hasCycles() {
        Set<Node> visited = new HashSet<>();
        for (Node node : this) {
            if (!visited.contains(node) && node.hasCycles()) {
                return true;
            }
            visited.add(node);
        }
        return false;
    }

    public void createFromTopics() {
        TopicManagerSingleton.TopicManager topicManager = TopicManagerSingleton.get();

        for (Topic topic : topicManager.getTopics()) {

            Node topicNode = findOrCreateNode("T" + topic.name); // Removed underscore


            if (topic.subs != null) {
                for (Agent subscriber : topic.subs) {
                    Node subscriberNode = findOrCreateNode("A" + subscriber.getName()); // Removed underscore
                    topicNode.addEdge(subscriberNode);
                }
            }


            if (topic.pubs != null) {
                for (Agent publisher : topic.pubs) {
                    Node publisherNode = findOrCreateNode("A" + publisher.getName()); // Removed underscore
                    publisherNode.addEdge(topicNode);
                }
            }
        }
    }


    private Node findOrCreateNode(String name) {
        for (Node node : this) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        Node newNode = new Node(name);
        this.add(newNode);
        return newNode;
    }
}
