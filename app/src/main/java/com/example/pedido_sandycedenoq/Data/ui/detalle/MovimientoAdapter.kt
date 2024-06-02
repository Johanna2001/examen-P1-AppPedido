package com.example.pedido_sandy_cedeo.ui.detalle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pedido_sandy_cedeo.R
import com.example.pedido_sandy_cedeo.Model.Movimiento
import java.text.SimpleDateFormat

class MovimientoAdapter(private val movimientos: List<Movimiento>) :
    RecyclerView.Adapter<MovimientoAdapter.MovimientoViewHolder>() {

    class MovimientoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descripcion: TextView = itemView.findViewById(R.id.descripcion)
        val fecha: TextView = itemView.findViewById(R.id.fecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimientoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movimiento, parent, false)
        return MovimientoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovimientoViewHolder, position: Int) {
        val movimiento = movimientos[position]
        holder.descripcion.text = movimiento.descripcion
        holder.fecha.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(movimiento.fecha))
    }

    override fun getItemCount() = movimientos.size
}
