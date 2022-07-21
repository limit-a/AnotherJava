package homework;

public class Circle0718 {

	private double radius;
	private double x;
	private double y;

	Circle0718() {
	}

	double getArea() {
//		double circleArea = Math.PI * Math.pow(this.radius, 2);
		return Math.PI * Math.pow(this.radius, 2);
	}

	public double getRadius() {
		return this.radius;
	}

	public void setRadius(double radius) {
//		if (radius < 0) {
//			this.radius = 0;
//		} else {
//			this.radius = radius;
//		}
		this.radius = radius < 0 ? 0 : radius;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
