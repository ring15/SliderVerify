package com.founq.sdk.sliderverify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar mSeekBar;
    private SliderVerifyView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.verify_view);
        mSeekBar = findViewById(R.id.seek_bar);

        mView.setCheckResultListen(new SliderVerifyView.OnCheckResult() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "恭喜你啊 验证成功 可以搞事情了", Toast.LENGTH_SHORT).show();
                mSeekBar.setEnabled(false);
            }

            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this, "你有80%的可能是机器人，现在走还来得及", Toast.LENGTH_SHORT).show();
                mSeekBar.setProgress(0);
                mView.resetSlider();
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mView.setOffset(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mSeekBar.setMax(mView.maxWidth());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mView.check();
            }
        });
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.click:
                mView.resetSlider();
                mSeekBar.setProgress(0);
                mSeekBar.setEnabled(true);
                break;
        }
    }
}
