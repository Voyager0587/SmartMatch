//package com.example.smartmatch.logic.db
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//
//import androidx.room.RoomDatabase
//import com.example.smartmatch.logic.dao.MMNetDao
//import com.example.smartmatch.logic.model.Area
//import com.example.smartmatch.logic.model.MMNetResponse
//
//
///**
// * @className: MMNetDB
// * @author: Voyager
// * @description: MMNet的database
// * @date:  2023/9/25 15:45
// * @version 1.0
// **/
//
////@Database(entities = [MMNetResponse::class], version = 1)
//abstract class MMNetDB : RoomDatabase() {
//
////    abstract fun dao(): MMNetDao
////
////    companion object {
////        private const val DATABASE_NAME = "mmnet_database"
////
////        @Volatile
////        private var INSTANCE: MMNetDB? = null
////
////        fun getDatabase(context: Context): MMNetDB {
////            val tempInstance = INSTANCE
////            if (tempInstance != null) {
////                return tempInstance
////            }
////            synchronized(this) {
////                val instance = Room.databaseBuilder(
////                    context.applicationContext,
////                    MMNetDB::class.java,
////                    DATABASE_NAME
////                )
////                    .fallbackToDestructiveMigration()
////                    .build()
////                INSTANCE = instance
////                return instance
////            }
////        }
////    }
//}
