package com.leusapp.material_calendarview

import android.view.View

interface CalendarAdapterBase {

    fun checkExistAndSwap(v: View, day: OneDayDate)
}