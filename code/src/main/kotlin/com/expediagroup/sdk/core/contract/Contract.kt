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
package com.expediagroup.sdk.core.contract

internal typealias Operation = (String) -> String

/**
 * A contract for a specific [operation].
 *
 * @property operation The operation to perform on a string.
 */
internal enum class Contract(val operation: Operation) {
    TRAILING_SLASH({ if (it.endsWith("/")) it else "$it/" })
}

/**
 * Adheres to the given [contract] on a [String].
 *
 * @param contract the [Contract] to adhere to.
 * @return the [String] adhering to the given [contract].
 */
internal fun String.adhereTo(contract: Contract): String = contract.operation(this)
