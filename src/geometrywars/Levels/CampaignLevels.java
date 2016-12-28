/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.levels;

import geometrywars.utilities.*;

/**
 *
 * @author oliver
 */
public class CampaignLevels {
    private String path;
    private int level;
    private int amountCircle;
    private int amountTriangle;
    private int amountHexagon;
    private boolean bossEnemy;
    
    public CampaignLevels(String path, int level){
        this.path = path;
        this.level = level;
        amountCircle = 0;
        amountTriangle = 0;
        amountHexagon = 0;
        this.bossEnemy = false;
        loadWorld();
    }
    
    public void loadWorld(){
        String file = Utils.loadFileAsString(path);
        String[] levels = file.split("\\s+");
        for (int i = 0; i<levels.length; i++){
            if (levels[i].equals("Level"+level)){
                if (levels[i+1].equals("BOSSLEVEL")){
                    bossEnemy = true;
                }else{
                    amountCircle = Utils.parseInt(levels[i+1]);
                    amountTriangle = Utils.parseInt(levels[i+2]);
                    amountHexagon = Utils.parseInt(levels[i+3]);
                }
                
            }
        }
    }

    public boolean getBossEnemy(){
        return bossEnemy;
    }
    
    public int getAmountCircle() {
        return amountCircle;
    }

    public int getAmountTriangle() {
        return amountTriangle;
    }

    public int getAmountHexagon() {
        return amountHexagon;
    }
    
    
    
}
