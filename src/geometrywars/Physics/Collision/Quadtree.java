package geometrywars.Physics.Collision;

import geometrywars.GameObjects.GameObject;
import java.util.List;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Quadtree {
 
  private int MAX_OBJECTS = 1;
  private int MAX_LEVELS = 125;
 
  private int level;
  private List<GameObject> objects;
  private Rectangle bounds;
  private Quadtree[] nodes;

  public Quadtree(int pLevel, Rectangle pBounds) {
   level = pLevel;
   objects = new ArrayList<>();
   bounds = pBounds;
   nodes = new Quadtree[4];
  }
  
 /*
 * Clears the quadtree
 */
 public void clear() {
   objects.clear();
 
   for (int i = 0; i < nodes.length; i++) {
     if (nodes[i] != null) {
       nodes[i].clear();
       nodes[i] = null;
     }
   }
 }
 /*
 * Splits the node into 4 subnodes (by dividing the node into four equal parts 
 *                                       and initializing the four subnodes with the new bounds)
 */
 private void split() {
   int subWidth = (int)(bounds.getWidth() / 2);
   int subHeight = (int)(bounds.getHeight() / 2);
   int x = (int)bounds.getX();
   int y = (int)bounds.getY();
 
   nodes[0] = new Quadtree(level+1, new Rectangle(x + subWidth, y, subWidth, subHeight));
   nodes[1] = new Quadtree(level+1, new Rectangle(x, y, subWidth, subHeight));
   nodes[2] = new Quadtree(level+1, new Rectangle(x, y + subHeight, subWidth, subHeight));
   nodes[3] = new Quadtree(level+1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
 }
 /* Determine which node the object belongs to. -1 means
 * object cannot completely fit within a child node and is part
 * of the parent node (helper function)
 */
 private int getIndex(GameObject object) {
    int index = -1;
    double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
    double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

    // Object can completely fit within the top quadrants
    boolean topQuadrant = (object.getY() < horizontalMidpoint && object.getY() + object.getHeight() < horizontalMidpoint);
    // Object can completely fit within the bottom quadrants
    boolean bottomQuadrant = (object.getY() > horizontalMidpoint);

    // Object can completely fit within the left quadrants
    if (object.getX() < verticalMidpoint && object.getX() + object.getWidth() < verticalMidpoint) {
      if (topQuadrant) {
        index = 1;
      }
      else if (bottomQuadrant) {
        index = 2;
      }
    }
    // Object can completely fit within the right quadrants
    else if (object.getX() > verticalMidpoint) {
     if (topQuadrant) {
       index = 0;
     }
     else if (bottomQuadrant) {
       index = 3;
     }
    }
 
    return index;
    }
 /*
 * Insert the object into the quadtree. If the node
 * exceeds the capacity, it will split and add all
 * objects to their corresponding nodes.
 */
 public void insert(GameObject object) {
   if (nodes[0] != null) {
     int index = getIndex(object);
 
     if (index != -1) {
       nodes[index].insert(object);
 
       return;
     }
   }
 
   objects.add(object);
 
   if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
      if (nodes[0] == null) { 
         split(); 
      }
 
     int i = 0;
     while (i < objects.size()) {
       int index = getIndex(objects.get(i));
       if (index != -1) {
         nodes[index].insert(objects.remove(i));
       }
       else {
         i++;
       }
     }
   }
 } 
 /*
 * Return all objects that could collide with the given object
 */
 public List retrieve(List returnObjects, GameObject object) {
   int index = getIndex(object);
   if (index != -1 && nodes[0] != null) {
        nodes[index].retrieve(returnObjects, object);
   }
 
   returnObjects.addAll(objects);
 
   return returnObjects;
 }
 
}