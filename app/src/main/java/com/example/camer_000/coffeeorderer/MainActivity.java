package com.example.camer_000.coffeeorderer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

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
        displayOrderSummary(false);
        cups = 98;
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
        displayOrderSummary(true);
    }

    /**
     *This method is called when the plus button is clicked.
     * It increases cups by 1.
     */
    public void increment(View view){
        if (cups < 100) {
            cups++;
            displayCups();
            displayOrderSummary(false);
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
            displayOrderSummary(false);
        }
    }

    public void wantsWhippedCream(View view) {
        hasWhippedCream = flipBoolean(hasWhippedCream);
        displayOrderSummary(false);
    }

    public void wantsChocolate(View view) {
        hasChocolate = flipBoolean(hasChocolate);
        displayOrderSummary(false);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayOrderSummary(boolean last) {
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
        if (last) {
            orderSummary += "\nThank you!";
        } else {
            orderSummary += "\n";
        }
        displayMessage(orderSummary);
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