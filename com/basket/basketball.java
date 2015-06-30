package com.basket;

import javax.swing.*;
import java.awt.event.*;

public class basketball extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel pan;
    private JButton restart;
    private Screen scr;
    KeyListener keyls = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 27) {
                onCancel();
            }
            if (e.getKeyCode() == 37) {
            scr.p1.start("l");
            }
            if(e.getKeyCode()==38){
                scr.p1.start("u");
            }
            if(e.getKeyCode()==39){
                scr.p1.start("r");
            }
            if(e.getKeyCode()==87){
                scr.p2.start("u");
            }
            if(e.getKeyCode()==65){
                scr.p2.start("l");
            }
            if(e.getKeyCode()==83){
                scr.p2.start("d");
            }
            if(e.getKeyCode()==68){
                scr.p2.start("r");
            }

            scr.Keycode=e.getKeyCode();
        }

        @Override
        public void keyReleased(KeyEvent e) {
if(e.getKeyCode()==39 || e.getKeyCode()==37){
    scr.p1.stop();
}
            if(e.getKeyCode()==65||e.getKeyCode()==68){
                scr.p2.stop();
            }
        }
    };

    public basketball() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE

        contentPane.setFocusable(true);
        contentPane.addKeyListener(keyls);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scr.reset();
            }
        });

    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        basketball dialog = new basketball();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
