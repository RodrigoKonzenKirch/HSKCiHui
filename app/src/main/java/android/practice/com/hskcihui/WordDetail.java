package android.practice.com.hskcihui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WordDetail extends AppCompatActivity {

    private DatabaseController dbController = null;
    private String id;
    private String level;
    private String simplified;
    private String traditional;
    private String hsk;
    private String pinyin;
    private String english;
    private String type;
    private String info;
    private final String[] levelContent= {"New Word", "Hard", "Not so hard", "Easy", "Special"};
    final Context context = this;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString( R.string.app_name)+" - "+
                getResources().getString(R.string.wordDetailActivityName));

        dbController = new DatabaseController(getBaseContext());

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            String noData = getResources().getString(R.string.no_data);
            id = noData;
            simplified = noData;
            traditional = noData;
            hsk = noData;
            pinyin = noData;
            english = noData;
            type = noData;
            level = noData;
            info = noData;
        }else{
            id = extras.getString("ID");
            simplified = extras.getString("SIMPLIFIED");
            traditional = extras.getString("TRADITIONAL");
            hsk = "HSK "+extras.getString("HSK");
            pinyin = extras.getString("PINYIN");
            english = extras.getString("ENGLISH");
            type = extras.getString("TYPE");
            level = extras.getString("LEVEL");
            info = extras.getString("INFO");
        }

        final TextView textViewEnglish = (TextView) findViewById(R.id.textViewWordEnglish);
        textViewEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mIntent = new Intent(context, RequestUserInputData.class);
                mIntent.putExtra("currentValue", textViewEnglish.getText().toString());
                startActivityForResult(mIntent, REQUEST_CODE);
            }
        });

        updateInterface();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("englishUpdated");
                dbController.setEnglishValueById(result, id);
                TextView textViewEnglish = (TextView) findViewById(R.id.textViewWordEnglish);
                textViewEnglish.setText(result);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void updateInterface(){
        int color;

        switch (level){
            case "0":
                color = Color.WHITE;
                break;
            case "1":
                color = Color.RED;
                break;
            case "2":
                color = Color.YELLOW;
                break;
            case "3":
                color = Color.GREEN;
                break;
            case "4":
                color = Color.CYAN;
                break;
            default:
                color = Color.WHITE;
                break;
        }

        TextView textViewSimplified = (TextView) findViewById(R.id.textViewWordSimplified);
        TextView textViewTraditional = (TextView) findViewById(R.id.textViewWordTraditional);
        TextView textViewHsk = (TextView) findViewById(R.id.textViewWordHskLevel);
        TextView textViewPinyin = (TextView) findViewById(R.id.textViewWordPinyin);
        TextView textViewEnglish = (TextView) findViewById(R.id.textViewWordEnglish);
        TextView textViewLevel = (TextView) findViewById(R.id.textViewWordLevel);

        textViewSimplified.setTextColor(color);
        textViewTraditional.setTextColor(color);
        textViewHsk.setTextColor(color);
        textViewPinyin.setTextColor(color);
        textViewEnglish.setTextColor(color);
        textViewLevel.setTextColor(color);

        String typePinyin = "["+type+"]\n"+pinyin;
        textViewSimplified.setText(simplified);
        textViewTraditional.setText(traditional);
        textViewHsk.setText(hsk);
        textViewPinyin.setText(typePinyin);
        textViewEnglish.setText(english);
        String textViewLevelContent = "LEVEL "+level+
                ": "+levelContent[Integer.parseInt(level)];
        textViewLevel.setText(textViewLevelContent);
    }

    public void setLevel(View view) {
        switch (view.getId()){
            case R.id.buttonWordDifficulty0:
                dbController.setLevelById(id, "0");
                level = "0";
                updateInterface();
                break;
            case R.id.buttonWordDifficulty1:
                dbController.setLevelById(id, "1");
                level = "1";
                updateInterface();
                break;
            case R.id.buttonWordDifficulty2:
                dbController.setLevelById(id, "2");
                level = "2";
                updateInterface();
                break;
            case R.id.buttonWordDifficulty3:
                dbController.setLevelById(id, "3");
                level = "3";
                updateInterface();
                break;
            case R.id.buttonWordDifficulty4:
                dbController.setLevelById(id, "4");
                level = "4";
                updateInterface();
                break;
        }
    }
}
