package meitu;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.aaa.R;

//使用像素点分析来调整图像的色彩
public class PixelsEffect extends Activity {

    private ImageView imageView1, imageView2, imageView3, imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pixels_effect);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.scene);
        imageView1 = (ImageView)findViewById(R.id.image_view1);
        imageView2 = (ImageView)findViewById(R.id.image_view2);
        imageView3 = (ImageView)findViewById(R.id.image_view3);
        imageView4 = (ImageView)findViewById(R.id.image_view4);

        imageView1.setImageBitmap(bitmap);  //显示原图
        imageView2.setImageBitmap(ImageHelper.handleImageNegative(bitmap)); //底片效果
        imageView3.setImageBitmap(ImageHelper.handleImagePixelsOldPhoto(bitmap)); //怀旧效果
        imageView4.setImageBitmap(ImageHelper.handleImagePixelsRelief(bitmap)); //浮雕效果

    }
}
