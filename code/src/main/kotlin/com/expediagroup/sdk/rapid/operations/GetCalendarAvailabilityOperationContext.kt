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

/**
 * @property test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error`
 */
data class GetCalendarAvailabilityOperationContext(
    val test: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var test: kotlin.String? = null
    ) {
        /**
         * @param test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error`
         */
        fun test(test: kotlin.String) = apply { this.test = test }

        fun build(): GetCalendarAvailabilityOperationContext {
            validateNullity()

            return GetCalendarAvailabilityOperationContext(
                test = test
            )
        }

        private fun validateNullity() {
        }
    }

    fun getHeaders(): Map<String, String> {
        return buildMap {
            test?.also { put("Test", test) }
        }
    }
}
