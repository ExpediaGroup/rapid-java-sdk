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
package com.expediagroup.sdk.core.plugin

import com.expediagroup.sdk.core.client.Client

/**
 * A helper to build a hook.
 */
internal interface HookBuilder<in C : PluginConfiguration> {
    fun build(configs: C)
}

/**
 * A hook is a post action we need to apply after creating a [Client].
 */
internal open class Hook<in C : PluginConfiguration>(
    private val configuration: C,
    private val builder: HookBuilder<C>
) {
    fun execute() = builder.build(configuration)
}

/** A singleton repository of all [Hook]s we need to apply on the [Client]. */
internal object Hooks {
    private val clientsHooks: MutableMap<Client, MutableList<Hook<*>>> = mutableMapOf()

    fun <C : PluginConfiguration> add(
        client: Client,
        hook: Hook<C>
    ) {
        clientsHooks.getOrPut(client) { mutableListOf() } += hook
    }

    fun execute(client: Client) {
        clientsHooks[client]?.forEach { it.execute() }
    }
}

/**
 * Provide an idiomatic scope to define hooks.
 */
internal fun Client.hooks(block: HookContext.() -> Unit) = block(HookContext(this))

internal class HookContext(private val client: Client) {
    /**
     * Provides an idiomatic way of defining a hook.
     */
    fun <C : PluginConfiguration> use(hookFactory: HookFactory<C>) = hookFactory

    /**
     * Provides an idiomatic way of configuring a hook.
     */
    fun <C : PluginConfiguration> HookFactory<C>.with(config: C) = Hooks.add(client, create(client, config))
}

internal interface HookFactory<C : PluginConfiguration> {
    fun create(
        client: Client,
        configuration: C
    ): Hook<C>
}
