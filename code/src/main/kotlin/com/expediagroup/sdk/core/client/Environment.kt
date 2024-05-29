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
package com.expediagroup.sdk.core.client

import com.expediagroup.sdk.core.constant.HeaderKey
import com.expediagroup.sdk.core.model.Properties
import com.expediagroup.sdk.core.model.TransactionId
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpHeaders

interface EnvironmentProvider {
    fun HttpRequestBuilder.appendHeaders(transactionId: TransactionId = TransactionId())
}

class DefaultEnvironmentProvider(
    namespace: String
) : EnvironmentProvider {
    private val properties = Properties.from(javaClass.classLoader.getResource("sdk.properties")!!)
    private val javaVersion = System.getProperty("java.version")
    private val operatingSystemName = System.getProperty("os.name")
    private val operatingSystemVersion = System.getProperty("os.version")
    private val userAgent = "expediagroup-sdk-java-$namespace/${properties["sdk-version"]!!} (Java $javaVersion; $operatingSystemName $operatingSystemVersion)"

    @Suppress("MemberVisibilityCanBePrivate")
    override fun HttpRequestBuilder.appendHeaders(transactionId: TransactionId) {
        with(headers) {
            append(HttpHeaders.UserAgent, userAgent)
            append(HeaderKey.X_SDK_TITLE, properties["sdk-title"]!!)
            append(HeaderKey.TRANSACTION_ID, transactionId.dequeue().toString())
        }
    }
}
