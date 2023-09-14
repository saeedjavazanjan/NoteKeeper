package com.project.practical.notekeeper.repository;


import static com.project.practical.notekeeper.repository.NoteRepository.INSERT_FAILURE;
import static com.project.practical.notekeeper.repository.NoteRepository.INSERT_SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.project.practical.notekeeper.modulse.Note;
import com.project.practical.notekeeper.persistence.NoteDao;
import com.project.practical.notekeeper.ui.Resource;
import com.project.practical.notekeeper.util.TestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import io.reactivex.Single;
import kotlin.Unit;

public class NoteRepositoryTest {

    private final Note note1 = new Note(TestUtil.TEST_NOTE_1);

    //system under test
    private NoteRepository noteRepository;
    private NoteDao noteDao;


    @BeforeEach
    public void initEach(){
        noteDao= mock(NoteDao.class);
        noteRepository=new NoteRepository(noteDao);
    }

    @Test
    public void dummTest() throws Exception {

     assertNotNull(noteDao);
      assertNotNull(noteRepository);

    }


    @Test
    void insertNote_returnRow() throws Exception {
        final Long insertedRow = 1L;
        final Single<Long> returnerData = Single.just(insertedRow);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnerData);

        final Resource<Integer> returnedValue=noteRepository.insertNote(note1).blockingFirst();

        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        System.out.println("Returned value: " +returnedValue.data);
        assertEquals(Resource.success(1,INSERT_SUCCESS),returnedValue);

        //or test using RxJava
      /*  noteRepository.insertNote(note1)
                .test()
                .await()
                .assertValue(Resource.success(1,INSERT_SUCCESS));*/
    }

    @Test
    void insertNote_returnFailure() throws Exception {
        final Long failureInsert = -1L;
        final Single<Long> returnerData = Single.just(failureInsert);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnerData);

        final Resource<Integer> returnedValue=noteRepository.insertNote(note1).blockingFirst();

        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        System.out.println("Returned value: " +returnedValue.data);
        assertEquals(Resource.error(null,INSERT_FAILURE),returnedValue);
    }

    @Test
    void insertNote_nullTitle_throwExeption() throws Exception {
      assertThrows(Exception.class , new Executable() {

          @Override
          public void execute() throws Throwable {
              final Note note=new Note(TestUtil.TEST_NOTE_1);
              note.setTitle(null);
              noteRepository.insertNote(note);
          }
      } );

    }

   /* assertThrows(Exception.class , new Executable(){
        @Override
        public void execute() throws Throwable {
            final Note note=new Note(TestUtil.TEST_NOTE_1);
            note.setTitle(null);
            noteRepository.insertNote(note);
        }
    });*/
}
