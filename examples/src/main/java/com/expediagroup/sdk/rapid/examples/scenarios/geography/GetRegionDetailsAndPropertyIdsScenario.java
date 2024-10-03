package com.expediagroup.sdk.rapid.examples.scenarios.geography;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.GeographyService;
import com.expediagroup.sdk.rapid.models.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

public class GetRegionDetailsAndPropertyIdsScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(GetRegionDetailsAndPropertyIdsScenario.class);
    private GeographyService geographyService = new GeographyService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        logger.info("Running Get Region Name of Region Scenario...");

        // Calling region API with details and property ids
        logger.info("Calling GET /region API to get region details and property ids by region id: [{}]...", Constants.TEST_REGION_ID);
        Region region = geographyService.getRegionDetailsAndPropertyIds(Constants.TEST_REGION_ID, "en-US", this.rapidPartnerSalesProfile);

        logger.info("Region Full Name: {}", region.getNameFull());
        logger.info("Region Type: {}", region.getType());
        logger.info("Region Country Code: {}", region.getCountryCode());

        String result = region.getPropertyIds().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        logger.info("Region Property IDs: [{}]", result);

    }
}
