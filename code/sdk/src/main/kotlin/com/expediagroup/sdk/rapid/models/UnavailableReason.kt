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

package com.expediagroup.sdk.rapid.models

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

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * An unavailable reason that suggests ways that the request could be modified to locate available rooms and rates.
 * @param code The code representing the reason. * `adults_exceed_threshold` - Number of adults requested exceeds room threshold. `data` will contain the maximum value. * `children_exceed_threshold` - Number of children requested exceeds room threshold. `data` will contain the maximum value. * `infants_exceed_threshold` - Number of infants requested exceeds room threshold. `data` will contain the maximum value. * `minimum_child_age` - Child age requested is less than the minimum age specified for the room. `data` will contain the minimum value. * `maximum_occupancy` - Number of occupants exceed the specified room limit. `data` will contain the maximum value. * `checkin_not_allowed` - Check-in not allowed for this stay date. * `checkout_not_allowed` - Check-out not allowed for this stay date. * `minimum_stay` - Length of stay is less than minimum. `data` will contain the minimum value. * `maximum_stay` - Length of stay is greater than maximum. `data` will contain the maximum value. * `restricted_stay_lengths` - Some stay lengths are not available for this check-in date. `data` will contain the allowed lengths separated by pipe `|`. eg. `3|5|6` * `same_day_restrictions` - Room is not available due to same day booking restrictions. * `maximum_rooms` - Room count exceeds provider limit. `data` will contain the maximum value. * `children_not_supported` - The property is restricted to adults only. * `minimum_advance_purchase` - Minimum Advance Purchase requirement not met. `data` will contain the minimum value. * `maximum_advance_purchase` - Maximum Advance Purchase requirement not met. `data` will contain the maximum value. * `partial_inventory_available` - Some of the dates have no inventory available for the specified duration. `data` will contain the unavailable dates separated by pipe `|`. eg. `2023-10-21|2023-10-23` * `no_inventory_available` - No inventory is available for the specified duration.
 * @param `data` An associated value that provides helpful information for some codes. Not present for all codes.
 */
