package com.expediagroup.sdk.rapid.examples.scenarios.geography;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.GeographyService;
import com.expediagroup.sdk.rapid.models.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetRegionByAncestorIdScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(GetRegionByAncestorIdScenario.class);
    private GeographyService geographyService = new GeographyService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
        public void run() {
            // Calling region API with details and property ids by Ancestor ID
            logger.info("Calling GET /region API to get region details and property ids by ancestor id: [{}]...", Constants.TEST_ANCESTOR_ID);
            List<List<Region>> regionsPages = geographyService.getRegionsByAncestor(Constants.TEST_ANCESTOR_ID, this.rapidPartnerSalesProfile);

            if (regionsPages == null || regionsPages.isEmpty()) {
                logger.error("No regions found for the ancestor id: [{}].", Constants.TEST_ANCESTOR_ID);
                return;
            }

            logger.info("Regions found for the ancestor id: [{}]:", Constants.TEST_ANCESTOR_ID);
            regionsPages.forEach(page -> {
                page.forEach(region -> {
                    logger.info("---------------------------------");
                    logger.info("Region Full Name: {}", region.getNameFull());
                    logger.info("Region Type: {}", region.getType());
                    logger.info("Region Country Code: {}", region.getCountryCode());
                    logger.info("---------------------------------");
                });
            });
        }
}
