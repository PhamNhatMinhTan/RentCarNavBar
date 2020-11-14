package edu.fu.rentcarnavbar.ui.Invoke;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InvokeViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public InvokeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }


    public LiveData<String> getText() {
        return mText;
    }
}