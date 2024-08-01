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
import java.util.concurrent.CompletableFuture;

public class AsyncSingleRoomBookScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(AsyncSingleRoomBookScenario.class);
    private ShopService shopService = new ShopService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        // Shopping for properties
        logger.info("Async call - Getting property availability for test property: {}", Constants.TEST_PROPERTY_ID);

        shopService.asyncGetSingleRoomPropertiesAvailability(this.rapidPartnerSalesProfile)
                .thenApply(response -> response.getData())
                .thenCompose(this::checkRoomPrices)
                .thenCompose(this::bookRoom)
                .thenCompose(this::getItinerary)
                .thenAccept(itinerary -> {
                    if (itinerary != null) {
                        logger.info("Itinerary: {}", itinerary.getItineraryId());
                    }
                });

    }

    private CompletableFuture<Itinerary> getItinerary(ItineraryCreation itineraryCreation) {
        if (itineraryCreation == null) {
            return CompletableFuture.completedFuture(null);
        }

        logger.info("Booking Success. Itinerary id: {}", itineraryCreation.getItineraryId());

        // Manage booking
        logger.info("Getting itinerary by itinerary id...");
        BookService bookService = new BookService();
        return bookService.asyncGetReservationByItineraryId(itineraryCreation)
                .thenApply(response -> response.getData());
    }

    private CompletableFuture<ItineraryCreation> bookRoom(RoomPriceCheck roomPriceCheck) {
        if (roomPriceCheck == null) {
            return CompletableFuture.completedFuture(null);
        }

        logger.info("Room Price Check: {}", roomPriceCheck.getStatus());

        // Booking a single room in the property
        logger.info("Booking a room in test property: {}...", Constants.TEST_PROPERTY_ID);

        BookService bookService = new BookService();
        return bookService.asyncCreateBooking(roomPriceCheck, Arrays.asList("2"))
                .thenApply(response -> response.getData());
    }

    private CompletableFuture<RoomPriceCheck> checkRoomPrices(List<Property> propertyAvailabilityList) {
        if (propertyAvailabilityList == null || propertyAvailabilityList.isEmpty()) {
            logger.error("No property availability found for the test property.");
            return CompletableFuture.completedFuture(null);
        }

        logger.info("Property Availability: {}", propertyAvailabilityList.get(0).getStatus());

        // Checking room prices for the property
        logger.info("Checking room prices for the property: {}...", Constants.TEST_PROPERTY_ID);
        Property property = propertyAvailabilityList.get(0);
        if (property instanceof PropertyAvailability) {
            PropertyAvailability propertyAvailability = (PropertyAvailability) property;
            return shopService.asyncCheckRoomPrices(propertyAvailability, 0, 0)
                    .thenApply(response -> response.getData());
        } else {
            return CompletableFuture.completedFuture(null);
        }
    }
}
