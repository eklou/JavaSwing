/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.kodjo.swing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author formation
 */
public class Note {
    
    private String title;
    private String text;
    private String fileName;
    private String folderName = "notes";
    private String fileExtension = ".txt";

    public Note() {
    }

    public Note(String fileName) {
        this.fileName = fileName;
    }

    public Note(String title, String text, String fileName) {
        this.title = title;
        this.text = text;
        this.fileName = fileName;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
    
    /**
     * Sauvegarder une note sur le disc dur
     */
    public void saveNote() throws IOException{
        
        //Le contenu du fichier note
        StringBuilder sb = new StringBuilder();
        sb.append(this.title);
        sb.append("\n");
        sb.append(this.text);
        
        String fileContent = sb.toString();
        
        //Instancier un fichier
        
        File noteFile = new File(this.folderName, this.fileName + this.fileExtension);
        
        /** si le fichier n'existe pas on le crée
         * le point d'exclamation represente la negation
        */
        if(! noteFile.exists()) {
        noteFile.createNewFile();
        }
    
        // Ecriture dans le fichier
        PrintWriter pw = new PrintWriter(noteFile);
        pw.write(fileContent);
        pw.flush();
        pw.close();
       
    }
    
    /**
     * Creation d'une nouvelle note
     */
    public void newNote() throws IOException{
        //this.fileName = String.valueOf((new Date()).getTime());
        
        
        //Le titre est utilisé comme nom du fichier
        
        String baseFileName = this.title.replaceAll(" ", " - ");
        String fileName = baseFileName;
        int suffix = 0;
        
        //Instanciation du fichier avec le nom de base pour tester son existance
        File f = new File(this.folderName, baseFileName + this.fileExtension);
        
        //Boucle jusqu'à trouver un nom de fichier qui n'existe pas
        while(f.exists()){
            suffix ++;
            fileName = baseFileName + "-" + suffix;
            f = new File(folderName, fileName + this.fileExtension);
        }
        
        //Attribution du nom trouvé à la proprieté fileName de l'objet
        this.fileName = fileName;
        this.saveNote();
    }
    /**
     * Chargement d'une note
     */
    
    public void loadNote() throws IOException{
    
        //Instanciation d'un fichier
        File noteFile = new File(this.folderName, this.fileName + this.fileExtension);
        
        //Instanciation de FileReader et un BufferedReader pour lire les fichiers ligne à ligne
        
        FileReader fr = new FileReader(noteFile);
        BufferedReader br = new BufferedReader(fr);
        
        //Recuperation du titre
        
        this.title = br.readLine();
        
        //Recuperation du texte
        
        String line;
        StringBuilder sb = new StringBuilder();
        
        // Boucle ligne à ligne jusquéà la fin du fichier
        while((line = br.readLine()) != null){
        
        sb.append(line);
        sb.append("\n");
        
    }
      
        this.text = sb.toString();
        
        br.close();
        fr.close();
    }

    public void deleteNote() {
        //Instancier le fichier
        File noteFile = new File(this.folderName, this.fileName + this.fileExtension);
        noteFile.delete();
    }
}
