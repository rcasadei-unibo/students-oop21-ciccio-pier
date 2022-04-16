package it.unibo.cicciopier.model.settings;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class generate the instance for the font used in the game
 */
public class CustomFont {
    private static final CustomFont INSTANCE = new CustomFont();
    private Font font;

    /**
     * This function loads the font from the resources
     */
    public void load() throws IOException, FontFormatException {
        final InputStream is = getClass().getResourceAsStream("/font/font.ttf");
        if (is == null) {
            throw new NullPointerException("Font not found");
        }
        this.font = Font.createFont(Font.TRUETYPE_FONT, is);
    }


    /**
     * This function returns the loaded font if found while loading, if not it will return a default font
     *
     * @return the font loaded or a default font if the loaded is not found
     */
    public Font getFontOrDefault() {
        return this.font == null ? Font.getFont("serif").deriveFont(Font.PLAIN, Screen.scale(20)) : this.font.deriveFont(Font.PLAIN, Screen.scale(20));
    }

    /**
     * This function return the instance of this singleton class
     *
     * @return the instance of the class
     */
    public static CustomFont getInstance() {
        return CustomFont.INSTANCE;
    }
}
