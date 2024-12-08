---
intro: Introducing Configurable HTTP Client
shortTitle: Introducing Configurable HTTP Client
title: Introducing Configurable HTTP Client
tags:
  - new
---

# Introducing Configurable HTTP Client

## What is a Configurable HTTP Client?

The RAPID SDK is built on top of an OkHttpClient that is not open for you to configure and tune. Lately, we've come to
realize your need to tune and optimize the HTTP client to your needs. So in order to give developers using the RAPID SDK
more control over the underlying HTTP client of the SDK, we're introducing a new builder which you can use to pass your
own HTTP Client for the SDK to use internally.

Using this builder, you can build an HTTP client with you own configurations and pass it to the SDK, or even pass a
client you're already using in your application.

{% messageCard type="info" %}
{% messageCardHeader %}
Note
{% /messageCardHeader %}
This feature is available in the `rapid-sdk` v5.2.0 and later.
{% /messageCard %}

## Why use a Configurable HTTP Client?

A configurable HTTP client will benefit you in the following ways:

- **Optimization:** Fine-tune the client for better performance, such as connection pooling, timeouts, and retries.
- **Integration:** Use an existing HTTP client that is already configured and tested within your application.

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
        .key("YOUR_API_KEY")
        .secret("YOUR_API_SECRET")
        .okHttpClient(customClient)
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
