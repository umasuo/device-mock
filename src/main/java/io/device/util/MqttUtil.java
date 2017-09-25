package io.device.util;

import io.device.config.ConfigLoader;
import io.device.dto.MqttConfig;

import org.apache.commons.lang3.StringUtils;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Davis on 17/9/25.
 */
public class MqttUtil {

  public static MqttConfig getMqttConfig(String path) throws IOException {
    MqttConfig mqttConfig = ConfigLoader.getMqttConfig(path);

    if (StringUtils.isBlank(mqttConfig.getUserName()) ||
        StringUtils.isBlank(mqttConfig.getPublicKey())) {
      Scanner sc = new Scanner(System.in);
      System.out.println("输入developerId:");
      String developerId = sc.nextLine();
      System.out.println("输入userId:");
      String userId = sc.nextLine();
      System.out.println("输入token:");
      String token = sc.nextLine();

      String productId = ConfigLoader.getProductId(path);

      String unionId = ConfigLoader.getUnionId(path);

      Map<String, String> activeResult = RestClient
          .activeDevice(developerId, userId, token, productId, unionId);

      mqttConfig.setUserName(activeResult.get("userName"));
      mqttConfig.setPublicKey(activeResult.get("publicKey"));

      System.out.println("Mqtt config: " + mqttConfig.toString());

      ConfigLoader.writeDeviceConfig(mqttConfig.getUserName(), mqttConfig.getPublicKey(), path);
    }
    return mqttConfig;
  }

  public static BlockingConnection connectMqtt(MqttConfig mqttConfig) throws Exception {
    BlockingConnection con = ConnectionFactory.build(mqttConfig);

    Topic[] topics = {new Topic("device/sub/" + mqttConfig.getUserName(), QoS.AT_LEAST_ONCE)};
    con.subscribe(topics);
    System.out.println("connected to mqtt block");
    return con;
  }
}
