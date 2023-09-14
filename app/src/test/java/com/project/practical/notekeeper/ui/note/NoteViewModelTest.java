package com.project.practical.notekeeper.ui.note;

import static com.project.practical.notekeeper.repository.NoteRepository.INSERT_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.practical.notekeeper.modulse.Note;
import com.project.practical.notekeeper.repository.NoteRepository;
import com.project.practical.notekeeper.ui.Resource;
import com.project.practical.notekeeper.util.InstantExecutorExtension;
import com.project.practical.notekeeper.util.LiveDataTestUtil;
import com.project.practical.notekeeper.util.TestUtil;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.internal.operators.single.SingleToFlowable;

@ExtendWith(InstantExecutorExtension.class)
public class NoteViewModelTest {

    //system under test
    private NoteViewModel noteViewModel;

    @Mock
    private NoteRepository noteRepository;


    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        noteViewModel=new NoteViewModel(noteRepository);
    }


    @Test
    void observeEmptyNoteWhenNoteSet() throws Exception {
        LiveDataTestUtil<Note> liveDataTestUtil =new LiveDataTestUtil<>();
        Note note=liveDataTestUtil.getValue(noteViewModel.observeNote());
        assertNull(note);
        
    }

    @Test
    void observeNote_WhenNoteSet() throws Exception {
        Note note=new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Note> liveDataTestUtil =new LiveDataTestUtil<>();
        noteViewModel.setNote(note);
        Note observedNote=liveDataTestUtil.getValue(noteViewModel.observeNote());
        assertEquals(note,observedNote);
    }

    @Test
    void insertNote_ReturnRow() throws Exception {
        Note note=new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil=new LiveDataTestUtil<>();
        final int insertedRow =1;
        Flowable<Resource<Integer>> returnedData= SingleToFlowable.just(Resource.success(insertedRow,INSERT_SUCCESS));
        when(noteRepository.insertNote(any(Note.class))).thenReturn(returnedData);

        noteViewModel.setNote(note);
/*
        Resource<Integer> returnedValue=liveDataTestUtil.getValue(noteViewModel.insertNote());
*/

        Resource<Integer> returnedValue=liveDataTestUtil.getValue(noteViewModel.insertNote());
        assertEquals(Resource.success(insertedRow,INSERT_SUCCESS),returnedValue);
    }

    @Test
    void dontReturnInsertRowWithoutObserver() throws Exception {

        Note note=new Note(TestUtil.TEST_NOTE_1);

        noteViewModel.setNote(note);

        verify(noteRepository,never()).insertNote(any(Note.class));
    }

    @Test
    void setNote_NullTitle_ThrowException() throws Exception {

        Note note=new Note(TestUtil.TEST_NOTE_1);
        note.setTitle(null);

        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                noteViewModel.setNote(note);
            }
        });
    }
}
