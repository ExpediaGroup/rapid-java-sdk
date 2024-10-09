package com.expediagroup.sdk.rapid.examples;

import com.expediagroup.sdk.rapid.examples.salesprofiles.DefaultRapidPartnerProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.AsyncSingleRoomBookScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.MultiRoomHoldAndResumeBookScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.CompletePaymentSessionSingleRoomBookScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.content.GetPropertyContentInAdditionalLanguageScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.content.GetPropertyContentScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.GetListOfRegionNamesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.GetRegionByAncestorIdScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.GetRegionDetailsAndPropertyIdsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionAncestorsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionCategoriesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionCoordinatesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionDescendantsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.geography.ParseRegionWithMultiPolygonCoordinatesScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.managebooking.CancelHeldBookingScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.managebooking.ChangeRoomDetailsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.managebooking.CommitHardChangeScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.managebooking.DeleteRoomScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.shopping.GetAdditionalAvailabilityOfPropertyScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.shopping.GetPaymentOptionsOfRoomScenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;


public class RapidSdkDemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(RapidSdkDemoApplication.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        logger.info("=======================================================================================");
        logger.info("=======================================================================================");
        logger.info("==                                                                                   ==");
        logger.info("==         Howdy! This is a demonstration of Expedia Group RAPID SDK, Enjoy!         ==");
        logger.info("==                                                                                   ==");
        logger.info("=======================================================================================");
        logger.info("=======================================================================================");

        logger.info("=============================== Running Shopping Scenarios ============================");

        /*  Run Get Additional Availability Of Property Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting property availability for a test property
            2. Getting additional availability of the first property returned
        */
        GetAdditionalAvailabilityOfPropertyScenario getAdditionalAvailabilityOfPropertyScenario = new GetAdditionalAvailabilityOfPropertyScenario();
        getAdditionalAvailabilityOfPropertyScenario.setProfile(new DefaultRapidPartnerProfile());
        getAdditionalAvailabilityOfPropertyScenario.run();

        /*  Run Get Payment Options Of Room Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties
            2. Checking room prices for the property
            3. Getting payment options of the first room in the property
        */
        GetPaymentOptionsOfRoomScenario getPaymentOptionsOfRoomScenario = new GetPaymentOptionsOfRoomScenario();
        getPaymentOptionsOfRoomScenario.setProfile(new DefaultRapidPartnerProfile());
        getPaymentOptionsOfRoomScenario.run();

        logger.info("=============================== End of Shopping Scenarios =============================");

        logger.info("=============================== Running Booking Scenarios =============================");

        /*  Run Single Room Book Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties
            2. Checking room prices for the property
            3. Booking the property
        */
        CompletePaymentSessionSingleRoomBookScenario completePaymentSessionSingleRoomBookScenario = new CompletePaymentSessionSingleRoomBookScenario();
        completePaymentSessionSingleRoomBookScenario.setProfile(new DefaultRapidPartnerProfile());
        completePaymentSessionSingleRoomBookScenario.run();

        /*  Run Async Single Room Book Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties
            2. Checking room prices for the property
            3. Booking the property in asynchronous manner
        */
        AsyncSingleRoomBookScenario asyncSingleRoomBookScenario = new AsyncSingleRoomBookScenario();
        asyncSingleRoomBookScenario.setProfile(new DefaultRapidPartnerProfile());
        asyncSingleRoomBookScenario.run();

        /*  Run Multiple Room Hold and Resume Book Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties with specifying multiple occupancy instances for multiple rooms
            2. Checking room prices for the property rooms.
            3. Hold the property rooms.
            4. Resume the booking process.
        */
        MultiRoomHoldAndResumeBookScenario multiRoomHoldAndResumeBookScenario = new MultiRoomHoldAndResumeBookScenario();
        multiRoomHoldAndResumeBookScenario.setProfile(new DefaultRapidPartnerProfile());
        multiRoomHoldAndResumeBookScenario.run();

        logger.info("=============================== End of Booking Scenarios ==============================");

        logger.info("=============================== Running Manage Booking Scenarios ===========================");

        /*  Run Change Room Details Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties
            2. Checking room prices for the property
            3. Booking a single room in the property
            4. Retrieve itinerary by itinerary id
            5. Change room details for first room in booking
            6. Retrieve updated itinerary
        */
        ChangeRoomDetailsScenario changeRoomDetailsScenario = new ChangeRoomDetailsScenario();
        changeRoomDetailsScenario.setProfile(new DefaultRapidPartnerProfile());
        changeRoomDetailsScenario.run();

        /*  Run Cancel Held Booking Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties
            2. Checking room prices for the property
            3. Booking a room with hold in the property
            4. Cancelling the held booking
        */
        CancelHeldBookingScenario cancelHeldBookingScenario = new CancelHeldBookingScenario();
        cancelHeldBookingScenario.setProfile(new DefaultRapidPartnerProfile());
        cancelHeldBookingScenario.run();

        /* Run Delete Room Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties
            2. Checking room prices for the property
            3. Booking a room in the property
            4. Retrieve itinerary by itinerary id
            5. Deleting the first room in booking
            6. Retrieve updated itinerary
         */
        DeleteRoomScenario deleteRoomScenario = new DeleteRoomScenario();
        deleteRoomScenario.setProfile(new DefaultRapidPartnerProfile());
        deleteRoomScenario.run();

        /*  Run Commit Change Room With Refund Scenario using the default profile
            This scenario demonstrates the following:
            1. Shopping for properties
            2. Checking room prices for the property
            3. Booking two rooms in the property
            4. Retrieve itinerary by itinerary id
            5. Check if booking is eligible for hard change
            6. Delete first room in booking
            7. Retrieve updated itinerary
        */
        CommitHardChangeScenario commitHardChangeScenario = new CommitHardChangeScenario();
        commitHardChangeScenario.setProfile(new DefaultRapidPartnerProfile());
        commitHardChangeScenario.run();

        logger.info("=============================== End of Manage Booking Scenarios ===========================");

        logger.info("=============================== Running Property Content Scenarios ===========================");

        /*  Run Get Property Content Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting property content by property id
        */
        GetPropertyContentScenario getPropertyContentScenario = new GetPropertyContentScenario();
        getPropertyContentScenario.setProfile(new DefaultRapidPartnerProfile());
        getPropertyContentScenario.run();

        /*  Run Get Property Content In Additional Language Scenario using the default profile
            This scenario demonstrates the following:
            1. Getting property content by property id
            2. Getting property content in additional language
        */
        GetPropertyContentInAdditionalLanguageScenario getPropertyContentInAdditionalLanguageScenario = new GetPropertyContentInAdditionalLanguageScenario();
        getPropertyContentInAdditionalLanguageScenario.setProfile(new DefaultRapidPartnerProfile());
        getPropertyContentInAdditionalLanguageScenario.run();

        logger.info("=============================== End of Property Content Scenarios ===========================");

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
        logger.info("==              That's all folks! That was the demonstration of RAPID SDK.           ==");
        logger.info("==                                                                                   ==");
        logger.info("=======================================================================================");
        logger.info("=======================================================================================");
        System.exit(0);
    }
}
