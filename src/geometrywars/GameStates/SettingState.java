/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrywars.GameStates;

import geometrywars.Display.SettingsForm;
import geometrywars.Display.Window;
import java.awt.Frame;

/**
 *
 * @author Olivier
 */
public class SettingState{

    private GameStateManager gsm;
    
    private Window gameWindow;
    private Frame gameFrame;
    
    private SettingsForm settingsPanel;

    private int width, height;

    public SettingState(GameStateManager gsm) {
        this.gsm = gsm;
        
        settingsPanel = new SettingsForm(gameFrame, true);
        settingsPanel.setGsm(gsm);
        
    }
    
    public void setMenuVisibility(boolean visible){
        settingsPanel.setVisible(visible);
    }

}
