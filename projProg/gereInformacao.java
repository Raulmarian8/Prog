package projProg;

import java.io.*;

public class gereInformacao {
    public void ficheiroInfo(String aUser){
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
        if(numTotal == null){
            numTotal = "0";
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
        }catch (IOException ioe){
            System.out.println("Erro! - " + ioe);
        }
    }

    public String lerFicheiroInfo(){
        String auxUser = "Numero de execucoes: ";
        try {
            FileReader reader = new FileReader("info_sistema.dat");
            BufferedReader input = new BufferedReader(reader);
            if(input != null) {
                auxUser += input.readLine();
                auxUser += ("\nUltimo utilizador: " + input.readLine());
            }
        } catch (IOException ioe){
            System.out.println("Erro! - " + ioe);
        }
        return auxUser;
    }
    public void ficheiroCredenciais(String aUser,String aPassword){
        try {
            FileWriter writer = new FileWriter("credenciais_acesso.txt");
            BufferedWriter output = new BufferedWriter(writer);
            output.write("Login: "+aUser + " | Password: " + aPassword + "\n");
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
