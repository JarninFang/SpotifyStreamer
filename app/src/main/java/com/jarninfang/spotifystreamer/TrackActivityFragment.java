package com.jarninfang.spotifystreamer;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.TracksPager;

/**
 * Created by jarnin on 7/8/15.
 */
public class TrackActivityFragment extends Fragment {

    ListView listView;
    String artistName;
    TrackAdapter trackAdapter;
    ArrayList<TrackInfo> trackInfoArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_track, container, false);

        artistName = getArtistDataFromIntent();
        listView = (ListView) rootView.findViewById(R.id.track_list_view);

        trackInfoArrayList = new ArrayList<TrackInfo>();

        updateTrackData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String trackName = trackInfoArrayList.get(position).getSongName();
                Intent i = new Intent(getActivity(), PlayerActivity.class);
                i.putExtra(Intent.EXTRA_TEXT, trackName);
            }
        });
        return rootView;
    }

    private void updateTrackData() {
        FetchTopTracksTask fetchTopTracksTask = new FetchTopTracksTask();
        fetchTopTracksTask.execute(artistName);
    }

    private String getArtistDataFromIntent() {
        String artistNameData;
        Intent i = getActivity().getIntent();

        if((i != null) && i.hasExtra(Intent.EXTRA_TEXT)) {
            artistNameData = i.getStringExtra(Intent.EXTRA_TEXT);
        } else {
            artistNameData = "Error retrieving artist name";
        }

        return artistNameData;
    }

    public class FetchTopTracksTask extends AsyncTask<String, Void, ArrayList<TrackInfo>> {
        ArrayList<TrackInfo> tracks;

        @Override
        protected ArrayList<TrackInfo> doInBackground(String... params) {


            SpotifyApi spotifyApi = new SpotifyApi();
            SpotifyService spotifyService = spotifyApi.getService();

            TracksPager results = spotifyService.searchTracks(params[0]);

            tracks = new ArrayList<TrackInfo>();

            for(int i = 0; i < results.tracks.items.size(); i++) {
                TrackInfo trackInfo = new TrackInfo();
                trackInfo.setImageUrl(results.tracks.items.get(i).album.images.get(0).url);
                trackInfo.setAlbumName(results.tracks.items.get(i).album.name);
                trackInfo.setSongName(results.tracks.items.get(i).name);
                tracks.add(trackInfo);
            }

            return tracks;
        }

        @Override
        protected void onPostExecute(ArrayList<TrackInfo> trackInfos) {
            super.onPostExecute(trackInfos);
            trackInfoArrayList = tracks;
            trackAdapter = new TrackAdapter(getActivity(), trackInfoArrayList);
            listView.setAdapter(trackAdapter);
        }
    }
}
