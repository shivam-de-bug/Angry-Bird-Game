package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class BuildingBlock {
    protected Texture texture;
    protected Vector2 position;
    protected Vector2 velocity;
    protected float scale;
    protected float health;
    protected Rectangle bounds;
    protected boolean isDestroyed;
    protected BlockType type;
    protected boolean isVertical;
    protected boolean isSettled;
    public boolean ishit;
    protected String newtexturepath;
    public int pathno;

    public enum BlockType {
        WOOD(5),
        GLASS(1),
        STONE(3);

        private final int durability;

        BlockType(int durability) {
            this.durability = durability;
        }

        public int getDurability() {
            return durability;
        }
    }

    public BuildingBlock(Texture texture, float x, float y, float scale, BlockType type, boolean vertical,String newtexturepath,int pathno) {
        this.texture = texture;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.scale = scale;
        this.type = type;
        this.health = type.getDurability();
        this.isDestroyed = false;
        this.isSettled = false;
        updateBounds();
        this.isVertical = vertical;
        this.newtexturepath=newtexturepath;
    this.pathno=pathno;
        this.ishit=false;

    }

    protected void updateBounds() {
        float width = texture.getWidth() * scale;
        float height = texture.getHeight() * scale;
        if (bounds == null) {
            bounds = new Rectangle(position.x, position.y, width, height);
        } else {
            bounds.set(position.x, position.y, width, height);
        }
    }
    public void settexture(){
        ishit=true;

        this.texture=new Texture(newtexturepath);

       if(this.pathno==1){
           this.pathno=7;
       }else if(this.pathno==2){
           this.pathno=8;
       } else if(this.pathno==3){
           this.pathno=9;
       }else if(this.pathno==4){
           this.pathno=10;
       }else if(this.pathno==5){
           this.pathno=11;
       }else if(this.pathno==6){
           this.pathno=12;
       }

    }
    public boolean ishit(){
        return this.ishit;
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getScale() {
        return scale;
    }

    public void dispose() {
        texture.dispose();
    }

    public Texture getBlockTexture() {
        return texture;
    }

    public float getCurrentHealth() {
        return health;
    }

    public void takeDamage(float damage) {
        // Reduce damage based on block type
        float reducedDamage;
        switch (type) {
            case WOOD:
                reducedDamage = damage;
                break;
            case GLASS:
                reducedDamage = damage * 0.7f;
                break;
            case STONE:
                reducedDamage = damage * 0.5f;
                break;
            default:
                reducedDamage = damage;
        }

        health -= reducedDamage;
        if (health <= 0) {
            health = 0;

            isDestroyed = true;
        }
    }


    public boolean isSupported(Array<BuildingBlock> allBlocks, float groundLevel) {
        Rectangle supportCheckBounds = new Rectangle(
            bounds.x,
            bounds.y - 1,
            bounds.width,
            1
        );

        for (BuildingBlock block : allBlocks) {
            if (block == this || block.isDestroyed) continue;


            if (block.getBounds().overlaps(supportCheckBounds)) {
                return true;}

                    if (Math.abs(this.position.x + this.bounds.width / 2 - block.position.x) < block.bounds.width / 2 &&
                        Math.abs(this.position.y - (block.position.y + block.bounds.height)) < 1) {
                        return true;
                    }


        }

        // Supported by ground
        return position.y <= groundLevel;
    }


    public void checkAndUpdateSettlement(Array<BuildingBlock> allBlocks, float groundLevel) {
        // If block is not supported, it will fall
        if (!isSupported(allBlocks, groundLevel)) {
            isSettled = false;
            velocity.y = -980f; // Start falling
        } else {
            isSettled = true;
            velocity.y = 0; // Stop falling
        }
    }

    public void update(float deltaTime, Array<BuildingBlock> allBlocks, float groundLevel) {
        if (!isDestroyed) {
            // Prevent blocks from overlapping
            preventOverlap(allBlocks);

            // Check if supported
            checkAndUpdateSettlement(allBlocks, groundLevel);

            // Apply gravity if not settled
            if (!isSettled) {
                velocity.y -= 980f * deltaTime;
            }

            // Update position
            position.x += velocity.x * deltaTime;
            position.y += velocity.y * deltaTime;

            // Prevent going below ground
            if (position.y <= groundLevel) {
                position.y = groundLevel;
                velocity.y = 0;
                isSettled = true;
            }

            updateBounds();
        }
    }

    private void preventOverlap(Array<BuildingBlock> allBlocks) {
        for (BuildingBlock otherBlock : allBlocks) {
            if (otherBlock == this || otherBlock.isDestroyed) continue;

            // Check for overlap
            if (this.bounds.overlaps(otherBlock.bounds)) {
                // Separate blocks vertically
                if (this.position.y < otherBlock.position.y) {
                    this.position.y = otherBlock.position.y - this.bounds.height;
                } else {
                    this.position.y = otherBlock.position.y + otherBlock.bounds.height;
                }

                // Stop vertical movement
                this.velocity.y = 0;
                updateBounds();
            }
        }
    }
}
