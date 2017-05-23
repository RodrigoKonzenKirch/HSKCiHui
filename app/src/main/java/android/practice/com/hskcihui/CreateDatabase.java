package android.practice.com.hskcihui;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

    /* HSK          - Level of HSK test (1 to 6)
    *  Simplified   - Chinese words using simplified character
    *  Traditional  - Chinese words using traditional character
    *  Pinyin       - Chinese words using pinyin
    *  English      - Translation of the words
    *  Type         - Verb, noun, adjective, adverb, numeral, auxiliary, measure word, pronoun, conjunction, preposition
    *  Level        - 0(not rated), 1(hard), 2(medium), 3(easy), 4(special)
    *  Info         - Information about the word
    * */

class CreateDatabase extends SQLiteOpenHelper {

    private static final String TAG = "CreateDatabase";
    private Context myContext;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HSKVocabulary";
    final String TABLE = "words";
    final String ID = "_id";
    final String HSK = "hsk";
    final String SIMPLIFIED = "simplified";
    final String TRADITIONAL = "traditional";
    final String PINYIN = "pinyin";
    final String ENGLISH = "english";
    final String TYPE = "type";
    final String LEVEL = "level";
    final String INFO = "info";

    CreateDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE + " ("
                + ID + " integer primary key autoincrement, "
                + HSK + " text, "
                + SIMPLIFIED + " text, "
                + TRADITIONAL + " text, "
                + PINYIN + " text, "
                + ENGLISH + " text, "
                + TYPE + " text, "
                + LEVEL + " text, "
                + INFO + " text"
                + ")";

        db.execSQL(sql);

        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(myContext.getAssets().open("Data.csv"), "UTF-8"));

            String myLine;
            String[] values;
            ContentValues contentValues = new ContentValues();

            while ((myLine = reader.readLine()) != null ){
                values = myLine.split("、");

                contentValues.put(HSK, values[0]);
                contentValues.put(SIMPLIFIED, values[1]);
                contentValues.put(TRADITIONAL, values[2]);
                contentValues.put(PINYIN, values[3]);
                contentValues.put(ENGLISH, values[4]);
                contentValues.put(TYPE, values[5]);
                contentValues.put(LEVEL, values[6]);
                contentValues.put(INFO, values[7]);

                db.insert(TABLE, null, contentValues);
            }
        }catch (IOException e){
            Log.e(TAG, "Error Opening File: "+e.getMessage());
        }finally {
            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    Log.e(TAG, "Error Closing File Reader: "+e.getMessage());
                }
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE);
        onCreate(db);
    }
}
