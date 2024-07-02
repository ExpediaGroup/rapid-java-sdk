package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.models.Link;
import com.expediagroup.sdk.rapid.models.Property;
import com.expediagroup.sdk.rapid.models.PropertyAvailability;
import com.expediagroup.sdk.rapid.models.RoomPriceCheck;
import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperation;
import com.expediagroup.sdk.rapid.operations.GetAvailabilityOperation;
import com.expediagroup.sdk.rapid.operations.GetAvailabilityOperationParams;
import com.expediagroup.sdk.rapid.operations.PriceCheckOperation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ShopService extends RapidService {

    public Response<List<Property>> getSingleRoomPropertiesAvailability(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        GetAvailabilityOperationParams params = GetAvailabilityOperationParams.builder()
                .customerIp(Constants.CUSTOMER_IP)
                .checkin(LocalDate.now().plusDays(14).toString())
                .checkout(LocalDate.now().plusDays(15).toString())
                .currency("USD")
                .countryCode("US")
                .language("en-US")
                .occupancy(Arrays.asList("2"))
                .propertyId(Arrays.asList(Constants.TEST_PROPERTY_ID))
                .salesChannel("website")
                .salesEnvironment("hotel_only")
                .ratePlanCount(BigDecimal.ONE)
                .build();

        GetAvailabilityOperation operation = new GetAvailabilityOperation(params);

        return rapidClient.execute(operation);
    }

    public Response<List<Property>> getMultipleRoomsPropertiesAvailability(RapidPartnerSalesProfile rapidPartnerSalesProfile, List<String> occupancy) {

        GetAvailabilityOperationParams params = GetAvailabilityOperationParams.builder()
                .customerIp(Constants.CUSTOMER_IP)
                .checkin(LocalDate.now().plusDays(14).toString())
                .checkout(LocalDate.now().plusDays(15).toString())
                .currency("USD")
                .countryCode("US")
                .language("en-US")
                .occupancy(occupancy)
                .propertyId(Arrays.asList(Constants.TEST_PROPERTY_ID))
                .ratePlanCount(BigDecimal.ONE)
                .salesChannel("website")
                .salesEnvironment("hotel_only")
                .build();

        GetAvailabilityOperation operation = new GetAvailabilityOperation(params);

        return rapidClient.execute(operation);
    }

    public Response<RoomPriceCheck> checkRoomPrices(PropertyAvailability propertyAvailability, int selectedRoomIndex, int selectedRateIndex) {

        Link priceCheckLink = propertyAvailability.getRooms().get(selectedRoomIndex).getRates()
                .get(selectedRateIndex).getBedGroups().entrySet().stream().findFirst()
                .get().getValue().getLinks().getPriceCheck();

        PriceCheckOperation operation = new PriceCheckOperation(priceCheckLink);
        return rapidClient.execute(operation);
    }

    public Response<List<PropertyAvailability>> getAdditionalAvailability(PropertyAvailability propertyAvailability) {
        return rapidClient.execute(new GetAdditionalAvailabilityOperation(propertyAvailability.getLinks().getAdditionalRates()));
    }
}