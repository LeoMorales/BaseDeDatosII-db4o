package tuto_db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

//import java.util. Scanner;

/**
 *
 * @author
 */

public class Consulta{

    //String matri,nomb,apellid,carr,are;

    public static void main(String[] args){
    	ObjectContainer db= Db4oEmbedded.openFile("RegistroPilotos.db4o");
    	
    	try{
    		storeFirstPilot(db);
            storeSecondPilot(db);
            retrieveAllPilots(db);
            retrievePilotByName(db);
            retrievePilotByExactPoints(db);
            updatePilot(db);
            deleteFirstPilotByName(db);
            deleteSecondPilotByName(db);

    	}
    	finally{
    		db.close();
    	}
	}

    public static void listResult(ObjectSet<Object> result){
        System.out.println(result.size());
        while(result.hasNext()) {
            System.out.println(result.next());
        }
    }
	
    public static void storeFirstPilot(ObjectContainer db) {
        Pilot pilot1=new Pilot("Guillermo Urrutio",100);
        db.store(pilot1);
        System.out.println("Stored "+pilot1);
    }

    public static void storeSecondPilot(ObjectContainer db) {
        Pilot pilot2=new Pilot("Rubens Barrichello",99);
        db.store(pilot2);
        System.out.println("Stored "+pilot2);
    }

    public static void retrieveAllPilots(ObjectContainer db) {
        Pilot proto=new Pilot(null,0);
        ObjectSet<Object> result = db.queryByExample(proto);
        //ObjectSet result=db.get(proto);
        listResult(result);
    }

    public static void retrievePilotByName(ObjectContainer db) {
        Pilot proto=new Pilot("Guillermo Urrutio",0);
        ObjectSet<Object> result=db.queryByExample(proto);
        listResult(result);
    }
    
    public static void retrievePilotByExactPoints(ObjectContainer db) {
        Pilot proto=new Pilot(null,100);
        ObjectSet<Object> result=db.queryByExample(proto);
        listResult(result);
    }

    public static void updatePilot(ObjectContainer db) {
        ObjectSet<Object> result=db.queryByExample(new Pilot("Guillermo Urrutio",0));
        Pilot found=(Pilot)result.next();
        found.addPoints(11);
        db.store(found);
        System.out.println("Added 11 points for "+found);
        retrieveAllPilots(db);
    }

    public static void deleteFirstPilotByName(ObjectContainer db) {
        ObjectSet<Object> result=db.queryByExample(new Pilot("Guillermo Urrutio",0));
        Pilot found=(Pilot)result.next();
        db.delete(found);
        System.out.println("Deleted "+found);
        retrieveAllPilots(db);
    }

    public static void deleteSecondPilotByName(ObjectContainer db) {
        ObjectSet<Object> result=db.queryByExample(new Pilot("Rubens Barrichello",0));
        Pilot found=(Pilot)result.next();
        db.delete(found);
        System.out.println("Deleted "+found);
        retrieveAllPilots(db);
    }


}//fin clase Consulta

	
	

