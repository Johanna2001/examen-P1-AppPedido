package com.example.pedido_sandy_cedeo.Repository
import androidx.lifecycle.LiveData
import com.example.pedido_sandy_cedeo.Data.Dao.MovimientoDao
import com.example.pedido_sandy_cedeo.Model.Movimiento

class MovimientoRepository(private val movimientoDao: MovimientoDao) {
    fun getMovimientosByPedidoId(pedidoId: Int): LiveData<List<Movimiento>> {
        return movimientoDao.getMovimientosByPedidoId(pedidoId)
    }

    suspend fun insert(movimiento: Movimiento) {
        movimientoDao.insert(movimiento)
    }
}
