package android.practice.com.hskcihui;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class WordListActivity extends AppCompatActivity {

    WordListAdapter wordListAdapter = null;
    ListView listView;
    DatabaseController dbController = null;
    ArrayList<Words> words = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        int hskLevel;
        int difficultyLevel;

        dbController = new DatabaseController(getBaseContext());

        Intent intent = getIntent();
        hskLevel = intent.getIntExtra("hskLevel",0);
        difficultyLevel = intent.getIntExtra("difficultyLevel" , 0);


        words = dbController.getWordsByHskAndDifficultyLevel(hskLevel, difficultyLevel);


        wordListAdapter = new WordListAdapter(this, R.layout.activity_word_list_item, words);

        listView = (ListView)findViewById(R.id.ListViewWords);
        listView.setAdapter(wordListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

                Words selectedLine = words.get(index);

                Intent mIntent = new Intent(WordListActivity.this, WordDetail.class);
                mIntent.putExtra("ID", selectedLine.getId());
                mIntent.putExtra("SIMPLIFIED", selectedLine.getSimplified());
                mIntent.putExtra("TRADITIONAL", selectedLine.getTraditional());
                mIntent.putExtra("HSK", selectedLine.getHsk());
                mIntent.putExtra("PINYIN", selectedLine.getPinyin());
                mIntent.putExtra("ENGLISH", selectedLine.getEnglish());
                mIntent.putExtra("TYPE", selectedLine.getType());
                mIntent.putExtra("LEVEL", selectedLine.getLevel());
                mIntent.putExtra("INFO", selectedLine.getInfo());

                startActivity(mIntent);
            }
        });
    }
}
