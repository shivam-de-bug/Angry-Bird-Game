package com.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.mockito.Mockito;

public class junit {
    private static HeadlessApplication application;
    private static GL20 gl;

    @BeforeClass
    public static void setUp() {
        // Mock the GL20 context to avoid null pointer exceptions
        gl = Mockito.mock(GL20.class);
        Gdx.gl = gl;
        Gdx.gl20 = gl;

        // Configure and create headless application
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(null, config);
    }

    @Test
    public void testBuildingBlockCreation() {
        // Create a mock pixmap and texture safely
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(1f, 0f, 0f, 1f);
        pixmap.fill();

        Texture mockTexture = null;
        try {
            mockTexture = new Texture(pixmap);

            BuildingBlock verticalBlock = new BuildingBlock(
                mockTexture,
                100f,
                200f,
                0.5f,
                BuildingBlock.BlockType.WOOD,
                true,
                "blue_bird.png",
                1
            );

            assertTrue(verticalBlock.isVertical());
            assertEquals(5f, verticalBlock.getCurrentHealth(), 0.01f);
        } finally {
            if (mockTexture != null) {
                mockTexture.dispose();
            }
            pixmap.dispose();
        }
    }
    @Test
    public void testBuildingBlockDamage() {
        // Create a mock pixmap and texture safely
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(1f, 0f, 0f, 1f);
        pixmap.fill();

        Texture mockTexture = null;
        try {
            mockTexture = new Texture(pixmap);

            // Create blocks of different types
            BuildingBlock woodBlock = new BuildingBlock(
                mockTexture, 100f, 200f, 0.5f,
                BuildingBlock.BlockType.WOOD, false, "blue_bird.png", 1
            );
            BuildingBlock glassBlock = new BuildingBlock(
                mockTexture, 200f, 200f, 0.5f,
                BuildingBlock.BlockType.GLASS, false, "blue_bird.png", 1
            );
            BuildingBlock stoneBlock = new BuildingBlock(
                mockTexture, 300f, 200f, 0.5f,
                BuildingBlock.BlockType.STONE, false, "blue_bird.png", 1
            );

            // Test damage reduction for different block types
            woodBlock.takeDamage(10f);
            glassBlock.takeDamage(0f);
            stoneBlock.takeDamage(10f);

            assertEquals("Wood block should take full damage", 0, woodBlock.getCurrentHealth(), 0.01f);

            assertEquals("Glass block should take reduced damage", 1f , glassBlock.getCurrentHealth(), 0.01f);

            assertEquals("Stone block should take minimal damage", 0, stoneBlock.getCurrentHealth(), 0.01f);
        } finally {
            if (mockTexture != null) {
                mockTexture.dispose();
            }
            pixmap.dispose();
        }
    }


    @Test
    public void testBlockSettlement() {
        // Create a mock pixmap and texture safely
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(1f, 0f, 0f, 1f);
        pixmap.fill();

        Texture mockTexture = null;
        try {
            mockTexture = new Texture(pixmap);

            Array<BuildingBlock> blocks = new Array<>();

            BuildingBlock baseBlock = new BuildingBlock(
                mockTexture, 100f, 100f, 0.5f,
                BuildingBlock.BlockType.WOOD, false, "blue_bird.png", 1
            );
            blocks.add(baseBlock);

            BuildingBlock topBlock = new BuildingBlock(
                mockTexture, 100f, 200f, 0.5f,
                BuildingBlock.BlockType.WOOD, false, "blue_bird.png", 1
            );
            blocks.add(topBlock);

            float groundLevel = 180f;

            topBlock.update(0.1f, blocks, groundLevel);

            assertTrue("Top block should be settled on base block", topBlock.isSupported(blocks, groundLevel));
        } finally {
            if (mockTexture != null) {
                mockTexture.dispose();
            }
            pixmap.dispose();
        }
    }



    @AfterClass
    public static void tearDown() {
        if (application != null) {
            application.exit();
        }
    }
}
