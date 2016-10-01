package nl.hku.studenthome.sell_it;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Locale;

import android.content.ActivityNotFoundException;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SollicitatieGesprek extends Activity {

    private ArrayList<String> myStringList = new ArrayList<>();
    private String[] questionsArray;
    private int currentQuestion = 0;
    private int sectorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        sectorId = b.getInt("sector");
        fillQuestionsArray();
        setContentView(R.layout.activity_sollicitatie_gesprek);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        txtQuestionsAsked = (TextView) findViewById(R.id.txtQuestions);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        btnSend = (ImageButton) findViewById(R.id.btnSend);
        setTheme();
        txtQuestionsAsked.setText(questionsArray[currentQuestion]);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtSpeechInput.getText() != "") {
                    promptSendInput();
                }
            }
        });
    }

    private void fillQuestionsArray(){
        switch (sectorId){
            case 1:
                questionsArray = getResources().getStringArray(R.array.SectorHorecaVragen);
                break;
            case 2:
                questionsArray = getResources().getStringArray(R.array.SectorOnderwijsVragen);
                break;
            case 3:
                questionsArray = getResources().getStringArray(R.array.SectorGrafischeVragen);
                break;
            case 4:
                questionsArray = getResources().getStringArray(R.array.SectorTuinbouwVragen);
                break;
            case 5:
                questionsArray = getResources().getStringArray(R.array.SectorZorgVragen);
                break;
            case 6:
                questionsArray = getResources().getStringArray(R.array.SectorBouwVragen);
                break;
            default:
                break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    private TextView txtSpeechInput;
    private TextView txtQuestionsAsked;
    private ImageButton btnSpeak;
    private ImageButton btnSend;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                }
                break;
            }
        }
    }

    private void promptSendInput() {
        String addString = (String) txtSpeechInput.getText();
        myStringList.add(addString);
        for (int i=0; i<myStringList.size();i++){
            Log.v("Tag", myStringList.get(i));
        }
        txtSpeechInput.setText(null);
        currentQuestion++;
        if (currentQuestion==questionsArray.length) {
            //TODO: go to results screen
        } else {
            setQuestions();
        }
    }

    private void setQuestions(){
        txtQuestionsAsked.setText(questionsArray[currentQuestion]);
    }

    private void setTheme() {
        RelativeLayout background = (RelativeLayout) findViewById(R.id.sollicitatiebg);
        ImageView character = (ImageView) findViewById(R.id.sollicitatiecharacter);

        switch (sectorId){
            case 1:
                background.setBackgroundResource(R.drawable.bg_horeca);
                character.setImageResource(R.drawable.char_horeca);
                break;
            case 2:
                background.setBackgroundResource(R.drawable.bg_onderwijs);
                character.setImageResource(R.drawable.char_onderwijs);
                break;
            case 3:
                background.setBackgroundResource(R.drawable.bg_grafisch);
                character.setImageResource(R.drawable.char_grafisch);
                break;
            case 4:
                background.setBackgroundResource(R.drawable.bg_tuinbouw);
                character.setImageResource(R.drawable.char_tuinbouw);
                break;
            case 5:
                background.setBackgroundResource(R.drawable.bg_zorg);
                character.setImageResource(R.drawable.char_zorg);
                break;
            case 6:
                background.setBackgroundResource(R.drawable.bg_bouw);
                character.setImageResource(R.drawable.char_bouw);
                break;
            default:
                break;
        }
    }
}
