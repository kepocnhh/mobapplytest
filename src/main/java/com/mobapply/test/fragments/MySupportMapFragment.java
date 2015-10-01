package com.mobapply.test.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.SupportMapFragment;

public class MySupportMapFragment
        extends SupportMapFragment
{
    public View mOriginalContentView;
    public TouchableWrapper mTouchView;
    private OnDownTouchListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        mOriginalContentView = super.onCreateView(inflater, parent, savedInstanceState);
        mTouchView = new TouchableWrapper(getActivity());
        mTouchView.addView(mOriginalContentView);
        return mTouchView;
    }

    @Override
    public View getView()
    {
        return mOriginalContentView;
    }


    public class TouchableWrapper
            extends FrameLayout
    {

        public TouchableWrapper(Context context)
        {
            super(context);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event)
        {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    if (listener != null)
                    {
                        listener.OnDownTouch();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return super.dispatchTouchEvent(event);
        }
    }

    public void setListener(OnDownTouchListener listener)
    {
        this.listener = listener;
    }

    public interface OnDownTouchListener
    {
        void OnDownTouch();
    }


}