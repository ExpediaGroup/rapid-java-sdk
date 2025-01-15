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

import com.expediagroup.sdk.core.constant.LogMaskingFields
import com.expediagroup.sdk.core.constant.LoggingMessage.LOGGING_PREFIX
import org.slf4j.Logger

internal class ExpediaGroupLogger(private val logger: Logger) : Logger by logger {
    companion object {
        @Volatile private var LOG_MASKER: LogMasker? = null

        private fun getLogMasker(): LogMasker = LOG_MASKER ?: LogMasker(getMaskedBodyFieldFilters()).also { LOG_MASKER = it }

        private fun getMaskedBodyFieldFilters(): Iterable<ExpediaGroupJsonFieldFilter> =
            listOf(
                ExpediaGroupJsonFieldFilter(LogMaskingFields.DEFAULT_MASKED_BODY_FIELDS.toTypedArray())
            )
    }

    private val mask = getLogMasker()

    override fun info(msg: String) {
        if (logger.isInfoEnabled) {
            logger.info(decorate(msg))
        }
    }

    override fun warn(msg: String) {
        if (logger.isWarnEnabled) {
            logger.warn(decorate(msg))
        }
    }

    override fun debug(msg: String) {
        if (logger.isDebugEnabled) {
            logger.debug(decorate(msg))
        }
    }

    private fun decorate(msg: String): String = "$LOGGING_PREFIX ${mask(msg)}"
}
