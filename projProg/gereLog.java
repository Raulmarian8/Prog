package projProg;

import java.io.*;

public class gereLog {
    public static void lerFicheiroLog(){
        String line;
        try {
            FileReader reader = new FileReader("log.txt");
            BufferedReader input = new BufferedReader(reader);
            if(input != null) {
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Erro! - " + fnfe);
        } catch (IOException ioe){
            System.out.println("Erro! - " + ioe);
        } catch (ArrayIndexOutOfBoundsException aioobe){
            System.out.print("Erro! - " + aioobe);
        }
    }

    public static void adicionarFicheiroLog(String aUser, String aAcao){
        try {
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter output = new BufferedWriter(writer);
            output.write("<"+aUser + "> <" + aAcao + ">\n");
            output.flush();
            output.close();
            writer.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Erro! - " + fnfe);
        } catch (IOException ioe) {
            System.out.println("Erro! - " + ioe);
        }
    }
}
