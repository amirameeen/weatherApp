package com.rajawali.weatherapps.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.rajawali.weatherapps.R
import com.rajawali.weatherapps.model.WeatherResponse
import kotlinx.android.synthetic.main.card_weather_data.view.*


/**
 * Created by macbookultimate on 12/27/17.
 */
class WeatherAdapter(val context: Context, val data : WeatherResponse?) : RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {
    var parentContext : Context = context

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(position : Int) {
            var datas = data?.list?.get(position)

            itemView.valueTemp.text = datas?.main?.temp.toString()
            itemView.valuePressure.text = datas?.main?.pressure.toString()
            itemView.valueHumidity.text = datas?.main?.humidity.toString()
            itemView.valueType.text = datas?.weather?.get(0)?.main.toString()
            itemView.valueDescription.text = datas?.weather?.get(0)?.description.toString()
            itemView.valueCloud.text = datas?.clouds?.all.toString()
            itemView.valueSpeed.text = datas?.wind?.speed.toString()
            itemView.valueDeg.text = datas?.wind?.deg.toString()
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        parentContext = parent.context
        return MyViewHolder(inflater.inflate(R.layout.card_weather_data, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int {
        return data?.list?.size!!
    }

}