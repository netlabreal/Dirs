package net.lab.ui;

import net.lab.dao.Factory;
import net.lab.entity.Streets;
import net.lab.util.StreetsDataTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lab on 10.06.2014.
 */
public class DataStreets extends JPanel {
    private String name;
    public JTable tt;
    public DataStreets dds = null;
    public  int selectedRow=0;

    public DataStreets(String ss){
        name=ss;
        setLayout(new BorderLayout());
        SetButtons();
        dds = this;
        tt = new JTable(GetData());

        ListSelectionModel cellSelectionModel = tt.getSelectionModel();
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                selectedRow = tt.getSelectedRow();
                System.out.println(selectedRow);
            }
        });

        SetSizeColumns();

        JScrollPane scroll = new JScrollPane(tt);
        add(scroll);

        JButton b = new JButton("ЗАКРЫТЬ ВКЛАДКУ");

        //*********************************//
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tt.setModel(GetData());
                SetSizeColumns();
                //Tabs.getInstance().DelTab(name);
            }
        });
        //*********************************//
        add(b, BorderLayout.SOUTH);
        tt.requestFocusInWindow();
    }

    public StreetsDataTableModel GetData(){
        List list = null;
        try {
            list = Factory.getInstance().getStreetsDao().getAllStreets();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        StreetsDataTableModel sdtm = new StreetsDataTableModel(list);
        return sdtm;
    }

    public void SetSizeColumns(){
        tt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //tt.setRowSelectionInterval(0,0);

        TableColumnModel tcm = tt.getColumnModel();
        TableColumn typeColumn = tcm.getColumn(0);
        typeColumn.setMaxWidth(100); typeColumn.setMinWidth(100);
        typeColumn = tcm.getColumn(1);
        typeColumn.setMaxWidth(900); typeColumn.setMinWidth(500);
        typeColumn = tcm.getColumn(2);
        typeColumn.setMaxWidth(350); typeColumn.setMinWidth(100);
    }

    public void SetButtons(){
        JPanel p_b = new JPanel();
        p_b.setLayout(new GridLayout());
        JButton b_add = new JButton("Добавить запись");
        JButton b_del = new JButton("Удалить запись");
        JButton b_izm = new JButton("Изменить запись");

        b_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(tt.getModel().getValueAt(selectedRow, 0));
                Integer ff = (Integer)tt.getModel().getValueAt(selectedRow,0);
                try {
                    Streets st = Factory.getInstance().getStreetsDao().getStreetById(ff);
                    Factory.getInstance().getStreetsDao().delStreet(st);
                    tt.setModel(GetData());
                    SetSizeColumns();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        b_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double f = getBounds().getCenterX();
                int x = (getWidth()/2)-150;	int y = (getHeight()/2)-25;
                StreetsDialog sd = new StreetsDialog(x,y,520,130,"Добавить запись",dds);
                sd.Param = 1;
                sd.setModal(true); sd.setVisible(true);

            }
        });
        b_izm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double f = getBounds().getCenterX();
                int x = (getWidth()/2)-150;
                int y = (getHeight()/2)-25;
                StreetsDialog sd = new StreetsDialog(x,y,520,130,"Добавить запись",dds);
                Streets st1 = new Streets();
                st1.setId((Integer)tt.getModel().getValueAt(selectedRow,0));
                st1.setName((String) tt.getModel().getValueAt(selectedRow, 1));
                st1.setPrim((String) tt.getModel().getValueAt(selectedRow, 2));

                sd.Param = 2;sd.street = st1;sd.str_txt.setText(st1.getName());
                sd.setModal(true);
                sd.setVisible(true);
            }
});

        p_b.add(b_add);p_b.add(b_izm);p_b.add(b_del);
        add(p_b,BorderLayout.NORTH);


    }
}
