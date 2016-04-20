package pl.swidurski.pacman.actions;

import com.badlogic.gdx.Gdx;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.Ghost;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;

import java.util.Timer;
import java.util.TimerTask;


public class ChangeBehaviourAction implements Action {

    long time;
    Timer timer = new Timer();
    boolean started = false;

    Action lastAction;

    public ChangeBehaviourAction(long time) {
        this.time = time;
        System.out.println("CHANGE ACTION");
    }


    private void startTimer(Map map) {
        if (started)
            return;
        start(map);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                map.getPacman().setSpeed(1f);
                for (Ghost ghost : map.getGhosts()) {
                    if (ghost.getMovingAction() instanceof RunAction)
                        ghost.setMovingAction(new HuntAction(ghost.getOffset()));
                    Gdx.app.postRunnable(() -> ghost.setEatable(false));
                }
            }
        }, time * 1000);
    }

    private void start(Map map) {
        started = true;
        map.getPacman().setSpeed(2f);
        // usun akcje polowania wszystkim duchom
        for (Ghost ghost : map.getGhosts()) {
            ghost.setMovingAction(new RunAction(ghost.getOffset()));
            Gdx.app.postRunnable(() -> ghost.setEatable(true));
        }
    }


    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (!started)
            startTimer(map);
    }
}

