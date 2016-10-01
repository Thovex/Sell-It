package nl.hku.studenthome.sell_it;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PortfolioCheck extends Activity {

    ProgressBar progressbar;
    EditText b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_portfolio_check);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        progressbar.setProgress(0);
        progressbar.setMax(100);


        b = (EditText) findViewById(R.id.b);

        b.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(b.getText().length()<=0){
                    progressbar.incrementProgressBy(5);
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
