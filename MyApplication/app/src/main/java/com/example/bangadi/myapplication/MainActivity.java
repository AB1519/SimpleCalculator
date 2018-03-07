package com.example.bangadi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView _screen;
    private String display="";
    private String currentOperator="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen = (TextView) findViewById(R.id.textView);
        _screen.setText(display);
    }

    private void updateScreen(){   //for rendering output screen
        _screen.setText(display);
    }

    public void onClickNumber(View v){  // to take  input from button
        Button b = (Button) v;
        display +=b.getText();
        updateScreen();
    }

    public void  onClickOperator(View v){  // to take input operator from button
        Button b =(Button) v;
        display += b.getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }

    private double operateArithmetic(String a, String b, String op){
        switch (op){
            case "+": return (Double.valueOf(a) + Double.valueOf(b));
            case "-": return (Double.valueOf(a) - Double.valueOf(b));
            case "x": return (Double.valueOf(a) * Double.valueOf(b));
            case "÷":
                try {
                return (Double.valueOf(a) / Double.valueOf(b));
            }catch (Exception e){
                Log.d("calc",e.getMessage());
            }
            default: return -1;

        }
    }

    public void onClickEqual(View v){ //After clicking "="
        String[] operation = display.split(Pattern.quote(currentOperator));
        Double result;
        result = operateArithmetic(operation[0],operation[1],currentOperator);
        _screen.setText(display+"\n" + String.valueOf(result));
    }

    private void clear(){
        display="";
        currentOperator="";
    }

    public void onClickClear(View v){
        clear();
        updateScreen();
    }
}