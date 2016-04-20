package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.Doors;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;
import pl.swidurski.pacman.map.elements.StaticElement;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Krystek on 2016-04-12.
 */

public class HideDoorsAction implements Action {

    long time;
    Timer timer = new Timer();
    boolean started = false;


    public HideDoorsAction(long time) {
        this.time = time;
    }


    private void startTimer(MovableObject source, MapElement<?> element, Map map) {
        if (started)
            return;
        started = true;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (StaticElement<?> object : map.getStaticObjects()) {
                    if (object instanceof Doors){
                        ((Doors)object).setOpen(true);
                        break;
                    }
                }
            }
        }, time * 1000);
    }


    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (!started)
            startTimer(source, element, map);
    }
}

