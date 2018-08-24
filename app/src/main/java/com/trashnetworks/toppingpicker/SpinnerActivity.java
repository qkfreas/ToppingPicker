package com.trashnetworks.toppingpicker;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private String itemSelected;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        try {

            String var = parent.getItemAtPosition(pos).toString();
            System.out.println("This is var: " + var);
            setItemSelected(var);
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + var,
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            System.out.println("ERROR AT: SpinnerActivity.onItemsSelected");
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public String getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(String itemSelected) {
        this.itemSelected = itemSelected;
    }
}
