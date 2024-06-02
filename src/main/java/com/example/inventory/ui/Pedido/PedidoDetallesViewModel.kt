
package com.example.inventory.ui.Pedido

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.PedidosRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve, update and delete an calendario from the [PedidosRepository]'s data source.
 */
class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: PedidosRepository,
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    /**
     * Holds the almacen details ui state. The data is retrieved from [PedidosRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<ItemDetailsUiState> =
        itemsRepository.getCalendarioStream(itemId)
            .filterNotNull()
            .map {
                ItemDetailsUiState(outOfStock = it.dias <= 0, calendarioDetails = it.toItemDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    /**
     * Reduces the almacen quantity by one and update the [PedidosRepository]'s data source.
     */
    fun reduceQuantityByOne() {
        viewModelScope.launch {
            val currentItem = uiState.value.calendarioDetails.toItem()
            if (currentItem.dias > 0) {
                itemsRepository.updateCalendario(currentItem.copy(dias = currentItem.dias - 1))
            }
        }
    }

    /**
     * Deletes the almacen from the [PedidosRepository]'s data source.
     */
    suspend fun deleteItem() {
        itemsRepository.deleteCalendario(uiState.value.calendarioDetails.toItem())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for ItemDetailsScreen
 */
data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val calendarioDetails: CalendarioDetails = CalendarioDetails()
)
