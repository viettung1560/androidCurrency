package com.example.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    var curr1 = 0
    var curr2 = 7
    lateinit var txtMsg: TextView
    lateinit var editTxt1: TextInputEditText
    lateinit var editTxt2: TextInputEditText
    var state = 0

    val languages: Array<String> = arrayOf(
        "U.S. dollar\t\tUSD\t\t\$, US\$",
        "Euro\t\tEUR\t\t€",
        "Japanese yen\t\tJPY\t\t¥",
        "Sterling\t\tGBP\t\t£",
        "Renminbi\t\tCNY\t\t¥",
        "Australian dollar\t\tAUD\t\t\$, \$A",
        "Canadian dollar\t\tCAD\t\t\$, Can\$",
        "Vietnamese dong\t\tVND\t\tđ"
    )
    val lang: Array<String> = arrayOf("USD","EUR","JPY","GBP","CNY","AUD","CAD","VND")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtMsg = findViewById(R.id.textView)
        editTxt1 = findViewById((R.id.textInputEditText1))
        editTxt2 = findViewById((R.id.textInputEditText2))
        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            languages)

        spinner1.run {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    curr1 = p2
                    if (state != 0) {
                        currencyConvert()
                        setMsgText()
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        spinner2.run {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    curr2 = p2
                    if (state != 0) {
                        currencyConvert()
                        setMsgText()
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        //layoutTxt1.isClickable = true
        editTxt1.onFocusChangeListener = (View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                state = 1
                editTxt1.setText("")
                setMsgText()
            }
        })
        editTxt1.setText("0")
        editTxt1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    if (state == 1)
                        currencyConvert()
                }
                else
                    editTxt2.setText("0")
            }
        })
        editTxt2.onFocusChangeListener = (View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                state = 2
                editTxt2.setText("")
                setMsgText()
            }
        })
        editTxt2.setText("0")
        editTxt2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    if (state == 2)
                        currencyConvert()
                }
                else
                    editTxt1.setText("0")
            }
        })
        spinner1.setSelection(curr1)
        spinner2.setSelection(curr2)
    }
    fun currencyConvert(){
        if (state == 1){
            editTxt2.setText((editTxt1.text.toString().toDouble() * convert(curr1,curr2)).toString())
        }
        else if (state == 2){
            editTxt1.setText((editTxt2.text.toString().toDouble() * convert(curr2,curr1)).toString())
        }
    }
    fun setMsgText(){
        if (state == 1){
            txtMsg.text = "1 ${lang[curr1]} = ${convert(curr1,curr2)} ${lang[curr2]}"
        }
        else if (state == 2){
            txtMsg.text = "1 ${lang[curr2]} = ${convert(curr2,curr1)} ${lang[curr1]}"
        }
    }
    fun convert(a: Int,b: Int):Double{
        when (a) {
            0 -> {
                when (b){
                    0 -> return 1.0
                    1 -> return 0.9246
                    2 -> return 153.24
                    3 -> return 0.7702
                    4 -> return 7.1254
                    5 -> return 1.5286
                    6 -> return 1.3896
                    7 -> return 25345.00
                }
            }
            1 -> {
                when (b){
                    0 -> return 1.0815
                    1 -> return 1.0
                    2 -> return 165.7365
                    3 -> return 0.833
                    4 -> return 7.7065
                    5 -> return 1.6424
                    6 -> return 1.5029
                    7 -> return 27411.8538
                }
            }
            2 -> {
                when (b){
                    0 -> return 0.006526
                    1 -> return 0.006934
                    2 -> return 1.0
                    3 -> return 0.005026
                    4 -> return 0.0465
                    5 -> return 0.00991
                    6 -> return 0.009068
                    7 -> return 165.3942
                }
            }
            3 -> {
                when (b){
                    0 -> return 1.2984
                    1 -> return 1.2005
                    2 -> return 198.9613
                    3 -> return 1.0
                    4 -> return 9.2514
                    5 -> return 1.9717
                    6 -> return 1.8042
                    7 -> return 32907.0371
                }
            }
            4 -> {
                when (b){
                    0 -> return 0.1403
                    1 -> return 0.1298
                    2 -> return 21.5062
                    3 -> return 0.1081
                    4 -> return 1.0
                    5 -> return 0.2131
                    6 -> return 0.195
                    7 -> return 3556.9933
                }
            }
            5 -> {
                when (b){
                    0 -> return 0.6585
                    1 -> return 0.6089
                    2 -> return 100.9087
                    3 -> return 0.5072
                    4 -> return 4.6921
                    5 -> return 1.0
                    6 -> return 0.9151
                    7 -> return 16689.7142
                }
            }
            6 -> {
                when (b){
                    0 -> return 0.7196
                    1 -> return 0.6654
                    2 -> return 110.2763
                    3 -> return 0.5543
                    4 -> return 5.1277
                    5 -> return 1.0928
                    6 -> return 1.0
                    7 -> return 18239.0616
                }
            }
            7 -> {
                when (b){
                    0 -> return 0.00003946
                    1 -> return 0.00003648
                    2 -> return 0.006046
                    3 -> return 0.00003039
                    4 -> return 0.0002811
                    5 -> return 0.00005992
                    6 -> return 0.00005483
                    7 -> return 1.0
                }
            }
        }
        return 0.0
    }
}

