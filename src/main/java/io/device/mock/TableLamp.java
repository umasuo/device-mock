package io.device.mock;

/**
 * Created by Davis on 17/9/25.
 */
public class TableLamp {

  private boolean power;

  private int brightness;

  private int color;

  private String rgb;

  private Mode mode;

  public enum Mode {
    mod1,
    mod2,
    mod3,
    mod4,
    mod5,
    mod6,
    mod7,
    mod8,
    mod9,
    mod10;

    public static Mode build(String data) {
      Mode mod = null;
      switch (data) {
        case "mod1":
          mod = mod1;
          break;
        case "mod2":
          mod = mod2;
          break;
        case "mod3":
          mod = mod3;
          break;
        case "mod4":
          mod = mod4;
          break;
        case "mod5":
          mod = mod5;
          break;
        case "mod6":
          mod = mod6;
          break;
        case "mod7":
          mod = mod7;
          break;
        case "mod8":
          mod = mod8;
          break;
        case "mod9":
          mod = mod9;
          break;
        case "mod10":
          mod = mod10;
          break;
        default:
          break;
      }
      return mod;
    }
  }

  public boolean isPower() {
    return power;
  }

  public void setPower(boolean power) {
    this.power = power;
  }

  public int getBrightness() {
    return brightness;
  }

  public void setBrightness(int brightness) {
    this.brightness = brightness;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public String getRgb() {
    return rgb;
  }

  public void setRgb(String rgb) {
    this.rgb = rgb;
  }

  public Mode getMode() {
    return mode;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  /**
   * toString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "TableLamp{"
        + "power=" + power
        + ", brightness=" + brightness
        + ", color=" + color
        + ", rgb='" + rgb + '\''
        + ", mode=" + mode
        + '}';
  }
}
