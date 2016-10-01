package nl.hku.studenthome.sell_it;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SectorItem extends Activity {

    private int sectorId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        sectorId = b.getInt("sector");

        setContentView(R.layout.activity_sector_item);
    }
    public void modeSelection(View view) {
        switch (view.getId()) {
            case R.id.buttonSG:
                Intent intent1 = new Intent(this, SollicitatieGesprek.class);
                intent1.putExtra("sector", sectorId);
                startActivity(intent1);
                break;
            case R.id.buttonPC:
                Intent intent2 = new Intent(this, PortfolioCheck.class);
                startActivity(intent2);
                break;
            default:
                break;
        }

    }


    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
