
package com.example.inventory.ui.Pedido

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.PedidosRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class PedidoEditarViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: PedidosRepository
) : ViewModel() {


    var itemUiState by mutableStateOf(ItemUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            itemUiState = itemsRepository.getCalendarioStream(itemId)
                .filterNotNull()
                .first()
                .toItemUiState(true)
        }
    }


    suspend fun updateItem() {
        if (validateInput(itemUiState.calendarioDetails)) {
            itemsRepository.updateCalendario(itemUiState.calendarioDetails.toItem())
        }
    }


    fun updateUiState(calendarioDetails: CalendarioDetails) {
        itemUiState =
            ItemUiState(calendarioDetails = calendarioDetails, isEntryValid = validateInput(calendarioDetails))
    }

    private fun validateInput(uiState: CalendarioDetails = itemUiState.calendarioDetails): Boolean {
        return with(uiState) {
            mes.isNotBlank() && dias.isNotBlank() && semanas.isNotBlank() && festividad.isNotBlank()
        }
    }
}


