package io.device;

import io.device.mock.SwitchController;

/**
 * Created by Davis on 17/9/14.
 */
public class Application {

  public static void main(String[] args) throws Exception {
    SwitchController deviceMock = new SwitchController();
    deviceMock.run();
  }
}
