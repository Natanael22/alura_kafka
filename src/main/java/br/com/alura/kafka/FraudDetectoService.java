package br.com.alura.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class FraudDetectoService {

    public static void main(String[] args){
       var consumer = new KafkaConsumer<String,String>(properties());
       consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));

       while(true) {
           var records = consumer.poll(Duration.ofMillis(100));
           if (!records.isEmpty()) {
               System.out.println("encontrei "+records.count()+" registros");
               //return;

           for (var record : records) {
               System.out.println("___________________________________");
               System.out.println("processing new order, checking for fraud");
               System.out.println("key " + record.key());
               System.out.println("value " + record.value());
               System.out.println("partition " + record.partition());
               System.out.println("offset " + record.offset());
               try {
                   Thread.sleep(5000);
               } catch (InterruptedException e) {
                   //ignoring
                   e.printStackTrace();
               }
               System.out.println("order processed");
           }
           }
       }
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectoService.class.getSimpleName());
        return properties;
    }
}