package sg.edu.nus.clubmanagement.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import sg.edu.nus.clubmanagement.R;
import sg.edu.nus.clubmanagement.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
    PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 3);
    viewPager.setAdapter(pagerAdapter);
    final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    tabLayout.addTab(tabLayout.newTab().setText("Medicine"));
    tabLayout.addTab(tabLayout.newTab().setText("Medicine Category"));
    tabLayout.addTab(tabLayout.newTab().setText("Bookings"));
    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });

    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
  }

  @Override
  public void onBackPressed() {
    //super.onBackPressed();

    new AlertDialog.Builder(this)
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                finish();
              }
            })
            .setNegativeButton("No", null)
            .show();
  }

  @Override protected void onStop() {
    //App.clubStore.saveAll(this);
    super.onStop();
  }
}
