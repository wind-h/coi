package com.wind.coi.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.wind.coi.utils.PerlinNoiseGenerator;

/**
 * @author hsc
 * @date 2024/10/18 15:08
 */
public class Map {

    private Array<Texture> textures;

    private int[][] mapData;

    private Integer mapWidth;

    private Integer mapHeight;

    private Integer cellSize;

    public Map(Integer mapWidth, Integer mapHeight, Integer cellSize, Array<Texture> textures) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.cellSize = cellSize;
        this.textures = textures;

        init();
    }

    public void init() {
        mapData = generateMapData(mapWidth, mapHeight);
    }

    private int[][] generateMapData(int width, int height) {
        int[][] map = new int[height][width];
        float scale = 0.1f;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double noiseValue = PerlinNoiseGenerator.perlin(x * scale, y * scale);
                if (noiseValue < 0.3) {
                    map[y][x] = 2; // 水
                } else if (noiseValue < 0.6) {
                    map[y][x] = 0; // 草地
                } else {
                    map[y][x] = 1; // 石头
                }
            }
        }
        return map;
    }

    public Integer getMapWidth() {
        return mapWidth;
    }

    public Integer getMapHeight() {
        return mapHeight;
    }

    public Integer getCellSize() {
        return cellSize;
    }

    public int[][] getMapData() {
        return mapData;
    }

    public Array<Texture> getTextures() {
        return textures;
    }
}
