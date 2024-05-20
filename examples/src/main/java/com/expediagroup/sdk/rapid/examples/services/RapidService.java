package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.client.rapidClient;

public abstract class RapidService {

    protected static final rapidClient rapidClientInstance = rapidClient
            .builder()
            .key(System.getProperty("com.expediagroup.rapidsdkjava.apikey"))
            .secret(System.getProperty("com.expediagroup.rapidsdkjava.apisecret"))
            .endpoint(Constants.SANDBOX_URL) // remove to connect to the production environment
            .requestTimeout(10000)
            .build();

}
