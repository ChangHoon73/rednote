package kr.co.yenglish.rednote.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import kr.co.yenglish.rednote.R;

public class LoginForm extends AppCompatActivity{
    EditText lf_edittxt_01_ID;
    EditText lf_edittxt_02_PW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

        lf_edittxt_01_ID = (EditText) this.findViewById(R.id.lf_edittxt_01_ID);
        lf_edittxt_02_PW = (EditText) this.findViewById(R.id.lf_edittxt_02_PW);

        Button lf_btn_01_login = (Button) this.findViewById(R.id.lf_btn_01_login);
        Button lf_btn_02_newaccount = (Button) this.findViewById(R.id.lf_btn_02_newaccount);
    }
}
