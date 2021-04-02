package com.example.todomvvm.ui.todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todomvvm.R;


public class ViewFragment extends Fragment {


TextView viewTitle,viewDescription,viewDate;

    public static Fragment newInstance() {

        return new ViewFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_view, container, false);

        viewTitle = view.findViewById(R.id.singleView);
        viewDescription = view.findViewById(R.id.singleViewDescription);
        viewDate = view.findViewById(R.id.date_view);


        return view;
    }
}