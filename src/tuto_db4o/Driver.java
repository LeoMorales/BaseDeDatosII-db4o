package tuto_db4o;

public class Driver {

		private String name;
		private Car mostLovedCar;
		
		public Driver(String name) {
			this.name = name;
		}

		public Driver(String name, Car mostLovedCar) {
			this.name = name;
			this.mostLovedCar = mostLovedCar;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public Car getMostLovedCar() {
			return mostLovedCar;
		}
}