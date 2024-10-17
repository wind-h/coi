package com.wind.coi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author hsc
 * @date 2024/10/17 10:03
 */
public class Map {

    private int mapWidth = 100;

    private int mapHeight = 100;

    private int cellSize = 16;

    private TextureRegion[][] textureRegions;

    private TextureRegion[][] map;

    private Texture texture;


    public Map(int mapWidth, int mapHeight, int cellSize, Texture texture) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.cellSize = cellSize;
        this.texture = texture;

        init();
    }

    public void init() {
        int regionWidth = texture.getWidth() / 4;
        int regionHeight = texture.getHeight() / 4;
        textureRegions = new TextureRegion[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                textureRegions[i][j] = new TextureRegion(texture,  i * regionWidth, j * regionHeight, regionWidth, regionHeight);
            }
        }
        initMap();
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                batch.draw(map[i][j], i * cellSize , j * cellSize , cellSize, cellSize);
            }
        }
    }

    private void initMap() {
        map = new TextureRegion[mapWidth][mapHeight];
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                TextureRegion randomTextureRegion = getRandomTextureRegion();
                map[i][j] = randomTextureRegion;
            }
        }
    }

    private TextureRegion getRandomTextureRegion() {
        int randomX = (int) (Math.random() * 4);
        int randomY = (int) (Math.random() * 4);
        return textureRegions[randomX][randomY];
    }

    public void saveMapToFile(String filePath) {
        try (DataOutputStream doc = new DataOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
            doc.writeInt(mapWidth);
            doc.writeInt(mapHeight);

            for (int i = 0; i < mapWidth; i++) {
                for (int j = 0; j < mapHeight; j++) {
                    int textureId = (map[i][j].getRegionX() / (texture.getWidth() / 4) +
                            map[i][j].getRegionY() / (texture.getHeight() / 4)) * 4;
                    doc.writeInt(textureId);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMapFromFile(String filePath) {
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(Paths.get(filePath)))) {
            int width = dis.readInt();
            int height = dis.readInt();

            map = new TextureRegion[width][height];

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int textureId = dis.readInt();
                    int x = textureId % 4;
                    int y = textureId / 4;
                    map[i][j] = textureRegions[x][y];
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }
}
