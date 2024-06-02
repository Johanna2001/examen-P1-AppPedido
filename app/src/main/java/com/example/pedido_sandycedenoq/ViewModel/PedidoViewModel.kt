package com.example.pedido_sandy_cedeo.ViewModel

class PedidoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PedidoRepository
    val allPedidos: LiveData<List<Pedido>>

    init {
        val pedidoDao = AppDatabase.getDatabase(application).pedidoDao()
        repository = PedidoRepository(pedidoDao)
        allPedidos = repository.allPedidos
    }

    fun insert(pedido: Pedido) = viewModelScope.launch {
        repository.insert(pedido)
    }
}
