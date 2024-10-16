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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport",
)

package com.expediagroup.sdk.rapid.models

import com.expediagroup.sdk.rapid.models.Deal
import com.expediagroup.sdk.rapid.models.ValueAdd
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Available promotions that apply to this rate.
 * @param valueAdds A collection of value adds that apply to this rate.
 * @param deal
 */
data class Promotions(
    // A collection of value adds that apply to this rate.
    @JsonProperty("value_adds")
    @field:Valid
    val valueAdds: kotlin.collections.Map<kotlin.String, ValueAdd>? = null,
    @JsonProperty("deal")
    @field:Valid
    val deal: Deal? = null,
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var valueAdds: kotlin.collections.Map<kotlin.String, ValueAdd>? = null,
        private var deal: Deal? = null,
    ) {
        fun valueAdds(valueAdds: kotlin.collections.Map<kotlin.String, ValueAdd>?) = apply { this.valueAdds = valueAdds }

        fun deal(deal: Deal?) = apply { this.deal = deal }

        fun build(): Promotions {
            return Promotions(
                valueAdds = valueAdds,
                deal = deal,
            )
        }
    }

    fun toBuilder() =
        Builder(
            valueAdds = valueAdds,
            deal = deal,
        )
}
