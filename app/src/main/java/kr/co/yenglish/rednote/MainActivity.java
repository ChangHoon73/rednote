package kr.co.yenglish.rednote;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import kr.co.yenglish.rednote.classes.ClassListFragment;
import kr.co.yenglish.rednote.fee.FeeListFragment;
import kr.co.yenglish.rednote.main.MainFormFragment;
import kr.co.yenglish.rednote.main.NoteFormFragment;
import kr.co.yenglish.rednote.member.MemberListFragment;
import kr.co.yenglish.rednote.msg.MsgListFragment;
import kr.co.yenglish.rednote.note.NoteListFragment;
import kr.co.yenglish.rednote.signup.SignupListFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(intent);
            }
        });

        fab.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //View layoutid = this.findViewById(R.id.includeid);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment =  null;
        String title = getString(R.string.app_name);
        if (id == R.id.nav_home) {
            // Handle the camera action
            fragment = new MainFormFragment();
            title = "홈메뉴";
        } else if (id == R.id.nav_note) {
            fragment = new NoteFormFragment();
            title = "출석";

        } else if (id == R.id.nav_member) {
            fragment = new MemberListFragment();
            title = "회원관리";

        } else if (id == R.id.nav_class) {
            fragment = new ClassListFragment();
            title = "과목관리";

        } else if (id == R.id.nav_fee) {
            fragment = new FeeListFragment();
            title = "수강료관리";

        } else if (id == R.id.nav_signup) {
            fragment = new SignupListFragment();
            title = "수강관리";

        } else if (id == R.id.nav_signup) {
            fragment = new MsgListFragment();
            title = "메세지관리";

        } else if (id == R.id.nav_student) {
            fragment = new NoteListFragment();
            title = "출석부관리";

        }

        if( fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_layout, fragment);
            ft.commit();
        }

        if( getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
