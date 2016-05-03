package gfx;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Processing;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Gui extends JFrame {

  private static final long serialVersionUID = 404210615100921952L;
  private static final int FRAMEWIDTH = 320;
  private static final int FRAMEHEIGHT = 234;
  private static final int NOROW = 4;

  private Processing pUnit;
  private JPanel contentPane;
  private JLabel lblNewLabel; // numberLabel
  private JLabel lblNewLabel_1; // equationLabel

  /**
   * Create the frame.
   */
  public Gui(Processing processingUnit) {

    pUnit = processingUnit;
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, FRAMEWIDTH + 10, FRAMEHEIGHT + 10);
    setTitle("CT - Calculator");
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GridBagLayout gbl_contentPane = new GridBagLayout();
    gbl_contentPane.columnWidths = new int[] { FRAMEWIDTH / NOROW, FRAMEWIDTH / NOROW, FRAMEWIDTH / NOROW, FRAMEWIDTH / NOROW, 0 };
    gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
    gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
    contentPane.setLayout(gbl_contentPane);

    lblNewLabel_1 = new JLabel(" ");
    lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 11));
    lblNewLabel_1.setVisible(true);
    GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
    gbc_lblNewLabel_1.anchor = GridBagConstraints.LINE_END;
    gbc_lblNewLabel_1.gridwidth = 3;
    gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel_1.gridx = 0;
    gbc_lblNewLabel_1.gridy = 0;
    contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

    JButton btnNewButton_16 = new JButton("=");
    GridBagConstraints gbc_btnNewButton_16 = new GridBagConstraints();
    gbc_btnNewButton_16.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_16.gridheight = 2;
    gbc_btnNewButton_16.insets = new Insets(0, 0, 5, 0);
    gbc_btnNewButton_16.gridx = 3;
    gbc_btnNewButton_16.gridy = 0;
    contentPane.add(btnNewButton_16, gbc_btnNewButton_16);
    btnNewButton_16.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        pUnit.equal();
        updateDisplay();
      }
    });

    lblNewLabel = new JLabel("0");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
    gbc_lblNewLabel.fill = GridBagConstraints.NONE;
    gbc_lblNewLabel.gridwidth = 3;
    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel.gridx = 0;
    gbc_lblNewLabel.gridy = 1;
    contentPane.add(lblNewLabel, gbc_lblNewLabel);

    JSeparator separator = new JSeparator();
    separator.setForeground(Color.BLACK);
    GridBagConstraints gbc_separator = new GridBagConstraints();
    gbc_separator.fill = GridBagConstraints.BOTH;
    gbc_separator.gridwidth = 4;
    gbc_separator.insets = new Insets(0, 0, 5, 0);
    gbc_separator.gridx = 0;
    gbc_separator.gridy = 2;
    contentPane.add(separator, gbc_separator);

    JButton btnNewButton_1 = new JButton("\u2190");
    btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 11));
    btnNewButton_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        pUnit.backspace();
        updateDisplay();
        ;
      }
    });
    GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
    gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_1.gridx = 0;
    gbc_btnNewButton_1.gridy = 3;
    contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

    JButton btnNewButton_2 = new JButton("C");
    btnNewButton_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pUnit.clear();
        updateDisplay();
      }
    });
    btnNewButton_2.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
    gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_2.gridx = 1;
    gbc_btnNewButton_2.gridy = 3;
    contentPane.add(btnNewButton_2, gbc_btnNewButton_2);

    JButton btnNewButton_3 = new JButton("x\u00B2"); // x square
    btnNewButton_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (pUnit.pow()) {
          updateDisplay();
        }
      }
    });
    btnNewButton_3.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
    gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_3.gridx = 2;
    gbc_btnNewButton_3.gridy = 3;
    contentPane.add(btnNewButton_3, gbc_btnNewButton_3);

    JButton btnNewButton = new JButton("\u221A"); // sqrt
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (pUnit.sqrt()) {
          updateDisplay();
        }
      }
    });
    GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
    gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
    gbc_btnNewButton.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton.gridx = 3;
    gbc_btnNewButton.gridy = 3;
    contentPane.add(btnNewButton, gbc_btnNewButton);

    JButton btnNewButton_4 = new JButton("7");
    btnNewButton_4.addActionListener((e) -> {

    });
    btnNewButton_4.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
    gbc_btnNewButton_4.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_4.gridx = 0;
    gbc_btnNewButton_4.gridy = 4;
    contentPane.add(btnNewButton_4, gbc_btnNewButton_4);

    JButton btnNewButton_5 = new JButton("8");
    btnNewButton_5.addActionListener(new CalculatorNumberListener());
    btnNewButton_5.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
    gbc_btnNewButton_5.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_5.gridx = 1;
    gbc_btnNewButton_5.gridy = 4;
    contentPane.add(btnNewButton_5, gbc_btnNewButton_5);

    JButton btnNewButton_6 = new JButton("9");
    btnNewButton_6.addActionListener(new CalculatorNumberListener());
    btnNewButton_6.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
    gbc_btnNewButton_6.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_6.gridx = 2;
    gbc_btnNewButton_6.gridy = 4;
    contentPane.add(btnNewButton_6, gbc_btnNewButton_6);

    JButton btnNewButton_15 = new JButton("+");
    btnNewButton_15.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        pUnit.operation('+');
        updateDisplay();
      }
    });
    btnNewButton_15.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_15 = new GridBagConstraints();
    gbc_btnNewButton_15.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_15.insets = new Insets(0, 0, 5, 0);
    gbc_btnNewButton_15.gridx = 3;
    gbc_btnNewButton_15.gridy = 4;
    contentPane.add(btnNewButton_15, gbc_btnNewButton_15);

    JButton btnNewButton_8 = new JButton("4");
    btnNewButton_8.addActionListener(new CalculatorNumberListener());
    btnNewButton_8.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
    gbc_btnNewButton_8.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_8.gridx = 0;
    gbc_btnNewButton_8.gridy = 5;
    contentPane.add(btnNewButton_8, gbc_btnNewButton_8);

    JButton btnNewButton_9 = new JButton("5");
    btnNewButton_9.addActionListener(new CalculatorNumberListener());
    btnNewButton_9.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
    gbc_btnNewButton_9.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_9.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_9.gridx = 1;
    gbc_btnNewButton_9.gridy = 5;
    contentPane.add(btnNewButton_9, gbc_btnNewButton_9);

    JButton btnNewButton_10 = new JButton("6");
    btnNewButton_10.addActionListener(new CalculatorNumberListener());
    btnNewButton_10.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_10 = new GridBagConstraints();
    gbc_btnNewButton_10.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_10.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_10.gridx = 2;
    gbc_btnNewButton_10.gridy = 5;
    contentPane.add(btnNewButton_10, gbc_btnNewButton_10);

    JButton btnNewButton_20 = new JButton("-");
    btnNewButton_20.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pUnit.operation('-');
        updateDisplay();
      }
    });
    btnNewButton_20.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_20 = new GridBagConstraints();
    gbc_btnNewButton_20.insets = new Insets(0, 0, 5, 0);
    gbc_btnNewButton_20.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_20.gridx = 3;
    gbc_btnNewButton_20.gridy = 5;
    contentPane.add(btnNewButton_20, gbc_btnNewButton_20);

    JButton btnNewButton_12 = new JButton("1");
    btnNewButton_12.addActionListener(new CalculatorNumberListener());
    btnNewButton_12.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_12 = new GridBagConstraints();
    gbc_btnNewButton_12.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_12.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_12.gridx = 0;
    gbc_btnNewButton_12.gridy = 6;
    contentPane.add(btnNewButton_12, gbc_btnNewButton_12);

    JButton btnNewButton_13 = new JButton("2");
    btnNewButton_13.addActionListener(new CalculatorNumberListener());
    btnNewButton_13.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_13 = new GridBagConstraints();
    gbc_btnNewButton_13.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_13.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_13.gridx = 1;
    gbc_btnNewButton_13.gridy = 6;
    contentPane.add(btnNewButton_13, gbc_btnNewButton_13);

    JButton btnNewButton_14 = new JButton("3");
    btnNewButton_14.addActionListener(new CalculatorNumberListener());
    btnNewButton_14.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_14 = new GridBagConstraints();
    gbc_btnNewButton_14.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_14.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_14.gridx = 2;
    gbc_btnNewButton_14.gridy = 6;
    contentPane.add(btnNewButton_14, gbc_btnNewButton_14);

    JButton button = new JButton("\u00D7"); // times
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pUnit.operation('*');
        updateDisplay();
      }
    });
    button.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_button = new GridBagConstraints();
    gbc_button.fill = GridBagConstraints.BOTH;
    gbc_button.insets = new Insets(0, 0, 5, 0);
    gbc_button.gridx = 3;
    gbc_button.gridy = 6;
    contentPane.add(button, gbc_button);

    JButton btnNewButton_17 = new JButton("0");
    btnNewButton_17.addActionListener(new CalculatorNumberListener());
    btnNewButton_17.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_17 = new GridBagConstraints();
    gbc_btnNewButton_17.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_17.gridwidth = 2;
    gbc_btnNewButton_17.insets = new Insets(0, 0, 0, 5);
    gbc_btnNewButton_17.gridx = 0;
    gbc_btnNewButton_17.gridy = 7;
    contentPane.add(btnNewButton_17, gbc_btnNewButton_17);

    JButton btnNewButton_19 = new JButton(".");
    btnNewButton_19.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (pUnit.appendDot()) {
          updateDisplay();
        }
      }
    });
    btnNewButton_19.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_19 = new GridBagConstraints();
    gbc_btnNewButton_19.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_19.insets = new Insets(0, 0, 0, 5);
    gbc_btnNewButton_19.gridx = 2;
    gbc_btnNewButton_19.gridy = 7;
    contentPane.add(btnNewButton_19, gbc_btnNewButton_19);

    JButton btnNewButton_11 = new JButton("\u00F7"); // division
    btnNewButton_11.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pUnit.operation('/');
        updateDisplay();
      }
    });
    btnNewButton_11.setFont(new Font("SansSerif", Font.PLAIN, 11));
    GridBagConstraints gbc_btnNewButton_11 = new GridBagConstraints();
    gbc_btnNewButton_11.fill = GridBagConstraints.BOTH;
    gbc_btnNewButton_11.gridx = 3;
    gbc_btnNewButton_11.gridy = 7;
    contentPane.add(btnNewButton_11, gbc_btnNewButton_11);
  }

  private void updateDisplay() {
    String eqLabel = pUnit.getEquationLine();
    String numLabel = pUnit.getNumberLine();
    lblNewLabel.setText(String.format("%21s", numLabel));
    lblNewLabel_1.setText(eqLabel);
  }

  private class CalculatorNumberListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JButton button = (JButton) e.getSource();
      String text = button.getText();
      if (pUnit.appendDigit(text)) {
        updateDisplay();
      }
    }

  }

}
