package com.appfibonacci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;

import com.appfibonacci.R;

public class MainActivity extends AppCompatActivity {

    private long fibMinus1 = 0;
    private long fibMinus2 = 1;
    private long currentFib = 0;
    private TextView showCount;
    private long n = 0;
    private long limit = 0;

    private EditText mLimitInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCount = (TextView) findViewById(R.id.show_count);
        mLimitInput = (EditText) findViewById(R.id.limit_input);
        updateFibonacciDisplay();
    }

    public void countUp(View view) {
        if (mLimitInput.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter the limit first", Toast.LENGTH_SHORT).show();
            return;
        }

        limit = Long.parseLong(mLimitInput.getText().toString());

        if (currentFib >= limit) {
            Toast.makeText(this, "Fibonacci limit reached", Toast.LENGTH_SHORT).show();
            return;
        }

        long newFib = fibMinus1 + fibMinus2;
        fibMinus2 = fibMinus1;
        fibMinus1 = newFib;
        currentFib = newFib;
        n++;

        if (currentFib >= limit) {
            Toast.makeText(this, "Fibonacci limit reached", Toast.LENGTH_SHORT).show();
            return;
        }

        updateFibonacciDisplay();
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.fibonacci_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void reset(View view) {
        currentFib = 0;
        fibMinus2 = 1;
        fibMinus1 = 0;
        limit = 0;
        n = 0;
        mLimitInput.setText(""); // Clear the input
        updateFibonacciDisplay();
    }

    private void updateFibonacciDisplay() {
        if (showCount != null) {
            showCount.setText(Long.toString(currentFib));
            showCount.setTextColor(getFibonacciColor());
        }
    }

    private int getFibonacciColor() {
        // Change color based on the Fibonacci value
        if (n % 2 == 0) {
            return ContextCompat.getColor(this, R.color.colorFibonacciBlue);
        } else {
            return ContextCompat.getColor(this, R.color.colorFibonacciRed);
        }
    }
}