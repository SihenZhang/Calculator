package com.sihenzhang.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.googlecode.aviator.AviatorEvaluator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonZero;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;
    private Button buttonPoint;
    private Button buttonAdd;
    private Button buttonSubtract;
    private Button buttonMultiply;
    private Button buttonDivide;
    private Button buttonPercent;
    private Button buttonEqual;
    private Button buttonClear;
    private ImageButton buttonDelete;
    private ImageButton buttonToggleAdvancedCalculator;
    private AppCompatTextView textView;

    private boolean clearFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonZero = findViewById(R.id.buttonZero);
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        buttonFive = findViewById(R.id.buttonFive);
        buttonSix = findViewById(R.id.buttonSix);
        buttonSeven = findViewById(R.id.buttonSeven);
        buttonEight = findViewById(R.id.buttonEight);
        buttonNine = findViewById(R.id.buttonNine);
        buttonPoint = findViewById(R.id.buttonPoint);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonPercent = findViewById(R.id.buttonPercent);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);
        buttonToggleAdvancedCalculator = findViewById(R.id.buttonToggleAdvancedCalculator);
        textView = findViewById(R.id.textView);

        buttonZero.setOnClickListener(this);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonToggleAdvancedCalculator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String input = textView.getText().toString();
        switch(v.getId()) {
            case R.id.buttonZero:
            case R.id.buttonOne:
            case R.id.buttonTwo:
            case R.id.buttonThree:
            case R.id.buttonFour:
            case R.id.buttonFive:
            case R.id.buttonSix:
            case R.id.buttonSeven:
            case R.id.buttonEight:
            case R.id.buttonNine:
            case R.id.buttonPoint:
                if(clearFlag) {
                    clearFlag = false;
                    input = "";
                }
                textView.setText(input + ((Button)v).getText());
                break;
            case R.id.buttonAdd:
            case R.id.buttonSubtract:
            case R.id.buttonMultiply:
            case R.id.buttonDivide:
                if(clearFlag) {
                    clearFlag = false;
                    textView.setText("");
                }
                else if(input != null && !input.equals(""))
                    textView.setText(input + ((Button)v).getText());
                break;
            case R.id.buttonClear:
                clearFlag = false;
                textView.setText("");
                break;
            case R.id.buttonDelete:
                if(clearFlag) {
                    clearFlag = false;
                    textView.setText("");
                }
                else if(input != null && !input.equals("")) {
                    textView.setText(input.substring(0, input.length() - 1));
                }
                break;
            case R.id.buttonEqual:
                getResult();
                break;
        }
    }

    private void getResult() {
        String input = textView.getText().toString();
        if(input == null || input.equals(""))
            return;
        if(input.contains("="))
            return;
        if(clearFlag) {
            clearFlag = false;
            return;
        }

        clearFlag = true;
        String expression = input.replace("×", "*").replace("÷", "/") + "M";
        try {
            textView.setText(input + "\n=" + AviatorEvaluator.execute(expression));
        }
        catch (ArithmeticException e) {
            if(e.getMessage() != null && e.getMessage().equals("Division by zero"))
                textView.setText(input + "\nCan't divide by zero");
        }
    }
}
