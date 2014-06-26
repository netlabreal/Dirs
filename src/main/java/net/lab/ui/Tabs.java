package net.lab.ui;

import javax.swing.*;

public class Tabs extends JTabbedPane {
    private static int count;
    private static Tabs instance;
    public Tabs(){
        count = 0;
    }

    public static synchronized Tabs getInstance(){
        if(instance==null){instance = new Tabs();}
        return instance;
    }

    public void NewTab(String s, Integer in){
        int ind = indexOfTab(s);
        if(ind==-1)
        {
            if(in==0)
            {
                addTab(s, new DataStreets(s));
                setTabComponentAt(count, new MainTab(s));
                setSelectedIndex(count);
                count++;
            }
            else {
                addTab(s,new JPanel());
                setTabComponentAt(count, new MainTab(s));
                setSelectedIndex(count);
                count++;
            }
        }
    }
    public void DelTab(String s){
        int ind = indexOfTab(s);
        removeTabAt(ind);
        count--;
    }

}
