package controller;

import model.Coordinate;
import model.CoordinateRobot;
import model.Robot;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.Random;

public class ComandiRobotBase implements DoCommand {

    private CoordinateRobot move(CoordinateRobot coord, Coordinate coordArrivo) {
    ArrayList<Double> incrementi = this.calcoloIncremento(coord,coordArrivo);
    CoordinateRobot app=new CoordinateRobot(coord.getX(), coord.getY(), coord.getVelocita());
    if(coord.getX()<= coordArrivo.getX()&&coord.getY()<=coordArrivo.getY()){
        return new CoordinateRobot(coord.getX()+incrementi.get(0), coord.getY()+incrementi.get(1),coord.getVelocita());
    } else if (coord.getX()<= coordArrivo.getX()&&coord.getY()>= coordArrivo.getY()) {
        return new CoordinateRobot(coord.getX()+incrementi.get(0), coord.getY()-incrementi.get(1), coord.getVelocita());
    } else if (coord.getX()>= coordArrivo.getX()&&coord.getY()>= coordArrivo.getY()) {
        return new CoordinateRobot(coord.getX()-incrementi.get(0), coord.getY()-incrementi.get(1), coord.getVelocita());
    } else if (coord.getX()>= coordArrivo.getX()&&coord.getY()<= coordArrivo.getY()) {
        return new CoordinateRobot(coord.getX()-incrementi.get(0),coord.getY()+incrementi.get(1), coord.getVelocita());
    }
        return app;
    }
    private ArrayList<Double> calcoloIncremento(CoordinateRobot coord, Coordinate coordArrivo){
        ArrayList<Double> result = new ArrayList<>(2);
        double direzione=Math.sqrt(Math.pow(coordArrivo.getX(),2)+Math.pow(coordArrivo.getY(),2));
        result.add((coordArrivo.getX()/direzione)* coord.getVelocita());
        result.add((coordArrivo.getY()/direzione)* coord.getVelocita());

        return result;
    }
    private CoordinateRobot moveRandom(CoordinateRobot coord, Coordinate coordArrivo) {
        double randomX = generateRandomValue(coord.getX(), coordArrivo.getX());
        double randomY = generateRandomValue(coord.getY(), coordArrivo.getY());

        Coordinate coordCasuali = new Coordinate(randomX, randomY);

        ArrayList<Double> incrementi = calcoloIncremento(coord, coordArrivo);

        if (coord.getX() <= coordArrivo.getX() && coord.getY() <= coordArrivo.getY()) {
            return new CoordinateRobot(coord.getX() + incrementi.get(0), coord.getY() + incrementi.get(1), coord.getVelocita());
        } else if (coord.getX() <= coordArrivo.getX() && coord.getY() >= coordArrivo.getY()) {
            return new CoordinateRobot(coord.getX() + incrementi.get(0), coord.getY() - incrementi.get(1), coord.getVelocita());
        } else if (coord.getX() >= coordArrivo.getX() && coord.getY() >= coordArrivo.getY()) {
            return new CoordinateRobot(coord.getX() - incrementi.get(0), coord.getY() - incrementi.get(1), coord.getVelocita());
        } else if (coord.getX() >= coordArrivo.getX() && coord.getY() <= coordArrivo.getY()) {
            return new CoordinateRobot(coord.getX() - incrementi.get(0), coord.getY() + incrementi.get(1), coord.getVelocita());
        }

        return null;
    }

    private double generateRandomValue(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    private void signal(String etichetta) {

    }

    private void unsignal(String etichetta) {

    }

    private void stop() {

    }
    private void follow() {

    }

    private void continueCommand() {

    }

    private void done() {

    }

    @Override
    public void doCommand(Robot robot, RobotCommand command,CoordinateRobot coord, String etichetta) {
        switch (command){
            case MOVE -> move(coord);
            case MOVERANDOM -> moveRandom(coord);
            case SIGNAL -> signal(etichetta);
            case UNSIGNAL -> unsignal(etichetta);
            case STOP -> stop();
            case FOLLOW -> follow();
            case CONTINUE -> continueCommand();
            case DONE -> done();
            default ->System.out.println("Comando non riconosciuto: " + command);
        }
    }
}
