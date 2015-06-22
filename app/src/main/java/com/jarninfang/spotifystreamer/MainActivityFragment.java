package com.jarninfang.spotifystreamer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    EditText mSearchEditText;
    ListView listView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //Instantialize the search bar
        mSearchEditText = (EditText) rootView.findViewById(R.id.search_edit_text);

        //ArrayList for fake artist data for the listview
        ArrayList<String> fakeArtists = new ArrayList<>();
        fakeArtists.add(0, "artist0");
        fakeArtists.add(1, "artist1");
        fakeArtists.add(2, "artist2");
        fakeArtists.add(3, "artist3");
        fakeArtists.add(4, "artist4");
        fakeArtists.add(5, "artist5");
        fakeArtists.add(6, "artist6");
        fakeArtists.add(7, "artist7");
        fakeArtists.add(8, "artist8");
        fakeArtists.add(9, "artist9");

        //Initialize Listview Adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_artist, R.id.list_item_artist_textview, fakeArtists);

        listView = (ListView) rootView.findViewById(R.id.artist_list_view);
        listView.setAdapter(arrayAdapter);

        return rootView;
    }
}
