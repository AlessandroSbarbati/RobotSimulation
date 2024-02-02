package controller;

import model.Coordinate;
import model.CoordinateRobot;
import model.Robot;
import model.RobotInterface;
import utils.RobotCommand;

import java.util.*;


public class ComandiRobotBase implements DoCommand {

    private HashMap<RobotInterface, ArrayList<RobotCommand>> move(CoordinateRobot coord, Coordinate coordArrivo,HashMap<RobotInterface, ArrayList<RobotCommand>> mappa) {
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
    private ArrayList<Double> calcoloIncremento(CoordinateRobot coord, Coordinate coordArrivo){
        ArrayList<Double> result = new ArrayList<>(2);
        double direzione=Math.sqrt(Math.pow(coordArrivo.getX(),2)+Math.pow(coordArrivo.getY(),2));
        result.add((coordArrivo.getX()/direzione)* coord.getVelocita());
        result.add((coordArrivo.getY()/direzione)* coord.getVelocita());

        return result;
    }
    private HashMap<RobotInterface, ArrayList<RobotCommand>> moveRandom(HashMap<RobotInterface, ArrayList<RobotCommand>>mappa,CoordinateRobot coord, Coordinate coordArrivo) {
        Coordinate coordCasuali = new Coordinate(generateRandomValue(coord.getX(), coordArrivo.getX()), generateRandomValue(coord.getY(), coordArrivo.getY()));
        return this.move(coord,coordCasuali,mappa);
    }

    private double generateRandomValue(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    private Robot signal(Robot robot,String etichetta) {
        if (this.validLabel(etichetta)) {
            robot.addCondition(etichetta);
            System.out.println("Segnalazione: " + etichetta);
            return robot;
        }else throw new IllegalArgumentException("Etichetta non valida :"+etichetta);
    }

    private boolean validLabel(String etichetta){
        for (int i = 0 ; i < etichetta.length();i++){
            if (etichetta.charAt(i)<'0' || etichetta.charAt(i)>'9'|| etichetta.charAt(i)<'a' || etichetta.charAt(i)>'z'
            || etichetta.charAt(i)<'A' || etichetta.charAt(i)>'Z' || etichetta.charAt(i)!='_'  ) return false;
        }
        return  true;
    }
    private Robot unsignal(Robot robot,String etichetta) {
        if (robot.removeCondition(etichetta)){
            System.out.println("Terminata segnalazione: " + etichetta);
            return robot;
        }else throw new IllegalArgumentException("Etichetta non esistente");
    }


    private HashMap<RobotInterface, ArrayList<RobotCommand>> stop(HashMap<RobotInterface, ArrayList<RobotCommand>> mappa) {
        for (RobotInterface robot : mappa.keySet()) robot.getCoordinate().setVelocita(0);
        return mappa;
    }

    private HashMap<RobotInterface, ArrayList<RobotCommand>> follow(HashMap<RobotInterface, ArrayList<RobotCommand>> mappa,String etichetta) {
        double xMedia=0;
        double yMedia=0;
        int cont=0;
        List<RobotInterface> listApp = mappa.keySet().stream().filter(e->e.hasCondition(etichetta)).toList();
        for (RobotInterface robot : listApp){
                xMedia=+robot.getCoordinate().getX();
                yMedia=+robot.getCoordinate().getY();
                cont++;
        }
        xMedia=xMedia/cont;
        yMedia=yMedia/cont;
        for (RobotInterface robot:listApp) {
            this.move(robot.getCoordinate(),new Coordinate(xMedia,yMedia),mappa);
        }
        return mappa;
    }
    private HashMap<RobotInterface, ArrayList<RobotCommand>> aggiornaMappa(HashMap<RobotInterface, ArrayList<RobotCommand>> mappa,CoordinateRobot coord, CoordinateRobot coordVecchie){
        RobotInterface app= new Robot(0,0,0);
        ArrayList<RobotCommand> appoggio=new ArrayList<>();
        for (RobotInterface robot: mappa.keySet()){
            if(robot.getCoordinate().equals(coordVecchie)){
               app=robot;
               appoggio=mappa.get(app);
            }
        }
        RobotInterface newRobot = new Robot(coord.getX(), coord.getY(),coord.getVelocita());
       mappa.remove(app);
       mappa.put(newRobot,appoggio);
        return mappa;
    }
    private HashMap<RobotInterface, ArrayList<RobotCommand>> continueCommand(int s,HashMap<RobotInterface, ArrayList<RobotCommand>> mappa,CoordinateRobot coord, Coordinate coordArrivo) {
        int cont=0;
        HashMap<RobotInterface, ArrayList<RobotCommand>> app=mappa;
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

    private HashMap<RobotInterface, ArrayList<RobotCommand>> ritornaRobotMove(HashMap<RobotInterface, ArrayList<RobotCommand>> mappa,CoordinateRobot coord, Coordinate coordArrivo){
        for (RobotInterface robot: mappa.keySet()){
            for (RobotCommand comandi: mappa.get(robot)){
                comandi.equals("MOVE");
                move(coord,coordArrivo,mappa);
            }
        }
        return mappa;
    }

    @Override
    public void doCommand(HashMap<RobotInterface, ArrayList<RobotCommand>> mappa,Robot robot, RobotCommand command, CoordinateRobot coord, Coordinate coordArrivo, String etichetta,int s) {
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
