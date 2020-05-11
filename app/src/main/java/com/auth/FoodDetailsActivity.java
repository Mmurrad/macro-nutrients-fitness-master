package com.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.easyfitness.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodDetailsActivity extends AppCompatActivity {

    EditText food_name,food_weight,food_calorie;
    Button save_button;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details2);

        food_name=findViewById(R.id.food_name_id);
        food_weight=findViewById(R.id.food_weight_id);
        food_calorie=findViewById(R.id.food_calorie_id);
        databaseReference= FirebaseDatabase.getInstance().getReference("FoodData");

        save_button=findViewById(R.id.save_id);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=food_name.getText().toString();
                String weight=food_weight.getText().toString();
                String calorie=food_calorie.getText().toString();

                FoodData foodData=new FoodData(name,weight,calorie);
                databaseReference.child(name).setValue(foodData);
                Toast.makeText(getApplicationContext(),"Information Store Succesfully",Toast.LENGTH_SHORT).show();
                food_name.setText(null);
                food_calorie.setText(null);
                food_weight.setText(null);
            }
        });
    }
}
