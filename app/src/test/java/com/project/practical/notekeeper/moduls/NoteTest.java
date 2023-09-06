package com.project.practical.notekeeper.moduls;

import static java.lang.Character.getName;

import com.project.practical.notekeeper.modulse.Note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoteTest {

    String TIMESTAMP_1="08-2023";
    String TIMESTAMP_2="07-2023";

    @Test
    void isNotesEqual_identicalProperties_returnTrue() throws Exception {
        Note note1= new Note("Note #1","content of Note #1",TIMESTAMP_1);
        note1.setId(1);
        Note note2= new Note("Note #1","content of Note #1",TIMESTAMP_1);
        note2.setId(1);

        Assertions.assertEquals(note1,note2);

        System.out.println("notes are equal !");

    }

    @Test
    void isNotesEqual_differentIds_returnFalse() throws Exception {
        Note note1= new Note("Note #1","content of Note #1",TIMESTAMP_1);
        note1.setId(1);
        Note note2= new Note("Note #1","content of Note #1",TIMESTAMP_1);
        note2.setId(2);

        Assertions.assertNotEquals(note1,note2);

        System.out.println("notes are not equal !");

    }
    @Test
    void isNotesEqual_differentTimestamp_returnFalse() throws Exception {
        Note note1= new Note("Note #1","content of Note #1",TIMESTAMP_1);
        note1.setId(1);
        Note note2= new Note("Note #1","content of Note #1",TIMESTAMP_2);
        note2.setId(1);

        Assertions.assertEquals(note1,note2);

        System.out.println("notes are  equal !");

    }
    @Test
    void isNotesEqual_differentTitle_returnFalse() throws Exception {
        Note note1= new Note("Note #1","content of Note #1",TIMESTAMP_1);
        note1.setId(1);
        Note note2= new Note("Note #2","content of Note #1",TIMESTAMP_1);
        note2.setId(1);

        Assertions.assertNotEquals(note1,note2);

        System.out.println("notes are not equal !");

    }
    @Test
    void isNotesEqual_differentContent_returnFalse() throws Exception {
        Note note1= new Note("Note #1","content of Note #1",TIMESTAMP_1);
        note1.setId(1);
        Note note2= new Note("Note #1","content of Note #2",TIMESTAMP_1);
        note2.setId(1);

        Assertions.assertNotEquals(note1,note2);

        System.out.println("notes are not equal !");

    }
}
