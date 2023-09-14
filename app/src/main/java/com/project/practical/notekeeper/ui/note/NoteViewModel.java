package com.project.practical.notekeeper.ui.note;

import static com.project.practical.notekeeper.repository.NoteRepository.NOTE_TITLE_NULL;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.practical.notekeeper.modulse.Note;
import com.project.practical.notekeeper.repository.NoteRepository;
import com.project.practical.notekeeper.ui.Resource;

import io.reactivex.Flowable;

public class NoteViewModel extends ViewModel {
    private static final String TAG= "NoteViewModel";
    private final NoteRepository noteRepository;

    private MutableLiveData<Note> note= new MutableLiveData<>();

    public NoteViewModel(NoteRepository noteRepository) {
        this.noteRepository=noteRepository;
    }

    public LiveData<Resource<Integer>> insertNote() throws Exception{
       return LiveDataReactiveStreams.fromPublisher(
               noteRepository.insertNote(note.getValue())
       );
    }

    public LiveData<Note> observeNote(){
        return note;
    }

    public void setNote (Note note) throws Exception{
        if (note.getTitle()==null || note.getTitle().equals("")) {
            throw new Exception(NOTE_TITLE_NULL);
        }
        this.note.setValue(note);
    }
}
