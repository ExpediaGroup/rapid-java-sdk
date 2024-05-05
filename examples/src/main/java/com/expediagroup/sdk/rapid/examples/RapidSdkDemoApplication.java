package com.expediagroup.sdk.rapid.examples;

import com.expediagroup.sdk.rapid.examples.salesprofiles.DefaultRapidPartnerProfile;
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
        logger.info("Running Single Room Book Scenario using the default profile...");
        SingleRoomBookScenario singleRoomBookScenario = new SingleRoomBookScenario();
        singleRoomBookScenario.setProfile(new DefaultRapidPartnerProfile());
        singleRoomBookScenario.run();
    }
}
