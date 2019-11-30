package sample;

import sample.Entity.Map.Spawner;
import sample.Entity.Map.Target;

public class Config {
    public static final String[][] MAP_SPRITES = new String[][] {
            { "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2"},
            { "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2"},
            { "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2"},
            { "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "9", "2", "2"},
            { "0", "1", "1", "1", "2", "4", "5", "2", "1", "1", "1", "1", "1", "1", "1", "1", "2", "2", "2", "2", "2"},
            { "2", "2", "2", "1", "2", "2", "2", "2", "1", "2", "2", "2", "2", "2", "2", "1", "2", "2", "2", "7", "2"},
            { "2", "2", "2", "1", "2", "2", "2", "2", "1", "2", "2", "5", "2", "2", "2", "1", "2", "2", "2", "2", "4"},
            { "2", "2", "2", "1", "2", "2", "2", "2", "1", "2", "2", "2", "2", "2", "2", "1", "2", "2", "2", "2", "2"},
            { "2", "2", "2", "1", "2", "2", "2", "2", "1", "2", "2", "2", "1", "1", "1", "1", "2", "2", "2", "2", "2"},
            { "3", "2", "2", "1", "2", "2", "2", "2", "1", "2", "2", "2", "1", "2", "2", "2", "2", "2", "2", "2", "2"},
            { "2", "3", "2", "1", "1", "1", "1", "2", "1", "2", "2", "2", "1", "2", "2", "2", "2", "2", "2", "2", "2"},
            { "2", "2", "2", "2", "2", "2", "1", "2", "1", "2", "2", "2", "1", "2", "2", "2", "2", "1", "1", "1", "10"},
            { "3", "2", "2", "2", "2", "2", "1", "2", "1", "2", "2", "2", "1", "2", "2", "2", "2", "1", "2", "2", "2"},
            { "2", "2", "2", "2", "2", "2", "1", "1", "1", "2", "2", "2", "1", "1", "1", "1", "1", "1", "2", "2", "2"},
            { "2", "2", "3", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2"},
            { "8", "2", "2", "8", "2", "2", "2", "2", "2", "2", "6", "2", "2", "2", "2", "2", "2", "3", "2", "2", "7"}
    };
    public static final int SCREEN_WIDTH = MAP_SPRITES[0].length * 60;
    public static final int SCREEN_HEIGHT = MAP_SPRITES.length * 60;
    public static final int TILE_SIZE = 60;
    public static Spawner spawner = new Spawner();
    public static Target target = new Target();

    public static final int MACHINE_GUN_RANGE = 120;
    public static final int MACHINE_GUN_SPEED = 2;
    public static final int MACHINE_GUN_DAMAGE = 3;

    public static final int NORMAL_TOWER_RANGE = 180;
    public static final int NORMAL_TOWER_SPEED = 3;
    public static final int NORMAL_TOWER_DAMAGE = 3;

    public static final int SNIPER_TOWER_RANGE = 300;
    public static final int SNIPER_TOWER_SPEED = 2;
    public static final int SNIPER_TOWER_DAMAGE = 5;

    public static final int NORMAL_ENEMY_HEALTH = 2;
    public static final int NORMAL_ENEMY_ARMOR = 1;
    public static final int NORMAL_ENEMY_SPEED = 4;
    public static final int NORMAL_ENEMY_REWARD = 2;

    public static final int TANK_ENEMY_HEALTH = 2;
    public static final int TANK_ENEMY_ARMOR = 2;
    public static final int TANK_ENEMY_SPEED = 5;
    public static final int TANK_ENEMY_REWARD = 2;

    public static final int SMALL_ENEMY_HEALTH = 2;
    public static final int SMALL_ENEMY_ARMOR = 0;
    public static final int SMALL_ENEMY_SPEED = 3;
    public static final int SMALL_ENEMY_REWARD = 1;

    public static final int BOSS_ENEMY_HEALTH = 3;
    public static final int BOSS_ENEMY_ARMOR = 3;
    public static final int BOSS_ENEMY_SPEED = 6;
    public static final int BOSS_ENEMY_REWARD = 5;
}
