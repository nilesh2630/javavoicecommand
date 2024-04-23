package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class GifNameGUI extends JFrame {
    private JLabel gifLabel;
    private JLabel nameLabel;
    private JPanel panel;

    public GifNameGUI() {
        setTitle("Gif and Name");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        // Create a label to display the GIF
        ImageIcon gif = new ImageIcon("D:\\voice.gif");
        gifLabel = new JLabel(gif);


        // Create a label to display the name

        // Create a panel to hold the labels
        panel = new JPanel();
        panel.add(gifLabel);


        // Add the panel to the frame
        add(panel);

        // Add a mouse listener to the GIF label


        setVisible(true);
    }

    public void ok(String s){

        // Change the visible label from the GIF to the name

        nameLabel = new JLabel(s);
        nameLabel.setFont(new Font("cursive", Font.BOLD, 40));
        nameLabel.setBounds(30,20,50,20);
        // nameLabel.setVisible(false);
        panel.add(nameLabel);
        gifLabel.setVisible(false);
        nameLabel.setVisible(true);

        try {
            // Pause the thread for 2 seconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
    public void kk(){
        nameLabel.setVisible(false);
        gifLabel.setVisible(true);
    }

//    public static void main(String[] args) {
//        GifNameGUI gui = new GifNameGUI();
//        Scanner sc=new Scanner(System.in);
//        String a=sc.next();
//        gui.ok(a);
//        gui.kk();
//    }
}