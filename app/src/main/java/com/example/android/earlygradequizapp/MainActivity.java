package com.example.android.earlygradequizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.security.acl.Group;

public class MainActivity extends AppCompatActivity {

    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the submit button is clicked.
     */
    public void submitOrder(View view) {
//        *//*//*/ Figure out what the correct answer is
        RadioButton radioGroup1  = (RadioButton) findViewById(R.id.radio1_q1);
        boolean rg1 = radioGroup1.isChecked();


        RadioButton radioGroup2  = (RadioButton) findViewById(R.id.radio2_q4);
        boolean rg2 = radioGroup2.isChecked();


        RadioButton radioGroup3  = (RadioButton) findViewById(R.id.radio3_q2);
        boolean rg3 = radioGroup3.isChecked();


        RadioButton radioGroup4  = (RadioButton) findViewById(R.id.radio4_q3);
        boolean rg4 = radioGroup4.isChecked();


        RadioButton radioGroup5  = (RadioButton) findViewById(R.id.radio5_q4);
        boolean rg5 = radioGroup5.isChecked();

        EditText txtEdit= (EditText) findViewById(R.id.name);
        String name = txtEdit.getText().toString();

        // Calculate the score
        int score = calculateScore(rg1, rg2, rg3, rg4, rg5);

        // Display the report summary on the screen and send by mail
        String priceMessage = createOrderSummary(score, rg1, rg2, rg3, rg4, rg5, name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Early Grade App" + name);
        intent.putExtra(Intent.EXTRA_SUBJECT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null);
        startActivity(intent);

    }


    /**
     * Calculates the score of the pupil.
     *
     * @return total score
     */
    private int calculateScore (boolean radio1_q1, boolean radio2_q4, boolean radio3_q2, boolean radio4_q3, boolean radio5_q4){
        int basePrice = 0;
        if (radio1_q1) {
            basePrice = basePrice + 1;
        }
        if (radio2_q4) {
            basePrice = basePrice + 1;
        }
        if (radio3_q2) {
            basePrice = basePrice + 1;
        }
        if (radio4_q3) {
            basePrice = basePrice + 1;
        }
        if (radio5_q4) {
            basePrice = basePrice + 1;
        }
        return basePrice;
    }



    /**
     * Create summary of the order.
     *
     * @param Score            of the pupil
     * @param radio1_q1, radio2_q4, radio3_q2, radio4_q3 and radio5_q4 are the correct answer
     *to be return when each of the group radio buttons are clicked
     * @return text summary
     */
    private String createOrderSummary ( int score, boolean radio1_q1, boolean radio2_q4, boolean radio3_q2,
                                        boolean radio4_q3, boolean radio5_q4, String onTxtEdit){
        String priceMessage = "Name: " + onTxtEdit;
//        priceMessage +="This " + radio1_q1 + radio2_q4 + radio3_q2 +radio4_q3 + radio5_q4);
        priceMessage += "\nTotal Sore " + score;
        priceMessage += "\nExcellent for scoring " + score;
        return priceMessage;
    }

}
