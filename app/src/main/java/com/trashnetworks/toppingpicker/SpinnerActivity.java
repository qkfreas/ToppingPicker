package com.trashnetworks.toppingpicker;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
//    Spinner spinner = (Spinner) findViewById(R.id.amount_spinner);
    private String itemSelected;

//    public SpinnerActivity() {
//
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.amount_array, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
//    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
         itemSelected = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public String getItemSelected() {
        return itemSelected;
    }
}
