package pl.swidurski.pacman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by student on 2016-04-10.
 */
public class AnimatedObject {


    private final int FRAME_COLS = 2;
    private final int FRAME_ROWS = 2;
    private final String filename;

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
        createAnimation();
    }

    private float duration = 0.1f;

    Animation animation;
    Texture sheet;
    TextureRegion[] frames;
    SpriteBatch spriteBatch;
    TextureRegion currentFrame;
    float stateTime;
    private int width;
    private int height;


    public AnimatedObject(final String filename) {
        this.filename = filename;
        createAnimation();
    }

    private void createAnimation() {
        sheet = new Texture(Gdx.files.internal(filename));
        width = sheet.getWidth() / FRAME_COLS;
        height = sheet.getHeight() / FRAME_ROWS;
        TextureRegion[][] tmp = TextureRegion.split(sheet, width, height);
        frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++)
            for (int j = 0; j < FRAME_COLS; j++)
                frames[index++] = tmp[i][j];
        animation = new Animation(duration, frames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
        System.out.println(filename + " duration " + duration);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TextureRegion getFrame() {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, true);
        return currentFrame;
    }
}

