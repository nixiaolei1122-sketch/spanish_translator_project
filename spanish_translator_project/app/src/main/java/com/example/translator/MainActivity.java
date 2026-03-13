package com.example.translator;

import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView chineseText;
    TextView spanishText;
    Button speakBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chineseText = findViewById(R.id.chineseText);
        spanishText = findViewById(R.id.spanishText);
        speakBtn = findViewById(R.id.speakBtn);

        speakBtn.setOnClickListener(v -> startVoice());
    }

    private void startVoice(){

        RecognizerIntent intent =
                new RecognizerIntent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,android.content.Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==1 && resultCode==RESULT_OK){

            ArrayList<String> result =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String chinese = result.get(0);

            chineseText.setText(chinese);

            spanishText.setText("Hola (示例翻译)");
        }
    }
}