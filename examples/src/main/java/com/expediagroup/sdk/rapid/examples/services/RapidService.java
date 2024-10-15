package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.rapid.client.RapidClient;
import com.expediagroup.sdk.rapid.examples.Constants;

public abstract class RapidService {

    protected static final RapidClient rapidClient = RapidClient
            .builder()
            .key(System.getProperty("com.expediagroup.rapidsdkjava.apikey", "6p1qjp7f060c96o0pfseh2od7a"))
            .secret(System.getProperty("com.expediagroup.rapidsdkjava.apisecret", "dbg0mibu765va"))
            .endpoint(Constants.SANDBOX_URL) // remove to connect to the production environment
            .requestTimeout(10000)
            .build();

}
