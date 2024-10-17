package com.wind.coi;

import java.util.Random;

public class PerlinNoiseGenerator {
    private static final int[] p = new int[512];
    private static final Random random = new Random();

    static {
        for (int i = 0; i < 256; i++) {
            p[i] = i;
        }
        shuffle(p, 256);
        for (int i = 0; i < 256; i++) {
            p[256 + i] = p[i];
        }
    }

    private static void shuffle(int[] array, int length) {
        for (int i = 0; i < length; i++) {
            int target = random.nextInt(length);
            int temp = array[i];
            array[i] = array[target];
            array[target] = temp;
        }
    }

    private static double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private static double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private static double grad(int hash, double x, double y) {
        int h = hash & 15;
        double u = h < 8 ? x : y;
        double v = h < 4 ? y : h == 12 || h == 14 ? x : 0;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    public static double perlin(double x, double y) {
        int xi = (int) Math.floor(x) & 255;
        int yi = (int) Math.floor(y) & 255;
        x -= Math.floor(x);
        y -= Math.floor(y);
        double u = fade(x);
        double v = fade(y);
        int aa = p[p[xi] + yi];
        int ab = p[p[xi] + yi + 1];
        int ba = p[p[xi + 1] + yi];
        int bb = p[p[xi + 1] + yi + 1];
        double result = lerp(v, lerp(u, grad(aa, x, y), grad(ba, x - 1, y)),
                             lerp(u, grad(ab, x, y - 1), grad(bb, x - 1, y - 1)));
        return (result + 1) / 2; // Normalize to [0, 1]
    }
}