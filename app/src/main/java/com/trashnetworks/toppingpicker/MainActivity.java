package com.trashnetworks.toppingpicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        OnClickListener {
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        final Button button = findViewById(R.id.my_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if (button.getText().equals("Hello")) {
//                    button.setText("Goodbye");
//                    TextView textView = findViewById(R.id.center_text);
//                    textView.setText("You pressed the button!");
//                }
//                else if (button.getText().equals("Goodbye")) {
//                    button.setText("Hello");
//                    TextView textView = findViewById(R.id.center_text);
//                    textView.setText("Hello World!");
//                }
//            }
//        });
//    }
    Button button;
    ListView listView;
    ArrayAdapter<String> adapter;
    private SpinnerActivity spinnerActivity;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList();
        amountSpinner();
    }

    private void itemList() {
        try {

            findViewsById();

            String[] sports = getResources().getStringArray(R.array.sports_array);
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_multiple_choice, sports);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);

            button.setOnClickListener(this);
        } catch (Exception e) {
            System.out.println("ERROR AT: MainActivity.itemList");
        }
    }

    private void amountSpinner() {
        try {
            spinnerActivity = new SpinnerActivity();
            Spinner spinner = (Spinner) findViewById(R.id.amount_spinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.amount_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(spinnerActivity);
        } catch (Exception e) {
            System.out.println("ERROR AT: MainActivity.amountSpinner");
        }
    }

    private void findViewsById() {
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.testbutton);
    }

    public void onClick(View v) {
        try {

            SparseBooleanArray checked = listView.getCheckedItemPositions();
            ArrayList<String> selectedItems = new ArrayList<String>();
            for (int i = 0; i < checked.size(); i++) {
                // Item position in adapter
                int position = checked.keyAt(i);
                // Add sport if it is checked i.e.) == TRUE!
                if (checked.valueAt(i)) {
                    selectedItems.add(adapter.getItem(position));
                }
            }

            String[] outputStrArr = new String[selectedItems.size() + 1];

            for (int i = 0; i < selectedItems.size() - 1; i++) {
                outputStrArr[i] = selectedItems.get(i);
            }
            outputStrArr[selectedItems.size()] = spinnerActivity.getItemSelected();
            System.out.println("Items Selected " + spinnerActivity.getItemSelected());

            Intent intent = new Intent(getApplicationContext(),
                    ResultActivity.class);

            // Create a bundle object
            Bundle b = new Bundle();
            b.putStringArray("selectedItems", outputStrArr);

            // Add the bundle to the intent.
            intent.putExtras(b);

            // start the ResultActivity
            startActivity(intent);
        } catch (Exception e) {
            System.out.println("ERROR AT: MainActivity.onClick");
        }
    }
}

