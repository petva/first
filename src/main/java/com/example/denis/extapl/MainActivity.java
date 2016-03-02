package com.example.denis.extapl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;


public class MainActivity extends Activity {

    LinearLayout bar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (LinearLayout) findViewById(R.id.bar);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onClick1(View w) {
        int color = (int) (0xff000000 + Math.random() * 0Xffffff);
        FrameImage objectItem = new FrameImage(getApplicationContext(), this, color);
        bar.addView(objectItem);

        textView.setText("count=" + bar.getChildCount());
    }

    public void onClick2(View w) {
        if (bar.getChildCount() > 0) bar.removeViewAt(0);

        textView.setText("count=" + bar.getChildCount());
    }

    //----------------------------------------------------------------------------------------------

    void startApplication(Context context, String packageName)
    {
        Intent intent = new Intent()
                .setPackage(packageName)
                .addCategory(Intent.CATEGORY_LAUNCHER)
                .setAction(Intent.ACTION_MAIN)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(intent, 0);

        if (list != null) {
            for (ResolveInfo ri : list)
            {
                intent.setClassName(ri.activityInfo.packageName, ri.activityInfo.name);
                break;
            }
        } else
        {
            Toast.makeText(context, String.format("Cannot resolve application '%s' activities", packageName), Toast.LENGTH_LONG).show();
            //android.util.Log.e("", String.format("Cannot resolve application '%s' activities", packageName));
        }

        try {
            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
            //android.util.Log.e("", ex.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------------
    public void btnClickGet(View w)
    {
        String jsonText = "{\"age\":2,\"food\":[\"food1\",\"food2\",\"food3\",\"food4\",\"food5\"],\"name\":\"Murzik2\",\"color\":\"GREEN\"}";

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Cat murzik = gson.fromJson(jsonText, Cat.class);

        textView.setText("Name: " + murzik.name + "\nAge: " + murzik.age + "\nColor: " + murzik.color + "\nFood: " + murzik.food);
    }
    //----------------------------------------------------------------------------------------------
    public void btnClickSet(View w)
    {
        Cat murzik = new Cat();
        murzik.name = "Murzik3";
        murzik.age = 3;
        murzik.color = "BLACK";
        murzik.food.add("fish");
        murzik.food.add("chicken");
        murzik.food.add("milk");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        textView.setText(gson.toJson(murzik));
    }

    //----------------------------------------------------------------------------------------------

}