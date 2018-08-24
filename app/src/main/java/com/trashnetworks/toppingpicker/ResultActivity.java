package com.trashnetworks.toppingpicker;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.result);

            Bundle b = getIntent().getExtras();
            String[] resultArr = b.getStringArray("selectedItems");
            resultArr = randomize(resultArr);
            ListView lv = (ListView) findViewById(R.id.outputList);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, resultArr);
            lv.setAdapter(adapter);
        } catch (Exception e) {
            System.out.println("ERROR AT: ResultActivity.onCreate");
        }
    }

    private String[] randomize(String[] arr) {
        String [] randomSelection = null;
        try {

            randomSelection = new String[Integer.parseInt(arr[arr.length-1])];
            for (int i = 0; i < randomSelection.length; i++) {
                randomSelection[i] = arr[(int)(Math.random()*arr.length)];
            }
        } catch (Exception e) {
            System.out.println("ERROR AT: ResultActivity.randomize");
        }
        return randomSelection;
    }
}