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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 *
 * @param url The url of the image.
 * @param width The width of the image.
 * @param height The height of the image.
 */
data class Image1(
    // The url of the image.
    @JsonProperty("url")
    @field:Valid
    val url: kotlin.String? = null,
    // The width of the image.
    @JsonProperty("width")
    @field:Valid
    val width: kotlin.String? = null,
    // The height of the image.
    @JsonProperty("height")
    @field:Valid
    val height: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var url: kotlin.String? = null,
        private var width: kotlin.String? = null,
        private var height: kotlin.String? = null
    ) {
        fun url(url: kotlin.String?) = apply { this.url = url }

        fun width(width: kotlin.String?) = apply { this.width = width }

        fun height(height: kotlin.String?) = apply { this.height = height }

        fun build(): Image1 {
            val instance =
                Image1(
                    url = url,
                    width = width,
                    height = height
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Image1) {
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
            url = url,
            width = width,
            height = height
        )
}
