package com.example.justjava; /**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justjava.R;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void submitOrder(View view) {
        EditText text = (EditText) findViewById(R.id.name_field);
        String valueName = text.getText().toString();
//        Log.v("MainActivity", "Name "+ valueName);

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);

        boolean hasWhippedCream =  whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();

//        Log.v("MainActivity", "Add whipped cream " + hasWhippedCream);

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        displayMessage(createOrderSummery(valueName, price, hasWhippedCream, hasChocolate));
    }

    /**
     * Calculates the price of the order.
     * * @return total price
     */
    private int calculatePrice( boolean addWhippedCream, boolean addChocolate ){
        int basePrice = 5;
        if(addWhippedCream){
            basePrice = basePrice + 1;
        }
        if(addChocolate){
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
    }

    private String createOrderSummery(String valueName, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + valueName;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuntity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    public void increment(View view) {
        if(quantity == 100){
            Toast.makeText(this, "You cann't have more than 100 cups of coffee!", Toast.LENGTH_LONG).show();
            return;
        }
        quantity = quantity + 1;
        displayQuntity(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1){
            Toast.makeText(this, "You cann't have less 1 cup of coffe", Toast.LENGTH_LONG).show();
            return;
        }
        quantity = quantity - 1;
        displayQuntity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummeryTextView = (TextView) findViewById(R.id.order_summery_text_view);
        orderSummeryTextView.setText(message);
    }

}