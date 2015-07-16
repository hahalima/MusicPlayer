package com.example.chris_000.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Button previousButton;
    private Button pausePlayButton;
    private Button nextButton;
    private TextView musicText;
    private MediaPlayer mediaPlayer;
    private SeekBar volumeBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicText = (TextView) findViewById(R.id.musicTextView);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.iphone_remix);

        previousButton = (Button) findViewById(R.id.previousButton);
        pausePlayButton = (Button) findViewById(R.id.pauseButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        volumeBar = (SeekBar) findViewById(R.id.volumeBar);

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println(seekBar.getProgress());
                float currentVol = progress/50.0f;
                if (progress == 0) {
                    mediaPlayer.setVolume(0.0f, 0.0f);
                }
                else if (progress == seekBar.getMax()) {
                    mediaPlayer.setVolume(1.0f, 1.0f);
                }
                else {
                    mediaPlayer.setVolume(currentVol, currentVol);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go backwards
            }
        });

        pausePlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    //stop playing the audio
                    pauseAudio(mediaPlayer);
                } else {
                    playAudio(mediaPlayer);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void playAudio(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            musicText.setText("Music playing now!");
            pausePlayButton.setBackground(getResources().getDrawable(R.drawable.media_pause));
            mediaPlayer.setVolume(volumeBar.getProgress()/50.0f, volumeBar.getProgress()/50.0f);
        }
    }

    public void pauseAudio(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            int duration = (mediaPlayer.getCurrentPosition()/1000);
            mediaPlayer.pause();
            musicText.setText("Music paused");
            pausePlayButton.setBackground(getResources().getDrawable(R.drawable.play_media));
            mediaPlayer.setVolume(volumeBar.getProgress() / 50.0f, volumeBar.getProgress() / 50.0f);
            Toast.makeText(getApplicationContext(), "Duration was " + duration + "seconds", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
