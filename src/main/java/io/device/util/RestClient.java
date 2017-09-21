package io.device.util;

import com.google.common.collect.Maps;

import io.device.dto.ProductView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Davis on 17/9/21.
 */
public class RestClient {

  public static Map<String, String> activeDevice(String developerId, String userId, String token,
      String productId, String unionId) {
    RestTemplate restTemplate = new RestTemplate();
    Map<String, String> result = Maps.newHashMap();

    // TODO: 17/9/21
    String url = "http://user.evacloud.cn/v1/devices";
    HttpHeaders headers = new HttpHeaders();
    headers.set("developerId", developerId);
    headers.set("userId", userId);
    headers.set("authorization", "Bearer " + token);
    headers.set("Content-Type", "application/json");

    ProductView productView = new ProductView(productId, unionId);

    HttpEntity httpEntity = new HttpEntity(productView, headers);

    ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);

    result.put("userName", ((LinkedHashMap) response.getBody()).get("deviceId").toString());
    result.put("publicKey", ((LinkedHashMap) response.getBody()).get("publicKey").toString());

    return result;
  }
}
