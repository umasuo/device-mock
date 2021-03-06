package io.device.controller;

import io.device.config.ConfigLoader;
import io.device.dto.MqttConfig;
import io.device.mock.TableLamp;
import io.device.mock.TableLamp.Mode;
import io.device.util.MqttUtil;
import io.device.util.PayloadUtil;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by rai on 2017/9/17.
 */
public class TableLampController {

  private final static String PATHNAME =
      System.getProperty("user.dir") + "/src/main/resources/tablelamp-config.yaml";

  public void run() throws Exception {

    System.out.println("Device table lamp start.");

    // 1. 获取设备ID及publicKey，如果没有，输入userId，developerId，token，激活与绑定设备
    MqttConfig mqttConfig = MqttUtil.getMqttConfig(PATHNAME);

    // 2. 配置mqtt client
    BlockingConnection con = MqttUtil.connectMqtt(mqttConfig);

    // 2. 初始化设备
    TableLamp myTableLamp = initSwitch();

    // 3. 接收mqtt消息
    receiveMessage(myTableLamp, con);
  }

  private TableLamp initSwitch() {
    TableLamp tableLamp = new TableLamp();
    tableLamp.setPower(true);
    System.out.println("Switch status: " + tableLamp.toString());
    return tableLamp;
  }

  private void receiveMessage(TableLamp myTableLamp, BlockingConnection con) throws Exception {
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

        handleMessage(myTableLamp, id, data);

        System.out.println("Table lamp status: " + myTableLamp.toString());
      }
    }
  }

  private void handleMessage(TableLamp myTableLamp, String id, String data) throws IOException {
    switch (id) {
      case "4001":
        // 总开关
        myTableLamp.setPower(Boolean.valueOf(data));
        break;
      case "4004":
        // 状态查询
        break;
      case "unbind":
        // 解绑
        ConfigLoader.cleanConfig(PATHNAME);
        System.out.println("Unbind device and clean the config. Program shut up right now.");
        System.exit(1);
        break;
      case "401":
        // 开关1
        myTableLamp.setBrightness(Integer.valueOf(data));
        break;
      case "402":
        // 开关2
        myTableLamp.setColor(Integer.valueOf(data));
        break;
      case "403":
        // 开关3
        myTableLamp.setRgb(data);
        break;
      case "404":
        // 开关4
        myTableLamp.setMode(Mode.build(data));
        break;
      default:
        break;
    }
  }
}
