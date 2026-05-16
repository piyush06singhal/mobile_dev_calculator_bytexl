package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var historyText: TextView

    private var currentInput = ""
    private var firstNumber = 0.0
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.textView1)
        historyText = findViewById(R.id.txtHistory)

        val numberButtons = listOf(
            R.id.btn0,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9,
            R.id.btn00,
            R.id.btnDot
        )

        for (id in numberButtons) {

            findViewById<Button>(id).setOnClickListener {

                animateButton(it)

                val value = (it as Button).text.toString()

                if (value == "." && currentInput.contains(".")) {
                    return@setOnClickListener
                }

                currentInput += value

                resultText.text = currentInput
            }
        }

        setupOperator(R.id.btnAdd, "+")
        setupOperator(R.id.btnSub, "-")
        setupOperator(R.id.btnMul, "*")
        setupOperator(R.id.btnDiv, "/")

        findViewById<Button>(R.id.btnEqual).setOnClickListener {

            animateButton(it)

            calculateResult()
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {

            animateButton(it)

            currentInput = ""
            firstNumber = 0.0
            operator = ""

            resultText.text = "0"
            historyText.text = ""
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {

            animateButton(it)

            if (currentInput.isNotEmpty()) {

                currentInput = currentInput.dropLast(1)

                if (currentInput.isEmpty()) {
                    resultText.text = "0"
                } else {
                    resultText.text = currentInput
                }
            }
        }

        findViewById<Button>(R.id.btnPercent).setOnClickListener {

            animateButton(it)

            if (currentInput.isNotEmpty()) {

                val number = currentInput.toDouble()

                val result = number / 100

                resultText.text = result.toString()

                currentInput = result.toString()
            }
        }
    }

    private fun setupOperator(buttonId: Int, op: String) {

        findViewById<Button>(buttonId).setOnClickListener {

            animateButton(it)

            if (currentInput.isNotEmpty()) {

                firstNumber = currentInput.toDouble()

                operator = op

                historyText.text = "$currentInput $operator"

                currentInput = ""
            }
        }
    }

    private fun calculateResult() {

        if (currentInput.isEmpty()) return

        val secondNumber = currentInput.toDouble()

        val result = when (operator) {

            "+" -> firstNumber + secondNumber

            "-" -> firstNumber - secondNumber

            "*" -> firstNumber * secondNumber

            "/" -> {
                if (secondNumber == 0.0) {
                    resultText.text = "Error"
                    return
                } else {
                    firstNumber / secondNumber
                }
            }

            else -> 0.0
        }

        historyText.text =
            "$firstNumber $operator $secondNumber"

        if (result % 1 == 0.0) {
            resultText.text = result.toInt().toString()
            currentInput = result.toInt().toString()
        } else {
            resultText.text = result.toString()
            currentInput = result.toString()
        }
    }

    private fun animateButton(view: View) {

        view.animate()
            .scaleX(0.90f)
            .scaleY(0.90f)
            .setDuration(70)
            .withEndAction {

                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(70)
                    .start()
            }
            .start()
    }
}