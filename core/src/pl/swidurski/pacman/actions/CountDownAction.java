package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.game.Scorer;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Krystek on 2016-04-12.
 */

public class CountDownAction implements Action {

    private final String text;
    long times;
    Timer timer = new Timer();
    boolean started = false;


    public CountDownAction(long time, String text) {
        this.times = time;
        this.text = text;
    }


    private void startTimer(MovableObject source, Map map) {
        if (started)
            return;
        started = true;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                map.getScorer().setText(String.valueOf(times--));

                // Dla zera wyświetl tekst
                if (times == 0)
                    map.getScorer().setText(text);

                // Zatrzymaj odliczanie i wyczyśc tekst
                if (times < 0) {
                    map.getScorer().setText(null);
                    cancel();
                }
            }
        }, 0, 1000);
    }


    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (!started)
            startTimer(source,map);
    }
}

