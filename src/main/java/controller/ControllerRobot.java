package controller;

import model.Area;
import model.Robot;
import simulator.SimulatorImpl;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.List;
/**
 * ControllerRobot gestisce le azioni e il comportamento del robot all'interno della simulazione.
 */
public class ControllerRobot implements InterfaceControllerRobot {

    private final Robot robot;
    private Area robotArea;
    private final SimulatorImpl simulator;

    private final DefaultInteractionHandler interactionHandler;

    private List<RobotCommand> listCommands;
    /**
     * Costruttore per ControllerRobot.
     *
     * @param robot     Il robot controllato dal controller.
     * @param robotArea L'area in cui il robot può muoversi.
     * @param simulator Il simulatore che gestisce la simulazione.
     */
    public ControllerRobot(Robot robot, Area robotArea, SimulatorImpl simulator) {
        this.robot = robot;
        this.robotArea=robotArea;
        this.simulator=simulator;
        this.interactionHandler = new DefaultInteractionHandler();
        this.listCommands = new ArrayList<>();
    }
    /**
     * Esegue un comando specificato con argomenti e un'etichetta associata.
     *
     * @param command Il comando da eseguire.
     * @param args    Gli argomenti associati al comando.
     * @param label   L'etichetta associata al comando.
     */
    @Override
    public void executeCommand(RobotCommand command, double[] args, String label) {
        switch (command) {
            case MOVE -> move(args);
            case MOVERANDOM -> moveRandom(args);
            case SIGNAL -> signal(label);
            case UNSIGNAL -> unsignal(label);
            case FOLLOW -> follow(label, args);
            case STOP -> stop();
            case CONTINUE -> continueCommand((int) args[0], args);
            case REPEAT -> {
                setListCommand(listCommands);
                repeatCommand((int) args[0]);
            }
            case UNTIL -> until(label);
            case FOREVER -> doForever();
            case DONE -> done();
            default ->
                // Gestione del caso in cui il comando non è riconosciuto
                    System.out.println("Comando non riconosciuto: " + command);
        }
    }
    /**
     * Muove il robot verso una posizione specificata.
     *
     * @param args Gli argomenti che specificano la posizione e la velocità del movimento.
     */
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
    /**
     * Muove il robot in modo casuale all'interno di un'area specificata.
     *
     * @param args Gli argomenti che specificano l'area e la velocità del movimento casuale.
     */
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

    /**
     * Aggiunge una condizione al robot indicando una segnalazione con un'etichetta specificata.
     *
     * @param label L'etichetta della segnalazione da aggiungere come condizione al robot.
     */
    @Override
    public void signal(String label) {
        robot.addCondition(label);
        System.out.println("Segnalazione: " + label);
    }
    /**
     * Rimuove una condizione dal robot indicando il termine di una segnalazione con un'etichetta specificata.
     *
     * @param label L'etichetta della segnalazione da rimuovere come condizione dal robot.
     */
    @Override
    public void unsignal(String label) {
        robot.removeCondition(label);
        System.out.println("Terminata segnalazione: " + label);
    }

    /**
     * Avvia la modalità "follow", facendo muovere il robot verso le posizioni degli altri robot
     * che segnalano la stessa condizione, oppure in una direzione casuale se nessun altro robot è presente.
     *
     * @param label L'etichetta della condizione da seguire.
     * @param args  Gli argomenti, con il passo di movimento specificato.
     */
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

    /**
     * Interrompe il movimento del robot, aggiungendo la condizione "Stopped".
     * Stampa un messaggio indicando che il movimento è stato interrotto.
     */
    @Override
    public void stop() {
        robot.stopMoving();
        robot.addCondition("Stopped");
        System.out.println("Movimento interrotto");
    }

    /**
     * Continua il movimento del robot per un periodo specificato in secondi.
     * Stampa un messaggio indicando che il movimento continua per il periodo specificato.
     * Durante il periodo di continuazione, esegue il movimento utilizzando gli argomenti forniti.
     *
     * @param seconds   Il numero di secondi per cui il movimento deve continuare.
     * @param moveArgs  Gli argomenti da utilizzare per il movimento.
     */
    @Override
    public void continueCommand(long seconds, double[] moveArgs) {
        System.out.println("Movimento continua per " + seconds + " secondi");

        long endTime = System.currentTimeMillis() + seconds * 1000; // Calcola il tempo di fine

        while (System.currentTimeMillis() < endTime) {
            move(moveArgs);
        }

        System.out.println("Fine del movimento continuo.");
    }

    /**
     * Ripete una sequenza di comandi per un numero specificato di iterazioni.
     * Stampa un messaggio indicando l'inizio di ogni iterazione.
     *
     * @param iterations Il numero di iterazioni da eseguire.
     */
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
    /**
     * Imposta la lista dei comandi che devono essere ripetuti.
     *
     * @param command La lista dei comandi da impostare per la ripetizione.
     */
    @Override
    public void setListCommand(List<RobotCommand> command) {
        this.listCommands = command;
    }

    /**
     * Ripete una sequenza di comandi fino a quando una certa condizione è percepita nell'ambiente (ossia il robot è all'interno di un'area con la label richiesta).
     *
     * @param label L'etichetta della condizione da attendere.
     */
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


    /**
     * Ripete un comportamento per sempre.
     */
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


    /**
     * Segnala la fine della sequenza di comandi.
     */
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

    /**
     * Simula il movimento del robot verso una destinazione specifica nel tempo specificato.
     *
     * @param targetX La coordinata x della destinazione.
     * @param targetY La coordinata y della destinazione.
     * @param time    Il tempo in cui completare il movimento.
     */
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
    /**
     * Gestisce l'interazione tra il robot e un'area specificata.
     *
     * @param robot Il robot coinvolto nell'interazione.
     * @param area  L'area con cui il robot interagisce.
     */
    public void handleInteraction(Robot robot, Area area) {
        interactionHandler.handleInteraction(robot, area);  // Delegato al gestore di interazione
    }

}
