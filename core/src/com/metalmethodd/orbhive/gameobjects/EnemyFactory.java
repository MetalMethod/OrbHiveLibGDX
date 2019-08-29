package com.metalmethodd.orbhive.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.gameobjects.enemies.AbstractEnemy;
import com.metalmethodd.orbhive.gameobjects.enemies.BrainSmall;
import com.metalmethodd.orbhive.gameobjects.enemies.SimpleEnemy;
import com.metalmethodd.orbhive.gameobjects.enemies.Wasp;

//TODO find solution to build html module using this import
//import java.util.concurrent.ThreadLocalRandom;

public class EnemyFactory {

    private int waspCount = 0;
    private int brainCount = 0;
    private int enemySimpleCount = 0;

    private int waspGroupCount = 0;

    public static int getRandomInt(int min, int max) {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        // return ThreadLocalRandom.current().nextInt(min, max + 1);

        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static Wasp createWasp() {
        float randomY = (float) (getRandomInt(10, Constants.GAME_HEIGHT-10));
        Vector2 position = new Vector2(Constants.GAME_WIDTH, randomY);

        return new Wasp(position);
    }

    public static SimpleEnemy createSimpleEnemy() {
        float randomY = (float) (getRandomInt(20, Constants.GAME_HEIGHT-20));
        Vector2 position = new Vector2(Constants.GAME_WIDTH, randomY);

        return new SimpleEnemy(position);
    }

    public static BrainSmall createBrain() {
        float randomY = (float) (getRandomInt(20, Constants.GAME_HEIGHT-20));
        Vector2 position = new Vector2(Constants.GAME_WIDTH, randomY);

        return new BrainSmall(position);
    }

    public void spawnWaspGroup(int size, Array<AbstractEnemy> enemies) {
        if (waspGroupCount < size) {
            enemies.add(EnemyFactory.createWasp());
            waspGroupCount++;
        }
    }

    public void resetSpawnGroup(){
        waspGroupCount = 0;
    }

    public void spawnEnemies(float progress, Array<AbstractEnemy> enemies) {
        int waspInterval = 35;
        int simpleInterval = 40;
        int brainInterval = 85;

        float waspIntroProgress = 0.3f;
        float brainIntroProgress = 0.8f;

        // Wasp creation
        waspCount++;
        if (progress > waspIntroProgress &&
                waspCount > waspInterval) {
            enemies.add(EnemyFactory.createWasp());
            waspCount = 0;
        }

        //regular Simple enemy creation
        enemySimpleCount++;
        if (enemySimpleCount > simpleInterval) {
            enemies.add(EnemyFactory.createSimpleEnemy());
            enemySimpleCount = 0;
        }

        //regular brain creation
        brainCount++;
        if (progress > brainIntroProgress &&
                brainCount > brainInterval) {
            enemies.add(EnemyFactory.createBrain());
            brainCount = 0;
        }

    }

/**
    public void spawnEnemiesByWaves(float progress, Array<AbstractEnemy> enemies) {
        //System.out.println(progress);

        int waspInterval = 35;
        int simpleInterval = 40;
        int brainInterval = 85;

        float waspIntroProgress = 0.3f;
        float brainIntroProgress = 0.8f;

        float waspWaveOneStop = 1.5f;
        float simpleWaveOneStop = waspWaveOneStop;
        float brainWaveOneStop = waspWaveOneStop;


        float waveTwoStart = 2.5f;
        float waveTwoStop = 10f;
        //wait a bit for intro


        // wasp creation WAVE ONE
        waspCount++;
        if (progress > waspIntroProgress &&
                progress < waspWaveOneStop &&
                waspCount > waspInterval) {
            enemies.add(EnemyFactory.createWasp());
            waspCount = 0;
        }


        //regular Simple creation
        enemySimpleCount++;
        if (enemySimpleCount > simpleInterval &&
                progress < simpleWaveOneStop
        ) {
            enemies.add(EnemyFactory.createSimpleEnemy());
            enemySimpleCount = 0;
        }

        //regular brain creation
        brainCount++;
        if (progress > brainIntroProgress &&
                progress < brainWaveOneStop &&
                brainCount > brainInterval) {
            enemies.add(EnemyFactory.createBrain());
            brainCount = 0;
        }

        // pause, wait for next wave

        //WAVE 2
        if(progress > waveTwoStart && progress < waveTwoStop
        ) {
            //regular Simple creation
            enemySimpleCount++;
            if (enemySimpleCount > simpleInterval) {
                enemies.add(EnemyFactory.createSimpleEnemy());
                enemySimpleCount = 0;
            }

            waspCount++;
            if (waspCount > waspInterval+50) {
                enemies.add(EnemyFactory.createWasp());
                waspCount = 0;
            }
        }

    }
 */

}
