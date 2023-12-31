package com.project.practical.notekeeper.di;

import androidx.lifecycle.ViewModelProvider;

import com.project.practical.notekeeper.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
