package meitu;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.aaa.R;

//色光三原色的基本属性，演示调整这些属性
public class PrimaryColor extends Activity implements SeekBar.OnSeekBarChangeListener{

    private ImageView mImageView;
    private SeekBar mSeekbarHue, mSeekbarSaturation, mSeekbarLum;
    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;

    private float mHue,mSaturation,mLum;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primary_color);

        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dusk);
        mImageView = findViewById(R.id.image_view);
        mSeekbarHue = findViewById(R.id.seekbarHue);
        mSeekbarSaturation = findViewById(R.id.seekbarSaturation);
        mSeekbarLum = findViewById(R.id.seekbarLum);
        mSeekbarHue.setOnSeekBarChangeListener(this);
        mSeekbarSaturation.setOnSeekBarChangeListener(this);
        mSeekbarLum.setOnSeekBarChangeListener(this);
        mSeekbarHue.setMax(MAX_VALUE);
        mSeekbarSaturation.setMax(MAX_VALUE);
        mSeekbarLum.setMax(MAX_VALUE);
        //初始化的时候刻度停留在中间位置
        mSeekbarHue.setProgress(MID_VALUE);
        mSeekbarSaturation.setProgress(MID_VALUE);
        mSeekbarLum.setProgress(MID_VALUE);
        mImageView.setImageBitmap(bitmap);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()){
            case R.id.seekbarHue:
                mHue = (i - MID_VALUE) * 1.0F / MID_VALUE * 180;    //色调
                break;
            case R.id.seekbarSaturation:
                mSaturation = i * 1.0F / MID_VALUE;     //饱和度（0-2）
                break;
            case R.id.seekbarLum:
                mLum = i * 1.0F / MID_VALUE;        //亮度（0-2）
                break;
        }
        mImageView.setImageBitmap(ImageHelper.handleImageEffect(bitmap,mHue,mSaturation,mLum)); //处理图片
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
