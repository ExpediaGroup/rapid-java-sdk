package com.expediagroup.sdk.rapid.examples.scenarios.content;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.ContentService;
import com.expediagroup.sdk.rapid.models.PropertyContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

public class GetPropertyContentScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(GetPropertyContentScenario.class);
    private ContentService contentService = new ContentService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        logger.info("Running Get Property Content Scenario...");

        // Calling property content API
        logger.info("Get property content by property id: [{}]...", Constants.TEST_PROPERTY_ID);
        Map<String, PropertyContent> propertyContentMap = contentService.getPropertyContent(Arrays.asList(Constants.TEST_PROPERTY_ID),
                "en-US", this.rapidPartnerSalesProfile).getData();

        if (propertyContentMap.isEmpty()) {
            throw new IllegalStateException("No property content found for property id:" + Constants.TEST_PROPERTY_ID);
        }

        // Print property content
        propertyContentMap.forEach((key, value) -> {
            logger.info("----Property id: [{}]----", key);
            logger.info("Property name: [{}]", value.getName());
            logger.info("Property address: [{}]", value.getAddress());
            logger.info("Property category: [{}]", value.getCategory());

        });
    }
}
