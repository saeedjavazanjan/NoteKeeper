package com.project.practical.notekeeper.ui.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.practical.notekeeper.R;

import dagger.android.support.DaggerAppCompatActivity;

public class NoteActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }
}