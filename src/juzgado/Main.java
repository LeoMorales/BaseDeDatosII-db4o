package juzgado;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Main {

	/**
	 * @param args --
	 */
	enum TipoFuero{civil, comercial, laboral, penal};
	
	public static void main(String[] args) {
		
		ObjectContainer db= Db4oEmbedded.openFile("BDJuzgado.db4o");
		ArrayList<Juez> jueces = new ArrayList<Juez>();
		try{
			crearJueces(db, jueces);
			crearJuzgados(db, jueces);

    	}
    	finally{
    		db.close();
    	}

	}
	
	public static void crearJueces(ObjectContainer db, ArrayList<Juez> jueces) {
		Juez juez1 = new Juez("Delfino German", 1000, "a");
		jueces.add(juez1);
        db.store(juez1);
        
        Juez juez2 = new Juez("Belligoy Federico", 2000, "b");
        jueces.add(juez2);
        db.store(juez2);
        
        Juez juez3 = new Juez("Abal Diego", 3000, "c");
        jueces.add(juez3);
        db.store(juez3);
        System.out.println("Jueces creados y almacenados: "+"\n"+juez1 +"\n"+ juez2 + "\n"+juez3);
	}

	public static void crearJuzgados(ObjectContainer db, ArrayList<Juez> jueces) {
		Juzgado juzgado1 = new Juzgado(Juzgado.TipoFuero.civil, jueces.get(0), "San Martin 1500", "Trelew");
        db.store(juzgado1);
        Juzgado juzgado2 = new Juzgado(Juzgado.TipoFuero.comercial, jueces.get(1), "Sarmiento 1500", "Trelew");
        db.store(juzgado2);
        Juzgado juzgado3 = new Juzgado(Juzgado.TipoFuero.penal, jueces.get(2), "Belgrano 1500", "Trelew");
        db.store(juzgado3);
        System.out.println("Juzgados creados y almacenados: "+"\n"+juzgado1 +"\n"+ juzgado2 + "\n"+juzgado3);
	}

}
