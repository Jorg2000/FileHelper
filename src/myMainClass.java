/**
 * Created by root on 03.05.2015.
 */
public class myMainClass {
    public static void main(String[] args) {

        Model myModel = new Model();
        Controller myController = new Controller(myModel);
        View myView = new View(myController);
        myView.start();

    }
}
