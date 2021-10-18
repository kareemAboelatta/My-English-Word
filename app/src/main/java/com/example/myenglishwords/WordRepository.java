package com.example.myenglishwords;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myenglishwords.database.WordDao;
import com.example.myenglishwords.database.WordDatabase;
import com.example.myenglishwords.model.ModelWord;

import java.util.ArrayList;
import java.util.List;

public class WordRepository {
    private WordDao wordDao ;
    private LiveData<List<ModelWord>> allWords;
    private LiveData<List<ModelWord>> someWords;
    private List<ModelWord> someWordsForGroup;
    private List<ModelWord> allWordsOrderByGN;


    public WordRepository(Application application) {
        WordDatabase database = WordDatabase.getInstance(application);
        wordDao = database.wordDao();
        allWords = wordDao.getAllWords();
        someWordsForGroup=new ArrayList<>();
        allWordsOrderByGN=new ArrayList<>();
    }

    public void insert(ModelWord word) {
        new InsertWordAsyncTask(wordDao).execute(word);
    }

    public void update(ModelWord word) {
        new UpdateWordsAsyncTask(wordDao).execute(word);
    }

    public void delete(ModelWord word) {
        new DeleteNoteAsyncTask(wordDao).execute(word);
    }

    public void deleteAllWords() {
        new DeleteAllWordsAsyncTask(wordDao).execute();
    }

    public LiveData<List<ModelWord>> getAllWords() {
        return allWords;
    }

    public LiveData<List<ModelWord>> getSomeWords(int gn) {
        someWords=wordDao.getSomeWords(gn);
        return someWords;
    }

    public List<ModelWord> getSomeWordsForGroup(int gn){
        someWordsForGroup=wordDao.getSomeWordsForGroup(gn);
        return someWordsForGroup;
    }

    public List<ModelWord> getAllWordsOrderByGN(){
        allWordsOrderByGN=wordDao.getAllWordsOrderByGN();
        return allWordsOrderByGN;
    }


    private static class InsertWordAsyncTask extends AsyncTask<ModelWord, Void, Void> {
        private WordDao wordDao;

        private InsertWordAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(ModelWord... words) {
            wordDao.insert(words[0]);
            return null;
        }
    }

    private static class UpdateWordsAsyncTask extends AsyncTask<ModelWord, Void, Void> {
        private WordDao wordDao;

        private UpdateWordsAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(ModelWord... words) {
            wordDao.update(words[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<ModelWord, Void, Void> {
        private WordDao wordDao;

        private DeleteNoteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(ModelWord... words) {
            wordDao.delete(words[0]);
            return null;
        }
    }

    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao wordDao;

        private DeleteAllWordsAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }

}
