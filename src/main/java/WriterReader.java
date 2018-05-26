package main.java; /**
 * @author Victor Machado Braz.
 * Created on 16/05/2018.
 */
import java.io.*;
import java.util.ArrayList;

public class WriterReader {

    private ArrayList<PrivateKeyModel> privateList;
    private Encrypt_Decrypt encryptDecrypt;


    public WriterReader(){
        this.privateList = new ArrayList<>();
        encryptDecrypt = new Encrypt_Decrypt();
    }

    public void WriteToFile (ArrayList<PrivateKeyModel> privateKeyList) throws Exception{

        try {
            FileOutputStream file = new FileOutputStream("src/main/java/resources");
            ObjectOutputStream o = new ObjectOutputStream(file);

            // Write objects to file
            for (PrivateKeyModel privateKeyModel : privateKeyList) {
                privateKeyModel.setPrivateKey(encryptDecrypt.encrypt(privateKeyModel.getPrivateKey()));
            }
            o.writeObject(privateKeyList);

            o.close();
            file.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error initializing stream");

        }
    }

    public ArrayList<PrivateKeyModel> ReadFromFile(){

        try {
            FileInputStream fi = new FileInputStream("src/main/java/resources");
            ObjectInputStream oi = new ObjectInputStream(fi);

            while(true){
                try{
                    privateList = (ArrayList<PrivateKeyModel> )oi.readObject();
                } catch (EOFException e){
                    oi.close();
                    fi.close();

                    for(PrivateKeyModel privateKey : privateList){

                        privateKey.setPrivateKey(encryptDecrypt.decrypt(privateKey.getPrivateKey()));

                    }
                    return privateList;
                }
            }
        } catch (Exception e){

        }
        return privateList;
    }

    public void WriteContent(String content) {

        File file = new File("src/main/java/content.txt");

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
