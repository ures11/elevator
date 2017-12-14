package ru.test.elevator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElevatorControlCenter implements ActionListener {

    private int floors;
    private int floorTime;
    private int doorTime;
    private int currentFloor;
    private ElevatorGUI gui;
    private Timer doorTimer;
    private Timer floorTimer;
    private JTextArea mainOut;
    private boolean isIdle;
    private boolean isClosed;
    private enum GeneralDirection{UP, DOWN};
    private GeneralDirection direction;
    private boolean destinations[];

    public ElevatorControlCenter(int floors, float height, float velocity, float doors) {
         this.floors = floors;
         this.doorTime = (int) (doors * 1000);
         this.floorTime = (int) (height / velocity * 1000);
         this.currentFloor = 1;
         this.isIdle = true;
         this.isClosed = true;
         this.destinations = new boolean[floors+1];
         this.direction=GeneralDirection.UP;
         doorTimer = new Timer(doorTime, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mainOut.append("doors closed\n");
                isClosed=true;
                if (!scanDestinations()) {
                    isIdle=true;
                } else {
                    move();
                }
                doorTimer.stop();
            }
         });
        floorTimer = new Timer(floorTime, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                scanDestinations();
                if (direction==GeneralDirection.UP) {
                    currentFloor++;
                } else {
                    currentFloor--;
                }
                mainOut.append(currentFloor + " floor\n");
                if(destinations[currentFloor]){
                    floorTimer.stop();
                    openDoors();
                    destinations[currentFloor]=false;
                }
            }
         });
    }

    public void go() {
        gui = new ElevatorGUI(floors, this);
        mainOut = gui.getOut();
    }

    private void move() {
        floorTimer.start();
    }

    private void closeDoors() {
        doorTimer.start();
    }

    private void openDoors() {
        mainOut.append("doors open\n");
        isClosed=false;
        isIdle=false;
        closeDoors();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
          addNewDestination(Integer.parseInt(e.getActionCommand()));
    }

    private void addNewDestination(int newDest) {
        if( isIdle && newDest==currentFloor){
          openDoors();
          destinations[newDest] = false;
        }else if(isClosed && newDest!=currentFloor){
            destinations[newDest] = true;
            isIdle=false;
            move();
        }else if(!(newDest==currentFloor)){
            destinations[newDest] = true;
        }
    }

    private boolean scanDestinations() {
        for (int i=1;i<=floors; i++) {
            if (destinations[i]) {
                if(direction==GeneralDirection.UP){
                    direction=GeneralDirection.DOWN;
                    for (int k=currentFloor;k<=floors; k++) {
                        if (destinations[k]) {
                            direction=GeneralDirection.UP;
                        }
                    }
                }else {
                    direction=GeneralDirection.UP;
                    for (int k=1;k<=currentFloor; k++) {
                        if (destinations[k]) {
                            direction=GeneralDirection.DOWN;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
