package com.example.camer_000.coffeeorderer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.DateFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    String name;
    private int cups;
    private int price;
    private boolean hasWhippedCream;
    private boolean hasChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayOrderSummary();
        cups = 0;
        price = 5;
        hasWhippedCream = false;
        hasChocolate = false;
        name = "Anon.";
    }

    /**
     * This method is called when the order button is clicked.
     * It calculates the price to have it displayed.
     */
    public void submitOrder(View view) {
        sendSummary();
    }

    /**
     *This method is called when the plus button is clicked.
     * It increases cups by 1.
     */
    public void increment(View view){
        if (cups < 100) {
            cups++;
            displayCups();
            displayOrderSummary();
        } else {
            Toast t = Toast.makeText(getApplicationContext(), "Sorry, you can't order more than 100 cups of coffee!", Toast.LENGTH_SHORT);
            t.show();
        }

    }

    /**
     *This method is called when the minus button is clicked.
     * It increases cups by 1.
     */
    public void decrement(View view){
        if (cups > 0) {
            cups--;
            displayCups();
            displayOrderSummary();
        }
    }

    public void wantsWhippedCream(View view) {
        hasWhippedCream = flipBoolean(hasWhippedCream);
        displayOrderSummary();
    }

    public void wantsChocolate(View view) {
        hasChocolate = flipBoolean(hasChocolate);
        displayOrderSummary();
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayOrderSummary() {
        displayMessage(NumberFormat.getCurrencyInstance().format(calculatePrice()));
    }

    private void sendSummary() {
        DateFormat df = new SimpleDateFormat("EEE, d MMM, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        checkName();
        String orderSummary;
        String yes = "Yes";
        String no = "None";
        orderSummary = "Name: " + name;
        orderSummary += "\nCups: ";
        orderSummary += cups;
        orderSummary += "\nToppings:";
        orderSummary += "\n  Whipped Cream: ";
        if (hasWhippedCream) {
            orderSummary += yes;
        } else {
            orderSummary += no;
        }
        orderSummary += "\n  Chocolate: ";
        if (hasChocolate) {
            orderSummary += yes;
        } else {
            orderSummary += no;
        }
        orderSummary += "\nTotal: ";
        orderSummary += NumberFormat.getCurrencyInstance().format(calculatePrice());

        orderSummary += "\nThank you!";


        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "user@gmail.com");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order Receipt - " + date);
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed, you weirdo.", Toast.LENGTH_SHORT).show();
        }


    }

    private int calculatePrice() {
        int p = cups * price;
        if (hasWhippedCream) {
            p += cups;
        }
        if (hasChocolate) {
            p += cups * 2;
        }
        return p;

    }

    private void checkName() {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String s = nameField.getText().toString();
        if (!s.isEmpty()) {
            name = s;
        } else {
            name = "Anon.";
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayCups() {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + cups);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private boolean flipBoolean(Boolean b) {
        return !b;
    }
}