package com.project.practical.notekeeper.di;

import com.project.practical.notekeeper.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract NotesListActivity contributeNoteListActivity();

}
