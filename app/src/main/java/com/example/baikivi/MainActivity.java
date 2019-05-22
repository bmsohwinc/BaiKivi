package com.example.baikivi;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {


    private Button play, stop, record;
    private MediaRecorder myaudrec;
    private String outputFile;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button)findViewById(R.id.button12);
        stop = (Button)findViewById(R.id.button11);
        record = (Button)findViewById(R.id.button10);
        tv = (TextView)findViewById(R.id.textView);

        stop.setEnabled(false);
        play.setEnabled(false);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";

        myaudrec = new MediaRecorder();
        myaudrec.setAudioSource(MediaRecorder.AudioSource.MIC);
        myaudrec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myaudrec.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myaudrec.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener(){

              @Override
              public void onClick(View v) {
                  try {
                      myaudrec.prepare();
                      myaudrec.start();
                      tv.setText("Recording !!");
                  } catch (IllegalStateException ise) {
                      // make something ...
                        tv.setText("Error in Recording");
                  } catch (IOException ioe) {
                      // make something
                      tv.setText("Error in Recording");
                  }
                  record.setEnabled(false);
                  stop.setEnabled(true);
                  Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
              }

        }
        ) ;

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myaudrec.stop();
                myaudrec.release();
                myaudrec = null;
                record.setEnabled(true);
                stop.setEnabled(false);
                play.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Audio Recorded successfully", Toast.LENGTH_LONG).show();
                tv.setText("Stopped Recording");
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
                    tv.setText("Playing ...");
                } catch (Exception e) {
                    // make something
                    tv.setText("Error in Playing the file !");
                }
            }
        });






/**/

    }
}
