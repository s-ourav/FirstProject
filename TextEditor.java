/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package text.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author souravmazumdar
 */
public class TextEditor implements ActionListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      TextEditor te=new TextEditor();
        
    }
        
        JFrame frame;
        JTextArea textarea;
        JMenuBar menubar;
        JMenu file;
        JMenu edit;
        JMenuItem newFile,openFile,saveFile;
        JMenuItem cut,copy,paste,selectAll,close;
        JButton color,textsize;
        JPanel panel;
        JScrollPane scrollPane;
        



        public TextEditor() {
            frame=new JFrame("TEXT EDITOR BY SOURAV");
            panel=new JPanel();
            textarea=new JTextArea();
            
            menubar=new JMenuBar();
            file=new JMenu("File");
            edit=new JMenu("Edit");
            
            newFile=new JMenuItem("New");
            openFile=new JMenuItem("Open");
            saveFile=new JMenuItem("Save");
            
            newFile.addActionListener(this);
            openFile.addActionListener(this);
            saveFile.addActionListener(this);
            
            
            file.add(newFile);
            file.add(openFile);
            file.add(saveFile);
            menubar.add(file);
            
            
            cut=new JMenuItem("Cut");
            copy=new JMenuItem("Copy");
            paste=new JMenuItem("Paste");
            selectAll=new JMenuItem("Select All");
            close=new JMenuItem("Close");
            
            
            color=new JButton("Choose Color");
            textsize=new JButton("Text-size");
            
            cut.addActionListener(this);
            copy.addActionListener(this);
            paste.addActionListener(this);
            selectAll.addActionListener(this);
            close.addActionListener(this);
            
            color.addActionListener(this);
            textsize.addActionListener(this);
            
            
            
            edit.add(cut);
            edit.add(copy);
            edit.add(paste);
            edit.add(selectAll);
            edit.add(close);
            //edit.add(color);
            menubar.add(edit);
            menubar.add(color);
            menubar.add(textsize);
            
            
            frame.setJMenuBar(menubar);
            
            
            
            scrollPane = new JScrollPane(textarea , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Add the text area to a scroll pane
            
            panel.setBackground(Color.PINK);
            panel.setBorder(new EmptyBorder(5,5,5,5) );
            panel.setLayout(new BorderLayout(0,0));
            
            
            panel.add(scrollPane);
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the frame is closed
            frame.setBounds(100,100,400,400);
            
            frame.setVisible(true);
            
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==cut){
            textarea.cut();
        }
        if(e.getSource()==copy){
            textarea.copy();
        }
        if(e.getSource()==paste){
            textarea.paste();
        }
        if(e.getSource()==selectAll){
            textarea.selectAll();
        }
        if(e.getSource()==close){
            System.exit(0);
        }
        if(e.getSource()==color){
            Color chosenColor = JColorChooser.showDialog(frame, "Choose Color", textarea.getForeground());
            if (chosenColor != null) {
                textarea.setForeground(chosenColor);
            }
        }
        if(e.getSource()==textsize){
            String input = JOptionPane.showInputDialog(frame, "Enter Text Size:");
            if (input != null) {
                try {
                    float textSize = Float.parseFloat(input);
                    textarea.setFont(textarea.getFont().deriveFont(textSize));
                } catch (NumberFormatException f) {
                    // Handle invalid input
                    f.printStackTrace();
                }
            }
        }
       
        
       
        
        if(e.getSource()== openFile ){
            
            JFileChooser filechooser=new JFileChooser();
            int response=filechooser.showOpenDialog(null);
            if(response==JFileChooser.APPROVE_OPTION){
                File file=filechooser.getSelectedFile();
                String path=file.getPath();
                
                try{
                    FileReader filereader=new FileReader(path);
                    BufferedReader bufferedReader=new BufferedReader(filereader);
                    String temp="",output="";
                    while((temp=bufferedReader.readLine())!=null){
                        output+=temp+"\n";
                    }
                    textarea.setText(output);
                }
                catch(IOException fileNotFoundException ){
                    fileNotFoundException.printStackTrace();
                }
                
            }   
        }
        if(e.getSource()==saveFile){
            JFileChooser filechooser=new JFileChooser();
            int response= filechooser.showSaveDialog(null);
            if(response==JFileChooser.APPROVE_OPTION){
                File file=new File(filechooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    FileWriter filewriter=new FileWriter(file);
                    BufferedWriter bufferedwriter=new BufferedWriter(filewriter);
                    textarea.write(bufferedwriter);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }   
            }
        }
        if(e.getSource()==newFile){
            TextEditor te=new TextEditor();
        }         
    }
}
  
