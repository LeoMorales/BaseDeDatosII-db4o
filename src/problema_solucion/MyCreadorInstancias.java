package problema_solucion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tuto_db4o.MyMarca;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

@SuppressWarnings("deprecation")
public class MyCreadorInstancias {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectContainer db= Db4oEmbedded.openFile("BDJuzgado.db4o");
		try
		{
			/* Gurada un objeto Juzgado */
//			storeMyJuzgado(db);
			/* Gurada un segundo objeto Juzgado */
//			storeMySecondJuzgado(db);
			/* Consulta SODA */
			getMyJuzgado(db);
			/* Elimina todos los objetos Juzgado*/
//			deleteMyJuzgado(db);
		}
		finally
		{
			db.close();
		}
	}
	
	/* Consulta SODA de los juzgados que tienen al menos una causa sin sentencia. */
	private static void getMyJuzgado(ObjectContainer db) {
		// TODO Auto-generated method stub
		Query query = db.query();
		query.constrain(Juzgado.class);
		
		//Consulta por el tipo de Juzgado
		query.descend("fuero").constrain(Juzgado.TipoFuero.civil);
		
		//Subconsulta sobre la coleccion de causas
		Query causasQuery = query.descend("causas");
		causasQuery.constrain(Causa.class);
		causasQuery.descend("sentencia").constrain(null);
		
		ObjectSet<Object> juzgados = query.execute();
		
		System.out.println("Cantidad de Juzgados: " + juzgados.size());
    	for (Object j : juzgados) {
			System.out.println(j);
			for (Causa c : ((Juzgado)j).getCausas()) {
				System.out.println("\t" + c);
			}
		}
	}

	private static void deleteMyJuzgado(ObjectContainer db) {
		// TODO Auto-generated method stub
		Db4o.configure().objectClass(Juzgado.class).cascadeOnDelete(true);
		ObjectSet result=db.queryByExample(new Juzgado(null, null, null, null));
        while(result.hasNext()) {
            db.delete(result.next());
        }
	}

	private static void storeMyJuzgado(ObjectContainer db) {
		// TODO Auto-generated method stub
		Juez myJuez = new Juez("Loo", 123, "Psss");
		
		Juzgado myJuzgado = new Juzgado(Juzgado.TipoFuero.civil, myJuez, "Pepe 50", "Rawson");
		
		Persona myPersona = new Persona("Juanciot", "Perez", 123456, nuevaFechaDesdeString("1989/10/8"), "masculino");
		
		ArrayList<Persona> myImputados1 = new ArrayList<Persona>();
		myImputados1.add(new Persona("Pepe", "pepon", 3333333, nuevaFechaDesdeString("1989/10/02"), "Masculino"));
		myImputados1.add(new Persona("Pepita", "pepon", 123123, nuevaFechaDesdeString("1989/4/02"), "Femenino"));
		ArrayList<Persona> testigos1 = new ArrayList<Persona>();
		testigos1.add(myPersona);
		Causa causa1 = new Causa(13, myImputados1, testigos1, "Culpables");
		db.store(causa1);
		
		ArrayList<Persona> myImputados2 = new ArrayList<Persona>();
		myImputados2.add(new Persona("Pepe", "pepon", 3333333, nuevaFechaDesdeString("1989/10/02"), "Masculino"));
		myImputados2.add(new Persona("Pepita", "pepon", 123123, nuevaFechaDesdeString("1989/4/02"), "Femenino"));
		ArrayList<Persona> testigos2 = new ArrayList<Persona>();
		testigos2.add(myPersona);
		Causa causa2 = new Causa(14, myImputados2, testigos2, "Culpables");
		db.store(causa2);
		
		Causa causa3 = new Causa(15, myImputados2, testigos2, "Culpables");
		db.store(causa3);
		
		myJuzgado.getCausas().add(causa1);
		myJuzgado.getCausas().add(causa2);
		myJuzgado.getCausas().add(causa3);
		db.store(myJuzgado);
	}
	
	private static void storeMySecondJuzgado(ObjectContainer db) {
		// TODO Auto-generated method stub
		Juez myJuez = new Juez("Guille", 123, "Psss");
		
		Juzgado myJuzgado = new Juzgado(Juzgado.TipoFuero.civil, myJuez, "Pepe 50", "Rawson");
		
		Persona myPersona = new Persona("Juanciot", "Perez", 123456, nuevaFechaDesdeString("1989/10/8"), "masculino");
		
		ArrayList<Persona> myImputados1 = new ArrayList<Persona>();
		myImputados1.add(new Persona("Pepe", "pepon", 3333333, nuevaFechaDesdeString("1989/10/02"), "Masculino"));
		myImputados1.add(new Persona("Pepita", "pepon", 123123, nuevaFechaDesdeString("1989/4/02"), "Femenino"));
		ArrayList<Persona> testigos1 = new ArrayList<Persona>();
		testigos1.add(myPersona);
		Causa causa1 = new Causa(2, myImputados1, testigos1, "Culpables");
		db.store(causa1);
		
		ArrayList<Persona> myImputados2 = new ArrayList<Persona>();
		myImputados2.add(new Persona("Pepe", "pepon", 3333333, nuevaFechaDesdeString("1989/10/02"), "Masculino"));
		myImputados2.add(new Persona("Pepita", "pepon", 123123, nuevaFechaDesdeString("1989/4/02"), "Femenino"));
		ArrayList<Persona> testigos2 = new ArrayList<Persona>();
		testigos2.add(myPersona);
		Causa causa2 = new Causa(1, myImputados2, testigos2, "Culpables");
		db.store(causa2);
		
		Causa causa3 = new Causa(3, myImputados2, testigos2, null);
		db.store(causa3);
		
		myJuzgado.getCausas().add(causa1);
		myJuzgado.getCausas().add(causa2);
		myJuzgado.getCausas().add(causa3);
		db.store(myJuzgado);
	}
	
	public static Date nuevaFechaDesdeString(String strFecha) {
	     SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy/MM/dd");
	     Date fecha = null;
	     try {
				fecha = formatoDelTexto.parse(strFecha);
	     } catch (java.text.ParseException e) {
				e.printStackTrace();
	     }
	     return fecha;
	}
}
