package com.zihaochen.kyle.mortgagecalculator;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    InputFragment firstFragment;
    OutputFragment secondFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.mainLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });
        firstFragment = new InputFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.firstLayout,firstFragment,firstFragment.getTag())
                .commit();
        secondFragment = new OutputFragment();
        //android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.secondLayout,secondFragment,secondFragment.getTag())
                .commit();

    }
    public Button getCalculate(){
        return findViewById(R.id.bCalculate);
    }
    public Button getReset(){
        return findViewById(R.id.bReset);
    }
    public void sendData(Double HomeValue,Double DownPayment, Double InterestRate, Double PropertyTaxRate, Integer Terms){
        secondFragment.calculate(HomeValue,DownPayment,InterestRate,PropertyTaxRate,Terms);
    }
    public void sendReset(){
        secondFragment.reset();
    }
}
