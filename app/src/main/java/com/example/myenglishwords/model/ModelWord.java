package com.example.myenglishwords.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class ModelWord {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private  String word ;
    private  String translateTheWord ;
    private int groupNumber ;


    public ModelWord(String word, String translateTheWord, int groupNumber) {
        this.word = word;
        this.translateTheWord = translateTheWord;
        this.groupNumber = groupNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslateTheWord() {
        return translateTheWord;
    }

    public void setTranslateTheWord(String translateTheWord) {
        this.translateTheWord = translateTheWord;
    }
}
