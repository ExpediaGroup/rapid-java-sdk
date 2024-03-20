package com.expediagroup.sdk.rapid.examples;

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

public class RapidSdkJavaB2CDemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(RapidSdkJavaB2CDemoApplication.class);

    public static void main(String[] args) {

        logger.info("=======================================================================================");
        logger.info("=======================================================================================");
        logger.info("==                                                                                   ==");
        logger.info("==         Howdy! This is a demonstration of Expedia Group RAPID SDK, Enjoy!         ==");
        logger.info("==                                                                                   ==");
        logger.info("=======================================================================================");
        logger.info("=======================================================================================");

        // Shopping for properties
        logger.info(String.format("Getting property availability for test property: %s", Constants.TEST_PROPERTY_ID));
        ShopService shopService = new ShopService();
        List<Property> propertyAvailabilityList = shopService.getPropertiesAvailability();

        if (propertyAvailabilityList != null && !propertyAvailabilityList.isEmpty()) {
            logger.info(String.format("Property Availability: %s", propertyAvailabilityList.get(0).getStatus()));
        } else {
            logger.error("No property availability found for the test property.");
            return;
        }

        // Checking room prices for the property
        logger.info(String.format("Checking room prices for the property: %s...", Constants.TEST_PROPERTY_ID));
        Property property = propertyAvailabilityList.get(0);
        RoomPriceCheck roomPriceCheck = null;
        if (property instanceof PropertyAvailability) {
            PropertyAvailability propertyAvailability = (PropertyAvailability) property;
            roomPriceCheck = shopService.checkRoomPrices(propertyAvailability);
        }

        if (roomPriceCheck != null) {
            logger.info(String.format("Room Price Check: %s", roomPriceCheck.getStatus()));
        } else {
            logger.error("No room price check found for the property.");
            return;
        }

        // Booking the property
        logger.info(String.format("Booking a room in test property: %s...", Constants.TEST_PROPERTY_ID));
        BookService bookService = new BookService();
        ItineraryCreation itineraryCreation = bookService.createInstantBooking(roomPriceCheck);

        if (itineraryCreation != null) {
            logger.info("Booking Success. Itinerary id: " + itineraryCreation.getItineraryId());
        } else {
            logger.error("No itinerary creation found for the property.");
            return;
        }

        // Manage booking
        logger.info("Getting itinerary by itinerary id...");
        Itinerary itinerary = bookService.getReservationByItineraryId(itineraryCreation);
        if (itinerary != null) {
            logger.info("Itinerary: " + itinerary.getItineraryId());
        } else {
            logger.error("No itinerary found for the property.");
        }
    }
}
