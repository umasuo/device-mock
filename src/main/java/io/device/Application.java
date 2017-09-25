package io.device;

import io.device.controller.SwitchController;
import io.device.controller.TableLampController;
import io.device.mock.TableLamp;

/**
 * Created by Davis on 17/9/14.
 */
public class Application {

  public static void main(String[] args) throws Exception {
    System.out.println("Start device mock program......");
//    SwitchController switchController = new SwitchController();
//    switchController.run();


    TableLampController tableLampController = new TableLampController();
    tableLampController.run();
  }
}
