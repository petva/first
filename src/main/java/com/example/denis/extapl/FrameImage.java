package com.example.denis.extapl;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FrameImage extends LinearLayout
{
    Context context;
    Activity activity;
    ImageView imageView;
    int color;

    public FrameImage(Context context, Activity activity, int color)
    {
        super(context);
        this.context = context;
        this.activity = activity;
        this.color = color;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.iamge_item, this);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setBackgroundColor(color);
    }

}
