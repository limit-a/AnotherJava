package homework;

public class TimeTest {

	public static void main(String[] args) {

		Time0718 time1 = new Time0718();

		System.out.println("기본 생성자 호출 후 시간 : " + time1);

		Time0718 time2 = new Time0718(22, 15, 48);

		System.out.println("두번째 생성자 호출 후 시간 : " + time2);

		Time0718 time3 = new Time0718(15, 66, 77);

		System.out.println("올바르지 않은 시간 설정 후 시간 : " + time3);
	
	
	
	}

}
