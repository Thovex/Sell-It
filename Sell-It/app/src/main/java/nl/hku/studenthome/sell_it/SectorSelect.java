package nl.hku.studenthome.sell_it;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class SectorSelect extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_sector_select);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    public void sectorSelection(View view) {
        int sector = 0;
        switch (view.getId()) {
            case R.id.buttonSector1:
                sector = 1;
                break;
            case R.id.buttonSector2:
                sector = 2;
                break;
            case R.id.buttonSector3:
                sector = 3;
                break;
            case R.id.buttonSector4:
                sector = 4;
                break;
            case R.id.buttonSector5:
                sector = 5;
                break;
            case R.id.buttonSector6:
                sector = 6;
                break;
            default:
                break;
        }
        Intent intent = new Intent(this, SectorItem.class);
        intent.putExtra("sector", sector);
        startActivity(intent);
    }
}
