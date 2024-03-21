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
 * The fees collected by the property. The values for each type of fee are the total for that type.  Mandatory fees are collected by the property at check-in or check-out. Resort fees are charged for amenities and extras and collected by the property at check-in or check-out. Mandatory taxes are taxes collected by the property at check-in or check-out.
 * @param mandatoryFee
 * @param resortFee
 * @param mandatoryTax
 */
data class FeesPricingInformation(
    @JsonProperty("mandatory_fee")
    @field:Valid
    val mandatoryFee: ChargeCalculated? = null,
    @JsonProperty("resort_fee")
    @field:Valid
    val resortFee: ChargeCalculated? = null,
    @JsonProperty("mandatory_tax")
    @field:Valid
    val mandatoryTax: ChargeCalculated? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var mandatoryFee: ChargeCalculated? = null,
        private var resortFee: ChargeCalculated? = null,
        private var mandatoryTax: ChargeCalculated? = null
    ) {
        fun mandatoryFee(mandatoryFee: ChargeCalculated?) = apply { this.mandatoryFee = mandatoryFee }

        fun resortFee(resortFee: ChargeCalculated?) = apply { this.resortFee = resortFee }

        fun mandatoryTax(mandatoryTax: ChargeCalculated?) = apply { this.mandatoryTax = mandatoryTax }

        fun build(): FeesPricingInformation {
            return FeesPricingInformation(
                mandatoryFee = mandatoryFee,
                resortFee = resortFee,
                mandatoryTax = mandatoryTax
            )
        }
    }
}
