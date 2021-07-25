package com.example.calculate_pay.datemanagers

class RealDateManager {

    companion object {
        const val MONTH_INCREMENT:Int = 1
        const val MONTH_DECREMENT:Int = 0
    }

    private var mCurrentYear:Int = 0
    private var mCurrentMonth:Int = 0
    private var mDateManager: DateManager

    constructor(){
        mDateManager = DateManager()
        mCurrentMonth = mDateManager.getCurrentMonth()
        mCurrentYear = mDateManager.getCurrentYear()
    }

    fun reset(){
        mCurrentMonth = mDateManager.getCurrentMonth()
        mCurrentYear = mDateManager.getCurrentYear()
    }

    fun refreshDate(todoCode:Int){
        when(todoCode){
            0 -> mCurrentMonth--
            1 -> mCurrentMonth++
        }

        when(mCurrentMonth){
            -1 ->{
                mCurrentMonth = 11
                mCurrentYear--
            }
            12 ->{
                mCurrentMonth = 0
                mCurrentYear++
            }
        }
    }

    fun getMonth():Int{
        return mCurrentMonth
    }

    fun getYear():Int{
        return mCurrentYear
    }
}