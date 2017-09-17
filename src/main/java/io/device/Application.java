package io.device;

import io.device.mock.DeviceMock;

/**
 * Created by Davis on 17/9/14.
 */
public class Application {

  public static void main(String[] args) throws Exception {
    DeviceMock deviceMock = new DeviceMock();
    deviceMock.run();
  }
}
