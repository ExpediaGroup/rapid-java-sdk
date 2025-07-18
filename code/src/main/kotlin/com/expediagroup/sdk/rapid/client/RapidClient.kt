/*
 * Copyright (C) 2022 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expediagroup.sdk.rapid.client

import com.expediagroup.sdk.core.client.BaseRapidClient
import com.expediagroup.sdk.core.configuration.RapidClientConfiguration
import com.expediagroup.sdk.core.constant.ConfigurationName
import com.expediagroup.sdk.core.constant.HeaderKey
import com.expediagroup.sdk.core.constant.provider.ExceptionMessageProvider.getMissingRequiredConfigurationMessage
import com.expediagroup.sdk.core.model.EmptyResponse
import com.expediagroup.sdk.core.model.Nothing
import com.expediagroup.sdk.core.model.Operation
import com.expediagroup.sdk.core.model.Response
import com.expediagroup.sdk.core.model.exception.client.ExpediaGroupConfigurationException
import com.expediagroup.sdk.core.model.exception.handle
import com.expediagroup.sdk.core.model.paging.Paginator
import com.expediagroup.sdk.core.model.paging.ResponsePaginator
import com.expediagroup.sdk.domain.rapid.RapidHelpers
import com.expediagroup.sdk.rapid.models.*
import com.expediagroup.sdk.rapid.models.exception.ErrorObjectMapper
import com.expediagroup.sdk.rapid.models.exception.ExpediaGroupApiErrorException
import com.expediagroup.sdk.rapid.operations.ChangeRoomDetailsOperation
import com.expediagroup.sdk.rapid.operations.ChangeRoomDetailsOperationParams
import com.expediagroup.sdk.rapid.operations.CommitChangeOperation
import com.expediagroup.sdk.rapid.operations.CommitChangeOperationParams
import com.expediagroup.sdk.rapid.operations.DeleteHeldBookingOperation
import com.expediagroup.sdk.rapid.operations.DeleteHeldBookingOperationParams
import com.expediagroup.sdk.rapid.operations.DeleteRoomOperation
import com.expediagroup.sdk.rapid.operations.DeleteRoomOperationParams
import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperation
import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperationParams
import com.expediagroup.sdk.rapid.operations.GetAmenitiesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetAmenitiesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetAvailabilityOperation
import com.expediagroup.sdk.rapid.operations.GetAvailabilityOperationParams
import com.expediagroup.sdk.rapid.operations.GetBookingReceiptOperation
import com.expediagroup.sdk.rapid.operations.GetBookingReceiptOperationParams
import com.expediagroup.sdk.rapid.operations.GetCalendarAvailabilityOperation
import com.expediagroup.sdk.rapid.operations.GetCalendarAvailabilityOperationParams
import com.expediagroup.sdk.rapid.operations.GetCategoriesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetCategoriesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetChainReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetChainReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetChainsReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetChainsReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetGeneralAttributesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetGeneralAttributesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetImagesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetImagesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetInactivePropertiesOperation
import com.expediagroup.sdk.rapid.operations.GetInactivePropertiesOperationParams
import com.expediagroup.sdk.rapid.operations.GetOnsitePaymentTypesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetOnsitePaymentTypesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetPaymentOptionsOperation
import com.expediagroup.sdk.rapid.operations.GetPaymentOptionsOperationParams
import com.expediagroup.sdk.rapid.operations.GetPetAttributesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetPetAttributesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetPropertyCatalogFileOperation
import com.expediagroup.sdk.rapid.operations.GetPropertyCatalogFileOperationParams
import com.expediagroup.sdk.rapid.operations.GetPropertyContentFileOperation
import com.expediagroup.sdk.rapid.operations.GetPropertyContentFileOperationParams
import com.expediagroup.sdk.rapid.operations.GetPropertyContentOperation
import com.expediagroup.sdk.rapid.operations.GetPropertyContentOperationParams
import com.expediagroup.sdk.rapid.operations.GetPropertyGuestReviewsOperation
import com.expediagroup.sdk.rapid.operations.GetPropertyGuestReviewsOperationParams
import com.expediagroup.sdk.rapid.operations.GetRateAmenitiesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetRateAmenitiesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetRegionOperation
import com.expediagroup.sdk.rapid.operations.GetRegionOperationParams
import com.expediagroup.sdk.rapid.operations.GetRegionsOperation
import com.expediagroup.sdk.rapid.operations.GetRegionsOperationParams
import com.expediagroup.sdk.rapid.operations.GetReservationByItineraryIdOperation
import com.expediagroup.sdk.rapid.operations.GetReservationByItineraryIdOperationParams
import com.expediagroup.sdk.rapid.operations.GetReservationOperation
import com.expediagroup.sdk.rapid.operations.GetReservationOperationParams
import com.expediagroup.sdk.rapid.operations.GetRoomAmenitiesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetRoomAmenitiesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetRoomImagesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetRoomImagesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetRoomViewsReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetRoomViewsReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetSpokenLanguagesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetSpokenLanguagesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetStatisticsReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetStatisticsReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.GetThemesReferenceOperation
import com.expediagroup.sdk.rapid.operations.GetThemesReferenceOperationParams
import com.expediagroup.sdk.rapid.operations.PostGeographyOperation
import com.expediagroup.sdk.rapid.operations.PostGeographyOperationParams
import com.expediagroup.sdk.rapid.operations.PostItineraryOperation
import com.expediagroup.sdk.rapid.operations.PostItineraryOperationParams
import com.expediagroup.sdk.rapid.operations.PostPaymentSessionsOperation
import com.expediagroup.sdk.rapid.operations.PostPaymentSessionsOperationParams
import com.expediagroup.sdk.rapid.operations.PriceCheckOperation
import com.expediagroup.sdk.rapid.operations.PriceCheckOperationParams
import com.expediagroup.sdk.rapid.operations.PutCompletePaymentSessionOperation
import com.expediagroup.sdk.rapid.operations.PutCompletePaymentSessionOperationParams
import com.expediagroup.sdk.rapid.operations.PutResumeBookingOperation
import com.expediagroup.sdk.rapid.operations.PutResumeBookingOperationParams
import com.expediagroup.sdk.rapid.operations.RequestTestNotificationOperation
import com.expediagroup.sdk.rapid.operations.RequestTestNotificationOperationParams
import com.expediagroup.sdk.rapid.operations.RequestUndeliveredNotificationsOperation
import com.expediagroup.sdk.rapid.operations.RequestUndeliveredNotificationsOperationParams
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture

/**
* EPS Rapid V3
*/

class RapidClient private constructor(clientConfiguration: RapidClientConfiguration) : BaseRapidClient("rapid", clientConfiguration) {
    @JvmField val helpers = RapidHelpers(this)

    class Builder : BaseRapidClient.Builder<Builder>() {
        override fun build() =
            RapidClient(
                RapidClientConfiguration(key, secret, endpoint, requestTimeout, connectionTimeout, socketTimeout, maskedLoggingHeaders, maskedLoggingBodyFields, null)
            )
    }

    class BuilderWithHttpClient() : BaseRapidClient.BuilderWithHttpClient<BuilderWithHttpClient>() {
        override fun build(): RapidClient {
            if (okHttpClient == null) {
                throw ExpediaGroupConfigurationException(getMissingRequiredConfigurationMessage(ConfigurationName.OKHTTP_CLIENT))
            }

            return RapidClient(
                RapidClientConfiguration(key, secret, endpoint, null, null, null, maskedLoggingHeaders, maskedLoggingBodyFields, okHttpClient)
            )
        }
    }

    companion object {
        @JvmStatic fun builder() = Builder()

        @JvmStatic fun builderWithHttpClient() = BuilderWithHttpClient()
    }

    override suspend fun throwServiceException(
        response: HttpResponse,
        operationId: String
    ): Unit = throw ErrorObjectMapper.process(response, operationId)

    private suspend inline fun <reified RequestType> executeHttpRequest(operation: Operation<RequestType>): HttpResponse =
        httpClient.request {
            method = HttpMethod.parse(operation.method)
            url(operation.url)

            operation.params?.getHeaders()?.let {
                headers.appendAll(it)
            }

            operation.params?.getQueryParams()?.let {
                url.parameters.appendAll(it)
            }

            val extraHeaders =
                buildMap {
                    put(HeaderKey.TRANSACTION_ID, operation.transactionId.dequeue().toString())
                }

            appendHeaders(extraHeaders)
            contentType(ContentType.Application.Json)
            setBody(operation.requestBody)
        }

