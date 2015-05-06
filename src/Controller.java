/**
 * Created by root on 05.05.2015.
 */
public class Controller implements IController {

    private Model model = null;

    public Controller(Model mdl) {
        this.model = mdl;
    }

    @Override
    public String listCurrentDirectory() {
        return model.showDirectoryContent(model.getCurrentDirectory());
    }

    @Override
    public String findFile(String name) {

        return model.search(model.getCurrentDirectory(), name);

    }

    @Override
    public String findFile(String path, String name) {
        return model.search(path, name);
    }


    @Override
    public void setCurrentDirectory(String path) {
        model.setCurrentDirectory(path);
    }

    @Override
    public String getCurrentDirectory() {
        return model.getCurrentDirectory();
    }

    @Override
    public String help() {
        return model.help();
    }


}
