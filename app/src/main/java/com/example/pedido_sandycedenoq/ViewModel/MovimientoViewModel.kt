package com.example.pedido_sandy_cedeo.ViewModel

class MovimientoViewModel(application: Application, private val pedidoId: Int) : AndroidViewModel(application) {
    private val repository: MovimientoRepository
    val movimientos: LiveData<List<Movimiento>>

    init {
        val movimientoDao = AppDatabase.getDatabase(application).movimientoDao()
        repository = MovimientoRepository(movimientoDao)
        movimientos = repository.getMovimientosByPedidoId(pedidoId)
    }

    fun insert(movimiento: Movimiento) = viewModelScope.launch {
        repository.insert(movimiento)
    }
}
