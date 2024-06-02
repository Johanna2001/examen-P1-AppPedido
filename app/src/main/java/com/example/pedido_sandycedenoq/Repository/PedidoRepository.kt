package com.example.pedido_sandy_cedeo.Repository

import androidx.lifecycle.LiveData
import com.example.pedido_sandy_cedeo.Data.Dao.PedidoDao
import com.example.pedido_sandy_cedeo.Model.Pedido

class PedidoRepository(private val pedidoDao: PedidoDao) {
    val allPedidos: LiveData<List<Pedido>> = pedidoDao.getAllPedidos()

    suspend fun insert(pedido: Pedido) {
        pedidoDao.insert(pedido)
    }
}
