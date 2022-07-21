package enum_example;

public class Developer {

	public String name;
	public int career;
	public DevType type;
	public Gender gender;
	public Rank rank;

	public enum Gender {
		MALE, FEMALE
	}
	
	public static void main(String[] args) {
		Developer developer = new Developer();
		developer.name = "홍길동";
		developer.career = 3;
		developer.type = DevType.WEB;
		developer.gender = Gender.MALE;
		developer.rank = Rank.Clerk;
		
		
		
		System.out.printf("개발자 이름 : %s\n", developer.name);
		System.out.printf("개발자 경력 : %d\n", developer.career);
		System.out.printf("직무 파트 : %s\n", developer.type);
		System.out.printf("성별 : %s\n", developer.gender);
		System.out.printf("직급 : %s\n", developer.rank);
		
		
		
	}

}

enum Rank {
	GeneralManager,
	DeputyGeneralManager,
	Manager,
	AssistantManager,
	SeniorClerk,
	Clerk
}
