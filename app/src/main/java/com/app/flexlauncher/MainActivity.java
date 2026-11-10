package com.app.flexlauncher;

import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listview_apps);

        PackageManager packageManager = getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN);

        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> appList =
                packageManager.queryIntentActivities(mainIntent, 0);

        AppAdapter appAdapter = new AppAdapter(
                this,
                R.layout.item_lista,
                appList
        );

        listView.setAdapter(appAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            ResolveInfo appInfo =
                    (ResolveInfo) parent.getItemAtPosition(position);

            String packageName = appInfo.activityInfo.packageName;

            Intent intent =
                    packageManager.getLaunchIntentForPackage(packageName);

            if (intent != null) {
                startActivity(intent);
            } else {
                Toast.makeText(
                        MainActivity.this,
                        "Não foi possível abrir o aplicativo",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}