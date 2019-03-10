package calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aaa.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NewCalendar extends LinearLayout {

    private ImageView btnPrev;  //上一月按钮
    private ImageView btnNext;  //下一月按钮
    private TextView txtDate;   //年月展示
    private GridView grid;      //日历展示

    private Calendar curDate = Calendar.getInstance();  //系统日历控件
    private String displayFormat;   //自定义日期格式

    public NewCalendarListener listener;

    public NewCalendar(Context context) {
        super(context);
    }

    public NewCalendar(Context context,AttributeSet attrs) {
        super(context, attrs);
        initControl(context,attrs);
    }

    public NewCalendar(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context,attrs);
    }

    //事件绑定、渲染、其它工作
    private void initControl(Context context,AttributeSet attrs){
        bindControl(context);
        bindControlEvent();

        //读取Attribute
        TypedArray ta = getContext().obtainStyledAttributes(attrs,R.styleable.NewCalendar);
        try {
            String format = ta.getString(R.styleable.NewCalendar_dateFormat);
            displayFormat = format;
            if (displayFormat == null){
                displayFormat = "MMM yyyy";
            }
        }finally {
            ta.recycle();   //回收
        }
        renderCalendar();   //渲染
    }

    //控件、类文件中一些属性的声明
    private void bindControl(Context context){
        LayoutInflater inflater = LayoutInflater.from(context); //LayoutInflater：找res/layout/下的xml布局文件，并且实例化
        inflater.inflate(R.layout.calendar_view,this);
        btnPrev = (ImageView)findViewById(R.id.btnPrev);
        btnNext = (ImageView)findViewById(R.id.btnNext);
        txtDate = (TextView)findViewById(R.id.txtDate);
        grid = (GridView)findViewById(R.id.calendar_grid);
    }

    private void bindControlEvent(){
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //将日历向前翻一个月
                curDate.add(Calendar.MONTH,-1);
                renderCalendar();
            }
        });

        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //将日历向后翻一个月
                curDate.add(Calendar.MONTH,+1);
                renderCalendar();
            }
        });
    }

    //日历控件的绘制,渲染
    private void renderCalendar(){
        SimpleDateFormat sdf = new SimpleDateFormat(displayFormat); //格式化日期
        txtDate.setText(sdf.format(curDate.getTime())); //当前月份的文本展示

        //GridView的数据展示
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar)curDate.clone();  //将从系统获得的日期进行克隆

        //算出当月有多少天
        calendar.set(Calendar.DAY_OF_MONTH,1);  //将日期置于当月的第一天
        int prevDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;  //减一是因为初始时将日期置于当月的第一天了
        calendar.add(Calendar.DAY_OF_MONTH,-prevDays);

        int maxCellCount = 6 * 7;   //最多有6行
        //给GridView填充日期
        while(cells.size() < maxCellCount){
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }

        grid.setAdapter(new CalendarAdapter(getContext(),cells));   //传入构造函数
        //设置长按事件
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listener == null){
                    return false;
                }else {
                    listener.onItemLongPress((Date)adapterView.getItemAtPosition(i));
                    return true;
                }
            }
        });
    }

    private class CalendarAdapter extends ArrayAdapter<Date>{
        LayoutInflater inflater;
        public CalendarAdapter(Context context,ArrayList<Date> days){
            super(context,R.layout.calendar_text_day,days);
            inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            Date date = getItem(position);  //获取日期
            if (convertView == null){
                convertView = inflater.inflate(R.layout.calendar_text_day,parent,false);
            }
            int day = date.getDate();
            ((TextView)convertView).setText(String.valueOf(day));

            Date now = new Date();
            Boolean isSameMonth = false;
            if (date.getMonth() == now.getMonth()){
                isSameMonth = true;
            }
            if(isSameMonth){
                //本月的日期颜色设置成黑色
                ((TextView)convertView).setTextColor(Color.parseColor("#000000"));
            }else {
                //不是本月的日期颜色设置成灰白色
                ((TextView)convertView).setTextColor(Color.parseColor("#cfdbdc"));
            }

            //当天处于选中状态
            if (now.getDate() == date.getDate() && now.getMonth() == date.getMonth()
                    && now.getYear() == date.getYear()){
                //当天的日期颜色设置成红色
                ((TextView)convertView).setTextColor(Color.parseColor("#ff0000"));
                ((NewCalendarTextView)convertView).isToday = true;
            }
            return convertView;
        }
    }

    public interface NewCalendarListener{
        void onItemLongPress(Date day);
    }
}
