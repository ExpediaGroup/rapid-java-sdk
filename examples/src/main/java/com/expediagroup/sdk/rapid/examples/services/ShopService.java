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

    public List<Property> getSingleRoomPropertiesAvailability(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
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

    public List<Property> getMultipleRoomsPropertiesAvailability(RapidPartnerSalesProfile rapidPartnerSalesProfile, List<String> occupancy) {
        return rapidClient.getAvailability(
                LocalDate.now().plusDays(14).toString(),
                LocalDate.now().plusDays(15).toString(),
                "USD",
                "US",
                "en-US",
                occupancy,
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

        RoomAvailability roomAvailability = propertyAvailability.getRooms().get(0);

        return rapidClient.priceCheck(
                propertyAvailability.getPropertyId(),
                roomAvailability.getId(),
                roomAvailability.getRates().get(0).getId(),
                rapidClient.helpers.extractToken(roomAvailability.getRates().get(0).getBedGroups().entrySet()
                        .stream().findFirst().get().getValue().getLinks().getPriceCheck().getHref()));
    }
}
