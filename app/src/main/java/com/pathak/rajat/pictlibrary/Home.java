package com.pathak.rajat.pictlibrary;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends Fragment
{
    long animationDuration = 2000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("BOOK LOCATOR");

        ImageView imageView = (ImageView)view.findViewById(R.id.mag);

        float x = view.getX();
        float y = view.getY();

        //ANIMATION

        ObjectAnimator objectAnimatorx = ObjectAnimator.ofFloat(imageView,"x",440f);
        ObjectAnimator objectAnimatory = ObjectAnimator.ofFloat(imageView,"y",440f);
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(imageView,"x",-440f);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(imageView,"y",-440f);
        objectAnimatorx.setDuration(animationDuration);
        objectAnimatorX.setDuration(animationDuration);
        objectAnimatory.setDuration(animationDuration);
        objectAnimatorY.setDuration(animationDuration);


        AnimatorSet animatorSet = new AnimatorSet();

            objectAnimatorX.setFloatValues(0f);
            animatorSet.playSequentially(objectAnimatorx, objectAnimatory, objectAnimatorX, objectAnimatorY);
            animatorSet.start();
            objectAnimatorY.setFloatValues(70f);




    }



}
