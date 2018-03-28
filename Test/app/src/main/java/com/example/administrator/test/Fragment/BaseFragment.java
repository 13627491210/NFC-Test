package com.example.administrator.test.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.test.Activity.MainActivity;
import com.example.administrator.test.util.AppClass;


/**
 * Created by hantao on 2017/9/3.
 */

public class BaseFragment extends Fragment {
    public MainActivity parent;
    public  int verCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
          parent=(MainActivity)this.getActivity();

          verCode = AppClass.getVersionCode(parent);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public BaseFragment() {

    }

    public  void CardIn(String cardno){

    }

}
