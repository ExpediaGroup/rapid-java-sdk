package com.expediagroup.sdk.rapid.examples.scenarios.shopping;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.ShopService;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This scenario demonstrates how to get payment options of a room.
 * The scenario performs the following steps:
 * 1. Shopping for properties
 * 2. Get payment options of a room
 */
public class GetPaymentOptionsOfRoomScenario implements RapidScenario {
  private static final Logger logger =
      LoggerFactory.getLogger(GetPaymentOptionsOfRoomScenario.class);
  private final ShopService shopService = new ShopService();
  private RapidPartnerSalesProfile rapidPartnerSalesProfile;

  /**
   * Set the profile for the scenario.
   *
   * @param rapidPartnerSalesProfile The profile to be used for the scenario.
   */
  @Override
  public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
    this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
  }

  /**
   * Run the scenario.
   */
  @Override
  public void run() {

    logger.info("Running Get Payment Options Of Room Scenario using the default profile...");

    // Shopping for properties
    logger.info("Getting property availability for test property: {}", Constants.TEST_PROPERTY_ID);

    List<com.expediagroup.sdk.rapid.models.Property> propertyAvailabilityList =
        shopService.getPropertiesAvailability(Arrays.asList("2"), this.rapidPartnerSalesProfile)
            .getData();

    if (propertyAvailabilityList == null || propertyAvailabilityList.isEmpty()) {
      throw new IllegalStateException("No property availability found for the test property.");
    }

    logger.info("Property Availability: {}", propertyAvailabilityList.get(0).getStatus());

    if (!(propertyAvailabilityList.get(
        0) instanceof com.expediagroup.sdk.rapid.models.PropertyAvailability)) {
      throw new IllegalStateException("Property is not of type PropertyAvailability");
    }

    com.expediagroup.sdk.rapid.models.PropertyAvailability propertyAvailability =
        (com.expediagroup.sdk.rapid.models.PropertyAvailability) propertyAvailabilityList.get(0);

    com.expediagroup.sdk.rapid.models.RoomAvailability selectedRoomAvailability =
        propertyAvailability.getRooms().get(0);
    logger.info("Asynchronously get payment options of room: [{}]...",
        selectedRoomAvailability.getId());

    shopService.getPaymentOptions(selectedRoomAvailability, this.rapidPartnerSalesProfile)
        .thenAccept(paymentOptionResponse -> {
          com.expediagroup.sdk.rapid.models.PaymentOption paymentOption =
              paymentOptionResponse.getData();
          if (paymentOption.getAffiliateCollect() != null) {
            logger.info("Payment option affiliate collect name: [{}]",
                paymentOption.getAffiliateCollect().getName());
          }

          if (paymentOption.getCreditCard() != null) {
            logger.info("Payment option credit card options:");
            paymentOption.getCreditCard().getCardOptions().forEach(creditCardOption -> {
              logger.info("Credit card option name: [{}]", creditCardOption.getName());
            });
          }
        });
  }
}
