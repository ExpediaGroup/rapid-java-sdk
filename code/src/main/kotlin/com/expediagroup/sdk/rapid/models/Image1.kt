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
import com.expediagroup.sdk.rapid.models.Link1
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * An individual image.
 * @param caption The image caption.
 * @param link The url to retrieve the image.
 */
data class Image1(
    // The image caption.
    @JsonProperty("caption")
    @field:Valid
    val caption: kotlin.String? = null,
    // The url to retrieve the image.
    @JsonProperty("link")
    @field:Valid
    val link: kotlin.collections.Map<kotlin.String, Link1>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var caption: kotlin.String? = null,
        private var link: kotlin.collections.Map<kotlin.String, Link1>? = null
    ) {
        fun caption(caption: kotlin.String?) = apply { this.caption = caption }

        fun link(link: kotlin.collections.Map<kotlin.String, Link1>?) = apply { this.link = link }

        fun build(): Image1 {
            val instance =
                Image1(
                    caption = caption,
                    link = link
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
            caption = caption,
            link = link
        )
}
