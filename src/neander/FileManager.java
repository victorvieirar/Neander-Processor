package neander;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    public static List<String> read(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        String line;
        int count;
        List<String> list = new ArrayList<>();

        while(!bufferedReader.readLine().contains("code:")) {
            continue;
        }

        while ((line = bufferedReader.readLine()) != null) {
            if(line.contains(" ")) {
                count = line.indexOf(" ");
                list.add(line.substring(0, count));
                list.add(line.substring(count+1));
            }
            else {
                list.add(line);
            }
        }

        bufferedReader.close();
        return list;
    }
    public static List<Integer> readData(String path)throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        List<Integer> list = new ArrayList<>();
        String line = "";
        if(bufferedReader.readLine() != null)
            line = bufferedReader.readLine();

        int count;
        while(!line.contains("code:")) {
            count = line.indexOf(" ");
            list.add(Integer.parseInt(line.substring(0,count).trim()));
            list.add(Integer.parseInt(line.substring(count+1).trim()));
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return list;

    }
    public static void write(String path) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Write something: ");
        line = in.nextLine();
        bufferedWriter.append(line + "\n");
        bufferedWriter.close();
    }

}
