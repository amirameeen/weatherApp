package com.rajawali.weatherapps.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.rajawali.weatherapps.R
import com.rajawali.weatherapps.adapter.WeatherAdapter
import com.rajawali.weatherapps.api.APIInterface
import com.rajawali.weatherapps.api.APIService
import com.rajawali.weatherapps.model.WeatherResponse
import com.rajawali.weatherapps.presenter.WeatherPresenter
import kotlinx.android.synthetic.main.action_bar_layout.*
import kotlinx.android.synthetic.main.activity_weather_result.*
import retrofit2.Call
import java.util.*

class WeatherResult : AppCompatActivity(), WeatherPresenter.WeatherInterface, View.OnClickListener {


    var firstName : String? = null
    var lastName : String? = null
    var zipCode : String? = null
    var adapter : WeatherAdapter? = null
    companion object {

        fun launchIntent(context: Context, firstName : String, lastName : String, zipCode : String) {
            val intent = Intent(context, WeatherResult::class.java)
            intent.putExtra("FIRST_NAME", firstName)
            intent.putExtra("LAST_NAME", lastName)
            intent.putExtra("ZIP_CODE", zipCode)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_result)
        getExtras()

        initView()
    }

    fun initView(){
        back_button.visibility = View.VISIBLE
        tv_welcome_user.text = "Current Weather"
        back_button.setOnClickListener(this)

        val c = Calendar.getInstance()
        val timeOfDay = c.get(Calendar.HOUR_OF_DAY)
        var textWelcome = ""

        if (timeOfDay >= 0 && timeOfDay < 12) {
            textWelcome = "Good Morning "
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            textWelcome = "Good Afternoon "
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            textWelcome = "Good Evening "
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            textWelcome = "Good Night "
        }

        textWelcome += firstName +" " + lastName
        tv_welcome2nd.text = textWelcome

        var presenter = WeatherPresenter(this@WeatherResult, this)
        presenter.getDataProcess(zipCode)
    }

    fun getExtras(){
        try {
            // get the Intent that started this Activity
            val `in` = intent
            // get the Bundle that stores the data of this Activity
            val dataBundle = `in`.extras
            if (dataBundle != null) {
                firstName = dataBundle.getString("FIRST_NAME")
                lastName = dataBundle.getString("LAST_NAME")
                zipCode = dataBundle.getString("ZIP_CODE")

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onLoadData() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun onLoadSuccess(data: WeatherResponse?) {
        progress_bar.visibility = View.GONE
//        Toast.makeText(this@WeatherResult, "Success", Toast.LENGTH_SHORT).show()
        initViewSuccess(data)

    }

    override fun onLoadFailure() {
        progress_bar.visibility = View.GONE
//        Toast.makeText(this@WeatherResult, "Failure", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when(v){
            back_button -> finish()
        }
    }

    fun initViewSuccess(data: WeatherResponse?){
        tv_city.text = data?.city?.name + ", " + data?.city?.country
        tv_zip_code.text = zipCode
        recycler_data.layoutManager = LinearLayoutManager(this@WeatherResult, LinearLayoutManager.VERTICAL, false)
        adapter = WeatherAdapter(this@WeatherResult, data)
        recycler_data.adapter = adapter

    }
}
