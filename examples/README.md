# Rapid Java SDK Examples

This repository contains examples of how to use the Expedia Group Rapid Java SDK. The examples are written in Java and use Maven for dependency management.

## Examples

The example implementation provided in the examples package demonstrate different scenarios utilizing various RAPID APIs through the SDK:

The scenarios below specifically demonstrate the usage of booking APIs:

1.[`SingleRoomBookScenario.java`](src/main/java/com/expediagroup/sdk/rapid/examples/scenarios/booking/SingleRoomBookScenario.java): This example demonstrates the process of booking a single room. It includes steps like getting property availability, checking room prices, and finally booking a room.

2.[`MultiRoomHoldAndResumeBookScenario.java`](src/main/java/com/expediagroup/sdk/rapid/examples/scenarios/booking/MultiRoomHoldAndResumeBookScenario.java): This example demonstrates how to hold multiple rooms for booking and then resume the booking process. It includes steps like creating a booking with hold, resuming the booking process, and verifying the booking has been resumed properly.

More scenarios are included in the [examples package](src/main/java/com/expediagroup/sdk/rapid/examples/scenarios) to demonstrate the usage of other RAPID APIs.

## Requirements
- Ensure you have a valid API key and secret from Expedia Group. Check [Getting started with RAPID](https://developers.expediagroup.com/docs/products/rapid/setup/getting-started#getting-started-with-rapid|Getting) for more info.
- Java 1.8 or higher
- Maven

## Setup
1. Clone the repository.
2. Navigate to the project directory `examples`.
3. Replace the placeholders `your_api_key` and `your_api_secret` in the `RapidService` class with your actual API key and secret.
4. Run `mvn clean install` to build the project and install the dependencies including the RAPID SDK.

## Running the Examples

To run the examples, navigate to the `src/main/java/com/expediagroup/sdk/rapid/examples` directory and run the `RapidSdkDemoApplication.java` file. This application runs a series of scenarios demonstrating various functionalities of the RAPID SDK. Feel free to add or remove scenarios you want to run.

## License

This project is licensed under the Apache License v2.0 - see the [LICENSE](LICENSE) for details.
