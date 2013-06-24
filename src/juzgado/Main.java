package juzgado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {

	/**
	 * @param args --
	 */
	
	public static void main(String[] args) {
		
		ObjectContainer db= Db4oEmbedded.openFile("BDJuzgado.db4o");
		try{
			generarInstancias(db);
			recuperarTodasLasCausas(db);
			
    	}
    	finally{
    		db.close();
    	}

	}
	
    public static void recuperarTodasLasCausas(ObjectContainer db) {
    	Causa protoCausa = new Causa(null, null, null, null, null);
        ObjectSet<Object> result = db.queryByExample(protoCausa);
        //listResultOC(result);
        //filtro...
        ArrayList<Causa> causas = causasMasDeDosImputados(result);
        
        listResult(causas);
    }
    
    public static ArrayList<Causa> causasMasDeDosImputados(ObjectSet<Object> causas) {
        
    	Causa unaCausa;
    	ArrayList<Causa> causasRet = new ArrayList<Causa>();
        while (causas.hasNext()) {
			 unaCausa = (Causa) causas.next();
			 if (unaCausa.getImputados().size() > 1) {
				causasRet.add(unaCausa);
			}
			 
		}

    	return causasRet;
		
	}

    public static void listResult(ArrayList<Causa> causas){
        System.out.println(causas.size());
        Iterator<Causa> iterador = causas.iterator();
        
        while(iterador.hasNext()) {
            System.out.println(iterador.next().toString());
        }
    }
    
    public static void listResultOC(ObjectSet<Object> result){
        System.out.println(result.size());
        while(result.hasNext()) {
            System.out.println(result.next());
        }
    }
	
	public static void generarInstancias(ObjectContainer db) {
		//generando instancias de jueces:
		Juez juez1 = new Juez("Delfino German", 1000, "a");
        db.store(juez1);
        Juez juez2 = new Juez("Belligoy Federico", 2000, "b");
        db.store(juez2);
        Juez juez3 = new Juez("Abal Diego", 3000, "c");
        db.store(juez3);
        System.out.println("Jueces creados y almacenados: "+"\n"+juez1 +"\n"+ juez2 + "\n"+juez3);

        //generando instancias de juzgados:
		Juzgado juzgado1 = new Juzgado(Juzgado.TipoFuero.civil, juez1, "San Martin 1500", "Trelew");
        db.store(juzgado1);
        Juzgado juzgado2 = new Juzgado(Juzgado.TipoFuero.comercial, juez2, "Sarmiento 1500", "Trelew");
        db.store(juzgado2);
        Juzgado juzgado3 = new Juzgado(Juzgado.TipoFuero.penal, juez3, "Belgrano 1500", "Trelew");
        db.store(juzgado3);
        
        System.out.println("\nJuzgados creados y almacenados: "+"\n"+juzgado1 +"\n"+ juzgado2 + "\n"+juzgado3);

        //generando instancias de personas:
		Persona persona1 = new Persona("Guillermo", "Urrupia", 30888777, nuevaFechaDesdeString("1989/5/2"), "Masculino");
		db.store(persona1);
        
		Persona persona2 = new Persona("Pablo", "Zavarro", 37555666, nuevaFechaDesdeString("1992/6/2"), "Masculino");
		db.store(persona2);
        
		Persona persona3 = new Persona("Samuel", "Almocacid", 31888777, nuevaFechaDesdeString("1989/7/2"), "Masculino");
		db.store(persona3);
        
		Persona persona4 = new Persona("Emmanuel", "Tominguez", 30888777, nuevaFechaDesdeString("1987/8/2"), "Masculino");
		db.store(persona4);
        
		System.out.println("\nPersonas creadas y almacenadas: "+"\n"+persona1 +"\n"+ persona2+ "\n"+persona3+ "\n"+persona4);

		//generando instancias de causas:
		ArrayList<Persona> imputados = new ArrayList<Persona>();
		imputados.add(persona2);
		imputados.add(persona4);
		ArrayList<Persona> testigos = new ArrayList<Persona>();
		testigos.add(persona1);
		Causa causa1 = new Causa(juzgado1, 0001, imputados, testigos, "Culpables");
		db.store(causa1);

		//Causa2:
		
		ArrayList<Persona> imputados1 = new ArrayList<Persona>();
		imputados1.add(persona3);
		
		Causa causa2 = new Causa(juzgado2, 0002, imputados1, testigos, "");
		db.store(causa2);
		
		//Causa3:
		Causa causa3 = new Causa(juzgado3, 0003, imputados, testigos, "Inocentes");
		db.store(causa3);

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
