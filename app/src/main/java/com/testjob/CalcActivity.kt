package com.testjob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.math.RoundingMode

class CalcActivity : AppCompatActivity() {

    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var btnMult: Button
    private lateinit var btnDivide: Button
    private lateinit var fieldA: EditText
    private lateinit var fieldB: EditText
    private lateinit var fieldResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        btnPlus = findViewById(R.id.buttonPlus)
        btnMinus = findViewById(R.id.buttonMinus)
        btnMult = findViewById(R.id.buttonMult)
        btnDivide = findViewById(R.id.buttonDivide)
        fieldA = findViewById(R.id.editTextNumber)
        fieldB = findViewById(R.id.editTextNumber2)
        fieldResult = findViewById(R.id.resultText)

        btnPlus.setOnClickListener {
            calculate(fieldA, fieldB, 0)
        }
        btnMinus.setOnClickListener {
            calculate(fieldA, fieldB, 1)
        }
        btnMult.setOnClickListener {
            calculate(fieldA, fieldB, 2)
        }
        btnDivide.setOnClickListener {
            if(fieldB.text.toString() == "0") Toast.makeText(this, "Не делите на ноль!", Toast.LENGTH_SHORT).show()
            else calculate(fieldA, fieldB, 3)
        }
    }

    private fun calculate(a: EditText, b: EditText, type: Int) {
        var aa = a.text.toString().toBigDecimal()
        val bb = b.text.toString().toBigDecimal()


        aa = when(type) {
            0 -> aa.add(bb)
            1 -> aa.subtract(bb)
            2 -> aa.multiply(bb)
            3 -> aa.divide(bb, 8, RoundingMode.HALF_UP)
            else -> aa.add(aa)
        }

        fieldResult.text = "$aa"
    }
}