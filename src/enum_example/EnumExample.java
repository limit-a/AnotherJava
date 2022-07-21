package enum_example;

public class EnumExample {

	public static final String MALE = "MALE";
	public static final String FEMALE = "FEMALE";

	public static void main(String[] args) {

		String gender1;
		gender1 = EnumExample.MALE;
		gender1 = EnumExample.FEMALE;

		gender1 = "boy";

		Gender gender2;

		gender2 = Gender.MALE;
		gender2 = Gender.FEMALE;

//		gender2 = "boy";

	}

}

enum Gender {
	MALE, // public static final Gender MALE = new Gender();
	FEMALE // public static final Gender FEMALE = new Gender();
}
