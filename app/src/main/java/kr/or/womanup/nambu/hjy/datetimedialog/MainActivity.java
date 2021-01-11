package kr.or.womanup.nambu.hjy.datetimedialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button btnDatePicker, btnTimePicker;
    static String tag = "DateTime";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btnDatePicker = findViewById(R.id.button);
        btnTimePicker = findViewById(R.id.button2);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(); //싱글톤 객체
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

//                String dayofweek={"","일요일",..."토요일"}
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); //1~7 일요일~토요일
                
                
                DPDListener listener = new DPDListener();
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        listener, year, month, day);

                dialog.show();

            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(); //싱글톤 객체
                int hour = calendar.get(Calendar.HOUR);
                int min = calendar.get(Calendar.MINUTE);
                TPDListener listener = new TPDListener();
                TimePickerDialog dialog = new TimePickerDialog(MainActivity.this,
                        listener,hour,min,false);
                dialog.show();
            }
        });

    }
    class TPDListener implements TimePickerDialog.OnTimeSetListener{

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            textView.setText(hourOfDay +":"+minute);
        }
    }


    //데이터가 셋 될 때(선택될 때) 작동
    class DPDListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Log.d(tag,"DatePicker OnDateSet");
            Log.d(tag,year+"-"+month+"-"+day);
            textView.setText(year+"-"+(month+1)+"-"+day);
        }
    }
    
}