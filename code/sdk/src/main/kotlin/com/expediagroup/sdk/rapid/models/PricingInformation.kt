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
 * The pricing information object.
 * @param nightly Array of arrays of amount objects. Each sub-array of amount objects represents a single night's charges.
 * @param stay Array of amount objects. Details any charges that apply to the entire stay (not divided per-night). Any per-room adjustments are applied to the `base_rate` amount within this object.
 * @param totals
 * @param fees
 */
data class PricingInformation(
    // Array of arrays of amount objects. Each sub-array of amount objects represents a single night's charges.
    @JsonProperty("nightly")
    @field:Valid
    val nightly: kotlin.collections.List<kotlin.collections.List<NightCharge>>? = null,
    // Array of amount objects. Details any charges that apply to the entire stay (not divided per-night). Any per-room adjustments are applied to the `base_rate` amount within this object.
    @JsonProperty("stay")
    @field:Valid
    val stay: kotlin.collections.List<Stay>? = null,
    @JsonProperty("totals")
    @field:Valid
    val totals: Totals? = null,
    @JsonProperty("fees")
    @field:Valid
    val fees: FeesPricingInformation? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var nightly: kotlin.collections.List<kotlin.collections.List<NightCharge>>? = null,
        private var stay: kotlin.collections.List<Stay>? = null,
        private var totals: Totals? = null,
        private var fees: FeesPricingInformation? = null
    ) {
        fun nightly(nightly: kotlin.collections.List<kotlin.collections.List<NightCharge>>?) = apply { this.nightly = nightly }

        fun stay(stay: kotlin.collections.List<Stay>?) = apply { this.stay = stay }

        fun totals(totals: Totals?) = apply { this.totals = totals }

        fun fees(fees: FeesPricingInformation?) = apply { this.fees = fees }

        fun build(): PricingInformation {
            return PricingInformation(
                nightly = nightly,
                stay = stay,
                totals = totals,
                fees = fees
            )
        }
    }
}
