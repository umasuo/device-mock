package io.device.controller;

import io.device.config.ConfigLoader;
import io.device.dto.MqttConfig;
import io.device.mock.Adapter;
import io.device.util.MqttUtil;
import io.device.util.PayloadUtil;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by rai on 2017/9/17.
 */
public class AdapterController {

  private final static String PATHNAME =
      System.getProperty("user.dir") + "/src/main/resources/adapter-config.yaml";

  public void run() throws Exception {

    System.out.println("Device switch start.");

    // 1. 获取设备ID及publicKey，如果没有，输入userId，developerId，token，激活与绑定设备
    MqttConfig mqttConfig = MqttUtil.getMqttConfig(PATHNAME);

    // 2. 配置mqtt client
    BlockingConnection con = MqttUtil.connectMqtt(mqttConfig);

    // 2. 初始化设备
    Adapter myAdapter = initAdapter();

    // 3. 接收mqtt消息
    receiveMessage(myAdapter, con);
  }

  private Adapter initAdapter() {
    Adapter adapter = new Adapter();
    adapter.setPower(true);
    System.out.println("Adapter status: " + adapter.toString());
    return adapter;
  }

  private void receiveMessage(Adapter myAdapter, BlockingConnection con) throws Exception {
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

        handleMessage(myAdapter, id, data);

        System.out.println("Adapter status: " + myAdapter.toString());
      }
    }
  }

  private void handleMessage(Adapter myAdapter, String id, String data) throws IOException {
    switch (id) {
      case "1":
        // 总开关
        myAdapter.setPower(Boolean.valueOf(data));
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
      case "101":
        // 开关1
        myAdapter.setSocket1(Boolean.valueOf(data));
        break;
      case "102":
        // 开关2
        myAdapter.setSocket3(Boolean.valueOf(data));
        break;
      case "103":
        // 开关3
        myAdapter.setSocket3(Boolean.valueOf(data));
        break;
      case "104":
        // 开关4
        myAdapter.setSocket4(Boolean.valueOf(data));
        break;
      case "105":
        // 开关1
        myAdapter.setUsb1(Boolean.valueOf(data));
        break;
      case "106":
        // 开关2
        myAdapter.setUsb2(Boolean.valueOf(data));
        break;
      case "107":
        // 开关3
        myAdapter.setUsb3(Boolean.valueOf(data));
        break;
      case "108":
        // 开关4
        myAdapter.setUsb4(Boolean.valueOf(data));
        break;
      default:
        break;
    }
  }
}
