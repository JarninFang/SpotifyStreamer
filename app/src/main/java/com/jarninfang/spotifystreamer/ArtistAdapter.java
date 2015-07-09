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
 * Created by jarnin on 7/1/15.
 */
public class ArtistAdapter extends BaseAdapter {

    private static ArrayList<ArtistInfo> artistInfoArrayList;
    private LayoutInflater inflater;

    String nullImage = "https://upload.wikimedia.org/wikipedia/" +
            "commons/4/44/Question_mark_(black_on_white).png";
    public ArtistAdapter(Context context, ArrayList<ArtistInfo> artistInfoArrayList) {
        this.artistInfoArrayList = artistInfoArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ArtistInfo getItem(int position) {
        return artistInfoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_view, null);
            holder = new ViewHolder();
            holder.artistImage = (ImageView)
                    convertView.findViewById(R.id.list_item_artist_imageview);
            holder.artistText = (TextView)
                    convertView.findViewById(R.id.list_item_artist_textview);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Log.d("ArtistAdapter", "artistInfoArrayList = " + artistInfoArrayList.get(position).getName());
        holder.artistText.setText(artistInfoArrayList.get(position).getName());
        if(artistInfoArrayList.get(position) != null) {
            Picasso.with(parent.getContext()).load(artistInfoArrayList.get(position).getImageURL())
                    .into(holder.artistImage);
        } else {
            Picasso.with(parent.getContext()).load(nullImage)
                    .into(holder.artistImage);
        }


        return convertView;
    }

    @Override
    public int getCount() {
        return artistInfoArrayList.size();
    }

    static class ViewHolder {
        ImageView artistImage;
        TextView artistText;
    }
}
