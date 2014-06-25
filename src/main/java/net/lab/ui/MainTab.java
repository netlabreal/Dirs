package net.lab.ui;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainTab extends JPanel {
    private JLabel lblTitle;

    public MainTab(String s){
        setLayout(new GridBagLayout());
        setOpaque(false);
        JButton btnClose = new JButton(MetalIconFactory.getInternalFrameCloseIcon(16));
        btnClose.setBorder(null);

        lblTitle = new JLabel(s);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(lblTitle, gbc);
        gbc.gridx++;
        gbc.weightx = 0;

        add(btnClose, gbc);

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(lblTitle.getText());
                Tabs.getInstance().DelTab(lblTitle.getText());
            }
        });
    }

}
