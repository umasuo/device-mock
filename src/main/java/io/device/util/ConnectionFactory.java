package io.device.util;

import io.device.dto.MqttConfig;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Created by rai on 2017/9/15.
 */
public class ConnectionFactory {

  public static BlockingConnection build(MqttConfig mqttConfig) {
    MQTT mqtt = new MQTT();

    try {
      mqtt.setHost(mqttConfig.getHost(), mqttConfig.getPort());
      mqtt.setUserName(mqttConfig.getUserName());
      mqtt.setPassword(getPassword(mqttConfig.getPublicKey()));

      BlockingConnection connection = mqtt.blockingConnection();
      connection.connect();

      return connection;
    } catch (URISyntaxException e) {
      throw new RuntimeException("Can not set mqtt host and port");
    } catch (Exception e) {
      throw new RuntimeException("Can not connect to mqtt block");
    }
  }

  private static String getPassword(String publicKey) {
    try {
      byte[] md5 = MessageDigest.getInstance("MD5")
          .digest(publicKey.getBytes(StandardCharsets.UTF_8));
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < md5.length; i++) {
        sb.append(Integer.toString((md5[i] & 0xff) + 0x100, 16).substring(1));
      }
      return sb.toString().substring(7, 23);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return publicKey;
  }
}
