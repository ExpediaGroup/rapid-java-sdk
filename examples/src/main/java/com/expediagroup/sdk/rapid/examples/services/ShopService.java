package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.models.BedGroupAvailability;
import com.expediagroup.sdk.rapid.models.BedGroupAvailabilityLinks;
import com.expediagroup.sdk.rapid.models.Link;
import com.expediagroup.sdk.rapid.models.Property;
import com.expediagroup.sdk.rapid.models.PropertyAvailability;
import com.expediagroup.sdk.rapid.models.PropertyAvailabilityLinks;
import com.expediagroup.sdk.rapid.models.Rate;
import com.expediagroup.sdk.rapid.models.RoomAvailability;
import com.expediagroup.sdk.rapid.models.RoomPriceCheck;
import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperation;
import com.expediagroup.sdk.rapid.operations.GetAvailabilityOperation;
import com.expediagroup.sdk.rapid.operations.GetAvailabilityOperationParams;
import com.expediagroup.sdk.rapid.operations.PriceCheckOperation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        List<RoomAvailability> rooms = propertyAvailability.getRooms();
        if (rooms == null) throw new RuntimeException();

        List<Rate> rates = rooms.get(selectedRoomIndex).getRates();
        if (rates == null) throw new RuntimeException();

        Rate selectedRate = rates.get(selectedRateIndex);
        if (selectedRate == null) throw new RuntimeException();

        Map<String, BedGroupAvailability> bedGroups = selectedRate.getBedGroups();
        if (bedGroups == null) throw new RuntimeException();

        Optional<Map.Entry<String, BedGroupAvailability>> bedGroupAvailabilityEntry = bedGroups.entrySet().stream().findFirst();
        if (!bedGroupAvailabilityEntry.isPresent()) throw new RuntimeException();

        BedGroupAvailabilityLinks links = bedGroupAvailabilityEntry.get().getValue().getLinks();
        if (links == null) throw new RuntimeException();

        Link priceCheckLink = links.getPriceCheck();
        if (priceCheckLink == null) throw new RuntimeException();

        PriceCheckOperation operation = new PriceCheckOperation(priceCheckLink, null);
        return rapidClient.execute(operation);
    }

    public Response<List<PropertyAvailability>> getAdditionalAvailability(PropertyAvailability propertyAvailability) {
        PropertyAvailabilityLinks links = propertyAvailability.getLinks();
        if (links == null) throw new RuntimeException();

        Link additionalRatesLink = links.getAdditionalRates();
        if (additionalRatesLink == null) throw new RuntimeException();

        return rapidClient.execute(new GetAdditionalAvailabilityOperation(additionalRatesLink, null));
    }
}