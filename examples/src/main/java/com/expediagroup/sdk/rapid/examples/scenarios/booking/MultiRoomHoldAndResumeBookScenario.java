package com.expediagroup.sdk.rapid.examples.scenarios.booking;

import com.expediagroup.sdk.core.model.Nothing;
import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.BookService;
import com.expediagroup.sdk.rapid.examples.services.ShopService;
import com.expediagroup.sdk.rapid.models.Itinerary;
import com.expediagroup.sdk.rapid.models.ItineraryCreation;
import com.expediagroup.sdk.rapid.models.Property;
import com.expediagroup.sdk.rapid.models.PropertyAvailability;
import com.expediagroup.sdk.rapid.models.RoomPriceCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class MultiRoomHoldAndResumeBookScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(MultiRoomHoldAndResumeBookScenario.class);
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;
    private ShopService shopService = new ShopService();

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        // Shopping for properties
        /*
         * To request multiple rooms (of the same type), include one instance of occupancy for each room requested.
         * Up to 8 rooms may be requested or booked at once.
         */
        logger.info("Getting property availability for test property: [{}]", Constants.TEST_PROPERTY_ID);
        List<String> occupancy = Arrays.asList("2", "2");
        List<Property> propertyAvailabilityList = shopService.getMultipleRoomsPropertiesAvailability(this.rapidPartnerSalesProfile, occupancy).getData();

        if (propertyAvailabilityList == null || propertyAvailabilityList.isEmpty()) {
            throw new IllegalStateException("No property availability found for the test property.");
        }

        logger.info("Property Availability found for property id: [{}] with status: [{}]",
                propertyAvailabilityList.get(0).getPropertyId(),
                propertyAvailabilityList.get(0).getStatus());

        // Check room prices for the property
        logger.info("Checking room prices for property id: [{}]...", Constants.TEST_PROPERTY_ID);
        PropertyAvailability propertyAvailability = (PropertyAvailability) propertyAvailabilityList.get(0);
        RoomPriceCheck roomPriceCheck = null;

        roomPriceCheck = shopService.checkRoomPrices(propertyAvailability, 0, 0).getData();
        logger.info("Room price check status: [{}]", roomPriceCheck.getStatus());

        /*
         * Book multiple rooms in the property.
         * The Booking link from your previous Price Check response expires after a short period.
         * If you receive an HTTP 503 error upon your first attempt, the link has likely expired.
         * Obtain a new link and attempt your booking again.
         */
        logger.info("Booking 2 rooms with [hold=true] in test property: [{}]...", Constants.TEST_PROPERTY_ID);
        BookService bookService = new BookService();
        ItineraryCreation itineraryCreation = bookService.createBookingWithHold(roomPriceCheck, occupancy).getData();
        logger.info("Booking with hold success. Itinerary id: [{}]. Link to resume booking: [{}]",
                itineraryCreation.getItineraryId(), itineraryCreation.getLinks().getResume().getHref());

        // Resume the booking process
        logger.info("Resuming the booking process...");
        Response<Nothing> response = bookService.resumeBooking(itineraryCreation);
        logger.info("Resume booking response status: [{}]", response.getStatusCode());

        // Make a retrieve call to verify the booking has been resumed properly.
        logger.info("Getting itinerary by itinerary id: [{}] to verify the booking has been resumed successfully...",
                itineraryCreation.getItineraryId());
        Itinerary itinerary = bookService.getReservationByItineraryId(itineraryCreation).getData();

        logger.info("Itinerary rooms status after resume booking:");
        itinerary.getRooms().forEach(room ->
            logger.info("Room: [{}], Status: [{}]", room.getId(), room.getStatus())
        );
    }
}
