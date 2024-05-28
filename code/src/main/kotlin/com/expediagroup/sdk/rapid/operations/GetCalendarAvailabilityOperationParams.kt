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

data class GetCalendarAvailabilityOperationParams(
    val test: kotlin.String? = null,
    val propertyId: kotlin.collections.List<kotlin.String>,
    val startDate: java.time.LocalDate,
    val endDate: java.time.LocalDate
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var test: kotlin.String? = null,
        private var propertyId: kotlin.collections.List<kotlin.String>? = null,
        private var startDate: java.time.LocalDate? = null,
        private var endDate: java.time.LocalDate? = null
    ) {
        fun test(test: kotlin.String) = apply { this.test = test }

        fun propertyId(propertyId: kotlin.collections.List<kotlin.String>) = apply { this.propertyId = propertyId }

        fun startDate(startDate: java.time.LocalDate) = apply { this.startDate = startDate }

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
            test?.also { put("Test", test) }
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            propertyId?.also { put("property_id", propertyId) }
            startDate?.also { put("start_date", listOf(startDate.toString())) }
            endDate?.also { put("end_date", listOf(endDate.toString())) }
        }
    }
}
