package com.testjob.room

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    fun register(user: User) {
        userDao.register(user)
    }

    fun findUser(lgn: String, psswrd: String): User {
        return userDao.findUser(lgn, psswrd)
    }

    fun checkUser(lgn: String): User {
        return userDao.checkUser(lgn)
    }
}