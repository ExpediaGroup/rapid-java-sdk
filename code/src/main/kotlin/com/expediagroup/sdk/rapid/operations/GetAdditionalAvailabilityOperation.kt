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

import com.expediagroup.sdk.core.model.Nothing
import com.expediagroup.sdk.core.model.Operation
import com.expediagroup.sdk.rapid.models.Link
import org.apache.commons.text.StringSubstitutor

/**
 * Get additional property room rates and availability
 * @property params [GetAdditionalAvailabilityOperationParams]
 */
class GetAdditionalAvailabilityOperation private constructor(
    params: GetAdditionalAvailabilityOperationParams?,
    link: Link?
) : Operation<
        Nothing
    >(
        url(params, link, "/v3/properties/{property_id}/availability"),
        "GET",
        "getAdditionalAvailability",
        null,
        params
    ) {
    constructor(
        params: GetAdditionalAvailabilityOperationParams
    ) : this(
        params,
        null
    )

    constructor(
        link: Link,
        context: GetAdditionalAvailabilityOperationContext?
    ) : this(
        GetAdditionalAvailabilityOperationParams(context),
        link
    )

    companion object : LinkableOperation {
        override fun pathPattern(): String {
            val paramsMap =
                buildMap {
                    put("property_id", "[a-z0-9\\-]+")
                }
            val substitutor = StringSubstitutor(paramsMap, "{", "}")
            return substitutor.replace("/v3/properties/{property_id}/availability")
        }
    }
}
