package com.rajawali.weatherapps.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rajawali.weatherapps.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submit_weather.setOnClickListener(this)
    }

    fun ValidationData(){
        val valueFirstName = et_first_name.text.toString()
        val valueLastName = et_lat_name.text.toString()
        val valueZipCode = et_zip_code.text.toString()

        var focusView : View? = null
        et_first_name.error = null
        et_lat_name.error = null
        et_zip_code.error = null

        var cancel = false


        if(valueZipCode.equals("") || valueZipCode == null){
            cancel = true
            et_zip_code.error = application.getString(R.string.input_error_universal)
            focusView = et_zip_code
        }

        if(valueLastName.equals("") || valueLastName == null){
            cancel = true
            et_lat_name.error = application.getString(R.string.input_error_universal)
            focusView = et_lat_name
        }

        if(valueFirstName.equals("") || valueFirstName == null){
            cancel = true
            et_first_name.error = application.getString(R.string.input_error_universal)
            focusView = et_first_name
        }

        if(cancel){
            focusView?.requestFocus()
        }else{
            WeatherResult.launchIntent(this, valueFirstName, valueLastName, valueZipCode)
        }

    }

    override fun onClick(v: View?) {
        when(v){
            submit_weather ->{
//                WeatherResult.launchIntent(this)
                ValidationData()
            }
        }
    }

}
