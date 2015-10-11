package com.example.camer_000.coffeeorderer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int cups;
    private int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cups = 0;
        price = 5;
    }

    /**
     * This method is called when the order button is clicked.
     * It calculates the price to have it displayed.
     */
    public void submitOrder(View view) {
        displayPrice(cups * price);
    }

    /**
     *This method is called when the plus button is clicked.
     * It increases cups by 1.
     */
    public void increment(View view){
        cups++;
        display();
    }

    /**
     *This method is called when the minus button is clicked.
     * It increases cups by 1.
     */
    public void decrement(View view){
        if (cups > 0){
            cups--;
            display();
        }
    }


    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display() {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + cups);
    }
}