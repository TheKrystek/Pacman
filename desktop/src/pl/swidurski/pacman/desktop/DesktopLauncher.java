package pl.swidurski.pacman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.swidurski.pacman.Pacman;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Title";
		config.height = 900;
		config.width = 1000;
		new LwjglApplication(new Pacman(), config);
	}
}
