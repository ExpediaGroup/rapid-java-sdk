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
import com.expediagroup.sdk.core.plugin.authentication.AuthenticationConfiguration
import com.expediagroup.sdk.core.plugin.authentication.strategy.AuthenticationStrategy.AuthenticationType.BASIC
import com.expediagroup.sdk.core.plugin.authentication.strategy.AuthenticationStrategy.AuthenticationType.BEARER
import com.expediagroup.sdk.core.plugin.authentication.strategy.AuthenticationStrategy.AuthenticationType.SIGNATURE
import io.ktor.client.plugins.auth.AuthConfig
import io.ktor.client.request.HttpRequestBuilder

internal interface AuthenticationStrategy {
    fun loadAuth(auth: AuthConfig) {}

    fun isTokenAboutToExpire(): Boolean

    fun renewToken()

    fun isIdentityRequest(request: HttpRequestBuilder): Boolean

    fun getAuthorizationHeader(): String

    companion object {
        fun from(
            configs: AuthenticationConfiguration,
            client: Client
        ): AuthenticationStrategy =
            when (configs.authType) {
                BASIC -> BasicAuthenticationStrategy(configs)
                BEARER -> ExpediaGroupAuthenticationStrategy(client, configs)
                SIGNATURE -> RapidAuthenticationStrategy(configs)
            }
    }

    enum class AuthenticationType {
        BASIC,
        BEARER,
        SIGNATURE
    }
}
