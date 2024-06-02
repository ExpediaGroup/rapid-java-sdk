package com.expediagroup.sdk.rapid.examples.scenarios.geography;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.GeographyService;
import com.expediagroup.sdk.rapid.models.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseRegionAncestorsScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(ParseRegionAncestorsScenario.class);
    private GeographyService geographyService = new GeographyService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
        public void run() {
            // Calling region API with details and property ids
            logger.info("Calling GET /region API to get region details and property ids by region id: [{}]...", Constants.TEST_REGION_ID);
            Region region = geographyService.getRegionDetailsAndPropertyIds(Constants.TEST_REGION_ID, "en-US", this.rapidPartnerSalesProfile);

            logger.info("Region Full Name: {}", region.getNameFull());
            logger.info("Region Type: {}", region.getType());
            logger.info("Region Country Code: {}", region.getCountryCode());

            logger.info("Region Ancestors:");
            region.getAncestors().stream().forEach(ancestor -> {
                logger.info("Ancestor id: [{}], type: [{}]", ancestor.getId(), ancestor.getType());
            });

        }
}
