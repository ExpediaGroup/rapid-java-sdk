package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.models.Property;
import com.expediagroup.sdk.rapid.models.PropertyAvailability;
import com.expediagroup.sdk.rapid.models.RoomAvailability;
import com.expediagroup.sdk.rapid.models.RoomPriceCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ShopService extends RapidService {

    private static final Logger logger = LoggerFactory.getLogger(ShopService.class);

    public List<Property> getPropertiesAvailability(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        return rapidClient.getAvailability(
                LocalDate.now().plusDays(14).toString(),
                LocalDate.now().plusDays(15).toString(),
                "USD",
                "US",
                "en-US",
                Arrays.asList("2"),
                Arrays.asList(Constants.TEST_PROPERTY_ID),
                BigDecimal.ONE,
                "website",
                "hotel_only",
                Constants.CUSTOMER_IP,
                null, null, null, null, null, null, null, null,
                rapidPartnerSalesProfile.billingTerms,
                rapidPartnerSalesProfile.paymentTerms,
                rapidPartnerSalesProfile.partnerPointOfSale,
                rapidPartnerSalesProfile.platformName
        );
    }

    public RoomPriceCheck checkRoomPrices(PropertyAvailability propertyAvailability) {

        if (propertyAvailability.getPropertyId() == null) {
            logger.error("Property ID is null");
            return null;
        }

        if (propertyAvailability.getRooms() == null || propertyAvailability.getRooms().isEmpty()) {
            logger.info("No rooms available for the property.");
            return null;
        }

        RoomAvailability roomAvailability = propertyAvailability.getRooms().get(0);

        if (roomAvailability.getId() == null) {
            logger.error("Room ID is null");
            return null;
        }

        if (roomAvailability.getRates() == null || roomAvailability.getRates().isEmpty()) {
            logger.error("No rates available for the room.");
            return null;
        }

        if (roomAvailability.getRates().get(0).getId() == null) {
            logger.error("Rate ID is null");
            return null;
        }

        return rapidClient.priceCheck(
                propertyAvailability.getPropertyId(),
                roomAvailability.getId(),
                roomAvailability.getRates().get(0).getId(),
                rapidClient.helpers.extractToken(roomAvailability.getRates().get(0).getBedGroups().entrySet()
                        .stream().findFirst().get().getValue().getLinks().getPriceCheck().getHref()));
    }
}
