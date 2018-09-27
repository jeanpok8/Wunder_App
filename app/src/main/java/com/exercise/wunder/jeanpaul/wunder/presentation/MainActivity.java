package com.exercise.wunder.jeanpaul.wunder.presentation;

import android.arch.persistence.room.Room;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.exercise.wunder.jeanpaul.wunder.R;
import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;
import com.exercise.wunder.jeanpaul.wunder.presentation.adapter.CarsInfoAdapter;
import com.exercise.wunder.jeanpaul.wunder.presentation.adapter.ICarsInfoAdapter;
import com.exercise.wunder.jeanpaul.wunder.presentation.base.BaseActivity;
import com.exercise.wunder.jeanpaul.wunder.presentation.base.BaseFragment;
import com.exercise.wunder.jeanpaul.wunder.presentation.base.MvpView;
import com.exercise.wunder.jeanpaul.wunder.presenter.CarInfoPresenterImpl;
import com.exercise.wunder.jeanpaul.wunder.presenter.base.BasePresenter;
import com.exercise.wunder.jeanpaul.wunder.presenter.base.MvpPresenter;
import com.exercise.wunder.jeanpaul.wunder.repository.local.CarsLocalRepo;
import com.exercise.wunder.jeanpaul.wunder.repository.local.CarsLocalRepoImpl;
import com.exercise.wunder.jeanpaul.wunder.repository.local.DBConstant;
import com.exercise.wunder.jeanpaul.wunder.repository.local.WunderDatabase;
import com.exercise.wunder.jeanpaul.wunder.repository.main.CarsInfoRepo;
import com.exercise.wunder.jeanpaul.wunder.repository.main.CarsInfoRepoImpl;
import com.exercise.wunder.jeanpaul.wunder.repository.remote.CarsRemoteRepoImpl;
import com.exercise.wunder.jeanpaul.wunder.repository.remote.ICarsRemoteRepo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity<CarInfoPresenter> extends BaseActivity implements CarInfoView, MapFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public CarsLocalRepo localRepo;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected CarInfoPresenterImpl createPresenter() {

        ICarsRemoteRepo remoteRepo = new CarsRemoteRepoImpl();
        WunderDatabase localDB = Room.databaseBuilder(getApplicationContext(), WunderDatabase.class, DBConstant.DB_NAME).build();
        localRepo = new CarsLocalRepoImpl(localDB.carInfoDao());
        CarsInfoRepo carsInfoRepo = new CarsInfoRepoImpl(remoteRepo, localRepo);

        return new CarInfoPresenterImpl(AndroidSchedulers.mainThread(), carsInfoRepo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        getCarsInfo();
    }

    public void getCarsInfo() {
        ((CarInfoPresenterImpl) getPresenter()).getCarsInfo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCarsInfo(List<CarInfo> carsInfo) {
        hideLoading();
        mSectionsPagerAdapter.setViewItems(carsInfo);
    }

    @Override
    public void showLoading() {
//TODO:we need to show a progress bar here
    }

    @Override
    public void hideLoading() {
//TODO: we need to hid progress bar
    }

    @Override
    public void showError(String error) {
        hideLoading();
        // TODO show error dialog[p0
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends BaseFragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        protected void setItemsInAdapter(List<CarInfo> carsInfo) {

        }
    }

    public static class ListOfCarsInfoFragment extends BaseFragment {

        private ICarsInfoAdapter adapter;
        private RecyclerView mRecyclerView;
        private RecyclerView.LayoutManager mLayoutManager;

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public ListOfCarsInfoFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ListOfCarsInfoFragment newInstance(int sectionNumber) {
            ListOfCarsInfoFragment fragment = new ListOfCarsInfoFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.cars_info_list_fragment, container, false);

            adapter = new CarsInfoAdapter(this.getContext());

            mRecyclerView = rootView.findViewById(R.id.recyclerView);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(this.getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                    DividerItemDecoration.HORIZONTAL);
            mRecyclerView.addItemDecoration(dividerItemDecoration);

            // specify an adapter
            mRecyclerView.setAdapter((RecyclerView.Adapter) adapter);
            return rootView;
        }

        @Override
        protected void setItemsInAdapter(List<CarInfo> carsInfo) {
            adapter.setItemInAdapter(carsInfo);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        BaseFragment[] fragments = new BaseFragment[2];

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return position == 0 ? ListOfCarsInfoFragment.newInstance(position) : MapFragment.newInstance();
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            BaseFragment fragment = (BaseFragment) super.instantiateItem(container, position);
            fragments[position] = fragment;

            return fragment;
        }

        public void setViewItems(List<CarInfo> carInfos) {
            for (BaseFragment fragment : fragments) {
                fragment.setCarsInfo(carInfos);
            }
        }
    }
}
