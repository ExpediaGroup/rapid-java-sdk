---
intro: Software development kits make integration simple so you can quickly get products to market
shortTitle: Quick start
title: Quick start
---

# Setting up the Java SDK for Rapid API

#### Software development kits make integration simple so you can quickly get products to market

## Become a partner and get your credentials

For more information, see [Getting started with Rapid](products/rapid/setup/getting-started).

{% messageCard type="info" %}
{% messageCardHeader %}
Note
{% /messageCardHeader %}
Expedia Group has made branding changes to the Rapid Java SDK. Information on this page refers to the rebranded SDK. [Learn more](/products/rapid/sdk/java/transitioning-to-v4-1-0)
{% /messageCard %}

{% br/ %}

## 1. Set up a Java development environment

Your development environment needs to have Java 8 or later. You can use Apache Maven or Gradle to configure SDK dependencies for your projects.
{% br/ %}

{% tabs code=true group="buildTools" %}
{% tab label="Maven" %}
```xml
<project>
    <dependencies>
        <dependency>
            <groupId>com.expediagroup</groupId>
            <artifactId>rapid-sdk</artifactId>
            <!-- version -->
        </dependency>
    </dependencies>
</project>
```
{% /tab %}
{% tab label="Gradle" %}
```gradle
dependencies {
    implementation 'com.expediagroup:rapid-sdk'
}
```
{% /tab %}
{% /tabs %}

## 2. Create a service client and configure it

To make requests to Rapid endpoints, create a service client and configure it with your credentials.

```java
RapidClient rapidClient =
    RapidClient
        .builder()
        .key("KEY")
        .secret("SECRET")
        .build();
```

{% expandoList %}
{% expandoListItem %}
{% expandoHeading %}
(Optional) 2.1. Configure timeout
{% /expandoHeading %}

The service client can be configured with different timeouts for request, connection, and socket.

{% br/ %}

### 2.1.1. Configure request timeout

Request timeout is the time period from the start of the request to the completion of the response.
The default value is infinite (no timeout).

```java
RapidClient rapidClient=
    RapidClient
        .builder()
        .requestTimeout(90_000) // 90 seconds
        .key("KEY")
        .secret("SECRET")
        .build();
```

{% br/ %}

### 2.1.2. Configure connection timeout

Connection timeout is the time period from the start of the request to the establishment of the connection with the
server.
The default value is 10000 milliseconds (10 seconds).

```java
RapidClient rapidClient=
    RapidClient
        .builder()
        .connectionTimeout(30_000) // 30 seconds
        .key("KEY")
        .secret("SECRET")
        .build();
```

{% br/ %}

### 2.1.3. Configure socket timeout
Socket timeout is the maximum period of inactivity between two consecutive data packets when exchanging data with a
server.
The default value is 15000 milliseconds (15 seconds).

```java
RapidClient rapidClient=
    RapidClient
        .builder()
        .socketTimeout(30_000) // 30 seconds
        .key("KEY")
        .secret("SECRET")
        .build();
```


{% /expandoListItem %}
{% expandoListItem %}
{% expandoHeading %}
(Optional) 2.2. Configure endpoint
{% /expandoHeading %}

The service client can also be configured to override the Rapid API endpoint, for example to use the test environment.

```java
RapidClient rapidClient =
    RapidClient
        .builder()
        .endpoint("https://test.ean.com/v3/")
        .key("KEY")
        .secret("SECRET")
        .build();
```

{% /expandoListItem %}
{% /expandoList %}

## 3. Make API calls

The service client has a method for each endpoint in the Rapid API. You can access the endpoint by calling the corresponding method with the required parameters. 

```java
GetAvailabilityOperationParams getAvailabilityOperationParams = GetAvailabilityOperationParams.builder()
        .checkin("YYYY-MM-DD")
        .checkout("YYYY-MM-DD")
        .currency("USD")
        .language("en_US")
        /* ... */
        .build();
GetAvailabilityOperation getAvailabilityOperation = new GetAvailabilityOperation(getAvailabilityOperationParams);
Response<List<Property>> propertiesResponse = rapidClient.execute(getAvailabilityOperation);
System.out.println(propertiesResponse.getData());
```

### Asynchronous execution
The service client also offers **asynchronous methods** for each endpoint. You can access the endpoint by calling the corresponding asynchronous method with the required parameters.

```java
GetAvailabilityOperationParams getAvailabilityOperationParams = GetAvailabilityOperationParams.builder()
        .checkin("YYYY-MM-DD")
        .checkout("YYYY-MM-DD")
        .currency("USD")
        .language("en_US")
        /* ... */
        .build();
        
GetAvailabilityOperation getAvailabilityOperation = new GetAvailabilityOperation(getAvailabilityOperationParams);

/*  
    The executeAsync method is used to perform an asynchronous operation with the rapidClient, 
    allowing the application to continue executing other tasks while waiting for the operation to complete.
 */
CompleteableFuture getAvailability = rapidClient.executeAsync(getAvailabilityOperation)
        .thenAccept(response -> System.out.println(response.getData()));
```

Need more information? Check out our [usage examples](products/rapid/sdk/java/usage-examples).
To get better insight into your API calls, configure [logging](products/rapid/sdk/java/logging).