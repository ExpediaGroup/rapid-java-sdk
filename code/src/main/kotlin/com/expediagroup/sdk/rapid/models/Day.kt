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
 *
 * @param date Date associated to the provided information.
 * @param available True if the property is available on the date.
 * @param checkin Enumeration indicating the capability of check-in on the date.  `CHECKIN_VALID`: The associated date is valid for check in.  `CHECKIN_INVALID`: Generic or Unknown reason for being not being a valid day for check in.  `CHECKIN_INVALID_DUE_TO_BEING_IN_PAST`: The associated date is not valid for check in due to being in the past.  `CHECKIN_INVALID_DUE_TO_MIN_PRIOR_NOTIFICATION`:  The associated date is not valid for check in because checking in on this date would not meet the owner's minimum prior notification requirements.  `CHECKIN_INVALID_DUE_TO_MAX_FUTURE_BOOKING`: The associated date is not valid for check in because it is too far in the future.  `CHECKIN_INVALID_DUE_TO_NOT_AVAILABLE`: The associated date is not valid for check in because it is not available (ie. the date is already reserved).  `CHECKIN_INVALID_DUE_TO_NON_CHANGEOVER_DAY_OF_WEEK`: The associated date is not valid for check in because it falls on a day of the week that check in is prohibited.  `CHECKIN_INVALID_DUE_TO_CHANGEOVER_EXCLUSION`: The associated date is not valid for check in because check in was prohibited on that specific date.  `CHECKIN_INVALID_DUE_TO_MIN_STAY_NOT_ACHIEVABLE`: The associated date is not valid for check in because checking in on this date does not satisfy the minimum stay duration.  `CHECKIN_INVALID_DUE_TO_NO_VALID_CHECKOUT_WITHIN_CONSTRAINTS`: The associated date is not valid for check in because there is not an associated check out date that would allow the stay to satisfy stay constraints.
 * @param checkout Enumeration indicating the capability of check-out on the date.  `CHECKOUT_VALID`: The associated date is valid for check out.  `CHECKOUT_INVALID`: The checkout validity value is invalid or unknown.  `CHECKOUT_INVALID_DUE_TO_NON_CHANGEOVER_DAY_OF_WEEK`: The associated date is not valid for check out because it falls on a day of the week that check out is prohibited.  `CHECKOUT_INVALID_DUE_TO_CHANGEOVER_EXCLUSION`: The associated date is not valid for check out because check out was prohibited on that specific date.
 * @param stayConstraints
 */
data class Day(
    // Date associated to the provided information.
    @JsonProperty("date")
    val date: java.time.LocalDate? = null,
    // True if the property is available on the date.
    @JsonProperty("available")
    @field:Valid
    val available: kotlin.Boolean? = null,
    // Enumeration indicating the capability of check-in on the date.  `CHECKIN_VALID`: The associated date is valid for check in.  `CHECKIN_INVALID`: Generic or Unknown reason for being not being a valid day for check in.  `CHECKIN_INVALID_DUE_TO_BEING_IN_PAST`: The associated date is not valid for check in due to being in the past.  `CHECKIN_INVALID_DUE_TO_MIN_PRIOR_NOTIFICATION`:  The associated date is not valid for check in because checking in on this date would not meet the owner's minimum prior notification requirements.  `CHECKIN_INVALID_DUE_TO_MAX_FUTURE_BOOKING`: The associated date is not valid for check in because it is too far in the future.  `CHECKIN_INVALID_DUE_TO_NOT_AVAILABLE`: The associated date is not valid for check in because it is not available (ie. the date is already reserved).  `CHECKIN_INVALID_DUE_TO_NON_CHANGEOVER_DAY_OF_WEEK`: The associated date is not valid for check in because it falls on a day of the week that check in is prohibited.  `CHECKIN_INVALID_DUE_TO_CHANGEOVER_EXCLUSION`: The associated date is not valid for check in because check in was prohibited on that specific date.  `CHECKIN_INVALID_DUE_TO_MIN_STAY_NOT_ACHIEVABLE`: The associated date is not valid for check in because checking in on this date does not satisfy the minimum stay duration.  `CHECKIN_INVALID_DUE_TO_NO_VALID_CHECKOUT_WITHIN_CONSTRAINTS`: The associated date is not valid for check in because there is not an associated check out date that would allow the stay to satisfy stay constraints.
    @JsonProperty("checkin")
    @field:Valid
    val checkin: kotlin.String? = null,
    // Enumeration indicating the capability of check-out on the date.  `CHECKOUT_VALID`: The associated date is valid for check out.  `CHECKOUT_INVALID`: The checkout validity value is invalid or unknown.  `CHECKOUT_INVALID_DUE_TO_NON_CHANGEOVER_DAY_OF_WEEK`: The associated date is not valid for check out because it falls on a day of the week that check out is prohibited.  `CHECKOUT_INVALID_DUE_TO_CHANGEOVER_EXCLUSION`: The associated date is not valid for check out because check out was prohibited on that specific date.
    @JsonProperty("checkout")
    @field:Valid
    val checkout: kotlin.String? = null,
    @JsonProperty("stay_constraints")
    @field:Valid
    val stayConstraints: StayConstraints? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var date: java.time.LocalDate? = null,
        private var available: kotlin.Boolean? = null,
        private var checkin: kotlin.String? = null,
        private var checkout: kotlin.String? = null,
        private var stayConstraints: StayConstraints? = null
    ) {
        fun date(date: java.time.LocalDate?) = apply { this.date = date }

        fun available(available: kotlin.Boolean?) = apply { this.available = available }

        fun checkin(checkin: kotlin.String?) = apply { this.checkin = checkin }

        fun checkout(checkout: kotlin.String?) = apply { this.checkout = checkout }

        fun stayConstraints(stayConstraints: StayConstraints?) = apply { this.stayConstraints = stayConstraints }

        fun build(): Day {
            return Day(
                date = date,
                available = available,
                checkin = checkin,
                checkout = checkout,
                stayConstraints = stayConstraints
            )
        }
    }
}
