package Configs;

import java.util.function.BinaryOperator;

import Graph.TopicManagerSingleton.TopicManager;
import Graph.TopicManagerSingleton;
import Graph.Agent;
import Graph.Message;

public class BinOpAgent implements Agent {

    private final String agentName;
    private final String inTopic1;
    private final String inTopic2;
    private final String outTopic;
    private final BinaryOperator<Double> operation;

    private double val1;
    private double val2;

    public BinOpAgent(String agentName, String inTopic1, String inTopic2, String outTopic, BinaryOperator<Double> operation) {
        this.agentName = agentName;
        this.inTopic1 = inTopic1;
        this.inTopic2 = inTopic2;
        this.outTopic = outTopic;
        this.operation = operation;

        TopicManager tm = TopicManagerSingleton.get();
        tm.getTopic(inTopic1).subscribe(this);
        tm.getTopic(inTopic2).subscribe(this);

        tm.getTopic(outTopic).addPublisher(this);

        reset();
    }

    @Override
    public String getName() {
        return agentName;
    }

    @Override
    public void reset() {
        val1 = 0.0;
        val2 = 0.0;
    }

    @Override
    public void callback(String topic, Message msg) {

        double newVal = msg.asDouble;

        if (topic.equals(inTopic1)) {
            val1 = newVal;
        } else if (topic.equals(inTopic2)) {
            val2 = newVal;
        }

        double result = operation.apply(val1, val2);

        TopicManagerSingleton.get().getTopic(outTopic).publish(new Message(result));
    }

    @Override
    public void close() {
        TopicManager tm = TopicManagerSingleton.get();
        tm.getTopic(inTopic1).unsubscribe(this);
        tm.getTopic(inTopic2).unsubscribe(this);
        tm.getTopic(outTopic).removePublisher(this);
    }
}
