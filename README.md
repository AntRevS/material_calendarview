## Calendar View  
Android material calendar view  
  
![hippo](https://github.com/reviakin-package/material_calendarview/blob/main/app/src/main/res/raw/calendar_work.gif)
  
## Usage
```xml
    <com.leusapp.material_calendarview.CalendarView
        android:id="@+id/calendar"
        android:layout_height="match_parent"
        android:layout_width="match_parent"
        app:lang="en">
    </com.leusapp.material_calendarview.CalendarView>
```
  
Other attributes  
- app:lang - chage date language(en, ru)  
- app:item_day - drawable for day item  
- app:color_title - color for title bar  
- app:calendar_backcolor - color for calendar background  
- app:calendar_layout_color - color for calendar layout  
- app:color_date_text - color for text date  
- app:size_date_text - size for text(sp)  
- app:color_week_bar - color for week bar  
  
## Custom adapter  
  
Example custom adapter for swap item type  
  
```kotlin
class CustomAdapter : CalendarAdapter {

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
```  
Activity  
  
```kotlin
  class MainActivity : FragmentActivity(), CalendarAdapter.OnCalendarItemListener, CalendarView.OnNavigationButtonClick {

    lateinit var calendar: CalendarView
    val TAG = "myLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendar = findViewById(R.id.calendar)
        initAdapter()
    }

    
    private fun initAdapter(){
        calendar.setCustomAdapter(
            CustomAdapter(this, calendar.getArrayCalendar(), R.drawable.material_style_btn, R.drawable.material_item_pressed)
        )
        calendar.notifyCustomAdapter()
    }

    override fun onItemClick(day: OneDayDate) {
        Log.d(TAG, "${day.day} ${day.month} ${day.year}")
    }

    override fun navigationClick() {
        initAdapter()
    }
}
```
  
## Integration
  
The lib is available in maven jitpack  
```gradle
    allprojects {
      repositories {
          maven { url 'https://jitpack.io' }
      }
    }
    dependencies {
        implementation 'com.github.reviakin-package:material_calendarview:1.3.4'
    }
```  
  
## License  
```license
    MIT License

Copyright (c) 2021 reviakin-package

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```


