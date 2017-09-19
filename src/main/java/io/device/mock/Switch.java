package io.device.mock;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Davis on 17/9/19.
 */
public class Switch {

  private boolean power;

  private boolean switch1;

  private boolean switch2;

  private boolean switch3;

  private boolean switch4;

  public boolean isPower() {
    return power;
  }

  public void setPower(boolean power) {
    this.power = power;
  }

  public boolean isSwitch1() {
    return switch1;
  }

  public void setSwitch1(boolean switch1) {
    this.switch1 = switch1;
  }

  public boolean isSwitch2() {
    return switch2;
  }

  public void setSwitch2(boolean switch2) {
    this.switch2 = switch2;
  }

  public boolean isSwitch3() {
    return switch3;
  }

  public void setSwitch3(boolean switch3) {
    this.switch3 = switch3;
  }

  public boolean isSwitch4() {
    return switch4;
  }

  public void setSwitch4(boolean switch4) {
    this.switch4 = switch4;
  }

  /**
   * toString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "Switch{"
        + "power=" + power
        + ", switch1=" + switch1
        + ", switch2=" + switch2
        + ", switch3=" + switch3
        + ", switch4=" + switch4
        + '}';
  }
}
