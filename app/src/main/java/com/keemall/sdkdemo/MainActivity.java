package com.keemall.sdkdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.keemall.aiosdk.sdks.AdManager;
import com.keemall.aiosdk.sdks.consts.AdType;
import com.keemall.aiosdk.sdks.consts.SdkName;
import com.keemall.aiosdk.sdks.entity.AdPosition;
import com.keemall.aiosdk.sdks.entity.SdkConfig;
import com.keemall.aiosdk.sdks.listener.BaseAdListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity activity = this;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button initSDK = findViewById(R.id.initsdk);
        initSDK.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "initSDK Button Clicked!", Toast.LENGTH_SHORT).show();
            List<SdkConfig> sdks = new ArrayList<>();
            sdks.add(new SdkConfig(
                    SdkName.BIGO,
                    "10182906",
                    "",
                    List.of(
                            new AdPosition(AdType.INTERSTITIAL, "10182906-10158798"),
                            new AdPosition(AdType.REWARD, "10182906-10001431")
                    )));
            sdks.add(new SdkConfig(
                    SdkName.MINTEGRAL,
                    "144002",
                    "7c22942b749fe6a6e361b675e96b3ee9",
                    List.of(
                            // 格式为：UNIT_ID-PLACEMENT_ID
                            new AdPosition(AdType.REWARD, "462372-290651"),
                            new AdPosition(AdType.INTERSTITIAL, "462374-290653")
                    )));
            sdks.add(new SdkConfig(SdkName.MAX,
                    "",
                    "05TMDQ5tZabpXQ45_UTbmEGNUtVAzSTzT6KmWQc5_CuWdzccS4DCITZoL3yIWUG3bbq60QC_d4WF28tUC4gVTF",
                    List.of(
                            new AdPosition(AdType.REWARD, "b0b2200f4b6b2857"),
                            new AdPosition(AdType.INTERSTITIAL, "93bc365da6e8464e")
                    )));
            sdks.add(new SdkConfig(SdkName.KWAI, "899999", "EaCw0AipSYyvf3E7",List.of(
                    new AdPosition(AdType.REWARD, "8999996001"),
                    new AdPosition(AdType.INTERSTITIAL, "8999996002")
            )));
            sdks.add(new SdkConfig(SdkName.PANGLE, "8025677", "", List.of(
                    new AdPosition(AdType.REWARD, "980088192"),
                    new AdPosition(AdType.INTERSTITIAL, "980088188")
            )));
            AdManager.intiAdSDK(activity, sdks);
        });
        Button showAd = findViewById(R.id.showad);
        showAd.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "showAd Button Clicked!", Toast.LENGTH_SHORT).show();
            AdManager.showAd(SdkName.MAX, AdType.INTERSTITIAL, new BaseAdListener() {
                @Override
                public void onAdShow() {
                    Log.i(TAG, "onAdShow");
                }

                @Override
                public void onAdShowFailed(int code, String msg, String sdkName) {
                    Log.i(TAG, sdkName + "onAdShowFailed code = " + code + " msg = " + msg);
                }

                @Override
                public void onAdClick() {
                    Log.i(TAG, "onAdClick");

                }

                @Override
                public void onAdPlayComplete() {
                    Log.i(TAG, "onAdPlayComplete");

                }

                @Override
                public void onRewardEarned() {
                    Log.i(TAG, "onAdEarned");
                }

                @Override
                public void onAdClose() {
                    Log.i(TAG, "onAdClose");

                }

                @Override
                public void onAdLoadFailed(int code, String msg, String sdkName) {
                    Log.i(TAG, sdkName + "onAdLoadFailed code = " + code + " msg = " + msg);
                }
            });
        });
    }
}