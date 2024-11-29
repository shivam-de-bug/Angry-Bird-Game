
package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Bird {
    protected Texture birdTexture;
    protected Vector2 position;
    protected Vector2 velocity;
    protected float scale;
     public boolean launched;
    protected float power;
    protected Rectangle bounds;
    protected boolean isSpecial;



    public Bird(Texture texture, float x, float y, float scale, float power) {
        this.birdTexture = texture;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.scale = scale;
        this.power = power;
        this.launched = false;
        this.isSpecial = false;
        newBounds();
    }

    public void launch(Vector2 initialVelocity) {
        this.velocity.set(initialVelocity);
        this.launched = true;
    }

    public void update(float deltaTime) {
        if (launched) {
            velocity.y -= 980f * deltaTime;

            position.x += velocity.x * deltaTime;
            position.y += velocity.y * deltaTime;

            velocity.x *= 0.99f;
            velocity.y *= 0.99f;

            newBounds();
        }
    }

    protected void newBounds() {
        float width = birdTexture.getWidth() * scale;
        float height = birdTexture.getHeight() * scale;
        if (bounds == null) {
            bounds = new Rectangle(position.x, position.y, width, height);
        } else {
            bounds.set(position.x, position.y, width, height);
        }
    }

    public void useSpecial() {
        if (!isSpecial) {
            isSpecial = true;
        }
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
        newBounds();
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    public float getPower() {
        return power;
    }

    public Texture getBirdTexture() {
        return birdTexture;
    }

    public float getScale() {
        return scale;
    }

    public void dispose() {
        birdTexture.dispose();
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getDamage() {
        return velocity.x*0.001f;
//        return 0;
    }

}

