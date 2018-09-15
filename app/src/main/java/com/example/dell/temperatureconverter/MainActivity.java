package com.example.dell.temperatureconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Spinner inputUnit, outputUnit;
    private EditText inputValue;
    private TextView result;
    private Button convertButton;


    private String[] units = {"Kelvin", "Celsius", "Fahrenheit"};

    private double value;
    //User selected units
    private String input, output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initVariable();
        initFunctionality();
        String title = "<font color = '#000000'>Temperature</font>";

        setTitle(Html.fromHtml(title));

        setTitle("Temperature");

    }

    private void initView() {
        inputUnit = findViewById(R.id.inputUnitId);
        outputUnit = findViewById(R.id.outputUnitId);
        result = findViewById(R.id.resultId);
        inputValue = findViewById(R.id.inputValueId);
        convertButton = findViewById(R.id.convertButtonId);


    }

    private void initVariable() {
    }

    private void initFunctionality() {

        ArrayAdapter adapter = new ArrayAdapter( MainActivity.this, R.layout.support_simple_spinner_dropdown_item, units );

        inputUnit.setAdapter(adapter);
        outputUnit.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();

            }
        });

        inputUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                input = units[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //get output unit
        outputUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                output = units[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void calculate() {

       value = Double.parseDouble(inputValue.getText().toString());

       double res = convert (input, output, value);
       result.setText(String.valueOf(res));

    }

    private double convert(String inputUnit, String outputUnit, double value) {

        double outputValue = 0;

        if (input.equalsIgnoreCase("Fahrenheit")){
            if (output.equalsIgnoreCase("Celsius")){

              outputValue = (value - 32) * (5/9.0);
            }
            else if (output.equalsIgnoreCase("Kelvin")){
                outputValue = (value - 32) * 5/9.0 + 273.15;
            }
            else {
                outputValue = value;
            }
        }
        else if (input.equalsIgnoreCase("Kelvin")){
            //from Kelvin to others
            if(output.equalsIgnoreCase("Fahrenheit")){
                outputValue = (value - 273.15) * 9/5.0 + 32;
            }
            else if (output.equalsIgnoreCase("Celsius")){
                outputValue = value - 273.15;
            }
            else {
                outputValue = value;
            }
        }
        else if(input.equalsIgnoreCase("Celsius")){
            // from celsius to others
            if (output.equalsIgnoreCase("Fahrenheit")){
                outputValue = (value * 9/5.0) + 32;
            }
            else if (output.equalsIgnoreCase("Kelvin")){
               outputValue = value + 273.15;
            }
            else {
                outputValue  = value;
            }
        }

        return outputValue;
    }
}
