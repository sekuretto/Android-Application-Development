package fi.k8691.sumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberInput(view: View) {
        // view is a button (pressed one) get text and convert to Int
        val digit = (view as Button).text.toString().toInt()
        var operation: Char = ' '
        var number1: Int = 0
        var number2: Int = 0
        // append a new string to textView
        input.append(digit.toString())

        fun selectOperation(c: Char) {
            operation = c
            number1 = digit
            input.text = ""
        }

        fun calculation(operation: Char) {
            when (operation) {
                '+' -> {
                    val sum = number1 + number2
                    input.append(sum.toString())
                }
                '-' -> {
                    val ero = number1 - number2
                    input.append(ero.toString())
                }
                '*' -> {
                    val multi = number1 * number2
                    input.append(multi.toString())
                }
            }
        }

        clear.setOnClickListener {
            input.text = ""
        }

        add.setOnClickListener {
            selectOperation('+')
        }

        equals.setOnClickListener {
            number2 = digit
            calculation(operation)
        }
    }
}