package com.example.pedido_sandy_cedeo.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movimientos")
data class Movimiento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pedidoId: Int,
    val descripcion: String,
    val fecha: Long
)
