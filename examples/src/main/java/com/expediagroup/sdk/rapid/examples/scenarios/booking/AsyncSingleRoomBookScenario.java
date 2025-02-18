package com.expediagroup.sdk.rapid.examples.scenarios.booking;

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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Scenario for booking a single room asynchronously.
 */
public class AsyncSingleRoomBookScenario implements RapidScenario {

  private static final Logger logger = LoggerFactory.getLogger(AsyncSingleRoomBookScenario.class);
  private ShopService shopService = new ShopService();
  private RapidPartnerSalesProfile rapidPartnerSalesProfile;

  /**
   * Sets the sales profile for the scenario.
   *
   * @param rapidPartnerSalesProfile the sales profile to set
   */
  @Override
  public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
    this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
  }

  /**
   * Runs the scenario to book a single room asynchronously.
   */
  @Override
  public void run() {

    logger.info(
        "Running Book Single Room Scenario using the default profile in asynchronous manner...");

    // Shopping for properties
    logger.info("Async call - Getting property availability for test property: {}",
        Constants.TEST_PROPERTY_ID);

    shopService.asyncGetSingleRoomPropertiesAvailability(this.rapidPartnerSalesProfile)
        .thenApply(Response::getData)
        .thenCompose(this::checkRoomPrices)
        .thenCompose(this::bookRoom)
        .thenCompose(this::getItinerary)
        .thenAccept(itinerary -> {
          if (itinerary == null) {
            throw new IllegalStateException("Itinerary is null");
          }
          logger.info("Itinerary: {}", itinerary.getItineraryId());
        });

  }

  /**
   * Retrieves the itinerary for the booking.
   *
   * @param itineraryCreation the itinerary creation details
   * @return a CompletableFuture with the itinerary
   */
  private CompletableFuture<Itinerary> getItinerary(ItineraryCreation itineraryCreation) {
    if (itineraryCreation == null) {
      throw new IllegalStateException("ItineraryCreation is null");
    }

    logger.info("Booking Success. Itinerary id: {}", itineraryCreation.getItineraryId());

    // Manage booking
    logger.info("Getting itinerary by itinerary id...");
    BookService bookService = new BookService();
    return bookService.asyncGetReservation(itineraryCreation)
        .thenApply(response -> response.getData());
  }

  /**
   * Books a room based on the room price check.
   *
   * @param roomPriceCheck the room price check details
   * @return a CompletableFuture with the itinerary creation details
   */
  private CompletableFuture<ItineraryCreation> bookRoom(RoomPriceCheck roomPriceCheck) {
    if (roomPriceCheck == null) {
      throw new IllegalStateException("Room Price Check is null");
    }

    logger.info("Room Price Check: {}", roomPriceCheck.getStatus());

    // Booking a single room in the property
    logger.info("Booking a room in test property: {}...", Constants.TEST_PROPERTY_ID);

    BookService bookService = new BookService();
    return bookService.asyncCreateBooking(roomPriceCheck, Arrays.asList("2"))
        .thenApply(response -> response.getData());
  }

  /**
   * Checks the room prices for the available properties.
   *
   * @param propertyAvailabilityList the list of available properties
   * @return a CompletableFuture with the room price check details
   */
  private CompletableFuture<RoomPriceCheck> checkRoomPrices(
      List<Property> propertyAvailabilityList) {
    if (propertyAvailabilityList == null || propertyAvailabilityList.isEmpty()) {
      throw new IllegalStateException("No property availability found for the test property.");
    }

    logger.info("Property Availability: {}", propertyAvailabilityList.get(0).getStatus());

    // Checking room prices for the property
    logger.info("Checking room prices for the property: {}...", Constants.TEST_PROPERTY_ID);
    Property property = propertyAvailabilityList.get(0);

    if (!(property instanceof PropertyAvailability)) {
      throw new IllegalStateException("Property is not an instance of PropertyAvailability");
    }

    PropertyAvailability propertyAvailability = (PropertyAvailability) property;
    return shopService.asyncCheckRoomPrices(propertyAvailability, 0, 0)
        .thenApply(response -> response.getData());

  }
}
