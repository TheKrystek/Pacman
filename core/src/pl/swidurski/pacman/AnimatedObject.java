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
    Animation animation;
    Animation alternativeAnimation;
    SpriteBatch spriteBatch;
    float stateTime;
    long counter = 0;
    boolean blink = false;
    private String secondFilename;
    private float duration = 0.1f;
    private int width;
    private int height;

    public AnimatedObject(final String filename) {
        this.filename = filename;
        this.animation = createAnimation(filename);
    }


    public AnimatedObject(final String filename, final String secondFilename) {
        this(filename);
        this.secondFilename = secondFilename;
        alternativeAnimation = createAnimation(secondFilename);
    }

    public void setDuration(float duration) {
        this.duration = duration;
        this.animation = createAnimation(filename);
        if (secondFilename != null)
            this.alternativeAnimation = createAnimation(secondFilename);
    }

    public void setBlinking(boolean blink) {
        this.blink = blink;
        setDuration(duration);
    }

    public Animation createAnimation(String filename) {
        Texture sheet = new Texture(Gdx.files.internal(filename));
        width = sheet.getWidth() / FRAME_COLS;
        height = sheet.getHeight() / FRAME_ROWS;
        TextureRegion[][] tmp = TextureRegion.split(sheet, width, height);
        TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++)
            for (int j = 0; j < FRAME_COLS; j++)
                frames[index++] = tmp[i][j];
        Animation animation = new Animation(duration, frames);
        stateTime = 0f;
        return animation;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TextureRegion getFrame() {
        counter++;
        stateTime += Gdx.graphics.getDeltaTime();

        if (!blink || alternativeAnimation == null)
            return animation.getKeyFrame(stateTime, true);

        if (counter % 20 < 10)
            return animation.getKeyFrame(stateTime, true);
        return alternativeAnimation.getKeyFrame(stateTime, true);
    }
}

