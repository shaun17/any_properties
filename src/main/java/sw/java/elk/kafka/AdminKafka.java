package sw.java.elk.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @Author wenrenhao
 * @Date 2020-09-04 00:11
 * @Version 1.0
 */
public class AdminKafka {
    public final static String TOPIC = "wrh-topic";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        desPartition();
    }

    public static void getTopic() throws ExecutionException, InterruptedException {
        AdminClient kafka = getKafka();
        ListTopicsResult listTopicsResult = kafka.listTopics();
        //ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
        //listTopicsOptions.listInternal(true);
//        ListTopicsResult listTopicsResult = kafka.listTopics(listTopicsOptions);
        Set<String> strings = listTopicsResult.names().get();
        strings.stream().forEach(System.out::println);

    }

    public static void delTopic() throws ExecutionException, InterruptedException {
        AdminClient kafka = getKafka();
        DeleteTopicsResult deleteTopicsResult = kafka.deleteTopics(Arrays.asList(TOPIC));
        deleteTopicsResult.all().get();
    }


    public static void creatTopic() throws ExecutionException, InterruptedException {
        AdminClient kafka = getKafka();
        Short s = 1;
        NewTopic newTopic = new NewTopic(TOPIC, 2, s);
        CreateTopicsResult topics = kafka.createTopics(Arrays.asList(newTopic));
        System.out.println(topics.all().get());

    }

    public static AdminClient getKafka() {
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "47.113.102.247:9092");
        AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }

    public static void increpations() throws ExecutionException, InterruptedException {
        AdminClient kafka = getKafka();
        Map<String,NewPartitions> map = new HashMap<>();
        NewPartitions newPartitions = NewPartitions.increaseTo(2);
        map.put(TOPIC,newPartitions);
        CreatePartitionsResult partitions = kafka.createPartitions(map);
        System.out.println(partitions.all().get());
    }

    public static void desPartition() throws ExecutionException, InterruptedException {
        AdminClient kafka = getKafka();
        DescribeTopicsResult describeTopicsResult = kafka.describeTopics(Arrays.asList(TOPIC));
        Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all().get();
        stringTopicDescriptionMap.values().stream().forEach(System.out::println);
    }
}
