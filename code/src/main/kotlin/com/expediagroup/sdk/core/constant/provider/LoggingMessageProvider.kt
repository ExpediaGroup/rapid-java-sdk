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
package com.expediagroup.sdk.core.constant.provider

import io.ktor.http.HttpStatusCode

internal object LoggingMessageProvider {
    fun getTokenExpiresInMessage(expiresIn: Int) = "New token expires in $expiresIn seconds"

    fun getResponseUnsuccessfulMessage(
        httpStatusCode: HttpStatusCode,
        transactionId: String?
    ) = "Unsuccessful response [$httpStatusCode]${getTransactionIdMessage(transactionId)}"

    fun getChosenProviderMessage(
        property: String,
        providerName: String
    ) = "Successfully loaded [$property] from [$providerName]"

    fun <T> getRuntimeConfigurationProviderMessage(
        property: String,
        value: T
    ) = "Setting [$property] to [$value] from runtime configuration provider"

    fun getResponseBodyMessage(
        body: String,
        transactionId: String?
    ) = "Response Body${getTransactionIdMessage(transactionId)}: $body"

    fun getRequestBodyMessage(
        body: String,
        transactionId: String?
    ) = "Request Body${getTransactionIdMessage(transactionId)}: $body"

    fun getTransactionIdMessage(transactionId: String?) = if (transactionId != null) " for transaction-id [$transactionId]" else ""
}
