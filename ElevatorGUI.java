package ru.test.elevator;

import javax.swing.*;
import java.awt.*;

public class ElevatorGUI {
    JTextArea textArea;


    public ElevatorGUI(int floors, ElevatorControlCenter ecc) {
        JFrame frame = new JFrame("Elevator sim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 800);
        frame.setLayout(new GridLayout(0,2));
        JLabel label = new JLabel("Elevator status: ");
        textArea = new JTextArea(10, 10);
        textArea.setEditable(false);
        textArea.setText("1 floor doors closed\n");
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(label);
        frame.getContentPane().add( scrollPane);
        frame.getContentPane().add( new JLabel("Buttons inside"));
        frame.getContentPane().add( new JLabel("Buttons outside"));
        for (int i = 1; i <= floors; i++){
            frame.getContentPane().add(new JButton(String.valueOf(i)));
            frame.getContentPane().add(new JButton(String.valueOf(i)));
        }
        for (Component comp:frame.getContentPane().getComponents()) {
            if (comp instanceof JButton)  {
                ((JButton) comp).addActionListener(ecc);
            }
        }
        frame.setVisible(true);

    }

    public JTextArea getOut() {
        return textArea;
    }

}
