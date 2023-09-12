package com.project.practical.notekeeper.repository;

import com.project.practical.notekeeper.persistence.NoteDao;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NoteRepository {
    @NotNull
    private final NoteDao noteDao;

    @Inject
    public NoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }
}
