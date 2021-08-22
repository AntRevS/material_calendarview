package com.leusapp.material_calendarview

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

open class CalendarAdapter(
    val ctx: Context,
    val days: Array<OneDayDate>,
    val item:Int,
    val itemPressed:Int
) : CalendarAdapterBase, View.OnTouchListener, RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    var calendarItemListener: OnCalendarItemListener? = null
    var inflater: LayoutInflater

    interface OnCalendarItemListener{
        fun onCalendarItemClick(day: OneDayDate)
    }

    init {
        inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        try{
            calendarItemListener = ctx as OnCalendarItemListener
        }catch (e: ClassCastException){
            throw ClassCastException(CalendarAdapter::class.java.simpleName + " must implement OnCalendarItemListener")
        }
    }

    inner class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var day: Button

        init {
            this.day = itemView as Button
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        var itemView = inflater.inflate(R.layout.material_day, parent, false)
        return CalendarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.day.text = getItem(position).day.toString()
        checkExistAndSwap(holder.day, getItem(position))
    }

    override fun getItemCount() = days.size

    override fun checkExistAndSwap(v: View, day: OneDayDate) {

    }

    private fun getItem(position: Int): OneDayDate{
        return days[position]
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if(v != null && event != null){
            when(event.action){
                MotionEvent.ACTION_UP -> {
                    if(calendarItemListener != null) {
                        var temp: OneDayDate = getItem(v.getTag() as Int)
                        calendarItemListener!!.onCalendarItemClick(temp)
                    }
                }
            }
        }
        return false
    }


}