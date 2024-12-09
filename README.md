# SDK 使用说明

## 1. SDK 说明
本项目集成了多个广告 SDK，包括 Bigo、AppLovin MAX、Pangle、Mintegral 和 Kwai。通过这些 SDK，您可以在应用中展示插屏广告和激励广告。

## 2. 下载 SDK
把本仓库libs文件夹的文件拷贝到你的android项目的根目录下的libs文件夹，然后在项目的根目录下的settings.gradle文件中的repositories添加以下代码：
```groovy
flatDir {
    dirs("libs")
}
```

## 3. 添加依赖
在 `app/build.gradle` 文件中添加以下依赖：
```groovy
dependencies {
    //其他依赖
    implementation fileTree(include: ['*.jar', '*.aar'], dir: 'libs')
    //bigo
    implementation 'com.bigossp:bigo-ads:4.8.0'
    //applovin MAX
    implementation 'com.applovin:applovin-sdk:+'
    //pangle
    implementation 'com.pangle.global:ads-sdk:6.0.0.7'
    //mtg
    implementation 'com.mbridge.msdk.oversea:reward:16.7.51'
    //如果您需要使用竞价广告,请添加此条依赖语句。(mbbid)
    implementation 'com.mbridge.msdk.oversea:mbbid:16.7.51'
    implementation 'com.mbridge.msdk.oversea:newinterstitial:16.7.51'

    implementation 'com.squareup.okhttp3:okhttp:4.12.0'
    implementation "androidx.media3:media3-exoplayer:1.0.0-alpha01"
    implementation "androidx.appcompat:appcompat:1.2.0"
    implementation "com.google.android.material:material:1.2.1"
    implementation "androidx.annotation:annotation:1.2.0"
    // 最低支持 kotlin1.4.10
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.10"
    // gaid 最低支持play-services-ads-identifier:18.0.1
    implementation "com.google.android.gms:play-services-ads-identifier:18.0.1"
}
```
## 4. 初始化 SDK
在 `Application` 的 `onCreate` 方法中初始化 SDK：
```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
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
    }
}
```
已上是各个sdk的默认参数，实际使用替换成自己的参数即可。

## 5. 加载广告
在需要加载广告的地方调用以下方法：
```java
public void showAD(){
AdManager.showAd(SdkName.KWAI, AdType.INTERSTITIAL, new BaseAdListener() {
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
}
```

