package br.com.alura.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;


public class FraudDetectoService {

    public static void main(String[] args) {

        var fraudService = new FraudDetectoService();
        var service = new KafkaService(FraudDetectoService.class.getSimpleName(),"ECOMMERCE_NEW_ORDER", fraudService::parse);
        service.run();
    }

    private void parse(ConsumerRecord<String, String> record) {

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
