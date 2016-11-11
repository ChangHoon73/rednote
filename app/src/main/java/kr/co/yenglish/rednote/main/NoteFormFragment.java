package kr.co.yenglish.rednote.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import kr.co.yenglish.rednote.BaseFragment;
import kr.co.yenglish.rednote.R;

public class NoteFormFragment extends BaseFragment{

    protected static volatile int instanceCount;

    public NoteFormFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.note_form, container, false);
        EditText nf_edittxt_01 = (EditText) view.findViewById(R.id.nf_edittxt_01);
        int width = nf_edittxt_01.getWidth();
        nf_edittxt_01.setHeight(30);
        return view;


    }

}
