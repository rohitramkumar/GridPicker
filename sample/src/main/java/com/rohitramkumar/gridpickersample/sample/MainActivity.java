package com.rohitramkumar.gridpickersample.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rohitramkumar.gridpicker.app.ClickListener;
import com.rohitramkumar.gridpicker.app.GridPicker;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridPicker gridPicker = (GridPicker) findViewById(R.id.sample_picker);

        gridPicker.setClickListeners(new ClickListener() {
            @Override
            public void onAdvanceButtonClicked() {
                // do nothing
            }

            @Override
            public void onRewindButtonClicked() {
                // do nothing
            }

            @Override
            public void onNumberClicked(float num) {
                Toast toast = Toast.makeText(MainActivity.this, Float.toString(num), Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
