package io.device.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Davis on 17/9/19.
 */
public class PayloadUtil {

  public static int getType(String payload) throws IOException {
    HashMap<String, Object> result = new ObjectMapper().readValue(payload, HashMap.class);

    int type = Integer.valueOf(result.get("type").toString());

    return type;
  }

  public static String getId(String payload) throws IOException {
    HashMap<String, Object> result = new ObjectMapper().readValue(payload, HashMap.class);

    HashMap<String, String> content = (HashMap<String, String>) result.get("content");

    String id = String.valueOf(content.get("id"));

    return id;
  }

  public static String getData(String payload) throws IOException {
    HashMap<String, Object> result = new ObjectMapper().readValue(payload, HashMap.class);

    HashMap<String, String> content = (HashMap<String, String>) result.get("content");

    String data = String.valueOf(content.get("data"));

    return data;
  }
}
