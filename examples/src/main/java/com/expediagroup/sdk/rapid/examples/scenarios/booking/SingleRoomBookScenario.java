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

import java.util.List;

public class SingleRoomBookScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(SingleRoomBookScenario.class);
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        // Shopping for properties
        logger.info("Getting property availability for test property: {}", Constants.TEST_PROPERTY_ID);
        ShopService shopService = new ShopService();
        List<Property> propertyAvailabilityList = shopService.getPropertiesAvailability(this.rapidPartnerSalesProfile);

        if (propertyAvailabilityList != null && !propertyAvailabilityList.isEmpty()) {
            logger.info("Property Availability: {}", propertyAvailabilityList.get(0).getStatus());
        } else {
            logger.error("No property availability found for the test property.");
            return;
        }

        // Checking room prices for the property
        logger.info("Checking room prices for the property: {}...", Constants.TEST_PROPERTY_ID);
        Property property = propertyAvailabilityList.get(0);
        RoomPriceCheck roomPriceCheck = null;

        if (property instanceof PropertyAvailability) {
            PropertyAvailability propertyAvailability = (PropertyAvailability) property;
            roomPriceCheck = shopService.checkRoomPrices(propertyAvailability);
            logger.info("Room Price Check: {}", roomPriceCheck.getStatus());
        }

        // Booking the property
        logger.info("Booking a room in test property: {}...", Constants.TEST_PROPERTY_ID);

        BookService bookService = new BookService();
        ItineraryCreation itineraryCreation = bookService.createBooking(roomPriceCheck);

        logger.info("Booking Success. Itinerary id: {}", itineraryCreation.getItineraryId());

        // Manage booking
        logger.info("Getting itinerary by itinerary id...");
        Itinerary itinerary = bookService.getReservationByItineraryId(itineraryCreation);
        logger.info("Itinerary: {}", itinerary.getItineraryId());
    }
}
