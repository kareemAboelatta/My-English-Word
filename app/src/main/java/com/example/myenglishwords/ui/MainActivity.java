package com.example.myenglishwords.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myenglishwords.AdapterWords;
import com.example.myenglishwords.model.ModelWord;
import com.example.myenglishwords.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddNote = findViewById(R.id.fab_add_words);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGetWordDialg();
            }
        });


        buttonAddNote.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
              //  startActivity(new Intent(MainActivity.this,GroupsActivity.class));


                return false;
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rec_words);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        final AdapterWords adapter = new AdapterWords(this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(WordActivityViewModel.class);
        viewModel.getAllWords().observe(this, new Observer<List<ModelWord>>() {
            @Override
            public void onChanged(List<ModelWord> modelWords) {

                adapter.setWords(modelWords);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getWordAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Word deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);



    }



    private void showGetWordDialg() {

        android.app.AlertDialog.Builder builder =new android.app.AlertDialog.Builder(this);
        builder.setTitle("New Word to your dictionary");
        LinearLayout linearLayout =new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(8,8,8,8);

        final EditText editText_word =new EditText(this);
        final EditText editText_translate =new EditText(this);
        editText_word.setHint("Enter The Word");
        editText_translate.setHint("ترجمتها");
        linearLayout.addView(editText_word);
        linearLayout.addView(editText_translate);
        builder.setView(linearLayout);

        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String word =editText_word.getText().toString().trim();
                final String translate =editText_translate.getText().toString().trim();
                if (!TextUtils.isEmpty(word)   &&  !TextUtils.isEmpty(translate)){

                    ModelWord modelWord=new ModelWord(word,translate,1);
                    viewModel.insert(modelWord);


                }else{
                    Toast.makeText(MainActivity.this, "please enter the word", Toast.LENGTH_SHORT).show();
                }

            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();


    }



}