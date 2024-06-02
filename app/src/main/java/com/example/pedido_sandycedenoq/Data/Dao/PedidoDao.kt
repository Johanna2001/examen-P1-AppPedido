package com.example.pedido_sandy_cedeo.Data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pedido_sandy_cedeo.Data.Entities.Pedido
@Dao
interface PedidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pedido: Pedido)

    @Query("SELECT * FROM pedidos")
    fun getAllPedidos(): LiveData<List<Pedido>>
}
