package com.app.baselibrary.base;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public abstract class BaseFragment extends Fragment {

    public BaseActivity mActivity;
    public Resources resources;
    int _layout = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity		=	(BaseActivity) this.getActivity();
        resources = getResources();
    }

    public void hideSoftKeyboard(){
        try {
            InputMethodManager imm = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            // imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showSoftKeyboard(){
        try {
            InputMethodManager imm = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
           // imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            imm.showSoftInput(getView(), InputMethodManager.SHOW_IMPLICIT);
            //imm.showSoftInputFromInputMethod(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
            Log.e("show","soft");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
