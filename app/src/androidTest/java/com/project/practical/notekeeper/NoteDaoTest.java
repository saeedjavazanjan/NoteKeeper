package com.project.practical.notekeeper;

import static org.junit.Assert.*;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteConstraintException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.project.practical.notekeeper.modulse.Note;
import com.project.practical.notekeeper.util.LiveDataTestUtil;
import com.project.practical.notekeeper.util.TestUtil;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class NoteDaoTest extends NoteDatabaseTest{

    private static final String TEST_TITLE="this is a test title";
    private static final String TEST_CONTENT="this is a test content";
    private static final String TEST_TIMESTAMP="09-2023";

    @Rule
    public InstantTaskExecutorRule rule=new InstantTaskExecutorRule();


    @Test
    public void insertReadDeleteData() throws Exception{
        Note note=new Note(TestUtil.TEST_NOTE_1);

        //insert
        getNoteDao().insertNote(note).blockingGet();

        //read
        LiveDataTestUtil<List<Note>> liveDataTestUtil=new LiveDataTestUtil<>();
        List<Note> insertedNotes=liveDataTestUtil.getValue(getNoteDao().getNotes());

        assertNotNull(insertedNotes);

        assertEquals(note.getContent(),insertedNotes.get(0).getContent());
        assertEquals(note.getTimestamp(),insertedNotes.get(0).getTimestamp());
        assertEquals(note.getTitle(),insertedNotes.get(0).getTitle());

        note.setId(insertedNotes.get(0).getId());

        assertEquals(note,insertedNotes.get(0));

        //delete
        getNoteDao().deleteNote(note).blockingGet();

        //confirm database is empty

        assertEquals(0,insertedNotes.size());

    }

    @Test
    public void insertReadUpdateReadDeleteData() throws Exception{
        Note note=new Note(TestUtil.TEST_NOTE_1);

        //insert
        getNoteDao().insertNote(note).blockingGet();

        //read
        LiveDataTestUtil<List<Note>> liveDataTestUtil=new LiveDataTestUtil<>();
        List<Note> insertedNotes=liveDataTestUtil.getValue(getNoteDao().getNotes());

        assertNotNull(insertedNotes);

        assertEquals(note.getContent(),insertedNotes.get(0).getContent());
        assertEquals(note.getTimestamp(),insertedNotes.get(0).getTimestamp());
        assertEquals(note.getTitle(),insertedNotes.get(0).getTitle());

        note.setId(insertedNotes.get(0).getId());

        assertEquals(note,insertedNotes.get(0));

        //update
        note.setTitle(TEST_TITLE);
        note.setContent(TEST_CONTENT);
        note.setTimestamp(TEST_TIMESTAMP);

        //read
        insertedNotes=liveDataTestUtil.getValue(getNoteDao().getNotes());
        note.setId(insertedNotes.get(0).getId());
        assertEquals(note,insertedNotes.get(0));

        //delete
        getNoteDao().deleteNote(note).blockingGet();

        //confirm database is empty

        assertEquals(0,insertedNotes.size());

    }

    @Test(expected = SQLiteConstraintException.class)

    public void insertNullTitle_throwSQLiteConstraintException() throws Exception{

        final Note note=new Note(TestUtil.TEST_NOTE_1);
        note.setTitle(null);
        //insert
        getNoteDao().insertNote(note).blockingGet();


    }

    @Test(expected = SQLiteConstraintException.class)

    public void updateNullTitle_throwSQLiteConstraintException() throws Exception{

       Note note=new Note(TestUtil.TEST_NOTE_1);
        note.setTitle(null);
        //insert
        getNoteDao().insertNote(note).blockingGet();

        //read
        LiveDataTestUtil<List<Note>> liveDataTestUtil=new LiveDataTestUtil<>();
        List<Note> insertedNotes=liveDataTestUtil.getValue(getNoteDao().getNotes());

        //update
        note=new Note(insertedNotes.get(0));
        note.setTitle(null);
        getNoteDao().updateNote(note).blockingGet();


    }

}
