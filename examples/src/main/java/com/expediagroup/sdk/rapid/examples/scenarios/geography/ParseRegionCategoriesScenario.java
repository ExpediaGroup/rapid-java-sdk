package com.expediagroup.sdk.rapid.examples.scenarios.geography;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.GeographyService;
import com.expediagroup.sdk.rapid.models.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseRegionCategoriesScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(ParseRegionCategoriesScenario.class);
    private GeographyService geographyService = new GeographyService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        logger.info("Running Parse Region Categories Scenario...");

        // Calling region API with details and property ids
        logger.info("Calling GET /region API to get region details and property ids by region id: [{}]...", Constants.TEST_REGION_ID);
        Region region = geographyService.getRegionDetailsAndPropertyIds(Constants.TEST_REGION_ID, "en-US", this.rapidPartnerSalesProfile);

        logger.info("Region full name: {}", region.getNameFull());
        logger.info("Region type: {}", region.getType());
        logger.info("Region country code: {}", region.getCountryCode());

        logger.info("Region categories:");
        region.getCategories().forEach(category -> {
            logger.info("Category key: [{}] value: [{}]", category.split(":")[0], category.split(":")[1]);
        });
    }
}
