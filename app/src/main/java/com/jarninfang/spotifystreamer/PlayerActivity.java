package com.jarninfang.spotifystreamer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jarnin on 7/15/15.
 */
public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.player_container, new PlayerActivityFragment())
                    .commit();
        }
    }
}
