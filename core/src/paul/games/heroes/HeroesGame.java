package paul.games.heroes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.ibm.dtfj.java.javacore.JCInvalidArgumentsException;
import paul.games.heroes.screens.MainScreen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class HeroesGame extends Game {
	// Collection of screens to reference for changeScreen(String screenId)
	public enum Screens {
		MAIN_SCREEN(MainScreen.class, "MainScreen");

		private final Class<? extends Screen> screenClass;
		Constructor<? extends Screen> constructor = null;
		private final String screenId;

		Screens(Class<? extends Screen> screenClass, String screenId) {
			this.screenClass = screenClass;
			this.screenId = screenId;
		}

		public Class<? extends Screen> getScreenClass() { return this.screenClass; }

		public String getScreenId() {
			return this.screenId;
		}

		public Screen create(HeroesGame heroesGame) {
			try {
				return getScreenClass().getDeclaredConstructor(new Class[]{HeroesGame.class}).newInstance(heroesGame);
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
				throw new InternalError(e);
			}
		}
	}
	// Loops over collection and sets screen, if screenId was valid
	public void changeScreen(String screenId) {
		for(Screens s : Screens.values()) {
			if (s.screenId.equals(screenId)) {
				Screen screen = s.create(this);
				System.out.println("Switched to screen: " + screenId + ".");
				this.setScreen(screen);
			} else {
				throw new IllegalArgumentException("Screen with screenId: " + screenId + " does not exist!");
			}
		}
	}
	public boolean isSameScreen(Screen s, String screenId) {
		return (s.getClass().getName().endsWith(screenId));
	}

	@Override
	public void create() {
		changeScreen(Screens.MAIN_SCREEN.screenId);
	}

}
