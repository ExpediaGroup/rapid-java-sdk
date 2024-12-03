---
intro: Instant booking
shortTitle: Instant booking
title: Instant booking
---
## Instant booking

#### Our modular API will provide you with all the data points you and your travelers need to complete a property booking.
For more information on how the Rapid API works and what it offers for partners, see [here](products/rapid/lodging/how-it-all-works).

### 1. Shop

The Shop API returns rates and availability on all room types for specified properties (maximum of 250 properties per request). The response includes rate details such as promos, whether the rate is refundable, cancellation penalties, and a full price breakdown to meet the price display requirements for your market. For more information on this service please see [here](products/rapid/lodging/shopping).

#### Get availability
```java
GetAvailabilityOperationParams getAvailabilityOperationParams = GetAvailabilityOperationParams.builder()
    .checkin("YYYY-MM-DD")
    .checkout("YYYY-MM-DD")
    .currency("USD")
    .language("en_US")
    .countryCode("US")
    .occupancy(List.of("OCCUPANCY"))
    .propertyId(List.of("PROPERTY ID"))
    .customerIp("127.0.0.1")
    .ratePlanCount(BigDecimal.ONE)
    .salesChannel("SALES CHANNEL")
    .salesEnvironment("SALES ENVIRONMENT")
    .build();

GetAvailabilityOperation getAvailabilityOperation = new GetAvailabilityOperation(getAvailabilityOperationParams);
Response<List<Property>> propertiesResponse = rapidClient.execute(getAvailabilityOperation);
```


#### Check room prices
```java
Property property = propertiesResponse.getData().get(0);

if (!(property instanceof PropertyAvailability)) {
    return;
}

PropertyAvailability propertyAvailability = (PropertyAvailability) property;
Link propertyAvailabilityLink = propertyAvailability.getRooms().get(0).getRates().get(0).getBedGroups().entrySet().stream().findFirst().get().getValue().getLinks().getPriceCheck(); // selecting the first rate for the first room
PriceCheckOperationContext priceCheckOperationContext = PriceCheckOperationContext.builder().customerIp("1.2.3.4").customerSessionId("12345").build(); // fill the context as needed
PriceCheckOperation priceCheckOperation = new PriceCheckOperation(propertyAvailabilityLink, priceCheckOperationContext);
Response<RoomPriceCheck> response = rapidClient.execute(priceCheckOperation);
RoomPriceCheck roomPriceCheck = response.getData();

```

### 2. Book

The Booking API allows you to book rooms and rates confirmed by the Price Check response. For more information on the Rapid Booking API please see [here](products/rapid/lodging/booking).

{% expandoList %}
{% expandoListItem %}
{% expandoHeading %}
Create Itinerary Request Helper Method
{% /expandoHeading %}

```java
CreateItineraryRequest createItineraryRequest(boolean hold) {
        PhoneRequest phone =
                PhoneRequest.builder()
                        .countryCode("COUNTRY CODE")
                        .areaCode("AREA CODE")
                        .number("NUMBER")
                        .build();

        List<CreateItineraryRequestRoom> rooms = List.of(
                CreateItineraryRequestRoom.builder()
                        .givenName("John")
                        .familyName("Smith")
                        .smoking(false)
                        .specialRequest("SPECIAL REQUEST")
                        .build()
        );

        BillingContactRequestAddress address =
                BillingContactRequestAddress.builder()
                        .line1("LINE 1")
                        .line2("LINE 2")
                        .line3("LINE 3")
                        .city("CITY")
                        .stateProvinceCode("STATE CODE")
                        .countryCode("COUNTRY CODE")
                        .postalCode("POSTAL CODE")
                        .build();

        BillingContactRequest billingContact =
                BillingContactRequest.builder()
                        .givenName("John")
                        .familyName("Smith")
                        .address(address)
                        .build();

        List<PaymentRequest> payments = List.of(
                PaymentRequest.builder()
                        .type(PaymentRequest.Type.CUSTOMER_CARD)
                        .number("NUMBER")
                        .securityCode("SECURITY CODE")
                        .expirationMonth("MONTH")
                        .expirationYear("YEAR")
                        .billingContact(billingContact)
                        .enrollmentDate("ENROLLMENT_DATE")
                        .build()
        );

        return CreateItineraryRequest.builder()
                .affiliateReferenceId(UUID.randomUUID().toString().substring(0, 28))
                .hold(hold)
                .email("john@example.com")
                .phone(phone)
                .rooms(rooms)
                .payments(payments)
                .affiliateMetadata("AFFILIATE METADATA")
                .taxRegistrationNumber("TAX NUMBER")
                .travelerHandlingInstructions("INSTRUCTIONS")
                .build();
}
```

{% /expandoListItem %}
{% /expandoList %}

#### Create itinerary

The primary itinerary method of the Booking API creates a reservation for the selected property, room, rate, and occupancy. Payment information, including billing/cardholder contact information, is provided directly within the request. See [here](products/rapid/lodging/booking) for more details.

```java
Link postItineraryLink = roomPriceCheck.getLinks().getBook(); // from the previous step
PostItineraryOperationContext postItineraryOperationContext = PostItineraryOperationContext.builder().customerIp("1.2.3.4").customerSessionId("12345").build(); // fill the context as needed
PostItineraryOperation itineraryCreationOperation = new PostItineraryOperation(postItineraryLink, postItineraryOperationContext, createItineraryRequest(false));
Response<ItineraryCreation> response = rapidClient.execute(itineraryCreationOperation);
ItineraryCreation itineraryCreationResponse = response.getData();
```
