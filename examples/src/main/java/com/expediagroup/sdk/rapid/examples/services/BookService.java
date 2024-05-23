package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.core.model.Nothing;
import com.expediagroup.sdk.core.model.Response;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BookService extends RapidService {

    public ItineraryCreation createBooking(RoomPriceCheck roomPriceCheck, List<String> occupancy) {

        String bookHref = roomPriceCheck.getLinks().getBook().getHref();

        // In the Book request, include corresponding separate instances of room in the rooms array for each room you wish to book. */
        List<CreateItineraryRequestRoom> rooms = Collections.nCopies(occupancy.size(), createRoom());

        return rapidClient.postItinerary(
                Constants.CUSTOMER_IP,
                Objects.requireNonNull(rapidClient.helpers.extractToken(bookHref)),
                CreateItineraryRequest.builder()
                        .affiliateReferenceId(UUID.randomUUID().toString().substring(0, 28))
                        .hold(false)
                        .email("john@example.com")
                        .phone(phone())
                        .rooms(rooms)
                        .payments(payments())
                        .affiliateMetadata("data_point_1:123|data_point2:This is data.")
                        .taxRegistrationNumber("12345678910")
                        .travelerHandlingInstructions("Please use the card provided for payment. Avoid cancellation as this is for a corporate traveler. Contact traveler if any issues.")
                        .build());
    }

    public ItineraryCreation createBookingWithHold(RoomPriceCheck roomPriceCheck, List<String> occupancy) {
        String bookHref = roomPriceCheck.getLinks().getBook().getHref();

        // In the Book request, include corresponding separate instances of room in the rooms array for each room you wish to book. */
        List<CreateItineraryRequestRoom> rooms = Collections.nCopies(occupancy.size(), createRoom());

        // Create a booking with hold, set hold to true
        return rapidClient.postItinerary(
                Constants.CUSTOMER_IP,
                Objects.requireNonNull(rapidClient.helpers.extractToken(bookHref)),
                CreateItineraryRequest.builder()
                        .affiliateReferenceId(UUID.randomUUID().toString().substring(0, 28))
                        .hold(true)
                        .email("john@example.com")
                        .phone(phone())
                        .rooms(rooms)
                        .payments(payments())
                        .affiliateMetadata("data_point_1:123|data_point2:This is data.")
                        .taxRegistrationNumber("12345678910")
                        .travelerHandlingInstructions("Please use the card provided for payment. Avoid cancellation as this is for a corporate traveler. Contact traveler if any issues.")
                        .build());
    }

    public Response<Nothing> resumeBooking(ItineraryCreation itineraryCreation) {
         return rapidClient.putResumeBookingWithResponse(
                 Constants.CUSTOMER_IP,
                 itineraryCreation.getItineraryId(),
                 rapidClient.helpers.extractToken(itineraryCreation.getLinks().getResume().getHref()),
                "123455656565",
                "standard"
         );
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

    /* Helper methods */
    private CreateItineraryRequestRoom createRoom() {
        return CreateItineraryRequestRoom.builder()
                .givenName("John")
                .familyName("Smith")
                .smoking(false)
                .specialRequest("Please provide a room with a view of the ocean.")
                .build();
    }

    /* Helper methods */
    private PhoneRequest phone() {
        return PhoneRequest.builder()
                .countryCode("1")
                .areaCode("487")
                .number("5550077")
                .build();
    }

    /* Helper methods */
    private List<PaymentRequest> payments() {
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

        BillingContactRequest billingContact = BillingContactRequest.builder()
                .givenName("John")
                .familyName("Smith")
                .address(address)
                .build();

        return Arrays.asList(PaymentRequest.builder()
                .type(PaymentRequest.Type.CUSTOMER_CARD)
                .number("4111111111111111")
                .securityCode("123")
                .expirationMonth("08")
                .expirationYear("2025")
                .billingContact(billingContact)
                .enrollmentDate("2018-09-15")
                .build());
    }
}
