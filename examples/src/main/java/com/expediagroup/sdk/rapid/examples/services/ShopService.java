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

/**
 * Service class for interacting with the Rapid API.
 */
public class ShopService extends RapidService {

  /**
   * Retrieves the availability of properties based on the given occupancy.
   *
   * @param occupancy the occupancy to check availability for
   * @param rapidPartnerSalesProfile the sales profile to use for the operation
   * @return the response containing the list of properties with availability
   */
  public Response<List<Property>> getPropertiesAvailability(
      List<String> occupancy,
      RapidPartnerSalesProfile rapidPartnerSalesProfile
  ) {
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

  /**
   * Retrieves the availability of properties based on the given occupancy asynchronously.
   *
   * @param rapidPartnerSalesProfile the sales profile to use for the operation
   * @return the future response containing the list of properties with availability
   */
  public CompletableFuture<Response<List<Property>>> asyncGetSingleRoomPropertiesAvailability(
      RapidPartnerSalesProfile rapidPartnerSalesProfile) {
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

  /**
   * Checks the room prices for a given property availability.
   *
   * @param propertyAvailability the property availability to check prices for
   * @param selectedRoomIndex the index of the selected room
   * @param selectedRateIndex the index of the selected rate
   * @return the response containing the room price check
   */
  public Response<RoomPriceCheck> checkRoomPrices(PropertyAvailability propertyAvailability,
                                                  int selectedRoomIndex, int selectedRateIndex) {

    PriceCheckOperationLink priceCheckLink =
        propertyAvailability.getRooms().get(selectedRoomIndex).getRates().get(selectedRateIndex)
            .getBedGroups().entrySet()
            .stream().findFirst().get().getValue().getLinks().getPriceCheck();

    PriceCheckOperation operation = new PriceCheckOperation(priceCheckLink, null);
    return rapidClient.execute(operation);
  }

  /**
   * Retrieves the additional availability for a given property availability.
   *
   * @param propertyAvailability the property availability to get additional availability for
   * @return the response containing the list of properties with additional availability
   */
  public Response<List<PropertyAvailability>> getAdditionalAvailability(
      PropertyAvailability propertyAvailability) {
    GetAdditionalAvailabilityOperationLink additionalRatesLink =
        propertyAvailability.getLinks().getAdditionalRates();

    return rapidClient.execute(new GetAdditionalAvailabilityOperation(additionalRatesLink, null));
  }

  /**
   * Retrieves the additional availability for a given property availability based on the new
   * check-in and check-out dates.
   *
   * @param additionalRatesLink the link to the additional rates operation
   * @param newCheckin the new check-in date
   * @param newCheckout the new check-out date
   * @return the response containing the list of properties with additional availability
   */
  public Response<List<PropertyAvailability>> getAdditionalAvailabilityForChange(
      GetAdditionalAvailabilityOperationLink additionalRatesLink, String newCheckin,
      String newCheckout) {
    GetAdditionalAvailabilityOperationParams params = GetAdditionalAvailabilityOperationParams
        .builder()
        .checkin(newCheckin)
        .checkout(newCheckout)
        .build();

    // we should be able to send params here
    return rapidClient.execute(new GetAdditionalAvailabilityOperation(additionalRatesLink, null));
  }

  /**
   * Asynchronously checks the room prices for a given property availability.
   *
   * @param propertyAvailability the property availability to check prices for
   * @param selectedRoomIndex the index of the selected room
   * @param selectedRateIndex the index of the selected rate
   * @return the future response containing the room price check
   */
  public CompletableFuture<Response<RoomPriceCheck>> asyncCheckRoomPrices(
      PropertyAvailability propertyAvailability, int selectedRoomIndex, int selectedRateIndex) {

    PriceCheckOperationLink priceCheckLink =
        propertyAvailability.getRooms().get(selectedRoomIndex).getRates().get(selectedRateIndex)
            .getBedGroups().entrySet()
            .stream().findFirst().get().getValue().getLinks().getPriceCheck();

    PriceCheckOperation operation = new PriceCheckOperation(priceCheckLink, null);

    return rapidClient.executeAsync(operation);
  }

  /**
   * Asynchronously retrieves the payment options for a given room availability.
   *
   * @param roomAvailability the room availability to get payment options for
   * @param rapidPartnerSalesProfile the sales profile to use for the operation
   * @return the future response containing the payment options
   * @throws IllegalStateException if no payment options link is found for the room
   */
  public CompletableFuture<Response<PaymentOption>> getPaymentOptions(
      RoomAvailability roomAvailability, RapidPartnerSalesProfile rapidPartnerSalesProfile) {
    GetPaymentOptionsOperationLink paymentOptionsLink =
        roomAvailability.getRates().get(0).getLinks().getPaymentOptions();
    if (paymentOptionsLink == null) {
      throw new IllegalStateException(
          "No payment options link found for room: " + roomAvailability.getId());
    }

    return rapidClient.executeAsync(new GetPaymentOptionsOperation(paymentOptionsLink,
        new GetPaymentOptionsOperationContext()));
  }
}
