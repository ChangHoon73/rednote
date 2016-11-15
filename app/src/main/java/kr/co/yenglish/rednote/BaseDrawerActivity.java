package kr.co.yenglish.rednote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import kr.co.yenglish.rednote.main.MainFormActivity;
import kr.co.yenglish.rednote.main.NoteFormActivity;
import kr.co.yenglish.rednote.member.MemberListActivity;

public class BaseDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawer;
    public Toolbar toolbar;
    public FrameLayout frameLayout;
    public NavigationView navigationView;
    public FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = (FrameLayout) findViewById(R.id.content_frame);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent = new Intent(BaseDrawerActivity.this, Main2Activity.class);
//                startActivity(intent);
            }
        });

        fab.setVisibility(View.GONE);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        View header=navigationView.getHeaderView(0);
//        TextView user_txt = (TextView) header.findViewById(R.id.user_txt);
//        String name = Utils.getStringPreference(this, "name").toString();
//        Utils.log("name:"+name);
//        user_txt.setText(name + " LogOut ");
//        //View layoutid = this.findViewById(R.id.includeid);
//        user_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BaseDrawerActivity.this.finish();
//            }
//        });

        //navigationView.getMenu().getItem(0).setChecked(true);
        //onNavigationItemSelected(navigationView.getMenu().getItem(0));
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(item.isChecked()){
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }

        String title = getString(R.string.app_name);
        if (id == R.id.nav_home) {
            startActivity(new Intent(getApplicationContext(), MainFormActivity.class));
            title = "홈메뉴";
        } else if (id == R.id.nav_note) {
            startActivity(new Intent(getApplicationContext(), NoteFormActivity.class));
            title = "출석";
        } else if (id == R.id.nav_member) {
            startActivity(new Intent(getApplicationContext(), MemberListActivity.class));
            title = "회원관리";
        } else if (id == R.id.nav_class) {
            title = "과목관리";

        } else if (id == R.id.nav_fee) {
            title = "수강료관리";

        } else if (id == R.id.nav_signup) {
            title = "수강관리";

        } else if (id == R.id.nav_signup) {
            title = "메세지관리";

        } else if (id == R.id.nav_student) {
            title = "출석부관리";

        }

//        if( fragment != null){
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.content_fragment_layout, fragment);
//            ft.commit();
//        }

        if( getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
