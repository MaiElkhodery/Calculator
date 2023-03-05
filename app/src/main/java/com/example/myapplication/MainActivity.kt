package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var addOperation = false
    var enableDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        deleteButton.setOnClickListener{
            val length = operationsTextView.text.length
            if(length > 0){
                operationsTextView.text = operationsTextView.text.subSequence(0,length-1)
            }
        }
        deleteAllButton.setOnClickListener {
            operationsTextView.text = ""
            resultTextView.text =""
        }


        zeroButton.setOnClickListener{
            onClickNumber(zeroButton)
        }
        oneButton.setOnClickListener{
            onClickNumber(oneButton)
        }
        twoButton.setOnClickListener{
            onClickNumber(twoButton)
        }
        threeButton.setOnClickListener{
            onClickNumber(threeButton)
        }
        fourButton.setOnClickListener{
            onClickNumber(fourButton)
        }
        fiveButton.setOnClickListener{
            onClickNumber(fiveButton)
        }
        sixButton.setOnClickListener{
            onClickNumber(sixButton)
        }
        sevenButton.setOnClickListener{
            onClickNumber(sevenButton)
        }
        eightButton.setOnClickListener{
            onClickNumber(eightButton)
        }
        nineButton.setOnClickListener{
            onClickNumber(nineButton)
        }
        dotButton.setOnClickListener{
            onClickNumber(dotButton)
        }

        divButton.setOnClickListener{
            onClickOperation(divButton)
        }
        mulButton.setOnClickListener{
            onClickOperation(mulButton)
        }
        addButton.setOnClickListener{
            onClickOperation(addButton)
        }
        subButton.setOnClickListener{
            onClickOperation(subButton)
        }
        modButton.setOnClickListener{
            onClickOperation(modButton)
        }

        equalButton.setOnClickListener{
            calc()
        }


    }
    fun onClickNumber(view : AppCompatButton){
        Log.d("Click Number","done")
            if(view.text == "."){
                if(enableDecimal){
                    operationsTextView.append(view.text)
                    enableDecimal=false
                }
            }
            else
                operationsTextView.append(view.text)
            addOperation = true
    }
    fun onClickOperation(view : AppCompatButton){
        Log.d("Click Operation","done")
        if( addOperation){
            operationsTextView.append(view.text)
            addOperation = false
        }
    }
    fun calc(){
        val digitList = digitsOperators()
        if(digitList.isEmpty()){
            resultTextView.text=""
        }
        var index =0
        var number = 0.0f
        var result = 0.0f
        while(index < digitList.size){
            Log.d("loop on list ",digitList[index].toString())
            if(digitList[index] == '+' || digitList[index] == '-'|| digitList[index] == '*' || digitList[index] == '/' || digitList[index] == '%'){
                Log.d("operation",digitList[index].toString())
                when(digitList[index]){
                    '+' -> result = digitList[index+1].toString().toFloat() + number
                    '-' -> result = number - digitList[index+1].toString().toFloat()
                    '*' -> result = digitList[index+1].toString().toFloat() * number
                    '/' -> result = number / digitList[index+1].toString().toFloat()
                    '%' -> result = number % digitList[index+1].toString().toFloat()
                }
                digitList[index+1] = result.toString()
                resultTextView.text= result.toString()
                Log.d("result",result.toString())
            }else{
                Log.d("number",digitList[index].toString())
                number = digitList[index].toString().toFloat()
            }
            index++
        }
        resultTextView.text= result.toString()
    }
    fun digitsOperators():MutableList<Any>{
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for (char in operationsTextView.text){
            if(char == '+'|| char == '-' || char == '*' || char == '/' || char == '%'){
                Log.d("add num to list",currentDigit)
                Log.d("add char to list",char+"")
                list.add(currentDigit.toFloat())
                currentDigit =""
                list.add(char)
            }
            else{
                currentDigit += char
            }
        }
        if(currentDigit != ""){
            list.add(currentDigit.toFloat())
        }
        return list
    }
}
