
package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

class OfflinePedidosRepository(private val itemDao: PedidoDao) : PedidosRepository {
    override fun getAllPedidosStream(): Flow<List<Pedido>> = itemDao.getAllPedidos()

    override fun getPedidoStream(id: Int): Flow<Pedido?> = itemDao.getPedido(id)

    override suspend fun insertPedido(item: Pedido) = itemDao.insert(item)

    override suspend fun deletePedido(item: Pedido) = itemDao.delete(item)

    override suspend fun updatePedido(item: Pedido) = itemDao.update(item)
}
