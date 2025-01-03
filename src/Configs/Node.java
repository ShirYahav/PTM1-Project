package Configs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Graph.Message;

public class Node {
    private String name;
    private List<Node> edges;
    private Message message;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Node edge) {
        edges.add(edge);
    }

    public boolean hasCycles() {
        return hasCyclesDFS(new HashSet<>(), new HashSet<>());
    }

    private boolean hasCyclesDFS(Set<Node> visited, Set<Node> inStack) {
        if (inStack.contains(this)) {
            return true;
        }
        if (visited.contains(this)) {
            return false;
        }
        visited.add(this);
        inStack.add(this);
        for (Node neighbor : edges) {
            if (neighbor != null && neighbor.hasCyclesDFS(visited, inStack)) {
                return true;
            }
        }
        inStack.remove(this);
        return false;
    }

    public String getName() {
        return this.name;
    }

    public List<Node> getEdges() {
        return this.edges;
    }

    public Message getMessage() {
        return this.message;
    }

    public void setEdges(List<Node> edges) {
        this.edges = edges;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }
}
