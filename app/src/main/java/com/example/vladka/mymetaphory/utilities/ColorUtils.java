package com.example.vladka.mymetaphory.utilities;

/**
 * Created by Vladka on 29/01/2018.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.example.vladka.mymetaphory.R;

/**
 * Colors the ViewHolders in RecyclerView
 */
public class ColorUtils {
    public static int getViewHolderCategoriesBackground(Context context, int instanceCategoryNum) {

        switch (instanceCategoryNum) {

            case 0:
                return ContextCompat.getColor(context, R.color.categoryWisdom);
            case 1:
                return ContextCompat.getColor(context, R.color.categoryUniverse);
            case 2:
                return ContextCompat.getColor(context, R.color.categoryLife);
            case 3:
                return ContextCompat.getColor(context, R.color.categoryFun);
            case 4:
                return ContextCompat.getColor(context, R.color.categoryHappyness);
            default:
                return Color.WHITE;
        }
    }
}
