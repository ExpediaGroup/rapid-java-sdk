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

package com.expediagroup.sdk.rapid.models

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo

/**
 *
 * @param propertyId Expedia property ID.
 * @param status Helps determine which type of property response is returned.
 * @param score A score to sort properties where the higher the value the better. It can be used to:<br> * Sort the properties on the response<br> * Sort properties across multiple responses in parallel searches for large regions<br>
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = ["status"])
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "status", visible = true)
@JsonSubTypes(
    Type(value = PropertyAvailability::class, name = "available"),
    Type(value = PropertyUnavailability::class, name = "partially_unavailable")
)
interface Property {
    // Expedia property ID.
    val propertyId: kotlin.String?

    // Helps determine which type of property response is returned.
    val status: Property.Status?

    // A score to sort properties where the higher the value the better. It can be used to:<br> * Sort the properties on the response<br> * Sort properties across multiple responses in parallel searches for large regions<br>
    val score: java.math.BigDecimal?

    /**
     * Helps determine which type of property response is returned.
     * Values: AVAILABLE,PARTIALLY_UNAVAILABLE
     */
    enum class Status(val value: kotlin.String) {
        @JsonProperty("available")
        AVAILABLE("available"),

        @JsonProperty("partially_unavailable")
        PARTIALLY_UNAVAILABLE("partially_unavailable")
    }
}
