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

import com.fasterxml.jackson.annotation.JsonProperty

/**
* The price breakout type.   * `base_rate` - The room rate without any taxes and fees applied.   * `tax_and_service_fee` - Tax recovery charges, service fees, and taxes. Ensure that you capture these values and display as:                             \"Taxes and Fees\"   * `extra_person_fee` - A per night fee that is charged by a hotel for additional adults over the nightly rate. This fee is included as part of the total.   * `property_fee` - The property fee surcharge type must be displayed beginning on the initial hotel room selection page, immediately after your hotel search results page. This placement is required by the U.S. Federal Trade Commission (FTC).                      Display this surcharge as \"Property Fee\" on your room selection page, as described above, and in all subsequent price breakdowns for the following Points of Sale:                      * `US`                      * `Canada`                      * `Brazil`                      * `LATAM`   * `sales_tax` - Taxes that must be displayed by certain jurisdictional laws. Ensure that you capture these values and display as \"Taxes\".   * `adjustment` - This is the amount that the individual night have been adjusted/discounted.   * `recovery_charges_and_fees` - Tax recovery charges, service fees, and taxes. Ensure that you capture these values and display as:                                   \"Taxes and Fees\"   * `traveler_service_fee` - Fee charged by Vrbo to support use of online tools, services and functions on its platform which enable guest self service. May be displayed as 'Traveler Service Fee' or 'Service Fee' any time fees are broken out for display on partner sites.
* Values: BASE_RATE,TAX_AND_SERVICE_FEE,EXTRA_PERSON_FEE,PROPERTY_FEE,SALES_TAX,ADJUSTMENT,RECOVERY_CHARGES_AND_FEES,TRAVELER_SERVICE_FEE
*/
enum class NightChargeType(val value: kotlin.String) {
    @JsonProperty("base_rate")
    BASE_RATE("base_rate"),

    @JsonProperty("tax_and_service_fee")
    TAX_AND_SERVICE_FEE("tax_and_service_fee"),

    @JsonProperty("extra_person_fee")
    EXTRA_PERSON_FEE("extra_person_fee"),

    @JsonProperty("property_fee")
    PROPERTY_FEE("property_fee"),

    @JsonProperty("sales_tax")
    SALES_TAX("sales_tax"),

    @JsonProperty("adjustment")
    ADJUSTMENT("adjustment"),

    @JsonProperty("recovery_charges_and_fees")
    RECOVERY_CHARGES_AND_FEES("recovery_charges_and_fees"),

    @JsonProperty("traveler_service_fee")
    TRAVELER_SERVICE_FEE("traveler_service_fee")
}
