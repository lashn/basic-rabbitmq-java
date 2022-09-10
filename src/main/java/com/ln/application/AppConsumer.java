package com.ln.application;
import com.ln.rabbitmqConnector.ConnectionMQ;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;


public class AppConsumer {
    ConnectionMQ conHandle = new ConnectionMQ();
    Connection connection = conHandle.getConnection();
    Channel channel = conHandle.getChannel(connection);

    public AppConsumer() throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
    }

    public void getMessage(String PUB_SUB_QUEUE) throws IOException {
        channel.queueDeclare(PUB_SUB_QUEUE, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(PUB_SUB_QUEUE, true, deliverCallback, consumerTag -> { });
    }

    public void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}

