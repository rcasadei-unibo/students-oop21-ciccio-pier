package it.unibo.cicciopier.model;

import java.util.HashSet;
import java.util.Set;

public class Level {

    private static final Set<Level> LEVELS = new HashSet<>();
    public static final Level FIRST_LEVEL = new Level("level-1-1.tmx","level_1","Level 1");
    public static final Level SECOND_LEVEL = new Level("level-1-2.tmx","level_2","Level 2");
    public static final Level THIRD_LEVEL = new Level("level-1-3.tmx","level_3","Level 3");
    public static final Level BOSS_LEVEL = new Level("level-1-4.tmx","level_4","BOSS Level");
    private final String fileName;
    private final String jsonId;
    private final String name;

    public Level(String fileName, String jsonId, String name) {
        this.fileName = fileName;
        this.jsonId = jsonId;
        this.name = name;
        LEVELS.add(this);
    }

    public String getFileName() {
        return fileName;
    }

    public String getJsonId() {
        return jsonId;
    }

    public String getName() {
        return name;
    }
    public static Set<Level> getLevels(){
        return Level.LEVELS;
    }
}
