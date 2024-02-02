package controller;

import model.*;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * ControllerRobot gestisce le azioni e il comportamento del robot all'interno della simulazione.
 */
public class ControllerRobot implements InterfaceControllerRobot {

    private final HashMap<RobotInterface,ArrayList<RobotCommand>> mappaComandiRobot;
    private final ComandiRobotLoop loop;
    private final ComandiRobotBase base;


    public ControllerRobot(ComandiRobotBase base,ComandiRobotLoop loop) {
        this.mappaComandiRobot=new HashMap<>();
        this.base=base;
        this.loop=loop;
    }


    public void addCommand(Robot robot, RobotCommand command) {

        ArrayList<RobotCommand> app = new ArrayList<>();
        if (this.mappaComandiRobot.containsKey(robot)) app = this.mappaComandiRobot.get(robot);
        app.add(command);
        this.mappaComandiRobot.put(robot, app);

    }

    public void executeCommandLoop(Robot robot, RobotCommand command, int n,boolean flag, ShapeData shape){
        addCommand(robot,command);
        loop.doCommandLoop(command,n,mappaComandiRobot,robot,flag,shape);
    }


    public void executeCommand(Robot robot, RobotCommand command, CoordinateRobot coord, Coordinate coordArrivo, String etichetta, int s) {
        addCommand(robot, command);
        base.doCommand(mappaComandiRobot,robot,command,coord,coordArrivo,etichetta,s);
    }
    /*    switch (command) {
            case MOVE -> new ComandiRobotBase().move(coord);
            case MOVERANDOM -> new ComandiRobotBase().moveRandom(coord);
            case SIGNAL -> new ComandiRobotBase().signal(etichetta);
            case UNSIGNAL -> new ComandiRobotBase().unsignal(etichetta);
            case FOLLOW -> new ComandiRobotBase().follow();
            case STOP -> new ComandiRobotBase().stop();
            case CONTINUE -> new ComandiRobotBase().continueCommand();
            case REPEAT -> new ComandiRobotLoop().repeatCommand();
            case UNTIL -> new ComandiRobotLoop().until();
            case FOREVER -> new ComandiRobotLoop().doForever();
            case DONE -> new ComandiRobotBase().done();
            default ->
                // Gestione del caso in cui il comando non è riconosciuto
                    System.out.println("Comando non riconosciuto: " + command);
        }
         @Override

    public void move(double[] args) {
        double x = args[0];
        double y = args[1];
        double speed = args[2];

        if (robotArea != null && robotArea.contains(x, y)) {
            if (!robot.isMoving()) {
                robot.startMoving();
                robot.addCondition("Moving");

                double distance = Math.sqrt(Math.pow(x - robot.getX(), 2) + Math.pow(y - robot.getY(), 2));
                double time = distance / speed;

                simulateMovement(x, y, time);

                robot.stopMoving();
                robot.addCondition("Stopped");
            } else {
                System.out.println("Il robot è già in movimento.");
            }
        } else {
            System.out.println("La posizione specificata non è contenuta nell'Area del robot.");
        }
    }
    @Override
    public void moveRandom(double[] args) {
        double x1 = args[0];
        double x2 = args[1];
        double y1 = args[2];
        double y2 = args[3];
        double speed = args[4];

        if (robotArea != null) {
            if (!robot.isMoving()) {
                robot.startMoving();
                robot.addCondition("Moving");

                // Calcola casualmente la nuova posizione nell'intervallo specificato
                double randomX = Math.random() * (x2 - x1) + x1;
                double randomY = Math.random() * (y2 - y1) + y1;

                double distance = Math.sqrt(Math.pow(randomX - robot.getX(), 2) + Math.pow(randomY - robot.getY(), 2));
                double time = distance / speed;

                simulateMovement(randomX, randomY, time);

                robot.stopMoving();
                robot.addCondition("Stopped");
            } else {
                System.out.println("Il robot è già in movimento.");
            }
        } else {
            System.out.println("L'Area del robot non è stata impostata correttamente.");
        }
    }

    @Override
    public void signal(String label) {
        robot.addCondition(label);
        System.out.println("Segnalazione: " + label);
    }
    @Override
    public void unsignal(String label) {
        robot.removeCondition(label);
        System.out.println("Terminata segnalazione: " + label);
    }

    @Override
    public void follow(String label, double[] args) {
        System.out.println("Segue il robot con etichetta " + label);

        // Imposta la modalità "follow"
        robot.setFollowing(true);

        // Ottieni tutti i robot che segnalano la condizione specificata
        List<Robot> robotsToFollow = simulator.findRobotsByCondition(label);

        if (!robotsToFollow.isEmpty()) {
            // Calcola la direzione media delle posizioni dei robot che segnalano la condizione
            double avgX = robotsToFollow.stream().mapToDouble(Robot::getX).average().orElse(0);
            double avgY = robotsToFollow.stream().mapToDouble(Robot::getY).average().orElse(0);

            // Calcola la direzione tra la posizione corrente del robot e la direzione
            double direction = Math.atan2(avgY - robot.getY(), avgX - robot.getX());

            // Calcola il passo da muovere
            double step = args[0];

            // Calcola i cambiamenti nelle coordinate x e y
            double deltaX = step * Math.cos(direction);
            double deltaY = step * Math.sin(direction);

            // Sposta il robot nella direzione
            robot.move(deltaX, deltaY);


            try {
                Thread.sleep(100); // Attendi per 100 millisecondi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Se non ci sono robot da seguire, scegli una direzione casuale nell'intervallo [-dist, dist]x[-dist, dist]
            double randomDirection = Math.toRadians(Math.random() * 360); // Direzione casuale in radianti
            double randomDistance = args[0] * Math.random(); // Distanza casuale nell'intervallo [0, dist]

            // Calcola i cambiamenti nelle coordinate x e y
            double deltaX = randomDistance * Math.cos(randomDirection);
            double deltaY = randomDistance * Math.sin(randomDirection);

            // Sposta il robot nella direzione casuale
            robot.move(deltaX, deltaY);

            try {
                Thread.sleep(100); // Attendi per 100 millisecondi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Resetta la modalità "follow" alla fine
        robot.setFollowing(false);
    }

    @Override
    public void stop() {
        robot.stopMoving();
        robot.addCondition("Stopped");
        System.out.println("Movimento interrotto");
    }


    @Override
    public void continueCommand(long seconds, double[] moveArgs) {
        System.out.println("Movimento continua per " + seconds + " secondi");

        long endTime = System.currentTimeMillis() + seconds * 1000; // Calcola il tempo di fine

        while (System.currentTimeMillis() < endTime) {
            move(moveArgs);
        }

        System.out.println("Fine del movimento continuo.");
    }


    @Override
    public void repeatCommand(int iterations) {
        if (listCommands != null) {
            for (int i = 0; i < iterations; i++) {
                System.out.println("Iterazione " + (i + 1));

                for (RobotCommand command : listCommands) {
                    executeCommand(command, new double[]{}, "");
                }

                try {
                    Thread.sleep(100); // Attendi per 100 millisecondi
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Attenzione: La lista di comandi ripetuti non è stata impostata.");
        }
    }

    @Override
    public void setListCommand(List<RobotCommand> command) {
        this.listCommands = command;
    }


    @Override
    public void until(String label) {
        if (listCommands != null) {
            while (!robot.hasCondition(label)) {
                for (RobotCommand command : listCommands) {
                    executeCommand(command, new double[]{}, "");
                }

                try {
                    Thread.sleep(100); // Attendi per 100 millisecondi
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Condizione " + label + " percepita");
        } else {
            System.out.println("Attenzione: La lista di comandi per UNTIL non è stata impostata.");
        }
    }



    @Override
    public void doForever() {
        if (listCommands != null) {
            while (true) {
                System.out.println("Iterazione infinita");

                for (RobotCommand command : listCommands) {
                    executeCommand(command, new double[]{}, "");
                }

                try {
                    Thread.sleep(100); // Attendi per 100 millisecondi
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Attenzione: La lista di comandi per DO FOREVER non è stata impostata.");
        }
    }



    @Override
    public void done() {
        System.out.println("Fine della sequenza di comandi");
    }

    @Override
    public void setRobotArea(Area area) {
        this.robotArea = area;
    }

    @Override
    public Area getRobotArea() {
        return this.robotArea;
    }

    @Override
    public Robot getRobot() {
        return robot;
    }


    public void simulateMovement(double targetX, double targetY, double time) {
        // Calcola la quantità di spostamento richiesta per ogni passo
        double deltaX = (targetX - robot.getX()) / time;
        double deltaY = (targetY - robot.getY()) / time;

        // Suddividi il tempo di movimento in passi più piccoli (ogni passo di 0.1 secondi)
        double stepTime = 0.1;
        int numSteps = (int) (time / stepTime);

        // Simula il movimento attraverso i passi
        for (int step = 0; step < numSteps; step++) {
            // Aggiorna direttamente le coordinate del robot durante ciascun passo
            robot.move(deltaX * stepTime, deltaY * stepTime);


            // Attendi per il tempo specificato tra i passi (100 millisecondi)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleInteraction(Robot robot, Area area) {
        interactionHandler.handleInteraction(robot, area);  // Delegato al gestore di interazione
    }
*/
}
