package net.lab.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lab on 11.06.2014.
 */
public class StreetsDialog extends JDialog {
    private DataStreets ds;

    public StreetsDialog(){

    }
    public StreetsDialog(int x,int y, int w, int z, String s, DataStreets d){
        ds = d;
        Init(x,y,w,z,s);
    }

    public void Init(int x,int y, int w, int z, String s) {
        setTitle(s);
        setBounds(x, y, w, z);
        //*************************************************//
        setLayout(new GridLayout());
        JLabel l1 = new JLabel("Название улицы : ");
        JLabel l2 = new JLabel("Примечание : ");

        JTextField str_txt = new JTextField(65);
        JTextField prim_txt = new JTextField(65);

        JButton bb = new JButton("real");
        bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ds.tt.setModel(ds.GetData());ds.SetSizeColumns();
            }
        });

        add(l1);add(str_txt);
        add(l2);add(prim_txt);
        add(bb);
        //*************************************************//
    }
}
