package com.ln.application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static com.ln.rabbitmqConnector.CommonConfigs.PUB_SUB_QUEUE;


public class MainServer {

    public static void main(String[] args) throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException, InterruptedException {

        //call producer
        AppProducer Producer = new AppProducer();
        for (int i=0;i<10;i++) {
            String message = "Hi inserting message no"+i;
            Producer.sendMessage(message, PUB_SUB_QUEUE);
        }
        Thread.sleep(3000);

        //call Consumer
        AppConsumer Consumer = new AppConsumer();
        for (int i = 0; i < 10; i++) {
            Consumer.getMessage(PUB_SUB_QUEUE);
        }
        Thread.sleep(3000);

        //close connections
        Producer.closeConnection();
        Consumer.closeConnection();

    }
}


