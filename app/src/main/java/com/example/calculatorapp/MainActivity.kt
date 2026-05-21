package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var result: TextView
    lateinit var history: TextView

    var num1 = 0.0
    var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.textView1)
        history = findViewById(R.id.txtHistory)

        // Number Buttons

        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val btn00 = findViewById<Button>(R.id.btn00)
        val btnDot = findViewById<Button>(R.id.btnDot)

        // Operator Buttons

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSub = findViewById<Button>(R.id.btnSub)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnDiv = findViewById<Button>(R.id.btnDiv)

        // Other Buttons

        val btnEqual = findViewById<Button>(R.id.btnEqual)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnPercent = findViewById<Button>(R.id.btnPercent)

        // Number Clicks

        btn0.setOnClickListener { result.append("0") }
        btn1.setOnClickListener { result.append("1") }
        btn2.setOnClickListener { result.append("2") }
        btn3.setOnClickListener { result.append("3") }
        btn4.setOnClickListener { result.append("4") }
        btn5.setOnClickListener { result.append("5") }
        btn6.setOnClickListener { result.append("6") }
        btn7.setOnClickListener { result.append("7") }
        btn8.setOnClickListener { result.append("8") }
        btn9.setOnClickListener { result.append("9") }
        btn00.setOnClickListener { result.append("00") }

        // Dot Button

        btnDot.setOnClickListener {

            if (!result.text.contains(".")) {
                result.append(".")
            }
        }

        // Add

        btnAdd.setOnClickListener {

            if (result.text.isNotEmpty()) {

                num1 = result.text.toString().toDouble()
                operator = "+"

                history.text = result.text.toString() + " +"

                result.text = ""
            }
        }

        // Subtract

        btnSub.setOnClickListener {

            if (result.text.isNotEmpty()) {

                num1 = result.text.toString().toDouble()
                operator = "-"

                history.text = result.text.toString() + " -"

                result.text = ""
            }
        }

        // Multiply

        btnMul.setOnClickListener {

            if (result.text.isNotEmpty()) {

                num1 = result.text.toString().toDouble()
                operator = "*"

                history.text = result.text.toString() + " *"

                result.text = ""
            }
        }

        // Divide

        btnDiv.setOnClickListener {

            if (result.text.isNotEmpty()) {

                num1 = result.text.toString().toDouble()
                operator = "/"

                history.text = result.text.toString() + " /"

                result.text = ""
            }
        }

        // Equal Button

        btnEqual.setOnClickListener {

            if (result.text.isNotEmpty()) {

                val num2 = result.text.toString().toDouble()

                var ans = 0.0

                if (operator == "+") {
                    ans = num1 + num2
                }

                if (operator == "-") {
                    ans = num1 - num2
                }

                if (operator == "*") {
                    ans = num1 * num2
                }

                if (operator == "/") {

                    if (num2 != 0.0) {
                        ans = num1 / num2
                    }
                    else {
                        result.text = "Error"
                    }
                }

                result.text = ans.toString()
            }
        }

        // Clear Button

        btnClear.setOnClickListener {

            result.text = ""
            history.text = ""

            num1 = 0.0
            operator = ""
        }

        // Backspace Button

        btnBack.setOnClickListener {

            if (result.text.isNotEmpty()) {

                result.text = result.text.dropLast(1)
            }
        }

        // Percent Button

        btnPercent.setOnClickListener {

            if (result.text.isNotEmpty()) {

                val value = result.text.toString().toDouble() / 100

                result.text = value.toString()
            }
        }
    }
}