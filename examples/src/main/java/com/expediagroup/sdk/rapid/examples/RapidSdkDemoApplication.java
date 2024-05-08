package com.expediagroup.sdk.rapid.examples;

import com.expediagroup.sdk.rapid.examples.salesprofiles.DefaultRapidPartnerProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.MultiRoomHoldAndResumeBookScenario;
import com.expediagroup.sdk.rapid.examples.scenarios.booking.SingleRoomBookScenario;
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

    }
}
