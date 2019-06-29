package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var value = 0.0
        var text = "0"

        fun update(entered : Char)
        {
            text = if (!value.isNaN()) text else "0"
            value = if (!value.isNaN()) value else 0.0

            when(entered)
            {
                in '0'..'9' ->
                {
                    if(text == "0")
                    {
                        if (entered != '0')
                            text += entered.toString()
                    }
                    else
                        text += entered
                }
                '.' ->
                {
                    if(text.contains('.'))

                    else
                        text += entered
                }
                else ->
                {
                    when(entered)
                    {
                        '+' -> value += text.toDouble()

                        '-' -> value -= text.toDouble()

                        'x' -> value *= text.toDouble()

                        '/' -> value /= text.toDouble()
                    }



                    text = when(value % 1 != 0.0)
                    {
                        true -> value.toString()
                        false -> value.toInt().toString()
                    }
                }
            }

            input.text = if (!value.isNaN()) text else "Err: Zero Divide"
        }

        fun clear()
        {
            input.text = "0"
            text = "0"
            value = 0.0
        }

        clear.setOnClickListener { clear() }

        zero.setOnClickListener { update('0') }
        one.setOnClickListener { update('1') }
        two.setOnClickListener { update('2') }
        three.setOnClickListener { update('3') }
        four.setOnClickListener { update('4') }
        five.setOnClickListener { update('5') }
        six.setOnClickListener { update('6') }
        seven.setOnClickListener { update('7') }
        eight.setOnClickListener { update('8') }
        nine.setOnClickListener { update('9') }
        decimal.setOnClickListener { update('.') }

        plus.setOnClickListener { update('+') }
        minus.setOnClickListener { update('-') }
        multiply.setOnClickListener { update('x') }
        divide.setOnClickListener { update('/') }
    }
}
