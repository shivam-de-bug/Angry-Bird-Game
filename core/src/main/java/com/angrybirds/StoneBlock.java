package com.angrybirds;
import com.angrybirds.BuildingBlock;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class StoneBlock extends BuildingBlock {
    public StoneBlock(Texture texture, float x, float y, float scale,boolean vertical,String path,int pathno) {
        super(texture,x,y,scale,BlockType.STONE,vertical,path,pathno);
    }
}
