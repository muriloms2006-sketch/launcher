package com.app.flexlauncher;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppAdapter extends ArrayAdapter<ResolveInfo> {

    public AppAdapter(Context context, int resource, List<ResolveInfo> appList) {
        super(context, resource, appList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_lista, parent, false);
        }

        ResolveInfo appInfo = getItem(position);

        TextView textViewAppName = view.findViewById(R.id.app_name);
        ImageView imageViewAppIcon = view.findViewById(R.id.app_icon);

        if (appInfo != null) {
            textViewAppName.setText(
                    appInfo.loadLabel(getContext().getPackageManager())
            );

            imageViewAppIcon.setImageDrawable(
                    appInfo.loadIcon(getContext().getPackageManager())
            );
        }

        return view;
    }
}