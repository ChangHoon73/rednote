package kr.co.yenglish.rednote.msg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.yenglish.rednote.BaseFragment;
import kr.co.yenglish.rednote.R;


public class MsgListFragment extends BaseFragment{

    public MsgListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.msg_list, container, false);
    }
}
