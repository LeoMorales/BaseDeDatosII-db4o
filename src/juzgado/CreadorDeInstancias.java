package juzgado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class CreadorDeInstancias {

	public static void main(String[] args) {

		ObjectContainer db= Db4oEmbedded.openFile("BDJuzgado.db4o");
		try{
			System.out.println("------------------------------------- INICIO CARGA -------------------------------------");
			//generando instancias de jueces:
			Juez juez1 = new Juez("Delfino German", 1000, "a");
	        db.store(juez1);
	        Juez juez2 = new Juez("Belligoy Federico", 2000, "b");
	        db.store(juez2);
	        Juez juez3 = new Juez("Abal Diego", 3000, "c");
	        db.store(juez3);
	        Juez juez4 = new Juez("Pezzota Mauricio", 4000, "d");
	        db.store(juez4);
	        System.out.println("Jueces creados y almacenados: "+"\n"+juez1 +"\n"+ juez2 + "\n"+juez3+ "\n"+juez4);


	        //generando instancias de personas:
			Persona persoGuille = new Persona("Guillermo", "Urrupia", 30888777, nuevaFechaDesdeString("1989/5/2"), "Masculino");
			db.store(persoGuille);
	        
			Persona persoPablo = new Persona("Pablo", "Zavarro", 37555666, nuevaFechaDesdeString("1992/6/2"), "Masculino");
			db.store(persoPablo);
	        
			Persona persoSamuel = new Persona("Samuel", "Almocacid", 31888777, nuevaFechaDesdeString("1989/7/2"), "Masculino");
			db.store(persoSamuel);
	        
			Persona persoEmma = new Persona("Emmanuel", "Tominguez", 30888777, nuevaFechaDesdeString("1987/8/2"), "Masculino");
			db.store(persoEmma);
	        
			Persona persoFurch = new Persona("Walter", "Furch", 18888777, nuevaFechaDesdeString("1970/8/2"), "Masculino");
			db.store(persoFurch);
			
			Persona persoBenedetto = new Persona("Guillermo", "Benedetto", 20888777, nuevaFechaDesdeString("1987/8/2"), "Masculino");
			db.store(persoBenedetto);
			
			Persona persoCarbonero = new Persona("Mario", "Carbonero", 25888777, nuevaFechaDesdeString("1982/8/2"), "Masculino");
			db.store(persoCarbonero);
			
			Persona persoCampestrini = new Persona("Matias", "Campestrini", 23888777, nuevaFechaDesdeString("1985/8/2"), "Masculino");
			db.store(persoCampestrini);
			
			System.out.println("\nPersonas creadas y almacenadas: "+"\n"+persoGuille +"\n"+ persoPablo+ "\n"+persoSamuel+ "\n"+persoEmma+ "\n"+persoFurch+ "\n"+persoBenedetto+ "\n"+persoCarbonero+ "\n"+persoCampestrini);

	        //generando instancias de juzgados:
			Juzgado juzgado1 = new Juzgado(Juzgado.TipoFuero.civil, juez1, "San Martin 1500", "Trelew");
			Juzgado juzgado2 = new Juzgado(Juzgado.TipoFuero.comercial, juez2, "Sarmiento 1500", "Trelew");
	        Juzgado juzgado3 = new Juzgado(Juzgado.TipoFuero.penal, juez3, "Belgrano 1500", "Trelew");
	        Juzgado juzgado4 = new Juzgado(Juzgado.TipoFuero.civil, juez4, "Mitre 1500", "Rawson");
	        Juzgado juzgado5 = new Juzgado(Juzgado.TipoFuero.civil, juez2, "Roca 1500", "Madryn");
			//Almacenamos los juzgados.
	        db.store(juzgado1);
	        db.store(juzgado2);
	        db.store(juzgado3);
	        db.store(juzgado4);
	        db.store(juzgado5);
	        
	        System.out.println("\nJuzgados creados y almacenados: "+"\n"+juzgado1 +"\n"+ juzgado2 + "\n"+juzgado3+ "\n"+juzgado4+ "\n"+juzgado5);

			//generando instancias de causas:
	        //Causa1
			ArrayList<Persona> imputados = new ArrayList<Persona>();
			imputados.add(persoPablo);
			imputados.add(persoEmma);
			ArrayList<Persona> testigos = new ArrayList<Persona>();
			testigos.add(persoGuille);
			Causa causa1 = new Causa(juzgado1, 0001, imputados, testigos, "Culpables");
			db.store(causa1);

			//Causa2:
			ArrayList<Persona> imputados1 = new ArrayList<Persona>();
			imputados1.add(persoSamuel); //Solo un imputado!
			
			Causa causa2 = new Causa(juzgado2, 0002, imputados1, testigos, "");
			db.store(causa2);
			
			//Causa3:
			Causa causa3 = new Causa(juzgado3, 0003, imputados, testigos, "Inocentes");
			db.store(causa3);
						
			//Dos causas (4 y 5), una con sentencia y otra sin sentencia:
			//Causa4
			ArrayList<Persona> imputadosC4 = new ArrayList<Persona>();
			imputadosC4.add(persoPablo);
			imputadosC4.add(persoSamuel);
			ArrayList<Persona> testigosC4 = new ArrayList<Persona>();
			testigosC4.add(persoEmma);
			Causa causa4 = new Causa(juzgado4, 0004, imputadosC4, testigosC4, "Culpables. Pablo y Samuel mataron a Guille.Pablo autor intelectual, Samuel autor material");
			db.store(causa4);

			//Causa5
			ArrayList<Persona> imputadosC5 = new ArrayList<Persona>();
			imputadosC5.add(persoPablo);
			imputadosC5.add(persoGuille);
			ArrayList<Persona> testigosC5 = new ArrayList<Persona>();
			testigosC5.add(persoEmma);
			Causa causa5 = new Causa(juzgado4, 0005, imputadosC5, testigosC5, null);
			db.store(causa5);
			
			//Causa6
			ArrayList<Persona> imputadosC6 = new ArrayList<Persona>();
			imputadosC6.add(persoPablo);
			imputadosC6.add(persoCarbonero);
			ArrayList<Persona> testigosC6 = new ArrayList<Persona>();
			testigosC6.add(persoCampestrini);
			Causa causa6 = new Causa(juzgado5, 0006, imputadosC6, testigosC6, null);
			db.store(causa6);
			//Causa7
			ArrayList<Persona> imputadosC7 = new ArrayList<Persona>();
			imputadosC7.add(persoFurch);
			ArrayList<Persona> testigosC7 = new ArrayList<Persona>();
			testigosC7.add(persoBenedetto);
			Causa causa7 = new Causa(juzgado5, 0007, imputadosC7, testigosC7, "Inocente");
			db.store(causa7);
			System.out.println("\nCausas creadas y almacenadas: "+"\n"+causa1 +"\n"+ causa2+ "\n"+causa3+ "\n"+causa4+ "\n"+causa5+ "\n"+causa6+ "\n"+causa7);

			storeMyJuzgado(db);
			
			System.out.println("\nCausas creadas y almacenadas: "+"\n"+causa1 +"\n"+ causa2+ "\n"+causa3+ "\n"+causa4+ "\n"+causa5);


	        System.out.println("\n----------------------------------- CARGA FINALIZADA -----------------------------------");
	        db.commit();

    	}
    	finally{
    		db.close();
    	}

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
	
	private static void storeMyJuzgado(ObjectContainer db) {
		// TODO Auto-generated method stub
		Juez myJuez = new Juez("Loo", 123, "Psss");
		
		Juzgado myJuzgado = new Juzgado(juzgado.Juzgado.TipoFuero.civil, myJuez, "Pepe 50", "Rawson");
		
		Persona myPersona = new Persona("Juanciot", "Perez", 123456, nuevaFechaDesdeString("1989/10/8"), "masculino");
		
		ArrayList<Persona> myImputados1 = new ArrayList<Persona>();
		myImputados1.add(new Persona("Pepe", "pepon", 3333333, nuevaFechaDesdeString("1989/10/02"), "Masculino"));
		myImputados1.add(new Persona("Pepita", "pepon", 123123, nuevaFechaDesdeString("1989/4/02"), "Femenino"));
		ArrayList<Persona> testigos1 = new ArrayList<Persona>();
		testigos1.add(myPersona);
		Causa causa1 = new Causa(myJuzgado, 13, myImputados1, testigos1, "Culpables");
		db.store(causa1);
		
		ArrayList<Persona> myImputados2 = new ArrayList<Persona>();
		myImputados2.add(new Persona("Pepe", "pepon", 3333333, nuevaFechaDesdeString("1989/10/02"), "Masculino"));
		myImputados2.add(new Persona("Pepita", "pepon", 123123, nuevaFechaDesdeString("1989/4/02"), "Femenino"));
		ArrayList<Persona> testigos2 = new ArrayList<Persona>();
		testigos2.add(myPersona);
		Causa causa2 = new Causa(myJuzgado, 14, myImputados2, testigos2, "Culpables");
		db.store(causa2);
		
		Causa causa3 = new Causa(myJuzgado, 15, myImputados2, testigos2, "Culpables");
		db.store(causa3);
		
		db.store(myJuzgado);
	}

}
