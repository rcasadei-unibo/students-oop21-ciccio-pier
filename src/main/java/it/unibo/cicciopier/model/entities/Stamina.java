package it.unibo.cicciopier.model.entities;

/**
 * Simple class to store all stamina constants
 */
public class Stamina {
    /**
     * Represents the player maximum stamina
     */
    public static final int MAX_PLAYER = 200;
    /**
     * Represents the minimum stamina that the junk food will give
     */
    public static final int MIN_JUNK_FOOD = 30;
    /**
     * Represents the maximum stamina that the junk food will give
     */
    public static final int MAX_JUNK_FOOD = 60;
    /**
     * Represents how much the junk food will damage the player health
     */
    public static final int JUNK_FOOD_DAMAGE = 5;
    /**
     * Represents the minimum stamina that the healthy food will give
     */
    public static final int MIN_HEALTHY_FOOD = 10;
    /**
     * Represents the maximum stamina that the healthy food will give
     */
    public static final int MAX_HEALTHY_FOOD = 30;
    /**
     * Represents how much the healthy food will heal the player
     */
    public static final int HEALTHY_FOOD_HEAL = 40;

    /**
     * Represents the minimum stamina needed to not get fatigued
     */
    public static final int FATIGUE = Stamina.MAX_PLAYER / 4;
    /**
     * Represents how much the jump will be affected if the player is fatigued
     */
    public static final double FATIGUE_JUMP_DEBUFF = 0.6D;
    /**
     * Represents how much the speed will be affected if the player is fatigued
     */
    public static final double FATIGUE_SPEED_DEBUFF = 0.75D;
    /**
     * Represents how much the stamina will be decreased when the player jumps
     */
    public static final int JUMP_DECREASE = 2;
    /**
     * Represents how much the health will be decreased if the stamina is 0
     */
    public static final int HEALTH_DECREASE = 10;
}
