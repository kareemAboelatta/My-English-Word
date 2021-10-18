package com.example.myenglishwords.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myenglishwords.model.ModelWord;

import java.util.List;


@Dao
public interface WordDao {

    @Insert
    void insert(ModelWord word);

    @Update
    void update(ModelWord word);

    @Delete
    void delete(ModelWord word);

    @Query("DELETE FROM word_table")
    void deleteAllWords();

    @Query("SELECT * FROM word_table ORDER BY id DESC")
    LiveData<List<ModelWord>> getAllWords();

    @Query("SELECT * FROM word_table ORDER BY groupNumber ASC")
    List<ModelWord> getAllWordsOrderByGN();

    @Query("SELECT * FROM word_table WHERE groupNumber=:gn ORDER BY id DESC")
    LiveData<List<ModelWord>> getSomeWords(int gn);


    @Query("SELECT * FROM word_table WHERE groupNumber=:gn ORDER BY id DESC")
    List<ModelWord> getSomeWordsForGroup(int gn);



}
