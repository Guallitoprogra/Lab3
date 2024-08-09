package com.example.lab3;

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var input: String = ""
    private var operator: String = ""
    private var value1: Double? = null
    private var value2: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tv_result)

        // Numeric Buttons
        val buttons = listOf<Button>(
            findViewById(R.id.btn_0), findViewById(R.id.btn_1), findViewById(R.id.btn_2),
            findViewById(R.id.btn_3), findViewById(R.id.btn_4), findViewById(R.id.btn_5),
            findViewById(R.id.btn_6), findViewById(R.id.btn_7), findViewById(R.id.btn_8),
            findViewById(R.id.btn_9), findViewById(R.id.btn_decimal)
        )

        for (button in buttons) {
            button.setOnClickListener {
                input += (it as Button).text
                tvResult.text = input
            }
        }

        // Operator Buttons
        findViewById<Button>(R.id.btn_plus).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btn_minus).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btn_multiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btn_divide).setOnClickListener { setOperator("/") }

        // Clear Button
        findViewById<Button>(R.id.btn_clear).setOnClickListener {
            clear()
        }

        // Equals Button
        findViewById<Button>(R.id.btn_equals).setOnClickListener {
            if (value1 != null && operator.isNotEmpty()) {
                value2 = input.toDoubleOrNull()
                if (value2 != null) {
                    val result = calculate()
                    tvResult.text = result.toString()
                    input = result.toString()
                    operator = ""
                    value1 = null
                    value2 = null
                }
            }
        }
    }

    private fun setOperator(op: String) {
        if (input.isNotEmpty()) {
            value1 = input.toDoubleOrNull()
            if (value1 != null) {
                operator = op
                input = ""
            }
        }
    }

    private fun calculate(): Double {
        return when (operator) {
            "+" -> value1!! + value2!!
            "-" -> value1!! - value2!!
            "*" -> value1!! * value2!!
            "/" -> value1!! / value2!!
            else -> 0.0
        }
    }

    private fun clear() {
        input = ""
        operator = ""
        value1 = null
        value2 = null
        tvResult.text = "0"
    }
}
