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
    "UnusedImport"
)

package com.expediagroup.sdk.rapid.models

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.rapid.models.Day
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 *
 * @param propertyId Expedia property ID.
 * @param days
 */
data class PropertyCalendarAvailability(
    // Expedia property ID.
    @JsonProperty("property_id")
    @field:Valid
    val propertyId: kotlin.String? = null,
    @JsonProperty("days")
    @field:Valid
    val days: kotlin.collections.List<Day>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var propertyId: kotlin.String? = null,
        private var days: kotlin.collections.List<Day>? = null
    ) {
        fun propertyId(propertyId: kotlin.String?) = apply { this.propertyId = propertyId }

        fun days(days: kotlin.collections.List<Day>?) = apply { this.days = days }

        fun build(): PropertyCalendarAvailability {
            val instance =
                PropertyCalendarAvailability(
                    propertyId = propertyId,
                    days = days
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PropertyCalendarAvailability) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(instance)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            propertyId = propertyId,
            days = days
        )
}
