package org.github.devicemanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

/**
 * Hello Mouse!
 *
 */
public class MouseMove
{
    private boolean quitPressed=false;

    public static void main( String[] args )
    {
        Robot robot = null;
        JFrame frame;
        JTextArea txt;
        boolean toggle = false;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            exit(1);
        }
        frame = new JFrame ("MouseMove");
        frame.setSize(250,130);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txt = new JTextArea("Loading ...", 3, 20);
        Container cnt = frame.getContentPane();
        cnt.setLayout(new GridLayout(2,1));
        cnt.add(txt);
        txt.setBounds(20,25,200, 40);
        final Button quitButton = new Button("Quit");
        cnt.add(quitButton);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //quitPressed=true;
            }
        });
        PointerInfo pointerInfo ;
        Point where;
        int x;
        int y;
        while (true) {

            pointerInfo = MouseInfo.getPointerInfo();
            if ((pointerInfo != null)){
                where = pointerInfo.getLocation();
                x = (int) where.getX();
                y = (int) where.getY();
                final String s1 = "x " + x + " y " + y;
                txt.setText(s1);
                if (toggle) {
                    toggle = false;
                    robot.mouseMove(x + 1, y);
                } else {
                    robot.mouseMove(x - 1, y);
                    toggle = true;
                }
            }
            sleep(8000);
        }

    }

    public static void sleep(int s) {
        try {
            Thread.sleep(s);
        } catch (Exception e) {
            // ignore
        }
    }

}


