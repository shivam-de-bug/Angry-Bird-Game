package com.angrybirds;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class WoodBlock extends BuildingBlock {
    public WoodBlock(Texture texture, float x, float y, float scale,boolean vertical,String path,int pathno) {
        super(texture,x,y,scale,BlockType.WOOD,vertical,path,pathno);
    }

}
