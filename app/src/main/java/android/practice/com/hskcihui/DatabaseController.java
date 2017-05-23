package android.practice.com.hskcihui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


class DatabaseController {

    private SQLiteDatabase db;
    private CreateDatabase database;

    DatabaseController(Context context){
        database = new CreateDatabase(context);
    }

    Cursor getWordsByHskLevel(int hskLevel){
        Cursor cursor;
        db = database.getReadableDatabase();
        String[] columns = {database.ID,  database.SIMPLIFIED, database.TRADITIONAL,
            database.PINYIN,database.ENGLISH,database.TYPE,database.LEVEL,database.INFO};
        String selection = database.HSK+" =?";
        String selectionArgs[] = {Integer.toString(hskLevel)};

        cursor = db.query(database.TABLE, columns, selection, selectionArgs, null, null, database.ID);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor getWordsByLevel(int level){
        return null;
    }
}
