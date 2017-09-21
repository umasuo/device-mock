package io.device.config;

import com.google.common.collect.Maps;

import io.device.dto.MqttConfig;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by rai on 2017/9/17.
 */
public class ConfigLoader {

  private static String pathname =
      System.getProperty("user.dir") + "/src/main/resources/application.yaml";

  public static String getProductId() {
    String productId = "";
    try {
      File dumpFile = new File(pathname);
      //初始化Yaml解析器
      Yaml yaml = new Yaml();
      //读入文件
      Map appConfig = (Map) yaml.load(new FileInputStream(dumpFile));

      Map deviceConfig = (Map) appConfig.get("device");

      productId = (String) deviceConfig.get("productId");
    } catch (FileNotFoundException e) {
      System.out.println("Wrong when get productId. Check the application.yaml");
      e.printStackTrace();
      throw new RuntimeException();
    }
    return productId;
  }

  public static String getUnionId() {
    String unionId = "";

    try {
      File dumpFile = new File(pathname);
      //初始化Yaml解析器
      Yaml yaml = new Yaml();
      //读入文件
      Map appConfig = (Map) yaml.load(new FileInputStream(dumpFile));

      Map deviceConfig = (Map) appConfig.get("device");

      unionId = (String) deviceConfig.get("unionId");
    } catch (FileNotFoundException e) {
      System.out.println("Wrong when get unionId. Check the application.yaml");
      e.printStackTrace();
      throw new RuntimeException();
    }

    return unionId;
  }

  public static void cleanConfig() throws IOException {
    writeDeviceConfig(null, null);
  }

  public static void writeDeviceConfig(String userName, String publicKey) throws IOException {
    System.out.println("Enter. userName: " + userName + ", publicKey: " + publicKey);
    File dumpFile = new File(pathname);
    //初始化Yaml解析器
    Yaml yaml = new Yaml();
    //读入文件
    Map appConfig = (Map) yaml.load(new FileInputStream(dumpFile));

    Map mqttConfig = (Map) appConfig.get("mqtt");

    mqttConfig.put("publicKey", publicKey);
    mqttConfig.put("userName", userName);

    FileWriter writer = new FileWriter(pathname);

    appConfig.putIfAbsent("mqtt", mqttConfig);

    yaml.dump(appConfig, writer);
  }

  public static MqttConfig getMqttConfig() {
    MqttConfig mqttConfig = new MqttConfig();

    File dumpFile = new File(pathname);
    //初始化Yaml解析器
    Yaml yaml = new Yaml();
    //读入文件
    Map appConfig = Maps.newConcurrentMap();

    try {

      appConfig = (Map) yaml.load(new FileInputStream(dumpFile));
    } catch (FileNotFoundException e) {
      System.out.println("Can not load config file.");
      e.printStackTrace();
      throw new RuntimeException();
    }

    Map config = (Map) appConfig.get("mqtt");
    mqttConfig.setUserName((String) config.getOrDefault("userName", ""));
    mqttConfig.setPublicKey((String) config.getOrDefault("publicKey", ""));
    mqttConfig.setHost(config.get("host").toString());
    mqttConfig.setPort(Integer.valueOf(config.get("port").toString()));

    return mqttConfig;
  }
}
