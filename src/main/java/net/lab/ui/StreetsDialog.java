package net.lab.ui;

import javafx.embed.swing.JFXPanel;
import net.lab.dao.Factory;
import net.lab.entity.Streets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by lab on 11.06.2014.
 */
public class StreetsDialog extends JDialog {
    private DataStreets ds;
    public int Param=0;
    public Streets street;
    public JTextField str_txt = new JTextField(35);

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
        setLayout(new BorderLayout());
        JLabel l1 = new JLabel("Название улицы : ");
        JLabel l2 = new JLabel("Примечание : ");

        final JTextField prim_txt = new JTextField(35);

        JButton bb = new JButton("real");
        bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ds.tt.setModel(ds.GetData());ds.SetSizeColumns();
            }
        });
       // new GridBagConstraints()
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(10,0,0,0);
        panel.add(l1, c);
        c.insets = new Insets(2,0,0,0);
        c.gridy++;
        panel.add(l2, c);

        c.gridy=0;
        c.gridx++;
        c.insets = new Insets(10,0,0,0);
        panel.add(str_txt, c);
        c.gridy++;

        c.insets = new Insets(10,0,0,0);

        panel.add(prim_txt, c);
        c.gridy++;
        c.gridx=0;

        JButton save = new JButton("Сохранить");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Param == 1) {
                    Streets st = new Streets();
                    st.setName(str_txt.getText());
                    st.setPrim(prim_txt.getText());
                    try {
                        Factory.getInstance().getStreetsDao().addStreet(st);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
                if(Param==2){
                    try {
                        Factory.getInstance().getStreetsDao().updateStreet(street);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            setVisible(false);
            ds.tt.setModel(ds.GetData());
            ds.SetSizeColumns();
            }
        });
        panel.add(save ,c);
        c.gridx++;
        JButton ex = new JButton("Выход");
        ex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ds.tt.setModel(ds.GetData());ds.SetSizeColumns();
            }
        });
        panel.add(ex, c);



        add(panel, BorderLayout.NORTH);




        //add(l1,new GridBagConstraints(0,0,0,0,1,1,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2, 2, 1, 1),0,0));add(str_txt,new GridBagConstraints(1,0,2,0,1,1,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(0, 2, 2, 2),0,0));
       // add(l2,new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,new Insets(2, 2, 2, 2),0,0));add(prim_txt,new GridBagConstraints(1,1,1,1,1,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,new Insets(2, 2, 2, 2),0,0));
        //add(bb,new GridBagConstraints(0,2,1,1,1,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,new Insets(2, 2, 2, 2),0,0));
        //*************************************************//
    }
}
