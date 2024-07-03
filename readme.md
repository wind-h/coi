## 静态类

Gdx.app : 代表一个游戏应用的实例，通常在平台的启动器中被实例化并启动API客户端（core 子项目可以看做是 API 客户端）。该接口实例会将应用程序层面的事件通知API客户端，比如窗口大小的改变。Application 还提供了日志输出系统和各种查询方法，例如内存的使用情况，操作系统版本信息，获取系统剪贴板等。
Gdx.files : 暴露平台底层的文件系统，提供统一的对文件操作的抽象接口。
Gdx.input : 通知 API 客户端用户的输入，例如鼠标点击，键盘按键按下，触摸屏幕和传感器事件。同时还支持轮询和事件驱动处理。
Gdx.net : 提供一个跨平台的通过 HTTP/HTTPS 访问资源的方法，还可以创建 TCP 服务和客户端 sockets。
Gdx.audio : 可用于创建音效和音乐实例，提供重放音效和音乐的方法，同时可以直接访问 PCM 音频的输入输出设备。
Gdx.graphics : 暴露 OpenGL ES 2.0 接口。还提供查询帧率，渲染时间步，获取屏幕宽高等方法。

## 生命周期

create()： 当应用被创建时调用一次。
resize(int width, int height)： 游戏屏幕尺寸改变并且不处于暂停状态将被调用，在 create() 方法之后也会被调用一次。
render()： ApplicationListener 中的游戏循环渲染方法，每时每刻都在被调用。游戏逻辑的更新通常也是在这个方法中被执行。
pause()： 当游戏界面被新的一个界面覆盖时（例如按下 Home 键回到主界面时被主界面覆盖，来电时被来电界面覆盖），该方法将被调用。通常在这里保存暂停时的游戏状态。
resume()： 被其他界面覆盖后（pause 状态后），重新回到游戏界面时，该方法被调用。
dispose()： 当应用被销毁时调用。

<img src="生命周期.jpg" style="zoom:50%;" />



## 主要类



Texture(纹理) : 解码一个图片文件并加载到 GPU 内存，可以代表为一张图片

```java
Texture img = new Texture("badlogic.jpg");
// 获取图片的长和宽
int height = img.getHeight();
int width = img.getWidth();
// 销毁
img.dispose();
```



SpriteBatch(精灵批处理) : 将纹理绘制到屏幕上，坐标原点为屏幕左下角，X 轴正方向水平向左，Y 轴正方向水平向上

```java
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpriteBatchStudy extends ApplicationAdapter {
    
	SpriteBatch batch;
    
	Texture img;
	
	@Override
	public void create () {
        // 初始化
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
    }

	@Override
	public void render () {
        // 清屏
		ScreenUtils.clear(0, 0, 0, 0);
        // 开始
		batch.begin();
        // 绘制
		batch.draw(img, 0, 0);
        // 结束
		batch.end();
	}
	
	@Override
	public void dispose () {
        // 销毁
		batch.dispose();
		img.dispose();
	}
}
```



 Pixmap 支撑加载图片，像素和绘制图形

```java
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
```



TextureRegion(纹理区域）表示 Texture(纹理）的一部分矩形区域，坐标原点为Texture左上角，X 轴正方向水平向左，Y 轴正方向水平向下

```java
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class TextureRegionStudy extends ApplicationAdapter {

    SpriteBatch batch;

    Texture img;

    TextureRegion imgRegion;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        imgRegion = new TextureRegion(img, 0, 0, 128, 128);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(imgRegion, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
```

