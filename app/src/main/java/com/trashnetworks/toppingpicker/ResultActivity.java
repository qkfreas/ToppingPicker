package com.trashnetworks.toppingpicker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Bundle b = getIntent().getExtras();
        String[] resultArr = b.getStringArray("selectedItems");
        resultArr = randomize(resultArr);
        ListView lv = (ListView) findViewById(R.id.outputList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, resultArr);
        lv.setAdapter(adapter);
    }

    private String[] randomize(String[] arr) {
        String[] randomSelection = new String[arr.length];
        for (int i = 0; i < randomSelection.length; i++) {
            randomSelection[i] = arr[(int)(Math.random()*arr.length)];
        }
        return randomSelection;
    }
}