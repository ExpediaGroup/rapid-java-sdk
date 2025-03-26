package com.expediagroup.sdk.rapid.examples.scenarios.shopping;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.ShopService;
import com.expediagroup.sdk.rapid.models.Property;
import com.expediagroup.sdk.rapid.models.PropertyAvailability;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This scenario demonstrates how to get additional availability of a property.
 * The scenario performs the following steps:
 * 1. Shopping for properties
 * 2. Get additional availability of the first property returned
 */
public class GetAdditionalAvailabilityOfPropertyScenario implements RapidScenario {
  private static final Logger logger =
      LoggerFactory.getLogger(GetAdditionalAvailabilityOfPropertyScenario.class);
  private final ShopService shopService = new ShopService();
  private RapidPartnerSalesProfile rapidPartnerSalesProfile;

  /**
   * Set the RapidPartnerSalesProfile for the scenario.
   *
   * @param rapidPartnerSalesProfile the RapidPartnerSalesProfile to set
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
    logger.info("Running Get Additional Availability Of Property Scenario...");

    // Shopping for properties
    logger.info("Getting property availability for test property: {}", Constants.TEST_PROPERTY_ID);

    List<Property> propertyAvailabilityList =
        shopService.getPropertiesAvailability(Arrays.asList("2"), this.rapidPartnerSalesProfile)
            .getData();

    if (propertyAvailabilityList == null || propertyAvailabilityList.isEmpty()) {
      throw new IllegalStateException("No property availability found for the test property.");
    }

    logger.info("Property Availability: {}", propertyAvailabilityList.get(0).getStatus());

    // Get additional availability of the first property returned
    Property property = propertyAvailabilityList.get(0);
    logger.info("Get additional availability of property id: {}...", property.getPropertyId());

    PropertyAvailability propertyAvailability = (PropertyAvailability) property;
    Response<List<PropertyAvailability>> additionalPropertyAvailability =
        shopService.getAdditionalAvailability(propertyAvailability);

    // Log the additional availability
    logger.info("Additional availability returned:");
    additionalPropertyAvailability.getData().forEach(additionalProperty -> {
      logger.info("---------------------------------");
      logger.info("Property Id: {}", additionalProperty.getPropertyId());
      logger.info("Property Status: {}", additionalProperty.getStatus());
      logger.info("Property Rooms Count: {}",
          additionalProperty.getRooms() == null ? 0 : additionalProperty.getRooms().size());
    });
  }
}
