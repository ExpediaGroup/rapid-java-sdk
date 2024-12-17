package com.expediagroup.sdk.rapid.examples.scenarios.managebooking;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.BookService;
import com.expediagroup.sdk.rapid.examples.services.ShopService;
import com.expediagroup.sdk.rapid.models.ItineraryCreation;
import com.expediagroup.sdk.rapid.models.Property;
import com.expediagroup.sdk.rapid.models.PropertyAvailability;
import com.expediagroup.sdk.rapid.models.RoomPriceCheck;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Scenario to cancel a held booking.
 */
public class CancelHeldBookingScenario implements RapidScenario {

  private static final Logger logger = LoggerFactory.getLogger(CancelHeldBookingScenario.class);
  private final ShopService shopService = new ShopService();
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
   * Runs the scenario to cancel a held booking.
   */
  @Override
  public void run() {

    logger.info("Running Cancel Held Booking Scenario using the default profile...");

    // Shopping for properties
    logger.info("Getting property availability for test property: [{}]",
        Constants.TEST_PROPERTY_ID);

    List<Property> propertyAvailabilityList =
        shopService.getPropertiesAvailability(Arrays.asList("2"), this.rapidPartnerSalesProfile)
            .getData();

    if (propertyAvailabilityList == null || propertyAvailabilityList.isEmpty()) {
      throw new IllegalStateException("No property availability found for the test property.");
    }

    logger.info("Property Availability: {}", propertyAvailabilityList.get(0).getStatus());

    // Checking room prices for the property
    logger.info("Checking room prices for the property: [{}]...", Constants.TEST_PROPERTY_ID);
    Property property = propertyAvailabilityList.get(0);

    if (!(property instanceof PropertyAvailability)) {
      throw new IllegalStateException("Property is not of type PropertyAvailability");
    }

    PropertyAvailability propertyAvailability = (PropertyAvailability) property;
    RoomPriceCheck roomPriceCheck =
        shopService.checkRoomPrices(propertyAvailability, 0, 0).getData();
    logger.info("Room Price Check: [{}]", roomPriceCheck.getStatus());

    // Booking a room with hold in the property
    logger.info("Booking a room with hold=true in test property: [{}]...",
        Constants.TEST_PROPERTY_ID);

    BookService bookService = new BookService();
    ItineraryCreation itineraryCreation =
        bookService.createBookingWithHold(roomPriceCheck, Arrays.asList("2")).getData();

    logger.info("Booking with hold success. Itinerary id: [{}]",
        itineraryCreation.getItineraryId());

    // Cancel the held booking
    logger.info("Cancelling the held booking with itinerary id: [{}]...",
        itineraryCreation.getItineraryId());
    bookService.cancelHeldReservation(itineraryCreation);
  }
}
