package kr.co.yenglish.rednote.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kr.co.yenglish.rednote.BaseFragment;
import kr.co.yenglish.rednote.R;


public class MainFormFragment extends BaseFragment {

//    protected static volatile int instanceCount;

    public MainFormFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_form, container, false);
        Button mf_btn_01 = (Button) view.findViewById(R.id.mf_btn_01);

        mf_btn_01.setOnClickListener(mClick);

        return view;

    }


    View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finishFragment();
            switch (view.getId()){
                case R.id.mf_btn_01:
                    startFragment(getFragmentManager(), NoteFormFragment.class, "출석");
                    break;
            }
        }
    };

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        instanceCount++;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        instanceCount--;
//    }
}
