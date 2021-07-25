package com.leusapp.material_calendar

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.leusapp.material_calendarview.CalendarView

class MainActivity : FragmentActivity() {

    //lateinit var calendar: CalendarView
    val TAG = "myLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // calendar = findViewById(R.id.calendar)
       // initAdapter()
    }

    
    private fun initAdapter(){
       // calendar.setCustomAdapter(
       //     CustomAdapter(this, calendar.getArrayCalendar(), R.drawable.material_style_btn, R.drawable.material_item_pressed)
       // )
       // calendar.notifyCustomAdapter()
    }


}