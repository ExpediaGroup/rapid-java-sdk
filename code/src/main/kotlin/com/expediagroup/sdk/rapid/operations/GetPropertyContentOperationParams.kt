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
import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.ktor.http.Headers
import io.ktor.http.Parameters
import io.ktor.http.parseUrlEncodedParameters
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import java.net.URI
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
 * @property supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
 * @property allInclusive Search to include properties that have the requested `all_inclusive` values equal to true. If this parameter is not supplied, all `all_inclusive` scenarios are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested scenarios.   * `all_rate_plans` - Return properties where `all_inclusive.all_rate_plans` is true.   * `some_rate_plans` = Return properties where `all_inclusive.some_rate_plans` is true.
 * @property amenityId The ID of the amenity you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested amenity IDs. This is currently only capable of searching for property level amenities. Room and rate level amenities cannot be searched on.
 * @property attributeId The ID of the attribute you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested attribute IDs.
 * @property brandId The ID of the brand you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested brand IDs.
 * @property businessModel Search for properties with the requested business model enabled. This parameter can be supplied multiple times with different values, which will return all properties that match any of the requested business models. The value must be lower case.   * `expedia_collect` - Return only properties where the payment is collected by Expedia.   * `property_collect` - Return only properties where the payment is collected at the property.
 * @property categoryId Search to include properties that have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested category IDs.
 * @property categoryIdExclude Search to exclude properties that do not have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will exclude properties that match any of the requested category IDs.
 * @property chainId The ID of the chain you want to search for. These chain IDs can be positive and negative numbers. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested chain IDs.
 * @property countryCode Search for properties with the requested country code, in ISO 3166-1 alpha-2 format. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested country codes.
 * @property dateAddedEnd Search for properties added on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
 * @property dateAddedStart Search for properties added on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
 * @property dateUpdatedEnd Search for properties updated on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
 * @property dateUpdatedStart Search for properties updated on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
 * @property include Each time this parameter is specified will add to the list of fields and associated objects returned in the response. All values and field names are lower case. The values `property_ids` and `catalog` will continue to behave as specified below for backwards compatibility. All other top level field names will add the specified field to the list of fields returned in the response. See the response schema for a full list of top level field names. Additionally, the field `property_id` will always be returned regardless of what include values are passed.<br><br> Possible values:  * `property_ids` - ***DEPRECATED*** - Please use `property_id` which matches the response field name.  * `catalog` - Include all property catalog fields. See     [Property Catalog File endpoint](https://developers.expediagroup.com/docs/rapid/resources/rapid-api#get-/files/properties/catalog) for a list of fields.  * `property_id` - Passing in the value `property_id` and no other values will limit the response to only      `property_id`. Not necessary to include in combination with other field name values, as it will always      be returned.  * All field names found at the top level of the property content response are now valid values for     inclusion.
 * @property multiUnit Search for multi-unit properties. If this parameter is not supplied, both single-unit and multi-unit properties will be included.   * `true` - Include only properties that are multi-unit.   * `false` - Do not include properties that are multi-unit.
 * @property propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
 * @property propertyRatingMax Search for properties with a property rating less than or equal to the requested rating. The highest property rating value is 5.0.
 * @property propertyRatingMin Search for properties with a property rating greater than or equal to the requested rating. The lowest property rating value is 0.0.
 * @property spokenLanguageId The id of the spoken language you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested spoken languages. The language code as a subset of BCP47 format.
 * @property billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
 * @property partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 * @property paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
 * @property platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 */
@JsonDeserialize(builder = GetPropertyContentOperationParams.Builder::class)
data class GetPropertyContentOperationParams
    internal constructor(
        @field:Valid
        val customerSessionId: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val language: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val supplySource: kotlin.String? = null,
        @field:Valid
        val allInclusive: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:Valid
        val amenityId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:Valid
        val attributeId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:Valid
        val brandId: kotlin.collections.List<
            kotlin.String
        >? = null,
        val businessModel: kotlin.collections.List<
            GetPropertyContentOperationParams.BusinessModel
        >? = null,
        @field:Valid
        val categoryId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:Valid
        val categoryIdExclude: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:Valid
        val chainId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:Valid
        val countryCode: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:Valid
        val dateAddedEnd: kotlin.String? = null,
        @field:Valid
        val dateAddedStart: kotlin.String? = null,
        @field:Valid
        val dateUpdatedEnd: kotlin.String? = null,
        @field:Valid
        val dateUpdatedStart: kotlin.String? = null,
        @field:Valid
        val include: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:Valid
        val multiUnit: kotlin.Boolean? = null,
        @field:Valid
        val propertyId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:Valid
        val propertyRatingMax: kotlin.String? = null,
        @field:Valid
        val propertyRatingMin: kotlin.String? = null,
        @field:Valid
        val spokenLanguageId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:Valid
        val billingTerms: kotlin.String? = null,
        @field:Valid
        val partnerPointOfSale: kotlin.String? = null,
        @field:Valid
        val paymentTerms: kotlin.String? = null,
        @field:Valid
        val platformName: kotlin.String? = null,
        private val dummy: Unit
    ) :
    OperationParams {
        companion object {
            @JvmStatic
            fun builder() = Builder()
        }

        constructor(
            customerSessionId: kotlin.String? =
                null,
            language: kotlin.String,
            supplySource: kotlin.String,
            allInclusive: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            amenityId: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            attributeId: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            brandId: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            businessModel: kotlin.collections.List<
                GetPropertyContentOperationParams.BusinessModel
            >? =
                null,
            categoryId: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            categoryIdExclude: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            chainId: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            countryCode: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            dateAddedEnd: kotlin.String? =
                null,
            dateAddedStart: kotlin.String? =
                null,
            dateUpdatedEnd: kotlin.String? =
                null,
            dateUpdatedStart: kotlin.String? =
                null,
            include: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            multiUnit: kotlin.Boolean? =
                null,
            propertyId: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            propertyRatingMax: kotlin.String? =
                null,
            propertyRatingMin: kotlin.String? =
                null,
            spokenLanguageId: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            billingTerms: kotlin.String? =
                null,
            partnerPointOfSale: kotlin.String? =
                null,
            paymentTerms: kotlin.String? =
                null,
            platformName: kotlin.String? =
                null
        ) : this(
            customerSessionId = customerSessionId,
            language = language,
            supplySource = supplySource,
            allInclusive = allInclusive,
            amenityId = amenityId,
            attributeId = attributeId,
            brandId = brandId,
            businessModel = businessModel,
            categoryId = categoryId,
            categoryIdExclude = categoryIdExclude,
            chainId = chainId,
            countryCode = countryCode,
            dateAddedEnd = dateAddedEnd,
            dateAddedStart = dateAddedStart,
            dateUpdatedEnd = dateUpdatedEnd,
            dateUpdatedStart = dateUpdatedStart,
            include = include,
            multiUnit = multiUnit,
            propertyId = propertyId,
            propertyRatingMax = propertyRatingMax,
            propertyRatingMin = propertyRatingMin,
            spokenLanguageId = spokenLanguageId,
            billingTerms = billingTerms,
            partnerPointOfSale = partnerPointOfSale,
            paymentTerms = paymentTerms,
            platformName = platformName,
            dummy = Unit
        )

        constructor(context: GetPropertyContentOperationContext?) : this(
            customerSessionId = context?.customerSessionId,
            dummy = Unit
        )

        enum class BusinessModel(
            val value: kotlin.String
        ) {
            EXPEDIA_COLLECT("expedia_collect"),

            PROPERTY_COLLECT("property_collect")

            ;

            companion object {
                private val map = entries.associateBy { it.value }

                infix fun from(value: kotlin.String) = map[value]
            }
        }

        class Builder(
            @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
            @JsonProperty("language") private var language: kotlin.String? = null,
            @JsonProperty("supply_source") private var supplySource: kotlin.String? = null,
            @JsonProperty("all_inclusive") private var allInclusive: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("amenity_id") private var amenityId: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("attribute_id") private var attributeId: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("brand_id") private var brandId: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("business_model") private var businessModel: kotlin.collections.List<
                GetPropertyContentOperationParams.BusinessModel
            >? = null,
            @JsonProperty("category_id") private var categoryId: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("category_id_exclude") private var categoryIdExclude: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("chain_id") private var chainId: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("country_code") private var countryCode: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("date_added_end") private var dateAddedEnd: kotlin.String? = null,
            @JsonProperty("date_added_start") private var dateAddedStart: kotlin.String? = null,
            @JsonProperty("date_updated_end") private var dateUpdatedEnd: kotlin.String? = null,
            @JsonProperty("date_updated_start") private var dateUpdatedStart: kotlin.String? = null,
            @JsonProperty("include") private var include: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("multi_unit") private var multiUnit: kotlin.Boolean? = null,
            @JsonProperty("property_id") private var propertyId: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("property_rating_max") private var propertyRatingMax: kotlin.String? = null,
            @JsonProperty("property_rating_min") private var propertyRatingMin: kotlin.String? = null,
            @JsonProperty("spoken_language_id") private var spokenLanguageId: kotlin.collections.List<
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
             * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
             */
            fun language(language: kotlin.String) = apply { this.language = language }

            /**
             * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
             */
            fun supplySource(supplySource: kotlin.String) = apply { this.supplySource = supplySource }

            /**
             * @param allInclusive Search to include properties that have the requested `all_inclusive` values equal to true. If this parameter is not supplied, all `all_inclusive` scenarios are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested scenarios.   * `all_rate_plans` - Return properties where `all_inclusive.all_rate_plans` is true.   * `some_rate_plans` = Return properties where `all_inclusive.some_rate_plans` is true.
             */
            fun allInclusive(
                allInclusive: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.allInclusive = allInclusive }

            /**
             * @param amenityId The ID of the amenity you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested amenity IDs. This is currently only capable of searching for property level amenities. Room and rate level amenities cannot be searched on.
             */
            fun amenityId(
                amenityId: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.amenityId = amenityId }

            /**
             * @param attributeId The ID of the attribute you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested attribute IDs.
             */
            fun attributeId(
                attributeId: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.attributeId = attributeId }

            /**
             * @param brandId The ID of the brand you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested brand IDs.
             */
            fun brandId(
                brandId: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.brandId = brandId }

            /**
             * @param businessModel Search for properties with the requested business model enabled. This parameter can be supplied multiple times with different values, which will return all properties that match any of the requested business models. The value must be lower case.   * `expedia_collect` - Return only properties where the payment is collected by Expedia.   * `property_collect` - Return only properties where the payment is collected at the property.
             */
            fun businessModel(
                businessModel: kotlin.collections.List<
                    GetPropertyContentOperationParams.BusinessModel
                >
            ) = apply { this.businessModel = businessModel }

            /**
             * @param categoryId Search to include properties that have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested category IDs.
             */
            fun categoryId(
                categoryId: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.categoryId = categoryId }

            /**
             * @param categoryIdExclude Search to exclude properties that do not have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will exclude properties that match any of the requested category IDs.
             */
            fun categoryIdExclude(
                categoryIdExclude: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.categoryIdExclude = categoryIdExclude }

            /**
             * @param chainId The ID of the chain you want to search for. These chain IDs can be positive and negative numbers. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested chain IDs.
             */
            fun chainId(
                chainId: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.chainId = chainId }

            /**
             * @param countryCode Search for properties with the requested country code, in ISO 3166-1 alpha-2 format. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested country codes.
             */
            fun countryCode(
                countryCode: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.countryCode = countryCode }

            /**
             * @param dateAddedEnd Search for properties added on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
             */
            fun dateAddedEnd(dateAddedEnd: kotlin.String) = apply { this.dateAddedEnd = dateAddedEnd }

            /**
             * @param dateAddedStart Search for properties added on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
             */
            fun dateAddedStart(dateAddedStart: kotlin.String) = apply { this.dateAddedStart = dateAddedStart }

            /**
             * @param dateUpdatedEnd Search for properties updated on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
             */
            fun dateUpdatedEnd(dateUpdatedEnd: kotlin.String) = apply { this.dateUpdatedEnd = dateUpdatedEnd }

            /**
             * @param dateUpdatedStart Search for properties updated on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
             */
            fun dateUpdatedStart(dateUpdatedStart: kotlin.String) = apply { this.dateUpdatedStart = dateUpdatedStart }

            /**
             * @param include Each time this parameter is specified will add to the list of fields and associated objects returned in the response. All values and field names are lower case. The values `property_ids` and `catalog` will continue to behave as specified below for backwards compatibility. All other top level field names will add the specified field to the list of fields returned in the response. See the response schema for a full list of top level field names. Additionally, the field `property_id` will always be returned regardless of what include values are passed.<br><br> Possible values:  * `property_ids` - ***DEPRECATED*** - Please use `property_id` which matches the response field name.  * `catalog` - Include all property catalog fields. See     [Property Catalog File endpoint](https://developers.expediagroup.com/docs/rapid/resources/rapid-api#get-/files/properties/catalog) for a list of fields.  * `property_id` - Passing in the value `property_id` and no other values will limit the response to only      `property_id`. Not necessary to include in combination with other field name values, as it will always      be returned.  * All field names found at the top level of the property content response are now valid values for     inclusion.
             */
            fun include(
                include: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.include = include }

            /**
             * @param multiUnit Search for multi-unit properties. If this parameter is not supplied, both single-unit and multi-unit properties will be included.   * `true` - Include only properties that are multi-unit.   * `false` - Do not include properties that are multi-unit.
             */
            fun multiUnit(multiUnit: kotlin.Boolean) = apply { this.multiUnit = multiUnit }

            /**
             * @param propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
             */
            fun propertyId(
                propertyId: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.propertyId = propertyId }

            /**
             * @param propertyRatingMax Search for properties with a property rating less than or equal to the requested rating. The highest property rating value is 5.0.
             */
            fun propertyRatingMax(propertyRatingMax: kotlin.String) = apply { this.propertyRatingMax = propertyRatingMax }

            /**
             * @param propertyRatingMin Search for properties with a property rating greater than or equal to the requested rating. The lowest property rating value is 0.0.
             */
            fun propertyRatingMin(propertyRatingMin: kotlin.String) = apply { this.propertyRatingMin = propertyRatingMin }

            /**
             * @param spokenLanguageId The id of the spoken language you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested spoken languages. The language code as a subset of BCP47 format.
             */
            fun spokenLanguageId(
                spokenLanguageId: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.spokenLanguageId = spokenLanguageId }

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

            companion object {
                @JvmStatic
                fun from(link: GetPropertyContentOperationLink): Builder {
                    val uri = link.href?.let { URI(it) }
                    val params = uri?.query?.parseUrlEncodedParameters()

                    val builder = Builder()

                    val customerSessionId =
                        params?.get("customerSessionId")

                    customerSessionId?.let {
                        builder.customerSessionId(
                            it
                        )
                    }
                    val language =
                        params?.get("language")

                    language?.let {
                        builder.language(
                            it
                        )
                    }
                    val supplySource =
                        params?.get("supplySource")

                    supplySource?.let {
                        builder.supplySource(
                            it
                        )
                    }
                    val allInclusive =
                        params?.getAll("allInclusive")
                    params?.get("allInclusive")

                    allInclusive?.let {
                        builder.allInclusive(
                            it
                        )
                    }
                    val amenityId =
                        params?.getAll("amenityId")
                    params?.get("amenityId")

                    amenityId?.let {
                        builder.amenityId(
                            it
                        )
                    }
                    val attributeId =
                        params?.getAll("attributeId")
                    params?.get("attributeId")

                    attributeId?.let {
                        builder.attributeId(
                            it
                        )
                    }
                    val brandId =
                        params?.getAll("brandId")
                    params?.get("brandId")

                    brandId?.let {
                        builder.brandId(
                            it
                        )
                    }
                    val businessModel =
                        params?.getAll("businessModel")
                            ?.mapNotNull { BusinessModel.from(it) }
                    params?.get("businessModel")
                        ?.let { BusinessModel.from(it) }

                    businessModel?.let {
                        builder.businessModel(
                            it
                        )
                    }
                    val categoryId =
                        params?.getAll("categoryId")
                    params?.get("categoryId")

                    categoryId?.let {
                        builder.categoryId(
                            it
                        )
                    }
                    val categoryIdExclude =
                        params?.getAll("categoryIdExclude")
                    params?.get("categoryIdExclude")

                    categoryIdExclude?.let {
                        builder.categoryIdExclude(
                            it
                        )
                    }
                    val chainId =
                        params?.getAll("chainId")
                    params?.get("chainId")

                    chainId?.let {
                        builder.chainId(
                            it
                        )
                    }
                    val countryCode =
                        params?.getAll("countryCode")
                    params?.get("countryCode")

                    countryCode?.let {
                        builder.countryCode(
                            it
                        )
                    }
                    val dateAddedEnd =
                        params?.get("dateAddedEnd")

                    dateAddedEnd?.let {
                        builder.dateAddedEnd(
                            it
                        )
                    }
                    val dateAddedStart =
                        params?.get("dateAddedStart")

                    dateAddedStart?.let {
                        builder.dateAddedStart(
                            it
                        )
                    }
                    val dateUpdatedEnd =
                        params?.get("dateUpdatedEnd")

                    dateUpdatedEnd?.let {
                        builder.dateUpdatedEnd(
                            it
                        )
                    }
                    val dateUpdatedStart =
                        params?.get("dateUpdatedStart")

                    dateUpdatedStart?.let {
                        builder.dateUpdatedStart(
                            it
                        )
                    }
                    val include =
                        params?.getAll("include")
                    params?.get("include")

                    include?.let {
                        builder.include(
                            it
                        )
                    }
                    val multiUnit =
                        params?.get("multiUnit")

                    multiUnit?.let {
                        builder.multiUnit(
                            it
                                .toBoolean()
                        )
                    }
                    val propertyId =
                        params?.getAll("propertyId")
                    params?.get("propertyId")

                    propertyId?.let {
                        builder.propertyId(
                            it
                        )
                    }
                    val propertyRatingMax =
                        params?.get("propertyRatingMax")

                    propertyRatingMax?.let {
                        builder.propertyRatingMax(
                            it
                        )
                    }
                    val propertyRatingMin =
                        params?.get("propertyRatingMin")

                    propertyRatingMin?.let {
                        builder.propertyRatingMin(
                            it
                        )
                    }
                    val spokenLanguageId =
                        params?.getAll("spokenLanguageId")
                    params?.get("spokenLanguageId")

                    spokenLanguageId?.let {
                        builder.spokenLanguageId(
                            it
                        )
                    }
                    val billingTerms =
                        params?.get("billingTerms")

                    billingTerms?.let {
                        builder.billingTerms(
                            it
                        )
                    }
                    val partnerPointOfSale =
                        params?.get("partnerPointOfSale")

                    partnerPointOfSale?.let {
                        builder.partnerPointOfSale(
                            it
                        )
                    }
                    val paymentTerms =
                        params?.get("paymentTerms")

                    paymentTerms?.let {
                        builder.paymentTerms(
                            it
                        )
                    }
                    val platformName =
                        params?.get("platformName")

                    platformName?.let {
                        builder.platformName(
                            it
                        )
                    }

                    return builder
                }
            }

            fun build(): GetPropertyContentOperationParams {
                val params =
                    GetPropertyContentOperationParams(
                        customerSessionId = customerSessionId,
                        language = language!!,
                        supplySource = supplySource!!,
                        allInclusive = allInclusive,
                        amenityId = amenityId,
                        attributeId = attributeId,
                        brandId = brandId,
                        businessModel = businessModel,
                        categoryId = categoryId,
                        categoryIdExclude = categoryIdExclude,
                        chainId = chainId,
                        countryCode = countryCode,
                        dateAddedEnd = dateAddedEnd,
                        dateAddedStart = dateAddedStart,
                        dateUpdatedEnd = dateUpdatedEnd,
                        dateUpdatedStart = dateUpdatedStart,
                        include = include,
                        multiUnit = multiUnit,
                        propertyId = propertyId,
                        propertyRatingMax = propertyRatingMax,
                        propertyRatingMin = propertyRatingMin,
                        spokenLanguageId = spokenLanguageId,
                        billingTerms = billingTerms,
                        partnerPointOfSale = partnerPointOfSale,
                        paymentTerms = paymentTerms,
                        platformName = platformName
                    )

                validate(params)

                return params
            }

            private fun validate(params: GetPropertyContentOperationParams) {
                val validator =
                    Validation
                        .byDefaultProvider()
                        .configure()
                        .messageInterpolator(ParameterMessageInterpolator())
                        .buildValidatorFactory()
                        .validator

                val violations = validator.validate(params)

                if (violations.isNotEmpty()) {
                    throw PropertyConstraintViolationException(
                        constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                    )
                }
            }
        }

        fun toBuilder() =
            Builder(
                customerSessionId = customerSessionId,
                language = language,
                supplySource = supplySource,
                allInclusive = allInclusive,
                amenityId = amenityId,
                attributeId = attributeId,
                brandId = brandId,
                businessModel = businessModel,
                categoryId = categoryId,
                categoryIdExclude = categoryIdExclude,
                chainId = chainId,
                countryCode = countryCode,
                dateAddedEnd = dateAddedEnd,
                dateAddedStart = dateAddedStart,
                dateUpdatedEnd = dateUpdatedEnd,
                dateUpdatedStart = dateUpdatedStart,
                include = include,
                multiUnit = multiUnit,
                propertyId = propertyId,
                propertyRatingMax = propertyRatingMax,
                propertyRatingMin = propertyRatingMin,
                spokenLanguageId = spokenLanguageId,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        override fun getHeaders(): Headers =
            Headers.build {
                customerSessionId?.let {
                    append("Customer-Session-Id", it)
                }
                append("Accept", "application/json")
            }

        override fun getQueryParams(): Parameters =
            Parameters.build {
                language?.let {
                    append("language", it)
                }
                supplySource?.let {
                    append("supply_source", it)
                }
                allInclusive?.let {
                    appendAll("all_inclusive", it)
                }
                amenityId?.let {
                    appendAll("amenity_id", it)
                }
                attributeId?.let {
                    appendAll("attribute_id", it)
                }
                brandId?.let {
                    appendAll("brand_id", it)
                }
                businessModel?.let {
                    appendAll("business_model", it.map { it.value })
                }
                categoryId?.let {
                    appendAll("category_id", it)
                }
                categoryIdExclude?.let {
                    appendAll("category_id_exclude", it)
                }
                chainId?.let {
                    appendAll("chain_id", it)
                }
                countryCode?.let {
                    appendAll("country_code", it)
                }
                dateAddedEnd?.let {
                    append("date_added_end", it)
                }
                dateAddedStart?.let {
                    append("date_added_start", it)
                }
                dateUpdatedEnd?.let {
                    append("date_updated_end", it)
                }
                dateUpdatedStart?.let {
                    append("date_updated_start", it)
                }
                include?.let {
                    appendAll("include", it)
                }
                multiUnit?.let {
                    append("multi_unit", it.toString())
                }
                propertyId?.let {
                    appendAll("property_id", it)
                }
                propertyRatingMax?.let {
                    append("property_rating_max", it)
                }
                propertyRatingMin?.let {
                    append("property_rating_min", it)
                }
                spokenLanguageId?.let {
                    appendAll("spoken_language_id", it)
                }
                billingTerms?.let {
                    append("billing_terms", it)
                }
                partnerPointOfSale?.let {
                    append("partner_point_of_sale", it)
                }
                paymentTerms?.let {
                    append("payment_terms", it)
                }
                platformName?.let {
                    append("platform_name", it)
                }
            }

        override fun getPathParams(): Map<String, String> =
            buildMap {
            }
    }
