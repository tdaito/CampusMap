package com.example.test2;

import android.Manifest;
import android.content.pm.PackageManager; //packagemanager使う時に必要
import android.os.Build; //Build使う時に必要
import android.support.annotation.NonNull; //@Nonnull使う時に必要
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.net.wifi.WifiManager;
import android.util.Log; //Log使う時に必要
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.net.wifi.ScanResult;
import android.widget.ImageView;
import android.widget.Toast;

import static android.content.Context.WIFI_SERVICE;


public class MainActivity extends AppCompatActivity
{
    private final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 0;
    private static final String TAG = "MainActivity";
    String CSVstr = "";

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 既に許可されているか確認
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // 許可されていなかったらリクエストする
                // ダイアログが表示される
                requestPermissions(
                        new String[]{
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        },
                        PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);
                return;
            }
        }
        logScanResults();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 許可された場合
            logScanResults();
        } else {
            // 許可されなかった場合
            // 何らかの対処が必要
        }
    }

    private void logScanResults() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        List<ScanResult> scanResults = wm.getScanResults();

        String[] ssid = new String[scanResults.size()];  //受信した全てのWiFiのSSID
        String[] macadress = new String[scanResults.size()];  //受信した全てのWiFiのMACｱﾄﾞﾚｽ
        int[] rssi = new int[scanResults.size()];  //受信した全てのWiFiの受信強度


        for (ScanResult scanResult : scanResults) {
            Log.d(TAG, scanResult.toString());
        }

        Toast.makeText(getApplicationContext(), "WiFi情報取得", Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // オプションメニューを作成する
    public boolean onCreateOptionsMenu(Menu menu){
        // menuにcustom_menuレイアウトを適用
        getMenuInflater().inflate(R.menu.menu, menu);
        // オプションメニュー表示する場合はtrue
        return true;
    }

    // メニュー選択時の処理　今回はトースト表示
    public boolean onOptionsItemSelected(MenuItem menuItem){

        // 押されたメニューのIDで処理を振り分ける
        switch (menuItem.getItemId()){
            case R.id.f1:
                ((ImageView)findViewById(R.id.imageView)).setImageResource(R.drawable.first);
                return true;

            case R.id.f2:
                ((ImageView)findViewById(R.id.imageView)).setImageResource(R.drawable.second);
                return true;

            case R.id.f3:
                ((ImageView)findViewById(R.id.imageView)).setImageResource(R.drawable.three);
                return true;

            case R.id.f4:
                ((ImageView)findViewById(R.id.imageView)).setImageResource(R.drawable.fourth);
                return true;
        }
        return true;
    }


}