package tuto_db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


public class BasicOperations {

	/**
	 * 
	 */
	public static void main(String[] args) {
		
		ObjectContainer container = Db4oEmbedded.openFile("databaseDrivers.db4o");
		try
		{
			//use the object container in here:
			
			//Store an Object:
			Driver driver = new Driver("Joe");
			container.store(driver);
			
			//Query the Database:
			/*ObjectContainer drivers = container.query(new Predicate<Driver>() {
				public boolean match(Driver d) {
					return d.getName().equals("Joe");
				}
			});*/
			
			System.out.println("Stored Pilots:");
			/*for (Driver aDriver : drivers) {
				System.out.println(aDriver.getName());
			}
			*/
			
			
		} finally {
		container.close();
		}
	}

}
