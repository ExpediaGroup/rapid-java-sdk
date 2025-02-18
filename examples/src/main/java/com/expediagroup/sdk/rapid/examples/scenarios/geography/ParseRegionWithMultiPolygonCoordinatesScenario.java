package com.expediagroup.sdk.rapid.examples.scenarios.geography;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.GeographyService;
import com.expediagroup.sdk.rapid.models.MultiPolygon;
import com.expediagroup.sdk.rapid.models.Region;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Scenario for retrieving and logging the details of a region by region ID, including its
 * multipolygon coordinates.
 */
public class ParseRegionWithMultiPolygonCoordinatesScenario implements RapidScenario {
  private static final Logger logger =
      LoggerFactory.getLogger(ParseRegionWithMultiPolygonCoordinatesScenario.class);
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
   * Runs the scenario to retrieve and log the details of a region by region ID, including its
   * multipolygon coordinates.
   */
  @Override
  public void run() {
    logger.info("Running Get Region With MultiPolygon Coordinates Scenario...");

    // Calling region API with details and property ids
    logger.info(
        "Calling GET /region API to get region details of a region with multipolygon coordinates "
            + "by region id: [{}]...",
        Constants.TEST_REGION_ID_WITH_MULTIPOLYGON);
    Region region =
        geographyService.getRegionDetailsAndPropertyIds(Constants.TEST_REGION_ID_WITH_MULTIPOLYGON,
            "en-US", this.rapidPartnerSalesProfile);

    logger.info("Region Full Name: {}", region.getNameFull());
    logger.info("Region Type: {}", region.getType());
    logger.info("Region Country Code: {}", region.getCountryCode());

    logger.info(
        "Verify the returned region has multipolygon coordinates by checking coordinates type...");
    logger.info("Region coordinates type: [{}]",
        region.getCoordinates().getBoundingPolygon().getType());

    MultiPolygon multiPolygonRegionCoordinates =
        (MultiPolygon) region.getCoordinates().getBoundingPolygon();
    logger.info("Number of bounding polygons in multipolygon region: [{}]",
        multiPolygonRegionCoordinates.getCoordinates().size());

    AtomicInteger index = new AtomicInteger();
    multiPolygonRegionCoordinates.getCoordinates().forEach(polygon -> {
      String coordinates = polygon.get(0).stream()
          .map(coordinate -> "[" + coordinate.get(0) + "," + coordinate.get(1) + "]")
          .collect(Collectors.joining(","));
      logger.info("Polygon index: [{}] coordinates: [{}]", index.getAndIncrement(), coordinates);
    });
  }
}
