package dev.ruanvictor.sleepystorie.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Random;

import dev.ruanvictor.sleepystorie.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {

    public PlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        SeekBar seekBar = view.findViewById(R.id.seekBar);
        TextView textPercent = view.findViewById(R.id.textPercent);
        TextView textTimeLeft = view.findViewById(R.id.textTimeLeft);
        Button buttonForward10 = view.findViewById(R.id.buttonForward10);
        Button buttonReplay10 = view.findViewById(R.id.buttonReplay10);

        int value = new Random().nextInt(100);

        seekBar.setProgress(value);
        textPercent.setText(String.valueOf(value).concat(getString(R.string.percent_complet)));
        textTimeLeft.setText(getString(R.string.time_left));

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textPercent.setText(String.valueOf(progress).concat(getString(R.string.percent_complet)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        buttonForward10.setOnClickListener(v -> seekBar.setProgress(seekBar.getProgress()+10));

        buttonReplay10.setOnClickListener(v -> seekBar.setProgress(seekBar.getProgress()-10));

        return view;
    }
}
