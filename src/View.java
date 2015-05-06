import java.util.Scanner;

/**
 * Created by root on 05.05.2015.
 */
public class View {
    private Scanner sc = new Scanner(System.in);
    private IController controller = null;

    public View(IController ctr){
        this.controller = ctr;
    }

    public void start(){

        showMainMenu();
        while(true){
            System.out.print(controller.getCurrentDirectory() + ">");
            String choice = sc.nextLine();
            doChoice(choice);
        }

    }

    private void doChoice(String choice){
        switch (choice) {

            case "cd" :
                System.out.println("Input directory");
                String key = sc.nextLine();
                controller.setCurrentDirectory(key);
                break;

            case "dir" :
                System.out.println(controller.listCurrentDirectory());
                break;

            case "help" :
                System.out.println(controller.help());

                break;

            case "find" :
                System.out.println("input name to find");
                String search = sc.next();
                System.out.println("Please wait. I am searching for something.");
                System.out.println(controller.findFile(search));
                break;

            default:
                System.err.println("wrong choice");
                break;
        }
    }

    private void showMainMenu() {
        System.out.println(controller.help());
    }

}




