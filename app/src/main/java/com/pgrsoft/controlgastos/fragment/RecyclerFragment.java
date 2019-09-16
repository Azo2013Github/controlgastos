package com.pgrsoft.controlgastos.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgrsoft.controlgastos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;

    public RecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_recycler_listado, container, false);
        recyclerView = (RecyclerView) myView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return myView;
    }

}
