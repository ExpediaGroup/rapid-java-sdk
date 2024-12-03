---
intro: Essential details about the new Rapid SDK and migration process
shortTitle: Transitioning to v4.1.0+
title: Transitioning to the new Rapid SDK
---

# Transitioning to Rapid SDK v4.1.0 and Later

## What's Changed

In the Rapid SDK v4.1.0, we have introduced a new paradigm for SDK interaction based on Operations.

In the previous versions, the interaction was as follows:

```java
rapidClient.operationX(requestXData, param1, param2, param3, ...,paramN);
```

In the latest version, the interaction has been modified to:

```java
OperationXParams operationXParams = OperationXParams.builder()
        .param1("value1")
        .param2("value2")
        .paramN("valueN")
        .build();
OperationX operationX = new OperationX(requestXData, operationXParams);
rapidClient.execute(operationX);
```

**Notes**:

- `operationX`: Represents the operation to be executed. See [Usage Examples](products/rapid/sdk/java/usage-examples) for real-world examples.
- The old methods are deprecated and will be removed in future versions.

## Why the Change?

The transition to an Operation-based interaction model with the SDK offers a more uniform and extendable approach for
Rapid API usage.
This change also paves the way for future enhancements and features, ensuring backward compatibility.

Moreover, the new model simplifies the interaction to a single `execute` method, eliminating the need for multiple
operation-specific methods.

## How to Migrate?

{% expandoList %}
{% expandoListItem %}
{% expandoHeading %}
If you are using the older `openworld-java-sdk-rapid` SDK, please start with the following steps
{% /expandoHeading %}

# Updating to the rebranded Java SDK

We've rebranded the Java SDK for Rapid API from `openworld-java-sdk-rapid` to `rapid-sdk`, which will impact any
applications using it.

The **SDK name**, **namespace**, and **exceptions** IDs have been changed. To use this updated SDK, you'll need to:
- Use the package name in project dependencies
- Import the models from the new namespaces
- Update the exception handlers with the new exception classes
- Adjust the log messages prefix


## SDK Name

### Before rebranding

{% tabs code=true group="buildTools" %}
{% tab label="Maven" %}

```xml

<project>
    <dependencies>
        <dependency>
            <groupId>com.expediagroup.openworld.sdk</groupId>
            <artifactId>openworld-java-sdk-rapid</artifactId>
            <!-- version -->
        </dependency>
    </dependencies>
</project>
```

{% /tab %}
{% tab label="Gradle" %}

```gradle
dependencies {
    implementation 'com.expediagroup.openworld.sdk:openworld-java-sdk-rapid'
}
```

{% /tab %}
{% /tabs %}

### Rebranded SDK

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


## Namespace


| Before rebranding                                              | Rebranded SDK                                        |
|----------------------------------------------------------------|------------------------------------------------------|
| import com.expediagroup.openworld.sdk.rapid.client.RapidClient | import com.expediagroup.sdk.rapid.client.RapidClient |
| import com.expediagroup.openworld.sdk.rapid.models.* 	         | import com.expediagroup.sdk.rapid.models.*           |


## Exceptions


| Before rebranding          | Rebranded SDK                |
|----------------------------|------------------------------|
| OpenWorldException         | ExpediaGroupException        |
| OpenWorldServiceException	 | ExpediaGroupServiceException |
| OpenWorldClientException   | ExpediaGroupClientException  |


## Logging message prefix


| Before rebranding | Rebranded SDK   |
|-------------------|-----------------|
| ExpediaSDK        | ExpediaGroupSDK |

{% /expandoListItem %}
{% /expandoList %}

If you are using the `rapid-sdk` dependency, then you only need to:
1. Update your `rapid-sdk` dependency to version `4.1.0` or later.
2. Refactor your code to adopt the new Operation-based interaction model with the SDK.

For detailed instructions on using the new Rapid SDK, refer to the
updated [Usage Examples](products/rapid/sdk/java/usage-examples).