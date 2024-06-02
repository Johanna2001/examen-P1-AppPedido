
package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Pedido] from a given data source.
 */
interface PedidosRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllPedidoStream(): Flow<List<Pedido>>

    /**
     * Retrieve an pedido from the given data source that matches with the [id].
     */
    fun getPedidoStream(id: Int): Flow<Pedido?>

    /**
     * Insert calendario in the data source
     */
    suspend fun insertPedido(item: Pedido)

    /**
     * Delete pedido from the data source
     */
    suspend fun deletePedido(item: Pedido)

    /**
     * Update pedido in the data source
     */
    suspend fun updatePedido(item: Pedido)
}
