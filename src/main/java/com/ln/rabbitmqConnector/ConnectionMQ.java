package com.ln.rabbitmqConnector;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

//common connection handler
public class ConnectionMQ {

    public Connection getConnection() throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(CommonConfigs.AMQP_URL);
        Connection connection = factory.newConnection(CommonConfigs.AMQP_URL);
        return connection;
    }

    public Channel getChannel(Connection connection) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, TimeoutException {
        Channel channel = connection.createChannel();
        return channel;
    }

    }

