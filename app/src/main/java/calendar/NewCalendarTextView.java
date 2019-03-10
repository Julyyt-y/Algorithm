package calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class NewCalendarTextView extends android.support.v7.widget.AppCompatTextView {

    public boolean isToday = false;
    private Paint paint = new Paint();

    public NewCalendarTextView(Context context) {

        super(context);
    }

    public NewCalendarTextView(Context context,AttributeSet attrs) {
        super(context, attrs);
        initControl();
    }

    public NewCalendarTextView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl();
    }

    //当天日期用红色圆圈圈出
    private void initControl(){
        paint.setStyle(Paint.Style.STROKE); //只绘制图形轮廓（描边）
        paint.setColor(Color.parseColor("#ff0000"));
    }

    //红色圆圈标出当前日期
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if (isToday) {
            canvas.translate(getWidth() / 2, getHeight() / 2);  //移到中心点位置
            canvas.drawCircle(0, 0, getWidth() / 2, paint); //绘制半径为1/2宽的圆圈
        }
    }
}
