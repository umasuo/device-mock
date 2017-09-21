package io.device.dto;

/**
 * Created by Davis on 17/9/21.
 */
public class ProductView {

  private String productId;

  private String unionId;

  public ProductView(String productId, String unionId) {
    this.productId = productId;
    this.unionId = unionId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getUnionId() {
    return unionId;
  }

  public void setUnionId(String unionId) {
    this.unionId = unionId;
  }
}
