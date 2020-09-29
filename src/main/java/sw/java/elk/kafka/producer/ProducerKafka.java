package sw.java.elk.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author wenrenhao
 * @Date 2020-09-05 11:14
 * @Version 1.0
 */
public class ProducerKafka {
    public final static String TOPIC = "wrh-topic";
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        producerSend();
    }

    public static void producerSend() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "47.113.102.247:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String,String> producer = new KafkaProducer(props);
        for (int i = 0; i < 100; i++){
            ProducerRecord<String,String> producerRecord = new ProducerRecord(TOPIC,"key-"+i,"val="+i);
            Future<RecordMetadata> send = producer.send(producerRecord);
            System.out.println("partition  :"+producerRecord.partition()+"key-"+i+"   val="+i);
        }
        producer.close();

    }
}
