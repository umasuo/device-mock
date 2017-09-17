package io.device.mock;

import io.device.client.ConnectionFactory;
import io.device.config.ConfigLoader;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import java.util.concurrent.TimeUnit;

/**
 * Created by rai on 2017/9/17.
 */
public class DeviceMock {

  public void run() throws Exception {
    // 1. 激活设备
    // 2. 获取设备ID及publicKey，配置mqtt client
    String userName = ConfigLoader.getUserName();

    String publicKey = ConfigLoader.getPublicKey();

    String host = "120.25.129.124";

    int port = 80;

    BlockingConnection con =ConnectionFactory.build(host, port, userName, publicKey);

    Topic[] topics = {new Topic("device/sub/" + userName, QoS.AT_LEAST_ONCE)};
    con.subscribe(topics);
    System.out.println("connected to mqtt block");

    while (true) {
      Message message = con.receive(1, TimeUnit.SECONDS);
      if (message != null) {
        byte[] payload = message.getPayload();
        message.ack();
        System.out.println(message.getTopic() + ":  " + new String(payload));
      }
    }
  }
}
