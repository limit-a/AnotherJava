package homework;

public class NewCar0718 {

	private double speed;
	private String color;
	private static final double MAX_SPEED = killoToMile(200);

	public NewCar0718() {
		this.color = "";
	}

	public NewCar0718(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car0718 [speed=" + speed + ", color=" + color + "]";
	}

	public double getSpeed() {
		return mileToKillo(speed);
	}

	public void setSpeed(double kmSpeed) {
		this.speed = killoToMile(kmSpeed);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean speedUp(double kmSpeed) {
		if (mileToKillo(this.speed) + kmSpeed < 0
				|| mileToKillo(this.speed) + kmSpeed > mileToKillo(MAX_SPEED)) {
			return false;
		} else {
			this.speed += killoToMile(kmSpeed);
			return true;
		}
	}

	public static double getMaxSpeed() {
		return mileToKillo(MAX_SPEED);
	}

	private static double killoToMile(double kmSpeed) {
		return kmSpeed / 1.6;
	}

	private static double mileToKillo(double mileSpeed) {
		return mileSpeed * 1.6;
	}
}
