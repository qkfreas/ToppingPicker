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
    private String itemSelected;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsById();

        String[] sports = getResources().getStringArray(R.array.sports_array);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, sports);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        button.setOnClickListener(this);


    }

    private void amountSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.amount_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.amount_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private void findViewsById() {
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.testbutton);
    }

    public void onClick(View v) {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }

        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }

        Intent intent = new Intent(getApplicationContext(),
                ResultActivity.class);

        // Create a bundle object
        Bundle b = new Bundle();
        b.putStringArray("selectedItems", outputStrArr);

        // Add the bundle to the intent.
        intent.putExtras(b);

        // start the ResultActivity
        startActivity(intent);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        itemSelected = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}

