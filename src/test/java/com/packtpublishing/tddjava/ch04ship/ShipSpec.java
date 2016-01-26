package com.packtpublishing.tddjava.ch04ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {


    Ship ship;
    Location location;
    Planet planet;
    List<Point> obstacles;


    @BeforeMethod
    public void beforeEachTestMethod(){
        location = new Location(new Point(10,10), Direction.NONE);
        obstacles = new ArrayList<Point>();
        for (int i=1;i<=50;i++){
            obstacles.add(new Point(50,i));
        }
        planet = new Planet(new Point(50,50), obstacles);
        ship = new Ship(location, planet);
    }


    public void whenInstantiatedThenLocationIsSet(){
        assertEquals(location, ship.getLocation());
    }


    public void whenInstantiatedThenPlanteIsSet(){
        assertEquals(planet, ship.getPlanet());
    }


    public void whenGoForwardThenForward(){
        Location expectedLocation = location.copy();
        expectedLocation.forward();
        ship.moveForward();
        assertEquals(expectedLocation, ship.getLocation());
    }


    public void whenGoBackwardThenBackward(){
        Location expectedLocation = location.copy();
        expectedLocation.backward();
        ship.moveBackward();
        assertEquals(expectedLocation, ship.getLocation());
    }


    public void whenTurnLeftThenLeft(){
        Location expectedLocation = location.copy();
        expectedLocation.turnLeft();
        ship.turnLeft();
        assertEquals(expectedLocation, ship.getLocation());
    }


    public void whenTurnRightThenRight(){
        Location expectedLocation = location.copy();
        expectedLocation.turnRight();
        ship.turnRight();
        assertEquals(expectedLocation, ship.getLocation());
    }


    public void whenReceiveForwardCommandThenForward(){
        Location expectedLocation = location.copy();
        expectedLocation.forward();
        ship.receiveCommand("f");
        assertEquals(expectedLocation, ship.getLocation());
    }


    public void whenReceiveBackwardCommandThenBackward(){
        Location expectedLocation = location.copy();
        expectedLocation.backward();
        ship.receiveCommand("b");
        assertEquals(expectedLocation, ship.getLocation());
    }


    public void whenReceiveTurnLeftCommandThenLeft(){
        Location expectedLocation = location.copy();
        expectedLocation.turnLeft();
        ship.receiveCommand("l");
        assertEquals(expectedLocation, ship.getLocation());
    }


    public void whenReceiveTurnRightCommandThenRight(){
        Location expectedLocation = location.copy();
        expectedLocation.turnRight();
        ship.receiveCommand("r");
        assertEquals(expectedLocation, ship.getLocation());
    }


    public void whenReceiveLRFBCommandThenLRFB(){
        Location expectedLocation = location.copy();
        expectedLocation.turnLeft();
        expectedLocation.turnRight();
        expectedLocation.forward();
        expectedLocation.backward();
        ship.receiveCommand("lrfb");
        assertEquals(expectedLocation, ship.getLocation());
    }


    public void overPassEastBoundary(){
        ship.getLocation().setDirection(Direction.EAST);
        ship.getLocation().getPoint().setX(planet.getMax().getX());
        ship.receiveCommand("f");
        assertEquals(ship.getLocation().getX(), 1);
    }


    public void overPassSouthhBoundary(){
        ship.getLocation().setDirection(Direction.SOUTH);
        ship.getLocation().getPoint().setY(1);
        ship.receiveCommand("b");
        assertEquals(ship.getLocation().getY(), planet.getMax().getY());
    }


    public void whenTryToCrossEastBoundaryThenFail(){
        ship.getLocation().setDirection(Direction.EAST);
        ship.getLocation().getPoint().setX(planet.getMax().getX()-1);
        String result = ship.receiveCommand("f");
        assertEquals(result, Ship.FAIL);
    }


    public void whenTryToDoRouteAndCrossEastBoundaryThenFail(){
        ship.getLocation().setDirection(Direction.EAST);
        ship.getLocation().getPoint().setX(planet.getMax().getX()-1);
        String result = ship.receiveCommand("bbfff");
        String expectedResult = Ship.OK + Ship.OK + Ship.OK + Ship.OK + Ship.FAIL;
        assertEquals(result, expectedResult);
    }


    public void whenTryToDoRouteAndCrossEastBoundaryThenNotCross(){
        ship.getLocation().setDirection(Direction.EAST);
        ship.getLocation().getPoint().setX(planet.getMax().getX()-1);
        Location expectedLocation = ship.getLocation().copy();
        ship.receiveCommand("bbfff");
        assertEquals(ship.getLocation(),expectedLocation);
    }
}
