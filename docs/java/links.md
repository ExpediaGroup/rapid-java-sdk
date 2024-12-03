---
intro: Introducing Links
shortTitle: Introducing Links
title: Introducing Links
tags:
  - new
---

# Introducing Links

## What is a Link?

RapidAPI consists of several API resources that enable you to create an end-to-end booking experience for your
travelers.
This means you'll use these resources in sequence to build complete transactions, such as **shopping**,
**price checking**, **booking**, and **managing bookings**.

To streamline these multistep transactions, RapidAPI employs a powerful tool called `Link`.

A `Link` is a reference to a related resource, acting as a guide that directs developers to the next relevant API
resource needed to seamlessly progress through the transaction.

By leveraging Links from previous operations within the Rapid SDK, developers can swiftly create and execute new
operations, bypassing the typical setup process and significantly enhancing efficiency.

{% messageCard type="info" %}
{% messageCardHeader %}
Note
{% /messageCardHeader %}
This feature is available in the `rapid-sdk` v4.3.0 and later.
{% /messageCard %}

## Why to use a Link?

`Link` is a convenient way to navigate through the Rapid API operations, without having to manually create the next
operation. You can extract a `Link` from the response of the previous operation and use it to create the next operation.

A link will benefit you in the following ways:

- **Saves time:** You don't have to manually create the next operation by setting up the request parameters and the
  operation.
- **Simplifies the process:** You can easily navigate through the Rapid API operations by using the `Link` from the
  previous operation, without having to extract the needed information manually.
- **Reduces errors:** By using the `Link`, you can avoid errors that might occur when manually creating the next
  operation.

## How to use a Link?

To get a `Link`, you need to extract it from the previous `Operation` response.
Then, you can create the next `Operation` from the `Link` and execute it with the `RapidClient`.

For example, you can create a `Link` from the response of a `GetAvailabilityOperation` and use it in creating
a `PriceCheckOperation`:

```java
// 1. Create and execute the GetAvailabilityOperation (The first operation)
GetAvailabilityOperation getAvailabilityOperation = new GetAvailabilityOperation(getAvailabilityOperationParams);
Response<List<Property>> propertiesResponse = rapidClient.execute(getAvailabilityOperation);

// 2a. Select the needed property from the response (Here, we select the first property)
Property property = propertiesResponse.getData().get(0);

// 2b. Make sure the property is an instance of PropertyAvailability
if (!(property instanceof PropertyAvailability)) {
    return;
}

PropertyAvailability propertyAvailability = (PropertyAvailability) property;

// 3a. Extract the priceCheck link from PropertyAvailability operation (Here, we select the first rate for the first room, then get the priceCheck link)
Link priceCheckLink = propertyAvailability.getRooms().get(0).getRates().get(0).getBedGroups().entrySet().stream().findFirst().get().getValue().getLinks().getPriceCheck();

// 3b. Create the needed context for the PriceCheckOperation
PriceCheckOperationContext priceCheckOperationContext = PriceCheckOperationContext.builder().customerIp("1.2.3.4").build(); // fill the context as needed

// 4. Create and execute the PriceCheckOperation using the Link
PriceCheckOperation priceCheckOperation = new PriceCheckOperation(priceCheckLink, priceCheckOperationContext);
Response<RoomPriceCheck> roomPriceCheckResponse = rapidClient.execute(priceCheckOperation);
// ...
```

Another example would be to create a `Link` from the response of a `PriceCheckOperation` and use it in creating a
booking.

```java
// 1. Get the RoomPriceCheck from the previous step
RoomPriceCheck roomPriceCheck = roomPriceCheckResponse.getData(); // from the previous step

// 2a. Extract the Link from the RoomPriceCheck
Link postItineraryLink = roomPriceCheck.getLinks().getBook();

// 2b. Create the needed context for the PostItineraryOperation
PostItineraryOperationContext postItineraryOperationContext = PostItineraryOperationContext.builder().customerIp("1.2.3.4").build(); // fill the context as needed

// 2c. Create the CreateItineraryRequest
CreateItineraryRequest createItineraryRequest = CreateItineraryRequest.builder().build(); // fill the request as needed

// 3. Create and execute the PostItineraryOperation using the Link
PostItineraryOperation postItineraryOperation = new PostItineraryOperation(postItineraryLink, postItineraryOperationContext, createItineraryRequest);

// 4. Execute the PostItineraryOperation
Response<ItineraryCreation> itineraryCreationResponse = rapidClient.execute(postItineraryOperation);
ItineraryCreation itineraryCreation = itineraryCreationResponse.getData();
```

For more examples on how to use `Link`, see the [Usage Examples](products/rapid/sdk/java/usage-examples) section.

