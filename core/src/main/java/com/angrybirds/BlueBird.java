package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
public class BlueBird extends Bird {
    private boolean split = false;
    private Array<BlueBird> copies;
   public  boolean isCopy;
    public BlueBird(Texture texture, float x, float y, float scale,boolean iscopy) {
        super(texture, x, y, scale, 1.0f);
        copies = new Array<>();
        this.isCopy=isCopy;
    }

    @Override
    public void useSpecial() {
        if (!isSpecial && !split) {
            split = true;
            super.useSpecial();
        }
    }
    public boolean isCopy() {
        return isCopy;
    }
    public void createCopies(AngryBirdsGame game) {
        if (split && copies.size == 0) {
            // Create two copies with slightly offset velocities
            BlueBird copy1 = new BlueBird(game.bluebirdTexture, position.x, position.y, scale,true);
            BlueBird copy2 = new BlueBird(game.bluebirdTexture, position.x, position.y, scale,true);

            // Set velocities for the copies
            Vector2 leftOffset = new Vector2(velocity).rotate(15);
            Vector2 rightOffset = new Vector2(velocity).rotate(-15);

            copy1.launch(leftOffset);
            copy2.launch(rightOffset);

            copies.add(copy1);
            copies.add(copy2);
        }
    }
    public Array<BlueBird> getCopies() {
        return copies;
    }
    public void setCopy(boolean copy) {
        isCopy = copy;
    }
    public boolean hasSplit() {
        return split;
    }
}
