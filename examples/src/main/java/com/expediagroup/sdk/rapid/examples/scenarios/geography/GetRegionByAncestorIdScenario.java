package com.expediagroup.sdk.rapid.examples.scenarios.geography;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.GeographyService;
import com.expediagroup.sdk.rapid.models.Region;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Scenario for retrieving and logging the details of regions by ancestor ID.
 */
public class GetRegionByAncestorIdScenario implements RapidScenario {

  private static final Logger logger = LoggerFactory.getLogger(GetRegionByAncestorIdScenario.class);
  private GeographyService geographyService = new GeographyService();
  private RapidPartnerSalesProfile rapidPartnerSalesProfile;

  /**
   * Sets the sales profile to be used for the scenario.
   *
   * @param rapidPartnerSalesProfile the sales profile to use
   */
  @Override
  public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
    this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
  }

  /**
   * Runs the scenario to retrieve and log the details of regions by ancestor ID.
   */
  @Override
  public void run() {

    logger.info("Running Get Region By Ancestor Id Scenario...");

    // Calling region API with details and property ids by Ancestor ID
    logger.info(
        "Calling GET /region API to get region details and property ids by ancestor id: [{}]...",
        Constants.TEST_ANCESTOR_ID);
    List<List<Region>> regionsPages =
        geographyService.getRegionsByAncestor(Constants.TEST_ANCESTOR_ID,
            this.rapidPartnerSalesProfile);

    if (regionsPages == null || regionsPages.isEmpty()) {
      throw new IllegalStateException(
          String.format("No regions found for the ancestor id: [%s].", Constants.TEST_ANCESTOR_ID));
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
