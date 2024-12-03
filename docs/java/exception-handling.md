---
intro: Exception handling in the Rapid SDK for Java
shortTitle: Exception handling
title: Exception handling
---

# Exception Handling in the Rapid SDK for Java

#### A guide to exception handling in the Rapid SDK for Java

The Rapid SDK for Java uses runtime (unchecked) exceptions to relay errors. At the root of the exception hierarchy is `ExpediaGroupException` from which all other exceptions are extended. `ExpediaGroupException` is never thrown directly.

There are two categories of `ExpediaGroupException`:

1. `ExpediaGroupServiceException`: Thrown when the downstream service returns an error response. That is, the service successfully received the request but it was not able to process it. The exception object provides the caller with several pieces of information about the error including an HTTP status code and a detailed message. `ExpediaGroupAuthException` is a subtype of this exception and it is thrown when authentication fails.

2. `ExpediaGroupClientException`: Thrown at client errors, either when trying to send the request or parse the response. For example, an `ExpediaGroupConfigurationException` is thrown if credentials are not configured.

Since exceptions are unchecked, the caller decides which exceptions to handle. In principle, an `ExpediaGroupClientException` is assumed to be not retryable and should typically be fixed during development. On the other hand, an `ExpediaGroupServiceException` might be recoverable, such as errors resulting from a service being temporarily unavailable. Error handling should therefore focus on the latter.
