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
import android.widget.TextView;
import android.widget.Toast;

public class SollicitatieGesprek extends Activity {

    ArrayList<String> myStringList = new ArrayList<>();
    String[] questionsArray;
    int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_sollicitatie_gesprek);
        questionsArray = getResources().getStringArray(R.array.QuestionsArray);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        txtQuestionsAsked = (TextView) findViewById(R.id.txtQuestions);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        btnSend = (ImageButton) findViewById(R.id.btnSend);

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
}
