package io.device.mock;

import io.device.client.ConnectionFactory;
import io.device.config.ConfigLoader;
import io.device.util.PayloadUtil;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import java.util.concurrent.TimeUnit;

/**
 * Created by rai on 2017/9/17.
 */
public class SwitchController {

  public void run() throws Exception {
    System.out.println("Device switch start.");

    // 1. 初始化设备
    Switch mySwitch = new Switch();
    mySwitch.setPower(true);
    System.out.println("Switch status: " + mySwitch.toString());

    // 2. 获取设备ID及publicKey，配置mqtt client
    BlockingConnection con = connetcMqtt();

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

  private void handleMessage(Switch mySwitch, String id, String data) {
    switch (id) {
      case "1":
        mySwitch.setPower(Boolean.valueOf(data));
        break;
      case "4":
        break;
      case "201":
        mySwitch.setSwitch1(Boolean.valueOf(data));
        break;
      case "202":
        mySwitch.setSwitch2(Boolean.valueOf(data));
        break;
      case "203":
        mySwitch.setSwitch3(Boolean.valueOf(data));
        break;
      case "204":
        mySwitch.setSwitch4(Boolean.valueOf(data));
        break;
      default:
        break;
    }
  }

  private BlockingConnection connetcMqtt() throws Exception {
    String userName = ConfigLoader.getUserName();

    String publicKey = ConfigLoader.getPublicKey();

    String host = "120.25.129.124";

    int port = 80;

    BlockingConnection con = ConnectionFactory.build(host, port, userName, publicKey);

    Topic[] topics = {new Topic("device/sub/" + userName, QoS.AT_LEAST_ONCE)};
    con.subscribe(topics);
    System.out.println("connected to mqtt block");
    return con;
  }

}
