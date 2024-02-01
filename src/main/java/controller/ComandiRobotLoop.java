package controller;

import model.Robot;
import model.RobotInterface;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;

public class ComandiRobotLoop implements DoCommandLoop{
    private void repeatCommand(int n, HashMap<RobotInterface, ArrayList<RobotCommand>> mappa) {

        for (int cont=0;cont<n;cont++){
            for (HashMap.Entry<RobotInterface, ArrayList<RobotCommand>> entry : mappa.entrySet()) {
                RobotInterface robot = entry.getKey();
                ArrayList<RobotCommand> comandi = entry.getValue();
                ControllerRobot controllerRobot=new ControllerRobot();
                controllerRobot.executeCommand(robot,comandi);
                cont++;
            }
        }
        done();
    }

    private void until() {

    }

    private void doForever(HashMap<RobotInterface, ArrayList<RobotCommand>> mappa,boolean flag) {
        flag=true;
        while(flag==true){
            for (HashMap.Entry<RobotInterface, ArrayList<RobotCommand>> entry : mappa.entrySet()) {
                RobotInterface robot = entry.getKey();
                ArrayList<RobotCommand> comandi = entry.getValue();
                ControllerRobot controllerRobot = new ControllerRobot();
                controllerRobot.executeCommand(robot, comandi);
            }
        }
        flag=false;
        done();
    }
    private void done() {
        System.out.println("Loop terminato");
    }

    @Override
    public void doCommandLoop(RobotCommand command, int n, HashMap<RobotInterface, ArrayList<RobotCommand>> mappa, Robot robot,boolean flag) {
       switch (command){
           case REPEAT -> repeatCommand(n,mappa);
           case UNTIL -> until();
           case FOREVER -> doForever(mappa,flag);
           case DONE -> done();
           default ->System.out.println("Comando non riconosciuto: " + command);
       }
    }
}
