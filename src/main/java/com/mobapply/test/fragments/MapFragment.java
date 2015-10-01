package com.mobapply.test.fragments;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobapply.test.R;
import com.mobapply.test.models.Order;

import java.io.IOException;
import java.util.List;

public class MapFragment
        extends Fragment
{
    Geocoder geocoder;
    GoogleMap mMap;
    MySupportMapFragment mapFragment;

    private List<Order> orders;

    public static MapFragment getInstance(List<Order> or)
    {
        MapFragment fragment = new MapFragment();
        fragment.orders = or;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setMap();
        markOrders();
    }

    private void setMap()
    {
        FragmentManager fragmentManager = getChildFragmentManager();
        mapFragment = (MySupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.setListener(new MySupportMapFragment.OnDownTouchListener()
        {
            @Override
            public void OnDownTouch()
            {
            }
        });
        mMap = mapFragment.getMap();
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker marker)
            {
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(final Marker marker)
            {
                return true;
            }
        });

        try
        {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(0, 0)));
        } catch(Exception e)
        {
            Log.e("Error", "crash set coord");
        }
    }

    private void markOrders()
    {
        if(mMap == null || orders == null|| orders.size() == 0)
        {
            return;
        }
        mMap.clear();
        //
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        geocoder = new Geocoder(getActivity());
        for(int i=0; i<orders.size(); i++)
        {
            LatLng coord = addAddress(orders.get(i).departureAddress, BitmapDescriptorFactory.HUE_GREEN);
            if(coord != null)
            {
                builder.include(coord);
            }
            coord = addAddress(orders.get(i).destinationAddress, BitmapDescriptorFactory.HUE_RED);
            if(coord != null)
            {
                builder.include(coord);
            }
        }
        //
        LatLngBounds bounds = builder.build();
        int padding = 50; // offset from edges of the map in pixels
        final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback()
        {
            @Override
            public void onMapLoaded()
            {
                mMap.animateCamera(cu);
            }
        });
    }

    private LatLng addAddress(Order.Address a, float f)
    {
        Geocoder geocoder = new Geocoder(getActivity());
        List<Address> address = null;
        try
        {
            address = geocoder.getFromLocationName(a.zipCode+" "+a.country+" "+a.city+" ", 1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if(address == null || address.size()<1)
        {
            return null;
        }
        LatLng coord = new LatLng(address.get(0).getLatitude(), address.get(0).getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(coord)
                .icon(BitmapDescriptorFactory.defaultMarker(f)));
        return coord;
    }
}