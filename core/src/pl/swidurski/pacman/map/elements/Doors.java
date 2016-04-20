package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.Const;

/**
 * Created by student on 2016-04-10.
 */
public class Doors extends Wall {
    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    boolean open = false;


    public Doors(int nodeId, Vector2 position) {
        super(nodeId, position);
        setColor(new Color(Color.valueOf(Const.COLORS_DOORS)));
    }


    @Override
    protected void draw() {
        if (!open)
        shapeRenderer.rect(shape.x, shape.y, shape.getWidth(), shape.getHeight());
    }

    @Override
    protected boolean collides(MapElement<?> element) {
        if (open)
            return false;
        return super.collides(element);
    }
}
