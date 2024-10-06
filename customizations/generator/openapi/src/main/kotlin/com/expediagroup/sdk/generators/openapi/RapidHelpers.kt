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
package com.expediagroup.sdk.generators.openapi

import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Template
import org.openapitools.codegen.CodegenOperation
import java.io.Writer

val linkableOperations =
    setOf(
        "changeRoomDetails",
        "commitChange",
        "deleteHeldBooking",
        "deleteRoom",
        "getAdditionalAvailability",
        "getAvailability",
        "getPaymentOptions",
        "getPropertyContent",
        "getReservationByItineraryId",
        "postItinerary",
        "postPaymentSessions",
        "priceCheck",
        "putCompletePaymentSession",
        "putResumeBooking"
    )

val isLinkable =
    object : Mustache.InvertibleLambda {
        override fun execute(
            fragment: Template.Fragment,
            writer: Writer
        ) {
            val operation = fragment.context() as CodegenOperation
            if (linkableOperations.contains(operation.operationId)) {
                fragment.execute(writer)
            }
        }

        override fun executeInverse(
            fragment: Template.Fragment,
            writer: Writer
        ) {
            val operation = fragment.context() as CodegenOperation
            if (!linkableOperations.contains(operation.operationId)) {
                fragment.execute(writer)
            }
        }
    }

val hasRequiredContext =
    object : Mustache.InvertibleLambda {
        override fun execute(
            fragment: Template.Fragment,
            writer: Writer
        ) {
            val operation = fragment.context() as CodegenOperation
            val headers = operation.headerParams
            if (headers.any { it.required }) {
                fragment.execute(writer)
            }
        }

        override fun executeInverse(
            fragment: Template.Fragment,
            writer: Writer
        ) {
            val operation = fragment.context() as CodegenOperation
            val headers = operation.headerParams
            if (headers.none { it.required }) {
                fragment.execute(writer)
            }
        }
    }

val rapidHelpers =
    mapOf(
        "isLinkable" to { isLinkable },
        "hasRequiredContext" to { hasRequiredContext }
    )
