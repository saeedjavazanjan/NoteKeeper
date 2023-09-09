package com.project.practical.notekeeper.di;

import static com.project.practical.notekeeper.persistence.NoteDatabase.DATABASE_NAME;

import android.app.Application;

import androidx.room.Room;

import com.project.practical.notekeeper.persistence.NoteDao;
import com.project.practical.notekeeper.persistence.NoteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application){
        return Room.databaseBuilder(
                application,
                NoteDatabase.class,
                DATABASE_NAME
        ).build();

    }

    @Singleton
    @Provides

    static NoteDao provideNoteDao(NoteDatabase noteDatabase){

        return noteDatabase.getNoteDao();
    }
}
