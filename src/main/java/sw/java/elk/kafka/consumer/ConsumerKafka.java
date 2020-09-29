package sw.java.elk.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Author wenrenhao
 * @Date 2020-09-05 11:27
 * @Version 1.0
 */
public class ConsumerKafka {
    public final static String TOPIC = "wrh-topic";

    public static void main(String[] args) {
        consumerget();
    }
    public static void consumerget(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "47.113.102.247:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", false);
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer kafkaConsumer = new KafkaConsumer(props);
//        kafkaConsumer.subscribe(Arrays.asList(TOPIC));
        TopicPartition topicPartition  = new TopicPartition(TOPIC,0);
        kafkaConsumer.assign(Arrays.asList(topicPartition));
//        kafkaConsumer.seek(topicPartition,60);
        while (true){
            ConsumerRecords<String,String> poll = kafkaConsumer.poll(Duration.ofMillis(100));

            for(ConsumerRecord<String,String> record : poll){
                System.out.printf("partition = %d , ===== offset = %d, key = %s, value = %s%n", record.partition(),record.offset(), record.key(), record.value());
            }
        }

    }

}
