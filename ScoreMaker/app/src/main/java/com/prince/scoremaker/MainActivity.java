package com.prince.scoremaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView score1=(TextView)findViewById(R.id.score1);
        TextView score2=(TextView)findViewById(R.id.score2);
        Button button1=(Button)findViewById(R.id.button1A);
        Button button2=(Button)findViewById(R.id.button2A);

        Button button3=(Button)findViewById(R.id.button3A);

        Button button4=(Button)findViewById(R.id.button4b);

        Button button5=(Button)findViewById(R.id.button5b);

        Button button6=(Button)findViewById(R.id.button6b);

        Button button7=(Button)findViewById(R.id.button7reset);




    }
}
