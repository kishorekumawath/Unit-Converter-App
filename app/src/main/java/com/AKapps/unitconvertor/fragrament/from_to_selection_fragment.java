package com.AKapps.unitconvertor.fragrament;

import android.annotation.SuppressLint;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.AKapps.unitconvertor.R;
import com.AKapps.unitconvertor.converter_activity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link from_to_selection_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class from_to_selection_fragment extends Fragment {
    ArrayAdapter<String> adapter;
    ListView listView;
    ArrayList<String> FromToList = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public from_to_selection_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment from_to_selection_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static from_to_selection_fragment newInstance(String param1, String param2) {
        from_to_selection_fragment fragment = new from_to_selection_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_from_to_selection_fragment, container, false);
        //getting the bundle data which got by the MainActivity
        Bundle args = this.getArguments();
        //this is moving to FromToList ArrayList (declared in global variable)
        FromToList = (ArrayList<String>) args.getSerializable("ArrayList");
        //here we setting the listview adapter
        listView = view.findViewById(R.id.listOfSelection);
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.item_selector_view,FromToList);
        listView.setAdapter(adapter);
        //when item is clicked in listview then this method is called
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // we are creating Converter_activity object
                converter_activity cm = (converter_activity) getActivity();
                //we calling the selectedData function present in Converter_activity,this function will just pass the item clicked data to Converter_activity
                    cm.selectedData(FromToList.get(position),position);
                    cm.fromclicked = true;
                    cm.toclicked = true;
            }
        });
        return view;
    }
}