package com.project.practical.notekeeper.ui.note_list;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.project.practical.notekeeper.R;
import com.project.practical.notekeeper.repository.NoteRepository;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class NotesListActivity extends DaggerAppCompatActivity{

    private static final String TAG="NoteListActivity";
    @Inject
    NoteRepository noteRepository;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        Log.d(TAG,"oncreate: " + noteRepository);

    }
}
