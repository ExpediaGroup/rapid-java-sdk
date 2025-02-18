---
intro: Configurable HTTP Client
shortTitle: Configurable HTTP Client
title: Configurable HTTP Client
tags:
  - new
---

# Configurable HTTP Client

The SDK uses an underlying HTTP client to execute API calls. In order to give developers using the Rapid SDK
more control over the HTTP client of the SDK, a builder is provided which you can use to pass your
own HTTP client for the SDK to use.

Using this builder, you can build an HTTP client with your own configurations and pass it to the SDK, or even pass a
client you're already using in your application.

{% messageCard type="info" %}
{% messageCardHeader %}
Note
{% /messageCardHeader %}
This feature is available in the `rapid-sdk` v5.3.1 and later.
{% /messageCard %}

## Choosing between configuring an HTTP client and using the default client

Using the default client provided by the SDK is the easiest way to get started with the SDK. However, configuring your
own HTTP client will benefit you in the following ways:

- **Optimization:** Fine-tune the client for better performance, such as connection pooling, timeouts, and retries.
- **Integration:** Use an existing HTTP client that is already configured and tested within your application.

So, if you're looking to optimize the SDK for better performance or integrate it with an existing HTTP client, configuring
your own HTTP client is the way to go.

## How to configure your HTTP client?

To configure your HTTP client, you need to create an instance of `OkHttpClient` and pass it to the `RapidClient` builder.

#### 1. Create an instance of `OkHttpClient` with your configurations. You may use an existing instance or create a new one. For example:
```java
OkHttpClient customClient = new OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build();
```
For more information on configuring the `OkHttpClient`, refer to the [OkHttp documentation](https://square.github.io/okhttp/).

#### 2. Pass the `OkHttpClient` instance to the `RapidClient` builder `builderWithHttpClient`
```java
RapidClient rapidClient = RapidClient.builderWithHttpClient()
        .okHttpClient(customClient)
        .key("YOUR_API_KEY")
        .secret("YOUR_API_SECRET")
        .build();
```

#### 3. Make API Calls: Use the configured rapidClient to make API calls as usual
```java
GetAvailabilityOperationParams getAvailabilityOperationParams = GetAvailabilityOperationParams.builder()
        .checkin("YYYY-MM-DD")
        .checkout("YYYY-MM-DD")
        .currency("USD")
        .language("en_US")
        /* ... */
        .build();

GetAvailabilityOperation operation = new GetAvailabilityOperation(params);
Response<List<Property>> response = rapidClient.execute(operation);
```
