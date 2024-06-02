package com.example.pedido_sandy_cedeo.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class Pedido(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val cantidad: Int,
    val precio: Double,
    val imagenUrl: String
)
