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
            //doChoice(choice);
            doChoiceIf(choice);
        }

    }

    private void doChoiceIf(String choice){
        String path = new String();
        if (choice.toLowerCase().contains("cd ")){
            path = choice.substring(choice.indexOf(" ") + 1, choice.length());
            if (path.contains(":")) {
                controller.setCurrentDirectory(path);
            }
            else {
                controller.setCurrentDirectory(controller.getCurrentDirectory()+ "\\" + path);
            }
            System.out.println(controller.getCurrentDirectory());
        }

        else if (choice.toLowerCase().contains("dir")) {
            System.out.println(controller.listCurrentDirectory());
        }
        else if (choice.toLowerCase().contains("help")) {
            System.out.println(controller.help());
        }
        else if (choice.toLowerCase().contains("find ")) {
            String key = new String();
            key = choice.substring(choice.indexOf(" ") + 1, choice.length());
            System.out.println("Please wait. I am searching for something.");
            System.out.println(controller.findFile(key));
        }
        else if (choice.toLowerCase().contains("md ")) {
            String name = new String();
            name = choice.substring(choice.indexOf(" ") + 1, choice.length());

            controller.newkDir(controller.getCurrentDirectory(), name);
        }

        else {
            System.out.println("unknown command!");
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




