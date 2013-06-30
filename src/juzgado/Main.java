package juzgado;

import java.util.ArrayList;
import java.util.Iterator;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

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
			
			juzgadosMasDeDosCausasConSentencias(db);
			juzgadosSinMasDeDosCausasConSentencias(db);
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
	
    private static void juzgadosMasDeDosCausasConSentencias(ObjectContainer db) {
    	
    	System.out.println(barraDivisoria+"\n\nCONSULTA: Mostrar los Juzgados con causas que tengan mas de dos sentencias\n\n"+barraDivisoria);
    	
    	//prototipo: todos los Juzgados del fuero civil:
    	Query query = db.query();
		query.constrain(Juzgado.class);
		//Subconsulta sobre la coleccion de causas
//		Query queryCausas = query.descend("causas").descend("sentencia");
//		queryCausas.constrain("Culpables");
		//Consulta por el tipo de Juzgado
		query.descend("fuero").constrain(Juzgado.TipoFuero.civil);
//		query.descend("causas").descend("sentencia").constrain("Culpables").not();
		//query.descend("causas").descend("sentencia").constrain(null).not();
		//query.descend("causas").descend("juzgado").descend("juez").descend("nombre").constrain("Loo");
		ObjectSet<Object> juzgados = query.execute();
		
		System.out.println("Cantidad de Juzgados: " + juzgados.size());
    	
    	ArrayList<Juzgado> tineMasDosSentencias = new ArrayList<Juzgado>();
    	int i = 0;

    	for (Object juzgado : juzgados) {
    		i = 0;
    		System.out.println( juzgado + "Cantidad de Causas: " + ((Juzgado)juzgado).getCausas().size());
    		/*for (Causa causa : ((Juzgado)juzgado).getCausas()) {
				System.out.println("\t" + causa);
			}*/
    		if(((Juzgado)juzgado).getCausas().size()>=2)
    		{
				for (Causa causa : ((Juzgado)juzgado).getCausas()) {
					if(causa.getSentencia()!=null)
						i++;
					if(i>=2)
					{
						i = 0;
						tineMasDosSentencias.add((Juzgado) juzgado);
						break;
					}
				}
    		}
		}
    	System.out.println(barraDivisoria+"\n\nJuzgados con mas de dos sentencias: \n\n"+barraDivisoria);
    	for (Juzgado juzgado : tineMasDosSentencias) {
    		System.out.println(juzgado.toString()+"cantidad de causas: "+juzgado.getCausas().size());
    		for (Causa c : juzgado.getCausas()) {
				System.out.println("\t" + c);
			}
		}
    }
    
private static void juzgadosSinMasDeDosCausasConSentencias(ObjectContainer db) {
    	
    	System.out.println(barraDivisoria+"\n\nCONSULTA: Mostrar los Juzgados con causas que tengan mas de dos sentencias\n\n"+barraDivisoria);
    	
    	//prototipo: todos los Juzgados del fuero civil:
    	Query query = db.query();
		query.constrain(Juzgado.class);
		//Subconsulta sobre la coleccion de causas
//		Query queryCausas = query.descend("causas").descend("sentencia");
//		queryCausas.constrain("Culpables");
		//Consulta por el tipo de Juzgado
		query.descend("fuero").constrain(Juzgado.TipoFuero.civil);
//		query.descend("causas").descend("sentencia").constrain("Culpables").not();
		//query.descend("causas").descend("sentencia").constrain(null).not();
		//query.descend("causas").descend("juzgado").descend("juez").descend("nombre").constrain("Loo");
		ObjectSet<Object> juzgados = query.execute();
		
		System.out.println("Cantidad de Juzgados: " + juzgados.size());
    	
    	ArrayList<Juzgado> tineMasDosSentencias = new ArrayList<Juzgado>();
    	int i = 0;

    	for (Object juzgado : juzgados) {
    		i = 0;
    		System.out.println( juzgado + "Cantidad de Causas: " + ((Juzgado)juzgado).getCausas().size());
    		/*for (Causa causa : ((Juzgado)juzgado).getCausas()) {
				System.out.println("\t" + causa);
			}*/
    		if(((Juzgado)juzgado).getCausas().size()<=2)
    		{
				tineMasDosSentencias.add((Juzgado) juzgado);
    		}
		}
    	System.out.println(barraDivisoria+"\n\nJuzgados Sin mas de dos sentencias: \n\n"+barraDivisoria);
    	for (Juzgado juzgado : tineMasDosSentencias) {
    		System.out.println(juzgado.toString()+"cantidad de causas: "+juzgado.getCausas().size());
    		for (Causa c : juzgado.getCausas()) {
				System.out.println("\t" + c);
			}
		}
    }
}
