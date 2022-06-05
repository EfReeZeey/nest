package com.testjob.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM registry WHERE login LIKE :lgn AND password LIKE :psswrd LIMIT 1")
    fun findUser(lgn: String, psswrd: String): User

    @Query("SELECT * FROM registry WHERE login LIKE :lgn LIMIT 1")
    fun checkUser(lgn: String): User

    @Insert
    fun register(user: User)
    //fun insert(vararg users: User)

    @Query("SELECT * FROM registry")
    fun readAllData(): LiveData<List<User>>
}