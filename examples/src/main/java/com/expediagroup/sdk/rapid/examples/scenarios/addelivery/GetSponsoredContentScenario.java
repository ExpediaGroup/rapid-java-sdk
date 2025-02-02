package com.expediagroup.sdk.rapid.examples.scenarios.addelivery;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.ShopService;
import com.expediagroup.sdk.rapid.examples.services.SponsoredContentService;
import com.expediagroup.sdk.rapid.models.Property;
import com.expediagroup.sdk.rapid.models.SponsoredContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetSponsoredContentScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(GetSponsoredContentScenario.class);
    private SponsoredContentService sponsoredContentService = new SponsoredContentService();
    private ShopService shopService = new ShopService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        logger.info("Running Get Lodging Sponsored Content...");

        // Shopping for properties
        logger.info("Getting property availability for test property: {}", Constants.TEST_PROPERTY_ID);

        List<Property> propertyAvailabilityList = shopService.getPropertiesAvailability(Arrays.asList("2"), this.rapidPartnerSalesProfile).getData();

        if (propertyAvailabilityList == null || propertyAvailabilityList.isEmpty()) {
            throw new IllegalStateException("No property availability found for the test property.");
        }

        // Get the property ids from response
        ArrayList<String> propertyIds = new ArrayList<>();
        propertyAvailabilityList.forEach(property -> propertyIds.add(property.getPropertyId()));

        // call Ad Delivery API
        logger.info("Calling PostAdDeliveryOperation for property ids:");
        propertyIds.forEach(propertyId -> logger.info("Property Id: [{}]", propertyId));

        SponsoredContentResponse sponsoredContentResponse = sponsoredContentService.getSponsoredContent(propertyIds).getData();
        logger.info("Sponsored content response, number of listings: {}", sponsoredContentResponse.getSponsoredListings().size());
    }
}