    private inline fun <reified RequestType> executeWithEmptyResponse(operation: Operation<RequestType>): EmptyResponse {
        try {
            return executeAsyncWithEmptyResponse(operation).get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    private inline fun <reified RequestType> executeAsyncWithEmptyResponse(operation: Operation<RequestType>): CompletableFuture<EmptyResponse> =
        GlobalScope.future(Dispatchers.IO) {
            try {
                val response = executeHttpRequest(operation)
                throwIfError(response, operation.operationId)
                EmptyResponse(response.status.value, response.headers.entries())
            } catch (exception: Exception) {
                exception.handle()
            }
        }

    private inline fun <reified RequestType, reified ResponseType> execute(operation: Operation<RequestType>): Response<ResponseType> {
        try {
            return executeAsync<RequestType, ResponseType>(operation).get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    private inline fun <reified RequestType, reified ResponseType> executeAsync(operation: Operation<RequestType>): CompletableFuture<Response<ResponseType>> =
        GlobalScope.future(Dispatchers.IO) {
            try {
                val response = executeHttpRequest(operation)
                throwIfError(response, operation.operationId)
                Response(response.status.value, response.body<ResponseType>(), response.headers.entries())
            } catch (exception: Exception) {
                exception.handle()
            }
        }

    /**
     * Change details of a room.
     * This link will be available in the retrieve response. Changes in smoking preference and special request will be passed along to the property and are not guaranteed.
     * @param operation [ChangeRoomDetailsOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    fun execute(operation: ChangeRoomDetailsOperation): EmptyResponse = executeWithEmptyResponse<ChangeRoomDetailsRequest>(operation)

    /**
     * Change details of a room.
     * This link will be available in the retrieve response. Changes in smoking preference and special request will be passed along to the property and are not guaranteed.
     * @param operation [ChangeRoomDetailsOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type Nothing
     */
    fun executeAsync(operation: ChangeRoomDetailsOperation): CompletableFuture<EmptyResponse> = executeAsyncWithEmptyResponse<ChangeRoomDetailsRequest>(operation)

    private suspend inline fun kchangeRoomDetailsWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        roomId: kotlin.String,
        token: kotlin.String,
        changeRoomDetailsRequest: ChangeRoomDetailsRequest,
        customerSessionId: kotlin.String? =
            null,
        test: ChangeRoomDetailsOperationParams.Test? =
            null
    ): Response<Nothing> {
        val params =
            ChangeRoomDetailsOperationParams(
                itineraryId = itineraryId,
                roomId = roomId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token
            )

        val operation =
            ChangeRoomDetailsOperation(
                params,
                changeRoomDetailsRequest
            )

        return execute(operation)
    }

    /**
     * Change details of a room.
     * This link will be available in the retrieve response. Changes in smoking preference and special request will be passed along to the property and are not guaranteed.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param roomId Room ID of a property.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param changeRoomDetailsRequest The request body is required, but only the fields that are being changed need to be passed in. Fields that are not being changed should not be included in the request body.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The change call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: ChangeRoomDetailsOperation)"))
    fun changeRoomDetails(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        roomId: kotlin.String,
        token: kotlin.String,
        changeRoomDetailsRequest: ChangeRoomDetailsRequest,
        customerSessionId: kotlin.String? =
            null,
        test: ChangeRoomDetailsOperationParams.Test? =
            null
    ): Nothing = changeRoomDetailsWithResponse(customerIp, itineraryId, roomId, token, changeRoomDetailsRequest, customerSessionId, test).data

    /**
     * Change details of a room.
     * This link will be available in the retrieve response. Changes in smoking preference and special request will be passed along to the property and are not guaranteed.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param roomId Room ID of a property.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param changeRoomDetailsRequest The request body is required, but only the fields that are being changed need to be passed in. Fields that are not being changed should not be included in the request body.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The change call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: ChangeRoomDetailsOperation)"))
    fun changeRoomDetailsWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        roomId: kotlin.String,
        token: kotlin.String,
        changeRoomDetailsRequest: ChangeRoomDetailsRequest,
        customerSessionId: kotlin.String? =
            null,
        test: ChangeRoomDetailsOperationParams.Test? =
            null
    ): Response<Nothing> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kchangeRoomDetailsWithResponse(customerIp, itineraryId, roomId, token, changeRoomDetailsRequest, customerSessionId, test)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Commit a change of itinerary that may require additional payment or refund.
     * This link will be available in the change response to confirm and complete the change transaction.  If additional charges are due, a payment must be submitted with this request. Note that Two-Factor  Authentication is not supported at this time.
     * @param operation [CommitChangeOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    fun execute(operation: CommitChangeOperation): EmptyResponse = executeWithEmptyResponse<CommitChangeRoomRequestBody>(operation)

    /**
     * Commit a change of itinerary that may require additional payment or refund.
     * This link will be available in the change response to confirm and complete the change transaction.  If additional charges are due, a payment must be submitted with this request. Note that Two-Factor  Authentication is not supported at this time.
     * @param operation [CommitChangeOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type Nothing
     */
    fun executeAsync(operation: CommitChangeOperation): CompletableFuture<EmptyResponse> = executeAsyncWithEmptyResponse<CommitChangeRoomRequestBody>(operation)

    private suspend inline fun kcommitChangeWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        roomId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: CommitChangeOperationParams.Test? =
            null,
        commitChangeRoomRequestBody: CommitChangeRoomRequestBody? =
            null
    ): Response<Nothing> {
        val params =
            CommitChangeOperationParams(
                itineraryId = itineraryId,
                roomId = roomId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token
            )

        val operation =
            CommitChangeOperation(
                params,
                commitChangeRoomRequestBody
            )

        return execute(operation)
    }

    /**
     * Commit a change of itinerary that may require additional payment or refund.
     * This link will be available in the change response to confirm and complete the change transaction.  If additional charges are due, a payment must be submitted with this request. Note that Two-Factor  Authentication is not supported at this time.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param roomId Room ID of a property.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The change call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error`  (optional)
     * @param commitChangeRoomRequestBody The request body is required if additional payment is necessary. The body can optionally contain the `change_reference_id`.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: CommitChangeOperation)"))
    fun commitChange(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        roomId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: CommitChangeOperationParams.Test? =
            null,
        commitChangeRoomRequestBody: CommitChangeRoomRequestBody? =
            null
    ): Nothing = commitChangeWithResponse(customerIp, itineraryId, roomId, token, customerSessionId, test, commitChangeRoomRequestBody).data

    /**
     * Commit a change of itinerary that may require additional payment or refund.
     * This link will be available in the change response to confirm and complete the change transaction.  If additional charges are due, a payment must be submitted with this request. Note that Two-Factor  Authentication is not supported at this time.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param roomId Room ID of a property.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The change call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error`  (optional)
     * @param commitChangeRoomRequestBody The request body is required if additional payment is necessary. The body can optionally contain the `change_reference_id`.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: CommitChangeOperation)"))
    fun commitChangeWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        roomId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: CommitChangeOperationParams.Test? =
            null,
        commitChangeRoomRequestBody: CommitChangeRoomRequestBody? =
            null
    ): Response<Nothing> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kcommitChangeWithResponse(customerIp, itineraryId, roomId, token, customerSessionId, test, commitChangeRoomRequestBody)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Cancel Held Booking
     * This link will be available in a held booking response.
     * @param operation [DeleteHeldBookingOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    fun execute(operation: DeleteHeldBookingOperation): EmptyResponse = executeWithEmptyResponse<Nothing>(operation)

    /**
     * Cancel Held Booking
     * This link will be available in a held booking response.
     * @param operation [DeleteHeldBookingOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type Nothing
     */
    fun executeAsync(operation: DeleteHeldBookingOperation): CompletableFuture<EmptyResponse> = executeAsyncWithEmptyResponse<Nothing>(operation)

    private suspend inline fun kdeleteHeldBookingWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: DeleteHeldBookingOperationParams.Test? =
            null
    ): Response<Nothing> {
        val params =
            DeleteHeldBookingOperationParams(
                itineraryId = itineraryId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token
            )

        val operation =
            DeleteHeldBookingOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Cancel Held Booking
     * This link will be available in a held booking response.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The cancel call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test held booking. * `service_unavailable` * `internal_server_error` * `post_stay_cancel`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: DeleteHeldBookingOperation)"))
    fun deleteHeldBooking(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: DeleteHeldBookingOperationParams.Test? =
            null
    ): Nothing = deleteHeldBookingWithResponse(customerIp, itineraryId, token, customerSessionId, test).data

    /**
     * Cancel Held Booking
     * This link will be available in a held booking response.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The cancel call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test held booking. * `service_unavailable` * `internal_server_error` * `post_stay_cancel`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: DeleteHeldBookingOperation)"))
    fun deleteHeldBookingWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: DeleteHeldBookingOperationParams.Test? =
            null
    ): Response<Nothing> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kdeleteHeldBookingWithResponse(customerIp, itineraryId, token, customerSessionId, test)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Cancel a room.
     * This link will be available in the retrieve response.
     * @param operation [DeleteRoomOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    fun execute(operation: DeleteRoomOperation): EmptyResponse = executeWithEmptyResponse<Nothing>(operation)

    /**
     * Cancel a room.
     * This link will be available in the retrieve response.
     * @param operation [DeleteRoomOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type Nothing
     */
    fun executeAsync(operation: DeleteRoomOperation): CompletableFuture<EmptyResponse> = executeAsyncWithEmptyResponse<Nothing>(operation)

    private suspend inline fun kdeleteRoomWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        roomId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: DeleteRoomOperationParams.Test? =
            null
    ): Response<Nothing> {
        val params =
            DeleteRoomOperationParams(
                itineraryId = itineraryId,
                roomId = roomId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token
            )

        val operation =
            DeleteRoomOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Cancel a room.
     * This link will be available in the retrieve response.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param roomId Room ID of a property.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The cancel call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error` * `post_stay_cancel`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: DeleteRoomOperation)"))
    fun deleteRoom(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        roomId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: DeleteRoomOperationParams.Test? =
            null
    ): Nothing = deleteRoomWithResponse(customerIp, itineraryId, roomId, token, customerSessionId, test).data

    /**
     * Cancel a room.
     * This link will be available in the retrieve response.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param roomId Room ID of a property.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The cancel call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error` * `post_stay_cancel`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: DeleteRoomOperation)"))
    fun deleteRoomWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        roomId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: DeleteRoomOperationParams.Test? =
            null
    ): Response<Nothing> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kdeleteRoomWithResponse(customerIp, itineraryId, roomId, token, customerSessionId, test)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Get additional property room rates and availability
     * Returns additional rates on available room types, using the parameters of the previous call.  The response includes rate details such as promos, whether the rate is refundable, cancellation penalties and a full price breakdown to meet the price display requirements for your market. _Note_: If there are no available rooms, the response will be an empty array. * The `nightly` array includes each individual night's charges. When the total price includes fees, charges, or adjustments that are not divided by night, these amounts will be included in the `stay` rate array, which details charges applied to the entire stay (each check-in).
     * @param operation [GetAdditionalAvailabilityOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<PropertyAvailability>
     */
    fun execute(operation: GetAdditionalAvailabilityOperation): Response<kotlin.collections.List<PropertyAvailability>> = execute<Nothing, kotlin.collections.List<PropertyAvailability>>(operation)

    /**
     * Get additional property room rates and availability
     * Returns additional rates on available room types, using the parameters of the previous call.  The response includes rate details such as promos, whether the rate is refundable, cancellation penalties and a full price breakdown to meet the price display requirements for your market. _Note_: If there are no available rooms, the response will be an empty array. * The `nightly` array includes each individual night's charges. When the total price includes fees, charges, or adjustments that are not divided by night, these amounts will be included in the `stay` rate array, which details charges applied to the entire stay (each check-in).
     * @param operation [GetAdditionalAvailabilityOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.List<PropertyAvailability>
     */
    fun executeAsync(operation: GetAdditionalAvailabilityOperation): CompletableFuture<Response<kotlin.collections.List<PropertyAvailability>>> =
        executeAsync<Nothing, kotlin.collections.List<PropertyAvailability>>(operation)

    private suspend inline fun kgetAdditionalAvailabilityWithResponse(
        propertyId: kotlin.String,
        token: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null,
        test: GetAdditionalAvailabilityOperationParams.Test? =
            null,
        checkin: kotlin.String? =
            null,
        checkout: kotlin.String? =
            null,
        exclusion: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Exclusion
        >? =
            null,
        filter: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Filter
        >? =
            null,
        include: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Include
        >? =
            null,
        occupancy: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        rateOption: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.RateOption
        >? =
            null,
        salesChannel: kotlin.String? =
            null,
        currency: kotlin.String? =
            null
    ): Response<kotlin.collections.List<PropertyAvailability>> {
        val params =
            GetAdditionalAvailabilityOperationParams(
                propertyId = propertyId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token,
                checkin = checkin,
                checkout = checkout,
                exclusion = exclusion,
                filter = filter,
                include = include,
                occupancy = occupancy,
                rateOption = rateOption,
                salesChannel = salesChannel,
                currency = currency
            )

        val operation =
            GetAdditionalAvailabilityOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Get additional property room rates and availability
     * Returns additional rates on available room types, using the parameters of the previous call.  The response includes rate details such as promos, whether the rate is refundable, cancellation penalties and a full price breakdown to meet the price display requirements for your market. _Note_: If there are no available rooms, the response will be an empty array. * The `nightly` array includes each individual night's charges. When the total price includes fees, charges, or adjustments that are not divided by night, these amounts will be included in the `stay` rate array, which details charges applied to the entire stay (each check-in).
     * @param propertyId Expedia Property ID.<br>
     * @param token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the `additional_rates` link from the shop response, or the `shop` link on a `sold_out` price check response. It is also provided from the `shop_for_change` link on an itinerary retrieve.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.  (optional)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error` * `no_availability` * `forbidden`  (optional)
     * @param checkin Check-in date, in ISO 8601 format (YYYY-MM-DD). This can be up to 365 days in the future. Some partner configurations may extend this up to 500 days.<br> Note: Only needed for hard change if desired check-in date is different than original booking. If specified must also specify `checkout`.  (optional)
     * @param checkout Check-out date, in ISO 8601 format (YYYY-MM-DD). Total length of stay cannot exceed 28 nights or 365 nights depending on Vacation Rental configurations.<br> Note: Only needed for hard change if desired check-out date is different than original booking. If specified must also specify `checkin`.<br>  (optional)
     * @param exclusion Single exclusion type. Send multiple instances of this parameter to request multiple exclusions.<br> Note: Optional parameter for use with hard change requests. <br> * `refundable_damage_deposit` - Excludes Rapid supplied Vrbo rates with refundable damage deposits from the response. * `card_on_file` - Excludes Rapid supplied Vrbo rates with card-on-file damage collection from the response.  (optional)
     * @param filter Single filter type. Send multiple instances of this parameter to request multiple filters.<br> Note: Optional parameter for use with hard change requests.<br> This parameter cannot be set to `property_collect` if the existing booking is `expedia_collect` and vice versa.<br> * `refundable` - Filters results to only show fully refundable rates. * `expedia_collect` - Filters results to only show rates where payment is collected by Expedia at the time of booking. These properties can be eligible for payments via Expedia Affiliate Collect(EAC). * `property_collect` - Filters results to only show rates where payment is collected by the property after booking. This can include rates that require a deposit by the property, dependent upon the deposit policies. * `loyalty` - Filters results to only show rates that are eligible for loyalty points.  (optional)
     * @param include Modify the response by including types of responses that are not provided by default.<br> * `sale_scenario.mobile_promotion` - Enable the `mobile_promotion` flag under the `sale_scenario` section of the response.  (optional)
     * @param occupancy Defines the requested occupancy for a single room. Each room must have at least 1 adult occupant.<br> Format: `numberOfAdults[-firstChildAge[,nextChildAge]]`<br> To request multiple rooms (of the same type), include one instance of occupancy for each room requested. Up to 8 rooms may be requested or booked at once.<br> Note: Only needed for hard change if desired occupancy is different than original booking.<br> Examples: * 2 adults, one 9-year-old and one 4-year-old would be represented by `occupancy=2-9,4`.<br> * A multi-room request to lodge an additional 2 adults would be represented by `occupancy=2-9,4&occupancy=2`  (optional)
     * @param rateOption Request specific rate options for each property. Send multiple instances of this parameter to request multiple rate options. Note: Optional parameter for use with hard change requests.<br> Accepted values:<br> * `member` - Return member rates for each property. This feature must be enabled and requires a user to be logged in to request these rates. * `net_rates` - Return net rates for each property. This feature must be enabled to request these rates. * `cross_sell` - Identify if the traffic is coming from a cross sell booking. Where the traveler has booked another service (flight, car, activities...) before hotel.  (optional)
     * @param salesChannel Provide the sales channel if you wish to override the sales_channel provided in the previous call. EPS dynamically provides the best content for optimal conversion on each sales channel.<br> Note: Must specify this value for hard change requests.<br> * `website` - Standard website accessed from the customer's computer * `agent_tool` - Your own agent tool used by your call center or retail store agent * `mobile_app` - An application installed on a phone or tablet device * `mobile_web` - A web browser application on a phone or tablet device * `meta` - Rates will be passed to and displayed on a 3rd party comparison website * `cache` - Rates will be used to populate a local cache  (optional)
     * @param currency Determines the returned currency type throughout the response <br> Note: This parameter is only valid for hard change requests and is ignored in all other cases  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.List<PropertyAvailability>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetAdditionalAvailabilityOperation)"))
    fun getAdditionalAvailability(
        propertyId: kotlin.String,
        token: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null,
        test: GetAdditionalAvailabilityOperationParams.Test? =
            null,
        checkin: kotlin.String? =
            null,
        checkout: kotlin.String? =
            null,
        exclusion: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Exclusion
        >? =
            null,
        filter: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Filter
        >? =
            null,
        include: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Include
        >? =
            null,
        occupancy: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        rateOption: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.RateOption
        >? =
            null,
        salesChannel: kotlin.String? =
            null,
        currency: kotlin.String? =
            null
    ): kotlin.collections.List<PropertyAvailability> =
        getAdditionalAvailabilityWithResponse(
            propertyId,
            token,
            customerIp,
            customerSessionId,
            test,
            checkin,
            checkout,
            exclusion,
            filter,
            include,
            occupancy,
            rateOption,
            salesChannel,
            currency
        ).data

    /**
     * Get additional property room rates and availability
     * Returns additional rates on available room types, using the parameters of the previous call.  The response includes rate details such as promos, whether the rate is refundable, cancellation penalties and a full price breakdown to meet the price display requirements for your market. _Note_: If there are no available rooms, the response will be an empty array. * The `nightly` array includes each individual night's charges. When the total price includes fees, charges, or adjustments that are not divided by night, these amounts will be included in the `stay` rate array, which details charges applied to the entire stay (each check-in).
     * @param propertyId Expedia Property ID.<br>
     * @param token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the `additional_rates` link from the shop response, or the `shop` link on a `sold_out` price check response. It is also provided from the `shop_for_change` link on an itinerary retrieve.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.  (optional)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error` * `no_availability` * `forbidden`  (optional)
     * @param checkin Check-in date, in ISO 8601 format (YYYY-MM-DD). This can be up to 365 days in the future. Some partner configurations may extend this up to 500 days.<br> Note: Only needed for hard change if desired check-in date is different than original booking. If specified must also specify `checkout`.  (optional)
     * @param checkout Check-out date, in ISO 8601 format (YYYY-MM-DD). Total length of stay cannot exceed 28 nights or 365 nights depending on Vacation Rental configurations.<br> Note: Only needed for hard change if desired check-out date is different than original booking. If specified must also specify `checkin`.<br>  (optional)
     * @param exclusion Single exclusion type. Send multiple instances of this parameter to request multiple exclusions.<br> Note: Optional parameter for use with hard change requests. <br> * `refundable_damage_deposit` - Excludes Rapid supplied Vrbo rates with refundable damage deposits from the response. * `card_on_file` - Excludes Rapid supplied Vrbo rates with card-on-file damage collection from the response.  (optional)
     * @param filter Single filter type. Send multiple instances of this parameter to request multiple filters.<br> Note: Optional parameter for use with hard change requests.<br> This parameter cannot be set to `property_collect` if the existing booking is `expedia_collect` and vice versa.<br> * `refundable` - Filters results to only show fully refundable rates. * `expedia_collect` - Filters results to only show rates where payment is collected by Expedia at the time of booking. These properties can be eligible for payments via Expedia Affiliate Collect(EAC). * `property_collect` - Filters results to only show rates where payment is collected by the property after booking. This can include rates that require a deposit by the property, dependent upon the deposit policies. * `loyalty` - Filters results to only show rates that are eligible for loyalty points.  (optional)
     * @param include Modify the response by including types of responses that are not provided by default.<br> * `sale_scenario.mobile_promotion` - Enable the `mobile_promotion` flag under the `sale_scenario` section of the response.  (optional)
     * @param occupancy Defines the requested occupancy for a single room. Each room must have at least 1 adult occupant.<br> Format: `numberOfAdults[-firstChildAge[,nextChildAge]]`<br> To request multiple rooms (of the same type), include one instance of occupancy for each room requested. Up to 8 rooms may be requested or booked at once.<br> Note: Only needed for hard change if desired occupancy is different than original booking.<br> Examples: * 2 adults, one 9-year-old and one 4-year-old would be represented by `occupancy=2-9,4`.<br> * A multi-room request to lodge an additional 2 adults would be represented by `occupancy=2-9,4&occupancy=2`  (optional)
     * @param rateOption Request specific rate options for each property. Send multiple instances of this parameter to request multiple rate options. Note: Optional parameter for use with hard change requests.<br> Accepted values:<br> * `member` - Return member rates for each property. This feature must be enabled and requires a user to be logged in to request these rates. * `net_rates` - Return net rates for each property. This feature must be enabled to request these rates. * `cross_sell` - Identify if the traffic is coming from a cross sell booking. Where the traveler has booked another service (flight, car, activities...) before hotel.  (optional)
     * @param salesChannel Provide the sales channel if you wish to override the sales_channel provided in the previous call. EPS dynamically provides the best content for optimal conversion on each sales channel.<br> Note: Must specify this value for hard change requests.<br> * `website` - Standard website accessed from the customer's computer * `agent_tool` - Your own agent tool used by your call center or retail store agent * `mobile_app` - An application installed on a phone or tablet device * `mobile_web` - A web browser application on a phone or tablet device * `meta` - Rates will be passed to and displayed on a 3rd party comparison website * `cache` - Rates will be used to populate a local cache  (optional)
     * @param currency Determines the returned currency type throughout the response <br> Note: This parameter is only valid for hard change requests and is ignored in all other cases  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<PropertyAvailability>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetAdditionalAvailabilityOperation)"))
    fun getAdditionalAvailabilityWithResponse(
        propertyId: kotlin.String,
        token: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null,
        test: GetAdditionalAvailabilityOperationParams.Test? =
            null,
        checkin: kotlin.String? =
            null,
        checkout: kotlin.String? =
            null,
        exclusion: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Exclusion
        >? =
            null,
        filter: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Filter
        >? =
            null,
        include: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Include
        >? =
            null,
        occupancy: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        rateOption: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.RateOption
        >? =
            null,
        salesChannel: kotlin.String? =
            null,
        currency: kotlin.String? =
            null
    ): Response<kotlin.collections.List<PropertyAvailability>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetAdditionalAvailabilityWithResponse(
                    propertyId,
                    token,
                    customerIp,
                    customerSessionId,
                    test,
                    checkin,
                    checkout,
                    exclusion,
                    filter,
                    include,
                    occupancy,
                    rateOption,
                    salesChannel,
                    currency
                )
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Amenities Reference
     * Returns a complete collection of amenities available in the Rapid API.
     * @param operation [GetAmenitiesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    fun execute(operation: GetAmenitiesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, AmenityReference>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, AmenityReference>>(operation)

    /**
     * Amenities Reference
     * Returns a complete collection of amenities available in the Rapid API.
     * @param operation [GetAmenitiesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    fun executeAsync(operation: GetAmenitiesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, AmenityReference>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, AmenityReference>>(operation)

    private suspend inline fun kgetAmenitiesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, AmenityReference>> {
        val params =
            GetAmenitiesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetAmenitiesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Amenities Reference
     * Returns a complete collection of amenities available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetAmenitiesReferenceOperation)"))
    fun getAmenitiesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, AmenityReference> = getAmenitiesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Amenities Reference
     * Returns a complete collection of amenities available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetAmenitiesReferenceOperation)"))
    fun getAmenitiesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, AmenityReference>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetAmenitiesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Get property room rates and availability
     * Returns rates on available room types for specified properties (maximum of 250 properties per request).  The response includes rate details such as promos, whether the rate is refundable, cancellation penalties and a full price breakdown to meet the price display requirements for your market. _Note_: If there are no available rooms, the response will be an empty array. * Multiple rooms of the same type may be requested by including multiple instances of the `occupancy` parameter. * The `nightly` array includes each individual night's charges. When the total price includes fees, charges, or adjustments that are not divided by night, these amounts will be included in the `stay` rate array, which details charges applied to the entire stay (each check-in).
     * @param operation [GetAvailabilityOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<Property>
     */
    fun execute(operation: GetAvailabilityOperation): Response<kotlin.collections.List<Property>> = execute<Nothing, kotlin.collections.List<Property>>(operation)

    /**
     * Get property room rates and availability
     * Returns rates on available room types for specified properties (maximum of 250 properties per request).  The response includes rate details such as promos, whether the rate is refundable, cancellation penalties and a full price breakdown to meet the price display requirements for your market. _Note_: If there are no available rooms, the response will be an empty array. * Multiple rooms of the same type may be requested by including multiple instances of the `occupancy` parameter. * The `nightly` array includes each individual night's charges. When the total price includes fees, charges, or adjustments that are not divided by night, these amounts will be included in the `stay` rate array, which details charges applied to the entire stay (each check-in).
     * @param operation [GetAvailabilityOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.List<Property>
     */
    fun executeAsync(operation: GetAvailabilityOperation): CompletableFuture<Response<kotlin.collections.List<Property>>> = executeAsync<Nothing, kotlin.collections.List<Property>>(operation)

    private suspend inline fun kgetAvailabilityWithResponse(
        checkin: kotlin.String,
        checkout: kotlin.String,
        currency: kotlin.String,
        countryCode: kotlin.String,
        language: kotlin.String,
        occupancy: kotlin.collections.List<
            kotlin.String
        >,
        propertyId: kotlin.collections.List<
            kotlin.String
        >,
        ratePlanCount: java.math.BigDecimal,
        salesChannel: kotlin.String,
        salesEnvironment: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null,
        test: GetAvailabilityOperationParams.Test? =
            null,
        amenityCategory: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        exclusion: kotlin.collections.List<
            GetAvailabilityOperationParams.Exclusion
        >? =
            null,
        filter: kotlin.collections.List<
            GetAvailabilityOperationParams.Filter
        >? =
            null,
        include: kotlin.collections.List<
            GetAvailabilityOperationParams.Include
        >? =
            null,
        rateOption: kotlin.collections.List<
            GetAvailabilityOperationParams.RateOption
        >? =
            null,
        travelPurpose: GetAvailabilityOperationParams.TravelPurpose? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.List<Property>> {
        val params =
            GetAvailabilityOperationParams(
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                checkin = checkin,
                checkout = checkout,
                currency = currency,
                countryCode = countryCode,
                language = language,
                occupancy = occupancy,
                propertyId = propertyId,
                ratePlanCount = ratePlanCount,
                salesChannel = salesChannel,
                salesEnvironment = salesEnvironment,
                amenityCategory = amenityCategory,
                exclusion = exclusion,
                filter = filter,
                include = include,
                rateOption = rateOption,
                travelPurpose = travelPurpose,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetAvailabilityOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Get property room rates and availability
     * Returns rates on available room types for specified properties (maximum of 250 properties per request).  The response includes rate details such as promos, whether the rate is refundable, cancellation penalties and a full price breakdown to meet the price display requirements for your market. _Note_: If there are no available rooms, the response will be an empty array. * Multiple rooms of the same type may be requested by including multiple instances of the `occupancy` parameter. * The `nightly` array includes each individual night's charges. When the total price includes fees, charges, or adjustments that are not divided by night, these amounts will be included in the `stay` rate array, which details charges applied to the entire stay (each check-in).
     * @param checkin Check-in date, in ISO 8601 format (YYYY-MM-DD). This can be up to 365 days in the future. Some partner configurations may extend this up to 500 days.
     * @param checkout Check-out date, in ISO 8601 format (YYYY-MM-DD). Total length of stay cannot exceed 28 nights or 365 nights depending on Vacation Rental configurations.
     * @param currency Requested currency for the rates, in ISO 4217 format<br><br> Currency Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/currency-options](https://developers.expediagroup.com/docs/rapid/resources/reference/currency-options)
     * @param countryCode The country code of the traveler's point of sale, in ISO 3166-1 alpha-2 format. This should represent the country where the shopping transaction is taking place.<br> For more information see: [https://www.iso.org/obp/ui/#search/code/](https://www.iso.org/obp/ui/#search/code/)
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. See [https://www.w3.org/International/articles/language-tags/](https://www.w3.org/International/articles/language-tags/)<br> Language Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/language-options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param occupancy Defines the requested occupancy for a single room. Each room must have at least 1 adult occupant.<br> Format: `numberOfAdults[-firstChildAge[,nextChildAge]]`<br> To request multiple rooms (of the same type), include one instance of occupancy for each room requested. Up to 8 rooms may be requested or booked at once.<br> Examples: * 2 adults, one 9-year-old and one 4-year-old would be represented by `occupancy=2-9,4`.<br> * A multi-room request to lodge an additional 2 adults would be represented by `occupancy=2-9,4&occupancy=2`
     * @param propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
     * @param ratePlanCount The number of rates to return per property. The rates with the best value will be returned, e.g. a rate_plan_count=4 will return the best 4 rates, but the rates are not ordered from lowest to highest or vice versa in the response. Generally lowest rates will be prioritized.<br><br> The value must be between 1 and 250.
     * @param salesChannel You must provide the sales channel for the display of rates. EPS dynamically provides the best content for optimal conversion on each sales channel. If you have a sales channel that is not currently supported in this list, please contact our support team.<br> * `website` - Standard website accessed from the customer's computer * `agent_tool` - Your own agent tool used by your call center or retail store agent * `mobile_app` - An application installed on a phone or tablet device * `mobile_web` - A web browser application on a phone or tablet device * `meta` - Rates will be passed to and displayed on a 3rd party comparison website * `cache` - Rates will be used to populate a local cache
     * @param salesEnvironment You must provide the sales environment in which rates will be sold. EPS dynamically provides the best content for optimal conversion. If you have a sales environment that is not currently supported in this list, please contact our support team.<br> * `hotel_package` - Use when selling the hotel with a transport product, e.g. flight & hotel. * `hotel_only` - Use when selling the hotel as an individual product. * `loyalty` - Use when you are selling the hotel as part of a loyalty program and the price is converted to points.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.  (optional)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error`  (optional)
     * @param amenityCategory Single amenity category. Send multiple instances of this parameter to request rates that match multiple amenity categories.<br> See the Amenity Categories section of the [Content Reference Lists](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for a list of values.  (optional)
     * @param exclusion Single exclusion type. Send multiple instances of this parameter to request multiple exclusions.<br> * `refundable_damage_deposit` - Excludes Rapid supplied Vrbo rates with refundable damage deposits from the response. * `card_on_file` - Excludes Rapid supplied Vrbo rates with card-on-file damage collection from the response.  (optional)
     * @param filter Single filter type. Send multiple instances of this parameter to request multiple filters.<br> * `refundable` - Filters results to only show fully refundable rates. * `expedia_collect` - Filters results to only show rates where payment is collected by Expedia at the time of booking. These properties can be eligible for payments via Expedia Affiliate Collect(EAC). * `property_collect` - Filters results to only show rates where payment is collected by the property after booking. This can include rates that require a deposit by the property, dependent upon the deposit policies. * `loyalty` - Filters results to only show rates that are eligible for loyalty points.  (optional)
     * @param include Modify the response by including types of responses that are not provided by default.<br> * `unavailable_reason` - When a property is unavailable for an actionable reason, return a response with that reason - See [Unavailable Reason Codes](https://developers.expediagroup.com/docs/rapid/resources/reference/unavailable-reason-codes) for possible values. * `sale_scenario.mobile_promotion` - Enable the `mobile_promotion` flag under the `room.rate.sale_scenario` section of the response. * `rooms.rates.marketing_fee_incentives` - When a rate has a marketing fee incentive applied, the response will include the `marketing_fee_incentives` array if this flag is provided in the request. * `rooms.rates.current_refundability` - Displays the current `refundability` of a rate.  (optional)
     * @param rateOption Request specific rate options for each property. Send multiple instances of this parameter to request multiple rate options. Accepted values:<br> * `member` - Return member rates for each property. This feature must be enabled and requires a user to be logged in to request these rates. * `net_rates` - Return net rates for each property. This feature must be enabled to request these rates. * `cross_sell` - Identify if the traffic is coming from a cross sell booking. Where the traveler has booked another service (flight, car, activities...) before hotel.  (optional)
     * @param travelPurpose This parameter is to specify the travel purpose of the booking. This may impact available rate plans, pricing, or tax calculations. * `leisure` * `business`  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.List<Property>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetAvailabilityOperation)"))
    fun getAvailability(
        checkin: kotlin.String,
        checkout: kotlin.String,
        currency: kotlin.String,
        countryCode: kotlin.String,
        language: kotlin.String,
        occupancy: kotlin.collections.List<
            kotlin.String
        >,
        propertyId: kotlin.collections.List<
            kotlin.String
        >,
        ratePlanCount: java.math.BigDecimal,
        salesChannel: kotlin.String,
        salesEnvironment: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null,
        test: GetAvailabilityOperationParams.Test? =
            null,
        amenityCategory: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        exclusion: kotlin.collections.List<
            GetAvailabilityOperationParams.Exclusion
        >? =
            null,
        filter: kotlin.collections.List<
            GetAvailabilityOperationParams.Filter
        >? =
            null,
        include: kotlin.collections.List<
            GetAvailabilityOperationParams.Include
        >? =
            null,
        rateOption: kotlin.collections.List<
            GetAvailabilityOperationParams.RateOption
        >? =
            null,
        travelPurpose: GetAvailabilityOperationParams.TravelPurpose? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.List<Property> =
        getAvailabilityWithResponse(
            checkin,
            checkout,
            currency,
            countryCode,
            language,
            occupancy,
            propertyId,
            ratePlanCount,
            salesChannel,
            salesEnvironment,
            customerIp,
            customerSessionId,
            test,
            amenityCategory,
            exclusion,
            filter,
            include,
            rateOption,
            travelPurpose,
            billingTerms,
            partnerPointOfSale,
            paymentTerms,
            platformName
        ).data

    /**
     * Get property room rates and availability
     * Returns rates on available room types for specified properties (maximum of 250 properties per request).  The response includes rate details such as promos, whether the rate is refundable, cancellation penalties and a full price breakdown to meet the price display requirements for your market. _Note_: If there are no available rooms, the response will be an empty array. * Multiple rooms of the same type may be requested by including multiple instances of the `occupancy` parameter. * The `nightly` array includes each individual night's charges. When the total price includes fees, charges, or adjustments that are not divided by night, these amounts will be included in the `stay` rate array, which details charges applied to the entire stay (each check-in).
     * @param checkin Check-in date, in ISO 8601 format (YYYY-MM-DD). This can be up to 365 days in the future. Some partner configurations may extend this up to 500 days.
     * @param checkout Check-out date, in ISO 8601 format (YYYY-MM-DD). Total length of stay cannot exceed 28 nights or 365 nights depending on Vacation Rental configurations.
     * @param currency Requested currency for the rates, in ISO 4217 format<br><br> Currency Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/currency-options](https://developers.expediagroup.com/docs/rapid/resources/reference/currency-options)
     * @param countryCode The country code of the traveler's point of sale, in ISO 3166-1 alpha-2 format. This should represent the country where the shopping transaction is taking place.<br> For more information see: [https://www.iso.org/obp/ui/#search/code/](https://www.iso.org/obp/ui/#search/code/)
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. See [https://www.w3.org/International/articles/language-tags/](https://www.w3.org/International/articles/language-tags/)<br> Language Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/language-options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param occupancy Defines the requested occupancy for a single room. Each room must have at least 1 adult occupant.<br> Format: `numberOfAdults[-firstChildAge[,nextChildAge]]`<br> To request multiple rooms (of the same type), include one instance of occupancy for each room requested. Up to 8 rooms may be requested or booked at once.<br> Examples: * 2 adults, one 9-year-old and one 4-year-old would be represented by `occupancy=2-9,4`.<br> * A multi-room request to lodge an additional 2 adults would be represented by `occupancy=2-9,4&occupancy=2`
     * @param propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
     * @param ratePlanCount The number of rates to return per property. The rates with the best value will be returned, e.g. a rate_plan_count=4 will return the best 4 rates, but the rates are not ordered from lowest to highest or vice versa in the response. Generally lowest rates will be prioritized.<br><br> The value must be between 1 and 250.
     * @param salesChannel You must provide the sales channel for the display of rates. EPS dynamically provides the best content for optimal conversion on each sales channel. If you have a sales channel that is not currently supported in this list, please contact our support team.<br> * `website` - Standard website accessed from the customer's computer * `agent_tool` - Your own agent tool used by your call center or retail store agent * `mobile_app` - An application installed on a phone or tablet device * `mobile_web` - A web browser application on a phone or tablet device * `meta` - Rates will be passed to and displayed on a 3rd party comparison website * `cache` - Rates will be used to populate a local cache
     * @param salesEnvironment You must provide the sales environment in which rates will be sold. EPS dynamically provides the best content for optimal conversion. If you have a sales environment that is not currently supported in this list, please contact our support team.<br> * `hotel_package` - Use when selling the hotel with a transport product, e.g. flight & hotel. * `hotel_only` - Use when selling the hotel as an individual product. * `loyalty` - Use when you are selling the hotel as part of a loyalty program and the price is converted to points.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.  (optional)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error`  (optional)
     * @param amenityCategory Single amenity category. Send multiple instances of this parameter to request rates that match multiple amenity categories.<br> See the Amenity Categories section of the [Content Reference Lists](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for a list of values.  (optional)
     * @param exclusion Single exclusion type. Send multiple instances of this parameter to request multiple exclusions.<br> * `refundable_damage_deposit` - Excludes Rapid supplied Vrbo rates with refundable damage deposits from the response. * `card_on_file` - Excludes Rapid supplied Vrbo rates with card-on-file damage collection from the response.  (optional)
     * @param filter Single filter type. Send multiple instances of this parameter to request multiple filters.<br> * `refundable` - Filters results to only show fully refundable rates. * `expedia_collect` - Filters results to only show rates where payment is collected by Expedia at the time of booking. These properties can be eligible for payments via Expedia Affiliate Collect(EAC). * `property_collect` - Filters results to only show rates where payment is collected by the property after booking. This can include rates that require a deposit by the property, dependent upon the deposit policies. * `loyalty` - Filters results to only show rates that are eligible for loyalty points.  (optional)
     * @param include Modify the response by including types of responses that are not provided by default.<br> * `unavailable_reason` - When a property is unavailable for an actionable reason, return a response with that reason - See [Unavailable Reason Codes](https://developers.expediagroup.com/docs/rapid/resources/reference/unavailable-reason-codes) for possible values. * `sale_scenario.mobile_promotion` - Enable the `mobile_promotion` flag under the `room.rate.sale_scenario` section of the response. * `rooms.rates.marketing_fee_incentives` - When a rate has a marketing fee incentive applied, the response will include the `marketing_fee_incentives` array if this flag is provided in the request. * `rooms.rates.current_refundability` - Displays the current `refundability` of a rate.  (optional)
     * @param rateOption Request specific rate options for each property. Send multiple instances of this parameter to request multiple rate options. Accepted values:<br> * `member` - Return member rates for each property. This feature must be enabled and requires a user to be logged in to request these rates. * `net_rates` - Return net rates for each property. This feature must be enabled to request these rates. * `cross_sell` - Identify if the traffic is coming from a cross sell booking. Where the traveler has booked another service (flight, car, activities...) before hotel.  (optional)
     * @param travelPurpose This parameter is to specify the travel purpose of the booking. This may impact available rate plans, pricing, or tax calculations. * `leisure` * `business`  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<Property>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetAvailabilityOperation)"))
    fun getAvailabilityWithResponse(
        checkin: kotlin.String,
        checkout: kotlin.String,
        currency: kotlin.String,
        countryCode: kotlin.String,
        language: kotlin.String,
        occupancy: kotlin.collections.List<
            kotlin.String
        >,
        propertyId: kotlin.collections.List<
            kotlin.String
        >,
        ratePlanCount: java.math.BigDecimal,
        salesChannel: kotlin.String,
        salesEnvironment: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null,
        test: GetAvailabilityOperationParams.Test? =
            null,
        amenityCategory: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        exclusion: kotlin.collections.List<
            GetAvailabilityOperationParams.Exclusion
        >? =
            null,
        filter: kotlin.collections.List<
            GetAvailabilityOperationParams.Filter
        >? =
            null,
        include: kotlin.collections.List<
            GetAvailabilityOperationParams.Include
        >? =
            null,
        rateOption: kotlin.collections.List<
            GetAvailabilityOperationParams.RateOption
        >? =
            null,
        travelPurpose: GetAvailabilityOperationParams.TravelPurpose? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.List<Property>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetAvailabilityWithResponse(
                    checkin,
                    checkout,
                    currency,
                    countryCode,
                    language,
                    occupancy,
                    propertyId,
                    ratePlanCount,
                    salesChannel,
                    salesEnvironment,
                    customerIp,
                    customerSessionId,
                    test,
                    amenityCategory,
                    exclusion,
                    filter,
                    include,
                    rateOption,
                    travelPurpose,
                    billingTerms,
                    partnerPointOfSale,
                    paymentTerms,
                    platformName
                )
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Booking Receipt
     * A link to the booking receipt will be provided in the retrieve response if partner configuration enabled. This endpoint will provide a PDF representation of a booking receipt that can be presented to a traveler for expense tracking purposes.
     * @param operation [GetBookingReceiptOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type java.io.InputStream
     */
    fun execute(operation: GetBookingReceiptOperation): Response<java.io.InputStream> = execute<Nothing, java.io.InputStream>(operation)

    /**
     * Booking Receipt
     * A link to the booking receipt will be provided in the retrieve response if partner configuration enabled. This endpoint will provide a PDF representation of a booking receipt that can be presented to a traveler for expense tracking purposes.
     * @param operation [GetBookingReceiptOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type java.io.InputStream
     */
    fun executeAsync(operation: GetBookingReceiptOperation): CompletableFuture<Response<java.io.InputStream>> = executeAsync<Nothing, java.io.InputStream>(operation)

    private suspend inline fun kgetBookingReceiptWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: GetBookingReceiptOperationParams.Test? =
            null,
        branding: GetBookingReceiptOperationParams.Branding? =
            null
    ): Response<java.io.InputStream> {
        val params =
            GetBookingReceiptOperationParams(
                itineraryId = itineraryId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token,
                branding = branding
            )

        val operation =
            GetBookingReceiptOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Booking Receipt
     * A link to the booking receipt will be provided in the retrieve response if partner configuration enabled. This endpoint will provide a PDF representation of a booking receipt that can be presented to a traveler for expense tracking purposes.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This path variable will be provided as part of the link. This specifies which itinerary the booking receipt request pertains to.
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The booking receipt call has a test header that can be used to return set responses with the following keywords: * `standard` * `service_unavailable` * `internal_server_error`  (optional)
     * @param branding This parameter specifies which branding should be present on the generated PDF. Default behavior will be to provide the booking receipt with `expedia_group` branding. Some partner configurations may change the default to unbranded.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return java.io.InputStream
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetBookingReceiptOperation)"))
    fun getBookingReceipt(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: GetBookingReceiptOperationParams.Test? =
            null,
        branding: GetBookingReceiptOperationParams.Branding? =
            null
    ): java.io.InputStream = getBookingReceiptWithResponse(customerIp, itineraryId, token, customerSessionId, test, branding).data

    /**
     * Booking Receipt
     * A link to the booking receipt will be provided in the retrieve response if partner configuration enabled. This endpoint will provide a PDF representation of a booking receipt that can be presented to a traveler for expense tracking purposes.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This path variable will be provided as part of the link. This specifies which itinerary the booking receipt request pertains to.
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The booking receipt call has a test header that can be used to return set responses with the following keywords: * `standard` * `service_unavailable` * `internal_server_error`  (optional)
     * @param branding This parameter specifies which branding should be present on the generated PDF. Default behavior will be to provide the booking receipt with `expedia_group` branding. Some partner configurations may change the default to unbranded.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type java.io.InputStream
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetBookingReceiptOperation)"))
    fun getBookingReceiptWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: GetBookingReceiptOperationParams.Test? =
            null,
        branding: GetBookingReceiptOperationParams.Branding? =
            null
    ): Response<java.io.InputStream> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetBookingReceiptWithResponse(customerIp, itineraryId, token, customerSessionId, test, branding)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Get a calendar of availability dates for properties. This is currently a Vrbo property only feature.
     * Returns availability information for the specified properties (maximum of 25 properties per request).  The response includes per day information about the property's availability, information about if check-in or check-out is available for the day,   and information regarding the stay constraints.
     * @param operation [GetCalendarAvailabilityOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<PropertyCalendarAvailability>
     */
    fun execute(operation: GetCalendarAvailabilityOperation): Response<kotlin.collections.List<PropertyCalendarAvailability>> =
        execute<Nothing, kotlin.collections.List<PropertyCalendarAvailability>>(operation)

    /**
     * Get a calendar of availability dates for properties. This is currently a Vrbo property only feature.
     * Returns availability information for the specified properties (maximum of 25 properties per request).  The response includes per day information about the property's availability, information about if check-in or check-out is available for the day,   and information regarding the stay constraints.
     * @param operation [GetCalendarAvailabilityOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.List<PropertyCalendarAvailability>
     */
    fun executeAsync(operation: GetCalendarAvailabilityOperation): CompletableFuture<Response<kotlin.collections.List<PropertyCalendarAvailability>>> =
        executeAsync<Nothing, kotlin.collections.List<PropertyCalendarAvailability>>(operation)

    private suspend inline fun kgetCalendarAvailabilityWithResponse(
        propertyId: kotlin.collections.List<
            kotlin.String
        >,
        startDate: java.time.LocalDate,
        endDate: java.time.LocalDate,
        test: GetCalendarAvailabilityOperationParams.Test? =
            null
    ): Response<kotlin.collections.List<PropertyCalendarAvailability>> {
        val params =
            GetCalendarAvailabilityOperationParams(
                test = test,
                propertyId = propertyId,
                startDate = startDate,
                endDate = endDate
            )

        val operation =
            GetCalendarAvailabilityOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Get a calendar of availability dates for properties. This is currently a Vrbo property only feature.
     * Returns availability information for the specified properties (maximum of 25 properties per request).  The response includes per day information about the property's availability, information about if check-in or check-out is available for the day,   and information regarding the stay constraints.
     * @param propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
     * @param startDate The first day of availability information to be returned, in ISO 8601 format (YYYY-MM-DD), up to 500 days in the future from the current date.
     * @param endDate The last day of availability information to be returned, in ISO 8601 format (YYYY-MM-DD). This must be 365 days or less from the start_date.
     * @param test Shop calls have a test header that can be used to return set responses with the following keywords: * `standard` * `service_unavailable` * `unknown_internal_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.List<PropertyCalendarAvailability>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetCalendarAvailabilityOperation)"))
    fun getCalendarAvailability(
        propertyId: kotlin.collections.List<
            kotlin.String
        >,
        startDate: java.time.LocalDate,
        endDate: java.time.LocalDate,
        test: GetCalendarAvailabilityOperationParams.Test? =
            null
    ): kotlin.collections.List<PropertyCalendarAvailability> = getCalendarAvailabilityWithResponse(propertyId, startDate, endDate, test).data

    /**
     * Get a calendar of availability dates for properties. This is currently a Vrbo property only feature.
     * Returns availability information for the specified properties (maximum of 25 properties per request).  The response includes per day information about the property's availability, information about if check-in or check-out is available for the day,   and information regarding the stay constraints.
     * @param propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
     * @param startDate The first day of availability information to be returned, in ISO 8601 format (YYYY-MM-DD), up to 500 days in the future from the current date.
     * @param endDate The last day of availability information to be returned, in ISO 8601 format (YYYY-MM-DD). This must be 365 days or less from the start_date.
     * @param test Shop calls have a test header that can be used to return set responses with the following keywords: * `standard` * `service_unavailable` * `unknown_internal_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<PropertyCalendarAvailability>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetCalendarAvailabilityOperation)"))
    fun getCalendarAvailabilityWithResponse(
        propertyId: kotlin.collections.List<
            kotlin.String
        >,
        startDate: java.time.LocalDate,
        endDate: java.time.LocalDate,
        test: GetCalendarAvailabilityOperationParams.Test? =
            null
    ): Response<kotlin.collections.List<PropertyCalendarAvailability>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetCalendarAvailabilityWithResponse(propertyId, startDate, endDate, test)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Categories Reference
     * Returns a complete collection of categories available in the Rapid API.
     * @param operation [GetCategoriesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, CategoryProperty>
     */
    fun execute(operation: GetCategoriesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, CategoryProperty>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, CategoryProperty>>(operation)

    /**
     * Categories Reference
     * Returns a complete collection of categories available in the Rapid API.
     * @param operation [GetCategoriesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, CategoryProperty>
     */
    fun executeAsync(operation: GetCategoriesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, CategoryProperty>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, CategoryProperty>>(operation)

    private suspend inline fun kgetCategoriesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, CategoryProperty>> {
        val params =
            GetCategoriesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetCategoriesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Categories Reference
     * Returns a complete collection of categories available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, CategoryProperty>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetCategoriesReferenceOperation)"))
    fun getCategoriesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, CategoryProperty> = getCategoriesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Categories Reference
     * Returns a complete collection of categories available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, CategoryProperty>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetCategoriesReferenceOperation)"))
    fun getCategoriesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, CategoryProperty>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetCategoriesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Chain Reference ***DEPRECATED***
     * ***DEPRECATED*** Please use `/references/chains` <br>Returns a complete collection of chains recognized by the Rapid API. <br>Chains represent a parent company which can have multiple brands associated with it. A brand can only be associated with one chain. For example, Hilton Worldwide is a chain that has multiple associated brands including DoubleTree, Hampton Inn and Embassy Suites. <br>The response is a JSON map where the key is the chain ID and the value is a chain object. Each chain object also contains a map of its related brands. <br>Note that the set of chain IDs and brand IDs are totally independent of one another. It is possible for a chain and a brand to both use the same number as their ID, but this is just a coincidence that holds no meaning. <br>Chain and brand names are provided in English only.
     * @param operation [GetChainReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, Chain>
     */
    fun execute(operation: GetChainReferenceOperation): Response<kotlin.collections.Map<kotlin.String, Chain>> = execute<Nothing, kotlin.collections.Map<kotlin.String, Chain>>(operation)

    /**
     * Chain Reference ***DEPRECATED***
     * ***DEPRECATED*** Please use `/references/chains` <br>Returns a complete collection of chains recognized by the Rapid API. <br>Chains represent a parent company which can have multiple brands associated with it. A brand can only be associated with one chain. For example, Hilton Worldwide is a chain that has multiple associated brands including DoubleTree, Hampton Inn and Embassy Suites. <br>The response is a JSON map where the key is the chain ID and the value is a chain object. Each chain object also contains a map of its related brands. <br>Note that the set of chain IDs and brand IDs are totally independent of one another. It is possible for a chain and a brand to both use the same number as their ID, but this is just a coincidence that holds no meaning. <br>Chain and brand names are provided in English only.
     * @param operation [GetChainReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, Chain>
     */
    fun executeAsync(operation: GetChainReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, Chain>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, Chain>>(operation)

    private suspend inline fun kgetChainReferenceWithResponse(
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, Chain>> {
        val params =
            GetChainReferenceOperationParams(
                customerSessionId = customerSessionId,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetChainReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Chain Reference ***DEPRECATED***
     * ***DEPRECATED*** Please use `/references/chains` <br>Returns a complete collection of chains recognized by the Rapid API. <br>Chains represent a parent company which can have multiple brands associated with it. A brand can only be associated with one chain. For example, Hilton Worldwide is a chain that has multiple associated brands including DoubleTree, Hampton Inn and Embassy Suites. <br>The response is a JSON map where the key is the chain ID and the value is a chain object. Each chain object also contains a map of its related brands. <br>Note that the set of chain IDs and brand IDs are totally independent of one another. It is possible for a chain and a brand to both use the same number as their ID, but this is just a coincidence that holds no meaning. <br>Chain and brand names are provided in English only.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, Chain>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetChainReferenceOperation)"))
    fun getChainReference(
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, Chain> = getChainReferenceWithResponse(customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Chain Reference ***DEPRECATED***
     * ***DEPRECATED*** Please use `/references/chains` <br>Returns a complete collection of chains recognized by the Rapid API. <br>Chains represent a parent company which can have multiple brands associated with it. A brand can only be associated with one chain. For example, Hilton Worldwide is a chain that has multiple associated brands including DoubleTree, Hampton Inn and Embassy Suites. <br>The response is a JSON map where the key is the chain ID and the value is a chain object. Each chain object also contains a map of its related brands. <br>Note that the set of chain IDs and brand IDs are totally independent of one another. It is possible for a chain and a brand to both use the same number as their ID, but this is just a coincidence that holds no meaning. <br>Chain and brand names are provided in English only.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, Chain>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetChainReferenceOperation)"))
    fun getChainReferenceWithResponse(
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, Chain>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetChainReferenceWithResponse(customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Chains Reference
     * Returns a complete collection of chains recognized by the Rapid API. <br>Chains represent a parent company which can have multiple brands associated with it. A brand can only be associated with one chain. For example, Hilton Worldwide is a chain that has multiple associated brands including DoubleTree, Hampton Inn and Embassy Suites. <br>The response is a JSON map where the key is the chain ID and the value is a chain object. Each chain object also contains a map of its related brands. <br>Note that the set of chain IDs and brand IDs are totally independent of one another. It is possible for a chain and a brand to both use the same number as their ID, but this is just a coincidence that holds no meaning. <br>Chain and brand names are provided in English only.
     * @param operation [GetChainsReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, Chain>
     */
    fun execute(operation: GetChainsReferenceOperation): Response<kotlin.collections.Map<kotlin.String, Chain>> = execute<Nothing, kotlin.collections.Map<kotlin.String, Chain>>(operation)

    /**
     * Chains Reference
     * Returns a complete collection of chains recognized by the Rapid API. <br>Chains represent a parent company which can have multiple brands associated with it. A brand can only be associated with one chain. For example, Hilton Worldwide is a chain that has multiple associated brands including DoubleTree, Hampton Inn and Embassy Suites. <br>The response is a JSON map where the key is the chain ID and the value is a chain object. Each chain object also contains a map of its related brands. <br>Note that the set of chain IDs and brand IDs are totally independent of one another. It is possible for a chain and a brand to both use the same number as their ID, but this is just a coincidence that holds no meaning. <br>Chain and brand names are provided in English only.
     * @param operation [GetChainsReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, Chain>
     */
    fun executeAsync(operation: GetChainsReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, Chain>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, Chain>>(operation)

    private suspend inline fun kgetChainsReferenceWithResponse(
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, Chain>> {
        val params =
            GetChainsReferenceOperationParams(
                customerSessionId = customerSessionId,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetChainsReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Chains Reference
     * Returns a complete collection of chains recognized by the Rapid API. <br>Chains represent a parent company which can have multiple brands associated with it. A brand can only be associated with one chain. For example, Hilton Worldwide is a chain that has multiple associated brands including DoubleTree, Hampton Inn and Embassy Suites. <br>The response is a JSON map where the key is the chain ID and the value is a chain object. Each chain object also contains a map of its related brands. <br>Note that the set of chain IDs and brand IDs are totally independent of one another. It is possible for a chain and a brand to both use the same number as their ID, but this is just a coincidence that holds no meaning. <br>Chain and brand names are provided in English only.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, Chain>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetChainsReferenceOperation)"))
    fun getChainsReference(
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, Chain> = getChainsReferenceWithResponse(customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Chains Reference
     * Returns a complete collection of chains recognized by the Rapid API. <br>Chains represent a parent company which can have multiple brands associated with it. A brand can only be associated with one chain. For example, Hilton Worldwide is a chain that has multiple associated brands including DoubleTree, Hampton Inn and Embassy Suites. <br>The response is a JSON map where the key is the chain ID and the value is a chain object. Each chain object also contains a map of its related brands. <br>Note that the set of chain IDs and brand IDs are totally independent of one another. It is possible for a chain and a brand to both use the same number as their ID, but this is just a coincidence that holds no meaning. <br>Chain and brand names are provided in English only.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, Chain>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetChainsReferenceOperation)"))
    fun getChainsReferenceWithResponse(
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, Chain>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetChainsReferenceWithResponse(customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * General Attributes Reference
     * Returns a complete collection of general attributes available in the Rapid API.
     * @param operation [GetGeneralAttributesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, AttributeReference>
     */
    fun execute(operation: GetGeneralAttributesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, AttributeReference>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, AttributeReference>>(operation)

    /**
     * General Attributes Reference
     * Returns a complete collection of general attributes available in the Rapid API.
     * @param operation [GetGeneralAttributesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, AttributeReference>
     */
    fun executeAsync(operation: GetGeneralAttributesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, AttributeReference>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, AttributeReference>>(operation)

    private suspend inline fun kgetGeneralAttributesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, AttributeReference>> {
        val params =
            GetGeneralAttributesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetGeneralAttributesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * General Attributes Reference
     * Returns a complete collection of general attributes available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, AttributeReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetGeneralAttributesReferenceOperation)"))
    fun getGeneralAttributesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, AttributeReference> =
        getGeneralAttributesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * General Attributes Reference
     * Returns a complete collection of general attributes available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, AttributeReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetGeneralAttributesReferenceOperation)"))
    fun getGeneralAttributesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, AttributeReference>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetGeneralAttributesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Images Reference
     * Returns a complete collection of images available in the Rapid API.
     * @param operation [GetImagesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, ImageReference>
     */
    fun execute(operation: GetImagesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, ImageReference>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, ImageReference>>(operation)

    /**
     * Images Reference
     * Returns a complete collection of images available in the Rapid API.
     * @param operation [GetImagesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, ImageReference>
     */
    fun executeAsync(operation: GetImagesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, ImageReference>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, ImageReference>>(operation)

    private suspend inline fun kgetImagesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, ImageReference>> {
        val params =
            GetImagesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetImagesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Images Reference
     * Returns a complete collection of images available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, ImageReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetImagesReferenceOperation)"))
    fun getImagesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, ImageReference> = getImagesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Images Reference
     * Returns a complete collection of images available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, ImageReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetImagesReferenceOperation)"))
    fun getImagesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, ImageReference>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetImagesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Inactive Properties
     * Request a list of properties that are inactive because they have been removed from sale since a specified date.<br><br> When there are a large number of properties in the response, it will be paginated. See the `Link` header in the 200 response section.
     * @param operation [GetInactivePropertiesOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<PropertyInactive>
     */
    fun execute(operation: GetInactivePropertiesOperation): Response<kotlin.collections.List<PropertyInactive>> = execute<Nothing, kotlin.collections.List<PropertyInactive>>(operation)

    /**
     * Inactive Properties
     * Request a list of properties that are inactive because they have been removed from sale since a specified date.<br><br> When there are a large number of properties in the response, it will be paginated. See the `Link` header in the 200 response section.
     * @param operation [GetInactivePropertiesOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.List<PropertyInactive>
     */
    fun executeAsync(operation: GetInactivePropertiesOperation): CompletableFuture<Response<kotlin.collections.List<PropertyInactive>>> =
        executeAsync<Nothing, kotlin.collections.List<PropertyInactive>>(operation)

    private suspend inline fun kgetInactivePropertiesWithResponse(
        customerSessionId: kotlin.String? =
            null,
        since: kotlin.String? =
            null,
        token: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.List<PropertyInactive>> {
        val params =
            GetInactivePropertiesOperationParams(
                customerSessionId = customerSessionId,
                since = since,
                token = token,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetInactivePropertiesOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Inactive Properties
     * Request a list of properties that are inactive because they have been removed from sale since a specified date.<br><br> When there are a large number of properties in the response, it will be paginated. See the `Link` header in the 200 response section.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param since Required on initial call, not accepted on subsequent paging links provided in response header.<br> The earliest date that a property became inactive to include in the results. ISO 8601 format (YYYY-MM-DD)  (optional)
     * @param token Only used for requesting additional pages of data. Provided by the `next` URL in the `Link` response header.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.List<PropertyInactive>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetInactivePropertiesOperation)"))
    fun getInactiveProperties(
        customerSessionId: kotlin.String? =
            null,
        since: kotlin.String? =
            null,
        token: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.List<PropertyInactive> = getInactivePropertiesWithResponse(customerSessionId, since, token, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Inactive Properties
     * Request a list of properties that are inactive because they have been removed from sale since a specified date.<br><br> When there are a large number of properties in the response, it will be paginated. See the `Link` header in the 200 response section.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param since Required on initial call, not accepted on subsequent paging links provided in response header.<br> The earliest date that a property became inactive to include in the results. ISO 8601 format (YYYY-MM-DD)  (optional)
     * @param token Only used for requesting additional pages of data. Provided by the `next` URL in the `Link` response header.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<PropertyInactive>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetInactivePropertiesOperation)"))
    fun getInactivePropertiesWithResponse(
        customerSessionId: kotlin.String? =
            null,
        since: kotlin.String? =
            null,
        token: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.List<PropertyInactive>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetInactivePropertiesWithResponse(customerSessionId, since, token, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    @JvmOverloads
    fun getPaginator(operation: GetInactivePropertiesOperation): ResponsePaginator<kotlin.collections.List<PropertyInactive>> {
        val response = execute(operation)
        return ResponsePaginator(this, response, emptyList()) { it.body<kotlin.collections.List<PropertyInactive>>() }
    }

    @JvmOverloads
    @Deprecated("Use getPaginator method instead", ReplaceWith("getPaginator(operation: GetInactivePropertiesOperation)"))
    fun getInactivePropertiesPaginator(
        customerSessionId: kotlin.String? =
            null,
        since: kotlin.String? =
            null,
        token: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Paginator<kotlin.collections.List<PropertyInactive>> {
        val response = getInactivePropertiesWithResponse(customerSessionId, since, token, billingTerms, partnerPointOfSale, paymentTerms, platformName)
        return Paginator(this, response, emptyList()) { it.body<kotlin.collections.List<PropertyInactive>>() }
    }

    @JvmOverloads
    @Deprecated("Use getPaginator method instead", ReplaceWith("getPaginator(operation: GetInactivePropertiesOperation)"))
    fun getInactivePropertiesPaginatorWithResponse(
        customerSessionId: kotlin.String? =
            null,
        since: kotlin.String? =
            null,
        token: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): ResponsePaginator<kotlin.collections.List<PropertyInactive>> {
        val response = getInactivePropertiesWithResponse(customerSessionId, since, token, billingTerms, partnerPointOfSale, paymentTerms, platformName)
        return ResponsePaginator(this, response, emptyList()) { it.body<kotlin.collections.List<PropertyInactive>>() }
    }

    /**
     * Onsite Payment Types Reference
     * Returns a complete collection of onsite payment types available in the Rapid API.
     * @param operation [GetOnsitePaymentTypesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, PaymentType>
     */
    fun execute(operation: GetOnsitePaymentTypesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, PaymentType>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, PaymentType>>(operation)

    /**
     * Onsite Payment Types Reference
     * Returns a complete collection of onsite payment types available in the Rapid API.
     * @param operation [GetOnsitePaymentTypesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, PaymentType>
     */
    fun executeAsync(operation: GetOnsitePaymentTypesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, PaymentType>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, PaymentType>>(operation)

    private suspend inline fun kgetOnsitePaymentTypesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, PaymentType>> {
        val params =
            GetOnsitePaymentTypesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetOnsitePaymentTypesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Onsite Payment Types Reference
     * Returns a complete collection of onsite payment types available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, PaymentType>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetOnsitePaymentTypesReferenceOperation)"))
    fun getOnsitePaymentTypesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, PaymentType> = getOnsitePaymentTypesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Onsite Payment Types Reference
     * Returns a complete collection of onsite payment types available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, PaymentType>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetOnsitePaymentTypesReferenceOperation)"))
    fun getOnsitePaymentTypesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, PaymentType>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetOnsitePaymentTypesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Get Accepted Payment Types - EPS MOR Only
     * Returns the accepted payment options.  Use this API to power your checkout page and display valid forms of payment, ensuring a smooth booking.
     * @param operation [GetPaymentOptionsOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type PaymentOption
     */
    fun execute(operation: GetPaymentOptionsOperation): Response<PaymentOption> = execute<Nothing, PaymentOption>(operation)

    /**
     * Get Accepted Payment Types - EPS MOR Only
     * Returns the accepted payment options.  Use this API to power your checkout page and display valid forms of payment, ensuring a smooth booking.
     * @param operation [GetPaymentOptionsOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type PaymentOption
     */
    fun executeAsync(operation: GetPaymentOptionsOperation): CompletableFuture<Response<PaymentOption>> = executeAsync<Nothing, PaymentOption>(operation)

    private suspend inline fun kgetPaymentOptionsWithResponse(
        propertyId: kotlin.String,
        token: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null
    ): Response<PaymentOption> {
        val params =
            GetPaymentOptionsOperationParams(
                propertyId = propertyId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                token = token
            )

        val operation =
            GetPaymentOptionsOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Get Accepted Payment Types - EPS MOR Only
     * Returns the accepted payment options.  Use this API to power your checkout page and display valid forms of payment, ensuring a smooth booking.
     * @param propertyId Expedia Property ID.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.  (optional)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return PaymentOption
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPaymentOptionsOperation)"))
    fun getPaymentOptions(
        propertyId: kotlin.String,
        token: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null
    ): PaymentOption = getPaymentOptionsWithResponse(propertyId, token, customerIp, customerSessionId).data

    /**
     * Get Accepted Payment Types - EPS MOR Only
     * Returns the accepted payment options.  Use this API to power your checkout page and display valid forms of payment, ensuring a smooth booking.
     * @param propertyId Expedia Property ID.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.  (optional)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type PaymentOption
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPaymentOptionsOperation)"))
    fun getPaymentOptionsWithResponse(
        propertyId: kotlin.String,
        token: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null
    ): Response<PaymentOption> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetPaymentOptionsWithResponse(propertyId, token, customerIp, customerSessionId)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Pet Attributes Reference
     * Returns a complete collection of pet attributes available in the Rapid API.
     * @param operation [GetPetAttributesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, AttributeReference>
     */
    fun execute(operation: GetPetAttributesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, AttributeReference>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, AttributeReference>>(operation)

    /**
     * Pet Attributes Reference
     * Returns a complete collection of pet attributes available in the Rapid API.
     * @param operation [GetPetAttributesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, AttributeReference>
     */
    fun executeAsync(operation: GetPetAttributesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, AttributeReference>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, AttributeReference>>(operation)

    private suspend inline fun kgetPetAttributesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, AttributeReference>> {
        val params =
            GetPetAttributesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetPetAttributesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Pet Attributes Reference
     * Returns a complete collection of pet attributes available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, AttributeReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPetAttributesReferenceOperation)"))
    fun getPetAttributesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, AttributeReference> = getPetAttributesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Pet Attributes Reference
     * Returns a complete collection of pet attributes available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, AttributeReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPetAttributesReferenceOperation)"))
    fun getPetAttributesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, AttributeReference>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetPetAttributesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Property Catalog File
     * Returns a link to download the master list of EPS's active properties in the requested language. The response includes high-level details about each property.  <br>This file is in JSONL format and is gzipped. The schema of each JSON object in the JSONL file is a subset of the schema of each JSON object from the Property Content call. The subset of fields included are:    * property_id   * name   * address   * ratings   * location   * phone   * fax   * category   * rank   * business_model   * dates   * statistics   * chain   * brand   * supply_source  <br>Example of a JSONL file with 2 properties: ``` {\"property_id\":\"12345\",\"name\":\"Test Property Name\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":false,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=12345\"},\"fr-FR\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=fr-FR&include=address&property_id=12345\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":48382,\"overall\":\"3.1\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"73%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":false},\"dates\":{\"added\":\"1998-07-19T05:00:00.000Z\",\"updated\":\"2018-03-22T07:23:14.000Z\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"chain\":{\"id\":\"-6\",\"name\":\"Hyatt Hotels\"},\"brand\":{\"id\":\"2209\",\"name\":\"Hyatt Place\"},\"supply_source\":\"expedia\"} {\"property_id\":\"67890\",\"name\":\"Test Property Name 2\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":true,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=67890\"},\"de-DE\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=de-DE&include=address&property_id=67890\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":7651,\"overall\":\"4.3\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"80%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":true},\"dates\":{\"added\":\"1998-07-20T05:00:00.000Z\",\"updated\":\"2018-03-22T13:33:17.000Z\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"chain\":{\"id\":\"-5\",\"name\":\"Hilton Worldwide\"},\"brand\":{\"id\":\"358\",\"name\":\"Hampton Inn\"},\"supply_source\":\"expedia\"} ``` 
     * @param operation [GetPropertyCatalogFileOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Link
     */
    fun execute(operation: GetPropertyCatalogFileOperation): Response<Link> = execute<Nothing, Link>(operation)

    /**
     * Property Catalog File
     * Returns a link to download the master list of EPS's active properties in the requested language. The response includes high-level details about each property.  <br>This file is in JSONL format and is gzipped. The schema of each JSON object in the JSONL file is a subset of the schema of each JSON object from the Property Content call. The subset of fields included are:    * property_id   * name   * address   * ratings   * location   * phone   * fax   * category   * rank   * business_model   * dates   * statistics   * chain   * brand   * supply_source  <br>Example of a JSONL file with 2 properties: ``` {\"property_id\":\"12345\",\"name\":\"Test Property Name\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":false,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=12345\"},\"fr-FR\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=fr-FR&include=address&property_id=12345\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":48382,\"overall\":\"3.1\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"73%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":false},\"dates\":{\"added\":\"1998-07-19T05:00:00.000Z\",\"updated\":\"2018-03-22T07:23:14.000Z\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"chain\":{\"id\":\"-6\",\"name\":\"Hyatt Hotels\"},\"brand\":{\"id\":\"2209\",\"name\":\"Hyatt Place\"},\"supply_source\":\"expedia\"} {\"property_id\":\"67890\",\"name\":\"Test Property Name 2\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":true,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=67890\"},\"de-DE\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=de-DE&include=address&property_id=67890\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":7651,\"overall\":\"4.3\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"80%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":true},\"dates\":{\"added\":\"1998-07-20T05:00:00.000Z\",\"updated\":\"2018-03-22T13:33:17.000Z\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"chain\":{\"id\":\"-5\",\"name\":\"Hilton Worldwide\"},\"brand\":{\"id\":\"358\",\"name\":\"Hampton Inn\"},\"supply_source\":\"expedia\"} ``` 
     * @param operation [GetPropertyCatalogFileOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type Link
     */
    fun executeAsync(operation: GetPropertyCatalogFileOperation): CompletableFuture<Response<Link>> = executeAsync<Nothing, Link>(operation)

    private suspend inline fun kgetPropertyCatalogFileWithResponse(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<Link> {
        val params =
            GetPropertyCatalogFileOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                supplySource = supplySource,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetPropertyCatalogFileOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Property Catalog File
     * Returns a link to download the master list of EPS's active properties in the requested language. The response includes high-level details about each property.  <br>This file is in JSONL format and is gzipped. The schema of each JSON object in the JSONL file is a subset of the schema of each JSON object from the Property Content call. The subset of fields included are:    * property_id   * name   * address   * ratings   * location   * phone   * fax   * category   * rank   * business_model   * dates   * statistics   * chain   * brand   * supply_source  <br>Example of a JSONL file with 2 properties: ``` {\"property_id\":\"12345\",\"name\":\"Test Property Name\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":false,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=12345\"},\"fr-FR\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=fr-FR&include=address&property_id=12345\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":48382,\"overall\":\"3.1\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"73%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":false},\"dates\":{\"added\":\"1998-07-19T05:00:00.000Z\",\"updated\":\"2018-03-22T07:23:14.000Z\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"chain\":{\"id\":\"-6\",\"name\":\"Hyatt Hotels\"},\"brand\":{\"id\":\"2209\",\"name\":\"Hyatt Place\"},\"supply_source\":\"expedia\"} {\"property_id\":\"67890\",\"name\":\"Test Property Name 2\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":true,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=67890\"},\"de-DE\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=de-DE&include=address&property_id=67890\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":7651,\"overall\":\"4.3\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"80%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":true},\"dates\":{\"added\":\"1998-07-20T05:00:00.000Z\",\"updated\":\"2018-03-22T13:33:17.000Z\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"chain\":{\"id\":\"-5\",\"name\":\"Hilton Worldwide\"},\"brand\":{\"id\":\"358\",\"name\":\"Hampton Inn\"},\"supply_source\":\"expedia\"} ``` 
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. See [https://www.w3.org/International/articles/language-tags/](https://www.w3.org/International/articles/language-tags/)  Language Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/language-options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return Link
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPropertyCatalogFileOperation)"))
    fun getPropertyCatalogFile(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Link = getPropertyCatalogFileWithResponse(language, supplySource, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Property Catalog File
     * Returns a link to download the master list of EPS's active properties in the requested language. The response includes high-level details about each property.  <br>This file is in JSONL format and is gzipped. The schema of each JSON object in the JSONL file is a subset of the schema of each JSON object from the Property Content call. The subset of fields included are:    * property_id   * name   * address   * ratings   * location   * phone   * fax   * category   * rank   * business_model   * dates   * statistics   * chain   * brand   * supply_source  <br>Example of a JSONL file with 2 properties: ``` {\"property_id\":\"12345\",\"name\":\"Test Property Name\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":false,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=12345\"},\"fr-FR\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=fr-FR&include=address&property_id=12345\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":48382,\"overall\":\"3.1\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"73%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":false},\"dates\":{\"added\":\"1998-07-19T05:00:00.000Z\",\"updated\":\"2018-03-22T07:23:14.000Z\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"chain\":{\"id\":\"-6\",\"name\":\"Hyatt Hotels\"},\"brand\":{\"id\":\"2209\",\"name\":\"Hyatt Place\"},\"supply_source\":\"expedia\"} {\"property_id\":\"67890\",\"name\":\"Test Property Name 2\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":true,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=67890\"},\"de-DE\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=de-DE&include=address&property_id=67890\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":7651,\"overall\":\"4.3\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"80%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":true},\"dates\":{\"added\":\"1998-07-20T05:00:00.000Z\",\"updated\":\"2018-03-22T13:33:17.000Z\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"chain\":{\"id\":\"-5\",\"name\":\"Hilton Worldwide\"},\"brand\":{\"id\":\"358\",\"name\":\"Hampton Inn\"},\"supply_source\":\"expedia\"} ``` 
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. See [https://www.w3.org/International/articles/language-tags/](https://www.w3.org/International/articles/language-tags/)  Language Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/language-options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Link
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPropertyCatalogFileOperation)"))
    fun getPropertyCatalogFileWithResponse(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<Link> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetPropertyCatalogFileWithResponse(language, supplySource, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Property Content
     * Search property content for active properties in the requested language.<br><br> When searching with query parameter, `property_id`, you may request 1 to 250 properties at a time.<br><br> When searching with query parameters other than `property_id`, the response will be paginated. See the `Link` header in the 200 response section.<br><br> The response is a JSON map where the key is the property ID and the value is the property object itself, which can include property-level, room-level and rate-level information.
     * @param operation [GetPropertyContentOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, PropertyContent>
     */
    fun execute(operation: GetPropertyContentOperation): Response<kotlin.collections.Map<kotlin.String, PropertyContent>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, PropertyContent>>(operation)

    /**
     * Property Content
     * Search property content for active properties in the requested language.<br><br> When searching with query parameter, `property_id`, you may request 1 to 250 properties at a time.<br><br> When searching with query parameters other than `property_id`, the response will be paginated. See the `Link` header in the 200 response section.<br><br> The response is a JSON map where the key is the property ID and the value is the property object itself, which can include property-level, room-level and rate-level information.
     * @param operation [GetPropertyContentOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, PropertyContent>
     */
    fun executeAsync(operation: GetPropertyContentOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, PropertyContent>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, PropertyContent>>(operation)

    private suspend inline fun kgetPropertyContentWithResponse(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        allInclusive: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        amenityId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        attributeId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        brandId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        businessModel: kotlin.collections.List<
            GetPropertyContentOperationParams.BusinessModel
        >? =
            null,
        categoryId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        categoryIdExclude: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        chainId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        countryCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        dateAddedEnd: kotlin.String? =
            null,
        dateAddedStart: kotlin.String? =
            null,
        dateUpdatedEnd: kotlin.String? =
            null,
        dateUpdatedStart: kotlin.String? =
            null,
        include: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        multiUnit: kotlin.Boolean? =
            null,
        propertyId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        propertyRatingMax: kotlin.String? =
            null,
        propertyRatingMin: kotlin.String? =
            null,
        spokenLanguageId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, PropertyContent>> {
        val params =
            GetPropertyContentOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                supplySource = supplySource,
                allInclusive = allInclusive,
                amenityId = amenityId,
                attributeId = attributeId,
                brandId = brandId,
                businessModel = businessModel,
                categoryId = categoryId,
                categoryIdExclude = categoryIdExclude,
                chainId = chainId,
                countryCode = countryCode,
                dateAddedEnd = dateAddedEnd,
                dateAddedStart = dateAddedStart,
                dateUpdatedEnd = dateUpdatedEnd,
                dateUpdatedStart = dateUpdatedStart,
                include = include,
                multiUnit = multiUnit,
                propertyId = propertyId,
                propertyRatingMax = propertyRatingMax,
                propertyRatingMin = propertyRatingMin,
                spokenLanguageId = spokenLanguageId,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetPropertyContentOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Property Content
     * Search property content for active properties in the requested language.<br><br> When searching with query parameter, `property_id`, you may request 1 to 250 properties at a time.<br><br> When searching with query parameters other than `property_id`, the response will be paginated. See the `Link` header in the 200 response section.<br><br> The response is a JSON map where the key is the property ID and the value is the property object itself, which can include property-level, room-level and rate-level information.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param allInclusive Search to include properties that have the requested `all_inclusive` values equal to true. If this parameter is not supplied, all `all_inclusive` scenarios are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested scenarios.   * `all_rate_plans` - Return properties where `all_inclusive.all_rate_plans` is true.   * `some_rate_plans` = Return properties where `all_inclusive.some_rate_plans` is true.  (optional)
     * @param amenityId The ID of the amenity you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested amenity IDs. This is currently only capable of searching for property level amenities. Room and rate level amenities cannot be searched on.  (optional)
     * @param attributeId The ID of the attribute you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested attribute IDs.  (optional)
     * @param brandId The ID of the brand you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested brand IDs.  (optional)
     * @param businessModel Search for properties with the requested business model enabled. This parameter can be supplied multiple times with different values, which will return all properties that match any of the requested business models. The value must be lower case.   * `expedia_collect` - Return only properties where the payment is collected by Expedia.   * `property_collect` - Return only properties where the payment is collected at the property.  (optional)
     * @param categoryId Search to include properties that have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested category IDs.  (optional)
     * @param categoryIdExclude Search to exclude properties that do not have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will exclude properties that match any of the requested category IDs.  (optional)
     * @param chainId The ID of the chain you want to search for. These chain IDs can be positive and negative numbers. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested chain IDs.  (optional)
     * @param countryCode Search for properties with the requested country code, in ISO 3166-1 alpha-2 format. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested country codes.  (optional)
     * @param dateAddedEnd Search for properties added on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)  (optional)
     * @param dateAddedStart Search for properties added on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)  (optional)
     * @param dateUpdatedEnd Search for properties updated on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)  (optional)
     * @param dateUpdatedStart Search for properties updated on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)  (optional)
     * @param include Each time this parameter is specified will add to the list of fields and associated objects returned in the response. All values and field names are lower case. The values `property_ids` and `catalog` will continue to behave as specified below for backwards compatibility. All other top level field names will add the specified field to the list of fields returned in the response. See the response schema for a full list of top level field names. Additionally, the field `property_id` will always be returned regardless of what include values are passed.<br><br> Possible values:  * `property_ids` - ***DEPRECATED*** - Please use `property_id` which matches the response field name.  * `catalog` - Include all property catalog fields. See     [Property Catalog File endpoint](https://developers.expediagroup.com/docs/rapid/resources/rapid-api#get-/files/properties/catalog) for a list of fields.  * `property_id` - Passing in the value `property_id` and no other values will limit the response to only      `property_id`. Not necessary to include in combination with other field name values, as it will always      be returned.  * All field names found at the top level of the property content response are now valid values for     inclusion.  (optional)
     * @param multiUnit Search for multi-unit properties. If this parameter is not supplied, both single-unit and multi-unit properties will be included.   * `true` - Include only properties that are multi-unit.   * `false` - Do not include properties that are multi-unit.  (optional)
     * @param propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.  (optional)
     * @param propertyRatingMax Search for properties with a property rating less than or equal to the requested rating. The highest property rating value is 5.0.  (optional)
     * @param propertyRatingMin Search for properties with a property rating greater than or equal to the requested rating. The lowest property rating value is 0.0.  (optional)
     * @param spokenLanguageId The id of the spoken language you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested spoken languages. The language code as a subset of BCP47 format.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, PropertyContent>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPropertyContentOperation)"))
    fun getPropertyContent(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        allInclusive: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        amenityId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        attributeId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        brandId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        businessModel: kotlin.collections.List<
            GetPropertyContentOperationParams.BusinessModel
        >? =
            null,
        categoryId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        categoryIdExclude: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        chainId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        countryCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        dateAddedEnd: kotlin.String? =
            null,
        dateAddedStart: kotlin.String? =
            null,
        dateUpdatedEnd: kotlin.String? =
            null,
        dateUpdatedStart: kotlin.String? =
            null,
        include: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        multiUnit: kotlin.Boolean? =
            null,
        propertyId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        propertyRatingMax: kotlin.String? =
            null,
        propertyRatingMin: kotlin.String? =
            null,
        spokenLanguageId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, PropertyContent> =
        getPropertyContentWithResponse(
            language,
            supplySource,
            customerSessionId,
            allInclusive,
            amenityId,
            attributeId,
            brandId,
            businessModel,
            categoryId,
            categoryIdExclude,
            chainId,
            countryCode,
            dateAddedEnd,
            dateAddedStart,
            dateUpdatedEnd,
            dateUpdatedStart,
            include,
            multiUnit,
            propertyId,
            propertyRatingMax,
            propertyRatingMin,
            spokenLanguageId,
            billingTerms,
            partnerPointOfSale,
            paymentTerms,
            platformName
        ).data

    /**
     * Property Content
     * Search property content for active properties in the requested language.<br><br> When searching with query parameter, `property_id`, you may request 1 to 250 properties at a time.<br><br> When searching with query parameters other than `property_id`, the response will be paginated. See the `Link` header in the 200 response section.<br><br> The response is a JSON map where the key is the property ID and the value is the property object itself, which can include property-level, room-level and rate-level information.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param allInclusive Search to include properties that have the requested `all_inclusive` values equal to true. If this parameter is not supplied, all `all_inclusive` scenarios are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested scenarios.   * `all_rate_plans` - Return properties where `all_inclusive.all_rate_plans` is true.   * `some_rate_plans` = Return properties where `all_inclusive.some_rate_plans` is true.  (optional)
     * @param amenityId The ID of the amenity you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested amenity IDs. This is currently only capable of searching for property level amenities. Room and rate level amenities cannot be searched on.  (optional)
     * @param attributeId The ID of the attribute you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested attribute IDs.  (optional)
     * @param brandId The ID of the brand you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested brand IDs.  (optional)
     * @param businessModel Search for properties with the requested business model enabled. This parameter can be supplied multiple times with different values, which will return all properties that match any of the requested business models. The value must be lower case.   * `expedia_collect` - Return only properties where the payment is collected by Expedia.   * `property_collect` - Return only properties where the payment is collected at the property.  (optional)
     * @param categoryId Search to include properties that have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested category IDs.  (optional)
     * @param categoryIdExclude Search to exclude properties that do not have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will exclude properties that match any of the requested category IDs.  (optional)
     * @param chainId The ID of the chain you want to search for. These chain IDs can be positive and negative numbers. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested chain IDs.  (optional)
     * @param countryCode Search for properties with the requested country code, in ISO 3166-1 alpha-2 format. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested country codes.  (optional)
     * @param dateAddedEnd Search for properties added on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)  (optional)
     * @param dateAddedStart Search for properties added on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)  (optional)
     * @param dateUpdatedEnd Search for properties updated on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)  (optional)
     * @param dateUpdatedStart Search for properties updated on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)  (optional)
     * @param include Each time this parameter is specified will add to the list of fields and associated objects returned in the response. All values and field names are lower case. The values `property_ids` and `catalog` will continue to behave as specified below for backwards compatibility. All other top level field names will add the specified field to the list of fields returned in the response. See the response schema for a full list of top level field names. Additionally, the field `property_id` will always be returned regardless of what include values are passed.<br><br> Possible values:  * `property_ids` - ***DEPRECATED*** - Please use `property_id` which matches the response field name.  * `catalog` - Include all property catalog fields. See     [Property Catalog File endpoint](https://developers.expediagroup.com/docs/rapid/resources/rapid-api#get-/files/properties/catalog) for a list of fields.  * `property_id` - Passing in the value `property_id` and no other values will limit the response to only      `property_id`. Not necessary to include in combination with other field name values, as it will always      be returned.  * All field names found at the top level of the property content response are now valid values for     inclusion.  (optional)
     * @param multiUnit Search for multi-unit properties. If this parameter is not supplied, both single-unit and multi-unit properties will be included.   * `true` - Include only properties that are multi-unit.   * `false` - Do not include properties that are multi-unit.  (optional)
     * @param propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.  (optional)
     * @param propertyRatingMax Search for properties with a property rating less than or equal to the requested rating. The highest property rating value is 5.0.  (optional)
     * @param propertyRatingMin Search for properties with a property rating greater than or equal to the requested rating. The lowest property rating value is 0.0.  (optional)
     * @param spokenLanguageId The id of the spoken language you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested spoken languages. The language code as a subset of BCP47 format.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, PropertyContent>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPropertyContentOperation)"))
    fun getPropertyContentWithResponse(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        allInclusive: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        amenityId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        attributeId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        brandId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        businessModel: kotlin.collections.List<
            GetPropertyContentOperationParams.BusinessModel
        >? =
            null,
        categoryId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        categoryIdExclude: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        chainId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        countryCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        dateAddedEnd: kotlin.String? =
            null,
        dateAddedStart: kotlin.String? =
            null,
        dateUpdatedEnd: kotlin.String? =
            null,
        dateUpdatedStart: kotlin.String? =
            null,
        include: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        multiUnit: kotlin.Boolean? =
            null,
        propertyId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        propertyRatingMax: kotlin.String? =
            null,
        propertyRatingMin: kotlin.String? =
            null,
        spokenLanguageId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, PropertyContent>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetPropertyContentWithResponse(
                    language,
                    supplySource,
                    customerSessionId,
                    allInclusive,
                    amenityId,
                    attributeId,
                    brandId,
                    businessModel,
                    categoryId,
                    categoryIdExclude,
                    chainId,
                    countryCode,
                    dateAddedEnd,
                    dateAddedStart,
                    dateUpdatedEnd,
                    dateUpdatedStart,
                    include,
                    multiUnit,
                    propertyId,
                    propertyRatingMax,
                    propertyRatingMin,
                    spokenLanguageId,
                    billingTerms,
                    partnerPointOfSale,
                    paymentTerms,
                    platformName
                )
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    @JvmOverloads
    fun getPaginator(operation: GetPropertyContentOperation): ResponsePaginator<kotlin.collections.Map<kotlin.String, PropertyContent>> {
        val response = execute(operation)
        return ResponsePaginator(this, response, emptyMap()) { it.body<kotlin.collections.Map<kotlin.String, PropertyContent>>() }
    }

    @JvmOverloads
    @Deprecated("Use getPaginator method instead", ReplaceWith("getPaginator(operation: GetPropertyContentOperation)"))
    fun getPropertyContentPaginator(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        allInclusive: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        amenityId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        attributeId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        brandId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        businessModel: kotlin.collections.List<
            GetPropertyContentOperationParams.BusinessModel
        >? =
            null,
        categoryId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        categoryIdExclude: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        chainId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        countryCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        dateAddedEnd: kotlin.String? =
            null,
        dateAddedStart: kotlin.String? =
            null,
        dateUpdatedEnd: kotlin.String? =
            null,
        dateUpdatedStart: kotlin.String? =
            null,
        include: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        multiUnit: kotlin.Boolean? =
            null,
        propertyId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        propertyRatingMax: kotlin.String? =
            null,
        propertyRatingMin: kotlin.String? =
            null,
        spokenLanguageId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Paginator<kotlin.collections.Map<kotlin.String, PropertyContent>> {
        val response =
            getPropertyContentWithResponse(
                language,
                supplySource,
                customerSessionId,
                allInclusive,
                amenityId,
                attributeId,
                brandId,
                businessModel,
                categoryId,
                categoryIdExclude,
                chainId,
                countryCode,
                dateAddedEnd,
                dateAddedStart,
                dateUpdatedEnd,
                dateUpdatedStart,
                include,
                multiUnit,
                propertyId,
                propertyRatingMax,
                propertyRatingMin,
                spokenLanguageId,
                billingTerms,
                partnerPointOfSale,
                paymentTerms,
                platformName
            )
        return Paginator(this, response, emptyMap()) { it.body<kotlin.collections.Map<kotlin.String, PropertyContent>>() }
    }

    @JvmOverloads
    @Deprecated("Use getPaginator method instead", ReplaceWith("getPaginator(operation: GetPropertyContentOperation)"))
    fun getPropertyContentPaginatorWithResponse(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        allInclusive: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        amenityId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        attributeId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        brandId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        businessModel: kotlin.collections.List<
            GetPropertyContentOperationParams.BusinessModel
        >? =
            null,
        categoryId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        categoryIdExclude: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        chainId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        countryCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        dateAddedEnd: kotlin.String? =
            null,
        dateAddedStart: kotlin.String? =
            null,
        dateUpdatedEnd: kotlin.String? =
            null,
        dateUpdatedStart: kotlin.String? =
            null,
        include: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        multiUnit: kotlin.Boolean? =
            null,
        propertyId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        propertyRatingMax: kotlin.String? =
            null,
        propertyRatingMin: kotlin.String? =
            null,
        spokenLanguageId: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): ResponsePaginator<kotlin.collections.Map<kotlin.String, PropertyContent>> {
        val response =
            getPropertyContentWithResponse(
                language,
                supplySource,
                customerSessionId,
                allInclusive,
                amenityId,
                attributeId,
                brandId,
                businessModel,
                categoryId,
                categoryIdExclude,
                chainId,
                countryCode,
                dateAddedEnd,
                dateAddedStart,
                dateUpdatedEnd,
                dateUpdatedStart,
                include,
                multiUnit,
                propertyId,
                propertyRatingMax,
                propertyRatingMin,
                spokenLanguageId,
                billingTerms,
                partnerPointOfSale,
                paymentTerms,
                platformName
            )
        return ResponsePaginator(this, response, emptyMap()) { it.body<kotlin.collections.Map<kotlin.String, PropertyContent>>() }
    }

    /**
     * Property Content File
     * Returns a link to download all content for all of EPS’s active properties in the requested language. The response includes property-level, room-level and rate-level information.  <br>This file is in JSONL format and is gzipped. The schema of each JSON object in the JSONL file is the same as the schema of each JSON object from the Property Content call.  <br>Example of a JSONL file with 2 properties:   ```   {\"property_id\":\"12345\",\"name\":\"Test Property Name\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":false,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=12345\"},\"fr-FR\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=fr-FR&include=address&property_id=12345\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":48382,\"overall\":\"3.1\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"73%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":false},\"checkin\":{\"24_hour\":\"24-hour check-in\",\"begin_time\":\"3:00 PM\",\"end_time\":\"11:00 PM\",\"instructions\":\"Extra-person charges may apply and vary depending on hotel policy. &lt;br />Government-issued photo identification and a credit card or cash deposit are required at check-in for incidental charges. &lt;br />Special requests are subject to availability upon check-in and may incur additional charges. Special requests cannot be guaranteed. &lt;br />\",\"special_instructions\":\"There is no front desk at this property. To make arrangements for check-in please contact the property ahead of time using the information on the booking confirmation.\",\"min_age\":18},\"checkout\":{\"time\":\"11:00 AM\"},\"fees\":{\"mandatory\":\"<p>You'll be asked to pay the following charges at the hotel:</p> <ul><li>Deposit: USD 50 per day</li><li>Resort fee: USD 29.12 per accommodation, per night</li></ul> The hotel resort fee includes:<ul><li>Fitness center access</li><li>Internet access</li><li>Phone calls</li><li>Additional inclusions</li></ul> <p>We have included all charges provided to us by the property. However, charges can vary, for example, based on length of stay or the room you book. </p>\",\"optional\":\"Fee for in-room wireless Internet: USD 15 per hour (rates may vary)</li> <li>Airport shuttle fee: USD 350 per vehicle (one way)</li>           <li>Rollaway bed fee: USD 175 per night</li>\"},\"policies\":{\"know_before_you_go\":\"Reservations are required for massage services and spa treatments. Reservations can be made by contacting the hotel prior to arrival, using the contact information on the booking confirmation. </li><li>Children 11 years old and younger stay free when occupying the parent or guardian's room, using existing bedding. </li><li>Only registered guests are allowed in the guestrooms. </li> <li>Some facilities may have restricted access. Guests can contact the property for details using the contact information on the booking confirmation. </li> </ul>\"},\"attributes\":{\"general\":{\"2549\":{\"id\":\"2549\",\"name\":\"No elevators\"},\"3357\":{\"id\":\"3357\",\"name\":\"Caters to adults only\"}},\"pets\":{\"51\":{\"id\":\"51\",\"name\":\"Pets allowed\"},\"2809\":{\"id\":\"2809\",\"name\":\"Dogs only\"},\"3321\":{\"id\":\"3321\",\"name\":\"Pet maximum weight in kg is - 24\",\"value\":24}}},\"amenities\":{\"9\":{\"id\":\"9\",\"name\":\"Fitness facilities\"},\"2820\":{\"id\":\"2820\",\"name\":\"Number of indoor pools - 10\",\"value\":10}},\"images\":[{\"caption\":\"Featured Image\",\"hero_image\":true,\"category\":3,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}}}],\"onsite_payments\":{\"currency\":\"USD\",\"types\":{\"171\":{\"id\":\"171\",\"name\":\"American Express\"}}},\"rooms\":{\"224829\":{\"id\":\"224829\",\"name\":\"Single Room\",\"descriptions\":{\"overview\":\"<strong>2 Twin Beds</strong><br />269-sq-foot (25-sq-meter) room with mountain views<br /><br /><b>Internet</b> - Free WiFi <br /> <b>Entertainment</b> - Flat-screen TV with cable channels<br /><b>Food & Drink</b> - Refrigerator, coffee/tea maker,  room service, and free bottled water<br /><b>Sleep</b> - Premium bedding <br /><b>Bathroom</b> - Private bathroom, shower, bathrobes, and free toiletries<br /><b>Practical</b> - Safe and desk; cribs/infant beds available on request<br /><b>Comfort</b> - Climate-controlled air conditioning and daily housekeeping<br />Non-Smoking<br />\"},\"amenities\":{\"130\":{\"id\":\"130\",\"name\":\"Refrigerator\"},\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"}},\"images\":[{\"hero_image\":true,\"category\":21001,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}},\"caption\":\"Guestroom\"}],\"bed_groups\":{\"37321\":{\"id\":\"37321\",\"description\":\"1 King Bed\",\"configuration\":[{\"type\":\"KingBed\",\"size\":\"King\",\"quantity\":1}]}},\"area\":{\"square_meters\":20,\"square_feet\":215},\"views\":{\"4146\":{\"id\":\"4146\",\"name\":\"Courtyard view\"}},\"occupancy\":{\"max_allowed\":{\"total\":5,\"children\":2,\"adults\":4},\"age_categories\":{\"Adult\":{\"name\":\"Adult\",\"minimum_age\":9}}}}},\"rates\":{\"333abc\":{\"id\":\"333abc\",\"amenities\":{\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"},\"2104\":{\"id\":\"2104\",\"name\":\"Full Breakfast\"}},\"special_offer_description\":\"<strong>Breakfast for 2</strong> - Rate includes the following:\\r\\n<ul><li>Accommodations as selected</li>\\r\\n<li>Breakfast in hotel restaurant for up to 2 adults and children 12 years old and under registered in the same room</li>\\r\\n</ul><em>Must book this rate plan to receive benefits. Details provided at check-in. Taxes and gratuity may not be included. No refunds for any unused portion of offer. Offer subject to availability. Offer is not valid with groups/conventions and may not be combined with other promotional offers. Other restrictions and blackout dates may apply.</em>\\r\\n\"}},\"dates\":{\"added\":\"1998-07-19T05:00:00.000Z\",\"updated\":\"2018-03-22T07:23:14.000Z\"},\"descriptions\":{\"amenities\":\"Don't miss out on the many recreational opportunities, including an outdoor pool, a sauna, and a fitness center. Additional features at this hotel include complimentary wireless Internet access, concierge services, and an arcade/game room.\",\"dining\":\"Grab a bite at one of the hotel's 3 restaurants, or stay in and take advantage of 24-hour room service. Quench your thirst with your favorite drink at a bar/lounge. Buffet breakfasts are available daily for a fee.\",\"renovations\":\"During renovations, the hotel will make every effort to minimize noise and disturbance.  The property will be renovating from 08 May 2017 to 18 May 2017 (completion date subject to change). The following areas are affected:  <ul><li>Fitness facilities</li></ul>\",\"national_ratings\":\"For the benefit of our customers, we have provided a rating based on our rating system.\",\"business_amenities\":\"Featured amenities include complimentary wired Internet access, a 24-hour business center, and limo/town car service. Event facilities at this hotel consist of a conference center and meeting rooms. Free self parking is available onsite.\",\"rooms\":\"Make yourself at home in one of the 334 air-conditioned rooms featuring LCD televisions. Complimentary wired and wireless Internet access keeps you connected, and satellite programming provides entertainment. Private bathrooms with separate bathtubs and showers feature deep soaking bathtubs and complimentary toiletries. Conveniences include phones, as well as safes and desks.\",\"attractions\":\"Distances are calculated in a straight line from the property's location to the point of interest or attraction, and may not reflect actual travel distance. <br /><br /> Distances are displayed to the nearest 0.1 mile and kilometer. <p>Sogo Department Store - 0.7 km / 0.4 mi <br />National Museum of Natural Science - 1.1 km / 0.7 mi <br />Shr-Hwa International Tower - 1.4 km / 0.8 mi <br />Shinkong Mitsukoshi Department Store - 1.5 km / 0.9 mi <br />Taichung Metropolitan Opera House - 1.7 km / 1 mi <br />Tiger City Mall - 1.8 km / 1.1 mi <br />Maple Garden Park - 1.9 km / 1.2 mi <br />National Museum of Fine Arts - 2.1 km / 1.3 mi <br />Feng Chia University - 2.4 km / 1.5 mi <br />Bao An Temple - 2.5 km / 1.6 mi <br />Fengjia Night Market - 2.5 km / 1.6 mi <br />Zhonghua Night Market - 2.7 km / 1.7 mi <br />Chonglun Park - 2.9 km / 1.8 mi <br />Wan He Temple - 2.9 km / 1.8 mi <br />Chungyo Department Store - 3.1 km / 1.9 mi <br /></p><p>The nearest airports are:<br />Taichung (RMQ) - 12 km / 7.5 mi<br />Taipei (TPE-Taoyuan Intl.) - 118.3 km / 73.5 mi<br />Taipei (TSA-Songshan) - 135.5 km / 84.2 mi<br /></p><p></p>\",\"location\":\"This 4-star hotel is within close proximity of Shr-Hwa International Tower and Shinkong Mitsukoshi Department Store.  A stay at Tempus Hotel Taichung places you in the heart of Taichung, convenient to Sogo Department Store and National Museum of Natural Science.\",\"headline\":\"Near National Museum of Natural Science\",\"general\":\"General description\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"airports\":{\"preferred\":{\"iata_airport_code\":\"SGF\"}},\"themes\":{\"2337\":{\"id\":\"2337\",\"name\":\"Luxury Hotel\"},\"2341\":{\"id\":\"2341\",\"name\":\"Spa Hotel\"}},\"all_inclusive\":{\"all_rate_plans\":true,\"some_rate_plans\":false,\"details\":\"<p>This resort is all-inclusive. Onsite food and beverages are included in the room price (some restrictions may apply). </p><p><strong>Activities and facilities/equipment</strong><br />Land activities<ul><li>Fitness facilities</li></ul><br />Lessons/classes/games <ul><li>Pilates</li><li>Yoga</li></ul></p><p><strong>Entertainment</strong><ul><li>Onsite entertainment and activities</li><li>Onsite live performances</li></ul></p>\"},\"tax_id\":\"AB-012-987-1234-01\",\"chain\":{\"id\":\"-6\",\"name\":\"Hyatt Hotels\"},\"brand\":{\"id\":\"2209\",\"name\":\"Hyatt Place\"},\"spoken_languages\":{\"vi\":{\"id\":\"vi\",\"name\":\"Vietnamese\"}},\"multi_unit\":true,\"payment_registration_recommended\":false,\"vacation_rental_details\": {\"registry_number\": \"Property Registration Number 123456\",\"private_host\": \"true\",\"property_manager\": {\"name\": \"Victor\",\"links\": {\"image\": {\"method\": \"GET\",\"href\": \"https://test-image/test/test/836f1097-fbcf-43b5-bc02-c8ff6658cb90.c1.jpg\"}}},\"rental_agreement\": {\"links\": {\"rental_agreement\": {\"method\": \"GET\",\"href\": \"https://test-link.test.amazonaws.com/rentalconditions_property_d65e7eb5-4a7c-4a80-a8a3-171999f9f444.pdf\"}}},\"house_rules\": [\"Children welcome\",\"No pets\",\"No smoking\",\"No parties or events\"],\"amenities\": {\"4296\": {\"id\": \"4296\",\"name\": \"Furnished balcony or patio\"},\"2859\": {\"id\": \"2859\",\"name\": \"Private pool\"}},\"vrbo_srp_id\": \"123.1234567.5678910\",\"listing_id\": \"1234567\",\"listing_number\": \"5678910\",\"listing_source\": \"HOMEAWAY_US\",\"listing_unit\": \"/units/0000/32d82dfa-1a48-45d6-9132-49fdbf1bfc60\"},\"supply_source\":\"vrbo\",\"registry_number\":\"Property Registration Number 123456\"}   {\"property_id\":\"67890\",\"name\":\"Test Property Name 2\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":true,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=67890\"},\"de-DE\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=de-DE&include=address&property_id=67890\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":7651,\"overall\":\"4.3\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"80%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838},\"obfuscated_coordinates\":{\"latitude\":28.339303,\"longitude\":-81.47791},\"obfuscation_required\":true},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":true},\"checkin\":{\"24_hour\":\"24-hour check-in\",\"begin_time\":\"3:00 PM\",\"end_time\":\"11:00 PM\",\"instructions\":\"Extra-person charges may apply and vary depending on hotel policy. &lt;br />Government-issued photo identification and a credit card or cash deposit are required at check-in for incidental charges. &lt;br />Special requests are subject to availability upon check-in and may incur additional charges. Special requests cannot be guaranteed. &lt;br />\",\"special_instructions\":\"There is no front desk at this property. To make arrangements for check-in please contact the property ahead of time using the information on the booking confirmation.\",\"min_age\":18},\"checkout\":{\"time\":\"11:00 AM\"},\"fees\":{\"mandatory\":\"<p>You'll be asked to pay the following charges at the hotel:</p> <ul><li>Deposit: USD 50 per day</li><li>Resort fee: USD 29.12 per accommodation, per night</li></ul> The hotel resort fee includes:<ul><li>Fitness center access</li><li>Internet access</li><li>Phone calls</li><li>Additional inclusions</li></ul> <p>We have included all charges provided to us by the property. However, charges can vary, for example, based on length of stay or the room you book. </p>\",\"optional\":\"Fee for in-room wireless Internet: USD 15 per hour (rates may vary)</li> <li>Airport shuttle fee: USD 350 per vehicle (one way)</li>           <li>Rollaway bed fee: USD 175 per night</li>\"},\"policies\":{\"know_before_you_go\":\"Reservations are required for massage services and spa treatments. Reservations can be made by contacting the hotel prior to arrival, using the contact information on the booking confirmation. </li><li>Children 11 years old and younger stay free when occupying the parent or guardian's room, using existing bedding. </li><li>Only registered guests are allowed in the guestrooms. </li> <li>Some facilities may have restricted access. Guests can contact the property for details using the contact information on the booking confirmation. </li> </ul>\"},\"attributes\":{\"general\":{\"2549\":{\"id\":\"2549\",\"name\":\"No elevators\"},\"3357\":{\"id\":\"3357\",\"name\":\"Caters to adults only\"}},\"pets\":{\"51\":{\"id\":\"51\",\"name\":\"Pets allowed\"},\"2809\":{\"id\":\"2809\",\"name\":\"Dogs only\"},\"3321\":{\"id\":\"3321\",\"name\":\"Pet maximum weight in kg is - 24\",\"value\":24}}},\"amenities\":{\"9\":{\"id\":\"9\",\"name\":\"Fitness facilities\"},\"2820\":{\"id\":\"2820\",\"name\":\"Number of indoor pools - 10\",\"value\":10}},\"images\":[{\"caption\":\"Featured Image\",\"hero_image\":true,\"category\":3,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}}}],\"onsite_payments\":{\"currency\":\"USD\",\"types\":{\"171\":{\"id\":\"171\",\"name\":\"American Express\"}}},\"rooms\":{\"224829\":{\"id\":\"224829\",\"name\":\"Single Room\",\"descriptions\":{\"overview\":\"<strong>2 Twin Beds</strong><br />269-sq-foot (25-sq-meter) room with mountain views<br /><br /><b>Internet</b> - Free WiFi <br /> <b>Entertainment</b> - Flat-screen TV with cable channels<br /><b>Food & Drink</b> - Refrigerator, coffee/tea maker,  room service, and free bottled water<br /><b>Sleep</b> - Premium bedding <br /><b>Bathroom</b> - Private bathroom, shower, bathrobes, and free toiletries<br /><b>Practical</b> - Safe and desk; cribs/infant beds available on request<br /><b>Comfort</b> - Climate-controlled air conditioning and daily housekeeping<br />Non-Smoking<br />\"},\"amenities\":{\"130\":{\"id\":\"130\",\"name\":\"Refrigerator\"},\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"}},\"images\":[{\"hero_image\":true,\"category\":21001,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}},\"caption\":\"Guestroom\"}],\"bed_groups\":{\"37321\":{\"id\":\"37321\",\"description\":\"1 King Bed\",\"configuration\":[{\"type\":\"KingBed\",\"size\":\"King\",\"quantity\":1}]}},\"area\":{\"square_meters\":17},\"views\":{\"4134\":{\"id\":\"4134\",\"name\":\"City view\"}},\"occupancy\":{\"max_allowed\":{\"total\":3,\"children\":2,\"adults\":3},\"age_categories\":{\"ChildAgeA\":{\"name\":\"ChildAgeA\",\"minimum_age\":3}}}}},\"rates\":{\"333abc\":{\"id\":\"333abc\",\"amenities\":{\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"},\"2104\":{\"id\":\"2104\",\"name\":\"Full Breakfast\"}},\"special_offer_description\":\"<strong>Breakfast for 2</strong> - Rate includes the following:\\r\\n<ul><li>Accommodations as selected</li>\\r\\n<li>Breakfast in hotel restaurant for up to 2 adults and children 12 years old and under registered in the same room</li>\\r\\n</ul><em>Must book this rate plan to receive benefits. Details provided at check-in. Taxes and gratuity may not be included. No refunds for any unused portion of offer. Offer subject to availability. Offer is not valid with groups/conventions and may not be combined with other promotional offers. Other restrictions and blackout dates may apply.</em>\\r\\n\"}},\"dates\":{\"added\":\"1998-07-20T05:00:00.000Z\",\"updated\":\"2018-03-22T13:33:17.000Z\"},\"descriptions\":{\"amenities\":\"Don't miss out on the many recreational opportunities, including an outdoor pool, a sauna, and a fitness center. Additional features at this hotel include complimentary wireless Internet access, concierge services, and an arcade/game room.\",\"dining\":\"Grab a bite at one of the hotel's 3 restaurants, or stay in and take advantage of 24-hour room service. Quench your thirst with your favorite drink at a bar/lounge. Buffet breakfasts are available daily for a fee.\",\"renovations\":\"During renovations, the hotel will make every effort to minimize noise and disturbance.  The property will be renovating from 08 May 2017 to 18 May 2017 (completion date subject to change). The following areas are affected:  <ul><li>Fitness facilities</li></ul>\",\"national_ratings\":\"For the benefit of our customers, we have provided a rating based on our rating system.\",\"business_amenities\":\"Featured amenities include complimentary wired Internet access, a 24-hour business center, and limo/town car service. Event facilities at this hotel consist of a conference center and meeting rooms. Free self parking is available onsite.\",\"rooms\":\"Make yourself at home in one of the 334 air-conditioned rooms featuring LCD televisions. Complimentary wired and wireless Internet access keeps you connected, and satellite programming provides entertainment. Private bathrooms with separate bathtubs and showers feature deep soaking bathtubs and complimentary toiletries. Conveniences include phones, as well as safes and desks.\",\"attractions\":\"Distances are calculated in a straight line from the property's location to the point of interest or attraction, and may not reflect actual travel distance. <br /><br /> Distances are displayed to the nearest 0.1 mile and kilometer. <p>Sogo Department Store - 0.7 km / 0.4 mi <br />National Museum of Natural Science - 1.1 km / 0.7 mi <br />Shr-Hwa International Tower - 1.4 km / 0.8 mi <br />Shinkong Mitsukoshi Department Store - 1.5 km / 0.9 mi <br />Taichung Metropolitan Opera House - 1.7 km / 1 mi <br />Tiger City Mall - 1.8 km / 1.1 mi <br />Maple Garden Park - 1.9 km / 1.2 mi <br />National Museum of Fine Arts - 2.1 km / 1.3 mi <br />Feng Chia University - 2.4 km / 1.5 mi <br />Bao An Temple - 2.5 km / 1.6 mi <br />Fengjia Night Market - 2.5 km / 1.6 mi <br />Zhonghua Night Market - 2.7 km / 1.7 mi <br />Chonglun Park - 2.9 km / 1.8 mi <br />Wan He Temple - 2.9 km / 1.8 mi <br />Chungyo Department Store - 3.1 km / 1.9 mi <br /></p><p>The nearest airports are:<br />Taichung (RMQ) - 12 km / 7.5 mi<br />Taipei (TPE-Taoyuan Intl.) - 118.3 km / 73.5 mi<br />Taipei (TSA-Songshan) - 135.5 km / 84.2 mi<br /></p><p></p>\",\"location\":\"This 4-star hotel is within close proximity of Shr-Hwa International Tower and Shinkong Mitsukoshi Department Store.  A stay at Tempus Hotel Taichung places you in the heart of Taichung, convenient to Sogo Department Store and National Museum of Natural Science.\",\"headline\":\"Near National Museum of Natural Science\",\"general\":\"General description\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"airports\":{\"preferred\":{\"iata_airport_code\":\"SGF\"}},\"themes\":{\"2337\":{\"id\":\"2337\",\"name\":\"Luxury Hotel\"},\"2341\":{\"id\":\"2341\",\"name\":\"Spa Hotel\"}},\"all_inclusive\":{\"all_rate_plans\":true,\"some_rate_plans\":false,\"details\":\"<p>This resort is all-inclusive. Onsite food and beverages are included in the room price (some restrictions may apply). </p><p><strong>Activities and facilities/equipment</strong><br />Land activities<ul><li>Fitness facilities</li></ul><br />Lessons/classes/games <ul><li>Pilates</li><li>Yoga</li></ul></p><p><strong>Entertainment</strong><ul><li>Onsite entertainment and activities</li><li>Onsite live performances</li></ul></p>\"},\"tax_id\":\"CD-012-987-1234-02\",\"chain\":{\"id\":\"-5\",\"name\":\"Hilton Worldwide\"},\"brand\":{\"id\":\"358\",\"name\":\"Hampton Inn\"},\"spoken_languages\":{\"en\":{\"id\":\"en\",\"name\":\"English\"}},\"multi_unit\":true,\"payment_registration_recommended\":true,\"vacation_rental_details\":{\"registry_number\":\"Property Registration Number 123456\",\"private_host\":\"true\",\"property_manager\":{\"name\":\"John Smith\",\"links\":{\"image\":{\"method\":\"GET\",\"href\":\"https://example.com/profile.jpg\"}}},\"rental_agreement\":{\"links\":{\"rental_agreement\":{\"method\":\"GET\",\"href\":\"https:/example.com/rentalconditions.pdf\"}}},\"house_rules\":[\"Children welcome\",\"No pets\",\"No smoking\",\"No parties or events\"],\"amenities\":{\"2859\":{\"id\":\"2859\",\"name\":\"Private pool\"},\"4296\":{\"id\":\"4296\",\"name\":\"Furnished balcony or patio\"}},\"vrbo_srp_id\":\"123.1234567.5678910\",\"listing_id\":\"1234567\",\"listing_number\":\"5678910\",\"listing_source\":\"HOMEAWAY_US\",\"listing_unit\":\"/units/0000/32d82dfa-1a48-45d6-9132-49fdbf1bfc60\"},\"supply_source\":\"vrbo\",\"registry_number\":\"Property Registration Number 123456\"}   ``` 
     * @param operation [GetPropertyContentFileOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Link
     */
    fun execute(operation: GetPropertyContentFileOperation): Response<Link> = execute<Nothing, Link>(operation)

    /**
     * Property Content File
     * Returns a link to download all content for all of EPS’s active properties in the requested language. The response includes property-level, room-level and rate-level information.  <br>This file is in JSONL format and is gzipped. The schema of each JSON object in the JSONL file is the same as the schema of each JSON object from the Property Content call.  <br>Example of a JSONL file with 2 properties:   ```   {\"property_id\":\"12345\",\"name\":\"Test Property Name\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":false,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=12345\"},\"fr-FR\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=fr-FR&include=address&property_id=12345\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":48382,\"overall\":\"3.1\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"73%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":false},\"checkin\":{\"24_hour\":\"24-hour check-in\",\"begin_time\":\"3:00 PM\",\"end_time\":\"11:00 PM\",\"instructions\":\"Extra-person charges may apply and vary depending on hotel policy. &lt;br />Government-issued photo identification and a credit card or cash deposit are required at check-in for incidental charges. &lt;br />Special requests are subject to availability upon check-in and may incur additional charges. Special requests cannot be guaranteed. &lt;br />\",\"special_instructions\":\"There is no front desk at this property. To make arrangements for check-in please contact the property ahead of time using the information on the booking confirmation.\",\"min_age\":18},\"checkout\":{\"time\":\"11:00 AM\"},\"fees\":{\"mandatory\":\"<p>You'll be asked to pay the following charges at the hotel:</p> <ul><li>Deposit: USD 50 per day</li><li>Resort fee: USD 29.12 per accommodation, per night</li></ul> The hotel resort fee includes:<ul><li>Fitness center access</li><li>Internet access</li><li>Phone calls</li><li>Additional inclusions</li></ul> <p>We have included all charges provided to us by the property. However, charges can vary, for example, based on length of stay or the room you book. </p>\",\"optional\":\"Fee for in-room wireless Internet: USD 15 per hour (rates may vary)</li> <li>Airport shuttle fee: USD 350 per vehicle (one way)</li>           <li>Rollaway bed fee: USD 175 per night</li>\"},\"policies\":{\"know_before_you_go\":\"Reservations are required for massage services and spa treatments. Reservations can be made by contacting the hotel prior to arrival, using the contact information on the booking confirmation. </li><li>Children 11 years old and younger stay free when occupying the parent or guardian's room, using existing bedding. </li><li>Only registered guests are allowed in the guestrooms. </li> <li>Some facilities may have restricted access. Guests can contact the property for details using the contact information on the booking confirmation. </li> </ul>\"},\"attributes\":{\"general\":{\"2549\":{\"id\":\"2549\",\"name\":\"No elevators\"},\"3357\":{\"id\":\"3357\",\"name\":\"Caters to adults only\"}},\"pets\":{\"51\":{\"id\":\"51\",\"name\":\"Pets allowed\"},\"2809\":{\"id\":\"2809\",\"name\":\"Dogs only\"},\"3321\":{\"id\":\"3321\",\"name\":\"Pet maximum weight in kg is - 24\",\"value\":24}}},\"amenities\":{\"9\":{\"id\":\"9\",\"name\":\"Fitness facilities\"},\"2820\":{\"id\":\"2820\",\"name\":\"Number of indoor pools - 10\",\"value\":10}},\"images\":[{\"caption\":\"Featured Image\",\"hero_image\":true,\"category\":3,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}}}],\"onsite_payments\":{\"currency\":\"USD\",\"types\":{\"171\":{\"id\":\"171\",\"name\":\"American Express\"}}},\"rooms\":{\"224829\":{\"id\":\"224829\",\"name\":\"Single Room\",\"descriptions\":{\"overview\":\"<strong>2 Twin Beds</strong><br />269-sq-foot (25-sq-meter) room with mountain views<br /><br /><b>Internet</b> - Free WiFi <br /> <b>Entertainment</b> - Flat-screen TV with cable channels<br /><b>Food & Drink</b> - Refrigerator, coffee/tea maker,  room service, and free bottled water<br /><b>Sleep</b> - Premium bedding <br /><b>Bathroom</b> - Private bathroom, shower, bathrobes, and free toiletries<br /><b>Practical</b> - Safe and desk; cribs/infant beds available on request<br /><b>Comfort</b> - Climate-controlled air conditioning and daily housekeeping<br />Non-Smoking<br />\"},\"amenities\":{\"130\":{\"id\":\"130\",\"name\":\"Refrigerator\"},\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"}},\"images\":[{\"hero_image\":true,\"category\":21001,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}},\"caption\":\"Guestroom\"}],\"bed_groups\":{\"37321\":{\"id\":\"37321\",\"description\":\"1 King Bed\",\"configuration\":[{\"type\":\"KingBed\",\"size\":\"King\",\"quantity\":1}]}},\"area\":{\"square_meters\":20,\"square_feet\":215},\"views\":{\"4146\":{\"id\":\"4146\",\"name\":\"Courtyard view\"}},\"occupancy\":{\"max_allowed\":{\"total\":5,\"children\":2,\"adults\":4},\"age_categories\":{\"Adult\":{\"name\":\"Adult\",\"minimum_age\":9}}}}},\"rates\":{\"333abc\":{\"id\":\"333abc\",\"amenities\":{\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"},\"2104\":{\"id\":\"2104\",\"name\":\"Full Breakfast\"}},\"special_offer_description\":\"<strong>Breakfast for 2</strong> - Rate includes the following:\\r\\n<ul><li>Accommodations as selected</li>\\r\\n<li>Breakfast in hotel restaurant for up to 2 adults and children 12 years old and under registered in the same room</li>\\r\\n</ul><em>Must book this rate plan to receive benefits. Details provided at check-in. Taxes and gratuity may not be included. No refunds for any unused portion of offer. Offer subject to availability. Offer is not valid with groups/conventions and may not be combined with other promotional offers. Other restrictions and blackout dates may apply.</em>\\r\\n\"}},\"dates\":{\"added\":\"1998-07-19T05:00:00.000Z\",\"updated\":\"2018-03-22T07:23:14.000Z\"},\"descriptions\":{\"amenities\":\"Don't miss out on the many recreational opportunities, including an outdoor pool, a sauna, and a fitness center. Additional features at this hotel include complimentary wireless Internet access, concierge services, and an arcade/game room.\",\"dining\":\"Grab a bite at one of the hotel's 3 restaurants, or stay in and take advantage of 24-hour room service. Quench your thirst with your favorite drink at a bar/lounge. Buffet breakfasts are available daily for a fee.\",\"renovations\":\"During renovations, the hotel will make every effort to minimize noise and disturbance.  The property will be renovating from 08 May 2017 to 18 May 2017 (completion date subject to change). The following areas are affected:  <ul><li>Fitness facilities</li></ul>\",\"national_ratings\":\"For the benefit of our customers, we have provided a rating based on our rating system.\",\"business_amenities\":\"Featured amenities include complimentary wired Internet access, a 24-hour business center, and limo/town car service. Event facilities at this hotel consist of a conference center and meeting rooms. Free self parking is available onsite.\",\"rooms\":\"Make yourself at home in one of the 334 air-conditioned rooms featuring LCD televisions. Complimentary wired and wireless Internet access keeps you connected, and satellite programming provides entertainment. Private bathrooms with separate bathtubs and showers feature deep soaking bathtubs and complimentary toiletries. Conveniences include phones, as well as safes and desks.\",\"attractions\":\"Distances are calculated in a straight line from the property's location to the point of interest or attraction, and may not reflect actual travel distance. <br /><br /> Distances are displayed to the nearest 0.1 mile and kilometer. <p>Sogo Department Store - 0.7 km / 0.4 mi <br />National Museum of Natural Science - 1.1 km / 0.7 mi <br />Shr-Hwa International Tower - 1.4 km / 0.8 mi <br />Shinkong Mitsukoshi Department Store - 1.5 km / 0.9 mi <br />Taichung Metropolitan Opera House - 1.7 km / 1 mi <br />Tiger City Mall - 1.8 km / 1.1 mi <br />Maple Garden Park - 1.9 km / 1.2 mi <br />National Museum of Fine Arts - 2.1 km / 1.3 mi <br />Feng Chia University - 2.4 km / 1.5 mi <br />Bao An Temple - 2.5 km / 1.6 mi <br />Fengjia Night Market - 2.5 km / 1.6 mi <br />Zhonghua Night Market - 2.7 km / 1.7 mi <br />Chonglun Park - 2.9 km / 1.8 mi <br />Wan He Temple - 2.9 km / 1.8 mi <br />Chungyo Department Store - 3.1 km / 1.9 mi <br /></p><p>The nearest airports are:<br />Taichung (RMQ) - 12 km / 7.5 mi<br />Taipei (TPE-Taoyuan Intl.) - 118.3 km / 73.5 mi<br />Taipei (TSA-Songshan) - 135.5 km / 84.2 mi<br /></p><p></p>\",\"location\":\"This 4-star hotel is within close proximity of Shr-Hwa International Tower and Shinkong Mitsukoshi Department Store.  A stay at Tempus Hotel Taichung places you in the heart of Taichung, convenient to Sogo Department Store and National Museum of Natural Science.\",\"headline\":\"Near National Museum of Natural Science\",\"general\":\"General description\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"airports\":{\"preferred\":{\"iata_airport_code\":\"SGF\"}},\"themes\":{\"2337\":{\"id\":\"2337\",\"name\":\"Luxury Hotel\"},\"2341\":{\"id\":\"2341\",\"name\":\"Spa Hotel\"}},\"all_inclusive\":{\"all_rate_plans\":true,\"some_rate_plans\":false,\"details\":\"<p>This resort is all-inclusive. Onsite food and beverages are included in the room price (some restrictions may apply). </p><p><strong>Activities and facilities/equipment</strong><br />Land activities<ul><li>Fitness facilities</li></ul><br />Lessons/classes/games <ul><li>Pilates</li><li>Yoga</li></ul></p><p><strong>Entertainment</strong><ul><li>Onsite entertainment and activities</li><li>Onsite live performances</li></ul></p>\"},\"tax_id\":\"AB-012-987-1234-01\",\"chain\":{\"id\":\"-6\",\"name\":\"Hyatt Hotels\"},\"brand\":{\"id\":\"2209\",\"name\":\"Hyatt Place\"},\"spoken_languages\":{\"vi\":{\"id\":\"vi\",\"name\":\"Vietnamese\"}},\"multi_unit\":true,\"payment_registration_recommended\":false,\"vacation_rental_details\": {\"registry_number\": \"Property Registration Number 123456\",\"private_host\": \"true\",\"property_manager\": {\"name\": \"Victor\",\"links\": {\"image\": {\"method\": \"GET\",\"href\": \"https://test-image/test/test/836f1097-fbcf-43b5-bc02-c8ff6658cb90.c1.jpg\"}}},\"rental_agreement\": {\"links\": {\"rental_agreement\": {\"method\": \"GET\",\"href\": \"https://test-link.test.amazonaws.com/rentalconditions_property_d65e7eb5-4a7c-4a80-a8a3-171999f9f444.pdf\"}}},\"house_rules\": [\"Children welcome\",\"No pets\",\"No smoking\",\"No parties or events\"],\"amenities\": {\"4296\": {\"id\": \"4296\",\"name\": \"Furnished balcony or patio\"},\"2859\": {\"id\": \"2859\",\"name\": \"Private pool\"}},\"vrbo_srp_id\": \"123.1234567.5678910\",\"listing_id\": \"1234567\",\"listing_number\": \"5678910\",\"listing_source\": \"HOMEAWAY_US\",\"listing_unit\": \"/units/0000/32d82dfa-1a48-45d6-9132-49fdbf1bfc60\"},\"supply_source\":\"vrbo\",\"registry_number\":\"Property Registration Number 123456\"}   {\"property_id\":\"67890\",\"name\":\"Test Property Name 2\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":true,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=67890\"},\"de-DE\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=de-DE&include=address&property_id=67890\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":7651,\"overall\":\"4.3\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"80%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838},\"obfuscated_coordinates\":{\"latitude\":28.339303,\"longitude\":-81.47791},\"obfuscation_required\":true},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":true},\"checkin\":{\"24_hour\":\"24-hour check-in\",\"begin_time\":\"3:00 PM\",\"end_time\":\"11:00 PM\",\"instructions\":\"Extra-person charges may apply and vary depending on hotel policy. &lt;br />Government-issued photo identification and a credit card or cash deposit are required at check-in for incidental charges. &lt;br />Special requests are subject to availability upon check-in and may incur additional charges. Special requests cannot be guaranteed. &lt;br />\",\"special_instructions\":\"There is no front desk at this property. To make arrangements for check-in please contact the property ahead of time using the information on the booking confirmation.\",\"min_age\":18},\"checkout\":{\"time\":\"11:00 AM\"},\"fees\":{\"mandatory\":\"<p>You'll be asked to pay the following charges at the hotel:</p> <ul><li>Deposit: USD 50 per day</li><li>Resort fee: USD 29.12 per accommodation, per night</li></ul> The hotel resort fee includes:<ul><li>Fitness center access</li><li>Internet access</li><li>Phone calls</li><li>Additional inclusions</li></ul> <p>We have included all charges provided to us by the property. However, charges can vary, for example, based on length of stay or the room you book. </p>\",\"optional\":\"Fee for in-room wireless Internet: USD 15 per hour (rates may vary)</li> <li>Airport shuttle fee: USD 350 per vehicle (one way)</li>           <li>Rollaway bed fee: USD 175 per night</li>\"},\"policies\":{\"know_before_you_go\":\"Reservations are required for massage services and spa treatments. Reservations can be made by contacting the hotel prior to arrival, using the contact information on the booking confirmation. </li><li>Children 11 years old and younger stay free when occupying the parent or guardian's room, using existing bedding. </li><li>Only registered guests are allowed in the guestrooms. </li> <li>Some facilities may have restricted access. Guests can contact the property for details using the contact information on the booking confirmation. </li> </ul>\"},\"attributes\":{\"general\":{\"2549\":{\"id\":\"2549\",\"name\":\"No elevators\"},\"3357\":{\"id\":\"3357\",\"name\":\"Caters to adults only\"}},\"pets\":{\"51\":{\"id\":\"51\",\"name\":\"Pets allowed\"},\"2809\":{\"id\":\"2809\",\"name\":\"Dogs only\"},\"3321\":{\"id\":\"3321\",\"name\":\"Pet maximum weight in kg is - 24\",\"value\":24}}},\"amenities\":{\"9\":{\"id\":\"9\",\"name\":\"Fitness facilities\"},\"2820\":{\"id\":\"2820\",\"name\":\"Number of indoor pools - 10\",\"value\":10}},\"images\":[{\"caption\":\"Featured Image\",\"hero_image\":true,\"category\":3,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}}}],\"onsite_payments\":{\"currency\":\"USD\",\"types\":{\"171\":{\"id\":\"171\",\"name\":\"American Express\"}}},\"rooms\":{\"224829\":{\"id\":\"224829\",\"name\":\"Single Room\",\"descriptions\":{\"overview\":\"<strong>2 Twin Beds</strong><br />269-sq-foot (25-sq-meter) room with mountain views<br /><br /><b>Internet</b> - Free WiFi <br /> <b>Entertainment</b> - Flat-screen TV with cable channels<br /><b>Food & Drink</b> - Refrigerator, coffee/tea maker,  room service, and free bottled water<br /><b>Sleep</b> - Premium bedding <br /><b>Bathroom</b> - Private bathroom, shower, bathrobes, and free toiletries<br /><b>Practical</b> - Safe and desk; cribs/infant beds available on request<br /><b>Comfort</b> - Climate-controlled air conditioning and daily housekeeping<br />Non-Smoking<br />\"},\"amenities\":{\"130\":{\"id\":\"130\",\"name\":\"Refrigerator\"},\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"}},\"images\":[{\"hero_image\":true,\"category\":21001,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}},\"caption\":\"Guestroom\"}],\"bed_groups\":{\"37321\":{\"id\":\"37321\",\"description\":\"1 King Bed\",\"configuration\":[{\"type\":\"KingBed\",\"size\":\"King\",\"quantity\":1}]}},\"area\":{\"square_meters\":17},\"views\":{\"4134\":{\"id\":\"4134\",\"name\":\"City view\"}},\"occupancy\":{\"max_allowed\":{\"total\":3,\"children\":2,\"adults\":3},\"age_categories\":{\"ChildAgeA\":{\"name\":\"ChildAgeA\",\"minimum_age\":3}}}}},\"rates\":{\"333abc\":{\"id\":\"333abc\",\"amenities\":{\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"},\"2104\":{\"id\":\"2104\",\"name\":\"Full Breakfast\"}},\"special_offer_description\":\"<strong>Breakfast for 2</strong> - Rate includes the following:\\r\\n<ul><li>Accommodations as selected</li>\\r\\n<li>Breakfast in hotel restaurant for up to 2 adults and children 12 years old and under registered in the same room</li>\\r\\n</ul><em>Must book this rate plan to receive benefits. Details provided at check-in. Taxes and gratuity may not be included. No refunds for any unused portion of offer. Offer subject to availability. Offer is not valid with groups/conventions and may not be combined with other promotional offers. Other restrictions and blackout dates may apply.</em>\\r\\n\"}},\"dates\":{\"added\":\"1998-07-20T05:00:00.000Z\",\"updated\":\"2018-03-22T13:33:17.000Z\"},\"descriptions\":{\"amenities\":\"Don't miss out on the many recreational opportunities, including an outdoor pool, a sauna, and a fitness center. Additional features at this hotel include complimentary wireless Internet access, concierge services, and an arcade/game room.\",\"dining\":\"Grab a bite at one of the hotel's 3 restaurants, or stay in and take advantage of 24-hour room service. Quench your thirst with your favorite drink at a bar/lounge. Buffet breakfasts are available daily for a fee.\",\"renovations\":\"During renovations, the hotel will make every effort to minimize noise and disturbance.  The property will be renovating from 08 May 2017 to 18 May 2017 (completion date subject to change). The following areas are affected:  <ul><li>Fitness facilities</li></ul>\",\"national_ratings\":\"For the benefit of our customers, we have provided a rating based on our rating system.\",\"business_amenities\":\"Featured amenities include complimentary wired Internet access, a 24-hour business center, and limo/town car service. Event facilities at this hotel consist of a conference center and meeting rooms. Free self parking is available onsite.\",\"rooms\":\"Make yourself at home in one of the 334 air-conditioned rooms featuring LCD televisions. Complimentary wired and wireless Internet access keeps you connected, and satellite programming provides entertainment. Private bathrooms with separate bathtubs and showers feature deep soaking bathtubs and complimentary toiletries. Conveniences include phones, as well as safes and desks.\",\"attractions\":\"Distances are calculated in a straight line from the property's location to the point of interest or attraction, and may not reflect actual travel distance. <br /><br /> Distances are displayed to the nearest 0.1 mile and kilometer. <p>Sogo Department Store - 0.7 km / 0.4 mi <br />National Museum of Natural Science - 1.1 km / 0.7 mi <br />Shr-Hwa International Tower - 1.4 km / 0.8 mi <br />Shinkong Mitsukoshi Department Store - 1.5 km / 0.9 mi <br />Taichung Metropolitan Opera House - 1.7 km / 1 mi <br />Tiger City Mall - 1.8 km / 1.1 mi <br />Maple Garden Park - 1.9 km / 1.2 mi <br />National Museum of Fine Arts - 2.1 km / 1.3 mi <br />Feng Chia University - 2.4 km / 1.5 mi <br />Bao An Temple - 2.5 km / 1.6 mi <br />Fengjia Night Market - 2.5 km / 1.6 mi <br />Zhonghua Night Market - 2.7 km / 1.7 mi <br />Chonglun Park - 2.9 km / 1.8 mi <br />Wan He Temple - 2.9 km / 1.8 mi <br />Chungyo Department Store - 3.1 km / 1.9 mi <br /></p><p>The nearest airports are:<br />Taichung (RMQ) - 12 km / 7.5 mi<br />Taipei (TPE-Taoyuan Intl.) - 118.3 km / 73.5 mi<br />Taipei (TSA-Songshan) - 135.5 km / 84.2 mi<br /></p><p></p>\",\"location\":\"This 4-star hotel is within close proximity of Shr-Hwa International Tower and Shinkong Mitsukoshi Department Store.  A stay at Tempus Hotel Taichung places you in the heart of Taichung, convenient to Sogo Department Store and National Museum of Natural Science.\",\"headline\":\"Near National Museum of Natural Science\",\"general\":\"General description\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"airports\":{\"preferred\":{\"iata_airport_code\":\"SGF\"}},\"themes\":{\"2337\":{\"id\":\"2337\",\"name\":\"Luxury Hotel\"},\"2341\":{\"id\":\"2341\",\"name\":\"Spa Hotel\"}},\"all_inclusive\":{\"all_rate_plans\":true,\"some_rate_plans\":false,\"details\":\"<p>This resort is all-inclusive. Onsite food and beverages are included in the room price (some restrictions may apply). </p><p><strong>Activities and facilities/equipment</strong><br />Land activities<ul><li>Fitness facilities</li></ul><br />Lessons/classes/games <ul><li>Pilates</li><li>Yoga</li></ul></p><p><strong>Entertainment</strong><ul><li>Onsite entertainment and activities</li><li>Onsite live performances</li></ul></p>\"},\"tax_id\":\"CD-012-987-1234-02\",\"chain\":{\"id\":\"-5\",\"name\":\"Hilton Worldwide\"},\"brand\":{\"id\":\"358\",\"name\":\"Hampton Inn\"},\"spoken_languages\":{\"en\":{\"id\":\"en\",\"name\":\"English\"}},\"multi_unit\":true,\"payment_registration_recommended\":true,\"vacation_rental_details\":{\"registry_number\":\"Property Registration Number 123456\",\"private_host\":\"true\",\"property_manager\":{\"name\":\"John Smith\",\"links\":{\"image\":{\"method\":\"GET\",\"href\":\"https://example.com/profile.jpg\"}}},\"rental_agreement\":{\"links\":{\"rental_agreement\":{\"method\":\"GET\",\"href\":\"https:/example.com/rentalconditions.pdf\"}}},\"house_rules\":[\"Children welcome\",\"No pets\",\"No smoking\",\"No parties or events\"],\"amenities\":{\"2859\":{\"id\":\"2859\",\"name\":\"Private pool\"},\"4296\":{\"id\":\"4296\",\"name\":\"Furnished balcony or patio\"}},\"vrbo_srp_id\":\"123.1234567.5678910\",\"listing_id\":\"1234567\",\"listing_number\":\"5678910\",\"listing_source\":\"HOMEAWAY_US\",\"listing_unit\":\"/units/0000/32d82dfa-1a48-45d6-9132-49fdbf1bfc60\"},\"supply_source\":\"vrbo\",\"registry_number\":\"Property Registration Number 123456\"}   ``` 
     * @param operation [GetPropertyContentFileOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type Link
     */
    fun executeAsync(operation: GetPropertyContentFileOperation): CompletableFuture<Response<Link>> = executeAsync<Nothing, Link>(operation)

    private suspend inline fun kgetPropertyContentFileWithResponse(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<Link> {
        val params =
            GetPropertyContentFileOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                supplySource = supplySource,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetPropertyContentFileOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Property Content File
     * Returns a link to download all content for all of EPS’s active properties in the requested language. The response includes property-level, room-level and rate-level information.  <br>This file is in JSONL format and is gzipped. The schema of each JSON object in the JSONL file is the same as the schema of each JSON object from the Property Content call.  <br>Example of a JSONL file with 2 properties:   ```   {\"property_id\":\"12345\",\"name\":\"Test Property Name\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":false,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=12345\"},\"fr-FR\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=fr-FR&include=address&property_id=12345\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":48382,\"overall\":\"3.1\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"73%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":false},\"checkin\":{\"24_hour\":\"24-hour check-in\",\"begin_time\":\"3:00 PM\",\"end_time\":\"11:00 PM\",\"instructions\":\"Extra-person charges may apply and vary depending on hotel policy. &lt;br />Government-issued photo identification and a credit card or cash deposit are required at check-in for incidental charges. &lt;br />Special requests are subject to availability upon check-in and may incur additional charges. Special requests cannot be guaranteed. &lt;br />\",\"special_instructions\":\"There is no front desk at this property. To make arrangements for check-in please contact the property ahead of time using the information on the booking confirmation.\",\"min_age\":18},\"checkout\":{\"time\":\"11:00 AM\"},\"fees\":{\"mandatory\":\"<p>You'll be asked to pay the following charges at the hotel:</p> <ul><li>Deposit: USD 50 per day</li><li>Resort fee: USD 29.12 per accommodation, per night</li></ul> The hotel resort fee includes:<ul><li>Fitness center access</li><li>Internet access</li><li>Phone calls</li><li>Additional inclusions</li></ul> <p>We have included all charges provided to us by the property. However, charges can vary, for example, based on length of stay or the room you book. </p>\",\"optional\":\"Fee for in-room wireless Internet: USD 15 per hour (rates may vary)</li> <li>Airport shuttle fee: USD 350 per vehicle (one way)</li>           <li>Rollaway bed fee: USD 175 per night</li>\"},\"policies\":{\"know_before_you_go\":\"Reservations are required for massage services and spa treatments. Reservations can be made by contacting the hotel prior to arrival, using the contact information on the booking confirmation. </li><li>Children 11 years old and younger stay free when occupying the parent or guardian's room, using existing bedding. </li><li>Only registered guests are allowed in the guestrooms. </li> <li>Some facilities may have restricted access. Guests can contact the property for details using the contact information on the booking confirmation. </li> </ul>\"},\"attributes\":{\"general\":{\"2549\":{\"id\":\"2549\",\"name\":\"No elevators\"},\"3357\":{\"id\":\"3357\",\"name\":\"Caters to adults only\"}},\"pets\":{\"51\":{\"id\":\"51\",\"name\":\"Pets allowed\"},\"2809\":{\"id\":\"2809\",\"name\":\"Dogs only\"},\"3321\":{\"id\":\"3321\",\"name\":\"Pet maximum weight in kg is - 24\",\"value\":24}}},\"amenities\":{\"9\":{\"id\":\"9\",\"name\":\"Fitness facilities\"},\"2820\":{\"id\":\"2820\",\"name\":\"Number of indoor pools - 10\",\"value\":10}},\"images\":[{\"caption\":\"Featured Image\",\"hero_image\":true,\"category\":3,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}}}],\"onsite_payments\":{\"currency\":\"USD\",\"types\":{\"171\":{\"id\":\"171\",\"name\":\"American Express\"}}},\"rooms\":{\"224829\":{\"id\":\"224829\",\"name\":\"Single Room\",\"descriptions\":{\"overview\":\"<strong>2 Twin Beds</strong><br />269-sq-foot (25-sq-meter) room with mountain views<br /><br /><b>Internet</b> - Free WiFi <br /> <b>Entertainment</b> - Flat-screen TV with cable channels<br /><b>Food & Drink</b> - Refrigerator, coffee/tea maker,  room service, and free bottled water<br /><b>Sleep</b> - Premium bedding <br /><b>Bathroom</b> - Private bathroom, shower, bathrobes, and free toiletries<br /><b>Practical</b> - Safe and desk; cribs/infant beds available on request<br /><b>Comfort</b> - Climate-controlled air conditioning and daily housekeeping<br />Non-Smoking<br />\"},\"amenities\":{\"130\":{\"id\":\"130\",\"name\":\"Refrigerator\"},\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"}},\"images\":[{\"hero_image\":true,\"category\":21001,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}},\"caption\":\"Guestroom\"}],\"bed_groups\":{\"37321\":{\"id\":\"37321\",\"description\":\"1 King Bed\",\"configuration\":[{\"type\":\"KingBed\",\"size\":\"King\",\"quantity\":1}]}},\"area\":{\"square_meters\":20,\"square_feet\":215},\"views\":{\"4146\":{\"id\":\"4146\",\"name\":\"Courtyard view\"}},\"occupancy\":{\"max_allowed\":{\"total\":5,\"children\":2,\"adults\":4},\"age_categories\":{\"Adult\":{\"name\":\"Adult\",\"minimum_age\":9}}}}},\"rates\":{\"333abc\":{\"id\":\"333abc\",\"amenities\":{\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"},\"2104\":{\"id\":\"2104\",\"name\":\"Full Breakfast\"}},\"special_offer_description\":\"<strong>Breakfast for 2</strong> - Rate includes the following:\\r\\n<ul><li>Accommodations as selected</li>\\r\\n<li>Breakfast in hotel restaurant for up to 2 adults and children 12 years old and under registered in the same room</li>\\r\\n</ul><em>Must book this rate plan to receive benefits. Details provided at check-in. Taxes and gratuity may not be included. No refunds for any unused portion of offer. Offer subject to availability. Offer is not valid with groups/conventions and may not be combined with other promotional offers. Other restrictions and blackout dates may apply.</em>\\r\\n\"}},\"dates\":{\"added\":\"1998-07-19T05:00:00.000Z\",\"updated\":\"2018-03-22T07:23:14.000Z\"},\"descriptions\":{\"amenities\":\"Don't miss out on the many recreational opportunities, including an outdoor pool, a sauna, and a fitness center. Additional features at this hotel include complimentary wireless Internet access, concierge services, and an arcade/game room.\",\"dining\":\"Grab a bite at one of the hotel's 3 restaurants, or stay in and take advantage of 24-hour room service. Quench your thirst with your favorite drink at a bar/lounge. Buffet breakfasts are available daily for a fee.\",\"renovations\":\"During renovations, the hotel will make every effort to minimize noise and disturbance.  The property will be renovating from 08 May 2017 to 18 May 2017 (completion date subject to change). The following areas are affected:  <ul><li>Fitness facilities</li></ul>\",\"national_ratings\":\"For the benefit of our customers, we have provided a rating based on our rating system.\",\"business_amenities\":\"Featured amenities include complimentary wired Internet access, a 24-hour business center, and limo/town car service. Event facilities at this hotel consist of a conference center and meeting rooms. Free self parking is available onsite.\",\"rooms\":\"Make yourself at home in one of the 334 air-conditioned rooms featuring LCD televisions. Complimentary wired and wireless Internet access keeps you connected, and satellite programming provides entertainment. Private bathrooms with separate bathtubs and showers feature deep soaking bathtubs and complimentary toiletries. Conveniences include phones, as well as safes and desks.\",\"attractions\":\"Distances are calculated in a straight line from the property's location to the point of interest or attraction, and may not reflect actual travel distance. <br /><br /> Distances are displayed to the nearest 0.1 mile and kilometer. <p>Sogo Department Store - 0.7 km / 0.4 mi <br />National Museum of Natural Science - 1.1 km / 0.7 mi <br />Shr-Hwa International Tower - 1.4 km / 0.8 mi <br />Shinkong Mitsukoshi Department Store - 1.5 km / 0.9 mi <br />Taichung Metropolitan Opera House - 1.7 km / 1 mi <br />Tiger City Mall - 1.8 km / 1.1 mi <br />Maple Garden Park - 1.9 km / 1.2 mi <br />National Museum of Fine Arts - 2.1 km / 1.3 mi <br />Feng Chia University - 2.4 km / 1.5 mi <br />Bao An Temple - 2.5 km / 1.6 mi <br />Fengjia Night Market - 2.5 km / 1.6 mi <br />Zhonghua Night Market - 2.7 km / 1.7 mi <br />Chonglun Park - 2.9 km / 1.8 mi <br />Wan He Temple - 2.9 km / 1.8 mi <br />Chungyo Department Store - 3.1 km / 1.9 mi <br /></p><p>The nearest airports are:<br />Taichung (RMQ) - 12 km / 7.5 mi<br />Taipei (TPE-Taoyuan Intl.) - 118.3 km / 73.5 mi<br />Taipei (TSA-Songshan) - 135.5 km / 84.2 mi<br /></p><p></p>\",\"location\":\"This 4-star hotel is within close proximity of Shr-Hwa International Tower and Shinkong Mitsukoshi Department Store.  A stay at Tempus Hotel Taichung places you in the heart of Taichung, convenient to Sogo Department Store and National Museum of Natural Science.\",\"headline\":\"Near National Museum of Natural Science\",\"general\":\"General description\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"airports\":{\"preferred\":{\"iata_airport_code\":\"SGF\"}},\"themes\":{\"2337\":{\"id\":\"2337\",\"name\":\"Luxury Hotel\"},\"2341\":{\"id\":\"2341\",\"name\":\"Spa Hotel\"}},\"all_inclusive\":{\"all_rate_plans\":true,\"some_rate_plans\":false,\"details\":\"<p>This resort is all-inclusive. Onsite food and beverages are included in the room price (some restrictions may apply). </p><p><strong>Activities and facilities/equipment</strong><br />Land activities<ul><li>Fitness facilities</li></ul><br />Lessons/classes/games <ul><li>Pilates</li><li>Yoga</li></ul></p><p><strong>Entertainment</strong><ul><li>Onsite entertainment and activities</li><li>Onsite live performances</li></ul></p>\"},\"tax_id\":\"AB-012-987-1234-01\",\"chain\":{\"id\":\"-6\",\"name\":\"Hyatt Hotels\"},\"brand\":{\"id\":\"2209\",\"name\":\"Hyatt Place\"},\"spoken_languages\":{\"vi\":{\"id\":\"vi\",\"name\":\"Vietnamese\"}},\"multi_unit\":true,\"payment_registration_recommended\":false,\"vacation_rental_details\": {\"registry_number\": \"Property Registration Number 123456\",\"private_host\": \"true\",\"property_manager\": {\"name\": \"Victor\",\"links\": {\"image\": {\"method\": \"GET\",\"href\": \"https://test-image/test/test/836f1097-fbcf-43b5-bc02-c8ff6658cb90.c1.jpg\"}}},\"rental_agreement\": {\"links\": {\"rental_agreement\": {\"method\": \"GET\",\"href\": \"https://test-link.test.amazonaws.com/rentalconditions_property_d65e7eb5-4a7c-4a80-a8a3-171999f9f444.pdf\"}}},\"house_rules\": [\"Children welcome\",\"No pets\",\"No smoking\",\"No parties or events\"],\"amenities\": {\"4296\": {\"id\": \"4296\",\"name\": \"Furnished balcony or patio\"},\"2859\": {\"id\": \"2859\",\"name\": \"Private pool\"}},\"vrbo_srp_id\": \"123.1234567.5678910\",\"listing_id\": \"1234567\",\"listing_number\": \"5678910\",\"listing_source\": \"HOMEAWAY_US\",\"listing_unit\": \"/units/0000/32d82dfa-1a48-45d6-9132-49fdbf1bfc60\"},\"supply_source\":\"vrbo\",\"registry_number\":\"Property Registration Number 123456\"}   {\"property_id\":\"67890\",\"name\":\"Test Property Name 2\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":true,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=67890\"},\"de-DE\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=de-DE&include=address&property_id=67890\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":7651,\"overall\":\"4.3\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"80%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838},\"obfuscated_coordinates\":{\"latitude\":28.339303,\"longitude\":-81.47791},\"obfuscation_required\":true},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":true},\"checkin\":{\"24_hour\":\"24-hour check-in\",\"begin_time\":\"3:00 PM\",\"end_time\":\"11:00 PM\",\"instructions\":\"Extra-person charges may apply and vary depending on hotel policy. &lt;br />Government-issued photo identification and a credit card or cash deposit are required at check-in for incidental charges. &lt;br />Special requests are subject to availability upon check-in and may incur additional charges. Special requests cannot be guaranteed. &lt;br />\",\"special_instructions\":\"There is no front desk at this property. To make arrangements for check-in please contact the property ahead of time using the information on the booking confirmation.\",\"min_age\":18},\"checkout\":{\"time\":\"11:00 AM\"},\"fees\":{\"mandatory\":\"<p>You'll be asked to pay the following charges at the hotel:</p> <ul><li>Deposit: USD 50 per day</li><li>Resort fee: USD 29.12 per accommodation, per night</li></ul> The hotel resort fee includes:<ul><li>Fitness center access</li><li>Internet access</li><li>Phone calls</li><li>Additional inclusions</li></ul> <p>We have included all charges provided to us by the property. However, charges can vary, for example, based on length of stay or the room you book. </p>\",\"optional\":\"Fee for in-room wireless Internet: USD 15 per hour (rates may vary)</li> <li>Airport shuttle fee: USD 350 per vehicle (one way)</li>           <li>Rollaway bed fee: USD 175 per night</li>\"},\"policies\":{\"know_before_you_go\":\"Reservations are required for massage services and spa treatments. Reservations can be made by contacting the hotel prior to arrival, using the contact information on the booking confirmation. </li><li>Children 11 years old and younger stay free when occupying the parent or guardian's room, using existing bedding. </li><li>Only registered guests are allowed in the guestrooms. </li> <li>Some facilities may have restricted access. Guests can contact the property for details using the contact information on the booking confirmation. </li> </ul>\"},\"attributes\":{\"general\":{\"2549\":{\"id\":\"2549\",\"name\":\"No elevators\"},\"3357\":{\"id\":\"3357\",\"name\":\"Caters to adults only\"}},\"pets\":{\"51\":{\"id\":\"51\",\"name\":\"Pets allowed\"},\"2809\":{\"id\":\"2809\",\"name\":\"Dogs only\"},\"3321\":{\"id\":\"3321\",\"name\":\"Pet maximum weight in kg is - 24\",\"value\":24}}},\"amenities\":{\"9\":{\"id\":\"9\",\"name\":\"Fitness facilities\"},\"2820\":{\"id\":\"2820\",\"name\":\"Number of indoor pools - 10\",\"value\":10}},\"images\":[{\"caption\":\"Featured Image\",\"hero_image\":true,\"category\":3,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}}}],\"onsite_payments\":{\"currency\":\"USD\",\"types\":{\"171\":{\"id\":\"171\",\"name\":\"American Express\"}}},\"rooms\":{\"224829\":{\"id\":\"224829\",\"name\":\"Single Room\",\"descriptions\":{\"overview\":\"<strong>2 Twin Beds</strong><br />269-sq-foot (25-sq-meter) room with mountain views<br /><br /><b>Internet</b> - Free WiFi <br /> <b>Entertainment</b> - Flat-screen TV with cable channels<br /><b>Food & Drink</b> - Refrigerator, coffee/tea maker,  room service, and free bottled water<br /><b>Sleep</b> - Premium bedding <br /><b>Bathroom</b> - Private bathroom, shower, bathrobes, and free toiletries<br /><b>Practical</b> - Safe and desk; cribs/infant beds available on request<br /><b>Comfort</b> - Climate-controlled air conditioning and daily housekeeping<br />Non-Smoking<br />\"},\"amenities\":{\"130\":{\"id\":\"130\",\"name\":\"Refrigerator\"},\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"}},\"images\":[{\"hero_image\":true,\"category\":21001,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}},\"caption\":\"Guestroom\"}],\"bed_groups\":{\"37321\":{\"id\":\"37321\",\"description\":\"1 King Bed\",\"configuration\":[{\"type\":\"KingBed\",\"size\":\"King\",\"quantity\":1}]}},\"area\":{\"square_meters\":17},\"views\":{\"4134\":{\"id\":\"4134\",\"name\":\"City view\"}},\"occupancy\":{\"max_allowed\":{\"total\":3,\"children\":2,\"adults\":3},\"age_categories\":{\"ChildAgeA\":{\"name\":\"ChildAgeA\",\"minimum_age\":3}}}}},\"rates\":{\"333abc\":{\"id\":\"333abc\",\"amenities\":{\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"},\"2104\":{\"id\":\"2104\",\"name\":\"Full Breakfast\"}},\"special_offer_description\":\"<strong>Breakfast for 2</strong> - Rate includes the following:\\r\\n<ul><li>Accommodations as selected</li>\\r\\n<li>Breakfast in hotel restaurant for up to 2 adults and children 12 years old and under registered in the same room</li>\\r\\n</ul><em>Must book this rate plan to receive benefits. Details provided at check-in. Taxes and gratuity may not be included. No refunds for any unused portion of offer. Offer subject to availability. Offer is not valid with groups/conventions and may not be combined with other promotional offers. Other restrictions and blackout dates may apply.</em>\\r\\n\"}},\"dates\":{\"added\":\"1998-07-20T05:00:00.000Z\",\"updated\":\"2018-03-22T13:33:17.000Z\"},\"descriptions\":{\"amenities\":\"Don't miss out on the many recreational opportunities, including an outdoor pool, a sauna, and a fitness center. Additional features at this hotel include complimentary wireless Internet access, concierge services, and an arcade/game room.\",\"dining\":\"Grab a bite at one of the hotel's 3 restaurants, or stay in and take advantage of 24-hour room service. Quench your thirst with your favorite drink at a bar/lounge. Buffet breakfasts are available daily for a fee.\",\"renovations\":\"During renovations, the hotel will make every effort to minimize noise and disturbance.  The property will be renovating from 08 May 2017 to 18 May 2017 (completion date subject to change). The following areas are affected:  <ul><li>Fitness facilities</li></ul>\",\"national_ratings\":\"For the benefit of our customers, we have provided a rating based on our rating system.\",\"business_amenities\":\"Featured amenities include complimentary wired Internet access, a 24-hour business center, and limo/town car service. Event facilities at this hotel consist of a conference center and meeting rooms. Free self parking is available onsite.\",\"rooms\":\"Make yourself at home in one of the 334 air-conditioned rooms featuring LCD televisions. Complimentary wired and wireless Internet access keeps you connected, and satellite programming provides entertainment. Private bathrooms with separate bathtubs and showers feature deep soaking bathtubs and complimentary toiletries. Conveniences include phones, as well as safes and desks.\",\"attractions\":\"Distances are calculated in a straight line from the property's location to the point of interest or attraction, and may not reflect actual travel distance. <br /><br /> Distances are displayed to the nearest 0.1 mile and kilometer. <p>Sogo Department Store - 0.7 km / 0.4 mi <br />National Museum of Natural Science - 1.1 km / 0.7 mi <br />Shr-Hwa International Tower - 1.4 km / 0.8 mi <br />Shinkong Mitsukoshi Department Store - 1.5 km / 0.9 mi <br />Taichung Metropolitan Opera House - 1.7 km / 1 mi <br />Tiger City Mall - 1.8 km / 1.1 mi <br />Maple Garden Park - 1.9 km / 1.2 mi <br />National Museum of Fine Arts - 2.1 km / 1.3 mi <br />Feng Chia University - 2.4 km / 1.5 mi <br />Bao An Temple - 2.5 km / 1.6 mi <br />Fengjia Night Market - 2.5 km / 1.6 mi <br />Zhonghua Night Market - 2.7 km / 1.7 mi <br />Chonglun Park - 2.9 km / 1.8 mi <br />Wan He Temple - 2.9 km / 1.8 mi <br />Chungyo Department Store - 3.1 km / 1.9 mi <br /></p><p>The nearest airports are:<br />Taichung (RMQ) - 12 km / 7.5 mi<br />Taipei (TPE-Taoyuan Intl.) - 118.3 km / 73.5 mi<br />Taipei (TSA-Songshan) - 135.5 km / 84.2 mi<br /></p><p></p>\",\"location\":\"This 4-star hotel is within close proximity of Shr-Hwa International Tower and Shinkong Mitsukoshi Department Store.  A stay at Tempus Hotel Taichung places you in the heart of Taichung, convenient to Sogo Department Store and National Museum of Natural Science.\",\"headline\":\"Near National Museum of Natural Science\",\"general\":\"General description\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"airports\":{\"preferred\":{\"iata_airport_code\":\"SGF\"}},\"themes\":{\"2337\":{\"id\":\"2337\",\"name\":\"Luxury Hotel\"},\"2341\":{\"id\":\"2341\",\"name\":\"Spa Hotel\"}},\"all_inclusive\":{\"all_rate_plans\":true,\"some_rate_plans\":false,\"details\":\"<p>This resort is all-inclusive. Onsite food and beverages are included in the room price (some restrictions may apply). </p><p><strong>Activities and facilities/equipment</strong><br />Land activities<ul><li>Fitness facilities</li></ul><br />Lessons/classes/games <ul><li>Pilates</li><li>Yoga</li></ul></p><p><strong>Entertainment</strong><ul><li>Onsite entertainment and activities</li><li>Onsite live performances</li></ul></p>\"},\"tax_id\":\"CD-012-987-1234-02\",\"chain\":{\"id\":\"-5\",\"name\":\"Hilton Worldwide\"},\"brand\":{\"id\":\"358\",\"name\":\"Hampton Inn\"},\"spoken_languages\":{\"en\":{\"id\":\"en\",\"name\":\"English\"}},\"multi_unit\":true,\"payment_registration_recommended\":true,\"vacation_rental_details\":{\"registry_number\":\"Property Registration Number 123456\",\"private_host\":\"true\",\"property_manager\":{\"name\":\"John Smith\",\"links\":{\"image\":{\"method\":\"GET\",\"href\":\"https://example.com/profile.jpg\"}}},\"rental_agreement\":{\"links\":{\"rental_agreement\":{\"method\":\"GET\",\"href\":\"https:/example.com/rentalconditions.pdf\"}}},\"house_rules\":[\"Children welcome\",\"No pets\",\"No smoking\",\"No parties or events\"],\"amenities\":{\"2859\":{\"id\":\"2859\",\"name\":\"Private pool\"},\"4296\":{\"id\":\"4296\",\"name\":\"Furnished balcony or patio\"}},\"vrbo_srp_id\":\"123.1234567.5678910\",\"listing_id\":\"1234567\",\"listing_number\":\"5678910\",\"listing_source\":\"HOMEAWAY_US\",\"listing_unit\":\"/units/0000/32d82dfa-1a48-45d6-9132-49fdbf1bfc60\"},\"supply_source\":\"vrbo\",\"registry_number\":\"Property Registration Number 123456\"}   ``` 
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return Link
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPropertyContentFileOperation)"))
    fun getPropertyContentFile(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Link = getPropertyContentFileWithResponse(language, supplySource, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Property Content File
     * Returns a link to download all content for all of EPS’s active properties in the requested language. The response includes property-level, room-level and rate-level information.  <br>This file is in JSONL format and is gzipped. The schema of each JSON object in the JSONL file is the same as the schema of each JSON object from the Property Content call.  <br>Example of a JSONL file with 2 properties:   ```   {\"property_id\":\"12345\",\"name\":\"Test Property Name\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":false,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=12345\"},\"fr-FR\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=fr-FR&include=address&property_id=12345\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":48382,\"overall\":\"3.1\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"73%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838}},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":false},\"checkin\":{\"24_hour\":\"24-hour check-in\",\"begin_time\":\"3:00 PM\",\"end_time\":\"11:00 PM\",\"instructions\":\"Extra-person charges may apply and vary depending on hotel policy. &lt;br />Government-issued photo identification and a credit card or cash deposit are required at check-in for incidental charges. &lt;br />Special requests are subject to availability upon check-in and may incur additional charges. Special requests cannot be guaranteed. &lt;br />\",\"special_instructions\":\"There is no front desk at this property. To make arrangements for check-in please contact the property ahead of time using the information on the booking confirmation.\",\"min_age\":18},\"checkout\":{\"time\":\"11:00 AM\"},\"fees\":{\"mandatory\":\"<p>You'll be asked to pay the following charges at the hotel:</p> <ul><li>Deposit: USD 50 per day</li><li>Resort fee: USD 29.12 per accommodation, per night</li></ul> The hotel resort fee includes:<ul><li>Fitness center access</li><li>Internet access</li><li>Phone calls</li><li>Additional inclusions</li></ul> <p>We have included all charges provided to us by the property. However, charges can vary, for example, based on length of stay or the room you book. </p>\",\"optional\":\"Fee for in-room wireless Internet: USD 15 per hour (rates may vary)</li> <li>Airport shuttle fee: USD 350 per vehicle (one way)</li>           <li>Rollaway bed fee: USD 175 per night</li>\"},\"policies\":{\"know_before_you_go\":\"Reservations are required for massage services and spa treatments. Reservations can be made by contacting the hotel prior to arrival, using the contact information on the booking confirmation. </li><li>Children 11 years old and younger stay free when occupying the parent or guardian's room, using existing bedding. </li><li>Only registered guests are allowed in the guestrooms. </li> <li>Some facilities may have restricted access. Guests can contact the property for details using the contact information on the booking confirmation. </li> </ul>\"},\"attributes\":{\"general\":{\"2549\":{\"id\":\"2549\",\"name\":\"No elevators\"},\"3357\":{\"id\":\"3357\",\"name\":\"Caters to adults only\"}},\"pets\":{\"51\":{\"id\":\"51\",\"name\":\"Pets allowed\"},\"2809\":{\"id\":\"2809\",\"name\":\"Dogs only\"},\"3321\":{\"id\":\"3321\",\"name\":\"Pet maximum weight in kg is - 24\",\"value\":24}}},\"amenities\":{\"9\":{\"id\":\"9\",\"name\":\"Fitness facilities\"},\"2820\":{\"id\":\"2820\",\"name\":\"Number of indoor pools - 10\",\"value\":10}},\"images\":[{\"caption\":\"Featured Image\",\"hero_image\":true,\"category\":3,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}}}],\"onsite_payments\":{\"currency\":\"USD\",\"types\":{\"171\":{\"id\":\"171\",\"name\":\"American Express\"}}},\"rooms\":{\"224829\":{\"id\":\"224829\",\"name\":\"Single Room\",\"descriptions\":{\"overview\":\"<strong>2 Twin Beds</strong><br />269-sq-foot (25-sq-meter) room with mountain views<br /><br /><b>Internet</b> - Free WiFi <br /> <b>Entertainment</b> - Flat-screen TV with cable channels<br /><b>Food & Drink</b> - Refrigerator, coffee/tea maker,  room service, and free bottled water<br /><b>Sleep</b> - Premium bedding <br /><b>Bathroom</b> - Private bathroom, shower, bathrobes, and free toiletries<br /><b>Practical</b> - Safe and desk; cribs/infant beds available on request<br /><b>Comfort</b> - Climate-controlled air conditioning and daily housekeeping<br />Non-Smoking<br />\"},\"amenities\":{\"130\":{\"id\":\"130\",\"name\":\"Refrigerator\"},\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"}},\"images\":[{\"hero_image\":true,\"category\":21001,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}},\"caption\":\"Guestroom\"}],\"bed_groups\":{\"37321\":{\"id\":\"37321\",\"description\":\"1 King Bed\",\"configuration\":[{\"type\":\"KingBed\",\"size\":\"King\",\"quantity\":1}]}},\"area\":{\"square_meters\":20,\"square_feet\":215},\"views\":{\"4146\":{\"id\":\"4146\",\"name\":\"Courtyard view\"}},\"occupancy\":{\"max_allowed\":{\"total\":5,\"children\":2,\"adults\":4},\"age_categories\":{\"Adult\":{\"name\":\"Adult\",\"minimum_age\":9}}}}},\"rates\":{\"333abc\":{\"id\":\"333abc\",\"amenities\":{\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"},\"2104\":{\"id\":\"2104\",\"name\":\"Full Breakfast\"}},\"special_offer_description\":\"<strong>Breakfast for 2</strong> - Rate includes the following:\\r\\n<ul><li>Accommodations as selected</li>\\r\\n<li>Breakfast in hotel restaurant for up to 2 adults and children 12 years old and under registered in the same room</li>\\r\\n</ul><em>Must book this rate plan to receive benefits. Details provided at check-in. Taxes and gratuity may not be included. No refunds for any unused portion of offer. Offer subject to availability. Offer is not valid with groups/conventions and may not be combined with other promotional offers. Other restrictions and blackout dates may apply.</em>\\r\\n\"}},\"dates\":{\"added\":\"1998-07-19T05:00:00.000Z\",\"updated\":\"2018-03-22T07:23:14.000Z\"},\"descriptions\":{\"amenities\":\"Don't miss out on the many recreational opportunities, including an outdoor pool, a sauna, and a fitness center. Additional features at this hotel include complimentary wireless Internet access, concierge services, and an arcade/game room.\",\"dining\":\"Grab a bite at one of the hotel's 3 restaurants, or stay in and take advantage of 24-hour room service. Quench your thirst with your favorite drink at a bar/lounge. Buffet breakfasts are available daily for a fee.\",\"renovations\":\"During renovations, the hotel will make every effort to minimize noise and disturbance.  The property will be renovating from 08 May 2017 to 18 May 2017 (completion date subject to change). The following areas are affected:  <ul><li>Fitness facilities</li></ul>\",\"national_ratings\":\"For the benefit of our customers, we have provided a rating based on our rating system.\",\"business_amenities\":\"Featured amenities include complimentary wired Internet access, a 24-hour business center, and limo/town car service. Event facilities at this hotel consist of a conference center and meeting rooms. Free self parking is available onsite.\",\"rooms\":\"Make yourself at home in one of the 334 air-conditioned rooms featuring LCD televisions. Complimentary wired and wireless Internet access keeps you connected, and satellite programming provides entertainment. Private bathrooms with separate bathtubs and showers feature deep soaking bathtubs and complimentary toiletries. Conveniences include phones, as well as safes and desks.\",\"attractions\":\"Distances are calculated in a straight line from the property's location to the point of interest or attraction, and may not reflect actual travel distance. <br /><br /> Distances are displayed to the nearest 0.1 mile and kilometer. <p>Sogo Department Store - 0.7 km / 0.4 mi <br />National Museum of Natural Science - 1.1 km / 0.7 mi <br />Shr-Hwa International Tower - 1.4 km / 0.8 mi <br />Shinkong Mitsukoshi Department Store - 1.5 km / 0.9 mi <br />Taichung Metropolitan Opera House - 1.7 km / 1 mi <br />Tiger City Mall - 1.8 km / 1.1 mi <br />Maple Garden Park - 1.9 km / 1.2 mi <br />National Museum of Fine Arts - 2.1 km / 1.3 mi <br />Feng Chia University - 2.4 km / 1.5 mi <br />Bao An Temple - 2.5 km / 1.6 mi <br />Fengjia Night Market - 2.5 km / 1.6 mi <br />Zhonghua Night Market - 2.7 km / 1.7 mi <br />Chonglun Park - 2.9 km / 1.8 mi <br />Wan He Temple - 2.9 km / 1.8 mi <br />Chungyo Department Store - 3.1 km / 1.9 mi <br /></p><p>The nearest airports are:<br />Taichung (RMQ) - 12 km / 7.5 mi<br />Taipei (TPE-Taoyuan Intl.) - 118.3 km / 73.5 mi<br />Taipei (TSA-Songshan) - 135.5 km / 84.2 mi<br /></p><p></p>\",\"location\":\"This 4-star hotel is within close proximity of Shr-Hwa International Tower and Shinkong Mitsukoshi Department Store.  A stay at Tempus Hotel Taichung places you in the heart of Taichung, convenient to Sogo Department Store and National Museum of Natural Science.\",\"headline\":\"Near National Museum of Natural Science\",\"general\":\"General description\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"airports\":{\"preferred\":{\"iata_airport_code\":\"SGF\"}},\"themes\":{\"2337\":{\"id\":\"2337\",\"name\":\"Luxury Hotel\"},\"2341\":{\"id\":\"2341\",\"name\":\"Spa Hotel\"}},\"all_inclusive\":{\"all_rate_plans\":true,\"some_rate_plans\":false,\"details\":\"<p>This resort is all-inclusive. Onsite food and beverages are included in the room price (some restrictions may apply). </p><p><strong>Activities and facilities/equipment</strong><br />Land activities<ul><li>Fitness facilities</li></ul><br />Lessons/classes/games <ul><li>Pilates</li><li>Yoga</li></ul></p><p><strong>Entertainment</strong><ul><li>Onsite entertainment and activities</li><li>Onsite live performances</li></ul></p>\"},\"tax_id\":\"AB-012-987-1234-01\",\"chain\":{\"id\":\"-6\",\"name\":\"Hyatt Hotels\"},\"brand\":{\"id\":\"2209\",\"name\":\"Hyatt Place\"},\"spoken_languages\":{\"vi\":{\"id\":\"vi\",\"name\":\"Vietnamese\"}},\"multi_unit\":true,\"payment_registration_recommended\":false,\"vacation_rental_details\": {\"registry_number\": \"Property Registration Number 123456\",\"private_host\": \"true\",\"property_manager\": {\"name\": \"Victor\",\"links\": {\"image\": {\"method\": \"GET\",\"href\": \"https://test-image/test/test/836f1097-fbcf-43b5-bc02-c8ff6658cb90.c1.jpg\"}}},\"rental_agreement\": {\"links\": {\"rental_agreement\": {\"method\": \"GET\",\"href\": \"https://test-link.test.amazonaws.com/rentalconditions_property_d65e7eb5-4a7c-4a80-a8a3-171999f9f444.pdf\"}}},\"house_rules\": [\"Children welcome\",\"No pets\",\"No smoking\",\"No parties or events\"],\"amenities\": {\"4296\": {\"id\": \"4296\",\"name\": \"Furnished balcony or patio\"},\"2859\": {\"id\": \"2859\",\"name\": \"Private pool\"}},\"vrbo_srp_id\": \"123.1234567.5678910\",\"listing_id\": \"1234567\",\"listing_number\": \"5678910\",\"listing_source\": \"HOMEAWAY_US\",\"listing_unit\": \"/units/0000/32d82dfa-1a48-45d6-9132-49fdbf1bfc60\"},\"supply_source\":\"vrbo\",\"registry_number\":\"Property Registration Number 123456\"}   {\"property_id\":\"67890\",\"name\":\"Test Property Name 2\",\"address\":{\"line_1\":\"123 Main St\",\"line_2\":\"Apt A\",\"city\":\"Springfield\",\"state_province_code\":\"MO\",\"state_province_name\":\"Missouri\",\"postal_code\":\"65804\",\"country_code\":\"US\",\"obfuscation_required\":true,\"localized\":{\"links\":{\"es-ES\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=es-ES&include=address&property_id=67890\"},\"de-DE\":{\"method\":\"GET\",\"href\":\"https://api.ean.com/v3/properties/content?language=de-DE&include=address&property_id=67890\"}}}},\"ratings\":{\"property\":{\"rating\":\"3.5\",\"type\":\"Star\"},\"guest\":{\"count\":7651,\"overall\":\"4.3\",\"cleanliness\":\"4.2\",\"service\":\"1.1\",\"comfort\":\"4.3\",\"condition\":\"1.6\",\"location\":\"4.0\",\"neighborhood\":\"3.4\",\"quality\":\"3.4\",\"value\":\"2.2\",\"amenities\":\"1.4\",\"recommendation_percent\":\"80%\"}},\"location\":{\"coordinates\":{\"latitude\":37.15845,\"longitude\":-93.26838},\"obfuscated_coordinates\":{\"latitude\":28.339303,\"longitude\":-81.47791},\"obfuscation_required\":true},\"phone\":\"1-417-862-0153\",\"fax\":\"1-417-863-7249\",\"category\":{\"id\":\"1\",\"name\":\"Hotel\"},\"rank\":42,\"business_model\":{\"expedia_collect\":true,\"property_collect\":true},\"checkin\":{\"24_hour\":\"24-hour check-in\",\"begin_time\":\"3:00 PM\",\"end_time\":\"11:00 PM\",\"instructions\":\"Extra-person charges may apply and vary depending on hotel policy. &lt;br />Government-issued photo identification and a credit card or cash deposit are required at check-in for incidental charges. &lt;br />Special requests are subject to availability upon check-in and may incur additional charges. Special requests cannot be guaranteed. &lt;br />\",\"special_instructions\":\"There is no front desk at this property. To make arrangements for check-in please contact the property ahead of time using the information on the booking confirmation.\",\"min_age\":18},\"checkout\":{\"time\":\"11:00 AM\"},\"fees\":{\"mandatory\":\"<p>You'll be asked to pay the following charges at the hotel:</p> <ul><li>Deposit: USD 50 per day</li><li>Resort fee: USD 29.12 per accommodation, per night</li></ul> The hotel resort fee includes:<ul><li>Fitness center access</li><li>Internet access</li><li>Phone calls</li><li>Additional inclusions</li></ul> <p>We have included all charges provided to us by the property. However, charges can vary, for example, based on length of stay or the room you book. </p>\",\"optional\":\"Fee for in-room wireless Internet: USD 15 per hour (rates may vary)</li> <li>Airport shuttle fee: USD 350 per vehicle (one way)</li>           <li>Rollaway bed fee: USD 175 per night</li>\"},\"policies\":{\"know_before_you_go\":\"Reservations are required for massage services and spa treatments. Reservations can be made by contacting the hotel prior to arrival, using the contact information on the booking confirmation. </li><li>Children 11 years old and younger stay free when occupying the parent or guardian's room, using existing bedding. </li><li>Only registered guests are allowed in the guestrooms. </li> <li>Some facilities may have restricted access. Guests can contact the property for details using the contact information on the booking confirmation. </li> </ul>\"},\"attributes\":{\"general\":{\"2549\":{\"id\":\"2549\",\"name\":\"No elevators\"},\"3357\":{\"id\":\"3357\",\"name\":\"Caters to adults only\"}},\"pets\":{\"51\":{\"id\":\"51\",\"name\":\"Pets allowed\"},\"2809\":{\"id\":\"2809\",\"name\":\"Dogs only\"},\"3321\":{\"id\":\"3321\",\"name\":\"Pet maximum weight in kg is - 24\",\"value\":24}}},\"amenities\":{\"9\":{\"id\":\"9\",\"name\":\"Fitness facilities\"},\"2820\":{\"id\":\"2820\",\"name\":\"Number of indoor pools - 10\",\"value\":10}},\"images\":[{\"caption\":\"Featured Image\",\"hero_image\":true,\"category\":3,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}}}],\"onsite_payments\":{\"currency\":\"USD\",\"types\":{\"171\":{\"id\":\"171\",\"name\":\"American Express\"}}},\"rooms\":{\"224829\":{\"id\":\"224829\",\"name\":\"Single Room\",\"descriptions\":{\"overview\":\"<strong>2 Twin Beds</strong><br />269-sq-foot (25-sq-meter) room with mountain views<br /><br /><b>Internet</b> - Free WiFi <br /> <b>Entertainment</b> - Flat-screen TV with cable channels<br /><b>Food & Drink</b> - Refrigerator, coffee/tea maker,  room service, and free bottled water<br /><b>Sleep</b> - Premium bedding <br /><b>Bathroom</b> - Private bathroom, shower, bathrobes, and free toiletries<br /><b>Practical</b> - Safe and desk; cribs/infant beds available on request<br /><b>Comfort</b> - Climate-controlled air conditioning and daily housekeeping<br />Non-Smoking<br />\"},\"amenities\":{\"130\":{\"id\":\"130\",\"name\":\"Refrigerator\"},\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"}},\"images\":[{\"hero_image\":true,\"category\":21001,\"links\":{\"70px\":{\"method\":\"GET\",\"href\":\"https://i.travelapi.com/hotels/1000000/20000/15300/15237/bef1b976_t.jpg\"}},\"caption\":\"Guestroom\"}],\"bed_groups\":{\"37321\":{\"id\":\"37321\",\"description\":\"1 King Bed\",\"configuration\":[{\"type\":\"KingBed\",\"size\":\"King\",\"quantity\":1}]}},\"area\":{\"square_meters\":17},\"views\":{\"4134\":{\"id\":\"4134\",\"name\":\"City view\"}},\"occupancy\":{\"max_allowed\":{\"total\":3,\"children\":2,\"adults\":3},\"age_categories\":{\"ChildAgeA\":{\"name\":\"ChildAgeA\",\"minimum_age\":3}}}}},\"rates\":{\"333abc\":{\"id\":\"333abc\",\"amenities\":{\"1234\":{\"id\":\"1234\",\"name\":\"Test Amenity - 200\",\"value\":\"200\"},\"2104\":{\"id\":\"2104\",\"name\":\"Full Breakfast\"}},\"special_offer_description\":\"<strong>Breakfast for 2</strong> - Rate includes the following:\\r\\n<ul><li>Accommodations as selected</li>\\r\\n<li>Breakfast in hotel restaurant for up to 2 adults and children 12 years old and under registered in the same room</li>\\r\\n</ul><em>Must book this rate plan to receive benefits. Details provided at check-in. Taxes and gratuity may not be included. No refunds for any unused portion of offer. Offer subject to availability. Offer is not valid with groups/conventions and may not be combined with other promotional offers. Other restrictions and blackout dates may apply.</em>\\r\\n\"}},\"dates\":{\"added\":\"1998-07-20T05:00:00.000Z\",\"updated\":\"2018-03-22T13:33:17.000Z\"},\"descriptions\":{\"amenities\":\"Don't miss out on the many recreational opportunities, including an outdoor pool, a sauna, and a fitness center. Additional features at this hotel include complimentary wireless Internet access, concierge services, and an arcade/game room.\",\"dining\":\"Grab a bite at one of the hotel's 3 restaurants, or stay in and take advantage of 24-hour room service. Quench your thirst with your favorite drink at a bar/lounge. Buffet breakfasts are available daily for a fee.\",\"renovations\":\"During renovations, the hotel will make every effort to minimize noise and disturbance.  The property will be renovating from 08 May 2017 to 18 May 2017 (completion date subject to change). The following areas are affected:  <ul><li>Fitness facilities</li></ul>\",\"national_ratings\":\"For the benefit of our customers, we have provided a rating based on our rating system.\",\"business_amenities\":\"Featured amenities include complimentary wired Internet access, a 24-hour business center, and limo/town car service. Event facilities at this hotel consist of a conference center and meeting rooms. Free self parking is available onsite.\",\"rooms\":\"Make yourself at home in one of the 334 air-conditioned rooms featuring LCD televisions. Complimentary wired and wireless Internet access keeps you connected, and satellite programming provides entertainment. Private bathrooms with separate bathtubs and showers feature deep soaking bathtubs and complimentary toiletries. Conveniences include phones, as well as safes and desks.\",\"attractions\":\"Distances are calculated in a straight line from the property's location to the point of interest or attraction, and may not reflect actual travel distance. <br /><br /> Distances are displayed to the nearest 0.1 mile and kilometer. <p>Sogo Department Store - 0.7 km / 0.4 mi <br />National Museum of Natural Science - 1.1 km / 0.7 mi <br />Shr-Hwa International Tower - 1.4 km / 0.8 mi <br />Shinkong Mitsukoshi Department Store - 1.5 km / 0.9 mi <br />Taichung Metropolitan Opera House - 1.7 km / 1 mi <br />Tiger City Mall - 1.8 km / 1.1 mi <br />Maple Garden Park - 1.9 km / 1.2 mi <br />National Museum of Fine Arts - 2.1 km / 1.3 mi <br />Feng Chia University - 2.4 km / 1.5 mi <br />Bao An Temple - 2.5 km / 1.6 mi <br />Fengjia Night Market - 2.5 km / 1.6 mi <br />Zhonghua Night Market - 2.7 km / 1.7 mi <br />Chonglun Park - 2.9 km / 1.8 mi <br />Wan He Temple - 2.9 km / 1.8 mi <br />Chungyo Department Store - 3.1 km / 1.9 mi <br /></p><p>The nearest airports are:<br />Taichung (RMQ) - 12 km / 7.5 mi<br />Taipei (TPE-Taoyuan Intl.) - 118.3 km / 73.5 mi<br />Taipei (TSA-Songshan) - 135.5 km / 84.2 mi<br /></p><p></p>\",\"location\":\"This 4-star hotel is within close proximity of Shr-Hwa International Tower and Shinkong Mitsukoshi Department Store.  A stay at Tempus Hotel Taichung places you in the heart of Taichung, convenient to Sogo Department Store and National Museum of Natural Science.\",\"headline\":\"Near National Museum of Natural Science\",\"general\":\"General description\"},\"statistics\":{\"52\":{\"id\":\"52\",\"name\":\"Total number of rooms - 820\",\"value\":\"820\"},\"54\":{\"id\":\"54\",\"name\":\"Number of floors - 38\",\"value\":\"38\"}},\"airports\":{\"preferred\":{\"iata_airport_code\":\"SGF\"}},\"themes\":{\"2337\":{\"id\":\"2337\",\"name\":\"Luxury Hotel\"},\"2341\":{\"id\":\"2341\",\"name\":\"Spa Hotel\"}},\"all_inclusive\":{\"all_rate_plans\":true,\"some_rate_plans\":false,\"details\":\"<p>This resort is all-inclusive. Onsite food and beverages are included in the room price (some restrictions may apply). </p><p><strong>Activities and facilities/equipment</strong><br />Land activities<ul><li>Fitness facilities</li></ul><br />Lessons/classes/games <ul><li>Pilates</li><li>Yoga</li></ul></p><p><strong>Entertainment</strong><ul><li>Onsite entertainment and activities</li><li>Onsite live performances</li></ul></p>\"},\"tax_id\":\"CD-012-987-1234-02\",\"chain\":{\"id\":\"-5\",\"name\":\"Hilton Worldwide\"},\"brand\":{\"id\":\"358\",\"name\":\"Hampton Inn\"},\"spoken_languages\":{\"en\":{\"id\":\"en\",\"name\":\"English\"}},\"multi_unit\":true,\"payment_registration_recommended\":true,\"vacation_rental_details\":{\"registry_number\":\"Property Registration Number 123456\",\"private_host\":\"true\",\"property_manager\":{\"name\":\"John Smith\",\"links\":{\"image\":{\"method\":\"GET\",\"href\":\"https://example.com/profile.jpg\"}}},\"rental_agreement\":{\"links\":{\"rental_agreement\":{\"method\":\"GET\",\"href\":\"https:/example.com/rentalconditions.pdf\"}}},\"house_rules\":[\"Children welcome\",\"No pets\",\"No smoking\",\"No parties or events\"],\"amenities\":{\"2859\":{\"id\":\"2859\",\"name\":\"Private pool\"},\"4296\":{\"id\":\"4296\",\"name\":\"Furnished balcony or patio\"}},\"vrbo_srp_id\":\"123.1234567.5678910\",\"listing_id\":\"1234567\",\"listing_number\":\"5678910\",\"listing_source\":\"HOMEAWAY_US\",\"listing_unit\":\"/units/0000/32d82dfa-1a48-45d6-9132-49fdbf1bfc60\"},\"supply_source\":\"vrbo\",\"registry_number\":\"Property Registration Number 123456\"}   ``` 
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Link
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPropertyContentFileOperation)"))
    fun getPropertyContentFileWithResponse(
        language: kotlin.String,
        supplySource: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<Link> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetPropertyContentFileWithResponse(language, supplySource, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Property Guest Reviews
     * <i>Note: Property Guest Reviews are only available if your account is configured for access and all launch requirements have been followed. Please find the launch requirements here [https://support.expediapartnersolutions.com/hc/en-us/articles/360008646799](https://support.expediapartnersolutions.com/hc/en-us/articles/360008646799) and contact your Account Manager for more details.</i>  The response is an individual Guest Reviews object containing multiple guest reviews for the requested active property.  To ensure you always show the latest guest reviews, this call should be made whenever a customer looks at the details for a specific property.
     * @param operation [GetPropertyGuestReviewsOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type GuestReviews
     */
    fun execute(operation: GetPropertyGuestReviewsOperation): Response<GuestReviews> = execute<Nothing, GuestReviews>(operation)

    /**
     * Property Guest Reviews
     * <i>Note: Property Guest Reviews are only available if your account is configured for access and all launch requirements have been followed. Please find the launch requirements here [https://support.expediapartnersolutions.com/hc/en-us/articles/360008646799](https://support.expediapartnersolutions.com/hc/en-us/articles/360008646799) and contact your Account Manager for more details.</i>  The response is an individual Guest Reviews object containing multiple guest reviews for the requested active property.  To ensure you always show the latest guest reviews, this call should be made whenever a customer looks at the details for a specific property.
     * @param operation [GetPropertyGuestReviewsOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type GuestReviews
     */
    fun executeAsync(operation: GetPropertyGuestReviewsOperation): CompletableFuture<Response<GuestReviews>> = executeAsync<Nothing, GuestReviews>(operation)

    private suspend inline fun kgetPropertyGuestReviewsWithResponse(
        propertyId: kotlin.String,
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        filter: kotlin.collections.List<
            GetPropertyGuestReviewsOperationParams.Filter
        >? =
            null,
        tripReason: kotlin.collections.List<
            GetPropertyGuestReviewsOperationParams.TripReason
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<GuestReviews> {
        val params =
            GetPropertyGuestReviewsOperationParams(
                propertyId = propertyId,
                customerSessionId = customerSessionId,
                language = language,
                filter = filter,
                tripReason = tripReason,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetPropertyGuestReviewsOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Property Guest Reviews
     * <i>Note: Property Guest Reviews are only available if your account is configured for access and all launch requirements have been followed. Please find the launch requirements here [https://support.expediapartnersolutions.com/hc/en-us/articles/360008646799](https://support.expediapartnersolutions.com/hc/en-us/articles/360008646799) and contact your Account Manager for more details.</i>  The response is an individual Guest Reviews object containing multiple guest reviews for the requested active property.  To ensure you always show the latest guest reviews, this call should be made whenever a customer looks at the details for a specific property.
     * @param propertyId Expedia Property ID.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param filter Single filter value. Send multiple instances of this parameter to request multiple filters. * `language` - Filters reviews to only those that match the specified `language` parameter value. Without   this filter, the matching language will be preferred, but other language results can be returned.   Specifying this filter could produce an error when there are no matching results.  (optional)
     * @param tripReason Desired reason provided for the reviewer's trip that you wish to display. This parameter can be supplied multiple times with different values, which will include reviews that match any of the requested trip reasons.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return GuestReviews
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPropertyGuestReviewsOperation)"))
    fun getPropertyGuestReviews(
        propertyId: kotlin.String,
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        filter: kotlin.collections.List<
            GetPropertyGuestReviewsOperationParams.Filter
        >? =
            null,
        tripReason: kotlin.collections.List<
            GetPropertyGuestReviewsOperationParams.TripReason
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): GuestReviews = getPropertyGuestReviewsWithResponse(propertyId, language, customerSessionId, filter, tripReason, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Property Guest Reviews
     * <i>Note: Property Guest Reviews are only available if your account is configured for access and all launch requirements have been followed. Please find the launch requirements here [https://support.expediapartnersolutions.com/hc/en-us/articles/360008646799](https://support.expediapartnersolutions.com/hc/en-us/articles/360008646799) and contact your Account Manager for more details.</i>  The response is an individual Guest Reviews object containing multiple guest reviews for the requested active property.  To ensure you always show the latest guest reviews, this call should be made whenever a customer looks at the details for a specific property.
     * @param propertyId Expedia Property ID.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param filter Single filter value. Send multiple instances of this parameter to request multiple filters. * `language` - Filters reviews to only those that match the specified `language` parameter value. Without   this filter, the matching language will be preferred, but other language results can be returned.   Specifying this filter could produce an error when there are no matching results.  (optional)
     * @param tripReason Desired reason provided for the reviewer's trip that you wish to display. This parameter can be supplied multiple times with different values, which will include reviews that match any of the requested trip reasons.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type GuestReviews
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetPropertyGuestReviewsOperation)"))
    fun getPropertyGuestReviewsWithResponse(
        propertyId: kotlin.String,
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        filter: kotlin.collections.List<
            GetPropertyGuestReviewsOperationParams.Filter
        >? =
            null,
        tripReason: kotlin.collections.List<
            GetPropertyGuestReviewsOperationParams.TripReason
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<GuestReviews> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetPropertyGuestReviewsWithResponse(propertyId, language, customerSessionId, filter, tripReason, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Rate Amenities Reference
     * Returns a complete collection of rate amenities available in the Rapid API.
     * @param operation [GetRateAmenitiesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    fun execute(operation: GetRateAmenitiesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, AmenityReference>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, AmenityReference>>(operation)

    /**
     * Rate Amenities Reference
     * Returns a complete collection of rate amenities available in the Rapid API.
     * @param operation [GetRateAmenitiesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    fun executeAsync(operation: GetRateAmenitiesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, AmenityReference>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, AmenityReference>>(operation)

    private suspend inline fun kgetRateAmenitiesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, AmenityReference>> {
        val params =
            GetRateAmenitiesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetRateAmenitiesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Rate Amenities Reference
     * Returns a complete collection of rate amenities available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRateAmenitiesReferenceOperation)"))
    fun getRateAmenitiesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, AmenityReference> = getRateAmenitiesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Rate Amenities Reference
     * Returns a complete collection of rate amenities available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRateAmenitiesReferenceOperation)"))
    fun getRateAmenitiesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, AmenityReference>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetRateAmenitiesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Region
     * Returns the geographic definition and property mappings for the requested Region ID. The response is a single JSON formatted region object.
     * @param operation [GetRegionOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Region
     */
    fun execute(operation: GetRegionOperation): Response<Region> = execute<Nothing, Region>(operation)

    /**
     * Region
     * Returns the geographic definition and property mappings for the requested Region ID. The response is a single JSON formatted region object.
     * @param operation [GetRegionOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type Region
     */
    fun executeAsync(operation: GetRegionOperation): CompletableFuture<Response<Region>> = executeAsync<Nothing, Region>(operation)

    private suspend inline fun kgetRegionWithResponse(
        regionId: kotlin.String,
        language: kotlin.String,
        include: kotlin.collections.List<
            GetRegionOperationParams.Include
        >,
        customerSessionId: kotlin.String? =
            null,
        supplySource: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<Region> {
        val params =
            GetRegionOperationParams(
                regionId = regionId,
                customerSessionId = customerSessionId,
                language = language,
                include = include,
                supplySource = supplySource,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetRegionOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Region
     * Returns the geographic definition and property mappings for the requested Region ID. The response is a single JSON formatted region object.
     * @param regionId ID of the region to retrieve.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. See [https://www.w3.org/International/articles/language-tags/](https://www.w3.org/International/articles/language-tags/)  Language Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/language-options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param include Options for which content to return in the response. This parameter can be supplied multiple times with different values. The value must be lower case.   * details - Include the metadata, coordinates and full hierarchy of the region.   * property_ids - Include the list of property IDs within the bounding polygon of the region.   * property_ids_expanded - Include the list of property IDs within the bounding polygon of the region and property IDs from the surrounding area if minimal properties are within the region.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param supplySource Options for which supply source you would like returned in the geography response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return Region
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRegionOperation)"))
    fun getRegion(
        regionId: kotlin.String,
        language: kotlin.String,
        include: kotlin.collections.List<
            GetRegionOperationParams.Include
        >,
        customerSessionId: kotlin.String? =
            null,
        supplySource: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Region = getRegionWithResponse(regionId, language, include, customerSessionId, supplySource, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Region
     * Returns the geographic definition and property mappings for the requested Region ID. The response is a single JSON formatted region object.
     * @param regionId ID of the region to retrieve.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. See [https://www.w3.org/International/articles/language-tags/](https://www.w3.org/International/articles/language-tags/)  Language Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/language-options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param include Options for which content to return in the response. This parameter can be supplied multiple times with different values. The value must be lower case.   * details - Include the metadata, coordinates and full hierarchy of the region.   * property_ids - Include the list of property IDs within the bounding polygon of the region.   * property_ids_expanded - Include the list of property IDs within the bounding polygon of the region and property IDs from the surrounding area if minimal properties are within the region.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param supplySource Options for which supply source you would like returned in the geography response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Region
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRegionOperation)"))
    fun getRegionWithResponse(
        regionId: kotlin.String,
        language: kotlin.String,
        include: kotlin.collections.List<
            GetRegionOperationParams.Include
        >,
        customerSessionId: kotlin.String? =
            null,
        supplySource: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<Region> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetRegionWithResponse(regionId, language, include, customerSessionId, supplySource, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Regions
     * Returns the geographic definition and property mappings of regions matching the specified parameters.<br><br> To request all regions in the world, omit the `ancestor` query parameter. To request all regions in a specific continent, country or other level, specify the ID of that region as the `ancestor`. Refer to the list of [top level regions](https://developers.expediagroup.com/docs/rapid/lodging/geography/geography-reference-lists). <br><br> The response is a paginated list of regions. See the `Link` header in the 200 response section.
     * @param operation [GetRegionsOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<Region>
     */
    fun execute(operation: GetRegionsOperation): Response<kotlin.collections.List<Region>> = execute<Nothing, kotlin.collections.List<Region>>(operation)

    /**
     * Regions
     * Returns the geographic definition and property mappings of regions matching the specified parameters.<br><br> To request all regions in the world, omit the `ancestor` query parameter. To request all regions in a specific continent, country or other level, specify the ID of that region as the `ancestor`. Refer to the list of [top level regions](https://developers.expediagroup.com/docs/rapid/lodging/geography/geography-reference-lists). <br><br> The response is a paginated list of regions. See the `Link` header in the 200 response section.
     * @param operation [GetRegionsOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.List<Region>
     */
    fun executeAsync(operation: GetRegionsOperation): CompletableFuture<Response<kotlin.collections.List<Region>>> = executeAsync<Nothing, kotlin.collections.List<Region>>(operation)

    private suspend inline fun kgetRegionsWithResponse(
        include: kotlin.collections.List<
            GetRegionsOperationParams.Include
        >,
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        ancestorId: kotlin.String? =
            null,
        area: kotlin.String? =
            null,
        countryCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        countrySubdivisionCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        iataLocationCode: kotlin.String? =
            null,
        limit: java.math.BigDecimal? =
            null,
        supplySource: kotlin.String? =
            null,
        type: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.List<Region>> {
        val params =
            GetRegionsOperationParams(
                customerSessionId = customerSessionId,
                include = include,
                language = language,
                ancestorId = ancestorId,
                area = area,
                countryCode = countryCode,
                countrySubdivisionCode = countrySubdivisionCode,
                iataLocationCode = iataLocationCode,
                limit = limit,
                supplySource = supplySource,
                type = type,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetRegionsOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Regions
     * Returns the geographic definition and property mappings of regions matching the specified parameters.<br><br> To request all regions in the world, omit the `ancestor` query parameter. To request all regions in a specific continent, country or other level, specify the ID of that region as the `ancestor`. Refer to the list of [top level regions](https://developers.expediagroup.com/docs/rapid/lodging/geography/geography-reference-lists). <br><br> The response is a paginated list of regions. See the `Link` header in the 200 response section.
     * @param include Options for which content to return in the response. This parameter can be supplied multiple times with different values. The standard and details options cannot be requested together. The value must be lower case.   * standard - Include the metadata and basic hierarchy of each region.   * details - Include the metadata, coordinates and full hierarchy of each region.   * property_ids - Include the list of property IDs within the bounding polygon of each region.   * property_ids_expanded - Include the list of property IDs within the bounding polygon of each region and     property IDs from the surrounding area if minimal properties are within the region.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param ancestorId Search for regions whose ancestors include the requested ancestor region ID. Refer to the list of [top level regions](https://developers.expediagroup.com/docs/rapid/lodging/geography/geography-reference-lists).  (optional)
     * @param area Filter the results to regions that intersect with a specified area.<br><br> The area may be defined in one of two ways:   * radius,region_id   * radius,latitude,longitude  Radius combined with region id would search an area that extends the number of kilometers out from the boundaries of the region in all directions.<br> Radius combined with a single point, specified by a latitude, longitude pair would search an area in a circle with the specified radius and the point as the center.<br> Radius should be specified in non-negative whole kilometers, decimals will return an error. A radius of 0 is allowed.<br> When specifying the area parameter, there will be a limit of 100 results, which can be narrowed further by the limit parameter.<br> Due to the number of results, unless `point_of_interest` is specified as the only type, regions of type `point_of_interest` will not be included in a request that filters to an area.<br><br> An example use case would be searching for the closest 3 airports within 50 kilometers of a specified point.<br>   `&type=airport&limit=3&area=50,37.227924,-93.310036`  (optional)
     * @param countryCode Filter the results to a specified ISO 3166-1 alpha-2 country code.  For more information see: [https://www.iso.org/obp/ui/#search/code/](https://www.iso.org/obp/ui/#search/code/)  (optional)
     * @param countrySubdivisionCode Filter the results down to only the ISO 3166-2 country subdivision. (optional)
     * @param iataLocationCode Search for regions by the requested 3-character IATA location code, which will apply to both iata_airport_code and iata_airport_metro_code. The code must be upper case.  (optional)
     * @param limit Limit the number of results returned. Using the area parameter will impose a max value of 100 for this whether specified or not.  (optional)
     * @param supplySource Options for which supply source you would like returned in the geography response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.  (optional)
     * @param type Filter the results to a specified region type.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.List<Region>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRegionsOperation)"))
    fun getRegions(
        include: kotlin.collections.List<
            GetRegionsOperationParams.Include
        >,
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        ancestorId: kotlin.String? =
            null,
        area: kotlin.String? =
            null,
        countryCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        countrySubdivisionCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        iataLocationCode: kotlin.String? =
            null,
        limit: java.math.BigDecimal? =
            null,
        supplySource: kotlin.String? =
            null,
        type: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.List<Region> =
        getRegionsWithResponse(
            include,
            language,
            customerSessionId,
            ancestorId,
            area,
            countryCode,
            countrySubdivisionCode,
            iataLocationCode,
            limit,
            supplySource,
            type,
            billingTerms,
            partnerPointOfSale,
            paymentTerms,
            platformName
        ).data

    /**
     * Regions
     * Returns the geographic definition and property mappings of regions matching the specified parameters.<br><br> To request all regions in the world, omit the `ancestor` query parameter. To request all regions in a specific continent, country or other level, specify the ID of that region as the `ancestor`. Refer to the list of [top level regions](https://developers.expediagroup.com/docs/rapid/lodging/geography/geography-reference-lists). <br><br> The response is a paginated list of regions. See the `Link` header in the 200 response section.
     * @param include Options for which content to return in the response. This parameter can be supplied multiple times with different values. The standard and details options cannot be requested together. The value must be lower case.   * standard - Include the metadata and basic hierarchy of each region.   * details - Include the metadata, coordinates and full hierarchy of each region.   * property_ids - Include the list of property IDs within the bounding polygon of each region.   * property_ids_expanded - Include the list of property IDs within the bounding polygon of each region and     property IDs from the surrounding area if minimal properties are within the region.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param ancestorId Search for regions whose ancestors include the requested ancestor region ID. Refer to the list of [top level regions](https://developers.expediagroup.com/docs/rapid/lodging/geography/geography-reference-lists).  (optional)
     * @param area Filter the results to regions that intersect with a specified area.<br><br> The area may be defined in one of two ways:   * radius,region_id   * radius,latitude,longitude  Radius combined with region id would search an area that extends the number of kilometers out from the boundaries of the region in all directions.<br> Radius combined with a single point, specified by a latitude, longitude pair would search an area in a circle with the specified radius and the point as the center.<br> Radius should be specified in non-negative whole kilometers, decimals will return an error. A radius of 0 is allowed.<br> When specifying the area parameter, there will be a limit of 100 results, which can be narrowed further by the limit parameter.<br> Due to the number of results, unless `point_of_interest` is specified as the only type, regions of type `point_of_interest` will not be included in a request that filters to an area.<br><br> An example use case would be searching for the closest 3 airports within 50 kilometers of a specified point.<br>   `&type=airport&limit=3&area=50,37.227924,-93.310036`  (optional)
     * @param countryCode Filter the results to a specified ISO 3166-1 alpha-2 country code.  For more information see: [https://www.iso.org/obp/ui/#search/code/](https://www.iso.org/obp/ui/#search/code/)  (optional)
     * @param countrySubdivisionCode Filter the results down to only the ISO 3166-2 country subdivision. (optional)
     * @param iataLocationCode Search for regions by the requested 3-character IATA location code, which will apply to both iata_airport_code and iata_airport_metro_code. The code must be upper case.  (optional)
     * @param limit Limit the number of results returned. Using the area parameter will impose a max value of 100 for this whether specified or not.  (optional)
     * @param supplySource Options for which supply source you would like returned in the geography response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.  (optional)
     * @param type Filter the results to a specified region type.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<Region>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRegionsOperation)"))
    fun getRegionsWithResponse(
        include: kotlin.collections.List<
            GetRegionsOperationParams.Include
        >,
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        ancestorId: kotlin.String? =
            null,
        area: kotlin.String? =
            null,
        countryCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        countrySubdivisionCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        iataLocationCode: kotlin.String? =
            null,
        limit: java.math.BigDecimal? =
            null,
        supplySource: kotlin.String? =
            null,
        type: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.List<Region>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetRegionsWithResponse(
                    include,
                    language,
                    customerSessionId,
                    ancestorId,
                    area,
                    countryCode,
                    countrySubdivisionCode,
                    iataLocationCode,
                    limit,
                    supplySource,
                    type,
                    billingTerms,
                    partnerPointOfSale,
                    paymentTerms,
                    platformName
                )
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    @JvmOverloads
    fun getPaginator(operation: GetRegionsOperation): ResponsePaginator<kotlin.collections.List<Region>> {
        val response = execute(operation)
        return ResponsePaginator(this, response, emptyList()) { it.body<kotlin.collections.List<Region>>() }
    }

    @JvmOverloads
    @Deprecated("Use getPaginator method instead", ReplaceWith("getPaginator(operation: GetRegionsOperation)"))
    fun getRegionsPaginator(
        include: kotlin.collections.List<
            GetRegionsOperationParams.Include
        >,
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        ancestorId: kotlin.String? =
            null,
        area: kotlin.String? =
            null,
        countryCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        countrySubdivisionCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        iataLocationCode: kotlin.String? =
            null,
        limit: java.math.BigDecimal? =
            null,
        supplySource: kotlin.String? =
            null,
        type: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Paginator<kotlin.collections.List<Region>> {
        val response =
            getRegionsWithResponse(
                include,
                language,
                customerSessionId,
                ancestorId,
                area,
                countryCode,
                countrySubdivisionCode,
                iataLocationCode,
                limit,
                supplySource,
                type,
                billingTerms,
                partnerPointOfSale,
                paymentTerms,
                platformName
            )
        return Paginator(this, response, emptyList()) { it.body<kotlin.collections.List<Region>>() }
    }

    @JvmOverloads
    @Deprecated("Use getPaginator method instead", ReplaceWith("getPaginator(operation: GetRegionsOperation)"))
    fun getRegionsPaginatorWithResponse(
        include: kotlin.collections.List<
            GetRegionsOperationParams.Include
        >,
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        ancestorId: kotlin.String? =
            null,
        area: kotlin.String? =
            null,
        countryCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        countrySubdivisionCode: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        iataLocationCode: kotlin.String? =
            null,
        limit: java.math.BigDecimal? =
            null,
        supplySource: kotlin.String? =
            null,
        type: kotlin.collections.List<
            kotlin.String
        >? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): ResponsePaginator<kotlin.collections.List<Region>> {
        val response =
            getRegionsWithResponse(
                include,
                language,
                customerSessionId,
                ancestorId,
                area,
                countryCode,
                countrySubdivisionCode,
                iataLocationCode,
                limit,
                supplySource,
                type,
                billingTerms,
                partnerPointOfSale,
                paymentTerms,
                platformName
            )
        return ResponsePaginator(this, response, emptyList()) { it.body<kotlin.collections.List<Region>>() }
    }

    /**
     * Search for and retrieve Bookings with Affiliate Reference Id
     * This can be called directly without a token when an affiliate reference id is provided. It returns details about bookings associated with an affiliate reference id, along with cancel links to cancel the bookings.  <i>Note: Newly created itineraries may sometimes have a small delay between the time of creation and the time that the itinerary can be retrieved. If you receive no results while trying to search for an itinerary that was successfully created, or if you receive a response with two fields, namely, `itinerary_id` and `creation_date_time`, then please wait a few minutes before trying to search for the itinerary again.</i>
     * @param operation [GetReservationOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<Itinerary>
     */
    fun execute(operation: GetReservationOperation): Response<kotlin.collections.List<Itinerary>> = execute<Nothing, kotlin.collections.List<Itinerary>>(operation)

    /**
     * Search for and retrieve Bookings with Affiliate Reference Id
     * This can be called directly without a token when an affiliate reference id is provided. It returns details about bookings associated with an affiliate reference id, along with cancel links to cancel the bookings.  <i>Note: Newly created itineraries may sometimes have a small delay between the time of creation and the time that the itinerary can be retrieved. If you receive no results while trying to search for an itinerary that was successfully created, or if you receive a response with two fields, namely, `itinerary_id` and `creation_date_time`, then please wait a few minutes before trying to search for the itinerary again.</i>
     * @param operation [GetReservationOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.List<Itinerary>
     */
    fun executeAsync(operation: GetReservationOperation): CompletableFuture<Response<kotlin.collections.List<Itinerary>>> = executeAsync<Nothing, kotlin.collections.List<Itinerary>>(operation)

    private suspend inline fun kgetReservationWithResponse(
        customerIp: kotlin.String,
        affiliateReferenceId: kotlin.String,
        email: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: GetReservationOperationParams.Test? =
            null,
        include: kotlin.collections.List<
            GetReservationOperationParams.Include
        >? =
            null
    ): Response<kotlin.collections.List<Itinerary>> {
        val params =
            GetReservationOperationParams(
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                affiliateReferenceId = affiliateReferenceId,
                email = email,
                include = include
            )

        val operation =
            GetReservationOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Search for and retrieve Bookings with Affiliate Reference Id
     * This can be called directly without a token when an affiliate reference id is provided. It returns details about bookings associated with an affiliate reference id, along with cancel links to cancel the bookings.  <i>Note: Newly created itineraries may sometimes have a small delay between the time of creation and the time that the itinerary can be retrieved. If you receive no results while trying to search for an itinerary that was successfully created, or if you receive a response with two fields, namely, `itinerary_id` and `creation_date_time`, then please wait a few minutes before trying to search for the itinerary again.</i>
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param affiliateReferenceId The affilliate reference id value. This field supports a maximum of 28 characters.
     * @param email Email associated with the booking. Special characters in the local part or domain should be encoded.<br>
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The retrieve call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `degraded_response` - Requires valid test booking * `service_unavailable` * `internal_server_error`  (optional)
     * @param include Options for which information to return in the response. The value must be lower case. * `history` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `other` in the response.   * `history_v2` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `supplier` in the response. See the [Itinerary history](https://developers.expediagroup.com/docs/rapid/lodging/manage-booking/itinerary-history#overview) for details.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.List<Itinerary>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetReservationOperation)"))
    fun getReservation(
        customerIp: kotlin.String,
        affiliateReferenceId: kotlin.String,
        email: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: GetReservationOperationParams.Test? =
            null,
        include: kotlin.collections.List<
            GetReservationOperationParams.Include
        >? =
            null
    ): kotlin.collections.List<Itinerary> = getReservationWithResponse(customerIp, affiliateReferenceId, email, customerSessionId, test, include).data

    /**
     * Search for and retrieve Bookings with Affiliate Reference Id
     * This can be called directly without a token when an affiliate reference id is provided. It returns details about bookings associated with an affiliate reference id, along with cancel links to cancel the bookings.  <i>Note: Newly created itineraries may sometimes have a small delay between the time of creation and the time that the itinerary can be retrieved. If you receive no results while trying to search for an itinerary that was successfully created, or if you receive a response with two fields, namely, `itinerary_id` and `creation_date_time`, then please wait a few minutes before trying to search for the itinerary again.</i>
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param affiliateReferenceId The affilliate reference id value. This field supports a maximum of 28 characters.
     * @param email Email associated with the booking. Special characters in the local part or domain should be encoded.<br>
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The retrieve call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `degraded_response` - Requires valid test booking * `service_unavailable` * `internal_server_error`  (optional)
     * @param include Options for which information to return in the response. The value must be lower case. * `history` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `other` in the response.   * `history_v2` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `supplier` in the response. See the [Itinerary history](https://developers.expediagroup.com/docs/rapid/lodging/manage-booking/itinerary-history#overview) for details.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.List<Itinerary>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetReservationOperation)"))
    fun getReservationWithResponse(
        customerIp: kotlin.String,
        affiliateReferenceId: kotlin.String,
        email: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: GetReservationOperationParams.Test? =
            null,
        include: kotlin.collections.List<
            GetReservationOperationParams.Include
        >? =
            null
    ): Response<kotlin.collections.List<Itinerary>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetReservationWithResponse(customerIp, affiliateReferenceId, email, customerSessionId, test, include)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Retrieve Booking
     * This API call returns itinerary details and links to resume or cancel the booking. There are two methods to retrieve a booking: * Using the link included in the original Book response, example: https://api.ean.com/v3/itineraries/8955599932111?token=QldfCGlcUA4GXVlSAQ4W * Using the email of the booking. If the email contains special characters, they must be encoded to successfully retrieve the booking. Example: https://api.ean.com/v3/itineraries/8955599932111?email=customer@email.com  <i>Note: Newly created itineraries may sometimes have a small delay between the time of creation and the time that the itinerary can be retrieved. If you receive an error when trying to retrieve an itinerary that was successfully created, or if you receive a response with two fields, namely, `itinerary_id` and `creation_date_time`, then please wait a few minutes before trying to retrieve the itinerary again.</i>
     * @param operation [GetReservationByItineraryIdOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Itinerary
     */
    fun execute(operation: GetReservationByItineraryIdOperation): Response<Itinerary> = execute<Nothing, Itinerary>(operation)

    /**
     * Retrieve Booking
     * This API call returns itinerary details and links to resume or cancel the booking. There are two methods to retrieve a booking: * Using the link included in the original Book response, example: https://api.ean.com/v3/itineraries/8955599932111?token=QldfCGlcUA4GXVlSAQ4W * Using the email of the booking. If the email contains special characters, they must be encoded to successfully retrieve the booking. Example: https://api.ean.com/v3/itineraries/8955599932111?email=customer@email.com  <i>Note: Newly created itineraries may sometimes have a small delay between the time of creation and the time that the itinerary can be retrieved. If you receive an error when trying to retrieve an itinerary that was successfully created, or if you receive a response with two fields, namely, `itinerary_id` and `creation_date_time`, then please wait a few minutes before trying to retrieve the itinerary again.</i>
     * @param operation [GetReservationByItineraryIdOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type Itinerary
     */
    fun executeAsync(operation: GetReservationByItineraryIdOperation): CompletableFuture<Response<Itinerary>> = executeAsync<Nothing, Itinerary>(operation)

    private suspend inline fun kgetReservationByItineraryIdWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: GetReservationByItineraryIdOperationParams.Test? =
            null,
        token: kotlin.String? =
            null,
        email: kotlin.String? =
            null,
        include: kotlin.collections.List<
            GetReservationByItineraryIdOperationParams.Include
        >? =
            null
    ): Response<Itinerary> {
        val params =
            GetReservationByItineraryIdOperationParams(
                itineraryId = itineraryId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token,
                email = email,
                include = include
            )

        val operation =
            GetReservationByItineraryIdOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Retrieve Booking
     * This API call returns itinerary details and links to resume or cancel the booking. There are two methods to retrieve a booking: * Using the link included in the original Book response, example: https://api.ean.com/v3/itineraries/8955599932111?token=QldfCGlcUA4GXVlSAQ4W * Using the email of the booking. If the email contains special characters, they must be encoded to successfully retrieve the booking. Example: https://api.ean.com/v3/itineraries/8955599932111?email=customer@email.com  <i>Note: Newly created itineraries may sometimes have a small delay between the time of creation and the time that the itinerary can be retrieved. If you receive an error when trying to retrieve an itinerary that was successfully created, or if you receive a response with two fields, namely, `itinerary_id` and `creation_date_time`, then please wait a few minutes before trying to retrieve the itinerary again.</i>
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The retrieve call has a test header that can be used to return set responses. Passing standard in the Test header will retrieve a test booking, and passing any of the errors listed below will return a stubbed error response that you can use to test your error handling code. Additionally, refer to the Test Request documentation for more details on how these header values are used. * `standard` - Requires valid test booking. * `degraded_response` - Requires valid test booking * `service_unavailable` * `internal_server_error`  (optional)
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.  (optional)
     * @param email Email associated with the booking. Special characters in the local part or domain should be encoded. (Email is required if the token is not provided the request) <br>  (optional)
     * @param include Options for which information to return in the response. The value must be lower case. * `history` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `other` in the response.   * `history_v2` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `supplier` in the response. See the [Itinerary history](https://developers.expediagroup.com/docs/rapid/lodging/manage-booking/itinerary-history#overview) for details.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return Itinerary
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetReservationByItineraryIdOperation)"))
    fun getReservationByItineraryId(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: GetReservationByItineraryIdOperationParams.Test? =
            null,
        token: kotlin.String? =
            null,
        email: kotlin.String? =
            null,
        include: kotlin.collections.List<
            GetReservationByItineraryIdOperationParams.Include
        >? =
            null
    ): Itinerary = getReservationByItineraryIdWithResponse(customerIp, itineraryId, customerSessionId, test, token, email, include).data

    /**
     * Retrieve Booking
     * This API call returns itinerary details and links to resume or cancel the booking. There are two methods to retrieve a booking: * Using the link included in the original Book response, example: https://api.ean.com/v3/itineraries/8955599932111?token=QldfCGlcUA4GXVlSAQ4W * Using the email of the booking. If the email contains special characters, they must be encoded to successfully retrieve the booking. Example: https://api.ean.com/v3/itineraries/8955599932111?email=customer@email.com  <i>Note: Newly created itineraries may sometimes have a small delay between the time of creation and the time that the itinerary can be retrieved. If you receive an error when trying to retrieve an itinerary that was successfully created, or if you receive a response with two fields, namely, `itinerary_id` and `creation_date_time`, then please wait a few minutes before trying to retrieve the itinerary again.</i>
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The retrieve call has a test header that can be used to return set responses. Passing standard in the Test header will retrieve a test booking, and passing any of the errors listed below will return a stubbed error response that you can use to test your error handling code. Additionally, refer to the Test Request documentation for more details on how these header values are used. * `standard` - Requires valid test booking. * `degraded_response` - Requires valid test booking * `service_unavailable` * `internal_server_error`  (optional)
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.  (optional)
     * @param email Email associated with the booking. Special characters in the local part or domain should be encoded. (Email is required if the token is not provided the request) <br>  (optional)
     * @param include Options for which information to return in the response. The value must be lower case. * `history` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `other` in the response.   * `history_v2` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `supplier` in the response. See the [Itinerary history](https://developers.expediagroup.com/docs/rapid/lodging/manage-booking/itinerary-history#overview) for details.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Itinerary
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetReservationByItineraryIdOperation)"))
    fun getReservationByItineraryIdWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: GetReservationByItineraryIdOperationParams.Test? =
            null,
        token: kotlin.String? =
            null,
        email: kotlin.String? =
            null,
        include: kotlin.collections.List<
            GetReservationByItineraryIdOperationParams.Include
        >? =
            null
    ): Response<Itinerary> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetReservationByItineraryIdWithResponse(customerIp, itineraryId, customerSessionId, test, token, email, include)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Room Amenities Reference
     * Returns a complete collection of roomo amenities available in the Rapid API.
     * @param operation [GetRoomAmenitiesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    fun execute(operation: GetRoomAmenitiesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, AmenityReference>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, AmenityReference>>(operation)

    /**
     * Room Amenities Reference
     * Returns a complete collection of roomo amenities available in the Rapid API.
     * @param operation [GetRoomAmenitiesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    fun executeAsync(operation: GetRoomAmenitiesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, AmenityReference>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, AmenityReference>>(operation)

    private suspend inline fun kgetRoomAmenitiesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, AmenityReference>> {
        val params =
            GetRoomAmenitiesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetRoomAmenitiesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Room Amenities Reference
     * Returns a complete collection of roomo amenities available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRoomAmenitiesReferenceOperation)"))
    fun getRoomAmenitiesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, AmenityReference> = getRoomAmenitiesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Room Amenities Reference
     * Returns a complete collection of roomo amenities available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, AmenityReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRoomAmenitiesReferenceOperation)"))
    fun getRoomAmenitiesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, AmenityReference>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetRoomAmenitiesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Room Images Reference
     * Returns a complete collection of room images available in the Rapid API.
     * @param operation [GetRoomImagesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, ImageReference>
     */
    fun execute(operation: GetRoomImagesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, ImageReference>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, ImageReference>>(operation)

    /**
     * Room Images Reference
     * Returns a complete collection of room images available in the Rapid API.
     * @param operation [GetRoomImagesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, ImageReference>
     */
    fun executeAsync(operation: GetRoomImagesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, ImageReference>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, ImageReference>>(operation)

    private suspend inline fun kgetRoomImagesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, ImageReference>> {
        val params =
            GetRoomImagesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetRoomImagesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Room Images Reference
     * Returns a complete collection of room images available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, ImageReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRoomImagesReferenceOperation)"))
    fun getRoomImagesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, ImageReference> = getRoomImagesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Room Images Reference
     * Returns a complete collection of room images available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, ImageReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRoomImagesReferenceOperation)"))
    fun getRoomImagesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, ImageReference>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetRoomImagesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Room Views Reference
     * Returns a complete collection of room views available in the Rapid API.
     * @param operation [GetRoomViewsReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, View>
     */
    fun execute(operation: GetRoomViewsReferenceOperation): Response<kotlin.collections.Map<kotlin.String, View>> = execute<Nothing, kotlin.collections.Map<kotlin.String, View>>(operation)

    /**
     * Room Views Reference
     * Returns a complete collection of room views available in the Rapid API.
     * @param operation [GetRoomViewsReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, View>
     */
    fun executeAsync(operation: GetRoomViewsReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, View>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, View>>(operation)

    private suspend inline fun kgetRoomViewsReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, View>> {
        val params =
            GetRoomViewsReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetRoomViewsReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Room Views Reference
     * Returns a complete collection of room views available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, View>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRoomViewsReferenceOperation)"))
    fun getRoomViewsReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, View> = getRoomViewsReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Room Views Reference
     * Returns a complete collection of room views available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, View>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetRoomViewsReferenceOperation)"))
    fun getRoomViewsReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, View>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetRoomViewsReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Spoken Languages Reference
     * Returns a complete collection of spoken languages available in the Rapid API.
     * @param operation [GetSpokenLanguagesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, SpokenLanguage>
     */
    fun execute(operation: GetSpokenLanguagesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, SpokenLanguage>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, SpokenLanguage>>(operation)

    /**
     * Spoken Languages Reference
     * Returns a complete collection of spoken languages available in the Rapid API.
     * @param operation [GetSpokenLanguagesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, SpokenLanguage>
     */
    fun executeAsync(operation: GetSpokenLanguagesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, SpokenLanguage>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, SpokenLanguage>>(operation)

    private suspend inline fun kgetSpokenLanguagesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, SpokenLanguage>> {
        val params =
            GetSpokenLanguagesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetSpokenLanguagesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Spoken Languages Reference
     * Returns a complete collection of spoken languages available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, SpokenLanguage>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetSpokenLanguagesReferenceOperation)"))
    fun getSpokenLanguagesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, SpokenLanguage> = getSpokenLanguagesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Spoken Languages Reference
     * Returns a complete collection of spoken languages available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, SpokenLanguage>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetSpokenLanguagesReferenceOperation)"))
    fun getSpokenLanguagesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, SpokenLanguage>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetSpokenLanguagesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Statistics Reference
     * Returns a complete collection of statistics available in the Rapid API.
     * @param operation [GetStatisticsReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, StatisticReference>
     */
    fun execute(operation: GetStatisticsReferenceOperation): Response<kotlin.collections.Map<kotlin.String, StatisticReference>> =
        execute<Nothing, kotlin.collections.Map<kotlin.String, StatisticReference>>(operation)

    /**
     * Statistics Reference
     * Returns a complete collection of statistics available in the Rapid API.
     * @param operation [GetStatisticsReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, StatisticReference>
     */
    fun executeAsync(operation: GetStatisticsReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, StatisticReference>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, StatisticReference>>(operation)

    private suspend inline fun kgetStatisticsReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, StatisticReference>> {
        val params =
            GetStatisticsReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetStatisticsReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Statistics Reference
     * Returns a complete collection of statistics available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, StatisticReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetStatisticsReferenceOperation)"))
    fun getStatisticsReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, StatisticReference> = getStatisticsReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Statistics Reference
     * Returns a complete collection of statistics available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, StatisticReference>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetStatisticsReferenceOperation)"))
    fun getStatisticsReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, StatisticReference>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetStatisticsReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Themes Reference
     * Returns a complete collection of themes available in the Rapid API.
     * @param operation [GetThemesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, Theme>
     */
    fun execute(operation: GetThemesReferenceOperation): Response<kotlin.collections.Map<kotlin.String, Theme>> = execute<Nothing, kotlin.collections.Map<kotlin.String, Theme>>(operation)

    /**
     * Themes Reference
     * Returns a complete collection of themes available in the Rapid API.
     * @param operation [GetThemesReferenceOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, Theme>
     */
    fun executeAsync(operation: GetThemesReferenceOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, Theme>>> =
        executeAsync<Nothing, kotlin.collections.Map<kotlin.String, Theme>>(operation)

    private suspend inline fun kgetThemesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, Theme>> {
        val params =
            GetThemesReferenceOperationParams(
                customerSessionId = customerSessionId,
                language = language,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            GetThemesReferenceOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Themes Reference
     * Returns a complete collection of themes available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, Theme>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetThemesReferenceOperation)"))
    fun getThemesReference(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, Theme> = getThemesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Themes Reference
     * Returns a complete collection of themes available in the Rapid API.
     * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, Theme>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: GetThemesReferenceOperation)"))
    fun getThemesReferenceWithResponse(
        language: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, Theme>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kgetThemesReferenceWithResponse(language, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Properties within Polygon
     * Returns the properties within an custom polygon that represents a multi-city area or smaller. The coordinates of the polygon should be in [GeoJSON format](https://tools.ietf.org/html/rfc7946) and the polygon must conform to the following restrictions:   * Polygon size - diagonal distance of the polygon must be less than 500km   * Polygon type - only single polygons are supported   * Number of coordinates - must be <= 2000
     * @param operation [PostGeographyOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, PropertyGeography>
     */
    fun execute(operation: PostGeographyOperation): Response<kotlin.collections.Map<kotlin.String, PropertyGeography>> =
        execute<PropertiesGeoJsonRequest, kotlin.collections.Map<kotlin.String, PropertyGeography>>(operation)

    /**
     * Properties within Polygon
     * Returns the properties within an custom polygon that represents a multi-city area or smaller. The coordinates of the polygon should be in [GeoJSON format](https://tools.ietf.org/html/rfc7946) and the polygon must conform to the following restrictions:   * Polygon size - diagonal distance of the polygon must be less than 500km   * Polygon type - only single polygons are supported   * Number of coordinates - must be <= 2000
     * @param operation [PostGeographyOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.Map<kotlin.String, PropertyGeography>
     */
    fun executeAsync(operation: PostGeographyOperation): CompletableFuture<Response<kotlin.collections.Map<kotlin.String, PropertyGeography>>> =
        executeAsync<PropertiesGeoJsonRequest, kotlin.collections.Map<kotlin.String, PropertyGeography>>(operation)

    private suspend inline fun kpostGeographyWithResponse(
        include: kotlin.String,
        supplySource: kotlin.String,
        propertiesGeoJsonRequest: PropertiesGeoJsonRequest,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, PropertyGeography>> {
        val params =
            PostGeographyOperationParams(
                customerSessionId = customerSessionId,
                include = include,
                supplySource = supplySource,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            PostGeographyOperation(
                params,
                propertiesGeoJsonRequest
            )

        return execute(operation)
    }

    /**
     * Properties within Polygon
     * Returns the properties within an custom polygon that represents a multi-city area or smaller. The coordinates of the polygon should be in [GeoJSON format](https://tools.ietf.org/html/rfc7946) and the polygon must conform to the following restrictions:   * Polygon size - diagonal distance of the polygon must be less than 500km   * Polygon type - only single polygons are supported   * Number of coordinates - must be <= 2000
     * @param include Options for which content to return in the response. The value must be lower case.   * property_ids - Include the property IDs.
     * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
     * @param propertiesGeoJsonRequest
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return kotlin.collections.Map<kotlin.String, PropertyGeography>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PostGeographyOperation)"))
    fun postGeography(
        include: kotlin.String,
        supplySource: kotlin.String,
        propertiesGeoJsonRequest: PropertiesGeoJsonRequest,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.Map<kotlin.String, PropertyGeography> =
        postGeographyWithResponse(include, supplySource, propertiesGeoJsonRequest, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Properties within Polygon
     * Returns the properties within an custom polygon that represents a multi-city area or smaller. The coordinates of the polygon should be in [GeoJSON format](https://tools.ietf.org/html/rfc7946) and the polygon must conform to the following restrictions:   * Polygon size - diagonal distance of the polygon must be less than 500km   * Polygon type - only single polygons are supported   * Number of coordinates - must be <= 2000
     * @param include Options for which content to return in the response. The value must be lower case.   * property_ids - Include the property IDs.
     * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
     * @param propertiesGeoJsonRequest
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type kotlin.collections.Map<kotlin.String, PropertyGeography>
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PostGeographyOperation)"))
    fun postGeographyWithResponse(
        include: kotlin.String,
        supplySource: kotlin.String,
        propertiesGeoJsonRequest: PropertiesGeoJsonRequest,
        customerSessionId: kotlin.String? =
            null,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.Map<kotlin.String, PropertyGeography>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kpostGeographyWithResponse(include, supplySource, propertiesGeoJsonRequest, customerSessionId, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Create Booking
     * This link will be available in the Price Check response or in the register payments response when Two-Factor Authentication is used. It returns an itinerary id and links to retrieve reservation details, cancel a held booking, resume a held booking or complete payment session. Please note that depending on the state of the booking, the response will contain only the applicable link(s).
     * @param operation [PostItineraryOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type ItineraryCreation
     */
    fun execute(operation: PostItineraryOperation): Response<ItineraryCreation> = execute<CreateItineraryRequest, ItineraryCreation>(operation)

    /**
     * Create Booking
     * This link will be available in the Price Check response or in the register payments response when Two-Factor Authentication is used. It returns an itinerary id and links to retrieve reservation details, cancel a held booking, resume a held booking or complete payment session. Please note that depending on the state of the booking, the response will contain only the applicable link(s).
     * @param operation [PostItineraryOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type ItineraryCreation
     */
    fun executeAsync(operation: PostItineraryOperation): CompletableFuture<Response<ItineraryCreation>> = executeAsync<CreateItineraryRequest, ItineraryCreation>(operation)

    private suspend inline fun kpostItineraryWithResponse(
        customerIp: kotlin.String,
        token: kotlin.String,
        createItineraryRequest: CreateItineraryRequest,
        customerSessionId: kotlin.String? =
            null,
        test: PostItineraryOperationParams.Test? =
            null
    ): Response<ItineraryCreation> {
        val params =
            PostItineraryOperationParams(
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token
            )

        val operation =
            PostItineraryOperation(
                params,
                createItineraryRequest
            )

        return execute(operation)
    }

    /**
     * Create Booking
     * This link will be available in the Price Check response or in the register payments response when Two-Factor Authentication is used. It returns an itinerary id and links to retrieve reservation details, cancel a held booking, resume a held booking or complete payment session. Please note that depending on the state of the booking, the response will contain only the applicable link(s).
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param createItineraryRequest
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The book call has a test header that can be used to return set responses with the following keywords:<br> * `standard` * `complete_payment_session` * `service_unavailable` * `internal_server_error` * `price_mismatch` * `cc_declined` * `rooms_unavailable`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return ItineraryCreation
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PostItineraryOperation)"))
    fun postItinerary(
        customerIp: kotlin.String,
        token: kotlin.String,
        createItineraryRequest: CreateItineraryRequest,
        customerSessionId: kotlin.String? =
            null,
        test: PostItineraryOperationParams.Test? =
            null
    ): ItineraryCreation = postItineraryWithResponse(customerIp, token, createItineraryRequest, customerSessionId, test).data

    /**
     * Create Booking
     * This link will be available in the Price Check response or in the register payments response when Two-Factor Authentication is used. It returns an itinerary id and links to retrieve reservation details, cancel a held booking, resume a held booking or complete payment session. Please note that depending on the state of the booking, the response will contain only the applicable link(s).
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param createItineraryRequest
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The book call has a test header that can be used to return set responses with the following keywords:<br> * `standard` * `complete_payment_session` * `service_unavailable` * `internal_server_error` * `price_mismatch` * `cc_declined` * `rooms_unavailable`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type ItineraryCreation
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PostItineraryOperation)"))
    fun postItineraryWithResponse(
        customerIp: kotlin.String,
        token: kotlin.String,
        createItineraryRequest: CreateItineraryRequest,
        customerSessionId: kotlin.String? =
            null,
        test: PostItineraryOperationParams.Test? =
            null
    ): Response<ItineraryCreation> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kpostItineraryWithResponse(customerIp, token, createItineraryRequest, customerSessionId, test)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Register Payments
     * <b>This link only applies to transactions where EPS takes the customer's payment information. This includes both Expedia Collect and Property Collect transactions.</b><br> This link will be available in the Price Check response if payment registration is required. It returns a payment session ID and a link to create a booking. This will be the first step in the booking flow only if you've opted into Two-Factor Authentication to comply with the September 2019 EU Regulations for PSD2. Learn more with our [PSD2 Overview](https://developers.expediagroup.com/docs/rapid/lodging/booking/psd2-regulation)
     * @param operation [PostPaymentSessionsOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type PaymentSessions
     */
    fun execute(operation: PostPaymentSessionsOperation): Response<PaymentSessions> = execute<PaymentSessionsRequest, PaymentSessions>(operation)

    /**
     * Register Payments
     * <b>This link only applies to transactions where EPS takes the customer's payment information. This includes both Expedia Collect and Property Collect transactions.</b><br> This link will be available in the Price Check response if payment registration is required. It returns a payment session ID and a link to create a booking. This will be the first step in the booking flow only if you've opted into Two-Factor Authentication to comply with the September 2019 EU Regulations for PSD2. Learn more with our [PSD2 Overview](https://developers.expediagroup.com/docs/rapid/lodging/booking/psd2-regulation)
     * @param operation [PostPaymentSessionsOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type PaymentSessions
     */
    fun executeAsync(operation: PostPaymentSessionsOperation): CompletableFuture<Response<PaymentSessions>> = executeAsync<PaymentSessionsRequest, PaymentSessions>(operation)

    private suspend inline fun kpostPaymentSessionsWithResponse(
        customerIp: kotlin.String,
        token: kotlin.String,
        paymentSessionsRequest: PaymentSessionsRequest,
        customerSessionId: kotlin.String? =
            null,
        test: PostPaymentSessionsOperationParams.Test? =
            null
    ): Response<PaymentSessions> {
        val params =
            PostPaymentSessionsOperationParams(
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token
            )

        val operation =
            PostPaymentSessionsOperation(
                params,
                paymentSessionsRequest
            )

        return execute(operation)
    }

    /**
     * Register Payments
     * <b>This link only applies to transactions where EPS takes the customer's payment information. This includes both Expedia Collect and Property Collect transactions.</b><br> This link will be available in the Price Check response if payment registration is required. It returns a payment session ID and a link to create a booking. This will be the first step in the booking flow only if you've opted into Two-Factor Authentication to comply with the September 2019 EU Regulations for PSD2. Learn more with our [PSD2 Overview](https://developers.expediagroup.com/docs/rapid/lodging/booking/psd2-regulation)
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param paymentSessionsRequest
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The book call has a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `internal_server_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return PaymentSessions
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PostPaymentSessionsOperation)"))
    fun postPaymentSessions(
        customerIp: kotlin.String,
        token: kotlin.String,
        paymentSessionsRequest: PaymentSessionsRequest,
        customerSessionId: kotlin.String? =
            null,
        test: PostPaymentSessionsOperationParams.Test? =
            null
    ): PaymentSessions = postPaymentSessionsWithResponse(customerIp, token, paymentSessionsRequest, customerSessionId, test).data

    /**
     * Register Payments
     * <b>This link only applies to transactions where EPS takes the customer's payment information. This includes both Expedia Collect and Property Collect transactions.</b><br> This link will be available in the Price Check response if payment registration is required. It returns a payment session ID and a link to create a booking. This will be the first step in the booking flow only if you've opted into Two-Factor Authentication to comply with the September 2019 EU Regulations for PSD2. Learn more with our [PSD2 Overview](https://developers.expediagroup.com/docs/rapid/lodging/booking/psd2-regulation)
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param paymentSessionsRequest
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The book call has a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `internal_server_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type PaymentSessions
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PostPaymentSessionsOperation)"))
    fun postPaymentSessionsWithResponse(
        customerIp: kotlin.String,
        token: kotlin.String,
        paymentSessionsRequest: PaymentSessionsRequest,
        customerSessionId: kotlin.String? =
            null,
        test: PostPaymentSessionsOperationParams.Test? =
            null
    ): Response<PaymentSessions> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kpostPaymentSessionsWithResponse(customerIp, token, paymentSessionsRequest, customerSessionId, test)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Price-Check
     * Confirms the price returned by the Property Availability response. Use this API to verify a previously-selected rate is still valid before booking. If the price is matched, the response returns a link to request a booking. If the price has changed, the response returns new price details and a booking link for the new price. If the rate is no longer available, the response will return a new Property Availability request link to search again for different rates. In the event of a price change, go back to Property Availability and book the property at the new price or return to additional rates for the property.
     * @param operation [PriceCheckOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type RoomPriceCheck
     */
    fun execute(operation: PriceCheckOperation): Response<RoomPriceCheck> = execute<Nothing, RoomPriceCheck>(operation)

    /**
     * Price-Check
     * Confirms the price returned by the Property Availability response. Use this API to verify a previously-selected rate is still valid before booking. If the price is matched, the response returns a link to request a booking. If the price has changed, the response returns new price details and a booking link for the new price. If the rate is no longer available, the response will return a new Property Availability request link to search again for different rates. In the event of a price change, go back to Property Availability and book the property at the new price or return to additional rates for the property.
     * @param operation [PriceCheckOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type RoomPriceCheck
     */
    fun executeAsync(operation: PriceCheckOperation): CompletableFuture<Response<RoomPriceCheck>> = executeAsync<Nothing, RoomPriceCheck>(operation)

    private suspend inline fun kpriceCheckWithResponse(
        propertyId: kotlin.String,
        roomId: kotlin.String,
        rateId: kotlin.String,
        token: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null,
        test: PriceCheckOperationParams.Test? =
            null
    ): Response<RoomPriceCheck> {
        val params =
            PriceCheckOperationParams(
                propertyId = propertyId,
                roomId = roomId,
                rateId = rateId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token
            )

        val operation =
            PriceCheckOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Price-Check
     * Confirms the price returned by the Property Availability response. Use this API to verify a previously-selected rate is still valid before booking. If the price is matched, the response returns a link to request a booking. If the price has changed, the response returns new price details and a booking link for the new price. If the rate is no longer available, the response will return a new Property Availability request link to search again for different rates. In the event of a price change, go back to Property Availability and book the property at the new price or return to additional rates for the property.
     * @param propertyId Expedia Property ID.<br>
     * @param roomId Room ID of a property.<br>
     * @param rateId Rate ID of a room.<br>
     * @param token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the price check link from the shop response.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.  (optional)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test Price check calls have a test header that can be used to return set responses with the following keywords:   * `available`   * `price_changed`   * `sold_out`   * `service_unavailable`   * `unknown_internal_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return RoomPriceCheck
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PriceCheckOperation)"))
    fun priceCheck(
        propertyId: kotlin.String,
        roomId: kotlin.String,
        rateId: kotlin.String,
        token: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null,
        test: PriceCheckOperationParams.Test? =
            null
    ): RoomPriceCheck = priceCheckWithResponse(propertyId, roomId, rateId, token, customerIp, customerSessionId, test).data

    /**
     * Price-Check
     * Confirms the price returned by the Property Availability response. Use this API to verify a previously-selected rate is still valid before booking. If the price is matched, the response returns a link to request a booking. If the price has changed, the response returns new price details and a booking link for the new price. If the rate is no longer available, the response will return a new Property Availability request link to search again for different rates. In the event of a price change, go back to Property Availability and book the property at the new price or return to additional rates for the property.
     * @param propertyId Expedia Property ID.<br>
     * @param roomId Room ID of a property.<br>
     * @param rateId Rate ID of a room.<br>
     * @param token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the price check link from the shop response.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.  (optional)
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test Price check calls have a test header that can be used to return set responses with the following keywords:   * `available`   * `price_changed`   * `sold_out`   * `service_unavailable`   * `unknown_internal_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type RoomPriceCheck
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PriceCheckOperation)"))
    fun priceCheckWithResponse(
        propertyId: kotlin.String,
        roomId: kotlin.String,
        rateId: kotlin.String,
        token: kotlin.String,
        customerIp: kotlin.String? =
            null,
        customerSessionId: kotlin.String? =
            null,
        test: PriceCheckOperationParams.Test? =
            null
    ): Response<RoomPriceCheck> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kpriceCheckWithResponse(propertyId, roomId, rateId, token, customerIp, customerSessionId, test)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Complete Payment Session
     * <b>This link only applies to transactions where EPS takes the customer's payment information. This includes both Expedia Collect and Property Collect transactions.</b><br> This link will be available in the booking response only if you've opted into Two-Factor Authentication to comply with the September 2019 EU Regulations for PSD2. It should be called after Two-Factor Authentication has been completed by the customer in order to finalize the payment and complete the booking or hold attempt. Learn more with our [PSD2 Overview](https://developers.expediagroup.com/docs/rapid/lodging/booking/psd2-regulation)
     * @param operation [PutCompletePaymentSessionOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type CompletePaymentSession
     */
    fun execute(operation: PutCompletePaymentSessionOperation): Response<CompletePaymentSession> = execute<Nothing, CompletePaymentSession>(operation)

    /**
     * Complete Payment Session
     * <b>This link only applies to transactions where EPS takes the customer's payment information. This includes both Expedia Collect and Property Collect transactions.</b><br> This link will be available in the booking response only if you've opted into Two-Factor Authentication to comply with the September 2019 EU Regulations for PSD2. It should be called after Two-Factor Authentication has been completed by the customer in order to finalize the payment and complete the booking or hold attempt. Learn more with our [PSD2 Overview](https://developers.expediagroup.com/docs/rapid/lodging/booking/psd2-regulation)
     * @param operation [PutCompletePaymentSessionOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type CompletePaymentSession
     */
    fun executeAsync(operation: PutCompletePaymentSessionOperation): CompletableFuture<Response<CompletePaymentSession>> = executeAsync<Nothing, CompletePaymentSession>(operation)

    private suspend inline fun kputCompletePaymentSessionWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: PutCompletePaymentSessionOperationParams.Test? =
            null
    ): Response<CompletePaymentSession> {
        val params =
            PutCompletePaymentSessionOperationParams(
                itineraryId = itineraryId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token
            )

        val operation =
            PutCompletePaymentSessionOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Complete Payment Session
     * <b>This link only applies to transactions where EPS takes the customer's payment information. This includes both Expedia Collect and Property Collect transactions.</b><br> This link will be available in the booking response only if you've opted into Two-Factor Authentication to comply with the September 2019 EU Regulations for PSD2. It should be called after Two-Factor Authentication has been completed by the customer in order to finalize the payment and complete the booking or hold attempt. Learn more with our [PSD2 Overview](https://developers.expediagroup.com/docs/rapid/lodging/booking/psd2-regulation)
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The payment-sessions call has a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `internal_server_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return CompletePaymentSession
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PutCompletePaymentSessionOperation)"))
    fun putCompletePaymentSession(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: PutCompletePaymentSessionOperationParams.Test? =
            null
    ): CompletePaymentSession = putCompletePaymentSessionWithResponse(customerIp, itineraryId, token, customerSessionId, test).data

    /**
     * Complete Payment Session
     * <b>This link only applies to transactions where EPS takes the customer's payment information. This includes both Expedia Collect and Property Collect transactions.</b><br> This link will be available in the booking response only if you've opted into Two-Factor Authentication to comply with the September 2019 EU Regulations for PSD2. It should be called after Two-Factor Authentication has been completed by the customer in order to finalize the payment and complete the booking or hold attempt. Learn more with our [PSD2 Overview](https://developers.expediagroup.com/docs/rapid/lodging/booking/psd2-regulation)
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The payment-sessions call has a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `internal_server_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type CompletePaymentSession
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PutCompletePaymentSessionOperation)"))
    fun putCompletePaymentSessionWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: PutCompletePaymentSessionOperationParams.Test? =
            null
    ): Response<CompletePaymentSession> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kputCompletePaymentSessionWithResponse(customerIp, itineraryId, token, customerSessionId, test)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Resume Booking
     * This link will be available in the booking response after creating a held booking.
     * @param operation [PutResumeBookingOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    fun execute(operation: PutResumeBookingOperation): EmptyResponse = executeWithEmptyResponse<Nothing>(operation)

    /**
     * Resume Booking
     * This link will be available in the booking response after creating a held booking.
     * @param operation [PutResumeBookingOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type Nothing
     */
    fun executeAsync(operation: PutResumeBookingOperation): CompletableFuture<EmptyResponse> = executeAsyncWithEmptyResponse<Nothing>(operation)

    private suspend inline fun kputResumeBookingWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: PutResumeBookingOperationParams.Test? =
            null
    ): Response<Nothing> {
        val params =
            PutResumeBookingOperationParams(
                itineraryId = itineraryId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token
            )

        val operation =
            PutResumeBookingOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Resume Booking
     * This link will be available in the booking response after creating a held booking.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The resume call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test held booking. * `service_unavailable` - Returns the HTTP 202 response caused by partial service unavailablity. * `internal_server_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PutResumeBookingOperation)"))
    fun putResumeBooking(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: PutResumeBookingOperationParams.Test? =
            null
    ): Nothing = putResumeBookingWithResponse(customerIp, itineraryId, token, customerSessionId, test).data

    /**
     * Resume Booking
     * This link will be available in the booking response after creating a held booking.
     * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
     * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
     * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
     * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.  (optional)
     * @param test The resume call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test held booking. * `service_unavailable` - Returns the HTTP 202 response caused by partial service unavailablity. * `internal_server_error`  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: PutResumeBookingOperation)"))
    fun putResumeBookingWithResponse(
        customerIp: kotlin.String,
        itineraryId: kotlin.String,
        token: kotlin.String,
        customerSessionId: kotlin.String? =
            null,
        test: PutResumeBookingOperationParams.Test? =
            null
    ): Response<Nothing> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                kputResumeBookingWithResponse(customerIp, itineraryId, token, customerSessionId, test)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Request Test Notification
     * This request triggers a test notification according to the specified event_type. All event types supported by the Notifications API are available to test.
     * @param operation [RequestTestNotificationOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    fun execute(operation: RequestTestNotificationOperation): EmptyResponse = executeWithEmptyResponse<TestNotificationRequest>(operation)

    /**
     * Request Test Notification
     * This request triggers a test notification according to the specified event_type. All event types supported by the Notifications API are available to test.
     * @param operation [RequestTestNotificationOperation]
     * @throws ExpediaGroupApiErrorException
     * @return a [CompletableFuture<Response>] object with a body of type Nothing
     */
    fun executeAsync(operation: RequestTestNotificationOperation): CompletableFuture<EmptyResponse> = executeAsyncWithEmptyResponse<TestNotificationRequest>(operation)

    private suspend inline fun krequestTestNotificationWithResponse(
        testNotificationRequest: TestNotificationRequest,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<Nothing> {
        val params =
            RequestTestNotificationOperationParams(
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            RequestTestNotificationOperation(
                params,
                testNotificationRequest
            )

        return execute(operation)
    }

    /**
     * Request Test Notification
     * This request triggers a test notification according to the specified event_type. All event types supported by the Notifications API are available to test.
     * @param testNotificationRequest
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: RequestTestNotificationOperation)"))
    fun requestTestNotification(
        testNotificationRequest: TestNotificationRequest,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Nothing = requestTestNotificationWithResponse(testNotificationRequest, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Request Test Notification
     * This request triggers a test notification according to the specified event_type. All event types supported by the Notifications API are available to test.
     * @param testNotificationRequest
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @throws ExpediaGroupApiErrorException
     * @return a [Response] object with a body of type Nothing
     */
    @Throws(
        ExpediaGroupApiErrorException::class
    )
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: RequestTestNotificationOperation)"))
    fun requestTestNotificationWithResponse(
        testNotificationRequest: TestNotificationRequest,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<Nothing> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                krequestTestNotificationWithResponse(testNotificationRequest, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    /**
     * Request Undelivered Notifications
     * Use this API to fetch undelivered notifications. Up to 25 notifications are returned with each call. Each undelivered notification will be returned only once.
     * @param operation [RequestUndeliveredNotificationsOperation]
     * @return a [Response] object with a body of type kotlin.collections.List<Notification>
     */
    fun execute(operation: RequestUndeliveredNotificationsOperation): Response<kotlin.collections.List<Notification>> = execute<Nothing, kotlin.collections.List<Notification>>(operation)

    /**
     * Request Undelivered Notifications
     * Use this API to fetch undelivered notifications. Up to 25 notifications are returned with each call. Each undelivered notification will be returned only once.
     * @param operation [RequestUndeliveredNotificationsOperation]
     * @return a [CompletableFuture<Response>] object with a body of type kotlin.collections.List<Notification>
     */
    fun executeAsync(operation: RequestUndeliveredNotificationsOperation): CompletableFuture<Response<kotlin.collections.List<Notification>>> =
        executeAsync<Nothing, kotlin.collections.List<Notification>>(operation)

    private suspend inline fun krequestUndeliveredNotificationsWithResponse(
        undeliverable: kotlin.Boolean,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.List<Notification>> {
        val params =
            RequestUndeliveredNotificationsOperationParams(
                undeliverable = undeliverable,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        val operation =
            RequestUndeliveredNotificationsOperation(
                params
            )

        return execute(operation)
    }

    /**
     * Request Undelivered Notifications
     * Use this API to fetch undelivered notifications. Up to 25 notifications are returned with each call. Each undelivered notification will be returned only once.
     * @param undeliverable Undeliverable notifications are returned when this parameter is set to `true`.
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @return kotlin.collections.List<Notification>
     */
    @Throws()
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: RequestUndeliveredNotificationsOperation)"))
    fun requestUndeliveredNotifications(
        undeliverable: kotlin.Boolean,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): kotlin.collections.List<Notification> = requestUndeliveredNotificationsWithResponse(undeliverable, billingTerms, partnerPointOfSale, paymentTerms, platformName).data

    /**
     * Request Undelivered Notifications
     * Use this API to fetch undelivered notifications. Up to 25 notifications are returned with each call. Each undelivered notification will be returned only once.
     * @param undeliverable Undeliverable notifications are returned when this parameter is set to `true`.
     * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.  (optional)
     * @return a [Response] object with a body of type kotlin.collections.List<Notification>
     */
    @Throws()
    @JvmOverloads
    @Deprecated("Use execute method instead", ReplaceWith("execute(operation: RequestUndeliveredNotificationsOperation)"))
    fun requestUndeliveredNotificationsWithResponse(
        undeliverable: kotlin.Boolean,
        billingTerms: kotlin.String? =
            null,
        partnerPointOfSale: kotlin.String? =
            null,
        paymentTerms: kotlin.String? =
            null,
        platformName: kotlin.String? =
            null
    ): Response<kotlin.collections.List<Notification>> {
        try {
            return GlobalScope.future(Dispatchers.IO) {
                krequestUndeliveredNotificationsWithResponse(undeliverable, billingTerms, partnerPointOfSale, paymentTerms, platformName)
            }.get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }
}
