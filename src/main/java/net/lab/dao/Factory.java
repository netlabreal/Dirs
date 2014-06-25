package net.lab.dao;

/**
 * Created by lab on 10.06.2014.
 */
public class Factory {
    private static Factory instance=null;
    private static StreetsDao streetsDao = null;

    public static synchronized Factory getInstance(){
        if (instance == null){instance = new Factory();}
        return instance;
    }

    public StreetsDao getStreetsDao(){
        if(streetsDao==null){streetsDao=new StreetsDaoImplementation();}
        return streetsDao;
    }
}
