package com.example.pertemuan6

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker.OnDateChangedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan6.databinding.ActivityMainBinding
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val stringKehadiran = resources.getStringArray(R.array.kehadiran)

        with(binding){
//            Get Array
            val monthList = resources.getStringArray(R.array.month)

//            Initiate
            var selectedTime ="${timePicker.hour}:${timePicker.minute}"
            val _tempCalendar : Calendar = Calendar.getInstance()
            _tempCalendar.timeInMillis = System.currentTimeMillis()
            var selectedDate = "${_tempCalendar.get(Calendar.DAY_OF_MONTH)} ${monthList[_tempCalendar.get(Calendar.MONTH)]} ${_tempCalendar.get(Calendar.YEAR)}"


//            Kehadiran Dropdown=======================================
            val adapterKehadiran = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                stringKehadiran
            )
            kehadiranSpinner.adapter = adapterKehadiran

//            Selected Kehadiran
            kehadiranSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selectedKehadiran = stringKehadiran[position]
                        if (selectedKehadiran == "Terlambat" || selectedKehadiran == "Izin") {
                            binding.keteranganEdittext.visibility = View.VISIBLE
                        } else {
                            binding.keteranganEdittext.visibility = View.GONE
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }

            binding.submitButton.setOnClickListener {
                selectedTime = "${binding.timePicker.hour}:${binding.timePicker.minute}"
                val toastMessage = "Presensi berhasil $selectedDate jam $selectedTime"
                Toast.makeText(this@MainActivity, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}