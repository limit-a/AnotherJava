package homework;

public class Car0718 {

	private double speed;
	private String color;
	private static final double MAX_SPEED = 200;

	public Car0718() {
		this.color = "";
	}

	public Car0718(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car0718 [speed=" + speed + ", color=" + color + "]";
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean speedUp(double speed) {
		if (this.speed + speed < 0 || this.speed + speed > MAX_SPEED) {
			return false;
		} else {
			this.speed += speed;
			return true;
		}
	}

	public static double getMaxSpeed() {
		return MAX_SPEED;
	}

}
