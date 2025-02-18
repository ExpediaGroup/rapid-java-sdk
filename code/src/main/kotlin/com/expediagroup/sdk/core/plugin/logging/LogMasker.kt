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

import com.expediagroup.sdk.core.constant.LogMaskingRegex.REPLACEMENT_TEMPLATE
import com.expediagroup.sdk.core.constant.provider.LogMaskingRegexProvider

internal fun mask(
    message: String,
    maskedBodyFields: Set<String>
): String {
    val masks: List<Mask> =
        listOf(
            Mask { LogMaskingRegexProvider.getMaskedFieldsRegex(maskedBodyFields) },
            Mask { LogMaskingRegexProvider.getMaskedFieldsRegex("number", "\\d{12,19}") }
        )

    return masks.fold(message) { acc, mask -> mask.mask(acc) }
}

internal fun interface Mask {
    fun getRegex(): Regex

    fun mask(string: String) = string.replace(this.getRegex(), REPLACEMENT_TEMPLATE)
}
