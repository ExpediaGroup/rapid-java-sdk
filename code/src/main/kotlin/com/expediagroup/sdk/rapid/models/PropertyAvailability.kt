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
import com.expediagroup.sdk.rapid.models.Property
import com.expediagroup.sdk.rapid.models.PropertyAvailabilityLinks
import com.expediagroup.sdk.rapid.models.RoomAvailability
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 *
 * @param rooms Array of objects containing room information.
 * @param links
 */
data class PropertyAvailability(
    // Expedia property ID.
    @JsonProperty("property_id")
    @field:Valid
    override val propertyId: kotlin.String? = null,
    // A score to sort properties where the higher the value the better. It can be used to:<br> * Sort the properties on the response<br> * Sort properties across multiple responses in parallel searches for large regions<br>
    @JsonProperty("score")
    @field:Valid
    override val score: java.math.BigDecimal? = null,
    // Array of objects containing room information.
    @JsonProperty("rooms")
    @field:Valid
    val rooms: kotlin.collections.List<RoomAvailability>? = null,
    @JsonProperty("links")
    @field:Valid
    val links: PropertyAvailabilityLinks? = null
) : Property {
    @JsonProperty("status")
    override val status: Property.Status = Property.Status.AVAILABLE

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var propertyId: kotlin.String? = null,
        private var score: java.math.BigDecimal? = null,
        private var rooms: kotlin.collections.List<RoomAvailability>? = null,
        private var links: PropertyAvailabilityLinks? = null
    ) {
        fun propertyId(propertyId: kotlin.String?) = apply { this.propertyId = propertyId }

        fun score(score: java.math.BigDecimal?) = apply { this.score = score }

        fun rooms(rooms: kotlin.collections.List<RoomAvailability>?) = apply { this.rooms = rooms }

        fun links(links: PropertyAvailabilityLinks?) = apply { this.links = links }

        fun build(): PropertyAvailability {
            val instance =
                PropertyAvailability(
                    propertyId = propertyId,
                    score = score,
                    rooms = rooms,
                    links = links
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PropertyAvailability) {
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
            score = score,
            rooms = rooms,
            links = links
        )
}
