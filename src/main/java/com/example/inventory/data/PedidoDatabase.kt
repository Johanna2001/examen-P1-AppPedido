
package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Pedido::class], version = 1, exportSchema = false)
abstract class PedidosDatabase : RoomDatabase() {

    abstract fun itemDao(): PedidoDao

    companion object {
        @Volatile
        private var Instance: PedidosDatabase? = null

        fun getDatabase(context: Context): PedidosDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PedidosDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
