
package com.example.inventory.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.inventory.PedidosApplication
import com.example.inventory.ui.home.HomeViewModel
import com.example.inventory.ui.Pedido.ItemDetailsViewModel
import com.example.inventory.ui.Pedido.PedidoEditarViewModel
import com.example.inventory.ui.Pedido.ItemEntryViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for AlmacenEditarViewModel
        initializer {
            PedidoEditarViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemsRepository
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemsRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(inventoryApplication().container.itemsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [CalendarioApplication].
 */
fun CreationExtras.inventoryApplication(): PedidosApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as PedidosApplication)
