/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.scaling;

import geometrywars.Display.Window;

/**
 *
 * @author Olivier
 */
public class Scale {

    private Window window;
    private int width;
    private double widthScale, heightScale;

    public Scale(Window window) {
        this.window = window;
        calculateWidthScale();
        calculateHeightScale();
    }

    public void calculateWidthScale() {
        widthScale = 1920.0 / window.getWidth();
    }

    public void calculateHeightScale() {
        heightScale = 1080.0 / window.getHeight();
    }

    public double getWidthScale() {
        return widthScale;
    }

    public double getHeightScale() {
        return heightScale;
    }

}
