package android.practice.com.hskcihui;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseController crud = new DatabaseController(getBaseContext());
        Cursor cursor = crud.getWordsByHskLevel(2);

        String fullMessage = "Cursor [0]: ";
        try{
            while (cursor.moveToNext()){
                fullMessage = fullMessage+cursor.getString(1)+cursor.getString(7)+",";
            }
        }finally {
            cursor.close();
        }

        TextView myText = (TextView) findViewById(R.id.text_to_display);
        myText.setText(fullMessage);

    }
}
