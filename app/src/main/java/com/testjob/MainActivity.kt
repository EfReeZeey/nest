package com.testjob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.testjob.room.User
import com.testjob.room.UserViewModel
import androidx.lifecycle.Observer as Observer1

class MainActivity : AppCompatActivity() {

    private lateinit var loginfield: EditText
    private lateinit var passwordfield: EditText
    private lateinit var loginbtn: Button
    private lateinit var registerbtn: Button
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        loginfield = findViewById(R.id.editTextTextPersonName)
        passwordfield = findViewById(R.id.editTextTextPassword)
        loginbtn = findViewById(R.id.loginbtn)
        registerbtn = findViewById(R.id.registerbtn)

        registerbtn.setOnClickListener {
            insertData()
        }
        loginbtn.setOnClickListener {
            loggingin()
        }
    }

    private fun insertData() {
        val login = loginfield.text.toString()
        val password = passwordfield.text.toString()

        if(inputCheck(login, password)) {
            var checklogin: String
            val user = User(0, login, password)

            loginfield.isEnabled = false
            passwordfield.isEnabled = false
            loginbtn.isEnabled = false
            registerbtn.isEnabled = false

            userViewModel.checkUser(login)
            userViewModel.readAllData.observe(this, Observer1 {
                checklogin = userViewModel.returnedVal.value?.login.toString()

                if (!login.equals(checklogin)) {
                    userViewModel.register(user)
                    Toast.makeText(this, "Вы зарегистрированы!", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(this, "Такой пользователь уже существует!", Toast.LENGTH_SHORT).show()
        })} else {
            Toast.makeText(this, "Введите логин и пароль!", Toast.LENGTH_SHORT).show()
        }
        loginfield.isEnabled = true
        passwordfield.isEnabled = true
        loginbtn.isEnabled = true
        registerbtn.isEnabled = true
    }

    private fun loggingin() {
        val login = loginfield.text.toString()
        val password = passwordfield.text.toString()
        if(inputCheck(login, password)) {

            userViewModel.findUser(login, password)
            userViewModel.readAllData.observe(this, Observer1 {
                val checklogin = userViewModel.returnedVal.value?.login.toString()
                val checkpassword = userViewModel.returnedVal.value?.password.toString()

                if(login.equals(checklogin) && password.equals(checkpassword)) {
                    val calc = Intent(this, CalcActivity::class.java)
                    startActivity(calc)
                    finish()
                } else Toast.makeText(this, "Ошибка авторизации!", Toast.LENGTH_SHORT).show()
            })
        } else Toast.makeText(this, "Введите логин и пароль!", Toast.LENGTH_SHORT).show()
    }

    private fun inputCheck(login: String, password: String): Boolean {
        return !(TextUtils.isEmpty(login) || TextUtils.isEmpty(password))
    }
}