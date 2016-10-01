package nl.hku.studenthome.sell_it;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_settings);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }


}
