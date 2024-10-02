package com.expediagroup.sdk.rapid.examples.scenarios.booking;

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

public class SingleRoomBookScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(SingleRoomBookScenario.class);
    private ShopService shopService = new ShopService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        // Shopping for properties
        logger.info("Getting property availability for test property: {}", Constants.TEST_PROPERTY_ID);

        List<Property> propertyAvailabilityList = shopService.getSingleRoomPropertiesAvailability(this.rapidPartnerSalesProfile).getData();

        if (propertyAvailabilityList == null || propertyAvailabilityList.isEmpty()) {
            throw new IllegalStateException("No property availability found for the test property.");
        }

        logger.info("Property Availability: {}", propertyAvailabilityList.get(0).getStatus());

        // Checking room prices for the property
        logger.info("Checking room prices for the property: {}...", Constants.TEST_PROPERTY_ID);
        Property property = propertyAvailabilityList.get(0);
        RoomPriceCheck roomPriceCheck = null;

        if (property instanceof PropertyAvailability) {
            PropertyAvailability propertyAvailability = (PropertyAvailability) property;
            roomPriceCheck = shopService.checkRoomPrices(propertyAvailability, 0, 0).getData();
            logger.info("Room Price Check: {}", roomPriceCheck.getStatus());
        }

        // Booking a single room in the property
        logger.info("Booking a room in test property: {}...", Constants.TEST_PROPERTY_ID);

        BookService bookService = new BookService();
        ItineraryCreation itineraryCreation = bookService.createBooking(roomPriceCheck, Arrays.asList("2")).getData();

        logger.info("Booking Success. Itinerary id: {}", itineraryCreation.getItineraryId());

        // Manage booking
        logger.info("Getting itinerary by itinerary id...");
        Itinerary itinerary = bookService.getReservation(itineraryCreation).getData();
        logger.info("Itinerary: {}", itinerary.getItineraryId());
        logger.info("Count of rooms booked: {}", itinerary.getRooms().size());
    }
}
