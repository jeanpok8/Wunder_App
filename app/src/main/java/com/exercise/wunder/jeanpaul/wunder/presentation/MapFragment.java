package com.exercise.wunder.jeanpaul.wunder.presentation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.wunder.jeanpaul.wunder.R;
import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;
import com.exercise.wunder.jeanpaul.wunder.presentation.base.BaseFragment;
import com.exercise.wunder.jeanpaul.wunder.presenter.MapLocationsPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private MapView gMapView;
    private GoogleMap gMap = null;
    private List<CarInfo> carsInfo;
    private List<Marker> allMarkers = new ArrayList<>();
    private int countClicksOnMarker = 0;

    MapLocationsPresenter mapPresenter;

    private OnFragmentInteractionListener mListener;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapFragment.
     */
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapPresenter = new MapLocationsPresenter(((MainActivity) getActivity()).localRepo);
        mapPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_map, container, false);

        gMapView = (MapView) view.findViewById(R.id.soleViewMap);
        gMapView.getMapAsync(this);

        gMapView.onCreate(getArguments());

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gMapView != null)
            gMapView.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (gMapView != null)
            gMapView.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (gMapView != null)
            gMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (gMapView != null)
            gMapView.onStop();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (gMapView != null)
            gMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void setItemsInAdapter(List<CarInfo> carsInfo) {

        this.carsInfo = carsInfo;
        addMarkers();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        } else {
            setUpMap();
        }

    }

    private void setUpMap() {

        if (carsInfo != null && carsInfo.size() > 0) {
            addMarkers();
        } else {

            mapPresenter.getCarsInfo();

        }
    }

    private void addMarkers() {
        for (CarInfo carInfo : carsInfo) {
            List<Double> coordinates = carInfo.getCoordinates();
            if (coordinates != null && coordinates.size() >= 2) {

                addMarkerOnMap(coordinates.get(0), coordinates.get(1), carInfo.getName());
            }
        }
    }

    private void addMarkerOnMap(double longitude, double latitude, String title) {

        LatLng UCA = new LatLng(latitude, longitude);
        MarkerOptions options = new MarkerOptions();
        options.position(UCA);
        options.title(title);
        Marker marker = gMap.addMarker(options);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UCA, 13));
        gMap.setOnMarkerClickListener(this);
        allMarkers.add(marker);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {


        if (countClicksOnMarker == 0) {
            for (Marker mrkr : allMarkers) {
                if (marker.equals(mrkr)) {
                    mrkr.showInfoWindow();
                    countClicksOnMarker++;
                } else {
                    mrkr.setVisible(false);
                }
            }
        } else {
            for (Marker mrkr : allMarkers) {
                if (marker.equals(mrkr) && mrkr.isVisible()) {
                    mrkr.hideInfoWindow();
                }
                mrkr.setVisible(true);
            }
            countClicksOnMarker = 0;
        }

        return true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
}
