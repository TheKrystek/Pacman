package pl.swidurski.pacman.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import pl.swidurski.pacman.Const;
import pl.swidurski.pacman.SoundManger;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;

/**
 * Created by Krystek on 2016-04-19.
 */
public class StartGameAction implements Action {
    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        for (MovableObject object : map.getMovableObjects())
            new SleepAction(3).execute(object, null, map);
        new CountDownAction(3, "Go!").execute(null, null, map);
        new HideDoorsAction(3).execute(null, null, map);
        SoundManger.playIntro();

    }
}
