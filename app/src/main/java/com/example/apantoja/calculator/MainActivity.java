package com.example.apantoja.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apantoja.calculator.R;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView screen;
    String display = "";
    String operator = "";
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView) findViewById(R.id.textView);
        screen.setText(display);
    }

    public void onClickNumber(View view) {
        if(result != ""){
            clear();
            refreshScreen();
        }
        Button btn = (Button) view;
        display += btn.getText();
        refreshScreen();
    }

    public void onClickOperator(View view) {
        if(display == "") return;

        Button btn = (Button) view;

        if(result != ""){
            String _display = result;
            clear();
            display = _display;
        }

        if(operator != ""){
            Log.d("CalcX", ""+display.charAt(display.length()-1));

            if(isOperator(display.charAt(display.length()-1))){
                display = display.replace(display.charAt(display.length()-1), btn.getText().charAt(0));
                refreshScreen();
                return;
            }

            else{
                getResult();
                display = result;
                result = "";
            }
            operator = btn.getText().toString();
        }
        display += btn.getText();
        operator = btn.getText().toString();
        refreshScreen();
    }

    public void onClickClear(View v){
        clear();
        refreshScreen();
    }

    public void onClickEqual(View v){
        if(display == "") return;
        if(!getResult()) return;
        screen.setText(display + "\n" + String.valueOf(result));
    }

    public void refreshScreen(){
        screen.setText(display);
    }

    private void clear(){
        //  display = String.valueOf(display.lastIndexOf()-1);
        display = "";
        operator = "";
        result = "";
    }

    private double operate(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "x": return Double.valueOf(a) * Double.valueOf(b);
            case "÷": try{
                return Double.valueOf(a) / Double.valueOf(b);
            }catch (Exception e){
                Log.d("Calc", e.getMessage());
            }
            default: return -1;
        }
    }

    private boolean getResult(){
        if(operator == "") return false;
        String[] operation = display.split(Pattern.quote(operator));
        if(operation.length < 2) return false;
        result = String.valueOf(operate(operation[0], operation[1], operator));
        return true;
    }

    private boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case 'x':
            case '÷':return true;
            default: return false;
        }
    }
}

