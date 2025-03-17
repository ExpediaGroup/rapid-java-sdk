package com.expediagroup.sdk.rapid.examples;

import com.expediagroup.sdk.rapid.examples.salesprofiles.DefaultRapidPartnerProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.addelivery.GetAdsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.AsyncSingleRoomBookScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.MultiRoomHoldAndResumeBookScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.SingleRoomBookScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.content.GetPropertyContentInAdditionalLanguageScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.content.GetPropertyContentScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.managebooking.CancelHeldBookingScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.managebooking.ChangeRoomDetailsScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.managebooking.DeleteRoomScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.shopping.GetAdditionalAvailabilityOfPropertyScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.shopping.GetPaymentOptionsOfRoomScenario;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class to run the Rapid SDK demonstration.
 */
public class RapidSdkDemoApplication {

  private static final Logger logger = LoggerFactory.getLogger(RapidSdkDemoApplication.class);

  /**
   * Main method to run the Rapid SDK demonstration.
   *
   * @param args The command line arguments.
   * @throws ExecutionException   If the execution fails.
   * @throws InterruptedException If the execution is interrupted.
   */
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    logger.info(
        "=======================================================================================");
    logger.info(
        "=======================================================================================");
    logger.info(
        "==                                                                                   ==");
    logger.info(
        "==         Howdy! This is a demonstration of Expedia Group RAPID SDK, Enjoy!         ==");
    logger.info(
        "==                                                                                   ==");
    logger.info(
        "=======================================================================================");
    logger.info(
        "=======================================================================================");

    logger.info(
        "=============================== Running Shopping Scenarios ============================");

    /*  Run Get Additional Availability Of Property Scenario using the default profile
        This scenario demonstrates the following:
        1. Getting property availability for a test property
        2. Getting additional availability of the first property returned
    */
    GetAdditionalAvailabilityOfPropertyScenario getAdditionalAvailabilityOfPropertyScenario =
        new GetAdditionalAvailabilityOfPropertyScenario();
    getAdditionalAvailabilityOfPropertyScenario.setProfile(new DefaultRapidPartnerProfile());
    getAdditionalAvailabilityOfPropertyScenario.run();

    /*  Run Get Payment Options Of Room Scenario using the default profile
        This scenario demonstrates the following:
        1. Shopping for properties
        2. Checking room prices for the property
        3. Getting payment options of the first room in the property
    */
    GetPaymentOptionsOfRoomScenario getPaymentOptionsOfRoomScenario =
        new GetPaymentOptionsOfRoomScenario();
    getPaymentOptionsOfRoomScenario.setProfile(new DefaultRapidPartnerProfile());
    getPaymentOptionsOfRoomScenario.run();

    logger.info(
        "=============================== End of Shopping Scenarios =============================");

    logger.info(
        "=============================== Running Booking Scenarios =============================");

    /*  Run Single Room Book Scenario using the default profile
        This scenario demonstrates the following:
        1. Shopping for properties
        2. Checking room prices for the property
        3. Booking the property
    */
    SingleRoomBookScenario singleRoomBookScenario = new SingleRoomBookScenario();
    singleRoomBookScenario.setProfile(new DefaultRapidPartnerProfile());
    singleRoomBookScenario.run();

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
    MultiRoomHoldAndResumeBookScenario multiRoomHoldAndResumeBookScenario =
        new MultiRoomHoldAndResumeBookScenario();
    multiRoomHoldAndResumeBookScenario.setProfile(new DefaultRapidPartnerProfile());
    multiRoomHoldAndResumeBookScenario.run();

    logger.info(
        "=============================== End of Booking Scenarios ==============================");

    logger.info(
        "=========================== Running Manage Booking Scenarios ===========================");

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

    logger.info(
        "============================ End of Manage Booking Scenarios ===========================");

    logger.info(
        "========================== Running Property Content Scenarios ==========================");

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
    GetPropertyContentInAdditionalLanguageScenario getPropertyContentInAdditionalLanguageScenario =
        new GetPropertyContentInAdditionalLanguageScenario();
    getPropertyContentInAdditionalLanguageScenario.setProfile(new DefaultRapidPartnerProfile());
    getPropertyContentInAdditionalLanguageScenario.run();

    logger.info(
        "========================== End of Property Content Scenarios ===========================");

    logger.info("======================== Running Ad Delivery Scenarios =========================");

    /* Run Get Ads Scenario using the default profile
        This scenario demonstrates the following:
        1. Shopping for properties
        2. Getting property availability for test property
        3. Getting sponsored listings for the property
    */
    GetAdsScenario getAdsScenario = new GetAdsScenario();
    getAdsScenario.setProfile(new DefaultRapidPartnerProfile());
    getAdsScenario.run();

    logger.info("=========================== End of Ad Delivery Scenarios ======================");

    logger.info(
        "=======================================================================================");
    logger.info(
        "=======================================================================================");
    logger.info(
        "==                                                                                   ==");
    logger.info(
        "==              That's all folks! That was the demonstration of RAPID SDK.           ==");
    logger.info(
        "==                                                                                   ==");
    logger.info(
        "=======================================================================================");
    logger.info(
        "=======================================================================================");
    System.exit(0);
  }
}
