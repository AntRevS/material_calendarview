package com.leusapp.material_calendar

import android.view.View
import com.example.calculate_pay.unit.OneDayDate

interface CalendarAdapterBase {

    fun checkExistAndSwap(v: View, day: OneDayDate)
}