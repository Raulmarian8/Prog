package projProg;

import java.io.*;

public class gereLog {
    public void lerFicheiroLog(){
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

    public void adicionarFicheiroLog(String aUser, String aAcao){
        String antigo = "";
        String linha;
        try {
            FileReader reader = new FileReader("log.txt");
            BufferedReader input = new BufferedReader(reader);
            if(input != null) {
                while ((linha = input.readLine()) != null) {
                    antigo+=linha+"\n";
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Erro! - " + fnfe);
        } catch (IOException ioe){
            System.out.println("Erro! - " + ioe);
        } catch (ArrayIndexOutOfBoundsException aioobe){
            System.out.print("Erro! - " + aioobe);
        }
        try {
            FileWriter writer = new FileWriter("log.txt");
            BufferedWriter output = new BufferedWriter(writer);
            output.write("<"+aUser + "> <" + aAcao + ">\n");
            output.write(antigo);
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
