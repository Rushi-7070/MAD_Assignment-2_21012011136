package com.example.mad_assignment_2_21012011136

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.mad_assignment_2_21012011136.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    var lastNumeric = false
    var stateError = false
    var lastdot=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onEqualClick(view: View) {
        onEqual()
        binding.text1.text=binding.text2.text.toString().drop(1)





    }



    fun onDigitClick(view: View) {

        if (stateError){
            binding.text1.text=(view as Button).text
            stateError=false
        }

        else{
            binding.text1.append((view as Button).text)
        }

        lastNumeric=true
        onEqual()
    }



    fun onAllclearClick(view: View) {
        binding.text1.text=""
        binding.text2.text=""
        stateError=false
        lastdot=false
        lastNumeric=false
        binding.text2.visibility=View.GONE


    }



    fun onoperatorClick(view: View) {

        if (!stateError && lastNumeric){
            binding.text1.append((view as Button).text)
            lastdot=false
            lastNumeric=false
            onEqual()
        }
    }



    fun onBackClick(view: View) {

        binding.text1.text=binding.text1.text.toString().dropLast(1)

        try {


            val lastChar = binding.text1.text.toString().last()
            if (lastChar.isDigit()) {
                onEqual()
            }
        }catch (e: Exception){
            binding.text2.text=""
            binding.text2.visibility=View.GONE
            Log.e("Last Char Error",e.toString())
        }


    }



    fun onClearClick(view: View) {
        binding.text1.text=""
        lastNumeric=false



    }

    fun onEqual(){
        if(lastNumeric && !stateError){
            val txt=binding.text1.text.toString()
            var expression = ExpressionBuilder(txt).build()

            try{
                val result=expression.evaluate()
                binding.text2.visibility=View.VISIBLE

                binding.text2.text="= "+result.toString()
            }catch (ex : ArithmeticException){
                Log.e("evaluate error",ex.toString())
                binding.text2.text="Error"
                stateError=true
                lastNumeric=false
            }
        }
    }
}