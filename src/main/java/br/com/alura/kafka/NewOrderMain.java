package br.com.alura.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try(var dispatcher = new KafkaDispatcher()) {
            for (int i = 0; i < 10; i++) {

                var key = UUID.randomUUID().toString();
                var value = key + ",987,10.50";
                var email = "bem vindo! nos estamos processando seu pedido";
                dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);
                dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);

            }
        }
    }




}
