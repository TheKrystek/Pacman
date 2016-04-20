package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.AnimatedObject;
import pl.swidurski.pacman.Const;
import pl.swidurski.pacman.actions.EatPacmanAction;
import pl.swidurski.pacman.actions.HuntAction;
import pl.swidurski.pacman.actions.SleepAction;
import pl.swidurski.pacman.actions.TeleportAction;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.Orientation;
import pl.swidurski.pacman.utils.GhostRotator;

/**
 * Created by Krystek on 2016-04-10.
 */
public class Ghost extends MovableObject implements Eatable {

    HuntAction movingAction;
    int offset;

    private boolean isEatable = false;

    public Ghost(int nodeId, String filename, Vector2 start, Map map) {
        super(nodeId, new AnimatedObject(filename, GhostColors.getFilename("transparent_ghost.png")), start);
        setMap(map);
        getObject().setDuration(1f);
        setRotator(new GhostRotator());
        setOrientation(Orientation.EAST);
    }

    public static Ghost createGhost(int nodeId, GhostColors ghostColor, Vector2 start, Map map) {
        Ghost ghost = new Ghost(nodeId, ghostColor.getFilename(), start, map);
        ghost.setSpeed(ghostColor.getSpeed());
        if (ghostColor.canUseTeleport())
            ghost.actions.add(new TeleportAction());
        ghost.setOffset(ghostColor.getOffset());
        ghost.movingAction = new HuntAction(ghost.getOffset());
        ghost.actions.add(new EatPacmanAction());
        return ghost;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public HuntAction getMovingAction() {
        return movingAction;
    }

    public void setMovingAction(HuntAction movingAction) {
        this.movingAction = movingAction;
    }

    @Override
    public int getPoints() {
        return 10;
    }

    @Override
    public boolean isEatable() {
        return isEatable;
    }

    public void setEatable(boolean eatable) {
        this.isEatable = eatable;
        getObject().setBlinking(eatable);
    }

    public Circle move() {
        switch (getOrientation()) {
            case NORTH:
                return moveUp(speed);
            case SOUTH:
                return moveDown(speed);
            case WEST:
                return moveLeft(speed);
            case EAST:
                return moveRight(speed);
        }
        return getShape();
    }

    @Override
    protected boolean collides(MapElement<?> element) {
        if (!(element instanceof Wall || element instanceof Doors || element instanceof PacmanObject || (element instanceof Teleport)))
            return false;
        return super.collides(element);
    }


    public enum GhostColors {
        BLUE("blue_ghost.png", true, 2, 0.9f),
        YELLOW("yellow_ghost.png", false, 5, 1.05f),
        RED("red_ghost.png", true, 0, 0.95f),
        PINK("pink_ghost.png", false, 0, 0.70f);

        private final String filename;
        private final boolean canUseTeleport;
        private final int offset;
        private final float speed;

        GhostColors(String filename, boolean canUseTeleport, int offset, float speed) {
            this.filename = filename;
            this.canUseTeleport = canUseTeleport;
            this.offset = offset;
            this.speed = speed;
        }

        public static String getFilename(String filename) {
            return String.format("%s%s", Const.PATHS_ASSETS, filename);
        }

        public boolean canUseTeleport() {
            return canUseTeleport;
        }

        public int getOffset() {
            return offset;
        }

        public float getSpeed() {
            return speed;
        }

        public String getFilename() {
            return GhostColors.getFilename(filename);
        }
    }
}
