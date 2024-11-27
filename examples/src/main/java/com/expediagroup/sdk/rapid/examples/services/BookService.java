package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.core.model.Nothing;
import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.rapid.models.BillingContactRequest;
import com.expediagroup.sdk.rapid.models.BillingContactRequestAddress;
import com.expediagroup.sdk.rapid.models.ChangeRoomDetailsRequest;
import com.expediagroup.sdk.rapid.models.CompletePaymentSession;
import com.expediagroup.sdk.rapid.models.CreateItineraryRequest;
import com.expediagroup.sdk.rapid.models.CreateItineraryRequestRoom;
import com.expediagroup.sdk.rapid.models.Itinerary;
import com.expediagroup.sdk.rapid.models.ItineraryCreation;
import com.expediagroup.sdk.rapid.models.ItineraryCreationLinks;
import com.expediagroup.sdk.rapid.models.PaymentRequest;
import com.expediagroup.sdk.rapid.models.PaymentSessions;
import com.expediagroup.sdk.rapid.models.PaymentSessionsRequest;
import com.expediagroup.sdk.rapid.models.PhoneRequest;
import com.expediagroup.sdk.rapid.models.RoomPriceCheck;
import com.expediagroup.sdk.rapid.operations.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BookService extends RapidService {

    public Response<ItineraryCreation> createBooking(RoomPriceCheck roomPriceCheck, List<String> occupancy) {

        // In the Book request, include corresponding separate instances of room in the rooms array for each room you wish to book. */
        List<CreateItineraryRequestRoom> rooms = Collections.nCopies(occupancy.size(), createRoom());

        CreateItineraryRequest createItineraryRequest = CreateItineraryRequest.builder()
                .affiliateReferenceId(UUID.randomUUID().toString().substring(0, 28))
                .hold(false)
                .email("john@example.com")
                .phone(phone())
                .rooms(rooms)
                .payments(payments())
                .affiliateMetadata("data_point_1:123|data_point2:This is data.")
                .taxRegistrationNumber("12345678910")
                .travelerHandlingInstructions("Please use the card provided for payment. Avoid cancellation as this is for a corporate traveler. Contact traveler if any issues.")
                .build();

        PostItineraryOperationLink bookLink = roomPriceCheck.getLinks().getBook();
        if (bookLink == null) throw new IllegalStateException("Book link not found");

        PostItineraryOperationContext postItineraryOperationContext =
                PostItineraryOperationContext.builder().customerIp("127.0.0.1").build();

        PostItineraryOperation operation = new PostItineraryOperation(bookLink, postItineraryOperationContext, createItineraryRequest);
        return rapidClient.execute(operation);
    }

    public CompletableFuture<Response<ItineraryCreation>> asyncCreateBooking(RoomPriceCheck roomPriceCheck, List<String> occupancy) {

        // In the Book request, include corresponding separate instances of room in the rooms array for each room you wish to book. */
        List<CreateItineraryRequestRoom> rooms = Collections.nCopies(occupancy.size(), createRoom());

        CreateItineraryRequest createItineraryRequest = CreateItineraryRequest.builder()
                .affiliateReferenceId(UUID.randomUUID().toString().substring(0, 28))
                .hold(false)
                .email("john@example.com")
                .phone(phone())
                .rooms(rooms)
                .payments(payments())
                .affiliateMetadata("data_point_1:123|data_point2:This is data.")
                .taxRegistrationNumber("12345678910")
                .travelerHandlingInstructions("Please use the card provided for payment. Avoid cancellation as this is for a corporate traveler. Contact traveler if any issues.")
                .build();

        PostItineraryOperationLink bookLink = roomPriceCheck.getLinks().getBook();
        if (bookLink == null) throw new IllegalStateException("Book link not found");

        PostItineraryOperationContext postItineraryOperationContext =
                PostItineraryOperationContext.builder().customerIp("127.0.0.1").build();

        PostItineraryOperation operation = new PostItineraryOperation(bookLink, postItineraryOperationContext, createItineraryRequest);

        return rapidClient.executeAsync(operation);
    }

    public Response<ItineraryCreation> createBookingWithHold(RoomPriceCheck roomPriceCheck, List<String> occupancy) {

        // In the Book request, include corresponding separate instances of room in the rooms array for each room you wish to book. */
        List<CreateItineraryRequestRoom> rooms = Collections.nCopies(occupancy.size(), createRoom());

        // Create a booking with hold, set hold to true in the request
        CreateItineraryRequest createItineraryRequest = CreateItineraryRequest.builder()
                .affiliateReferenceId(UUID.randomUUID().toString().substring(0, 28))
                .hold(true)
                .email("john@example.com")
                .phone(phone())
                .rooms(rooms)
                .payments(payments())
                .affiliateMetadata("data_point_1:123|data_point2:This is data.")
                .taxRegistrationNumber("12345678910")
                .travelerHandlingInstructions("Please use the card provided for payment. Avoid cancellation as this is for a corporate traveler. Contact traveler if any issues.")
                .build();

        PostItineraryOperationLink bookLink = roomPriceCheck.getLinks().getBook();
        if (bookLink == null) throw new IllegalStateException("Book link not found");


        PostItineraryOperationContext postItineraryOperationContext =
                PostItineraryOperationContext.builder().customerIp("127.0.0.1").build();

        PostItineraryOperation operation = new PostItineraryOperation(bookLink, postItineraryOperationContext, createItineraryRequest);
        return rapidClient.execute(operation);
    }

    public Response<Nothing> resumeBooking(ItineraryCreation itineraryCreation) {
        ItineraryCreationLinks itineraryCreationLinks = itineraryCreation.getLinks();
        if (itineraryCreationLinks == null) throw new IllegalStateException("itineraryCreationLinks not found");

        PutResumeBookingOperationLink resumeLink = itineraryCreationLinks.getResume();
        if (resumeLink == null) throw new IllegalStateException("Resume link not found");

        PutResumeBookingOperationContext putResumeBookingOperationContext =
                PutResumeBookingOperationContext.builder().customerIp("127.0.0.1").build();

        return rapidClient.execute(new PutResumeBookingOperation(resumeLink, putResumeBookingOperationContext));
    }

    public Response<Itinerary> getReservation(ItineraryCreation itineraryCreation) {
        GetReservationByItineraryIdOperationLink retrieveLink = itineraryCreation.getLinks().getRetrieve();
        if (retrieveLink == null) throw new IllegalStateException("Retrieve link not found");

        GetReservationByItineraryIdOperationContext getReservationByItineraryIdOperationContext =
                GetReservationByItineraryIdOperationContext.builder().customerIp("127.0.0.1").build();

        return rapidClient.execute(new GetReservationByItineraryIdOperation(retrieveLink, getReservationByItineraryIdOperationContext  ));
    }

    public Response<Nothing> cancelHeldReservation(ItineraryCreation itineraryCreation) {
        ItineraryCreationLinks itineraryCreationLinks = itineraryCreation.getLinks();
        if (itineraryCreationLinks == null) throw new RuntimeException();

        DeleteHeldBookingOperationLink cancelLink = itineraryCreationLinks.getCancel();
        if (cancelLink == null) throw new IllegalStateException("Cancel link not found");

        DeleteHeldBookingOperationContext deleteHeldBookingOperationContext =
                DeleteHeldBookingOperationContext.builder().customerIp("127.0.0.1").build();

        return rapidClient.execute(new DeleteHeldBookingOperation(cancelLink, deleteHeldBookingOperationContext));
    }

    public Response<Nothing> deleteRoom(Itinerary itinerary, int roomIndex) {
        DeleteRoomOperationLink cancelRoomLink = itinerary.getRooms().get(roomIndex).getLinks().getCancel();

        if (cancelRoomLink == null) throw new IllegalStateException("Cancel room link not found");

        DeleteRoomOperation deleteRoomOperation = new DeleteRoomOperation(cancelRoomLink,
                DeleteRoomOperationContext.builder().customerIp("127.0.0.1").build());

        return rapidClient.execute(deleteRoomOperation);
    }

    public Response<Nothing> changeRoomDetails(Itinerary itinerary, int roomIndex) {
        ChangeRoomDetailsOperationLink changeRoomDetailsLink = itinerary.getRooms().get(roomIndex).getLinks().getChange();

        if (changeRoomDetailsLink == null) throw new IllegalStateException("Change room link not found");

        ChangeRoomDetailsOperation changeRoomDetailsOperation = new ChangeRoomDetailsOperation(changeRoomDetailsLink,
                ChangeRoomDetailsOperationContext.builder().customerIp("127.0.0.1").build(),
                ChangeRoomDetailsRequest.builder()
                        .givenName("Jane")
                        .familyName("Doe")
                        .build());

        return rapidClient.execute(changeRoomDetailsOperation);
    }

    public CompletableFuture<Response<Itinerary>> asyncGetReservation(ItineraryCreation itineraryCreation) {

        GetReservationByItineraryIdOperationLink retrieveLink = itineraryCreation.getLinks().getRetrieve();
        if (retrieveLink == null) throw new IllegalStateException("Retrieve link not found");

        GetReservationByItineraryIdOperationContext getReservationByItineraryIdOperationContext =
                GetReservationByItineraryIdOperationContext.builder().customerIp("127.0.0.1").build();

        return rapidClient.executeAsync(new GetReservationByItineraryIdOperation(retrieveLink, getReservationByItineraryIdOperationContext));
    }

    public Response<CompletePaymentSession> completePaymentSession(ItineraryCreation itineraryCreation) {
        PutCompletePaymentSessionOperationLink putCompletePaymentSessionLink = itineraryCreation.getLinks().getCompletePaymentSession();
        if (putCompletePaymentSessionLink == null) throw new IllegalStateException("Complete payment session link not found");

        PutCompletePaymentSessionOperationContext putCompletePaymentSessionOperationContext =
                PutCompletePaymentSessionOperationContext.builder().customerIp("127.0.0.1").build();

        return rapidClient.execute(new PutCompletePaymentSessionOperation(putCompletePaymentSessionLink,
                putCompletePaymentSessionOperationContext));
    }

    public Response<PaymentSessions> postPaymentSession(RoomPriceCheck roomPriceCheck) {
        PostPaymentSessionsOperationLink postPaymentSessionLink = roomPriceCheck.getLinks().getPaymentSession();
        if (postPaymentSessionLink == null) throw new IllegalStateException("Post payment session link not found");

        PostPaymentSessionsOperationContext postPaymentSessionOperationContext =
                PostPaymentSessionsOperationContext.builder().customerIp("127.0.0.1").build();

        return rapidClient.execute(new PostPaymentSessionsOperation(postPaymentSessionLink,
                postPaymentSessionOperationContext, PaymentSessionsRequest.builder().build()));
    }

    public Response<Nothing> commitChange(CommitChangeOperationLink commitChangeLink) {
        return rapidClient.execute(new CommitChangeOperation(commitChangeLink, null, null));
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
