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
package com.expediagroup.sdk.rapid.operations

import com.expediagroup.sdk.core.model.OperationParams
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property test Shop calls have a test header that can be used to return set responses with the following keywords: * `standard` * `service_unavailable` * `unknown_internal_error`
 * @property propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
 * @property startDate The first day of availability information to be returned, in ISO 8601 format (YYYY-MM-DD), up to 500 days in the future from the current date.
 * @property endDate The last day of availability information to be returned, in ISO 8601 format (YYYY-MM-DD). This must be 365 days or less from the start_date.
 */
@JsonDeserialize(builder = GetCalendarAvailabilityOperationParams.Builder::class)
data class GetCalendarAvailabilityOperationParams(
    val test: GetCalendarAvailabilityOperationParams.Test? =
        null,
    val propertyId: kotlin.collections.List<
        kotlin.String
    >,
    val startDate: java.time.LocalDate,
    val endDate: java.time.LocalDate
) :
    OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    enum class Test(
        val value: kotlin.String
    ) {
        STANDARD("standard"),
        SERVICE_UNAVAILABLE("service_unavailable"),
        UNKNOWN_INTERNAL_ERROR("unknown_internal_error")
    }

    class Builder(
        @JsonProperty("Test") private var test: GetCalendarAvailabilityOperationParams.Test? = null,
        @JsonProperty("property_id") private var propertyId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("start_date") private var startDate: java.time.LocalDate? = null,
        @JsonProperty("end_date") private var endDate: java.time.LocalDate? = null
    ) {
        /**
         * @param test Shop calls have a test header that can be used to return set responses with the following keywords: * `standard` * `service_unavailable` * `unknown_internal_error`
         */
        fun test(test: GetCalendarAvailabilityOperationParams.Test) = apply { this.test = test }

        /**
         * @param propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
         */
        fun propertyId(
            propertyId: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.propertyId = propertyId }

        /**
         * @param startDate The first day of availability information to be returned, in ISO 8601 format (YYYY-MM-DD), up to 500 days in the future from the current date.
         */
        fun startDate(startDate: java.time.LocalDate) = apply { this.startDate = startDate }

        /**
         * @param endDate The last day of availability information to be returned, in ISO 8601 format (YYYY-MM-DD). This must be 365 days or less from the start_date.
         */
        fun endDate(endDate: java.time.LocalDate) = apply { this.endDate = endDate }

        fun build(): GetCalendarAvailabilityOperationParams {
            validateNullity()

            return GetCalendarAvailabilityOperationParams(
                test = test,
                propertyId = propertyId!!,
                startDate = startDate!!,
                endDate = endDate!!
            )
        }

        private fun validateNullity() {
            if (propertyId == null) {
                throw NullPointerException("Required parameter propertyId is missing")
            }
            if (startDate == null) {
                throw NullPointerException("Required parameter startDate is missing")
            }
            if (endDate == null) {
                throw NullPointerException("Required parameter endDate is missing")
            }
        }
    }

    override fun getHeaders(): Map<String, String> {
        return buildMap {
            test?.also {
                put("Test", test.value)
            }
            put("Accept", "application/json")
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            propertyId?.also {
                put(
                    "property_id",
                    propertyId
                )
            }
            startDate?.also {
                put(
                    "start_date",
                    listOf(startDate.toString())
                )
            }
            endDate?.also {
                put(
                    "end_date",
                    listOf(endDate.toString())
                )
            }
        }
    }

    override fun getPathParams(): Map<String, String> {
        return buildMap {
        }
    }
}
