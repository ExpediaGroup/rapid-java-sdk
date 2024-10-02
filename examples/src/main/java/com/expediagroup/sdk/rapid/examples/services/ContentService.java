package com.expediagroup.sdk.rapid.examples.services;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import com.expediagroup.sdk.rapid.models.PropertyContent;
import com.expediagroup.sdk.rapid.operations.GetPropertyContentOperation;
import com.expediagroup.sdk.rapid.operations.GetPropertyContentOperationContext;
import com.expediagroup.sdk.rapid.operations.GetPropertyContentOperationLink;
import com.expediagroup.sdk.rapid.operations.GetPropertyContentOperationParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ContentService extends RapidService {

    Logger logger = LoggerFactory.getLogger(ContentService.class);

    public Response<Map<String, PropertyContent>> getPropertyContent(List<String> propertyIds, String language, RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        GetPropertyContentOperationParams params = GetPropertyContentOperationParams.builder()
                .propertyId(propertyIds)
                .supplySource("expedia")
                .language(language)
                .build();

        GetPropertyContentOperation operation = new GetPropertyContentOperation(params);

        return rapidClient.execute(operation);
    }

    public CompletableFuture<Response<Map<String, PropertyContent>>> asyncGetPropertyContent(List<String> propertyIds, String language, RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        GetPropertyContentOperationParams params = GetPropertyContentOperationParams.builder()
                .propertyId(propertyIds)
                .supplySource("expedia")
                .language(language)
                .build();

        GetPropertyContentOperation operation = new GetPropertyContentOperation(params);

        return rapidClient.executeAsync(operation);
    }

    public CompletableFuture<Response<Map<String, PropertyContent>>> asyncGetPropertyContentInAdditionalLanguage(PropertyContent propertyContent, RapidPartnerSalesProfile rapidPartnerSalesProfile) {
        String language = propertyContent.getAddress().getLocalized()
                .getLinks().keySet().iterator().next();
        logger.info("Get property content in additional language from returned link, language: [{}]...", language);

        GetPropertyContentOperationLink propertyContentLink = propertyContent.getAddress().getLocalized().getLinks().get(language);

        if (propertyContentLink == null) {
            throw new IllegalStateException("No link found for fr-FR language");
        }

        GetPropertyContentOperation operation = new GetPropertyContentOperation(propertyContentLink, new GetPropertyContentOperationContext());

        return rapidClient.executeAsync(operation);
    }
}
