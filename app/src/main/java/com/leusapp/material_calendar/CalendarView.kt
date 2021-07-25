package com.leusapp.material_calendar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.calculate_pay.datemanagers.DateManager
import com.example.calculate_pay.datemanagers.RealDateManager
import com.example.calculate_pay.unit.OneDayDate
import java.lang.ClassCastException


class CalendarView : FrameLayout, View.OnClickListener {

    private lateinit var textDate: TextView
    private lateinit var btnDateLeft: Button
    private lateinit var btnDateRight: Button
    private lateinit var calendar: GridView
    private lateinit var titleBar: LinearLayout
    private lateinit var calendarLayout: LinearLayout
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var realDateManager: RealDateManager
    private lateinit var dateManager: DateManager
    private lateinit var adsLayout: LinearLayout

    private var currentItem = 0
    private var lang: String? = null
    private var titleColor: Int = 0
    private var calendBackColor: Int = 0
    private var calendarLayoutColor: Int = 0
    private var colorDateText: Int = 0
    private var colorWeekBar: Int = 0
    private var sizeDateText: Float = 0F

    private var customAdapterFlag = false

    private lateinit var weekDayList: ArrayList<TextView>

    private lateinit var navigationListener : OnNavigationButtonClick

    companion object{
        val MONTH_INCREMENT = 0
        val MONTH_DECREMENT = 1
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        var typedArray: TypedArray =
            getContext().obtainStyledAttributes(attributeSet, R.styleable.CalendarView)
        lang = typedArray.getString(R.styleable.CalendarView_lang)
        currentItem = typedArray.getResourceId(
            R.styleable.CalendarView_item_day,
            R.drawable.material_style_btn
        )
        titleColor = typedArray.getResourceId(R.styleable.CalendarView_color_title, R.color.colorAccent)
        calendBackColor = typedArray.getResourceId(R.styleable.CalendarView_calendar_backcolor, R.color.white)
        calendarLayoutColor = typedArray.getResourceId(R.styleable.CalendarView_calendar_layout_color, R.color.white)
        colorDateText = typedArray.getResourceId(R.styleable.CalendarView_color_date_text, R.color.gray)
        colorWeekBar = typedArray.getResourceId(R.styleable.CalendarView_color_week_bar, R.color.gray)
        sizeDateText = typedArray.getDimension(R.styleable.CalendarView_size_date_text, 18F)
        typedArray.recycle()

        init()
    }

    interface OnNavigationButtonClick{
        fun navigationClick()
    }

    private fun initAttrs(){
        setTitleBarColor(titleColor)
        setCalendarBackGroundColor(calendBackColor)
        setCalendarLayoutBackgroundColor(calendarLayoutColor)
        setDateTextColor(colorDateText)
        setDateTextSize(sizeDateText)
    }

    private fun init() {
        var view = LayoutInflater.from(context).inflate(R.layout.activity_calendar, this, true)
        textDate = view.findViewById(R.id.dateTime)
        btnDateLeft = view.findViewById(R.id.btnDateLeft)
        btnDateRight = view.findViewById(R.id.btnDateRight)
        calendar = view.findViewById(R.id.calendarDays)
        titleBar = view.findViewById(R.id.controlLine)
        calendarLayout = view.findViewById(R.id.calendarLayout)
        adsLayout = view.findViewById(R.id.ads_layout)

        realDateManager = RealDateManager()
        dateManager = DateManager()

        btnDateLeft.setOnClickListener(this)
        btnDateRight.setOnClickListener(this)


        weekDayList = arrayListOf(
            findViewById(R.id.textMonday),
            findViewById(R.id.textTuesday),
            findViewById(R.id.textWednesday),
            findViewById(R.id.textThursday),
            findViewById(R.id.textFriday),
            findViewById(R.id.textSaturday),
            findViewById(R.id.textSunday)
        )

        try {
            navigationListener = context as OnNavigationButtonClick
        }catch (e: ClassCastException){
            //...
        }

        initLang()
        initAttrs()
        initAdapter()
    }

    private fun initAdapter() {
        calendarAdapter = CalendarAdapter(
            context,
            getArrayCalendar(),
            currentItem, R.drawable.material_item_pressed
        )
        calendar.adapter = calendarAdapter
    }


    private fun initLang() {
        if (lang != null) {
            when (lang) {
                "ru" -> {
                    textDate.text =
                        "${resources.getString(Arrays.month[realDateManager.getMonth()])}, ${realDateManager.getYear()}"
                    for (i in weekDayList.indices) {
                        weekDayList[i].text = resources.getString(Arrays.weekDays[i])
                    }
                }
                "en" -> {
                    textDate.text =
                        "${resources.getString(Arrays.monthEng[realDateManager.getMonth()])}, ${realDateManager.getYear()}"
                    for (i in weekDayList.indices) {
                        weekDayList[i].text = resources.getString(Arrays.weekDaysEn[i])
                    }
                }
            }
        } else {
            textDate.text =
                "${resources.getString(Arrays.monthEng[realDateManager.getMonth()])}, ${realDateManager.getYear()}"
            for (i in weekDayList.indices) {
                weekDayList[i].text = resources.getString(Arrays.weekDaysEn[i])
            }
        }
    }

    fun setCustomAdapter(adapter: CalendarAdapter){
        calendarAdapter = adapter
    }

    fun notifyCustomAdapter(){
        customAdapterFlag = true
        initLang()
        calendar.adapter = calendarAdapter
    }

    fun setTitleBarColor(color: Int) {
        titleBar.setBackgroundResource(color)
    }

    fun setCalendarLayoutBackgroundColor(color: Int) {
        calendarLayout.setBackgroundResource(color)
        adsLayout.setBackgroundResource(color)
    }

    fun setCalendarBackGroundColor(color: Int) {
        calendar.setBackgroundResource(color)
    }

    fun setDateTextColor(color: Int) {
        textDate.setTextColor(color)
    }

    fun setCalendarItemLayout(item: Int) {
        currentItem = item
        initAdapter()
    }

    fun setDateTextSize(size: Float){
        textDate.textSize = size
    }

    fun getArrayCalendar(): Array<OneDayDate>{
        return dateManager.getArrayMonthCalendar(realDateManager.getMonth(), realDateManager.getYear())
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btnDateRight -> {
                    when(customAdapterFlag){
                        true -> {
                            realDateManager.refreshDate(RealDateManager.MONTH_INCREMENT)
                            navigationListener.navigationClick()
                        }
                        false -> {
                            realDateManager.refreshDate(RealDateManager.MONTH_INCREMENT)
                            initAdapter()
                            initLang()
                        }
                    }
                }
                R.id.btnDateLeft -> {
                    when(customAdapterFlag){
                        true -> {
                            realDateManager.refreshDate(RealDateManager.MONTH_DECREMENT)
                            navigationListener.navigationClick()
                        }
                        false -> {
                            realDateManager.refreshDate(RealDateManager.MONTH_DECREMENT)
                            initAdapter()
                            initLang()
                        }
                    }
                }
            }
        }
    }

}