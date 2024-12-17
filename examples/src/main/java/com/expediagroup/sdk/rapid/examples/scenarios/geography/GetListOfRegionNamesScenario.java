package com.expediagroup.sdk.rapid.examples.scenarios.geography;

import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.GeographyService;
import com.expediagroup.sdk.rapid.models.Region;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Scenario for retrieving and logging the names of all regions with details.
 */
public class GetListOfRegionNamesScenario implements RapidScenario {
  private static final Logger logger = LoggerFactory.getLogger(GetListOfRegionNamesScenario.class);
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
   * Runs the scenario to retrieve and log the names of all regions with details.
   */
  @Override
  public void run() {

    logger.info("Running Get List of Region Names Scenario...");

    // Calling regions API with details
    logger.info("Calling GET /regions API to get list of regions...");
    List<List<Region>> regions =
        geographyService.getAllRegionsWithDetails(this.rapidPartnerSalesProfile);

    // Filter regions names
    logger.info("Regions names:");
    regions.forEach(regionList -> {
      regionList.forEach(region -> {
        logger.info(region.getNameFull());
      });
    });
  }
}