data class UnavailableReason(
    // The code representing the reason. * `adults_exceed_threshold` - Number of adults requested exceeds room threshold. `data` will contain the maximum value. * `children_exceed_threshold` - Number of children requested exceeds room threshold. `data` will contain the maximum value. * `infants_exceed_threshold` - Number of infants requested exceeds room threshold. `data` will contain the maximum value. * `minimum_child_age` - Child age requested is less than the minimum age specified for the room. `data` will contain the minimum value. * `maximum_occupancy` - Number of occupants exceed the specified room limit. `data` will contain the maximum value. * `checkin_not_allowed` - Check-in not allowed for this stay date. * `checkout_not_allowed` - Check-out not allowed for this stay date. * `minimum_stay` - Length of stay is less than minimum. `data` will contain the minimum value. * `maximum_stay` - Length of stay is greater than maximum. `data` will contain the maximum value. * `restricted_stay_lengths` - Some stay lengths are not available for this check-in date. `data` will contain the allowed lengths separated by pipe `|`. eg. `3|5|6` * `same_day_restrictions` - Room is not available due to same day booking restrictions. * `maximum_rooms` - Room count exceeds provider limit. `data` will contain the maximum value. * `children_not_supported` - The property is restricted to adults only. * `minimum_advance_purchase` - Minimum Advance Purchase requirement not met. `data` will contain the minimum value. * `maximum_advance_purchase` - Maximum Advance Purchase requirement not met. `data` will contain the maximum value. * `partial_inventory_available` - Some of the dates have no inventory available for the specified duration. `data` will contain the unavailable dates separated by pipe `|`. eg. `2023-10-21|2023-10-23` * `no_inventory_available` - No inventory is available for the specified duration.
    @JsonProperty("code")
    val code: UnavailableReason.Code? = null,
    // An associated value that provides helpful information for some codes. Not present for all codes.
    @JsonProperty("data")
    @field:Valid
    val `data`: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: UnavailableReason.Code? = null,
        private var `data`: kotlin.String? = null
    ) {
        fun code(code: UnavailableReason.Code?) = apply { this.code = code }

        fun `data`(`data`: kotlin.String?) = apply { this.`data` = `data` }

        fun build(): UnavailableReason {
            return UnavailableReason(
                code = code,
                `data` = `data`
            )
        }
    }

    /**
     * The code representing the reason. * `adults_exceed_threshold` - Number of adults requested exceeds room threshold. `data` will contain the maximum value. * `children_exceed_threshold` - Number of children requested exceeds room threshold. `data` will contain the maximum value. * `infants_exceed_threshold` - Number of infants requested exceeds room threshold. `data` will contain the maximum value. * `minimum_child_age` - Child age requested is less than the minimum age specified for the room. `data` will contain the minimum value. * `maximum_occupancy` - Number of occupants exceed the specified room limit. `data` will contain the maximum value. * `checkin_not_allowed` - Check-in not allowed for this stay date. * `checkout_not_allowed` - Check-out not allowed for this stay date. * `minimum_stay` - Length of stay is less than minimum. `data` will contain the minimum value. * `maximum_stay` - Length of stay is greater than maximum. `data` will contain the maximum value. * `restricted_stay_lengths` - Some stay lengths are not available for this check-in date. `data` will contain the allowed lengths separated by pipe `|`. eg. `3|5|6` * `same_day_restrictions` - Room is not available due to same day booking restrictions. * `maximum_rooms` - Room count exceeds provider limit. `data` will contain the maximum value. * `children_not_supported` - The property is restricted to adults only. * `minimum_advance_purchase` - Minimum Advance Purchase requirement not met. `data` will contain the minimum value. * `maximum_advance_purchase` - Maximum Advance Purchase requirement not met. `data` will contain the maximum value. * `partial_inventory_available` - Some of the dates have no inventory available for the specified duration. `data` will contain the unavailable dates separated by pipe `|`. eg. `2023-10-21|2023-10-23` * `no_inventory_available` - No inventory is available for the specified duration.
     * Values: ADULTS_EXCEED_THRESHOLD,CHILDREN_EXCEED_THRESHOLD,INFANTS_EXCEED_THRESHOLD,MINIMUM_CHILD_AGE,MAXIMUM_OCCUPANCY,CHECKIN_NOT_ALLOWED,CHECKOUT_NOT_ALLOWED,MINIMUM_STAY,MAXIMUM_STAY,RESTRICTED_DATES,SAME_DAY_RESTRICTIONS,MAXIMUM_ROOMS,CHILDREN_NOT_SUPPORTED,MINIMUM_ADVANCE_PURCHASE,MAXIMUM_ADVANCE_PURCHASE,PARTIAL_INVENTORY_AVAILABLE,NO_INVENTORY_AVAILABLE
     */
    enum class Code(val value: kotlin.String) {
        @JsonProperty("adults_exceed_threshold")
        ADULTS_EXCEED_THRESHOLD("adults_exceed_threshold"),

        @JsonProperty("children_exceed_threshold")
        CHILDREN_EXCEED_THRESHOLD("children_exceed_threshold"),

        @JsonProperty("infants_exceed_threshold")
        INFANTS_EXCEED_THRESHOLD("infants_exceed_threshold"),

        @JsonProperty("minimum_child_age")
        MINIMUM_CHILD_AGE("minimum_child_age"),

        @JsonProperty("maximum_occupancy")
        MAXIMUM_OCCUPANCY("maximum_occupancy"),

        @JsonProperty("checkin_not_allowed")
        CHECKIN_NOT_ALLOWED("checkin_not_allowed"),

        @JsonProperty("checkout_not_allowed")
        CHECKOUT_NOT_ALLOWED("checkout_not_allowed"),

        @JsonProperty("minimum_stay")
        MINIMUM_STAY("minimum_stay"),

        @JsonProperty("maximum_stay")
        MAXIMUM_STAY("maximum_stay"),

        @JsonProperty("restricted_dates")
        RESTRICTED_DATES("restricted_dates"),

        @JsonProperty("same_day_restrictions")
        SAME_DAY_RESTRICTIONS("same_day_restrictions"),

        @JsonProperty("maximum_rooms")
        MAXIMUM_ROOMS("maximum_rooms"),

        @JsonProperty("children_not_supported")
        CHILDREN_NOT_SUPPORTED("children_not_supported"),

        @JsonProperty("minimum_advance_purchase")
        MINIMUM_ADVANCE_PURCHASE("minimum_advance_purchase"),

        @JsonProperty("maximum_advance_purchase")
        MAXIMUM_ADVANCE_PURCHASE("maximum_advance_purchase"),

        @JsonProperty("partial_inventory_available")
        PARTIAL_INVENTORY_AVAILABLE("partial_inventory_available"),

        @JsonProperty("no_inventory_available")
        NO_INVENTORY_AVAILABLE("no_inventory_available")
    }
}
