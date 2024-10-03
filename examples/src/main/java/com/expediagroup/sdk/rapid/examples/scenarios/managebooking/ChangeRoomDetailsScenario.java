package com.expediagroup.sdk.rapid.examples.scenarios.managebooking;

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

public class ChangeRoomDetailsScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(ChangeRoomDetailsScenario.class);
    private ShopService shopService = new ShopService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        logger.info("Running Change Room Details Scenario using the default profile...");

        // Shopping for properties
        logger.info("Getting property availability for test property: {}", Constants.TEST_PROPERTY_ID);

        List<Property> propertyAvailabilityList = shopService.getPropertiesAvailability(Arrays.asList("2"), this.rapidPartnerSalesProfile).getData();

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
        logger.info("Room info before change:");
        itinerary.getRooms().forEach(room -> {
            logger.info("----Room: [{}]----", room.getId());
            logger.info("Given name: [{}]", room.getGivenName());
            logger.info("Family name: [{}]", room.getFamilyName());
            logger.info("Smoking: [{}]", room.getSmoking());
        });

        // Change room details for first room in booking
        logger.info("Change room details for room [id:{}] in itinerary...", itinerary.getRooms().get(0).getId());
        Response<Nothing> response = bookService.changeRoomDetails(itinerary, 0);
        logger.info("Change room details for room [id:{}] response status: [{}]", itinerary.getRooms().get(0).getId(), response.getStatusCode());

        // Get updated itinerary
        logger.info("Getting updated itinerary by itinerary id...");
        Itinerary updatedItinerary = bookService.getReservation(itineraryCreation).getData();
        logger.info("Itinerary rooms details after change room:");
        updatedItinerary.getRooms().forEach(room -> {
            logger.info("----Room: [{}]----", room.getId());
            logger.info("Given name: [{}]", room.getGivenName());
            logger.info("Family name: [{}]", room.getFamilyName());
            logger.info("Smoking: [{}]", room.getSmoking());
        });
    }
}
