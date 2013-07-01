package tuto_db4o;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class ConsultaSODA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ObjectContainer db= Db4oEmbedded.openFile("RegistroPilotos.db4o");
		try {
			storeMyMarca(db);
			storeSecondMyMarca(db);
			getMarcas(db);
			deleteMarca(db);
		}
		finally
		{
			db.close();
		}
	}

	private static void deleteMarca(ObjectContainer db) {
		// TODO Auto-generated method stub
		Db4o.configure().objectClass(MyMarca.class).cascadeOnDelete(true);
		ObjectSet result=db.queryByExample(new MyMarca(null));
        while(result.hasNext()) {
            db.delete(result.next());
        }
	}

	private static void getMarcas(ObjectContainer db) {
		// TODO Auto-generated method stub
		Query query = db.query();
		query.constrain(MyMarca.class);
		//Subconsulta List Pilotos
		Query pilotQuery = query.descend("pilotos");
		pilotQuery.constrain(Pilot.class);
		pilotQuery.descend("points").constrain(10);
		
		ObjectSet<MyMarca> result = query.execute();
		
		for (MyMarca myMarca : result) {
			System.out.println(myMarca);
		}
	}

	private static void storeMyMarca(ObjectContainer db) {
		// TODO Auto-generated method stub
		MyMarca m = new MyMarca("Ferrari");
		
		Pilot p1 = new Pilot("pepe", 100);
		Pilot p2 = new Pilot("franco", 10);
		db.store(p1);
		db.store(p2);
		
		m.getPilotos().add(p1);
		m.getPilotos().add(p2);
		db.store(m);
	}
	
	private static void storeSecondMyMarca(ObjectContainer db) {
		// TODO Auto-generated method stub
		MyMarca m = new MyMarca("BMW");
		
		Pilot p1 = new Pilot("Juan", 100);
		db.store(p1);
		
		m.getPilotos().add(p1);
		db.store(m);
	}

}
