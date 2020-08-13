package nix.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SetDatabase {

    public static void set(DbConnection db) throws IOException {
        List<String> cities = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("database_data/src/main/resources/input.txt")));
        int n = Integer.parseInt(reader.readLine());
        int [][] values = new int [n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                values[i][j] = 0;
            }
        }

        int p;
        for(int i=0; i<n; i++){
            cities.add(reader.readLine());
            p = Integer.parseInt(reader.readLine());
            for(int j=0; j<p; j++){
                StringTokenizer numbers = new StringTokenizer(reader.readLine(), " ");
                values[i][Integer.parseInt(numbers.nextToken())-1] = Integer.parseInt(numbers.nextToken());
            }
        }



        db.setLocation(cities);
        db.setRoute(values);

        int r = Integer.parseInt(reader.readLine());
        String directions ="";
        for(int i=0; i<r; i++)
            directions+=reader.readLine()+";";

        StringTokenizer direct = new StringTokenizer(directions, ";");
        for(int j=0; j<r; j++){
            StringTokenizer fromTo = new StringTokenizer(direct.nextToken(), " ");
            String temp1 = fromTo.nextToken();  int start=0;
            String temp2 = fromTo.nextToken();  int end=0;
            for(int i=0; i<cities.size(); i++)
                if (cities.get(i).equals(temp1)) start = i;
            for(int i=0; i<cities.size(); i++)
                if (cities.get(i).equals(temp2)) end = i;


            db.setProblem((j+1), start+1, end+1);

        }
        reader.close();



    }
}
