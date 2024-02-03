package controller;

import model.*;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Questa classe implementa l'interfaccia DoCommandLoop e fornisce i metodi che riguardano i comandi loop.
 */
public class ComandiRobotLoop implements DoCommandLoop{
    /**
     * Questo metodo serve per ripetere i comandi per n volte.
     * @param mappa mappa dove vengono salvati i robot.
     * @param robot robot.
     * @param command comandi dei robot.
     * @param n numero di volte.
     */
    private void repeatCommand(HashMap<RobotInterface, ArrayList<RobotCommand>> mappa,Robot robot, RobotCommand command, int n,boolean flag, ShapeData shape) {

        for (int cont=0;cont<n;cont++){
            for (RobotInterface robots: mappa.keySet()){
                for (ArrayList<RobotCommand> comandi: mappa.values()){
                    ControllerRobot controllerRobot=new ControllerRobot();
                    controllerRobot.executeCommandLoop(robot,command,n,flag,shape);
                    cont++;
                }
            }
        }
        done();
    }

    /**
     * Questo metodo serve per ripetere i comandi fino a quando i robot sono all’interno di un’area con la condizione richiesta.
     * @param mappa mappa dove vengono salvati i robot.
     * @param robot robot.
     * @param command comandi dei robot.
     * @param shape area circolare o rettangolare.
     */
    private void until(HashMap<RobotInterface, ArrayList<RobotCommand>> mappa,Robot robot, RobotCommand command, int n,boolean flag, ShapeData shape) {
        for (RobotInterface robots: mappa.keySet()){
            for (ArrayList<RobotCommand> comandi: mappa.values()){
                ControllerRobot controllerRobot=new ControllerRobot();
                Area rectangularArea = new RectangularArea(shape);
                Area circularArea = new CircularArea(shape);
                if(shape.equals("RECTANGLE")){
                    while (rectangularArea.areaChecker(robot.getCoordinate())) {
                        controllerRobot.executeCommandLoop(robot,command,n,flag,shape);
                    }
                } else if (shape.equals("CIRCLE")) {
                    while(circularArea.areaChecker(robot.getCoordinate())){
                        controllerRobot.executeCommandLoop(robot,command,n,flag,shape);
                    }
                }
            }
        }
        done();
    }

    /**
     * Questo metodo serve per ripetere all'infinito i comandi.
     * @param mappa mappa dove vengono salvati i robot.
     * @param robot robot.
     * @param command comandi dei robot.
     * @param flag boolean per creare un ciclo infinito.
     */
    private void doForever(HashMap<RobotInterface, ArrayList<RobotCommand>> mappa,Robot robot, RobotCommand command, int n,boolean flag, ShapeData shape) {
        flag=true;
        while(flag){
           for (RobotInterface robots: mappa.keySet()){
               for (ArrayList<RobotCommand> comandi: mappa.values()){
                   ControllerRobot controllerRobot = new ControllerRobot();
                   controllerRobot.executeCommandLoop(robot,command,n,flag,shape);
               }
           }
        }
        flag=false;
        done();
    }

    /**
     * Questo metodo serve per stampare che il comando loop è terminato.
     */
    private void done() {
        System.out.println("Loop terminato");
    }

    /**
     * Questo metodo è l'unico pubblico della classe perché serve per richiamare tutti gli altri ed eseguirli.
     * @param command comandi dei robot.
     * @param n numero di volte per il metodo REPEAT.
     * @param mappa mappa dove vengono salvare i robot.
     * @param robot robot.
     * @param flag boolean per il metodo FOREVER.
     * @param shape area per il metodo UNTIL.
     */
    @Override
    public void doCommandLoop(RobotCommand command, int n, HashMap<RobotInterface, ArrayList<RobotCommand>> mappa, Robot robot,boolean flag,ShapeData shape) {
       switch (command){
           case REPEAT -> repeatCommand(mappa,robot,command,n,flag,shape);
           case UNTIL -> until(mappa,robot,command,n,flag,shape);
           case FOREVER -> doForever(mappa,robot,command,n,flag,shape);
           case DONE -> done();
           default ->System.out.println("Comando non riconosciuto: " + command);
       }
    }
}
