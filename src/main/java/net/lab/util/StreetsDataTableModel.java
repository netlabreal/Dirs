package net.lab.util;

import net.lab.entity.Streets;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StreetsDataTableModel extends AbstractTableModel {
    private List data;
    private int ColumnCount = 3;
    private int selectedId=0;
    private int selectedRow=0;

    public StreetsDataTableModel(List rr){
        data = rr;
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return ColumnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Streets rdm = (Streets)data.get(rowIndex);
        switch (columnIndex) {
            case 0: return rdm.getId();
            case 1: return rdm.getName();
            case 2: return rdm.getPrim();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Код улицы";
            case 1: return "Название улицы";
            case 2: return "Примечание";
        }
        return null;
    }

}
