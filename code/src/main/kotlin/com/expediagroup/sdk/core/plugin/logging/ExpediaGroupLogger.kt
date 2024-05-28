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
package com.expediagroup.sdk.core.plugin.logging

import com.expediagroup.sdk.core.client.Client
import com.expediagroup.sdk.core.constant.LogMaskingFields
import com.expediagroup.sdk.core.constant.LoggingMessage.LOGGING_PREFIX
import org.slf4j.Logger

internal class ExpediaGroupLogger(private val logger: Logger, private val client: Client? = null) : Logger by logger {
    override fun info(msg: String) = logger.info(decorate(msg))

    override fun warn(msg: String) = logger.warn(decorate(msg))

    override fun debug(msg: String) = logger.debug(decorate(msg))

    private fun decorate(msg: String): String = "$LOGGING_PREFIX ${mask(msg, getMaskedBodyFields())}"

    private fun getMaskedBodyFields(): Set<String> = client?.getLoggingMaskedFieldsProvider()?.getMaskedBodyFields() ?: LogMaskingFields.DEFAULT_MASKED_BODY_FIELDS
}
