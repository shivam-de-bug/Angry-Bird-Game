
package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Pig {
    protected Texture texture;
    protected Vector2 position;
    protected Vector2 velocity;
    protected float scale;
    protected float health;
    protected Rectangle bounds;
    protected boolean isDestroyed;
    protected PigType type;
    private Texture originalTexture;
    private Texture hurtTexture;
    public boolean hasBeenHit = false;




    public enum PigType {
        SMALL(10),
        MEDIUM(20),
        LARGE(30);

        private final int health;

        PigType(int health) {
            this.health = health;
        }

        public int getHealth() {
            return health;
        }
    }

    public Pig(Texture texture, float x, float y, float scale, PigType type) {
        this.texture = texture;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.scale = scale;
        this.type = type;
        this.health = type.getHealth();
        this.isDestroyed = false;
        this.originalTexture = texture;
        this.hurtTexture = new Texture("hurt.png");
        updateBounds();
    }
public void setTexture(String makedifficult) {
    if (hasBeenHit  ) {
        this.texture = hurtTexture;
        //this.scale += 0.007f;
        if(type==PigType.SMALL){
            this.scale=0.23f;
        }
        else if(this.type==PigType.MEDIUM){
            this.scale=0.35f;
        } else if (this.type==PigType.LARGE) {
            this.scale=0.47f;

        }
//        if(makedifficult.equals(("YES"))){
//            this.scale += 0.0001f;
////            this.health=health+0.05f;
//
//        }
        hasBeenHit = true;
    }
//    if(makedifficult.equals(("YES"))){
//        this.scale += 0.001f;
//        this.health=health+0.0000000000000000000005f;
//        hasBeenHit = true;
//    }
}

    public boolean isSupported(Array<BuildingBlock> blocks, float groundLevel) {
    Rectangle pigFootprint = new Rectangle(
        bounds.x,
        bounds.y - 1,
        bounds.width,
        1
    );

    for (BuildingBlock block : blocks) {
        if (block.isDestroyed()) continue;
        if (block.getBounds().overlaps(pigFootprint)) {
            return true;
        }
    }

    return position.y <= groundLevel;
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


    public boolean isDestroyed() {
        return isDestroyed;
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
    public Texture getPigTexture() {
        return texture;
    }



    public float getCurrentHealth() {
        return health;
    }

    public void newposition(float deltaTime, Array<BuildingBlock> blocks, float groundLevel) {
        if (!isDestroyed) {
            velocity.y -= 980f * deltaTime;

            velocity.x *= 0.95f;

            position.x += velocity.x * deltaTime;
            position.y += velocity.y * deltaTime;

            if (!isSupported(blocks, groundLevel)) {
                velocity.y -= 980f * deltaTime;
            } else {
                position.y = Math.max(groundLevel, getHighestSupportLevel(blocks));
                velocity.y = 0;
            }

            if (position.y < groundLevel) {
                position.y = groundLevel;
                velocity.y = 0;
            }

            updateBounds();

            if (health <= 0) {
                isDestroyed = true;
            }
        }
    }

    private float getHighestSupportLevel(Array<BuildingBlock> blocks) {
        float highestLevel = 0;
        Rectangle pigFootprint = new Rectangle(
            bounds.x,
            bounds.y - 1,
            bounds.width,
            1
        );

        for (BuildingBlock block : blocks) {
            if (block.isDestroyed()) continue;

            Rectangle blockBounds = block.getBounds();
            if (blockBounds.overlaps(pigFootprint)) {
                highestLevel = Math.max(highestLevel, blockBounds.y + blockBounds.height);
            }
        }

        return highestLevel;
    }



    // Modify takeDamage based on pig type and impact force
    public void takeDamage(float damage) {
        // Reduce damage based on pig type
        float reducedDamage;
        switch (type) {
            case SMALL:
                reducedDamage = damage;
                break;
            case MEDIUM:
                reducedDamage = damage * 0.7f;
                break;
            case LARGE:
                reducedDamage = damage * 0.5f;
                break;
            default:
                reducedDamage = damage;
        }

        health -= reducedDamage;
//        if (health <= 0) {
//            health = 0;
//        }
        if (health > 0) {
            setTexture("NO");
        }

        if (health <= 0) {
            health = 0;
        }
    }

    public void dispose() {
        originalTexture.dispose();
        hurtTexture.dispose();
    }
}
