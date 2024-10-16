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

import com.expediagroup.sdk.rapid.models.CardOption
import com.expediagroup.sdk.rapid.models.CreditCardMerchant
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 *
 * @param cardOptions
 * @param merchant
 * @param name Display name of payment option.
 */
data class CreditCard(
    @JsonProperty("card_options")
    @field:Valid
    val cardOptions: kotlin.collections.List<CardOption>? = null,
    @JsonProperty("merchant")
    @field:Valid
    val merchant: CreditCardMerchant? = null,
    // Display name of payment option.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var cardOptions: kotlin.collections.List<CardOption>? = null,
        private var merchant: CreditCardMerchant? = null,
        private var name: kotlin.String? = null,
    ) {
        fun cardOptions(cardOptions: kotlin.collections.List<CardOption>?) = apply { this.cardOptions = cardOptions }

        fun merchant(merchant: CreditCardMerchant?) = apply { this.merchant = merchant }

        fun name(name: kotlin.String?) = apply { this.name = name }

        fun build(): CreditCard {
            return CreditCard(
                cardOptions = cardOptions,
                merchant = merchant,
                name = name,
            )
        }
    }

    fun toBuilder() =
        Builder(
            cardOptions = cardOptions,
            merchant = merchant,
            name = name,
        )
}
