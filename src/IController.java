/**
 * Created by root on 05.05.2015.
 */
public interface IController {
    public String listCurrentDirectory();
    public String findFile(String name);
    public String findFile(String path,String name);
    public void setCurrentDirectory(String path);
    public String getCurrentDirectory();
    public String help();
}
