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
import com.expediagroup.sdk.rapid.operations.CommitChangeOperationLink;
import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperationLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CommitHardChangeScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(CommitHardChangeScenario.class);
    private ShopService shopService = new ShopService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        logger.info("Running Commit Hard Change Scenario using the default profile...");

        // Shopping for properties
        logger.info("Getting property availability for test property: {}", Constants.TEST_PROPERTY_ID);

        // Shop for a single room
        List<String> occupancy = Arrays.asList("2");
        List<Property> propertyAvailabilityList = shopService.getPropertiesAvailability(occupancy, this.rapidPartnerSalesProfile).getData();

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

        // Booking a single rooms in the property
        logger.info("Booking a single rooms in test property: {}...", Constants.TEST_PROPERTY_ID);

        BookService bookService = new BookService();
        ItineraryCreation itineraryCreation = bookService.createBooking(roomPriceCheck, occupancy).getData();

        logger.info("Booking Success. Itinerary id: {}", itineraryCreation.getItineraryId());

        // Manage booking
        logger.info("Getting itinerary by itinerary id...");
        Itinerary itinerary = bookService.getReservation(itineraryCreation).getData();
        logger.info("Itinerary: {}", itinerary.getItineraryId());
        logger.info("Count of rooms booked: {}", itinerary.getRooms().size());

        // Check if booking is eligible for hard change
        logger.info("Checking if booked room is eligible for hard change...");
        GetAdditionalAvailabilityOperationLink shopForChange = itinerary.getRooms().get(0).getLinks().getShopForChange();
        if (shopForChange == null) {
            logger.info("Room is not eligible for hard change.");
            return;
        }

        logger.info("Room is eligible for hard change. Shop for change link: [{}]", shopForChange.getHref());

        // Use shop_for_change link to shop for different dates
        logger.info("Shopping for different dates using shop_for_change link...");
        Response<List<PropertyAvailability>> response = shopService.getAdditionalAvailabilityForChange(shopForChange,
                LocalDate.now().plusDays(17).toString(),
                LocalDate.now().plusDays(18).toString());

        // Check new price for change
        logger.info("Checking room prices for the change...");
        PropertyAvailability propertyAvailability = response.getData().get(0);
        RoomPriceCheck roomPriceCheckForChange = shopService.checkRoomPrices(propertyAvailability, 0, 0).getData();
        logger.info("Room Price Check for change: {}", roomPriceCheckForChange.getStatus());

        CommitChangeOperationLink commitChangeLink = roomPriceCheckForChange.getLinks().getCommit();
        if (commitChangeLink == null) {
            logger.info("Room is not eligible for hard change.");
            return;
        }

        logger.info("Room is eligible for hard change. Commit change link: [{}]", commitChangeLink.getHref());

        // Commit hard change
        Response<Nothing> commitChangeResponse = bookService.commitChange(commitChangeLink);
        logger.info("Commit hard change response status: [{}]", commitChangeResponse.getStatusCode());

        // Get updated itinerary
        logger.info("Getting updated itinerary by itinerary id...");
        Itinerary updatedItinerary = bookService.getReservation(itineraryCreation).getData();
        logger.info("Itinerary booked for dates:");
        logger.info("Checkin Date: [{}]", updatedItinerary.getRooms().get(0).getCheckin());
        logger.info("Checkout Date: [{}]", updatedItinerary.getRooms().get(0).getCheckout());
    }
}
