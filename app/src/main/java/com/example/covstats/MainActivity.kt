package com.example.covstats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.covstats.viewmodels.StatsViewModel

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val viewModel: StatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getUpdatedStatsFromNetwork()

        viewModel.apiResponse.observe(this, Observer { response ->
            viewModel.clearDb()
            viewModel.updateDb(response)
        })

        val spinner = findViewById<Spinner>(R.id.spinner)
        viewModel.statistics.observe(this, Observer { statistic ->
            val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
            val sortedWith = statistic.sortedWith(compareBy { it.country })
            sortedWith.forEach {
                arrayAdapter.add(it.country)
            }
            spinner.adapter = arrayAdapter
        })
        spinner.onItemSelectedListener = this

        viewModel.statistic?.observe(this, Observer {
            findViewById<TextView>(R.id.txt_new_cases).text = it.newCases
            findViewById<TextView>(R.id.txt_active_cases).text = it.active.toString()
            findViewById<TextView>(R.id.txt_critical_cases).text = it.critical.toString()
            findViewById<TextView>(R.id.txt_recovered_cases).text = it.recovered.toString()
            findViewById<TextView>(R.id.txt_total_cases).text = it.total.toString()
        })
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        p0?.getItemAtPosition(p2)?.let { viewModel.setSelectedCountry(it) }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}