package com.expediagroup.sdk.rapid.examples.scenarios.geography;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.GeographyService;
import com.expediagroup.sdk.rapid.models.MultiPolygon;
import com.expediagroup.sdk.rapid.models.Polygon;
import com.expediagroup.sdk.rapid.models.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ParseRegionCoordinatesScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(ParseRegionCoordinatesScenario.class);
    private GeographyService geographyService = new GeographyService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        logger.info("Running Parse Region Coordinates Scenario...");

        // Calling region API with details and property ids
        logger.info("Calling GET /region API to get region details and property ids by region id: [{}]...", Constants.TEST_REGION_ID);
        Region region = geographyService.getRegionDetailsAndPropertyIds(Constants.TEST_REGION_ID, "en-US", this.rapidPartnerSalesProfile);

        logger.info("Region full name: {}", region.getNameFull());
        logger.info("Region type: {}", region.getType());
        logger.info("Region country code: {}", region.getCountryCode());
        logger.info("Region center coordinates: lat:[{}], long:[{}]",
                region.getCoordinates().getCenterLatitude(), region.getCoordinates().getCenterLongitude());

        String regionCoordinatesType = region.getCoordinates().getBoundingPolygon().getType();
        logger.info("Region coordinates type: [{}]", regionCoordinatesType);

        if (regionCoordinatesType.equals("POLYGON")) {
            Polygon regionBoundingPolygon = (Polygon) region.getCoordinates().getBoundingPolygon();
            logger.info("Parse region coordinates: [{}]", regionBoundingPolygon.getCoordinates().get(0).stream()
                    .map(coordinate -> "[" + coordinate.get(0) + "," + coordinate.get(1) + "]")
                    .collect(Collectors.joining(",")));

        } else if (regionCoordinatesType.equals("MultiPolygon")) {
            MultiPolygon regionBoundingMultipolygons = (MultiPolygon) region.getCoordinates().getBoundingPolygon();
            logger.info("Number of bounding polygons in multipolygon region: [{}]", regionBoundingMultipolygons.getCoordinates().size());

            AtomicInteger index = new AtomicInteger();

            regionBoundingMultipolygons.getCoordinates().forEach(polygon -> {
                String coordinates = polygon.get(0).stream()
                        .map(coordinate -> "[" + coordinate.get(0) + "," + coordinate.get(1) + "]")
                        .collect(Collectors.joining(","));
                logger.info("Parse region polygon index: [{}] coordinates: [{}]", index.getAndIncrement(), coordinates);
            });
        }
    }
}
