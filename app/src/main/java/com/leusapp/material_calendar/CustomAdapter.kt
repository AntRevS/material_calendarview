package com.leusapp.material_calendar

import android.content.Context
import android.view.View
import com.example.calculate_pay.unit.OneDayDate

class CustomAdapter : CalendarAdapter{

    var i = 0
    var itemPressed = 0

    constructor(ctx: Context, days: Array<OneDayDate>, item: Int, itemPressed: Int) : super(ctx, days, item, itemPressed){
        this.itemPressed = itemPressed
    }

    override fun checkExistAndSwap(v: View, day: OneDayDate) {
        super.checkExistAndSwap(v, day)
        if(i % 2 == 0){
            v.setBackgroundResource(itemPressed)
        }
        i++
    }
}