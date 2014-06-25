package net.lab.app;

import net.lab.ui.DataStreets;
import net.lab.ui.MForm;
import net.lab.ui.StreetsDialog;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                MForm mf = new MForm();
                //DataStreets dds = null;
                //StreetsDialog sd = new StreetsDialog(100,500,520,130,"Добавить запись",dds);
                //sd.setModal(true);
                //sd.setVisible(true);
            }
        });
    }
}
