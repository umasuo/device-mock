package io.device.mock;

/**
 * Created by Davis on 17/9/25.
 */
public class Adapter {
  private boolean power;

  private boolean socket1;

  private boolean socket2;

  private boolean socket3;

  private boolean socket4;

  private boolean usb1;

  private boolean usb2;

  private boolean usb3;

  private boolean usb4;

  public boolean isPower() {
    return power;
  }

  public void setPower(boolean power) {
    this.power = power;
  }

  public boolean isSocket1() {
    return socket1;
  }

  public void setSocket1(boolean socket1) {
    this.socket1 = socket1;
  }

  public boolean isSocket2() {
    return socket2;
  }

  public void setSocket2(boolean socket2) {
    this.socket2 = socket2;
  }

  public boolean isSocket3() {
    return socket3;
  }

  public void setSocket3(boolean socket3) {
    this.socket3 = socket3;
  }

  public boolean isSocket4() {
    return socket4;
  }

  public void setSocket4(boolean socket4) {
    this.socket4 = socket4;
  }

  public boolean isUsb1() {
    return usb1;
  }

  public void setUsb1(boolean usb1) {
    this.usb1 = usb1;
  }

  public boolean isUsb2() {
    return usb2;
  }

  public void setUsb2(boolean usb2) {
    this.usb2 = usb2;
  }

  public boolean isUsb3() {
    return usb3;
  }

  public void setUsb3(boolean usb3) {
    this.usb3 = usb3;
  }

  public boolean isUsb4() {
    return usb4;
  }

  public void setUsb4(boolean usb4) {
    this.usb4 = usb4;
  }

  /**
   * toString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "Adapter{"
        + "power=" + power
        + ", socket1=" + socket1
        + ", socket2=" + socket2
        + ", socket3=" + socket3
        + ", socket4=" + socket4
        + ", usb1=" + usb1
        + ", usb2=" + usb2
        + ", usb3=" + usb3
        + ", usb4=" + usb4
        + '}';
  }
}
