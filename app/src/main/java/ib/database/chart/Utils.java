package ib.database.chart;

/**
 * Created by Chen on 12/7/2016.
 */
import android.graphics.Color;
public class Utils {
    public static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }
}

