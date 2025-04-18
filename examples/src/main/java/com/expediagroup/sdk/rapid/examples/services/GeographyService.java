package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.core.model.paging.ResponsePaginator;
import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.models.Region;
import com.expediagroup.sdk.rapid.operations.GetRegionOperation;
import com.expediagroup.sdk.rapid.operations.GetRegionOperationParams;
import com.expediagroup.sdk.rapid.operations.GetRegionsOperation;
import com.expediagroup.sdk.rapid.operations.GetRegionsOperationParams;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This shows how to consume the rapid geography apis using the rapid java sdk to:
 * 1. get all regions
 * 2. get all regions within a specific larger region
 * 3. get one region definition and properties mappings within this region
 */
public class GeographyService extends RapidService {

  private static final Logger logger = LoggerFactory.getLogger(GeographyService.class);

  /**
   * Retrieves all regions with detailed information.
   *
   * @param rapidPartnerSalesProfile the sales profile to use for the operation
   * @return a list of lists containing region details
   */
  public List<List<Region>> getAllRegionsWithDetails(
      RapidPartnerSalesProfile rapidPartnerSalesProfile) {

    logger.info(
        "------------- Calling getRegions paginated results with no ancestor ID and "
            + "include=details to get all regions:");

    GetRegionsOperationParams params = GetRegionsOperationParams.builder()
        .include(Collections.singletonList(GetRegionsOperationParams.Include.DETAILS))
        .language("en-US")
        .customerSessionId(Constants.CUSTOMER_SESSION_ID)
        .limit(BigDecimal.valueOf(10))
        .area("50,37.227924,-93.310036")
        .supplySource("expedia")
        .countryCode(Collections.singletonList("US"))
        .build();

    ResponsePaginator<List<Region>> responsePaginator =
        rapidClient.getPaginator(new GetRegionsOperation(params));

    List<List<Region>> pages = new ArrayList<>();

    logger.info("Paginator total results count: {}", responsePaginator.getPaginationTotalResults());

    responsePaginator.forEachRemaining(page -> pages.add(page.getData()));

    return pages;
  }

  /**
   * Retrieves all regions within a specific larger region.
   *
   * @param ancestorId the ancestor region id
   * @param rapidPartnerSalesProfile the sales profile to use for the operation
   * @return a list of lists containing region details
   */
  public List<List<Region>> getRegionsByAncestor(
      String ancestorId,
      RapidPartnerSalesProfile rapidPartnerSalesProfile
  ) {
    logger.info("------------- Calling getRegionsPaginator by ancestor ID: [{}]", ancestorId);

    GetRegionsOperationParams params = GetRegionsOperationParams.builder()
        .include(Collections.singletonList(GetRegionsOperationParams.Include.DETAILS))
        .language("en-US")
        .customerSessionId(Constants.CUSTOMER_SESSION_ID)
        .limit(BigDecimal.valueOf(10))
        .supplySource("expedia")
        .countryCode(Collections.singletonList("US"))
        .ancestorId(ancestorId)
        .build();

    ResponsePaginator<List<Region>> responsePaginator =
        rapidClient.getPaginator(new GetRegionsOperation(params));

    List<List<Region>> pages = new ArrayList<>();

    logger.info("Paginator total results count: {}", responsePaginator.getPaginationTotalResults());

    responsePaginator.forEachRemaining(page -> pages.add(page.getData()));

    return pages;
  }

  /**
   * Retrieves one region definition and properties mappings within this region.
   *
   * @param regionId the region id
   * @param language the language
   * @param rapidPartnerSalesProfile the sales profile to use for the operation
   * @return the region details
   */
  public Region getRegionDetailsAndPropertyIds(
      String regionId,
      String language,
      RapidPartnerSalesProfile rapidPartnerSalesProfile
  ) {
    logger.info("------------- Calling GetRegion:");

    GetRegionOperationParams params = GetRegionOperationParams.builder()
        .include(Arrays.asList(GetRegionOperationParams.Include.DETAILS,
            GetRegionOperationParams.Include.PROPERTY_IDS))
        .language(language)
        .regionId(regionId)
        .customerSessionId(Constants.CUSTOMER_SESSION_ID)
        .supplySource("expedia")
        .build();

    return rapidClient.execute(new GetRegionOperation(params)).getData();
  }
}
