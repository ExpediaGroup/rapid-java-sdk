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
package com.expediagroup.sdk.core.plugin.serialization

import com.expediagroup.sdk.core.client.Client
import com.expediagroup.sdk.core.plugin.Plugin
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.jackson.jackson
import java.text.SimpleDateFormat

internal object SerializationPlugin : Plugin<SerializationConfiguration> {
    override fun install(
        client: Client,
        configurations: SerializationConfiguration
    ) {
        configurations.httpClientConfiguration.install(ContentNegotiation) {
            jackson {
                enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                setDateFormat(SimpleDateFormat())
                findAndRegisterModules()
            }
        }
    }
}
