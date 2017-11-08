package tw.com.flag.bloodbar;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private     long    starttime;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        取得目前時間
        starttime = System.currentTimeMillis();
//        設定要執行的方法
        handler.removeCallbacks(updateTimer);
//            設定delay時間
        handler.postDelayed(updateTimer,1000);


    }
    public int time = 0;
    public void getback(View v)
    {
        TextView new_blood = (TextView)findViewById(R.id.blood);
        EditText passWord = (EditText)findViewById(R.id.password);

        String paSsword = (String)passWord.getText().toString();
        if(paSsword.equals("志剛小雞雞"))
        {//這邊加入 補血正常訊息

            new_blood.setWidth(720);
            new_blood.setText("水啦~  陳昕水喝不夠");
            time = 3;

        }else
        {
            //這邊加入 補血失敗訊息
            new_blood.setText("沒補到血啦 白癡~~   快去問志剛");
            time = 3;
        }


    }

//    final TextView blooder = (TextView)findViewById(R.id.blood);
//    final int sub_len = blooder.getWidth() / 10;
//    寫入要執行的方法
    private Runnable updateTimer = new Runnable() {
    @Override
    public void run() {
        TextView blooder = (TextView)findViewById(R.id.blood);
        int blood_len = blooder.getWidth();
        final TextView alerT = (TextView)findViewById(R.id.alert);
        final int minblood = blooder.getWidth() / 5;
        long  spendtime = System.currentTimeMillis()-starttime;
        //目前經過地分鐘數
        long spentmin = (spendtime/1000) / 60;
        //目前經過的秒數
        long spentsec = (spendtime/1000) % 60;



        if((spendtime % 2) == 1)
        {
         int x = blooder.getWidth() - 36;
            blooder.setWidth(x);
            if(blooder.getWidth() < 30)
            {
                alerT.setText("快輸了  快喝水~~");
            }
            alerT.setText("請多喝水歐~~");

//            這邊給他顯示3次就關掉
            boolean c = false;
            if(time > 0)
            {
                time--;
                if(time == 0)
                {
                    c = true;
                }
            }
            if (c)
            {
                blooder.setText("");
            }
        }else
        {

            if(blooder.getWidth() < minblood)
            {
                alerT.setText("快輸了  快喝水~~");
            }
            alerT.setText("");
        }

        handler.postDelayed(this,1000);

    }
};

}
