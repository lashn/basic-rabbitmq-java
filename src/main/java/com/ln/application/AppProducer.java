package com.ln.application;

import com.ln.rabbitmqConnector.ConnectionMQ;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class AppProducer {
    //    private final static String QUEUE_NAME = DEFAULT_QUEUE;
    ConnectionMQ conHandle = new ConnectionMQ();
    Connection connection = conHandle.getConnection();
    Channel channel = conHandle.getChannel(connection);

    public AppProducer() throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
    }

    public void sendMessage(String message, String PUB_SUB_QUEUE) throws IOException {
        channel.queueDeclare(PUB_SUB_QUEUE, false, false, false, null);
        channel.basicPublish("", PUB_SUB_QUEUE, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }

    public void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}


