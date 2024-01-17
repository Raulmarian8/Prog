package projProg;

import java.io.*;

public class gereInformacao {
    public static void ficheiroInfo(String aUser){
        String numTotal = null;
        try {
            FileReader reader = new FileReader("info_sistema.dat");
            BufferedReader input = new BufferedReader(reader);
            if(input != null) {
                numTotal = input.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Erro! - " + fnfe);
        } catch (IOException ioe){
            System.out.println("Erro! - " + ioe);
        }
        int numTotalInt = Integer.parseInt(numTotal);
        numTotalInt++;
        try{
            FileWriter writer = new FileWriter("info_sistema.dat");
            BufferedWriter output = new BufferedWriter(writer);
            output.write(numTotalInt + "\n" + aUser);
            output.flush();
            output.close();
            writer.close();
        }catch (FileNotFoundException fnfe) {
            System.out.println("Erro! - " + fnfe);
        } catch (IOException ioe){
            System.out.println("Erro! - " + ioe);
        }
    }

    public String lerFicheiroInfo(){
        String auxUser = "";
        try {
            FileReader reader = new FileReader("info_sistema.dat");
            BufferedReader input = new BufferedReader(reader);
            if(input != null) {
                auxUser += input.readLine();
                auxUser += (" execuções. Ultimo utilizador: " + input.readLine());
            }
        } catch (IOException ioe){
            System.out.println("Erro! - " + ioe);
        }
        return auxUser;
    }
}
