---
intro: PSD2 European compliance
shortTitle: PSD2 European compliance
title: PSD2 European compliance
---
## PSD2 European compliance

#### Use the SDK to ease compliance with PSD2 EEA regulations.
The Payment Services Directive 2 (PSD2) is an EEA regulation that requires changes to the check-out and booking process for all transactions involving a credit card issued by an EEA state. For more information on PSD2 please see [here](products/rapid/lodging/booking/psd2-regulation).

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
PriceCheckOperation priceCheckOperation = new PriceCheckOperation(priceCheckOperationLink);
Response<RoomPriceCheck> response = rapidClient.execute(priceCheckOperation);
RoomPriceCheck roomPriceCheck = response.getData();
```

### 2. Book

{% expandoList %}
{% expandoListItem %}
{% expandoHeading %}
Create Payment Session Request Helper Method
{% /expandoHeading %}

```java
PaymentSessionsRequest createPaymentSessionRequest() {
    PaymentSessionsRequestCustomerAccountDetails customerAccountDetails =
            PaymentSessionsRequestCustomerAccountDetails.builder()
                    .authenticationMethod(PaymentSessionsRequestCustomerAccountDetails.AuthenticationMethod.GUEST)
                    .authenticationTimestamp("YYYY-MM-DDTHH:mm:00.000Z")
                    .createDate("YYYY-MM-DD")
                    .changeDate("YYYY-MM-DD")
                    .passwordChangeDate("YYYY-MM-DD")
                    .addCardAttempts(BigDecimal.ONE)
                    .accountPurchases(BigDecimal.ONE)
                    .build();

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
                    .expirationMonth("EXPIRATION MONTH")
                    .expirationYear("EXPIRATION YEAR")
                    .billingContact(billingContact)
                    .enrollmentDate("ENTROLLMET DATE")
                    .build()
    );

    return PaymentSessionsRequest.builder()
            .version("VERSION")
            .browserAcceptHeader("*/*")
            .encodedBrowserMetadata("BROWSER METADATA")
            .preferredChallengeWindowSize(PaymentSessionsRequest.PreferredChallengeWindowSize.MEDIUM)
            .merchantUrl("MERCHANT URL")
            .customerAccountDetails(customerAccountDetails)
            .payments(payments)
            .build();
}
```

{% /expandoListItem %}
{% /expandoList %}

#### Create payment session
```java
PostPaymentSessionsOperationLink postPaymentSessionsOperationLink = roomPriceCheck.getLinks().getPaymentSession();
PostPaymentSessionsOperationContext postPaymentSessionsOperationContext = PostPaymentSessionsOperationContext.builder().customerIp("1.2.3.4").customerSessionId("12345").build(); // fill the context as needed
PostPaymentSessionsOperation paymentSessionsOperation = new PostPaymentSessionsOperation(postPaymentSessionsOperationLink, postPaymentSessionsOperationContext, createPaymentSessionRequest());
Response<PaymentSessions> paymentSessionsResponse = rapidClient.execute(paymentSessionsOperation);
PaymentSessions paymentSessions = paymentSessionsResponse.getData();
```

#### Pass the JavaScript challenge
If 2FA is required, the response will include an `encodedChallengeConfig`. The `encodedChallengeConfig` and `paymentSessionId` returned will need to be passed as parameters into the JavaScript challenge method.


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
PostItineraryOperationLink postItineraryOperationLink = roomPriceCheck.getLinks().getBook(); // from the first step
PostItineraryOperationContext postItineraryOperationContext = PostItineraryOperationContext.builder().customerIp("1.2.3.4").customerSessionId("12345").build(); // fill the context as needed
PostItineraryOperation itineraryCreationOperation = new PostItineraryOperation(postItineraryOperationLink, postItineraryOperationContext, createItineraryRequest(true));
Response<ItineraryCreation> response = rapidClient.execute(itineraryCreationOperation);
ItineraryCreation itineraryCreation = response.getData();
```

#### Complete payment session
```java
PutCompletePaymentSessionOperation putCompletePaymentSessionOperation = itineraryCreation.getLinks().getCompletePaymentSession();
PutCompletePaymentSessionOperationContext putCompletePaymentSessionOperationContext = PutCompletePaymentSessionOperationContext.builder().customerIp("1.2.3.4").customerSessionId("12345").build(); // fill the context as needed
PutCompletePaymentSessionOperation completePaymentSessionOperation = new PutCompletePaymentSessionOperation(putCompletePaymentSessionOperation, putCompletePaymentSessionOperationContext);
Response<CompletePaymentSession> completePaymentSessionResponse = rapidClient.execute(completePaymentSessionOperation);
CompletePaymentSession completePaymentSession = completePaymentSessionResponse.getData();
```

#### Resume on-hold booking
```java
PutResumeBookingOperationLink putResumeBookingOperationLink = itineraryCreation.getLinks().getResume();
PutResumeBookingOperationContext putResumeBookingOperationContext = PutResumeBookingOperationContext.builder().customerIp("1.2.3.4").customerSessionId("12345").build(); // fill the context as needed
PutResumeBookingOperation putResumeBookingOperation = new PutResumeBookingOperation(putResumeBookingOperationLink, putResumeBookingOperationContext);
rapidClient.execute(putResumeBookingOperation);
```

