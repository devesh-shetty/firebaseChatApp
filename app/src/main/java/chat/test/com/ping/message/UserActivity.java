package chat.test.com.ping.message;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import chat.test.com.ping.R;
import chat.test.com.ping.message.fragment.FriendListFragment;
import chat.test.com.ping.message.fragment.FriendListItem;
import chat.test.com.ping.message.fragment.OnListFragmentInteractionListener;
import chat.test.com.ping.model.PingUser;
import chat.test.com.ping.util.Constants;
import chat.test.com.ping.util.PreferencesStorage;

/**
 * This activity will contain fragments related to list of users and chats
 * @author Devesh Shetty
 */
public class UserActivity extends AppCompatActivity implements OnListFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ArrayList<FriendListItem> friendListItems;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mContext = UserActivity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        friendListItems  = new ArrayList<>();

        String userId = PreferencesStorage.getValueForKey(mContext, PreferencesStorage.USER_ID);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference pingUsersReference = firebaseDatabase.getReference(Constants.PING_USERS_REF);
        ValueEventListener usersListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                for(DataSnapshot userSnapshot : dataSnapshot.getChildren())
                {
                    PingUser pingUser = userSnapshot.getValue(PingUser.class);
                    if(userId != pingUser.getUserId())
                    {
                        FriendListItem item = new FriendListItem(pingUser.getUsername(), pingUser.getUserId());
                        friendListItems.add(item);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        };

        pingUsersReference.addValueEventListener(usersListener);

    }

    @Override
    public void onListFragmentInteraction(FriendListItem item) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position)
            {
                case 0:
                case 1:
                    fragment = FriendListFragment.newInstance(friendListItems);
                    break;

            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return "Messages";
                case 1:
                    return "Users";
            }
            return null;
        }
    }
}
