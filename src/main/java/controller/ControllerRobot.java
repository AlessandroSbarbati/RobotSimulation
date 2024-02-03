package controller;

import model.*;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * ControllerRobot gestisce le azioni e il comportamento del robot all'interno della simulazione.
 */
public class ControllerRobot implements ControllerInterface {

    private final HashMap<Robot,ArrayList<RobotCommand>> mappaComandiRobot;
    private final ComandiRobotLoop loop;
    private final ComandiRobotBase base;

    public ControllerRobot() {
        this.mappaComandiRobot=new HashMap<>();
        this.base=new ComandiRobotBase();
        this.loop=new ComandiRobotLoop();
    }

    /**
     * Questo metodo serve per aggiungere comandi alla mappa associandoli ai robot.
     * @param robot robot.
     * @param command comandi dei robot.
     */
    public void addCommand(Robot robot, RobotCommand command) {

        ArrayList<RobotCommand> app = new ArrayList<>();
        if (this.mappaComandiRobot.containsKey(robot)) app = this.mappaComandiRobot.get(robot);
        app.add(command);
        this.mappaComandiRobot.put(robot, app);

    }

    /**
     * Questo metodo serve per eseguire i comandi loop.
     * @param robot robot.
     * @param command comandi dei robot.
     * @param n numero di volte per il metodo REPEAT.
     * @param flag boolean per il metodo FOREVER.
     * @param shape area per il metodo UNTIL.
     */
    public void executeCommandLoop(Robot robot, RobotCommand command, int n,boolean flag, ShapeData shape){
        addCommand(robot,command);
        loop.doCommandLoop(command,n,mappaComandiRobot,robot,flag,shape);
    }

    /**
     * Questo metodo serve per eseguire i comandi base.
     * @param robot robot.
     * @param command comandi dei robot.
     * @param coord coordinate di partenza dei robot.
     * @param coordArrivo coordinate di arrivo dei robot.
     * @param etichetta condizione dei robot.
     * @param s secondi per il metodo CONTINUE.
     */
    public void executeCommand(Robot robot, RobotCommand command, CoordinateRobot coord, Coordinate coordArrivo, String etichetta, int s) {
        addCommand(robot, command);
        base.doCommand(mappaComandiRobot,robot,command,coord,coordArrivo,etichetta,s);
    }
    public HashMap<Robot, ArrayList<RobotCommand>> getMappaComandiRobot() {
        return mappaComandiRobot;
    }
}
