package net.cold.coldsmod.stat;

public class StatUtils {

    public static String formatValue(double value) {
        if (Math.abs(value - Math.round(value)) < 0.01) {
            return String.format("%d", (int)Math.round(value));
        }

        int decimals = 0;
        double temp = value;

        while (decimals < 2 && Math.floor(temp) != temp) {
            temp *= 10;
            decimals++;
        }

        switch (decimals) {
            case 0: return String.format("%.0f", value);
            case 1: return String.format("%.1f", value);
            case 3: return String.format("%.3f", value);
            default: return String.format("%.2f", value);
        }
    }

    public static String formatValue(double value, boolean extraDigit) {
        if (Math.abs(value - Math.round(value)) < 0.01) {
            return String.format("%d", (int)Math.round(value));
        }

        int decimals = 0;
        double temp = value;

        while (decimals < 2 && Math.floor(temp) != temp) {
            temp *= 10;
            decimals++;
        }
        if (decimals == 3) {
            return String.format("%.3f", value);
        }

        switch (decimals) {
            case 0: return String.format("%.0f", value);
            case 1: return String.format("%.1f", value);
            default: return String.format("%.2f", value);
        }
    }
}
