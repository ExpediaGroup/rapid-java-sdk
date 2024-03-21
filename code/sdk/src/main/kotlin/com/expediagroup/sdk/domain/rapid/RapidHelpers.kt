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
package com.expediagroup.sdk.domain.rapid

import com.expediagroup.sdk.core.client.BaseRapidClient
import com.expediagroup.sdk.core.client.ClientHelpers
import com.expediagroup.sdk.core.constant.HeaderKey
import com.expediagroup.sdk.core.model.Response

class RapidHelpers(client: BaseRapidClient) : ClientHelpers(client) {
    /** Extracts the token parameter from a URL string if it exists; otherwise, returns null. */
    fun extractToken(url: String): String? = Regex("token=([^&]*)").find(url)?.groupValues?.getOrNull(1)

    /** Extracts the room booking ID from a URL string if it exists; otherwise, returns null. */
    fun extractRoomBookingId(url: String): String? = Regex("rooms\\/([a-z0-9-]+)").find(url)?.groupValues?.getOrNull(1)

    /** Extracts the transaction ID from a response object if it exists; otherwise, returns null. */
    fun <T> extractTransactionId(response: Response<T>): String? = response.headers[HeaderKey.TRANSACTION_ID]?.first()
}
