---
intro: Booking Hold and Resume
shortTitle: Booking Hold and Resume
title: Booking Hold and Resume
---
## Booking Hold and Resume

#### Rapid's Hold and Resume functionality gives you the ability to support a 2-step booking model.
Please see [here](products/rapid/lodging/booking/hold-resume) for more information on Hold and Resume.

### 1. Shop

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
PriceCheckOperationLink priceCheckOperationLink = propertyAvailability.getRooms().get(0).getRates().get(0).getBedGroups().entrySet().stream().findFirst().get().getValue().getLinks().getPriceCheck(); // selecting the first rate for the first room
PriceCheckOperationContext priceCheckOperationContext = PriceCheckOperationContext.builder().customerIp("1.2.3.4").customerSessionId("12345").build(); // fill the context as needed
PriceCheckOperation priceCheckOperation = new PriceCheckOperation(priceCheckOperationLink, priceCheckOperationContext);
Response<RoomPriceCheck> response = rapidClient.execute(priceCheckOperation);
RoomPriceCheck roomPriceCheck = response.getData();
```

### 2. Book

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
```java
PostItineraryOperationLink postItineraryOperationLink = roomPriceCheck.getLinks().getBook(); // from the previous step
PostItineraryOperationContext postItineraryOperationContext = PostItineraryOperationContext.builder().customerIp("1.2.3.4").customerSessionId("12345").build(); // fill the context as needed
PostItineraryOperation itineraryCreationOperation = new PostItineraryOperation(postItineraryOperationLink, postItineraryOperationContext, createItineraryRequest(true));
Response<ItineraryCreation> response = rapidClient.execute(itineraryCreationOperation);
ItineraryCreation itineraryCreation = response.getData();
```

#### Resume on-hold booking
```java
PutResumeBookingOperationLink putResumeBookingOperationLink = itineraryCreation.getLinks().getResume(); // from the previous step
PutResumeBookingOperationContext putResumeBookingOperationContext = PutResumeBookingOperationContext.builder().customerIp("1.2.3.4").customerSessionId("12345").build(); // fill the context as needed
PutResumeBookingOperation itineraryResumeOperation = new PutResumeBookingOperation(putResumeBookingOperationLink, putResumeBookingOperationContext);
rapidClient.execute(itineraryResumeOperation);
```
