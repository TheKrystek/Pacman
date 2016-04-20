package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by student on 2016-04-10.
 */
public class Path extends Wall {


    Integer north;
    Integer south;
    Integer east;
    Integer west;
    private BitmapFont font = new BitmapFont();
    private BitmapFont font2 = new BitmapFont();

    public Path(int nodeId, Vector2 position) {
        super(nodeId, position);
        setColor(Color.PINK);
        font2.getData().setScale(0.7f);
        font.getData().setScale(0.9f);
    }


    @Override
    public void draw(Batch batch) {
        if (north != null)
            font2.draw(batch, String.valueOf(north), position.x, position.y + Wall.SIZE / 2 + 19);
        if (east != null)
            font2.draw(batch, String.valueOf(east), position.x + 22, position.y + Wall.SIZE / 2 + 19);
        if (west != null)
            font2.draw(batch, String.valueOf(west), position.x, position.y + Wall.SIZE / 2 - 10);
        if (south != null)
            font2.draw(batch, String.valueOf(south), position.x + 22, position.y + Wall.SIZE / 2 - 10);
        font.draw(batch, String.valueOf(nodeId), position.x + 10, position.y + Wall.SIZE / 2 + 5);
    }


    public Integer getNorth() {
        return north;
    }

    public void setNorth(Integer nodeId) {
        if (nodeId > 0)
            this.north = nodeId;
    }

    public Integer getSouth() {
        return south;
    }

    public void setSouth(Integer nodeId) {
        if (nodeId > 0)
            this.south = nodeId;
    }

    public Integer getEast() {
        return east;
    }

    public void setEast(Integer nodeId) {
        if (nodeId > 0)
            this.east = nodeId;
    }

    public Integer getWest() {
        return west;
    }

    public void setWest(Integer nodeId) {
        if (nodeId > 0)
            this.west = nodeId;
    }

    @Override
    protected void init() {
        shape = new Rectangle(position.x, position.y, SIZE, SIZE);
    }

    @Override
    protected boolean collides(MapElement<?> element) {
        return false;
    }


}
