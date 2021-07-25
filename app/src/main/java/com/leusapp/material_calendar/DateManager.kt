package com.example.calculate_pay.datemanagers

import android.util.Log
import com.example.calculate_pay.unit.OneDayDate
import java.util.*

class DateManager {

    companion object{
        val LOG_TAG:String = "CalendarActivity"
    }

    private lateinit var calendar:Calendar
    private var countPrevMonthDays:Int = 0
    private lateinit var mMonthLeft:Array<OneDayDate>
    private lateinit var mOneDay: OneDayDate

    fun getCurrentMonth(): Int {
        calendar = Calendar.getInstance()
        return calendar.get(Calendar.MONTH)
    }

    fun getMonthLeft(): Array<OneDayDate>{
        return mMonthLeft
    }

    fun getCountPrevMonthDays():Int{
        return countPrevMonthDays
    }

    fun getCurrentYear(): Int{
        calendar = Calendar.getInstance()
        return calendar.get(Calendar.YEAR)
    }

    fun getCountDaysOfMonth(month:Int, year:Int): Int{
        calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun getArrayCountMonth(month:Int, year:Int): Array<OneDayDate>{
        var count = getCountDaysOfMonth(month, year)
        var result:Array<OneDayDate> = Array(count, {
            OneDayDate(
                0,
                0,
                0
            )
        })
        for(i in 0..count-1){
            mOneDay =
                OneDayDate(i + 1, month + 1, year)
            result[i] = mOneDay
        }
        return result
    }

    fun calendarWeekAdapter(dayOfWeek:Int): Int{
        var result:Int = 0
        when(dayOfWeek){
            2 ->{
                result = 0
            }
            3 ->{
                result = 1
            }
            4 ->{
                result = 2
            }
            5 ->{
                result = 3
            }
            6 ->{
                result = 4
            }
            7 ->{
                result = 5
            }
            1 ->{
                result = 6
            }
        }
        return result
    }

    fun getArrayMonthCalendar(month:Int, year:Int): Array<OneDayDate>{
        calendar = Calendar.getInstance(Locale("ru", "RU"))
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH))
        var dayOfWeekFirstDay = calendarWeekAdapter(calendar.get(Calendar.DAY_OF_WEEK))
        calendar.set(Calendar.MONTH, month - 1)
        var first = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - dayOfWeekFirstDay + 1
        var pred = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        countPrevMonthDays = pred - first + 1
        Log.d(LOG_TAG, countPrevMonthDays.toString())

        var days_left:Array<OneDayDate> = Array(countPrevMonthDays, {
            OneDayDate(
                0,
                0,
                0
            )
        })
        var days_center:Array<OneDayDate> = getArrayCountMonth(month, year)

        for(i in days_left.indices){
            if(month ==  0){
                mOneDay = OneDayDate(
                    first,
                    12,
                    year - 1
                )
            }else{
                mOneDay =
                    OneDayDate(first, month, year)
            }
            days_left[i] = mOneDay
            first++
        }
        mMonthLeft = days_left

        var result:Array<OneDayDate> = Array(days_left.size + days_center.size, {
            OneDayDate(
                0,
                0,
                0
            )
        })

        for(i in result.indices){
            when{
                i < days_left.size -> result[i] = days_left[i]
                i >= days_left.size -> result[i] = days_center[i - days_left.size]
            }
        }
        return result
    }
}