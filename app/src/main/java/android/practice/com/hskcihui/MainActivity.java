package android.practice.com.hskcihui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choosing);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

    }

    public void generateListOfWords(View view){
        int radioButtonPositionHskLevel;
        int radioButtonPositionDifficultyLevel;
        int radioButtonID;
        View mRadioButton;

        RadioGroup radioGroupHskLevel = (RadioGroup) findViewById(R.id.radioGroupHskLevel);
        RadioGroup radioGroupDifficultyLevel = (RadioGroup) findViewById(R.id.radioGroupDifficultyLevel);

        radioButtonID = radioGroupHskLevel.getCheckedRadioButtonId();
        mRadioButton = radioGroupHskLevel.findViewById(radioButtonID);
        radioButtonPositionHskLevel = radioGroupHskLevel.indexOfChild(mRadioButton);

        radioButtonID = radioGroupDifficultyLevel.getCheckedRadioButtonId();
        mRadioButton = radioGroupDifficultyLevel.findViewById(radioButtonID);
        radioButtonPositionDifficultyLevel = radioGroupDifficultyLevel.indexOfChild(mRadioButton);

        Intent mIntent = new Intent(this, WordListActivity.class);
        mIntent.putExtra("hskLevel", radioButtonPositionHskLevel);
        mIntent.putExtra("difficultyLevel", radioButtonPositionDifficultyLevel);
        startActivity(mIntent);
    }
}
