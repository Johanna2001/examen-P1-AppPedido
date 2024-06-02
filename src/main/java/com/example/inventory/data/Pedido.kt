
package com.example.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "items")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mes: String,
    val nombre_Producto: String,
    val Stock: Int,
    val detalles: String
)
