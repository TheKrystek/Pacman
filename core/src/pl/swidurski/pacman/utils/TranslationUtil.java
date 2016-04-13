package pl.swidurski.pacman.utils;

import pl.swidurski.pacman.Const;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Krystek on 2016-04-09.
 */
public class TranslationUtil {
    private static final String prefix = "lang";
    private static Locale currentLocale = new Locale("pl");
    private static ResourceBundle currentBundle = null;

    public static String translate(String translationKey) {
        if (currentBundle == null)
            changeLanguage(currentLocale);

        if (currentBundle.containsKey(translationKey))
            return currentBundle.getString(translationKey);
        return translationKey;
    }

    public static void changeLanguage(String language, String country) {
        changeLanguage(new Locale(language, country));
    }

    public static void changeLanguage(Locale locale) {
        currentLocale = locale;
        currentBundle = ResourceBundle.getBundle(prefix, currentLocale, getClassLoader());
    }

    private static ClassLoader getClassLoader() {
        File file = new File(Const.PATHS_LANGS);
        URL[] urls = new URL[0];
        try {
            urls = new URL[]{file.toURI().toURL()};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new URLClassLoader(urls);
    }
}
