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

import com.expediagroup.sdk.core.model.Operation
import com.expediagroup.sdk.rapid.models.ChangeRoomDetailsRequest
import com.expediagroup.sdk.rapid.models.Link
import org.apache.commons.text.StringSubstitutor

/**
 * Change details of a room.
 * @property requestBody [ChangeRoomDetailsRequest]
 * @property params [ChangeRoomDetailsOperationParams]
 */
class ChangeRoomDetailsOperation private constructor(
    params: ChangeRoomDetailsOperationParams?,
    requestBody: ChangeRoomDetailsRequest?,
    link: Link?
) : Operation<
        ChangeRoomDetailsRequest
    >(
        url(params, link, "/v3/itineraries/{itinerary_id}/rooms/{room_id}"),
        "PUT",
        "changeRoomDetails",
        requestBody,
        params
    ) {
    @Deprecated("Switch order of arguments", ReplaceWith("Operation(params: ChangeRoomDetailsOperationParams, requestBody: ChangeRoomDetailsRequest?)"))
    constructor(
        requestBody: ChangeRoomDetailsRequest?,
        params: ChangeRoomDetailsOperationParams
    ) : this(params, requestBody)

    constructor(
        params: ChangeRoomDetailsOperationParams,
        requestBody: ChangeRoomDetailsRequest?
    ) : this(
        params,
        requestBody,
        null
    )

    constructor(
        link: Link,
        context: ChangeRoomDetailsOperationContext,
        requestBody: ChangeRoomDetailsRequest?
    ) : this(
        ChangeRoomDetailsOperationParams(context),
        requestBody,
        link
    )

    companion object : LinkableOperation {
        override fun pathPattern(): String {
            val paramsMap =
                buildMap {
                    put("itinerary_id", "[a-z0-9\\-]+")
                    put("room_id", "[a-z0-9\\-]+")
                }
            val substitutor = StringSubstitutor(paramsMap, "{", "}")
            return substitutor.replace("/v3/itineraries/{itinerary_id}/rooms/{room_id}")
        }
    }
}
