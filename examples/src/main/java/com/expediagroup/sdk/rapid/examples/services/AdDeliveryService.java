package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.models.AdsRequest;
import com.expediagroup.sdk.rapid.models.AdsResponse;
import com.expediagroup.sdk.rapid.models.Coordinates1;
import com.expediagroup.sdk.rapid.models.GuestCounts;
import com.expediagroup.sdk.rapid.models.PageType;
import com.expediagroup.sdk.rapid.models.ProductLine;
import com.expediagroup.sdk.rapid.models.SalesChannel;
import com.expediagroup.sdk.rapid.models.SortType;
import com.expediagroup.sdk.rapid.operations.GetAdsOperation;
import com.expediagroup.sdk.rapid.operations.GetAdsOperationParams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AdDeliveryService extends RapidService {

    public Response<AdsResponse> getAds(List<String> propertyIds) {
        GetAdsOperationParams getAdsOperationParams = getAdsOperationParams();

        AdsRequest adsRequest = AdsRequest.builder()
                .checkin(LocalDate.now().plusDays(20).toString())
                .checkout(LocalDate.now().plusDays(23).toString())
                .occupancies(Arrays.asList(GuestCounts.builder().adultCount(2).childCount(0).build()))
                .searchProductLines(Arrays.asList(ProductLine.LODGING))
                .propertyIds(propertyIds)
                .pageType(PageType.SEARCH_RESULTS)
                .salesChannel(SalesChannel.WEBSITE)
                .countryCode("US")
                .experimentIds(Arrays.asList("1234"))
                .language("en-US")
                .sortType(SortType.DEFAULT)
                .travelerLocation(Coordinates1.builder()
                    .latitude(BigDecimal.valueOf(37.7749))
                    .longitude(BigDecimal.valueOf(-122.4194))
                    .build())
                .build();

        GetAdsOperation getAdsOperation =
                new GetAdsOperation(getAdsOperationParams, adsRequest);

        return rapidClient.execute(getAdsOperation);
    }

    private GetAdsOperationParams getAdsOperationParams() {
        return GetAdsOperationParams
                .builder()
                .customerIp(Constants.CUSTOMER_IP)
                .build();
    }
}
