package com.example.pedido_sandy_cedeo.ui.main

class PedidoAdapter(private val pedidos: List<Pedido>, private val onItemClick: (Pedido) -> Unit) :
    RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.nombre)
        val cantidad: TextView = itemView.findViewById(R.id.cantidad)
        val precio: TextView = itemView.findViewById(R.id.precio)
        val imagen: ImageView = itemView.findViewById(R.id.imagen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pedido, parent, false)
        return PedidoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.nombre.text = pedido.nombre
        holder.cantidad.text = pedido.cantidad.toString()
        holder.precio.text = pedido.precio.toString()
        Picasso.get().load(pedido.imagenUrl).into(holder.imagen)

        holder.itemView.setOnClickListener {
            onItemClick(pedido)
        }
    }

    override fun getItemCount() = pedidos.size
}
