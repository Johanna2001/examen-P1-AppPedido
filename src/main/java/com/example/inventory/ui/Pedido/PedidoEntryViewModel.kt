
package com.example.inventory.ui.Pedido

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Pedido
import com.example.inventory.data.PedidosRepository

/**
 * ViewModel to validate and insert items in the Room database.
 */
class ItemEntryViewModel
    (private val itemsRepository: PedidosRepository) :
    ViewModel() {

    /**
     * Holds current calendario ui state
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(calendarioDetails: CalendarioDetails) {
        itemUiState =
            ItemUiState(calendarioDetails = calendarioDetails, isEntryValid = validateInput(calendarioDetails))
    }

    /**
     * Inserts an [calendario] in the Room database
     */
    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertCalendario(itemUiState.calendarioDetails.toItem())
        }
    }

    private fun validateInput(uiState: CalendarioDetails = itemUiState.calendarioDetails): Boolean {
        return with(uiState) {
            mes.isNotBlank() && dias.isNotBlank() && semanas.isNotBlank() && festividad.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Pedido.
 */
data class ItemUiState(
    val calendarioDetails: CalendarioDetails = CalendarioDetails(),
    val isEntryValid: Boolean = false
)

data class CalendarioDetails(
    val id: Int = 0,
    val mes: String = "",
    val dias: String = "",
    val semanas: String = "",
    val festividad: String = "",
)

/**
 * Extension function to convert [ItemUiState] to [Pedido]. If the value of [CalendarioDetails.semanas] is
 * not a valid [int], then the address will be set to 0.0. Similarly if the value of
 * [ItemUiState] is not a valid [Int], then the quantity will be set to 0
 */
fun CalendarioDetails.toItem(): Pedido = Pedido(
    id = id,
    mes = mes,
    dias = dias.toIntOrNull() ?: 0,
    semanas = semanas.toIntOrNull() ?: 0,
    festividad = festividad
)




/**
 * Extension function to convert [Pedido] to [ItemUiState]
 */
fun Pedido.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    calendarioDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Pedido] to [CalendarioDetails]
 */
fun Pedido.toItemDetails(): CalendarioDetails = CalendarioDetails(
    id = id,
    mes = mes,
    dias = dias.toString(),
    semanas = semanas.toString(),
    festividad = festividad
)
