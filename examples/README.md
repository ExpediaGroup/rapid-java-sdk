# Rapid Java SDK Examples

This repository contains examples of how to use the Expedia Group Rapid Java SDK. The examples are written in Java and use Maven for dependency management.

## Examples

The example implementation provided in the examples package demonstrate different scenarios utilizing various RAPID APIs through the SDK:

The scenarios below specifically demonstrate the usage of booking APIs:

1.`SingleRoomBookScenario.java`: This example demonstrates the process of booking a single room. It includes steps like getting property availability, checking room prices, and finally booking a room.

2.`MultiRoomHoldAndResumeBookScenario.java`: This example demonstrates how to hold multiple rooms for booking and then resume the booking process. It includes steps like creating a booking with hold, resuming the booking process, and verifying the booking has been resumed properly.

More scenarios are included in the examples package to demonstrate the usage of other RAPID APIs.

## Setup

1. Clone the repository.
2. Navigate to the project directory `examples`.
3. Acquire your own API key and secret from Expedia Group. Replace the placeholders `your_api_key` and `your_api_secret` in the `RapidService` class with your actual API key and secret.
4. Run `mvn clean install` to build the project and install the dependencies including the RAPID SDK.

## Running the Examples

To run the examples, navigate to the `src/main/java/com/expediagroup/sdk/rapid/examples` directory and run the `RapidSdkDemoApplication.java` file. This application runs a series of scenarios demonstrating various functionalities of the RAPID SDK. Feel free to add or remove scenarios you want to run.

## Dependencies

The project uses the following dependencies:

- `rapid-sdk`: The Expedia Group Rapid SDK.
- `log4j-api`: Apache Log4j API for logging.
- `log4j-slf4j2-impl`: Apache Log4j SLF4J Binding.

## Requirements

- Java 1.8 or higher
- Maven

## License

This project is licensed under the terms of the MIT license.