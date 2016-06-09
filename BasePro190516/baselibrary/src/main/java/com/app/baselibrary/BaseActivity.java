package com.app.baselibrary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public abstract class BaseActivity extends AppCompatActivity {

    public Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_fram);
        resources = getResources();
    }

    public void callFragment(Fragment fragment, int id) {
        this.callFragment(fragment,false,id,null);
    }

    public void callFragment(Fragment fragment, int id,Bundle bundle) {
        this.callFragment(fragment,false,id,bundle);
    }

    public void callFragment(Fragment fragment,boolean isBack,int id) {
        this.callFragment(fragment,isBack,id,null);
    }

    public void callFragment(Fragment fragment,boolean isBack,int id,Bundle bundle) {
        if(bundle != null) {
            fragment.setArguments(bundle);
        }
        FragmentTransaction fTransaction = getFragmentManager().beginTransaction();

        fTransaction.replace(id, fragment);
        if (isBack){fTransaction.addToBackStack(null);}
        fTransaction.commit();
    }

    public void addFragment(Fragment fragment,int id) {
        this.addFragment(fragment,id,null);
    }
    public void addFragment(Fragment fragment,int id,Bundle bundle) {
        if(bundle != null) {
            fragment.setArguments(bundle);
        }
        FragmentTransaction fTransaction = getFragmentManager().beginTransaction();
        fTransaction.add(id, fragment);
        fTransaction.commit();
    }

    public void callIntent(Class cls) {
        callIntent(cls,null);
    }

    public void callIntent(Class cls, Bundle bundle) {
        callIntent(cls,bundle,false,null);
    }

    public void callIntent(Class cls, boolean isFinish) {
        callIntent(cls,null,isFinish,null);
    }

    public void callIntent(Class cls, Bundle bundle, boolean isFinish) {
        callIntent(cls,bundle,isFinish,null);
    }
    public static void callIntent(Class cls, Bundle bundle, boolean isFinish, Activity activity) {
        Intent intent = new Intent(activity, cls);
        if(bundle != null)
        intent.putExtras(bundle);
        activity.startActivity(intent);
        if (isFinish)
            activity.finish();
    }

    public Drawable getDrawables(int drawable){
        return getDrawables(drawable,this);
    }

    public static Drawable getDrawables(int drawable,Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getResources().getDrawable(drawable, null);
        else
        return context.getResources().getDrawable(drawable);
    }

    public void setBackgrounds(boolean isSelected,View view,int draUnSelected,int draSelected){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(getDrawables((isSelected) ? draUnSelected : draSelected));
        }else {
            view.setBackgroundDrawable(getDrawables((isSelected) ? draUnSelected : draSelected));
        }
    }


    public String getStr(int id){
        return getStr(id,this);
    }

    public static String getStr(int id, Context context){
        return context.getResources().getString(id);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
