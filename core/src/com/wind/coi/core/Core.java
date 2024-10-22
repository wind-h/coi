package com.wind.coi.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

/**
 * @author hsc
 * @date 2024/10/22 13:56
 */
public class Core extends Gdx {

   public static AssetManager assets;

   static {
      assets = new AssetManager();
   }
}
