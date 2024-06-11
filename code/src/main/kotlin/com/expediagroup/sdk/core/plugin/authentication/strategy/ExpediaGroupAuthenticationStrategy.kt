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

import com.expediagroup.sdk.core.client.Client
import com.expediagroup.sdk.core.configuration.Credentials
import com.expediagroup.sdk.core.constant.Authentication
import com.expediagroup.sdk.core.constant.Constant
import com.expediagroup.sdk.core.constant.ExceptionMessage
import com.expediagroup.sdk.core.constant.LoggingMessage
import com.expediagroup.sdk.core.constant.provider.LoggingMessageProvider
import com.expediagroup.sdk.core.model.exception.service.ExpediaGroupAuthException
import com.expediagroup.sdk.core.model.getTransactionId
import com.expediagroup.sdk.core.plugin.authentication.AuthenticationConfiguration
import com.expediagroup.sdk.core.plugin.logging.ExpediaGroupLoggerFactory
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.basicAuth
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.ParametersBuilder
import io.ktor.http.clone
import io.ktor.http.contentType
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

internal class ExpediaGroupAuthenticationStrategy(
    private val client: Client,
    private val configs: AuthenticationConfiguration
) : AuthenticationStrategy {
    private val log = ExpediaGroupLoggerFactory.getLogger(javaClass)
    private var bearerTokenStorage = BearerTokensInfo.emptyBearerTokenInfo

    override fun loadAuth(auth: Auth) {
        auth.bearer {
            sendWithoutRequest { request ->
                isIdentityRequest(request)
            }
        }
    }

    override fun isTokenAboutToExpire(): Boolean = bearerTokenStorage.isAboutToExpire().also { if (it) log.info(LoggingMessage.TOKEN_EXPIRED) }

    override fun renewToken() {
        val httpClient = client.httpClient
        log.info(LoggingMessage.TOKEN_RENEWAL_IN_PROGRESS)
        clearTokens(httpClient)
        val renewTokenResponse =
            runBlocking {
                httpClient.request {
                    method = HttpMethod.Post
                    parameter(Authentication.GRANT_TYPE, Authentication.CLIENT_CREDENTIALS)
                    contentType(ContentType.Application.FormUrlEncoded)
                    url(configs.authUrl)
                    basicAuth(configs.credentials)
                    with(client) { appendHeaders() }
                }
            }
        if (renewTokenResponse.status.value !in Constant.SUCCESSFUL_STATUS_CODES_RANGE) {
            throw ExpediaGroupAuthException(renewTokenResponse.status, ExceptionMessage.AUTHENTICATION_FAILURE, renewTokenResponse.headers.getTransactionId())
        }
        val renewedTokenInfo: TokenResponse = runBlocking { renewTokenResponse.body() }
        log.info(LoggingMessage.TOKEN_RENEWAL_SUCCESSFUL)
        log.info(LoggingMessageProvider.getTokenExpiresInMessage(renewedTokenInfo.expiresIn))
        bearerTokenStorage =
            BearerTokensInfo(
                BearerTokens(renewedTokenInfo.accessToken, renewedTokenInfo.accessToken),
                renewedTokenInfo.expiresIn
            )
        bearerTokenStorage
    }

    private fun clearTokens(client: HttpClient) {
        log.info(LoggingMessage.TOKEN_CLEARING_IN_PROGRESS)
        client.plugin(Auth).providers.filterIsInstance<BearerAuthProvider>().first().clearToken()
        bearerTokenStorage = BearerTokensInfo.emptyBearerTokenInfo
        log.info(LoggingMessage.TOKEN_CLEARING_SUCCESSFUL)
    }

    private fun getTokens(): BearerTokens = bearerTokenStorage.bearerTokens

    private fun HttpRequestBuilder.basicAuth(credentials: Credentials) {
        basicAuth(
            credentials.key,
            credentials.secret
        )
    }

    override fun isIdentityRequest(request: HttpRequestBuilder): Boolean = request.url.clone().apply { encodedParameters = ParametersBuilder() }.buildString() == configs.authUrl

    override fun getAuthorizationHeader() = "${Authentication.BEARER} ${getTokens().accessToken}"

    internal open class BearerTokensInfo(val bearerTokens: BearerTokens, expiresIn: Int) {
        private val expiryDate: LocalDateTime

        init {
            this.expiryDate = calculateExpiryDate(expiresIn)
        }

        private fun calculateExpiryDate(expiresIn: Int): LocalDateTime = LocalDateTime.now().plusSeconds(expiresIn.toLong())

        open fun isAboutToExpire(): Boolean = LocalDateTime.now().isAfter(expiryDate.minusSeconds(Authentication.BEARER_EXPIRY_DATE_MARGIN))

        companion object {
            internal val emptyBearerTokenInfo =
                object : BearerTokensInfo(BearerTokens(Constant.EMPTY_STRING, Constant.EMPTY_STRING), -1) {
                    override fun isAboutToExpire() = true
                }
        }
    }

    internal data class TokenResponse(
        val accessToken: String,
        val expiresIn: Int
    )
}
