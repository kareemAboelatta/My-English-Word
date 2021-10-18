package com.example.myenglishwords.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myenglishwords.model.ModelWord;
import com.example.myenglishwords.WordRepository;

import java.util.List;

public class WordActivityViewModel extends AndroidViewModel {
    private WordRepository repository;
    private LiveData<List<ModelWord>> allWords;
    private LiveData<List<ModelWord>> someWords;


    public WordActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new WordRepository(application);
        allWords = repository.getAllWords();

    }

    public void insert(ModelWord word) {
        repository.insert(word);
    }

    public void update(ModelWord word) {
        repository.update(word);
    }

    public void delete(ModelWord word) {
        repository.delete(word);
    }

    public void deleteAllNotes() {
        repository.deleteAllWords();
    }

    public LiveData<List<ModelWord>> getAllWords() {
        return allWords;

    }

    public LiveData<List<ModelWord>> getSomeWords() {

        return allWords;

    }





}
