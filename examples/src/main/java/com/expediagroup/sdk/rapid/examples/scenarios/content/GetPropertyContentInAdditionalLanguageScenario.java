package com.expediagroup.sdk.rapid.examples.scenarios.content;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.ContentService;
import com.expediagroup.sdk.rapid.models.PropertyContent;
import com.expediagroup.sdk.rapid.operations.GetPropertyContentOperationLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

public class GetPropertyContentInAdditionalLanguageScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(GetPropertyContentInAdditionalLanguageScenario.class);
    private ContentService contentService = new ContentService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        logger.info("Running Get Property Content In Additional Language Scenario...");

        // Calling property content API
        logger.info("Get property content by property id: [{}]...", Constants.TEST_PROPERTY_ID);
        contentService.asyncGetPropertyContent(Arrays.asList(Constants.TEST_PROPERTY_ID),
                "en-US", this.rapidPartnerSalesProfile).thenApply(propertyContentMap -> {
            if (propertyContentMap.getData().isEmpty()) {
                throw new IllegalStateException("No property content found for property id:" + Constants.TEST_PROPERTY_ID);
            }

            logger.info("Property content in en-US language:");
            // Print property content
            propertyContentMap.getData().forEach((key, value) -> {
                logger.info("----Property id: [{}]----", key);
                logger.info("Property name: [{}]", value.getName());
                logger.info("Property address: [{}]", value.getAddress());
                logger.info("Property category: [{}]", value.getCategory());

            });

            // Print available links to other languages
            logger.info("Available links to property content in other languages:");
            Map<String, GetPropertyContentOperationLink> selectedPropertyLinks = propertyContentMap.getData().entrySet().stream().findFirst().get()
                    .getValue().getAddress().getLocalized().getLinks();

            if (selectedPropertyLinks.isEmpty()) {
                throw new IllegalStateException("No links found to property content in other languages.");
            }

            selectedPropertyLinks.forEach((language, link) -> logger.info("Language: [{}], Link: [{}]", language, link.getHref()));
            return propertyContentMap.getData();

        }).thenCompose(propertyContentMap -> {
            // Get property content in additional language
            PropertyContent propertyContent = propertyContentMap.entrySet().iterator().next().getValue();
            return contentService.asyncGetPropertyContentInAdditionalLanguage(propertyContent, this.rapidPartnerSalesProfile);
        }).thenApply(propertyContentMap -> {
            if (propertyContentMap.getData().isEmpty()) {
                throw new IllegalStateException("No property content found for property id:" + Constants.TEST_PROPERTY_ID);
            }

            logger.info("Property content in additional language:");
            // Print property content
            propertyContentMap.getData().forEach((key, value) -> {
                logger.info("----Property id: [{}]----", key);
                logger.info("Property name: [{}]", value.getName());
                logger.info("Property address: [{}]", value.getAddress());
                logger.info("Property category: [{}]", value.getCategory());

            });

            return propertyContentMap;
        }).join();
    }
}

