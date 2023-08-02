package com.nhnacademy;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BorderLayoutTest {
    static int operand = 0;
    static String operator = null;

    public static int calc(int operand) {
        int result = 0;

        if (operator.equals("+")) {
            result = BorderLayoutTest.operand + operand;
        }
        if (operator.equals("-")) {
            result = BorderLayoutTest.operand - operand;
        }
        if (operator.equals("*")) {
            result = BorderLayoutTest.operand * operand;
        }
        if (operator.equals("/")) {
            result = BorderLayoutTest.operand / operand;
        }
        BorderLayoutTest.operand = 0;
        operator = null;

        return result;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JTextField textField = new JTextField();
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");
        JButton applyButton = new JButton("=");
        JButton ACButton = new JButton("AC");
        GridBagConstraints constraints = new GridBagConstraints();

        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(310, 330);
        frame.setLocation(1000, 500);
        frame.setResizable(false);

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridwidth = 4;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipady = 20;
        frame.add(textField, constraints);
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setText("0");
        textField.setBackground(Color.LIGHT_GRAY);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        frame.add(button7, constraints);
        button7.addActionListener(e -> {
            if (textField.getText().matches("[1-9]*")) {
                textField.setText(textField.getText() + "7");
                return;
            }
            textField.setText("7");
        });

        constraints.gridx = 1;
        constraints.gridy = 1;
        frame.add(button8, constraints);
        button8.addActionListener(e -> {
            if (textField.getText().matches("[1-9]*")) {
                textField.setText(textField.getText() + "8");
                return;
            }
            textField.setText("8");
        });

        constraints.gridx = 2;
        constraints.gridy = 1;
        frame.add(button9, constraints);
        button9.addActionListener(e -> {
            if (textField.getText().matches("[1-9]*")) {
                textField.setText(textField.getText() + "9");
                return;
            }
            textField.setText("9");
        });

        constraints.gridx = 3;
        constraints.gridy = 1;
        frame.add(plusButton, constraints);
        plusButton.addActionListener(e -> {
            if (operator == null) {
                operator = "+";
                operand = Integer.parseInt(textField.getText());
                textField.setText(textField.getText() + "+");
                return;
            }
            if (textField.getText().matches("[0-9]*")) {
                operand = calc(Integer.parseInt(textField.getText()));
                operator = "+";
                textField.setText(operand + "+");
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 2;
        frame.add(button4, constraints);
        button4.addActionListener(e -> {
            if (textField.getText().matches("[1-9]*")) {
                textField.setText(textField.getText() + "4");
                return;
            }
            textField.setText("4");
        });

        constraints.gridx = 1;
        constraints.gridy = 2;
        frame.add(button5, constraints);
        button5.addActionListener(e -> {
            if (textField.getText().matches("[1-9]*")) {
                textField.setText(textField.getText() + "5");
                return;
            }
            textField.setText("5");
        });

        constraints.gridx = 2;
        constraints.gridy = 2;
        frame.add(button6, constraints);
        button6.addActionListener(e -> {
            if (textField.getText().matches("[1-9]*")) {
                textField.setText(textField.getText() + "6");
                return;
            }
            textField.setText("6");
        });

        constraints.gridx = 3;
        constraints.gridy = 2;
        frame.add(minusButton, constraints);
        minusButton.addActionListener(e -> {
            if (operator == null) {
                operator = "-";
                operand = Integer.parseInt(textField.getText());
                textField.setText(textField.getText() + "-");
                return;
            }
            if (textField.getText().matches("[0-9]*")) {
                operand = calc(Integer.parseInt(textField.getText()));
                operator = "-";
                textField.setText(operand + "-");
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 3;
        frame.add(button1, constraints);
        button1.addActionListener(e -> {
            if (textField.getText().matches("[1-9]*")) {
                textField.setText(textField.getText() + "1");
                return;
            }
            textField.setText("1");
        });

        constraints.gridx = 1;
        constraints.gridy = 3;
        frame.add(button2, constraints);
        button2.addActionListener(e -> {
            if (textField.getText().matches("[1-9]*")) {
                textField.setText(textField.getText() + "2");
                return;
            }
            textField.setText("2");
        });

        constraints.gridx = 2;
        constraints.gridy = 3;
        frame.add(button3, constraints);
        button3.addActionListener(e -> {
            if (textField.getText().matches("[1-9]*")) {
                textField.setText(textField.getText() + "3");
                return;
            }
            textField.setText("3");
        });

        constraints.gridx = 3;
        constraints.gridy = 3;
        frame.add(multiplyButton, constraints);
        multiplyButton.addActionListener(e -> {
            if (operator == null) {
                operator = "*";
                operand = Integer.parseInt(textField.getText());
                textField.setText(textField.getText() + "*");
                return;
            }
            if (textField.getText().matches("[0-9]*")) {
                operand = calc(Integer.parseInt(textField.getText()));
                operator = "*";
                textField.setText(operand + "*");
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 4;
        frame.add(button0, constraints);
        button0.addActionListener(e -> {
            if (textField.getText().equals("0")) {
                textField.setText("0");
                return;
            }
            textField.setText(textField.getText() + "0");
        });

        constraints.gridx = 3;
        constraints.gridy = 4;
        frame.add(divideButton, constraints);
        divideButton.addActionListener(e -> {
            if (operator == null) {
                operator = "/";
                operand = Integer.parseInt(textField.getText());
                textField.setText(textField.getText() + "/");
                return;
            }
            if (textField.getText().matches("[0-9]*")) {
                operand = calc(Integer.parseInt(textField.getText()));
                operator = "/";
                textField.setText(operand + "/");
            }
        });

        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 5;
        frame.add(applyButton, constraints);
        applyButton.addActionListener(e -> {
            if (operator != null) {
                if (textField.getText().matches("[0-9]*")) {
                    textField.setText("" + calc(Integer.parseInt(textField.getText())));
                }
            }
        });

        constraints.gridwidth = 1;
        constraints.gridx = 3;
        constraints.gridy = 5;
        frame.add(ACButton, constraints);
        button0.addActionListener(e -> {
            if (textField.getText().matches("[0-9]*") && !textField.getText().startsWith("0")) {
                textField.setText(textField.getText() + "0");
                return;
            }
            textField.setText("0");
        });

        frame.setVisible(true);
    }
}
