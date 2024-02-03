package controller;

import model.Coordinate;
import model.CoordinateRobot;
import model.Robot;
import model.RobotInterface;
import utils.RobotCommand;

import java.util.*;

/**
 * Questa classe implementa l'interfaccia DoCommand e fornisce i metodi che riguardano i comandi base.
 */
public class ComandiRobotBase implements DoCommand {
    /**
     * Questo metodo serve per gestire il comando MOVE.
     * @param coord coordinate di partenza del robot.
     * @param coordArrivo coordinate di arrivo del robot.
     * @param mappa mappa dove vengono salvati tutti i robot.
     * @return mappa aggiornata a seconda della move del robot.
     */
    public HashMap<Robot, ArrayList<RobotCommand>> move(CoordinateRobot coord, Coordinate coordArrivo, HashMap<Robot, ArrayList<RobotCommand>> mappa) {
    ArrayList<Double> incrementi = this.calcoloIncremento(coord,coordArrivo);
    if(coord.getX()<= coordArrivo.getX()&&coord.getY()<=coordArrivo.getY()){
        return aggiornaMappa(mappa,new CoordinateRobot(coord.getX()+incrementi.get(0), coord.getY()+incrementi.get(1),coord.getVelocita()),coord) ;
    } else if (coord.getX()<= coordArrivo.getX()&&coord.getY()>= coordArrivo.getY()) {
        return aggiornaMappa(mappa,new CoordinateRobot(coord.getX()+incrementi.get(0), coord.getY()-incrementi.get(1), coord.getVelocita()),coord) ;
    } else if (coord.getX()>= coordArrivo.getX()&&coord.getY()>= coordArrivo.getY()) {
        return aggiornaMappa(mappa,new CoordinateRobot(coord.getX()-incrementi.get(0), coord.getY()-incrementi.get(1), coord.getVelocita()),coord);
    } else if (coord.getX()>= coordArrivo.getX()&&coord.getY()<= coordArrivo.getY()) {
        return aggiornaMappa(mappa,new CoordinateRobot(coord.getX()-incrementi.get(0),coord.getY()+incrementi.get(1), coord.getVelocita()),coord);
    }
        return null;
    }

    /**
     * Questo metodo serve per calcolare la direzione e quindi lo spostamento del robot.
     * @param coord coordinate di partenza del robot.
     * @param coordArrivo coordinate di arrivo del robot.
     * @return coordinate nuove per la move.
     */
    public ArrayList<Double> calcoloIncremento(CoordinateRobot coord, Coordinate coordArrivo){
        ArrayList<Double> result = new ArrayList<>(2);
        double direzione=Math.sqrt(Math.pow(coordArrivo.getX(),2)+Math.pow(coordArrivo.getY(),2));
        result.add((coordArrivo.getX()/direzione)* coord.getVelocita());
        result.add((coordArrivo.getY()/direzione)* coord.getVelocita());

        return result;
    }

    /**
     * Questo metodo serve per gestire un movimento casuale dei robot all'interno di un intervallo.
     * @param mappa mappa dove vengono salvati tutti i robot.
     * @param coord coordinate di partenza del robot (primo elemento dell'intervallo).
     * @param coordArrivo coordinate di arrivo del robot (secondo elemento dell'intervallo).
     * @return mappa aggiornata a seconda della move del robot.
     */
    public HashMap<Robot, ArrayList<RobotCommand>> moveRandom(HashMap<Robot, ArrayList<RobotCommand>> mappa, CoordinateRobot coord, Coordinate coordArrivo) {
        Coordinate coordCasuali = new Coordinate(generateRandomValue(coord.getX(), coordArrivo.getX()), generateRandomValue(coord.getY(), coordArrivo.getY()));
        return this.move(coord,coordCasuali,mappa);
    }

