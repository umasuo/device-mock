package io.device.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by rai on 2017/9/17.
 */
public class ConfigLoader {

  private static String pathname =
      System.getProperty("user.dir") + "/src/main/resources/application.yaml";

  public static String getUserName() throws IOException {
    String userName = "";
    File dumpFile = new File(pathname);
    //初始化Yaml解析器
    Yaml yaml = new Yaml();
    //读入文件
    Map appConfig = (Map) yaml.load(new FileInputStream(dumpFile));

    Map mqttConfig = (Map) appConfig.get("mqtt");

    if (null == mqttConfig.get("userName")) {
      Scanner sc = new Scanner(System.in);
      System.out.println("输入mqtt用户名:");
      userName = sc.nextLine();  //读取字符串型输入
      System.out.println(userName);

      mqttConfig.put("userName", userName);

      FileWriter writer = new FileWriter(pathname);

      appConfig.putIfAbsent("mqtt", mqttConfig);

      yaml.dump(appConfig, writer);
    } else {
      userName = (String) mqttConfig.get("userName");
    }

    return userName;
  }

  public static String getPublicKey() throws IOException {
    String publicKey = "";
    File dumpFile = new File(pathname);
    //初始化Yaml解析器
    Yaml yaml = new Yaml();
    //读入文件
    Map appConfig = (Map) yaml.load(new FileInputStream(dumpFile));

    Map mqttConfig = (Map) appConfig.get("mqtt");

    if (null == mqttConfig.get("publicKey")) {
      Scanner sc = new Scanner(System.in);
      System.out.println("输入mqtt 的 key:");
      publicKey = sc.nextLine();  //读取字符串型输入
      System.out.println(publicKey);

      mqttConfig.put("publicKey", publicKey);

      FileWriter writer = new FileWriter(pathname);

      appConfig.putIfAbsent("mqtt", mqttConfig);

      yaml.dump(appConfig, writer);
    } else {
      publicKey = (String) mqttConfig.get("publicKey");
    }

    return publicKey;
  }
}
