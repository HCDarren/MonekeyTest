package tencent.darren.monekey.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("TAG"," -> "+getResources().getDisplayMetrics().heightPixels);
    }

    public void click(View view) {
        Log.e("TAG", "被点击了");
    }

    public void click1(View view) {
        Log.e("TAG", "我被点击了1");
    }
}
