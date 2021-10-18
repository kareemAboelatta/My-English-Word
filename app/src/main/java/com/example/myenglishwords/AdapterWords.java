package com.example.myenglishwords;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myenglishwords.model.ModelWord;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterWords extends RecyclerView.Adapter<AdapterWords.MyHolder> {

    private List<ModelWord> words = new ArrayList<>();

    Context  context;


    TextToSpeech t1;


    public AdapterWords(Context context) {
        this.context = context;


        t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status !=TextToSpeech.ERROR){
                    t1.setLanguage(Locale.ENGLISH);
                }

            }
        });

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_word, parent, false);
        return new MyHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


        final ModelWord currentWord=words.get(position);

        holder.word.setText(currentWord.getWord());
        holder.wordt.setText(currentWord.getTranslateTheWord());

        holder.readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.speak(currentWord.getWord(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return words.size();    }


    public void setWords(List<ModelWord> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    public ModelWord getWordAt(int position) {
        return words.get(position);
    }



    public class MyHolder extends RecyclerView.ViewHolder{

        TextView word , wordt;
        ImageButton readBtn;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            word=itemView.findViewById(R.id.textview_word);
            wordt=itemView.findViewById(R.id.textview_translate);
            readBtn=itemView.findViewById(R.id.readBtn);

        }
    }
}
