package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.models.BillingContactRequest;
import com.expediagroup.sdk.rapid.models.BillingContactRequestAddress;
import com.expediagroup.sdk.rapid.models.CreateItineraryRequest;
import com.expediagroup.sdk.rapid.models.CreateItineraryRequestRoom;
import com.expediagroup.sdk.rapid.models.Itinerary;
import com.expediagroup.sdk.rapid.models.ItineraryCreation;
import com.expediagroup.sdk.rapid.models.PaymentRequest;
import com.expediagroup.sdk.rapid.models.PhoneRequest;
import com.expediagroup.sdk.rapid.models.RoomPriceCheck;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BookService extends RapidService {

    public ItineraryCreation createBooking(RoomPriceCheck roomPriceCheck) {

        String bookHref = roomPriceCheck.getLinks().getBook().getHref();

        PhoneRequest phone =
                PhoneRequest.builder()
                        .countryCode("1")
                        .areaCode("487")
                        .number("5550077")
                        .build();

        List<CreateItineraryRequestRoom> rooms = Arrays.asList(
                CreateItineraryRequestRoom.builder()
                        .givenName("John")
                        .familyName("Smith")
                        .smoking(false)
                        .specialRequest("Top floor or away from street please")
                        .build()
        );

        BillingContactRequestAddress address =
                BillingContactRequestAddress.builder()
                        .line1("555 1st St")
                        .line2("10th Floor")
                        .line3("Unit 12")
                        .city("Seattle")
                        .stateProvinceCode("WA")
                        .countryCode("US")
                        .postalCode("98121")
                        .build();

        BillingContactRequest billingContact =
                BillingContactRequest.builder()
                        .givenName("John")
                        .familyName("Smith")
                        .address(address)
                        .build();

        List<PaymentRequest> payments = Arrays.asList(
                PaymentRequest.builder()
                        .type(PaymentRequest.Type.CUSTOMER_CARD)
                        .number("4111111111111111")
                        .securityCode("123")
                        .expirationMonth("08")
                        .expirationYear("2025")
                        .billingContact(billingContact)
                        .enrollmentDate("2018-09-15")
                        .build()
        );

        return rapidClient.postItinerary(
                Constants.CUSTOMER_IP,
                Objects.requireNonNull(rapidClient.helpers.extractToken(bookHref)),
                CreateItineraryRequest.builder()
                        .affiliateReferenceId(UUID.randomUUID().toString().substring(0, 28))
                        .hold(false)
                        .email("john@example.com")
                        .phone(phone)
                        .rooms(rooms)
                        .payments(payments)
                        .affiliateMetadata("data_point_1:123|data_point2:This is data.")
                        .taxRegistrationNumber("12345678910")
                        .travelerHandlingInstructions("Please use the card provided for payment. Avoid cancellation as this is for a corporate traveler. Contact traveler if any issues.")
                        .build());
    }

    public Itinerary getReservationByItineraryId(ItineraryCreation itineraryCreation) {
        return rapidClient.getReservationByItineraryId(
                Constants.CUSTOMER_IP,
                itineraryCreation.getItineraryId(),
                "123455656565",
                "standard",
                rapidClient.helpers.extractToken(itineraryCreation.getLinks().getRetrieve().getHref())
        );
    }
}
