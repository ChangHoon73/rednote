package kr.co.yenglish.rednote.member;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.yenglish.rednote.BaseFragment;
import kr.co.yenglish.rednote.R;


public class MemberListFragment extends BaseFragment{

    public MemberListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.member_list, container, false);
    }
}
