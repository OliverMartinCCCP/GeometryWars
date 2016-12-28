/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Physics.Movement;

import geometrywars.GameObjects.GameObject;


/**
 *
 * @author STEF
 */
public interface Movement {
    public void move();
    public void setTarget(GameObject target);
    public void setTargets(GameObject target1, GameObject target2);
}
