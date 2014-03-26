package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AjoutCategorieDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel label1;
    private JLabel label2;
    private JTextField _nomcat = new JTextField();
    private JTextField _pxcat = new JTextField();
    private JButton buttonCancel;

    public AjoutCategorieDialog(Frame parent) {

        super(parent, true);
        setTitle("Ajout categorie");
        getContentPane().setLayout(new BorderLayout());
        setSize(403, 129);
        setVisible(false);
        label1 = new JLabel("Categorie :");
        label2 = new JLabel("Prix :");
        _nomcat = new JTextField();
        _nomcat.setPreferredSize(new Dimension(120, 24));
        _pxcat = new JTextField();
        _pxcat.setPreferredSize(new Dimension(120, 24));
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");
        JPanel north = new JPanel();
        north.add(label1);
        north.add(_nomcat);
        this.add(north, BorderLayout.NORTH);
        JPanel centre = new JPanel();
        centre.add(label2);
        centre.add(_pxcat);
        this.add(centre, BorderLayout.CENTER);
        JPanel sud = new JPanel();
        sud.add(buttonOK);
        sud.add(buttonCancel);
        this.add(sud, BorderLayout.SOUTH);

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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    String getNomCat() {
        return _nomcat.getText();
    }

    String getPxCat() {
        return _pxcat.getText();
    }

    private void onOK() {

        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

}
