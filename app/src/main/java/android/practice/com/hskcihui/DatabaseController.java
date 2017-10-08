package android.practice.com.hskcihui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;


class DatabaseController {

    private CreateDatabase database;

    DatabaseController(Context context){
        database = new CreateDatabase(context);
    }

    public ArrayList<Words> getWordsByHskAndDifficultyLevel(int hskLevel, int difficultyLevel){
        SQLiteDatabase db;
        ArrayList<Words> words = new ArrayList<>();
        db = database.getReadableDatabase();

        String[] columns = {CreateDatabase.ID, CreateDatabase.HSK, CreateDatabase.SIMPLIFIED, CreateDatabase.TRADITIONAL,
                CreateDatabase.PINYIN,CreateDatabase.ENGLISH,CreateDatabase.TYPE,CreateDatabase.LEVEL,CreateDatabase.INFO};

        /* hsklevel and difficultylevel are parameters for DB query
          hsklevel value range from 0 to 6
          where 0(all 6 levels), 1 to 6 = HSK 1 to 6

          DifficultyLevel value range from 0 to 5.
          Convert user input value into database value:
          Input received from interface = 0(All), 1(not rated), 2(hard), 3(medium), 4(easy), 5(special)
          Database internal value =       0(not rated), 1(hard), 2(medium), 3(easy), 4(special)
 * */
        String selection = null;
        String[] selectionArgs = null;
        if((hskLevel <= 0 || hskLevel > 6) && (difficultyLevel <= 0 || difficultyLevel > 5)){
            selection = null;
            selectionArgs = null;
        }else if (!(hskLevel <= 0 || hskLevel > 6) && (difficultyLevel <= 0 || difficultyLevel > 5)){
            selection = CreateDatabase.HSK+" = ? ";
            String[] hskLevelToString = {Integer.toString(hskLevel)};
            selectionArgs = hskLevelToString;
        }else if ((hskLevel <= 0 || hskLevel > 6) && !(difficultyLevel <= 0 || difficultyLevel > 5)){
            selection = CreateDatabase.LEVEL+" = ? ";
            String[] difficultyLevelToString = {Integer.toString(difficultyLevel-1)};
            selectionArgs = difficultyLevelToString;
        }else if (!(hskLevel <= 0 || hskLevel > 6) && !(difficultyLevel <= 0 || difficultyLevel > 5)){
            selection = CreateDatabase.HSK+" = ? AND "+CreateDatabase.LEVEL+" = ? ";
            String[] difficultyLevelToString = {Integer.toString(hskLevel), Integer.toString(difficultyLevel-1)};
            selectionArgs = difficultyLevelToString;
        }

        Cursor cursor = db.query(database.TABLE, columns, selection, selectionArgs, null, null, CreateDatabase.ID);

        if(cursor.getCount()<=0){
            String noDataWarning = "No data found";
            words.add(new Words(noDataWarning,noDataWarning,noDataWarning,noDataWarning,noDataWarning,
                    noDataWarning,noDataWarning,noDataWarning,noDataWarning));
        }else{

            while (cursor.moveToNext()) {
                words.add(new Words(cursor.getString(cursor.getColumnIndex(CreateDatabase.ID)),
                        cursor.getString(cursor.getColumnIndex(CreateDatabase.HSK)),
                        cursor.getString(cursor.getColumnIndex(CreateDatabase.SIMPLIFIED)),
                        cursor.getString(cursor.getColumnIndex(CreateDatabase.TRADITIONAL)),
                        cursor.getString(cursor.getColumnIndex(CreateDatabase.PINYIN)),
                        cursor.getString(cursor.getColumnIndex(CreateDatabase.ENGLISH)),
                        cursor.getString(cursor.getColumnIndex(CreateDatabase.TYPE)),
                        cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)),
                        cursor.getString(cursor.getColumnIndex(CreateDatabase.INFO))));
            }
        }

        db.close();
        cursor.close();
        return words;
    }

    public void setLevelById(String id, String level){
        SQLiteDatabase db;
        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CreateDatabase.LEVEL, level);

        String selection = CreateDatabase.ID + " LIKE ?";
        String[] selectionArgs = {id};

        db.update(database.TABLE, values, selection, selectionArgs);

        db.close();

    }

    public int[][] getStatistics(){
        int[][] stats = new int[5][6];


        return stats;
    }

}
