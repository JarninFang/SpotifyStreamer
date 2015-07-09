package com.jarninfang.spotifystreamer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    EditText mSearchEditText;
    ListView listView;
    ArrayList<ArtistInfo> artistsArrayList;
    ArtistAdapter artistAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        artistsArrayList = new ArrayList<ArtistInfo>();

        //Instantialize the search bar
        mSearchEditText = (EditText) rootView.findViewById(R.id.search_edit_text);
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.d("MainActivityFragment", "actionId == IME_ACTION_DONE");
                    updateArtistArrayList();
                }

                if(actionId == EditorInfo.IME_ACTION_GO) {
                    Log.d("MainActivityFragment", "actionId == IME_ACTION_GO");
                    updateArtistArrayList();
                }

                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.d("MainActivityFragment", "actionId == IME_ACTION_SEARCH");
                    updateArtistArrayList();
                }
                return false;
            }
        });

        //Initialize the adapter
        artistAdapter = new ArtistAdapter(getActivity(), artistsArrayList);
        Log.d("MainActivityFragment" , "artistsArrayList " + artistsArrayList.toString());
        listView = (ListView) rootView.findViewById(R.id.artist_list_view);
        listView.setAdapter(artistAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String artistName = artistAdapter.getItem(position).getName();
                Intent intent = new Intent(getActivity(), TrackActivity.class);
            }
        });

        return rootView;
    }

    private void updateArtistArrayList() {
        FetchArtistsTask fetchArtistsTask = new FetchArtistsTask();
        Log.d("MainActivityFragment", "edittext string = " + mSearchEditText.getText().toString());
        fetchArtistsTask.execute(mSearchEditText.getText().toString());
    }


    //Gets data from SpotifyAPI and sets listview with it
    public class FetchArtistsTask extends AsyncTask<String, Void, ArrayList<ArtistInfo>> {

        ArrayList<ArtistInfo> artists;

        //Returns ArrayList of ArtistInfo objects
        @Override
        protected ArrayList<ArtistInfo> doInBackground(String... params) {
            //Connect to the SpotifyAPI with the wrapper
            SpotifyApi spotifyApi = new SpotifyApi();

            //Create a SpotifyService object that we can use to get the desired data
            SpotifyService spotifyService = spotifyApi.getService();

            //ArtistsPager object holds results of spotifyService search for artist
            ArtistsPager results = spotifyService.searchArtists(params[0]);

            artists = new ArrayList<>();

            for(int i = 0; i < results.artists.items.size(); i++) {
                ArtistInfo artistInfo = new ArtistInfo();

                if(!results.artists.items.get(i).images.isEmpty()) {
                    artistInfo.setImageURL(results.artists.items.get(i).images.get(0).url);
                }
                artistInfo.setName(results.artists.items.get(i).name);

                artists.add(artistInfo);
                Log.d("FetchArtistsTask", "artist name = " + artists.get(i).getName());
                Log.d("FetchArtistsTask", "artist url = " + artists.get(i).getImageURL());
                /*artists.add(i, results.artists.items.get(i).images.get(i).url);
                artists.get(i).setImageURL(results.artists.items.get(i).images.get(0).url);
                artists.get(i).setName(results.artists.items.get(i).name);*/
            }

            return artists;
        }

        @Override
        protected void onPostExecute(ArrayList<ArtistInfo> artistInfoArrayList) {
            super.onPostExecute(artistInfoArrayList);
            artistsArrayList.clear();
            artistsArrayList.addAll(artists);

            listView.setAdapter(new ArtistAdapter(getActivity(), artistsArrayList));
            Log.d("MainActivityFragment" , "onPostExecute");
        }
    }

    public class FetchTracksTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            SpotifyApi spotifyApi = new SpotifyApi();
            SpotifyService spotifyService = spotifyApi.getService();


            return null;
        }
    }

}
