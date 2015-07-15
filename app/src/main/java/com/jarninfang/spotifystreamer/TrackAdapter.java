package com.jarninfang.spotifystreamer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jarnin on 7/10/15.
 */
public class TrackAdapter extends BaseAdapter {

    private ArrayList<TrackInfo> trackInfoArrayList;
    private LayoutInflater inflater;

    public TrackAdapter(Context context, ArrayList<TrackInfo> trackInfos) {
        this.trackInfoArrayList = trackInfos;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return trackInfoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return trackInfoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        String nullImage = "https://upload.wikimedia.org/wikipedia/" +
                "commons/4/44/Question_mark_(black_on_white).png";

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.track_list_item_view, null);
            viewHolder = new ViewHolder();
            viewHolder.albumName = (TextView)
                    convertView.findViewById(R.id.album_text_view);
            viewHolder.trackImage = (ImageView)
                    convertView.findViewById(R.id.preview_image_view);
            viewHolder.trackName = (TextView)
                    convertView.findViewById(R.id.song_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.trackName.setText(trackInfoArrayList.get(position).getSongName());
        viewHolder.albumName.setText(trackInfoArrayList.get(position).getAlbumName());
        if(trackInfoArrayList.get(position).getImageUrl() != null) {
            Log.d("TrackAdapter", trackInfoArrayList.get(position).getImageUrl());
            Picasso.with(parent.getContext()).load(trackInfoArrayList.get(position).getImageUrl())
                    .into(viewHolder.trackImage);
        } else {
            Picasso.with(parent.getContext()).load(nullImage)
                    .into(viewHolder.trackImage);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView trackImage;
        TextView albumName;
        TextView trackName;
    }
}
