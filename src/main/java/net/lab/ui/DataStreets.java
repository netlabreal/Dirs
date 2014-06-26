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

    private JButton b_add;
    private JButton b_del;
    private JButton b_izm;


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
                Tabs.getInstance().DelTab(name);
            }
        });
        //*********************************//
        add(b, BorderLayout.SOUTH);
        InitMenu();
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

        if(tt.getModel().getRowCount()==0){
            b_del.setEnabled(false);
            b_izm.setEnabled(false);
        }
        else{
            b_del.setEnabled(true);
            b_izm.setEnabled(true);
        }
    }

    public void SetButtons(){
        JPanel p_b = new JPanel();
        p_b.setLayout(new GridLayout());
        b_add = new JButton("Добавить запись");
        b_del = new JButton("Удалить запись");
        b_izm = new JButton("Изменить запись");

        b_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Streets st1 = new Streets();
                st1.setId((Integer)tt.getModel().getValueAt(selectedRow,0));
                st1.setName((String) tt.getModel().getValueAt(selectedRow, 1));
                st1.setPrim((String) tt.getModel().getValueAt(selectedRow, 2));
                try {
                    Factory.getInstance().getStreetsDao().delStreet(st1);
                    tt.setModel(GetData());
                    SetSizeColumns();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if(tt.getModel().getRowCount()!=0)
                {
                    tt.setRowSelectionInterval(0, 0);
                }
            }
        });

        b_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double f = getBounds().getCenterX();
                int x = (getWidth()/2)-150;	int y = (getHeight()/2)-25;
                StreetsDialog sd = new StreetsDialog(x,y,520,130,"Добавить запись",dds);
                sd.Param = 1;    sd.Srow = selectedRow;
                sd.setModal(true); sd.setVisible(true);

            }
        });
        b_izm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double f = getBounds().getCenterX();
                int x = (getWidth() / 2) - 150;
                int y = (getHeight() / 2) - 25;
                StreetsDialog sd = new StreetsDialog(x, y, 520, 130, "Изменить запись", dds);
                Streets st1 = new Streets();
                st1.setId((Integer) tt.getModel().getValueAt(selectedRow, 0));
                st1.setName((String) tt.getModel().getValueAt(selectedRow, 1));
                st1.setPrim((String) tt.getModel().getValueAt(selectedRow, 2));

                sd.Param = 2;
                sd.street = st1;
                sd.str_txt.setText(st1.getName());
                sd.prim_txt.setText(st1.getPrim());
                sd.Srow = selectedRow;
                sd.setModal(true);
                sd.setVisible(true);
            }
        });
        p_b.add(b_add);p_b.add(b_izm);p_b.add(b_del);
        add(p_b, BorderLayout.NORTH);
    }

    public void InitMenu() {
        JPopupMenu poupmenu = new JPopupMenu();
        JMenuItem jMenuItem_A = new JMenuItem("Добавить запись");
        JMenuItem jMenuItem_I = new JMenuItem("Изменить запись");
        JMenuItem jMenuItem_D = new JMenuItem("Удалить запись");
        poupmenu.add(jMenuItem_A);poupmenu.add(jMenuItem_I);poupmenu.add(jMenuItem_D);
        //***********************************************//
        jMenuItem_A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double f = getBounds().getCenterX();
                int x = (getWidth()/2)-150;	int y = (getHeight()/2)-25;
                StreetsDialog sd = new StreetsDialog(x,y,520,130,"Добавить запись",dds);
                sd.Param = 1;    sd.Srow = selectedRow; sd.setModal(true); sd.setVisible(true);
            }
        });
        //***********************************************//
        jMenuItem_I.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double f = getBounds().getCenterX();
                int x = (getWidth() / 2) - 150;
                int y = (getHeight() / 2) - 25;
                StreetsDialog sd = new StreetsDialog(x, y, 520, 130, "Изменить запись", dds);
                Streets st1 = new Streets();
                st1.setId((Integer) tt.getModel().getValueAt(selectedRow, 0));
                st1.setName((String) tt.getModel().getValueAt(selectedRow, 1));
                st1.setPrim((String) tt.getModel().getValueAt(selectedRow, 2));

                sd.Param = 2; sd.street = st1;
                sd.str_txt.setText(st1.getName()); sd.prim_txt.setText(st1.getPrim());
                sd.Srow = selectedRow; sd.setModal(true); sd.setVisible(true);
            }
        });
        //***********************************************//
        jMenuItem_D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Streets st1 = new Streets();
                st1.setId((Integer)tt.getModel().getValueAt(selectedRow,0));
                st1.setName((String) tt.getModel().getValueAt(selectedRow, 1));
                st1.setPrim((String) tt.getModel().getValueAt(selectedRow, 2));
                try {
                    Factory.getInstance().getStreetsDao().delStreet(st1);
                    tt.setModel(GetData());
                    SetSizeColumns();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if(tt.getModel().getRowCount()!=0)
                {
                    tt.setRowSelectionInterval(0, 0);
                }
            }
        });
        //***********************************************//
        tt.setComponentPopupMenu(poupmenu);
    }
}
