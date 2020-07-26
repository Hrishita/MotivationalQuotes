package com.jayshreegopalapps.motivationalquotes;

import java.util.Random;

public class RandomUtils {
    public static int[] colors = {R.color.colorRed1,R.color.colorRed2,R.color.colorRed3,R.color.colorRed4,R.color.colorRed5,R.color.colorRed6,R.color.colorRed8,R.color.colorBlue16,R.color.colorBlue15,R.color.colorBlue14,R.color.colorBlue13,R.color.colorBlue12,R.color.colorBlue11,R.color.colorBlue10,R.color.colorBlue9,R.color.colorBlue8,R.color.colorBlue7,R.color.colorBlue6,R.color.colorBlue5,R.color.colorBlue4,R.color.colorBlue3,R.color.colorBlue2,R.color.colorBlue1,R.color.colorBlue17,R.color.colorBlue18,R.color.colorGreen7,R.color.colorGreen6,R.color.colorGreen5,R.color.colorGreen4,R.color.colorGreen2,R.color.colorGreen1};
    public static int getRandomColor() {
        return colors[new Random().nextInt(colors.length)];
    }
}
