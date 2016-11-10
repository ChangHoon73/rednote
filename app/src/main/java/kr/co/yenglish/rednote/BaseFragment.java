package kr.co.yenglish.rednote;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hoon on 2016-11-10.
 */

public class BaseFragment extends Fragment{

    protected void startFragment(FragmentManager fm, Class<? extends BaseFragment> fragmentClass, String title){
        BaseFragment fragment = null;
        try{
            fragment = fragmentClass.newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }

        if( fragment == null){
            throw new IllegalStateException("cannot start fragment"+ fragmentClass.getName());
        }
        //getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_fragment_layout, fragment).addToBackStack(null).commit();
        getFragmentManager().beginTransaction().replace(R.id.content_fragment_layout, fragment).addToBackStack(null).commit();
        if( ((AppCompatActivity)getActivity()).getSupportActionBar() != null)
        {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        }
    }

    protected void finishFragment(){
//        getFragmentManager().popBackStack();
        getFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void onPressBackkey(){
        finishFragment();
    }


}
