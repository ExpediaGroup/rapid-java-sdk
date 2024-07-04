package com.expediagroup.sdk.rapid.examples;

import com.expediagroup.sdk.rapid.examples.salesprofiles.DefaultRapidPartnerProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.CancelHeldBookingScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.MultiRoomHoldAndResumeBookScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.SingleRoomBookScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.GetListOfRegionNamesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.GetRegionByAncestorIdScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.GetRegionDetailsAndPropertyIdsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionAncestorsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionCategoriesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionCoordinatesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionDescendantsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionWithMultiPolygonCoordinatesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.shopping.GetAdditionalAvailabilityOfPropertyScenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RapidSdkDemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(RapidSdkDemoApplication.class);

    public static void main(String[] args) {

        logger.info("=======================================================================================");
        logger.info("=======================================================================================");
        logger.info("==                                                                                   ==");
        logger.info("==         Howdy! This is a demonstration of Expedia Group RAPID SDK, Enjoy!         ==");
        logger.info("==                                                                                   ==");
        logger.info("=======================================================================================");
        logger.info("=======================================================================================");

        logger.info("=============================== Running Shopping Scenarios ============================");

        logger.info("Running Get Additional Availability Of Property Scenario...");
        GetAdditionalAvailabilityOfPropertyScenario getAdditionalAvailabilityOfPropertyScenario = new GetAdditionalAvailabilityOfPropertyScenario();
        getAdditionalAvailabilityOfPropertyScenario.setProfile(new DefaultRapidPartnerProfile());
        getAdditionalAvailabilityOfPropertyScenario.run();

        logger.info("=============================== End of Shopping Scenarios =============================");

        logger.info("=============================== Running Booking Scenarios =============================");

        /*  Run Single Room Book Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties
            2. Checking room prices for the property
            3. Booking the property
        */

        logger.info("Running Book Single Room Scenario using the default profile...");
        SingleRoomBookScenario singleRoomBookScenario = new SingleRoomBookScenario();
        singleRoomBookScenario.setProfile(new DefaultRapidPartnerProfile());
        singleRoomBookScenario.run();

        /*  Run Multiple Room Hold and Resume Book Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties with specifying multiple occupancy instances for multiple rooms
            2. Checking room prices for the property rooms.
            3. Hold the property rooms.
            4. Resume the booking process.
        */
        logger.info("Running Book Multiple Rooms with Hold and Resume Scenario using the default profile...");
        MultiRoomHoldAndResumeBookScenario multiRoomHoldAndResumeBookScenario = new MultiRoomHoldAndResumeBookScenario();
        multiRoomHoldAndResumeBookScenario.setProfile(new DefaultRapidPartnerProfile());
        multiRoomHoldAndResumeBookScenario.run();

        /*  Run Cancel Held Booking Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties
            2. Checking room prices for the property
            3. Booking a room with hold in the property
            4. Cancelling the held booking
        */
        logger.info("Running Cancel Held Booking Scenario using the default profile...");
        CancelHeldBookingScenario cancelHeldBookingScenario = new CancelHeldBookingScenario();
        cancelHeldBookingScenario.setProfile(new DefaultRapidPartnerProfile());
        cancelHeldBookingScenario.run();

        logger.info("=============================== End of Booking Scenarios ==============================");

        logger.info("=============================== Running Geography Scenarios ===========================");

        /*  Run Get List of Region Names Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting all regions with details in a paginated manner
            2. Filtering region names
        */
        logger.info("Running Get List of Region Names Scenario...");
        GetListOfRegionNamesScenario getListOfRegionNamesScenario = new GetListOfRegionNamesScenario();
        getListOfRegionNamesScenario.setProfile(new DefaultRapidPartnerProfile());
        getListOfRegionNamesScenario.run();

        /*  Run Get Region Name of Region Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details by region id
            2. Accessing region details
        */
        logger.info("Running Get Region Name of Region Scenario...");
        GetRegionDetailsAndPropertyIdsScenario getRegionNameOfRegionScenario = new GetRegionDetailsAndPropertyIdsScenario();
        getRegionNameOfRegionScenario.setProfile(new DefaultRapidPartnerProfile());
        getRegionNameOfRegionScenario.run();

        /*  Run Get Region By Ancestor Id Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting paginated regions details by ancestor id
            2. Accessing region details
        */
        logger.info("Running Get Region By Ancestor Id Scenario...");
        GetRegionByAncestorIdScenario getRegionByAncestorIdScenario = new GetRegionByAncestorIdScenario();
        getRegionByAncestorIdScenario.setProfile(new DefaultRapidPartnerProfile());
        getRegionByAncestorIdScenario.run();

        /*  Run Get Region With MultiPolygon Coordinates Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details with multipolygon coordinates by region id
            2. Accessing region coordinates of type multipolygon
            3. Accessing every polygon list of coordinates.
        */
        logger.info("Running Get Region With MultiPolygon Coordinates Scenario...");
        ParseRegionWithMultiPolygonCoordinatesScenario parseRegionWithMultiPolygonCoordinatesScenario = new ParseRegionWithMultiPolygonCoordinatesScenario();
        parseRegionWithMultiPolygonCoordinatesScenario.setProfile(new DefaultRapidPartnerProfile());
        parseRegionWithMultiPolygonCoordinatesScenario.run();

        /*  Run Parse Region Ancestors Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details with ancestors by region id
            2. Parsing region ancestors
        */
        logger.info("Running Parse Region Ancestors Scenario...");
        ParseRegionAncestorsScenario parseRegionAncestorsScenario = new ParseRegionAncestorsScenario();
        parseRegionAncestorsScenario.setProfile(new DefaultRapidPartnerProfile());
        parseRegionAncestorsScenario.run();

        /*  Run Parse Region Descendants Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details with descendants by region id
            2. Parsing region descendants
        */
        logger.info("Running Parse Region Descendants Scenario...");
        ParseRegionDescendantsScenario parseRegionDescendantsScenario = new ParseRegionDescendantsScenario();
        parseRegionDescendantsScenario.setProfile(new DefaultRapidPartnerProfile());
        parseRegionDescendantsScenario.run();

        /*  Run Parse Region Coordinates Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details with coordinates by region id
            2. Parsing region coordinates
        */
        logger.info("Running Parse Region Coordinates Scenario...");
        ParseRegionCoordinatesScenario parseRegionCoordinatesScenario = new ParseRegionCoordinatesScenario();
        parseRegionCoordinatesScenario.setProfile(new DefaultRapidPartnerProfile());
        parseRegionCoordinatesScenario.run();

        /*  Run Parse Region Categories Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting region details with property ids by region id
            2. Parsing region categories
        */
        logger.info("Running Parse Region Categories Scenario...");
        ParseRegionCategoriesScenario parseRegionCategoriesScenario = new ParseRegionCategoriesScenario();
        parseRegionCategoriesScenario.setProfile(new DefaultRapidPartnerProfile());
        parseRegionCategoriesScenario.run();

        /*  Run Get Additional Availability Of Property Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting property availability for a test property
            2. Getting additional availability of the first property returned
        */

        logger.info("=============================== End of Geography Scenarios ===========================");


        logger.info("=======================================================================================");
        logger.info("=======================================================================================");
        logger.info("==                                                                                   ==");
        logger.info("==         That's all folks! Thanks for watching the demonstration of RAPID SDK.     ==");
        logger.info("==                                                                                   ==");
        logger.info("=======================================================================================");
        logger.info("=======================================================================================");

    }
}
