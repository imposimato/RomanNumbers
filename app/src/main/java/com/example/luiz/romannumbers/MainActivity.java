package com.example.luiz.romannumbers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input = (EditText) findViewById(R.id.input);
        Button convert = (Button) findViewById(R.id.convert);
        input.setEnabled(true);

    }

    public void onConvert(View view) {

        EditText input = (EditText) findViewById(R.id.input);
        TextView result = (TextView) findViewById(R.id.result);
        ToggleButton tb = (ToggleButton) findViewById(R.id.roman_or_arabic);

        boolean tbOn = tb.isChecked();

        if (tbOn){
            String roman;
            int number;
            try {
                number = Integer.parseInt(input.getText().toString());
                roman = ArabicToRoman.toRoman(number);
                result.setText(roman);
            } catch (Throwable e) {
                Toast toast = Toast.makeText(getApplicationContext(), "Something wrong happened", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }

        } else {
            String roman;
            int number;
            try {
                roman = input.getText().toString().toUpperCase();
                number = RomanToArabic.toArabic(roman);
                result.setText(String.valueOf(number));
            } catch (NotValidRoman nvr){
                Toast toast = Toast.makeText(getApplicationContext(), nvr.getMessage(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }

    public void onToggleClicked(View view) {

        EditText edit = (EditText) findViewById(R.id.input);
        TextView result = (TextView) findViewById(R.id.result);
        edit.setText("");
        result.setText("");

        boolean on = ((ToggleButton) view).isChecked();

        if (on){
            edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            edit.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }
}
