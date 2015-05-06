

import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by root on 03.05.2015.
 */
public class Model {

    private String currentDirectory = "";

    public Model() {
        currentDirectory = "C:\\";
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }


    /*
    * Function retunrnes string with list of files in directory given as argument.
    *
    * */

    public String showDirectoryContent(String path) {

        ArrayList<File> content = null; // Warehouse for elements (files & directories) in current directory
        ArrayList<String> contentDescription = new ArrayList<>(); // List of sting for return.
        ArrayList<String> lines = new ArrayList<>();

        StringBuilder result = new StringBuilder();
        File currentDir = new File(path);

        if (currentDir.isDirectory()) {
              content = new ArrayList<>(Arrays.asList(currentDir.listFiles()));
        }

        else {
            throw new InvalidPathException(path,"It's not a path to the directory!");
        }
        /*Looking for the file with max size. This information will be used for creation good looking output string
        i.e files and directories names in output string will be aligned one below other regardless of the file size length.
        */
        if (content.size() != 0) {
            long maxLength = 0;
            File maxLengthFile = null;
            for(File fl : content) {

                if (maxLength < fl.length()) {
                    maxLength = fl.length();
                    maxLengthFile = fl;
                }
            }

            for (File pth : content) {
                File element = new File(pth.getAbsolutePath());
                StringBuilder elementDescription = new StringBuilder();
                Date date=new Date(element.lastModified());
                elementDescription.append(String.format("%1$ty.%1$tm.%1$td %1$tH:%1$tM:%1$tS", date));
                String maxSize = lengthReformater(maxLengthFile.length());

                /*
                * If current element is a directory, we put in the StringBuffer data and time of last modification of
                * the element, mark <DIR>, adding empty string with length equal to length of the string represents
                * size of the file with maximum size. At the end we add tab symbol
                *
                * */
                if(element.isDirectory()){
                    elementDescription.append("\t<DIR>\t");
                    char[] chars = new char[maxSize.length()];
                    Arrays.fill(chars, ' ');
                    String filler = new String(chars);
                    elementDescription.append(filler + "\t");
                    elementDescription.append(pth.getName());

                }
                /*
                * If current element is file we put to the StringBuffer data and time, space and tabulator to show in result
                * list size of the file after <DIR> mark for directories.
                * Then we are checking file size. If current element has maximum size we just adding tab to the string,
                * If not,  calculating difference betwen length of the string represents size of current file and file with
                * maximum size. Obtained difference used to create empty string which we add to the size info
                */

                else if (element.isFile()){
                    elementDescription.append("\t     \t");
                    String size = lengthReformater(element.length());
                    elementDescription.append(size);
                    if (element.length() == maxLengthFile.length()) {
                        elementDescription.append("\t");
                        elementDescription.append(pth.getName());
                        }
                    else
                    {
                        char[] chars = new char[maxSize.length() - lengthReformater(element.length()).length()];
                        Arrays.fill(chars, ' ');
                        String filler = new String(chars);
                        elementDescription.append(filler + "\t");
                        elementDescription.append(pth.getName());

                    }

                }
                contentDescription.add(elementDescription.toString());
            }

        }
        for (String str : contentDescription) {
            result.append(str + "\n");
        }
        String tmp = result.toString();
        return result.toString();
    }


    /*
    * Recursive function for seraching file in directory
    *
    * */


    public String search(String path, String name) {

        String found = "";

        ArrayList<File> files = null;
        File currentDir = new File(path);
        try {
            if (currentDir.listFiles() != null) {
                files = new ArrayList<>(Arrays.asList(currentDir.listFiles()));
            }


            for (File fl : files) {
                if (fl.getName().toLowerCase().contains(name.toLowerCase())) {
                    found = found + path + "\\" + fl.getName() + "\n";
                }
                if (fl.isDirectory()) {
                    found = found + search(path + "\\" + fl.getName(), name);
                }
            }
        }
        catch (NullPointerException ex){

        }
        return found;
    }

    public String help(){
        StringBuilder hlp = new StringBuilder();
        hlp.append("dir - Show directory content \n");
        hlp.append("cd - enter current directory. Type \"cd\" and press ENTER then type path like D:\\\\DIRECTORY\\\\DIRECTORY end press ENTER \n");
        hlp.append("help - show command list \n");
        hlp.append("find - find file in current directory Type find and press ENTER then type name of the file and press ENTER  \n");

        return hlp.toString();
    }




    /*
    * Function for put space betwen every three (3) numbers of length of the file.
    *
    */
    private String lengthReformater(long len) {
        StringBuilder result = new StringBuilder();
        String length = Long.toString(len);
            if (length.length() >= 3) {
            for (int i = 0; i < length.length(); i++) {

                // Checking if current index is multiple of 3 (add Space if "Yes")
                if (i%3 == 0 && i!=0 ){
                    result.insert(0,length.charAt(length.length() - i - 1) + " ");
                }
                else
                {
                    result.insert(0, length.charAt(length.length()- i - 1));
                }
            }
        }
        else {
                result.append(length);
        }
     return result.toString();
    }

}
