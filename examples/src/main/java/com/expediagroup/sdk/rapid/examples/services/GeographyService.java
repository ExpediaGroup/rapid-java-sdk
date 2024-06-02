package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.core.model.paging.Paginator;
import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.models.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This shows how to consume the rapid geography apis using the rapid java sdk to:
 * 1. get all regions
 * 2. get all regions within a specific larger region
 * 3. get one region definition and properties mappings within this region
 */
public class GeographyService extends RapidService {

    private static final Logger logger = LoggerFactory.getLogger(GeographyService.class);

    public List<List<Region>> getAllRegionsWithDetails(RapidPartnerSalesProfile rapidPartnerSalesProfile) {

        logger.info("------------- Calling getRegionsPaginator with no ancestor ID and include=details to get all regions:");
        Paginator<List<Region>> paginator = rapidClient.getRegionsPaginator(
                Arrays.asList("details"),
                "en-US",
                Constants.CUSTOMER_SESSION_ID,
                null,
                null ,
                null,
                null,
                null,
                BigDecimal.valueOf(10),
                "expedia",
                Arrays.asList("country"),
                rapidPartnerSalesProfile.billingTerms,
                rapidPartnerSalesProfile.paymentTerms,
                rapidPartnerSalesProfile.partnerPointOfSale,
                rapidPartnerSalesProfile.platformName
        );

        List<List<Region>> pages = new ArrayList<>();

        logger.info("Paginator total results count: {}", paginator.getPaginationTotalResults());

        while (paginator.hasNext()) {
            List<Region> page = paginator.next();
            pages.add(page);

            break; // remove to iterate over all pages.
        }

        return pages;
    }

    public List<List<Region>> getRegionsByAncestor(String ancestorId, RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        logger.info("------------- Calling getRegionsPaginator by ancestor ID: [{}]", ancestorId);
        Paginator<List<Region>> paginator = rapidClient.getRegionsPaginator(
                Arrays.asList("details"),
                "en-US",
                Constants.CUSTOMER_SESSION_ID,
                ancestorId,
                null,
                null,
                null,
                null,
                BigDecimal.valueOf(10),
                "expedia",
                null,
                rapidPartnerSalesProfile.billingTerms,
                rapidPartnerSalesProfile.paymentTerms,
                rapidPartnerSalesProfile.partnerPointOfSale,
                rapidPartnerSalesProfile.platformName
        );

        List<List<Region>> pages = new ArrayList<>();

        while (paginator.hasNext()) {
            List<Region> page = paginator.next();
            pages.add(page);

            break; // remove to iterate over all pages.
        }

        return pages;
    }

    public Region getRegionDetailsAndPropertyIds(String regionId, String language, RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        logger.info("------------- Calling GetRegion:");
        Region region = rapidClient.getRegion(
                regionId,
                language,
                Arrays.asList("details","property_ids"),
                Constants.CUSTOMER_SESSION_ID,
                rapidPartnerSalesProfile.billingTerms,
                rapidPartnerSalesProfile.paymentTerms,
                rapidPartnerSalesProfile.partnerPointOfSale,
                rapidPartnerSalesProfile.platformName,
                "expedia"
        );

        return region;
    }

}
