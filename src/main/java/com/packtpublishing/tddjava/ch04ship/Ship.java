package com.packtpublishing.tddjava.ch04ship;

public class Ship {

    public static final String  OK = "O";
    public static final String  FAIL = "X";

    private Location location;
    private Planet planet;


    public Ship(Location location, Planet planet){
        this.location = location;
        this.planet = planet;
    }


    public Location getLocation() {
        return location;
    }


    public Planet getPlanet() {
        return planet;
    }

    public boolean moveForward(){
        return this.location.forward(planet.getMax(), planet.getObstacles());
    }


    public boolean moveBackward(){
        return this.location.backward(planet.getMax(), planet.getObstacles());
    }


    public void turnLeft(){
        this.location.turnLeft();
    }


    public void turnRight(){
        this.location.turnRight();
    }


    public String receiveCommand(String commands){
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<commands.length();i++) {
            char command = commands.charAt(i);
            boolean validCommand = true;
            if (command == 'f') {
                validCommand = moveForward();
            } else if (command == 'b') {
                validCommand = moveBackward();
            } else if (command == 'l') {
                turnLeft();
            } else if (command == 'r') {
                turnRight();
            }

            if (validCommand){
                builder.append(OK);
            }else{
                builder.append(FAIL);
            }
        }
        return builder.toString();
    }
}
