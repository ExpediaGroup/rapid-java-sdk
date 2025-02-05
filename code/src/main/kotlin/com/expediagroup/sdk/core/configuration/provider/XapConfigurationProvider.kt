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
package com.expediagroup.sdk.core.configuration.provider

import com.expediagroup.sdk.core.constant.Constant

/**
 * Default configuration provider for Rapid.
 *
 * @property name The name of the provider.
 * @property endpoint The API endpoint to use for requests.
 * @property requestTimeout The API response timeout to use for requests.
 * @property connectionTimeout The connection timeout to use for requests.
 * @property socketTimeout The socket timeout to use for requests.
 */
internal object XapConfigurationProvider : ConfigurationProvider {
    override val name: String = "XAP Configuration Provider"
    override val endpoint: String = "https://apim.expedia.com"
    override val requestTimeout: Long = Constant.INFINITE_TIMEOUT
    override val connectionTimeout: Long = Constant.TEN_SECONDS_IN_MILLIS
    override val socketTimeout: Long = Constant.FIFTEEN_SECONDS_IN_MILLIS
}
