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
 * How and when the payment can be taken.
 * @param expediaCollect Whether or not a payment for this property can be taken by Expedia at the time of booking.
 * @param propertyCollect Whether or not a payment for this property can be taken by the property upon arrival.
 */
data class BusinessModel(
    // Whether or not a payment for this property can be taken by Expedia at the time of booking.
    @JsonProperty("expedia_collect")
    @field:Valid
    val expediaCollect: kotlin.Boolean? = null,
    // Whether or not a payment for this property can be taken by the property upon arrival.
    @JsonProperty("property_collect")
    @field:Valid
    val propertyCollect: kotlin.Boolean? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var expediaCollect: kotlin.Boolean? = null,
        private var propertyCollect: kotlin.Boolean? = null
    ) {
        fun expediaCollect(expediaCollect: kotlin.Boolean?) = apply { this.expediaCollect = expediaCollect }

        fun propertyCollect(propertyCollect: kotlin.Boolean?) = apply { this.propertyCollect = propertyCollect }

        fun build(): BusinessModel {
            return BusinessModel(
                expediaCollect = expediaCollect,
                propertyCollect = propertyCollect
            )
        }
    }
}
