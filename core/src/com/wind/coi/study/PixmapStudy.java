package com.wind.coi.study;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PixmapStudy extends ApplicationAdapter {

    SpriteBatch batch;

    Pixmap pixmap;

    Texture imgPixmap;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // 创建一个宽高都为 256, 像素点颜色值格式为 RGBA8888(每个像素颜色值占 4 个字节) 的 Pixmap
        pixmap = new Pixmap(256, 256, Pixmap.Format.RGBA8888);

        // 设置绘图颜色为白色
        pixmap.setColor(1, 1, 1, 1);
        // 将整个 pixmap 填充为当前设置的颜色
        pixmap.fill();

        // 设置绘图颜色为红色
        pixmap.setColor(1, 0, 0, 1);
        // 画一个空心圆
        pixmap.drawCircle(64, 64, 32);

        // 设置绘图颜色为绿色
        pixmap.setColor(Color.GREEN);
        // 画一条线段, 线段两点为 (0, 0) 到 (256, 128)
        pixmap.drawLine(0, 0, 256, 128);

        // 设置绘图颜色为蓝色
        pixmap.setColor(Color.BLUE);
        // 画一个矩形, 矩形左上角坐标(128, 128), 宽高均为64
        pixmap.drawRectangle(128, 128, 64, 64);

        // 设置绘图颜色为黄色
        pixmap.setColor(Color.YELLOW);
        // 填充一个三角形, 三点(0, 256), (0, 128), (128, 128)
        pixmap.fillTriangle(0, 256, 0, 128, 128, 128);

        // pixmap 处理完成后转换成纹理
        imgPixmap = new Texture(pixmap);

        // pixmap 已不再需要用到, 释放资源
        pixmap.dispose();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(imgPixmap, Gdx.graphics.getWidth() - 256, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgPixmap.dispose();
    }
}
