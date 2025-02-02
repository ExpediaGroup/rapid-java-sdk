package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.rapid.examples.Constants;
import com.expediagroup.sdk.rapid.models.Coordinates1;
import com.expediagroup.sdk.rapid.models.GuestCounts;
import com.expediagroup.sdk.rapid.models.LineOfBusiness;
import com.expediagroup.sdk.rapid.models.PageType;
import com.expediagroup.sdk.rapid.models.RequestCriteria;
import com.expediagroup.sdk.rapid.models.SalesChannel;
import com.expediagroup.sdk.rapid.models.SearchCriteria;
import com.expediagroup.sdk.rapid.models.SponsoredContentRequest;
import com.expediagroup.sdk.rapid.models.SponsoredContentResponse;
import com.expediagroup.sdk.rapid.operations.PostAdDeliveryOperation;
import com.expediagroup.sdk.rapid.operations.PostAdDeliveryOperationParams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SponsoredContentService extends RapidService {

    public Response<SponsoredContentResponse> getSponsoredContent(List<String> propertyIds) {
        PostAdDeliveryOperationParams postAdDeliveryOperationParams = getPostAdDeliveryOperationParams();

        SponsoredContentRequest sponsoredContentRequest = SponsoredContentRequest.builder()
                .searchCriteria(
                        SearchCriteria.builder()
                                .checkin(LocalDate.now().plusDays(20).toString())
                                .checkout(LocalDate.now().plusDays(23).toString())
                                .occupancy(Arrays.asList(GuestCounts.builder().adultCount(2).childCount(0).build()))
                                .searchLinesOfBusiness(Arrays.asList(LineOfBusiness.LODGING))
                                .properties(propertyIds)
                                .pageType(PageType.SEARCH_RESULTS)
                                .build()
                ).requestCriteria(
                        RequestCriteria.builder()
                                .salesChannel(SalesChannel.WEBSITE)
                                .countryCode("US")
                                .experimentIds(Arrays.asList("1234"))
                                .language("en-US")
                                .travelerLocation(Coordinates1.builder()
                                        .latitude(BigDecimal.valueOf(37.7749))
                                        .longitude(BigDecimal.valueOf(-122.4194))
                                        .build())
                                .build())
                .build();
        PostAdDeliveryOperation postAdDeliveryOperation =
                new PostAdDeliveryOperation(postAdDeliveryOperationParams, sponsoredContentRequest);

        return rapidClient.execute(postAdDeliveryOperation);
    }

    private PostAdDeliveryOperationParams getPostAdDeliveryOperationParams() {
        PostAdDeliveryOperationParams postAdDeliveryOperationParams = PostAdDeliveryOperationParams
                .builder()
                .traceId(UUID.randomUUID())
                .customerIp(Constants.CUSTOMER_IP)
                .customerSessionId(Constants.CUSTOMER_SESSION_ID)
                .build();
        return postAdDeliveryOperationParams;
    }
}