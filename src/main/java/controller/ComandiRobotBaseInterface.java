package controller;

public interface ComandiRobotBaseInterface {
    public void move(double[] args);
    public void moveRandom(double[] args);
    public void signal(String etichetta);
    public void unsignal(String etichetta);
    public void stop();
    public void follow();
    public void continueCommand();
    public void done();
}
