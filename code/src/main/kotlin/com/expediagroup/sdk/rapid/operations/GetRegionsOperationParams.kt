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
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property include Options for which content to return in the response. This parameter can be supplied multiple times with different values. The standard and details options cannot be requested together. The value must be lower case.   * standard - Include the metadata and basic hierarchy of each region.   * details - Include the metadata, coordinates and full hierarchy of each region.   * property_ids - Include the list of property IDs within the bounding polygon of each region.   * property_ids_expanded - Include the list of property IDs within the bounding polygon of each region and     property IDs from the surrounding area if minimal properties are within the region.
 * @property language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
 * @property ancestorId Search for regions whose ancestors include the requested ancestor region ID. Refer to the list of [top level regions](https://developers.expediagroup.com/docs/rapid/lodging/geography/geography-reference-lists).
 * @property area Filter the results to regions that intersect with a specified area.<br><br> The area may be defined in one of two ways:   * radius,region_id   * radius,latitude,longitude  Radius combined with region id would search an area that extends the number of kilometers out from the boundaries of the region in all directions.<br> Radius combined with a single point, specified by a latitude, longitude pair would search an area in a circle with the specified radius and the point as the center.<br> Radius should be specified in non-negative whole kilometers, decimals will return an error. A radius of 0 is allowed.<br> When specifying the area parameter, there will be a limit of 100 results, which can be narrowed further by the limit parameter.<br> Due to the number of results, unless `point_of_interest` is specified as the only type, regions of type `point_of_interest` will not be included in a request that filters to an area.<br><br> An example use case would be searching for the closest 3 airports within 50 kilometers of a specified point.<br>   `&type=airport&limit=3&area=50,37.227924,-93.310036`
 * @property countryCode Filter the results to a specified ISO 3166-1 alpha-2 country code.  For more information see: [https://www.iso.org/obp/ui/#search/code/](https://www.iso.org/obp/ui/#search/code/)
 * @property countrySubdivisionCode Filter the results down to only the ISO 3166-2 country subdivision.
 * @property iataLocationCode Search for regions by the requested 3-character IATA location code, which will apply to both iata_airport_code and iata_airport_metro_code. The code must be upper case.
 * @property limit Limit the number of results returned. Using the area parameter will impose a max value of 100 for this whether specified or not.
 * @property supplySource Options for which supply source you would like returned in the geography response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
 * @property type Filter the results to a specified region type.
 * @property billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
 * @property partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 * @property paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
 * @property platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 */
@JsonDeserialize(builder = GetRegionsOperationParams.Builder::class)
data class GetRegionsOperationParams(
    val customerSessionId: kotlin.String? =
        null,
    val include: kotlin.collections.List<
        GetRegionsOperationParams.Include
    >,
    val language: kotlin.String,
    val ancestorId: kotlin.String? =
        null,
    val area: kotlin.String? =
        null,
    val countryCode: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    val countrySubdivisionCode: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    val iataLocationCode: kotlin.String? =
        null,
    val limit: java.math.BigDecimal? =
        null,
    val supplySource: kotlin.String? =
        null,
    val type: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    val billingTerms: kotlin.String? =
        null,
    val partnerPointOfSale: kotlin.String? =
        null,
    val paymentTerms: kotlin.String? =
        null,
    val platformName: kotlin.String? =
        null
) :
    OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    enum class Include(
        val value: kotlin.String
    ) {
        STANDARD("standard"),
        DETAILS("details"),
        PROPERTY_IDS("property_ids"),
        PROPERTY_IDS_EXPANDED("property_ids_expanded")
    }

    class Builder(
        @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
        @JsonProperty("include") private var include: kotlin.collections.List<
            GetRegionsOperationParams.Include
        >? = null,
        @JsonProperty("language") private var language: kotlin.String? = null,
        @JsonProperty("ancestor_id") private var ancestorId: kotlin.String? = null,
        @JsonProperty("area") private var area: kotlin.String? = null,
        @JsonProperty("country_code") private var countryCode: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("country_subdivision_code") private var countrySubdivisionCode: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("iata_location_code") private var iataLocationCode: kotlin.String? = null,
        @JsonProperty("limit") private var limit: java.math.BigDecimal? = null,
        @JsonProperty("supply_source") private var supplySource: kotlin.String? = null,
        @JsonProperty("type") private var type: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("billing_terms") private var billingTerms: kotlin.String? = null,
        @JsonProperty("partner_point_of_sale") private var partnerPointOfSale: kotlin.String? = null,
        @JsonProperty("payment_terms") private var paymentTerms: kotlin.String? = null,
        @JsonProperty("platform_name") private var platformName: kotlin.String? = null
    ) {
        /**
         * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
         */
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        /**
         * @param include Options for which content to return in the response. This parameter can be supplied multiple times with different values. The standard and details options cannot be requested together. The value must be lower case.   * standard - Include the metadata and basic hierarchy of each region.   * details - Include the metadata, coordinates and full hierarchy of each region.   * property_ids - Include the list of property IDs within the bounding polygon of each region.   * property_ids_expanded - Include the list of property IDs within the bounding polygon of each region and     property IDs from the surrounding area if minimal properties are within the region.
         */
        fun include(
            include: kotlin.collections.List<
                GetRegionsOperationParams.Include
            >
        ) = apply { this.include = include }

        /**
         * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
         */
        fun language(language: kotlin.String) = apply { this.language = language }

        /**
         * @param ancestorId Search for regions whose ancestors include the requested ancestor region ID. Refer to the list of [top level regions](https://developers.expediagroup.com/docs/rapid/lodging/geography/geography-reference-lists).
         */
        fun ancestorId(ancestorId: kotlin.String) = apply { this.ancestorId = ancestorId }

        /**
         * @param area Filter the results to regions that intersect with a specified area.<br><br> The area may be defined in one of two ways:   * radius,region_id   * radius,latitude,longitude  Radius combined with region id would search an area that extends the number of kilometers out from the boundaries of the region in all directions.<br> Radius combined with a single point, specified by a latitude, longitude pair would search an area in a circle with the specified radius and the point as the center.<br> Radius should be specified in non-negative whole kilometers, decimals will return an error. A radius of 0 is allowed.<br> When specifying the area parameter, there will be a limit of 100 results, which can be narrowed further by the limit parameter.<br> Due to the number of results, unless `point_of_interest` is specified as the only type, regions of type `point_of_interest` will not be included in a request that filters to an area.<br><br> An example use case would be searching for the closest 3 airports within 50 kilometers of a specified point.<br>   `&type=airport&limit=3&area=50,37.227924,-93.310036`
         */
        fun area(area: kotlin.String) = apply { this.area = area }

        /**
         * @param countryCode Filter the results to a specified ISO 3166-1 alpha-2 country code.  For more information see: [https://www.iso.org/obp/ui/#search/code/](https://www.iso.org/obp/ui/#search/code/)
         */
        fun countryCode(
            countryCode: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.countryCode = countryCode }

        /**
         * @param countrySubdivisionCode Filter the results down to only the ISO 3166-2 country subdivision.
         */
        fun countrySubdivisionCode(
            countrySubdivisionCode: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.countrySubdivisionCode = countrySubdivisionCode }

        /**
         * @param iataLocationCode Search for regions by the requested 3-character IATA location code, which will apply to both iata_airport_code and iata_airport_metro_code. The code must be upper case.
         */
        fun iataLocationCode(iataLocationCode: kotlin.String) = apply { this.iataLocationCode = iataLocationCode }

        /**
         * @param limit Limit the number of results returned. Using the area parameter will impose a max value of 100 for this whether specified or not.
         */
        fun limit(limit: java.math.BigDecimal) = apply { this.limit = limit }

        /**
         * @param supplySource Options for which supply source you would like returned in the geography response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
         */
        fun supplySource(supplySource: kotlin.String) = apply { this.supplySource = supplySource }

        /**
         * @param type Filter the results to a specified region type.
         */
        fun type(
            type: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.type = type }

        /**
         * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
         */
        fun billingTerms(billingTerms: kotlin.String) = apply { this.billingTerms = billingTerms }

        /**
         * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
         */
        fun partnerPointOfSale(partnerPointOfSale: kotlin.String) = apply { this.partnerPointOfSale = partnerPointOfSale }

        /**
         * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
         */
        fun paymentTerms(paymentTerms: kotlin.String) = apply { this.paymentTerms = paymentTerms }

        /**
         * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
         */
        fun platformName(platformName: kotlin.String) = apply { this.platformName = platformName }

        fun build(): GetRegionsOperationParams {
            validateNullity()

            return GetRegionsOperationParams(
                customerSessionId = customerSessionId,
                include = include!!,
                language = language!!,
                ancestorId = ancestorId,
                area = area,
                countryCode = countryCode,
                countrySubdivisionCode = countrySubdivisionCode,
                iataLocationCode = iataLocationCode,
                limit = limit,
                supplySource = supplySource,
                type = type,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )
        }

        private fun validateNullity() {
            if (include == null) {
                throw NullPointerException("Required parameter include is missing")
            }
            if (language == null) {
                throw NullPointerException("Required parameter language is missing")
            }
        }
    }

    override fun getHeaders(): Map<String, String> {
        return buildMap {
            customerSessionId?.also {
                put("Customer-Session-Id", customerSessionId)
            }
            put("Accept", "application/json")
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            include?.also {
                put(
                    "include",
                    include.map { it.value }
                )
            }
            language?.also {
                put(
                    "language",
                    listOf(language)
                )
            }
            ancestorId?.also {
                put(
                    "ancestor_id",
                    listOf(ancestorId)
                )
            }
            area?.also {
                put(
                    "area",
                    listOf(area)
                )
            }
            countryCode?.also {
                put(
                    "country_code",
                    countryCode
                )
            }
            countrySubdivisionCode?.also {
                put(
                    "country_subdivision_code",
                    countrySubdivisionCode
                )
            }
            iataLocationCode?.also {
                put(
                    "iata_location_code",
                    listOf(iataLocationCode)
                )
            }
            limit?.also {
                put(
                    "limit",
                    listOf(limit.toString())
                )
            }
            supplySource?.also {
                put(
                    "supply_source",
                    listOf(supplySource)
                )
            }
            type?.also {
                put(
                    "type",
                    type
                )
            }
            billingTerms?.also {
                put(
                    "billing_terms",
                    listOf(billingTerms)
                )
            }
            partnerPointOfSale?.also {
                put(
                    "partner_point_of_sale",
                    listOf(partnerPointOfSale)
                )
            }
            paymentTerms?.also {
                put(
                    "payment_terms",
                    listOf(paymentTerms)
                )
            }
            platformName?.also {
                put(
                    "platform_name",
                    listOf(platformName)
                )
            }
        }
    }

    override fun getPathParams(): Map<String, String> {
        return buildMap {
        }
    }
}
