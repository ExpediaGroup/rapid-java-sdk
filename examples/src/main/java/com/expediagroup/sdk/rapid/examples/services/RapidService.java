package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.rapid.client.RapidClient;
import com.expediagroup.sdk.rapid.examples.Constants;

/**
 * Abstract class representing a service that interacts with the Rapid API.
 */
public abstract class RapidService {
  /**
   * The RapidClient instance used to execute operations.
   */
  protected static final RapidClient rapidClient = RapidClient
      .builder()
      .key(System.getProperty("com.expediagroup.rapidsdkjava.apikey", "your_api_key"))
      .secret(System.getProperty("com.expediagroup.rapidsdkjava.apisecret", "your_api_secret"))
      .endpoint(Constants.SANDBOX_URL) // remove to connect to the production environment
      .requestTimeout(10000)
      .build();
}
