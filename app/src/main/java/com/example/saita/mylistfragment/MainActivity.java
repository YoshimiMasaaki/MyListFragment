package com.example.saita.mylistfragment;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.saita.mylistfragment.dummy.DummyContent.DummyItem;

public class MainActivity extends AppCompatActivity
        implements ItemFragment.OnListFragmentInteractionListener {

    private int nItemNo = -1;

    private ItemFragment itemFragment;
    private FragmentTransaction fragmentTrans;

    private TextView mTextMessage;
    private TextInputEditText mEditDetail;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);

                    itemFragment = ItemFragment.newInstance(1);
                    fragmentTrans = getSupportFragmentManager().beginTransaction();
                    fragmentTrans.replace(R.id.frame, itemFragment);
                    fragmentTrans.commit();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);

                    fragmentTrans = getSupportFragmentManager().beginTransaction();
                    fragmentTrans.remove(itemFragment);
                    fragmentTrans.commit();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);

                    fragmentTrans = getSupportFragmentManager().beginTransaction();
                    fragmentTrans.remove(itemFragment);
                    fragmentTrans.commit();
                    return true;
            }
            return false;
        }
    };

    private Button.OnClickListener mOnChangeDetailListener =
            new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            if ( nItemNo != -1 ) {
                itemFragment.OnListFragmentItemChanged(nItemNo, mEditDetail.getText().toString());

                itemFragment = ItemFragment.newInstance(1);
                fragmentTrans = getSupportFragmentManager().beginTransaction();
                fragmentTrans.replace(R.id.frame, itemFragment);
                fragmentTrans.commit();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemFragment = ItemFragment.newInstance(1);

        fragmentTrans = getSupportFragmentManager().beginTransaction();
        fragmentTrans.replace(R.id.frame, itemFragment);
        fragmentTrans.commit();

        mTextMessage = findViewById(R.id.message);
        mEditDetail = findViewById(R.id.detail);
        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(mOnChangeDetailListener);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onListFragmentInteraction(DummyItem item) {
        nItemNo = Integer.parseInt(item.id);
        mEditDetail.setText(item.details);
    }
}
