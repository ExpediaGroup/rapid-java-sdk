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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class BookService extends RapidService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    public ItineraryCreation createInstantBooking(RoomPriceCheck roomPriceCheck) {

        if (roomPriceCheck == null) {
            logger.error("Room price check is null");
            return null;
        }

        if (roomPriceCheck.getLinks() == null) {
            logger.error("Links in room price check is null");
            return null;
        }

        if (roomPriceCheck.getLinks().getBook() == null) {
            logger.error("Book in links is null");
            return null;
        }

        String bookHref = roomPriceCheck.getLinks().getBook().getHref();
        if (bookHref == null || bookHref.isEmpty()) {
            logger.error("Href in book is null");
            return null;
        }

        PhoneRequest phone =
                PhoneRequest.builder()
                        .countryCode("1")
                        .areaCode("487")
                        .number("5550077")
                        .build();

        List<CreateItineraryRequestRoom> rooms = List.of(
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

        List<PaymentRequest> payments = List.of(
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
                "5.5.5.5",
                rapidClient.helpers.extractToken(bookHref),
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
        if (itineraryCreation.getItineraryId() == null) {
            logger.error("Itinerary id is null");
            return null;
        }

        if (itineraryCreation.getLinks() == null) {
            logger.error("Links in itinerary creation is null");
            return null;
        }

        if (itineraryCreation.getLinks().getRetrieve() == null) {
            logger.error("Retrieve in links is null");
            return null;
        }

        if (itineraryCreation.getLinks().getRetrieve().getHref() == null) {
            logger.error("Href in retrieve is null");
            return null;
        }

        return rapidClient.getReservationByItineraryId(
                Constants.CUSTOMER_IP,
                itineraryCreation.getItineraryId(),
                "123455656565",
                "standard",
                rapidClient.helpers.extractToken(itineraryCreation.getLinks().getRetrieve().getHref())
        );
    }
}
