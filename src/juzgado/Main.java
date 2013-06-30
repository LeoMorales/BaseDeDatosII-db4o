package juzgado;

import java.util.ArrayList;
import java.util.Iterator;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {

	static String barraDivisoria = "\n======================================================================================================================================================================================";
	
	public static void main(String[] args) {
		
		ObjectContainer db= Db4oEmbedded.openFile("BDJuzgado.db4o");
		ArrayList<Causa> causasMasDeDosImputados;
		try{
			//generarInstancias(db);
			System.out.println(barraDivisoria+"\n\nCONSULTA: Mostrar las causas con sentencia que tengan mas de 2 imputados , se deberá mostrar los datos de la causa y los datos de sus imputados:\n\n"+barraDivisoria);
			causasMasDeDosImputados = recuperarTodasLasCausas(db);
			mostrarCausas(causasMasDeDosImputados);
			System.out.println(barraDivisoria+"\n\nCONSULTA: Mostrar los juzgados del fuero civil que tengan al menos una causa con sentencia y una causa sin sentencia:\n\n"+barraDivisoria);
			consultaJuzgadosCivil(db);
			
    	}
    	finally{
    		db.close();
    	}

	}
	
    private static void consultaJuzgadosCivil(ObjectContainer db) {
    	//prototipo: todos los Juzgados del fuero civil:
    	Juzgado protoJuzgado = new Juzgado(Juzgado.TipoFuero.civil, null, null, null);
    	ObjectSet<Object> resultJuzgado = db.queryByExample(protoJuzgado);
    	Causa protoCausa = new Causa(protoJuzgado, null, null, null, null);
        ObjectSet<Object> resultCausas = db.queryByExample(protoCausa);
    	listResult(resultJuzgado, resultCausas);
    	
    }    

    private static boolean tieneCausasValidas(Juzgado unJuzgado) {
		return unJuzgado.poseeCausaConSentencia() && unJuzgado.poseeCausaSinSentencia();
	}

	public static void listResult(ObjectSet<Object> resultJuzgado, ObjectSet<Object> resultCausas){
		Causa unaCausa;
        while(resultCausas.hasNext()) {
        	unaCausa = (Causa) resultCausas.next();
        	unaCausa.agregarASuJuzgado();
        }
				
        System.out.println("\nCantidad total de juzgados con fuero civil encontrados:"+resultJuzgado.size()+"\nJuzgados que cumplen la condicion:");
        Juzgado unJuzgado;
        while(resultJuzgado.hasNext()) {
        	unJuzgado = (Juzgado) resultJuzgado.next();
        	if (tieneCausasValidas(unJuzgado)) {
	        	System.out.println("\n"+unJuzgado.toString());
	        	System.out.println("\tCausas del juzgado: "+unJuzgado.getCausas().size());
	    		for (int i = 0; i < unJuzgado.getCausas().size(); i++) 
	    			System.out.println("\t"+unJuzgado.getCausas().get(i));
        	}
        }
    }

	public static ArrayList<Causa> recuperarTodasLasCausas(ObjectContainer db) {
		Causa protoCausa = new Causa(null, null, null, null, null);
        ObjectSet<Object> result = db.queryByExample(protoCausa);
        //filtro...
        ArrayList<Causa> causas = causasMasDeDosImputados(result);
        return(causas);
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

    
    public static void mostrarCausas(ArrayList<Causa> causas){
        System.out.println("\nCausas con mas de dos imputados:"+causas.size());
        Iterator<Causa> iterador = causas.iterator();
        Causa causa;
        while(iterador.hasNext()) {
        	causa = iterador.next();
            System.out.println("\n"+causa.toString());
            System.out.println("\tDatos de los Imputados:");
            for (int i = 0; i < causa.getImputados().size(); i++) {
            	System.out.println("\t"+causa.getImputados().get(i).toString());
			}
        }
    }
	
}
