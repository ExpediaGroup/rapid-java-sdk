package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.models.PaymentOption;
import com.expediagroup.sdk.rapid.models.Property;
import com.expediagroup.sdk.rapid.models.PropertyAvailability;
import com.expediagroup.sdk.rapid.models.RoomAvailability;
import com.expediagroup.sdk.rapid.models.RoomPriceCheck;
import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperation;
import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperationLink;
import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperationParams;
import com.expediagroup.sdk.rapid.operations.GetAvailabilityOperation;
import com.expediagroup.sdk.rapid.operations.GetAvailabilityOperationParams;
import com.expediagroup.sdk.rapid.operations.GetPaymentOptionsOperation;
import com.expediagroup.sdk.rapid.operations.GetPaymentOptionsOperationContext;
import com.expediagroup.sdk.rapid.operations.GetPaymentOptionsOperationLink;
import com.expediagroup.sdk.rapid.operations.PriceCheckOperation;
import com.expediagroup.sdk.rapid.operations.PriceCheckOperationLink;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ShopService extends RapidService {

    public Response<List<Property>> getPropertiesAvailability(List<String> occupancy, RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        GetAvailabilityOperationParams params = GetAvailabilityOperationParams.builder()
                .customerIp(Constants.CUSTOMER_IP)
                .checkin(LocalDate.now().plusDays(14).toString())
                .checkout(LocalDate.now().plusDays(15).toString())
                .currency("USD")
                .countryCode("US")
                .language("en-US")
                .occupancy(occupancy)
                .propertyId(Arrays.asList(Constants.TEST_PROPERTY_ID))
                .salesChannel("website")
                .salesEnvironment("hotel_only")
                .ratePlanCount(BigDecimal.ONE)
                .build();

        GetAvailabilityOperation operation = new GetAvailabilityOperation(params);

        return rapidClient.execute(operation);
    }

    public CompletableFuture<Response<List<Property>>> asyncGetSingleRoomPropertiesAvailability(RapidPartnerSalesProfile rapidPartnerSalesProfile) {
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

        return rapidClient.executeAsync(operation);
    }

    public Response<RoomPriceCheck> checkRoomPrices(PropertyAvailability propertyAvailability, int selectedRoomIndex, int selectedRateIndex) {

        PriceCheckOperationLink priceCheckLink = propertyAvailability.getRooms().get(selectedRoomIndex).getRates().get(selectedRateIndex).getBedGroups().entrySet()
                .stream().findFirst().get().getValue().getLinks().getPriceCheck();

        PriceCheckOperation operation = new PriceCheckOperation(priceCheckLink, null);
        return rapidClient.execute(operation);
    }

    public Response<List<PropertyAvailability>> getAdditionalAvailability(PropertyAvailability propertyAvailability) {
        GetAdditionalAvailabilityOperationLink additionalRatesLink = propertyAvailability.getLinks().getAdditionalRates();

        return rapidClient.execute(new GetAdditionalAvailabilityOperation(additionalRatesLink, null));
    }

    public Response<List<PropertyAvailability>> getAdditionalAvailabilityForChange(GetAdditionalAvailabilityOperationLink additionalRatesLink, String newCheckin, String newCheckout) {
        GetAdditionalAvailabilityOperationParams params = GetAdditionalAvailabilityOperationParams
                .builder()
                .checkin(newCheckin)
                .checkout(newCheckout)
                .build();

        // we should be able to send params here
        return rapidClient.execute(new GetAdditionalAvailabilityOperation(additionalRatesLink, null));
    }

    public CompletableFuture<Response<RoomPriceCheck>> asyncCheckRoomPrices(PropertyAvailability propertyAvailability, int selectedRoomIndex, int selectedRateIndex) {

        PriceCheckOperationLink priceCheckLink = propertyAvailability.getRooms().get(selectedRoomIndex).getRates().get(selectedRateIndex).getBedGroups().entrySet()
                .stream().findFirst().get().getValue().getLinks().getPriceCheck();

        PriceCheckOperation operation = new PriceCheckOperation(priceCheckLink, null);

        return rapidClient.executeAsync(operation);
    }

    public CompletableFuture<Response<PaymentOption>> getPaymentOptions(RoomAvailability roomAvailability, RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        GetPaymentOptionsOperationLink paymentOptionsLink = roomAvailability.getRates().get(0).getLinks().getPaymentOptions();
        if (paymentOptionsLink == null) {
            throw new IllegalStateException("No payment options link found for room: " + roomAvailability.getId());
        }

        return rapidClient.executeAsync(new GetPaymentOptionsOperation(paymentOptionsLink, new GetPaymentOptionsOperationContext()));
    }
}