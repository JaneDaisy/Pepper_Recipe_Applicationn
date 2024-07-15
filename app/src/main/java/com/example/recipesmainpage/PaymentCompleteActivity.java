package com.example.recipesmainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipesmainpage.R;

public class PaymentCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_complete);

        Button buttonReturnToMain = findViewById(R.id.buttonReturnToMain);
        buttonReturnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentCompleteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
