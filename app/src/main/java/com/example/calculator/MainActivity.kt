package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity()
{

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		var value = 0.0
		var text = "0"
		val equation = mutableListOf<String>()
		val operators = listOf("+", "-", "x", "/")
		var cleared = false

		fun clear()
		{
			input.text = "0"
			equation.clear()
			text = "0"
			value = 0.0
			cleared = true
		}

		fun evaluate()
		{
			var operation = "+"
			for (e in equation)
			{
				if (e !in operators)
				{
					when (operation)
					{
						"+" -> value += e.toDouble()
						"-" -> value -= e.toDouble()
						"x" -> value *= e.toDouble()
						"/" -> value /= e.toDouble()
					}
				}
				else
					operation = e
			}
		}

		fun update(entered: Char)
		{
			text = if (!cleared) text else "0"
			cleared = false
			when (entered)
			{
				in '0'..'9' ->
				{
					if (text == "0")
					{
						if (entered != '0')
							text = entered.toString()
					}
					else
						text += entered
				}
				'.'         ->
				{
					if (text.contains('.'))
					else
						text += entered
				}
				else        ->
				{
					if (text != "")
						equation.add(text.toDouble().toString())

					if (equation.isEmpty() || equation.last() in operators)
					{
						text = "Error"
						equation.clear()
						cleared = true
					}
					else if (entered == '=')
					{
						evaluate()
						text = "$value \n $equation"
						value = 0.0
						equation.clear()
						cleared = true
					}
					else
					{
						equation.add(entered.toString())
						text = ""
						println(equation)
					}

				}
			}

			input.text = text
			text = if (cleared) "" else text
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

		equals.setOnClickListener { update('=') }
	}
}
