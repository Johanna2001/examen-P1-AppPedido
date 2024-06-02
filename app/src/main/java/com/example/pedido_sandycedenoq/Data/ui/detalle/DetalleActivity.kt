package com.example.pedido_sandy_cedeo.ui.detalle
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pedido_sandy_cedeo.R

class DetalleActivity : AppCompatActivity() {
    private lateinit var movimientoViewModel: MovimientoViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovimientoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val pedidoId = intent.getIntExtra("PEDIDO_ID", -1)
        if (pedidoId == -1) {
            finish()
            return
        }

        recyclerView = findViewById(R.id.recyclerViewMovimientos)
        adapter = MovimientoAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        movimientoViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(MovimientoViewModel::class.java, pedidoId)
        movimientoViewModel.movimientos.observe(this, { movimientos ->
            adapter = MovimientoAdapter(movimientos)
            recyclerView.adapter = adapter
        })

        // Agregar algunos movimientos de prueba
        val testMovimiento = Movimiento(pedidoId = pedidoId, descripcion = "Movimiento 1", fecha = System.currentTimeMillis())
        movimientoViewModel.insert(testMovimiento)
    }
}
