package com.example.admin.creditcardapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText card_bal,yrly_int, min_pay, fin_bal,rem_month, paid_int;
    Button compute,clear;
    float principle, rate, monthlyFloatInterestPaid, minimum_payment, monthlyPrinciple, monthlyBalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card_bal=(EditText)findViewById(R.id.card_bal);
        yrly_int=(EditText)findViewById(R.id.yr_rate);
        min_pay=(EditText)findViewById(R.id.min_pay);
        fin_bal=(EditText)findViewById(R.id.fin_bal);
        rem_month=(EditText)findViewById(R.id.rem_mon);
        paid_int=(EditText)findViewById(R.id.paid_int);

        compute=(Button)findViewById(R.id.comp);
        clear=(Button)findViewById(R.id.clr);

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principle=Integer.parseInt(card_bal.getText().toString());
                rate=Integer.parseInt((yrly_int.getText().toString()));
                minimum_payment=Integer.parseInt(min_pay.getText().toString());
                float[] soln=total_floaterest_payable(principle, rate, minimum_payment);
                fin_bal.setText(String.valueOf(soln[1]));
                rem_month.setText(String.valueOf(soln[2]));
                paid_int.setText(String.valueOf(soln[0]));
            }
        });
    }

    private float[] total_floaterest_payable(float principle, float rate, float minimum_payment){
        float monthlyfloatInterestPaid = 0, monthlyPrinciple = 0, monthlyBalance = 0;
        float data[] = new float[3];
        float count = 0;

        do {
            count++;
            monthlyfloatInterestPaid = Math.round((principle * (rate / (100 * 12))));// 1
            monthlyPrinciple = minimum_payment - monthlyfloatInterestPaid;// 2
            monthlyBalance = principle - monthlyPrinciple;// 3
            principle = monthlyBalance;// 4

            if (count == 1) {

                data[0] = monthlyfloatInterestPaid;

                data[1] = monthlyBalance;

                System.out.println(monthlyfloatInterestPaid + " , " + monthlyBalance);

            }

        } while (monthlyBalance > 0);

        System.out.println(count);
        data[2] = count - 1;

        return data;


    }
}