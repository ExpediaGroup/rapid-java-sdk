package com.expediagroup.sdk.rapid.examples.scenarios.addelivery;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.AdDeliveryService;
import com.expediagroup.sdk.rapid.examples.services.ShopService;
import com.expediagroup.sdk.rapid.models.AdsResponse;
import com.expediagroup.sdk.rapid.models.Property;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This scenario demonstrates the GetAds operation.
 */
public class GetAdsScenario implements RapidScenario {

  private static final Logger logger = LoggerFactory.getLogger(GetAdsScenario.class);
  private AdDeliveryService adDeliveryService = new AdDeliveryService();
  private ShopService shopService = new ShopService();
  private RapidPartnerSalesProfile rapidPartnerSalesProfile;

  /**
   * Sets the profile.
   *
   * @param rapidPartnerSalesProfile the profile
   */
  @Override
  public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
    this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
  }

  @Override
  public void run() {

    logger.info("Running Get Lodging Ads...");

    // Shopping for properties
    logger.info("Getting property availability for test property: {}", Constants.TEST_PROPERTY_ID);

    List<Property> propertyAvailabilityList = shopService.getPropertiesAvailability(
        Arrays.asList("2"), this.rapidPartnerSalesProfile).getData();

    if (propertyAvailabilityList == null || propertyAvailabilityList.isEmpty()) {
      throw new IllegalStateException("No property availability found for the test property.");
    }

    // Get the property ids from response
    ArrayList<String> propertyIds = new ArrayList<>();
    propertyAvailabilityList.forEach(property -> propertyIds.add(property.getPropertyId()));

    // call Ad Delivery API
    logger.info("Calling GetAdsOperation for property ids:");
    propertyIds.forEach(propertyId -> logger.info("Property Id: [{}]", propertyId));

    Response<AdsResponse> adsResponse = adDeliveryService.getAds(propertyIds);
    logger.info("Get ads response status: [{}]", adsResponse.getStatusCode());
    logger.info("Get ads response, number of sponsored listings: {}", adsResponse.getData()
        .getSponsoredListings().size());
  }
}
