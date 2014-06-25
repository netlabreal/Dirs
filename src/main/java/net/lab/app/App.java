package net.lab.app;

import net.lab.ui.MForm;

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
            }
        });
    }
}
