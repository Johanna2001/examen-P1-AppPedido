

package com.example.inventory.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val itemsRepository: PedidosRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflinePedidosRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [PedidosRepository]
     */
    override val itemsRepository: PedidosRepository by lazy {
        OfflinePedidosRepository(PedidosDatabase.getDatabase(context).itemDao())
    }
}
