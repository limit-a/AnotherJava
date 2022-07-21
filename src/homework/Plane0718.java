package homework;

public class Plane0718 {

	private String manufacture;
	private String model;
	private int maxNumberOfPassengers;
	private static int numberOfPlanes;

	public Plane0718() {
		this.manufacture = "";
		this.model = "";
		numberOfPlanes++;
	}

	public Plane0718(String manufacture, String model,
			int maxNumberOfPassengers) {
		this.manufacture = manufacture;
		this.model = model;
		setMaxNumberOfPassengers(maxNumberOfPassengers);
//		this.maxNumberOfPassengers = maxNumberOfPassengers < 0 ? 0
//				: maxNumberOfPassengers;
		numberOfPlanes++;
	}

	@Override
	public String toString() {
		return "Plane0718 [manufacture=" + manufacture + ", model=" + model
				+ ", maxNumberOfPassengers=" + maxNumberOfPassengers + "]";
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMaxNumberOfPassengers() {
		return maxNumberOfPassengers;
	}

	public void setMaxNumberOfPassengers(int maxNumberOfPassengers) {
		this.maxNumberOfPassengers = maxNumberOfPassengers < 0 ? 0
				: maxNumberOfPassengers;
	}

	public static int getNumberOfPlanes() {
		return numberOfPlanes;
	}

}
