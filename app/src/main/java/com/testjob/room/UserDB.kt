package com.testjob.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun getDB(context: Context): UserDB {
            return Room.databaseBuilder(
                context.applicationContext,
                UserDB::class.java, "registry"
            ).build()
        }
    }
}