package io.device.dto;

/**
 * Created by Davis on 17/9/21.
 */
public class MqttConfig {

  private String host;

  private int port;

  private String userName;

  private String publicKey;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  /**
   * toString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "MqttConfig{"
        + "host='" + host + '\''
        + ", port=" + port
        + ", userName='" + userName + '\''
        + ", publicKey='" + publicKey + '\''
        + '}';
  }
}
