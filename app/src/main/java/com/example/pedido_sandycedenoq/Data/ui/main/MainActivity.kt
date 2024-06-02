package com.example.pedido_sandy_cedeo.ui.main

class MainActivity : AppCompatActivity() {
    private lateinit var pedidoViewModel: PedidoViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PedidoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewPedidos)
        adapter = PedidoAdapter(emptyList()) { pedido ->
            val intent = Intent(this, DetalleActivity::class.java)
            intent.putExtra("PEDIDO_ID", pedido.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        pedidoViewModel = ViewModelProvider(this).get(PedidoViewModel::class.java)
        pedidoViewModel.allPedidos.observe(this, { pedidos ->
            adapter = PedidoAdapter(pedidos) { pedido ->
                val intent = Intent(this, DetalleActivity::class.java)
                intent.putExtra("PEDIDO_ID", pedido.id)
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        })

        // Agregar algunos registros de prueba
        val testPedido = Pedido(nombre = "Pedido 1", cantidad = 2, precio = 20.0, imagenUrl = "https://via.placeholder.com/150")
        pedidoViewModel.insert(testPedido)
    }
}
