package nl.hku.studenthome.sell_it;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RatingBar;

import java.util.Random;

public class ResultsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_results);

        Random randomizer = new Random();

        int randNr1 = randomizer.nextInt(6);
        int randNr2 = randomizer.nextInt(6);
        int randNr3 = randomizer.nextInt(6);
        int randNr4 = randomizer.nextInt(6);

        RatingBar rb1 = (RatingBar) findViewById(R.id.ratingBar);
        RatingBar rb2 = (RatingBar) findViewById(R.id.ratingBar2);
        RatingBar rb3 = (RatingBar) findViewById(R.id.ratingBar3);
        RatingBar rb4 = (RatingBar) findViewById(R.id.ratingBar4);

        rb1.setRating(randNr1);
        rb2.setRating(randNr2);
        rb3.setRating(randNr3);
        rb4.setRating(randNr4);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
