package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatePriceActivity extends AppCompatActivity {

    EditText edtItem1, edtItem2, edtItem3;
    TextView txtTotalPrice;
    Button btnCalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_price);

        edtItem1 = findViewById(R.id.edtItem1);
        edtItem2 = findViewById(R.id.edtItem2);
        edtItem3 = findViewById(R.id.edtItem3);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double item1 = Double.parseDouble(edtItem1.getText().toString());
                double item2 = Double.parseDouble(edtItem2.getText().toString());
                double item3 = Double.parseDouble(edtItem3.getText().toString());

                double totalPrice = item1 + item2 + item3;

                txtTotalPrice.setText("Total price: " + totalPrice);
            }
        });
    }
}
