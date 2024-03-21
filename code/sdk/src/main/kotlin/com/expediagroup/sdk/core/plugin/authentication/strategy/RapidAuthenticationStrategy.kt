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
package com.expediagroup.sdk.core.plugin.authentication.strategy

import com.expediagroup.sdk.core.constant.Authentication
import com.expediagroup.sdk.core.constant.Constant
import com.expediagroup.sdk.core.constant.SignatureValues
import com.expediagroup.sdk.core.plugin.authentication.AuthenticationConfiguration
import io.ktor.client.request.HttpRequestBuilder
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.spec.MGF1ParameterSpec
import java.time.Instant

internal class RapidAuthenticationStrategy(private val configs: AuthenticationConfiguration) : AuthenticationStrategy {
    private var signature: String = Constant.EMPTY_STRING

    override fun isTokenAboutToExpire(): Boolean = true

    override fun renewToken() {
        val credentials = configs.credentials
        signature = calculateSignature(credentials.key, credentials.secret, Instant.now().epochSecond)
    }

    override fun isIdentityRequest(request: HttpRequestBuilder) = false

    override fun getAuthorizationHeader() = createAuthorizationHeader(signature)

    private fun createAuthorizationHeader(signature: String?): String = "${Authentication.EAN} $signature"

    private fun calculateSignature(
        apiKey: String,
        secret: String,
        timestamp: Long
    ): String {
        val toBeHashed = apiKey + secret + timestamp
        val messageDigest = MessageDigest.getInstance(MGF1ParameterSpec.SHA512.digestAlgorithm)
        val bytes = messageDigest.digest(toBeHashed.toByteArray(StandardCharsets.UTF_8))
        val signature =
            buildString {
                bytes.forEach {
                    append(((it.toInt() and SignatureValues.ONE_BYTE_MASK) + SignatureValues.INCREMENT).toString(SignatureValues.RADIX).substring(BigInteger.ONE.toInt()))
                }
            }
        return "${SignatureValues.API_KEY}=$apiKey,${SignatureValues.SIGNATURE}=$signature,${SignatureValues.TIMESTAMP}=$timestamp"
    }
}