    /**
     * Questo metodo serve per generare un numero casuale all'interno di un intervallo.
     * @param min primo elemento dell'intervallo.
     * @param max secondo elemento dell'intervallo.
     * @return numero generato casualmente all'interno dell'intervallo.
     */
    private double generateRandomValue(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    /**
     * Questo metodo serve per segnalare un'etichetta del robot.
     * @param robot robot che segnala l'etichetta.
     * @param etichetta condizione da segnalare.
     * @return robot con una nuova etichetta.
     */
    public Robot signal(Robot robot, String etichetta) {
        if (this.validLabel(etichetta)) {
            robot.addCondition(etichetta);
            System.out.println("Segnalazione: " + etichetta);
            return robot;
        }else throw new IllegalArgumentException("Etichetta non valida :"+etichetta);
    }

    /**
     * Questo metodo serve per controllare se l'etichetta è valida.
     * @param etichetta etichetta da controllare.
     * @return false se l'etichetta non è valida, true altrimenti.
     */
    public boolean validLabel(String etichetta){
        for (int i = 0 ; i < etichetta.length();i++){
            if (etichetta.charAt(i)<'0' || etichetta.charAt(i)>'9'|| etichetta.charAt(i)<'a' || etichetta.charAt(i)>'z'
            || etichetta.charAt(i)<'A' || etichetta.charAt(i)>'Z' || etichetta.charAt(i)!='_'  ) return false;
        }
        return  true;
    }

    /**
     * Questo metodo serve per rimuovere una condizione da un robot.
     * @param robot robot a cui viene levata la condizione.
     * @param etichetta la condizione da rimuovere.
     * @return robot con l'etichetta rimossa.
     */
    public Robot unsignal(Robot robot, String etichetta) {
        if (robot.removeCondition(etichetta)){
            System.out.println("Terminata segnalazione: " + etichetta);
            return robot;
        }else throw new IllegalArgumentException("Etichetta non esistente");
    }

    /**
     * Questo metodo serve per fermare i robot.
     * @param mappa mappa dove vengono salvati i robot.
     * @return mappa aggiornata con tutti i robot fermi.
     */
    public HashMap<Robot, ArrayList<RobotCommand>> stop(HashMap<Robot, ArrayList<RobotCommand>> mappa) {
        for (Robot robot : mappa.keySet()) robot.getCoordinate().setVelocita(0);
        return mappa;
    }

    /**
     * Questo metodo serve per far muovere i robot verso il punto medio delle loro posizioni.
     * @param mappa mappa dove vengono salvati i robot.
     * @param etichetta condizione da controllare per poter far muovere i robot.
     * @return mappa aggiornata.
     */
    public HashMap<Robot, ArrayList<RobotCommand>> follow(HashMap<Robot, ArrayList<RobotCommand>> mappa, String etichetta) {
        double xMedia=0;
        double yMedia=0;
        int cont=0;
        List<Robot> listApp = mappa.keySet().stream().filter(e->e.hasCondition(etichetta)).toList();
        for (Robot robot : listApp){
                xMedia=+robot.getCoordinate().getX();
                yMedia=+robot.getCoordinate().getY();
                cont++;
        }
        xMedia=xMedia/cont;
        yMedia=yMedia/cont;
        for (Robot robot:listApp) {
            this.move(robot.getCoordinate(),new Coordinate(xMedia,yMedia),mappa);
        }
        return mappa;
    }

    /**
     * Questo metodo serve per aggiornare la mappa.
     * @param mappa mappa dove vengono salvati i robot.
     * @param coord coordinate nuove dei robot.
     * @param coordVecchie coordinate vecchie dei robot.
     * @return mappa aggiornata.
     */
    public HashMap<Robot, ArrayList<RobotCommand>> aggiornaMappa(HashMap<Robot, ArrayList<RobotCommand>> mappa, CoordinateRobot coord, CoordinateRobot coordVecchie){
        Robot app= new Robot(0,0,0);
        ArrayList<RobotCommand> appoggio=new ArrayList<>();
        for (Robot robot: mappa.keySet()){
            if(robot.getCoordinate().equals(coordVecchie)){
               app=robot;
               appoggio=mappa.get(app);
            }
        }
        Robot newRobot = new Robot(coord.getX(), coord.getY(),coord.getVelocita());
       mappa.remove(app);
       mappa.put(newRobot,appoggio);
        return mappa;
    }

    /**
     * Questo metodo serve per far continuare a muovere i robot verso una direzione per s secondi.
     * @param s secondi.
     * @param mappa mappa dove vengono salvati i robot.
     * @param coord coordinate di partenza dei robot.
     * @param coordArrivo coordinate di arrivo dei robot.
     * @return mappa aggiornata.
     */
    public HashMap<Robot, ArrayList<RobotCommand>> continueCommand(int s, HashMap<Robot, ArrayList<RobotCommand>> mappa, CoordinateRobot coord, Coordinate coordArrivo) {
        int cont=0;
        HashMap<Robot, ArrayList<RobotCommand>> app=mappa;
        for (cont = 0 ;cont <s;cont++) {
            app=ritornaRobotMove(app, coord, coordArrivo);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return app;
    }

    /**
     * Questo metodo serve per eseguire il comando CONTINUE.
     * @param mappa mappa dove vengono salvati i robot.
     * @param coord coordinate di partenza dei robot.
     * @param coordArrivo coordinate di arrivo dei robot.
     * @return mappa aggiornata.
     */
    public HashMap<Robot, ArrayList<RobotCommand>> ritornaRobotMove(HashMap<Robot, ArrayList<RobotCommand>> mappa, CoordinateRobot coord, Coordinate coordArrivo){
        for (Robot robot: mappa.keySet()){
            for (RobotCommand comandi: mappa.get(robot)){
                comandi.equals("MOVE");
                move(coord,coordArrivo,mappa);
            }
        }
        return mappa;
    }

    /**
     * Questo metodo serve per richiamare tutti gli altri metodi ed eseguirli.
     * @param mappa mappa dove vengono salvati i robot.
     * @param robot robot.
     * @param command comandi dei robot.
     * @param coord coordinate di partenza dei robot.
     * @param coordArrivo coordinate di arrivo dei robot.
     * @param etichetta condizione.
     * @param s secondi.
     */
    @Override
    public void doCommand(HashMap<Robot, ArrayList<RobotCommand>> mappa,Robot robot, RobotCommand command, CoordinateRobot coord, Coordinate coordArrivo, String etichetta,int s) {
        switch (command){
            case MOVE -> move(coord,coordArrivo,mappa);
            case MOVERANDOM -> moveRandom(mappa,coord,coordArrivo);
            case SIGNAL -> signal(robot,etichetta);
            case UNSIGNAL -> unsignal(robot,etichetta);
            case STOP -> stop(mappa);
            case FOLLOW -> follow(mappa,etichetta);
            case CONTINUE -> continueCommand(s,mappa,coord,coordArrivo);
            default ->System.out.println("Comando non riconosciuto: " + command);
        }
    }
}
