package homework;

public class CircleTest {

	public static void main(String[] args) {

		Circle0718 circle1 = new Circle0718();

		circle1.setRadius(3.6);
		circle1.setX(0.0);
		circle1.setY(1.5);

		System.out.printf("반지름 : %.1f\n", circle1.getRadius());
		System.out.printf("중심 좌표 : (%.1f,%.1f)\n", circle1.getX(),
				circle1.getY());
		System.out.printf("넓이 : %.2f\n", circle1.getArea());
		
		Circle0718 circle2 = new Circle0718();

		circle2.setRadius(-5.0);
		circle2.setX(1.0);
		circle2.setY(2.2);

		System.out.printf("반지름 : %.1f\n", circle2.getRadius());
		System.out.printf("중심 좌표 : (%.1f,%.1f)\n", circle2.getX(),
				circle2.getY());
		System.out.printf("넓이 : %.2f\n", circle2.getArea());

	}

}
