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

    public int[][] getStats(){
        int[][] stats = new int[5][6];
        SQLiteDatabase db;
        db = database.getReadableDatabase();

        String[] columns = {CreateDatabase.HSK,CreateDatabase.LEVEL};

        Cursor cursor = db.query(database.TABLE, columns, null, null, null, null, null);

        if (cursor.getCount()>0){
            while (cursor.moveToNext()) {
                if (cursor.getString(cursor.getColumnIndex(CreateDatabase.HSK)).equals("1")){
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("0")){
                        stats[0][0]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("1")){
                        stats[1][0]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("2")){
                        stats[2][0]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("3")){
                        stats[3][0]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("4")){
                        stats[4][0]++;
                    }
                }
                if (cursor.getString(cursor.getColumnIndex(CreateDatabase.HSK)).equals("2")){
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("0")){
                        stats[0][1]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("1")){
                        stats[1][1]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("2")){
                        stats[2][1]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("3")){
                        stats[3][1]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("4")){
                        stats[4][1]++;
                    }

                }
                if (cursor.getString(cursor.getColumnIndex(CreateDatabase.HSK)).equals("3")){
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("0")){
                        stats[0][2]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("1")){
                        stats[1][2]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("2")){
                        stats[2][2]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("3")){
                        stats[3][2]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("4")){
                        stats[4][2]++;
                    }
                }
                if (cursor.getString(cursor.getColumnIndex(CreateDatabase.HSK)).equals("4")){
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("0")){
                        stats[0][3]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("1")){
                        stats[1][3]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("2")){
                        stats[2][3]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("3")){
                        stats[3][3]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("4")){
                        stats[4][3]++;
                    }
                }
                if (cursor.getString(cursor.getColumnIndex(CreateDatabase.HSK)).equals("5")){
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("0")){
                        stats[0][4]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("1")){
                        stats[1][4]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("2")){
                        stats[2][4]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("3")){
                        stats[3][4]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("4")){
                        stats[4][4]++;
                    }
                }
                if (cursor.getString(cursor.getColumnIndex(CreateDatabase.HSK)).equals("6")){
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("0")){
                        stats[0][5]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("1")){
                        stats[1][5]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("2")){
                        stats[2][5]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("3")){
                        stats[3][5]++;
                    }
                    if (cursor.getString(cursor.getColumnIndex(CreateDatabase.LEVEL)).equals("4")){
                        stats[4][5]++;
                    }
                }
            }
        }
        db.close();
        cursor.close();
        return stats;
    }

    public void setEnglishValueById(String newValue, String id){
        SQLiteDatabase db;
        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CreateDatabase.ENGLISH, newValue);

        String selection = CreateDatabase.ID + " LIKE ?";
        String[] selectionArgs = {id};

        db.update(database.TABLE,values, selection, selectionArgs);
        db.close();
    }
}
