package org.cfp.cilc.revealit.gui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cilc.cfp.org.gui.TextAnalysisFragment;
import cilc.cfp.org.revealit.R;

public class MainLauncher extends AppCompatActivity implements HomeFragment.OnHomeFragmentInteractionListener,ImageAnalysisFragment.OnImageFragmentInteractionListener,TextAnalysisFragment.OnTextFragmentInteractionListener {


    HomeFragment homeFragment = new HomeFragment();
    TextAnalysisFragment textAnalysisFragment = new TextAnalysisFragment();
    ImageAnalysisFragment imageAnalysisFragment = new ImageAnalysisFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Bundle args = new Bundle();
                    homeFragment.setArguments(args);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.fragment_container, homeFragment);
                    transaction.addToBackStack(null);

// Commit the transaction
                    transaction.commit();

                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);

                    Bundle args1 = new Bundle();
                    textAnalysisFragment.setArguments(args1);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.fragment_container, imageAnalysisFragment);
                    transaction.addToBackStack(null);

// Commit the transaction
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:

                    Bundle args2 = new Bundle();
                    imageAnalysisFragment.setArguments(args2);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.fragment_container, textAnalysisFragment);
                    transaction.addToBackStack(null);

// Commit the transaction
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_luncher);

       /* mTextMessage = (TextView) findViewById(R.id.message);
        mTextMessage.setText(R.string.app_desc);*/
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout


            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            homeFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, homeFragment).commit();
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onHomeFragmentInteraction(Uri uri) {

    }

    @Override
    public void onImageFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTextFragmentInteraction(Uri uri) {

    }
}
