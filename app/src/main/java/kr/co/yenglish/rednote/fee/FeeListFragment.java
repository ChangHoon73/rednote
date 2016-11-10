package kr.co.yenglish.rednote.fee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.yenglish.rednote.BaseFragment;
import kr.co.yenglish.rednote.R;


public class FeeListFragment extends BaseFragment {

    public FeeListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fee_list, container, false);
    }
}
