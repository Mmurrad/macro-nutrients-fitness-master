package com.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.easyfitness.R;

public class BMICalculationActivity extends AppCompatActivity {

    EditText weight_text,height_text;
    Button calculation_bmi_button;
    TextView bmi_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i_calculation);
        height_text=findViewById(R.id.height_id);
        weight_text=findViewById(R.id.weight_id);
        calculation_bmi_button=findViewById(R.id.calculate_bmi_id);
        bmi_result=findViewById(R.id.bmi_result_id);

        calculation_bmi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weight=weight_text.getText().toString();
                String height=height_text.getText().toString();

                float w=Float.parseFloat(weight);
                float h=Float.parseFloat(height)/100;

                float r=w/(h*h);
                bmi_result.setText("BMI "+r);
            }
        });
    }
}
