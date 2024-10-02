package com.expediagroup.sdk.rapid.examples.scenarios.shopping;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.examples.scenarios.RapidScenario;
import com.expediagroup.sdk.rapid.examples.services.ShopService;
import com.expediagroup.sdk.rapid.models.PaymentOption;
import com.expediagroup.sdk.rapid.models.Property;
import com.expediagroup.sdk.rapid.models.PropertyAvailability;
import com.expediagroup.sdk.rapid.models.RoomAvailability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class GetPaymentOptionsOfRoomScenario implements RapidScenario {

    private static final Logger logger = LoggerFactory.getLogger(GetPaymentOptionsOfRoomScenario.class);
    private final ShopService shopService = new ShopService();
    private RapidPartnerSalesProfile rapidPartnerSalesProfile;

    @Override
    public void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        this.rapidPartnerSalesProfile = rapidPartnerSalesProfile;
    }

    @Override
    public void run() {

        logger.info("Running Get Payment Options Of Room Scenario using the default profile...");

        // Shopping for properties
        logger.info("Getting property availability for test property: {}", Constants.TEST_PROPERTY_ID);

        List<Property> propertyAvailabilityList = shopService.getPropertiesAvailability(Arrays.asList("2"), this.rapidPartnerSalesProfile).getData();

        if (propertyAvailabilityList == null || propertyAvailabilityList.isEmpty()) {
            throw new IllegalStateException("No property availability found for the test property.");
        }

        logger.info("Property Availability: {}", propertyAvailabilityList.get(0).getStatus());

        if (!(propertyAvailabilityList.get(0) instanceof PropertyAvailability)) {
            throw new IllegalStateException("Property is not of type PropertyAvailability");
        }

        PropertyAvailability propertyAvailability = (PropertyAvailability) propertyAvailabilityList.get(0);

        RoomAvailability selectedRoomAvailability = propertyAvailability.getRooms().get(0);
        logger.info("Asynchronously get payment options of room: [{}]...", selectedRoomAvailability.getId());

        shopService.getPaymentOptions(selectedRoomAvailability, this.rapidPartnerSalesProfile)
                .thenAccept(paymentOptionResponse -> {
                    PaymentOption paymentOption = paymentOptionResponse.getData();
                    if (paymentOption.getAffiliateCollect() != null) {
                        logger.info("Payment option affiliate collect name: [{}]", paymentOption.getAffiliateCollect().getName());
                    }

                    if (paymentOption.getCreditCard() != null) {
                        logger.info("Payment option credit card options:");
                        paymentOption.getCreditCard().getCardOptions().forEach(creditCardOption -> {
                            logger.info("Credit card option name: [{}]", creditCardOption.getName());
                        });
                    }
                });
    }
}
