import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The Bus subclass
 */
public class Bus extends Vehicle
{
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first

        //Set up values for Bus
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = 15;
    }

    /**
     * Act - do whatever the Bus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }

    public boolean checkHitPedestrian() {
        List<Pedestrian> peds = getIntersectingObjects(Pedestrian.class);//populate list of pedestrians which are touching the bus
        int counter = 0;
        if (!peds.isEmpty()) {//check for pedestrians
            double oldSpeed = speed;
            for (Pedestrian ped : new ArrayList<>(peds)) { //iterate through all the pedestrians and remove them from the world
                if(ped.isAwake()){
                    getWorld().removeObject(ped);
                    this.stopMe(60);//stop the bus for 60 acts/frames
                }
            }
            moving = true;
            speed = oldSpeed;
            return true;
        }
        return false;
    }

    public void stopMe (int time) {//
        moving = false;
        speed = 0;
        sleepFor(time);
    }
}
