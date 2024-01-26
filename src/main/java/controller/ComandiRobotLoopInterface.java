package controller;

public interface ComandiRobotLoopInterface {
    public void repeatCommand(int iterazioni);
    public void until(String etichetta);
    public void doForever();
}
