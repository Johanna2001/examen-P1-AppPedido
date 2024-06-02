
package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Inventory database
 */
@Dao
interface PedidoDao {

    @Query("SELECT * from items ORDER BY mes ASC")
    fun getAllPedidos(): Flow<List<Pedido>>

    @Query("SELECT * from items WHERE id = :id")
    fun getPedido(id: Int): Flow<Pedido>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing CALENDARIO into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Pedido)

    @Update
    suspend fun update(item: Pedido)

    @Delete
    suspend fun delete(item: Pedido)
}
