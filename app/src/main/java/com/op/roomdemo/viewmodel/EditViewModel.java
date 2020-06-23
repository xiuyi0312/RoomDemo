package com.op.roomdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditViewModel extends ViewModel {
    public final MutableLiveData<String> liveName = new MutableLiveData<>("");
    public final MutableLiveData<String> liveAge = new MutableLiveData<>("18");
}
