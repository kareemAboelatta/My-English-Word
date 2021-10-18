package com.example.myenglishwords.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myenglishwords.model.ModelWord;


@Database(entities = {ModelWord.class}, version = 1)
public abstract class WordDatabase  extends RoomDatabase {


    private static WordDatabase instance;

    public abstract WordDao wordDao();

    public static synchronized WordDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WordDatabase.class, "word_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao wordDao;

        private PopulateDbAsyncTask(WordDatabase db) {
            wordDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.insert(new ModelWord("dungeons","الابراج المحصنة",2));
            wordDao.insert(new ModelWord("prosperous","مزدهر",1));
            wordDao.insert(new ModelWord("Stack","كومة",1));
            wordDao.insert(new ModelWord("Dealing of actions","تصريف الافعال",1));
            wordDao.insert(new ModelWord("Maintain","حافظ أو صان",1));
            wordDao.insert(new ModelWord("Acquire","يكتسب",2));


            return null;
        }
    }

}
