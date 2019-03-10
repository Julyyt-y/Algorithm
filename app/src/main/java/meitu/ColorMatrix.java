package meitu;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.aaa.R;

//Android系统提供的颜色矩阵来调整图像的色彩
public class ColorMatrix extends Activity {

    private ImageView mImageView;
    private GridLayout mGroup;
    private Bitmap bitmap;

    private int mEtWidth, mEtHeight;
    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrix = new float[20];   //矩阵

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_matrix);

        //从资源文件中得到原图
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dusk);

        mImageView = (ImageView)findViewById(R.id.image_view);
        mGroup = (GridLayout)findViewById(R.id.group);

        mImageView.setImageBitmap(bitmap);

        //动态绘制20个EditText
        mGroup.post(new Runnable() {
            @Override
            public void run() {
                //绘制后执行
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 4;
                addEts();
                initMatrix();   //给20个EditText赋值
            }
        });
    }

    //获取矩阵中每个元素的值
    private void getMatrix(){
        for (int i = 0; i < 20; i++){
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }

    private void setImageMatrix(){
        //通过原图创建一个新的Bitmap
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        android.graphics.ColorMatrix colorMatrix = new android.graphics.ColorMatrix();
        colorMatrix.set(mColorMatrix);  //将颜色矩阵以一维数组的形式传递给colorMatrix

        //在canvas画布上用paint画笔，以颜色矩阵colorMatrix的形式，将bitmap重新绘制，从而创建一个新的图像bmp
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        mImageView.setImageBitmap(bmp); //将创建的图像设置给mImageView
    }

    public void btnChange(View view){
        getMatrix();    //获取到当前整个EditText的值
        setImageMatrix();   //将这些赋给mColorMatrix颜色矩阵
    }

    //重置颜色矩阵
    public void btnReset(View view){
        initMatrix();
        getMatrix();
        setImageMatrix();
    }

    //20个EditText动态地添加到GridLayout中
    private void addEts(){
        for(int i = 0; i < 20; i ++){
            EditText editText = new EditText(ColorMatrix.this);
            mEts[i] = editText;
            mGroup.addView(editText,mEtWidth,mEtHeight);
        }
    }

    //给色彩矩阵的元素设置值
    private void initMatrix(){
        for (int i = 0; i < 20; i++){
            if (i % 6 == 0){
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }
}
