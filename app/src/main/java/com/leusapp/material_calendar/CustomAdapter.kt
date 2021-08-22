package com.leusapp.material_calendar

import android.content.Context
import android.view.View
import com.leusapp.material_calendarview.CalendarAdapter
import com.leusapp.material_calendarview.OneDayDate

class CustomAdapter(ctx: Context, days: Array<OneDayDate>, item: Int, itemPressed: Int) :
    CalendarAdapter(ctx, days, item, itemPressed) {

    var i = 0

    override fun checkExistAndSwap(v: View, day: OneDayDate) {
        super.checkExistAndSwap(v, day)
        if(i % 2 == 0){
            v.setBackgroundResource(itemPressed)
        }
        i++
    }
}