package io.device.controller;

import io.device.config.ConfigLoader;
import io.device.dto.MqttConfig;
import io.device.mock.Switch;
import io.device.util.MqttUtil;
import io.device.util.PayloadUtil;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by rai on 2017/9/17.
 */
public class SwitchController {

  private final static String PATHNAME =
      System.getProperty("user.dir") + "/src/main/resources/switch-config.yaml";

  public void run() throws Exception {

    System.out.println("Device switch start.");

    // 1. 获取设备ID及publicKey，如果没有，输入userId，developerId，token，激活与绑定设备
    MqttConfig mqttConfig = MqttUtil.getMqttConfig(PATHNAME);

    // 2. 配置mqtt client
    BlockingConnection con = MqttUtil.connectMqtt(mqttConfig);

    // 2. 初始化设备
    Switch mySwitch = initSwitch();

    // 3. 接收mqtt消息
    receiveMessage(mySwitch, con);
  }

  private Switch initSwitch() {
    Switch mySwitch = new Switch();
    mySwitch.setPower(true);
    System.out.println("Switch status: " + mySwitch.toString());
    return mySwitch;
  }

  private void receiveMessage(Switch mySwitch, BlockingConnection con) throws Exception {
    System.out.println("Enter. receive message.");
    while (true) {
      Message message = con.receive(1, TimeUnit.SECONDS);
      if (message != null) {
        String payload = new String(message.getPayload());
        message.ack();

        int type = PayloadUtil.getType(payload);
        String id = PayloadUtil.getId(payload);
        String data = PayloadUtil.getData(payload);

        System.out.println("type: " + type);
        System.out.println("id: " + id);
        System.out.println("data: " + data);

        handleMessage(mySwitch, id, data);

        System.out.println("Switch status: " + mySwitch.toString());
      }
    }
  }

  private void handleMessage(Switch mySwitch, String id, String data) throws IOException {
    switch (id) {
      case "1":
        // 总开关
        mySwitch.setPower(Boolean.valueOf(data));
        break;
      case "4":
        // 状态查询
        break;
      case "unbind":
        // 解绑
        ConfigLoader.cleanConfig(PATHNAME);
        System.out.println("Unbind device and clean the config. Program shut up right now.");
        System.exit(1);
        break;
      case "201":
        // 开关1
        mySwitch.setSwitch1(Boolean.valueOf(data));
        break;
      case "202":
        // 开关2
        mySwitch.setSwitch2(Boolean.valueOf(data));
        break;
      case "203":
        // 开关3
        mySwitch.setSwitch3(Boolean.valueOf(data));
        break;
      case "204":
        // 开关4
        mySwitch.setSwitch4(Boolean.valueOf(data));
        break;
      default:
        break;
    }
  }
}
