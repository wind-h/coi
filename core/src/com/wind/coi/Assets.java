package com.wind.coi;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.wind.coi.constants.GlobalConstant;
import com.wind.coi.core.Core;

/**
 * @author hsc
 * @date 2024/10/24 15:44
 */
public class Assets implements Disposable, AssetErrorListener {

    public final static String TAG = Assets.class.getName();

    public final static Assets ASSETS = new Assets();

    private AssetManager assetManager;

    private Assets() {

    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        // 设置资源管理器的错误处理对象
        assetManager.setErrorListener(this);
        // 预加载纹理集资源
        assetManager.load(GlobalConstant.TEXTURE_ATLAS_OBJS, TextureAtlas.class);
        // 完成加载资源
        assetManager.finishLoading();
        Core.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);

    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {

    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
