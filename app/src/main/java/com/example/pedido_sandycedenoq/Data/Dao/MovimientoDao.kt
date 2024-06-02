package com.example.pedido_sandy_cedeo.Data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pedido_sandy_cedeo.Data.Entities.Movimiento

@Dao
interface MovimientoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movimiento: Movimiento)

    @Query("SELECT * FROM movimientos WHERE pedidoId = :pedidoId")
    fun getMovimientosByPedidoId(pedidoId: Int): LiveData<List<Movimiento>>
}
