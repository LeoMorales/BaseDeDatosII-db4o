package juzgado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class CreadorDeInstancias {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ObjectContainer db= Db4oEmbedded.openFile("BDJuzgado.db4o");
		try{
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
	        
			System.out.println("\nPersonas creadas y almacenadas: "+"\n"+persoGuille +"\n"+ persoPablo+ "\n"+persoSamuel+ "\n"+persoEmma);

	        //generando instancias de juzgados:
			Juzgado juzgado1 = new Juzgado(Juzgado.TipoFuero.civil, juez1, "San Martin 1500", "Trelew");
			Juzgado juzgado2 = new Juzgado(Juzgado.TipoFuero.comercial, juez2, "Sarmiento 1500", "Trelew");
	        Juzgado juzgado3 = new Juzgado(Juzgado.TipoFuero.penal, juez3, "Belgrano 1500", "Trelew");
	        Juzgado juzgado4 = new Juzgado(Juzgado.TipoFuero.civil, juez4, "Mitre 1500", "Rawson");
	        
	        System.out.println("\nJuzgados creados y almacenados: "+"\n"+juzgado1 +"\n"+ juzgado2 + "\n"+juzgado3+ "\n"+juzgado4);

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
			//En el constructor de Causa, causa4 se agrega a la coleccion de causas de juzgado4
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
			
			System.out.println("\nCausas creadas y almacenadas: "+"\n"+causa1 +"\n"+ causa2+ "\n"+causa3+ "\n"+causa4+ "\n"+causa5);

			//Almacenamos los juzgados, luego de que las causas se han agregado a las colecciones de dichos juzgados:
	        db.store(juzgado1);
	        db.store(juzgado2);
	        db.store(juzgado3);
	        db.store(juzgado4);
	        
	        System.out.println("Al final juzgado4 tiene: "+juzgado4.getCausas().size()+" causas");
	        
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


}
