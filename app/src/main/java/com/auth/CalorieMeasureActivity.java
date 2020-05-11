package com.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.easyfitness.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CalorieMeasureActivity extends AppCompatActivity {

    Button add_item_button,calculate_item_button;
    ListView listView;
    TextView calorie_result;
    List<FoodData> foodDataList;
    DatabaseReference databaseReference;
    String foodweight;
    String[] array=new String[100];
    int count=0,result=0;
    FoodData foodData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caorie_measure);
        add_item_button=findViewById(R.id.add_item_id);
        calculate_item_button=findViewById(R.id.calculate_calorie_id);
        listView=findViewById(R.id.listview_id);
        calorie_result=findViewById(R.id.calorie_result_id);

        foodDataList=new ArrayList<>();


        databaseReference=FirebaseDatabase.getInstance().getReference("FoodData");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodDataList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                     foodData=dataSnapshot1.getValue(FoodData.class);
                     foodDataList.add(foodData);
                }
                FoodAdapter foodAdapter=new FoodAdapter(CalorieMeasureActivity.this,foodDataList);
                listView.setAdapter(foodAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        AlertDialog.Builder alertdialog=new AlertDialog.Builder(CalorieMeasureActivity.this);
                        alertdialog.setTitle("Enter Weight");
                        final EditText weight=new EditText(CalorieMeasureActivity.this);
                        weight.setInputType(InputType.TYPE_CLASS_NUMBER);
                        alertdialog.setView(weight);

                        String click=foodAdapter.getItem(i).getCalorie();
                        Toast.makeText(getApplicationContext(),"calorie "+click,Toast.LENGTH_LONG).show();

                        alertdialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                foodweight=weight.getText().toString();
                                    count=Integer.parseInt(foodweight)*Integer.parseInt(click);
                            }
                        }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertdialog.show();
                        result=result+count;
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        add_item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CalorieMeasureActivity.this,FoodDetailsActivity.class);
                startActivity(intent);
            }
        });
        calculate_item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calorie_result.setText("Total Calorie = "+result);
            }
        });
    }
}
