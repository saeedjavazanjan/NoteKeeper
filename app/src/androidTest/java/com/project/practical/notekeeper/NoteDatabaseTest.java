package com.project.practical.notekeeper;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.project.practical.notekeeper.persistence.NoteDao;
import com.project.practical.notekeeper.persistence.NoteDatabase;

import org.junit.After;
import org.junit.Before;

public abstract class NoteDatabaseTest {
    private NoteDatabase noteDatabase;

    public NoteDao getNoteDao(){
        return noteDatabase.getNoteDao();
    }
    @Before
    public void init(){
        noteDatabase= Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                NoteDatabase.class
        ).build();
    }

    @After
    public void finish(){
        noteDatabase.close();
    }
}
