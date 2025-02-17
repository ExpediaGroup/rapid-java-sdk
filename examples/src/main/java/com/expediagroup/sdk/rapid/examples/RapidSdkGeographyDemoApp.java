package com.expediagroup.sdk.rapid.examples;

import com.expediagroup.sdk.rapid.examples.salesprofiles.DefaultRapidPartnerProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.GetListOfRegionNamesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.GetRegionByAncestorIdScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.GetRegionDetailsAndPropertyIdsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionAncestorsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionCategoriesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionCoordinatesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionDescendantsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionWithMultiPolygonCoordinatesScenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;


public class RapidSdkGeographyDemoApp {

    private static final Logger logger = LoggerFactory.getLogger(RapidSdkGeographyDemoApp.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        logger.info("=======================================================================================");
        logger.info("=======================================================================================");
        logger.info("==                                                                                   ==");
        logger.info("==    Howdy! This is a demonstration of Expedia Group RAPID SDK Geography, Enjoy!    ==");
        logger.info("==                                                                                   ==");
        logger.info("=======================================================================================");
        logger.info("=======================================================================================");

        logger.info("=============================== Running Geography Scenarios ===========================");

        /*  Run Get List of Region Names Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting all regions with details in a paginated manner
            2. Filtering region names
        */
        GetListOfRegionNamesScenario getListOfRegionNamesScenario = new GetListOfRegionNamesScenario();
        getListOfRegionNamesScenario.setProfile(new DefaultRapidPartnerProfile());
        getListOfRegionNamesScenario.run();

        /*  Run Get Region Name of Region Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details by region id
            2. Accessing region details
        */
        GetRegionDetailsAndPropertyIdsScenario getRegionNameOfRegionScenario = new GetRegionDetailsAndPropertyIdsScenario();
        getRegionNameOfRegionScenario.setProfile(new DefaultRapidPartnerProfile());
        getRegionNameOfRegionScenario.run();

        /*  Run Get Region By Ancestor Id Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting paginated regions details by ancestor id
            2. Accessing region details
        */
        GetRegionByAncestorIdScenario getRegionByAncestorIdScenario = new GetRegionByAncestorIdScenario();
        getRegionByAncestorIdScenario.setProfile(new DefaultRapidPartnerProfile());
        getRegionByAncestorIdScenario.run();

        /*  Run Get Region With MultiPolygon Coordinates Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details with multipolygon coordinates by region id
            2. Accessing region coordinates of type multipolygon
            3. Accessing every polygon list of coordinates.
        */
        ParseRegionWithMultiPolygonCoordinatesScenario parseRegionWithMultiPolygonCoordinatesScenario = new ParseRegionWithMultiPolygonCoordinatesScenario();
        parseRegionWithMultiPolygonCoordinatesScenario.setProfile(new DefaultRapidPartnerProfile());
        parseRegionWithMultiPolygonCoordinatesScenario.run();

        /*  Run Parse Region Ancestors Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details with ancestors by region id
            2. Parsing region ancestors
        */
        ParseRegionAncestorsScenario parseRegionAncestorsScenario = new ParseRegionAncestorsScenario();
        parseRegionAncestorsScenario.setProfile(new DefaultRapidPartnerProfile());
        parseRegionAncestorsScenario.run();

        /*  Run Parse Region Descendants Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details with descendants by region id
            2. Parsing region descendants
        */
        ParseRegionDescendantsScenario parseRegionDescendantsScenario = new ParseRegionDescendantsScenario();
        parseRegionDescendantsScenario.setProfile(new DefaultRapidPartnerProfile());
        parseRegionDescendantsScenario.run();

        /*  Run Parse Region Coordinates Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details with coordinates by region id
            2. Parsing region coordinates
        */
        ParseRegionCoordinatesScenario parseRegionCoordinatesScenario = new ParseRegionCoordinatesScenario();
        parseRegionCoordinatesScenario.setProfile(new DefaultRapidPartnerProfile());
        parseRegionCoordinatesScenario.run();

        /*  Run Parse Region Categories Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details with property ids by region id
            2. Parsing region categories
        */
        ParseRegionCategoriesScenario parseRegionCategoriesScenario = new ParseRegionCategoriesScenario();
        parseRegionCategoriesScenario.setProfile(new DefaultRapidPartnerProfile());
        parseRegionCategoriesScenario.run();

        logger.info("=============================== End of Geography Scenarios ===========================");

        logger.info("=======================================================================================");
        logger.info("=======================================================================================");
        logger.info("==                                                                                   ==");
        logger.info("==        That's all folks! That was the demonstration of RAPID SDK Geography.       ==");
        logger.info("==                                                                                   ==");
        logger.info("=======================================================================================");
        logger.info("=======================================================================================");
        System.exit(0);
    }
}
