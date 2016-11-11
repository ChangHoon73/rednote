package kr.co.yenglish.rednote.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kr.co.yenglish.rednote.MainActivity;
import kr.co.yenglish.rednote.R;
import kr.co.yenglish.rednote.util.CommunicationUtils;
import kr.co.yenglish.rednote.util.Utils;

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

        lf_btn_01_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundAsyncTask(LoginForm.this).execute(lf_edittxt_01_ID.getText().toString(), lf_edittxt_02_PW.getText().toString() );
            }
        });
    }

    public class BackgroundAsyncTask extends AsyncTask<String, Integer, Boolean>{
        private Context ctx;
        private ProgressDialog ld;

        public BackgroundAsyncTask(Context ctx){
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            ld = new ProgressDialog(ctx);
            ld.setTitle("Login");
            ld.setMessage("Wait a Minute..");
            ld.setCancelable(false);
            ld.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            boolean isSuccess = false;
            try{
                String uid = params[0];
                String upw = params[1];

                CommunicationUtils com = new CommunicationUtils(ctx);
                isSuccess = com.loginService(uid, upw);


            }catch(Exception e){
                Utils.exceptionLog(e);
                isSuccess = false;
            }
            return isSuccess;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            try{
                if(result){
                    Utils.showLongToast(ctx, "로그인 성공");
                    Intent intent = new Intent(LoginForm.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Utils.showLongToast(ctx, "로그인 실패");
                }
            }catch(Exception e){
                Utils.exceptionLog(e);
            }
            ld.dismiss();
        }
    }
}
