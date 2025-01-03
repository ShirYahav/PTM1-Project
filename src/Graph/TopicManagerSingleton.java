package Graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TopicManagerSingleton {

    public static class TopicManager{
        private static final TopicManager instance = new TopicManager();
        private final Map<String, Topic> topics = new HashMap<>();

        private TopicManager(){}

        public Topic getTopic(String topicName){
            return topics.computeIfAbsent(topicName,name-> new Topic(name, null, null));
        }

        public Collection<Topic> getTopics() {
            return topics.values();
        }
        public void clear() {
            topics.clear();
        }

    }

    public static TopicManager get(){
        return TopicManager.instance;
    }
}


