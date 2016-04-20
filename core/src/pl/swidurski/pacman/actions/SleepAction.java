package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Krystek on 2016-04-12.
 */

public class SleepAction implements Action {

    long time;
    Timer timer = new Timer();
    boolean started = false;


    public SleepAction(long time) {
        this.time = time;
    }


    private void startTimer(MovableObject source) {
        if (started)
            return;
        started = true;
        source.setSpeed(0);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                source.setSpeed(source.defaultSpeed);
            }
        }, time * 1000);
    }


    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (!started)
            startTimer(source);
    }
}

