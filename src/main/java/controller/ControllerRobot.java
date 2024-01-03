package controller;

import model.Robot;
import utils.RobotCommand;

public class ControllerRobot implements InterfaceControllerRobot {

    private Robot robot;

    public ControllerRobot(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void executeCommand(RobotCommand command, double[] args, String label) {
        switch (command) {
            case MOVE:
                move(args);
                break;
            case SIGNAL:
                signal(label);
                break;
            case UNSIGNAL:
                unsignal(label);
                break;
            case FOLLOW:
                follow(label, args);
                break;
            case STOP:
                stop();
                break;
            case CONTINUE:
                continueCommand((int) args[0]);
                break;
            case REPEAT:
                repeatCommand((int) args[0]);
                break;
            case UNTIL:
                until(label);
                break;
            case FOREVER:
                doForever();
                break;
            case DONE:
                done();
                break;
            default:
                // Gestione del caso in cui il comando non è riconosciuto
                System.out.println("Comando non riconosciuto: " + command);
                break;
        }
    }

    @Override
    public void move(double[] args) {
        double x = args[0];
        double y = args[1];
        double speed = args[2];

        if (!robot.isMoving()) {
            robot.stopMoving();
            robot.addCondition("Stopped");

            double distance = Math.sqrt(Math.pow(x - robot.getX(), 2) + Math.pow(y - robot.getY(), 2));
            double time = distance / speed;

            simulateMovement(x, y, time);

            robot.stopMoving();
            robot.addCondition("Stopped");
        } else {
            System.out.println("Il robot è già in movimento.");
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
    }

    @Override
    public void stop() {
        robot.stopMoving();
        robot.addCondition("Stopped");
        System.out.println("Movimento interrotto");
    }

    @Override
    public void continueCommand(int seconds) {
        System.out.println("Movimento continua per " + seconds + " secondi");
        // Puoi utilizzare un timer o un thread per gestire il tempo di continuazione.
    }

    @Override
    public void repeatCommand(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.println("Iterazione " + (i + 1));
            // Aggiungi la logica dei comandi che devono essere ripetuti
        }
    }

    @Override
    public void until(String label) {
        while (!robot.hasCondition(label)) {
            // Aggiungi la logica dei comandi che devono essere ripetuti finché la condizione non è soddisfatta
        }
        System.out.println("Condizione " + label + " percepita");
    }

    @Override
    public void doForever() {
        while (true) {
            System.out.println("Iterazione infinita");
            // Aggiungi la logica dei comandi che devono essere ripetuti all'infinito
        }
    }

    @Override
    public void done() {
        System.out.println("Fine della sequenza di comandi");
    }

    private void simulateMovement(double targetX, double targetY, double time) {
        // Implementa la logica per simulare il movimento del robot per il tempo specificato
        // Puoi utilizzare un timer, un thread o un'altra logica di simulazione a seconda delle tue esigenze.
        // Ad esempio, potresti aggiornare le coordinate del robot in modo incrementale per simulare il movimento.
    }
}
