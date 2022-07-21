package homework;

public class CarTest {

	public static void main(String[] args) {

		Car0718 myCar = new Car0718("red");

		System.out.println("myCar의 색 : " + myCar.getColor());
		System.out.println("차의 최대 속력 : " + Car0718.getMaxSpeed() + "km/h");

		System.out.print("첫 번째 speedUp(100.0km/h) : ");

		if (myCar.speedUp(100.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력 : " + myCar.getSpeed() + "km/h");

		System.out.print("두 번째 speedUp(90.0km/h) : ");

		if (myCar.speedUp(90.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력 : " + myCar.getSpeed() + "km/h");

		Car0718 yourCar = new Car0718("blue");

		System.out.println();
		System.out.println("yourCar의 색 : " + yourCar.getColor());
		System.out.println("차의 최대 속력 : " + Car0718.getMaxSpeed() + "km/h");

		System.out.print("첫 번째 speedUp(-100.0km/h) : ");
		if (yourCar.speedUp(-100.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력 : " + yourCar.getSpeed() + "km/h");

		System.out.print("두 번째 speedUp(210.0km/h) : ");
		if (yourCar.speedUp(210.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력 : " + yourCar.getSpeed() + "km/h");

		NewCar0718 myCarMile = new NewCar0718("red");

		System.out.println();
		System.out.println("myCarMile의 색 : " + myCarMile.getColor());
		System.out.println("차의 최대 속력 : " + NewCar0718.getMaxSpeed() + "km/h");

		System.out.print("첫 번째 speedUp(100.0km/h) : ");

		if (myCarMile.speedUp(100.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력 : " + myCarMile.getSpeed() + "km/h");

		System.out.print("두 번째 speedUp(90.0km/h) : ");

		if (myCarMile.speedUp(90.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력 : " + myCarMile.getSpeed() + "km/h");

		NewCar0718 yourCarMile = new NewCar0718("blue");

		System.out.println();
		System.out.println("yourCarMile의 색 : " + yourCarMile.getColor());
		System.out.println("차의 최대 속력 : " + NewCar0718.getMaxSpeed() + "km/h");

		System.out.print("첫 번째 speedUp(-100.0km/h) : ");
		if (yourCarMile.speedUp(-100.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력 : " + yourCarMile.getSpeed() + "km/h");

		System.out.print("두 번째 speedUp(210.0km/h) : ");
		if (yourCarMile.speedUp(210.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력 : " + yourCarMile.getSpeed() + "km/h");
	}

}
