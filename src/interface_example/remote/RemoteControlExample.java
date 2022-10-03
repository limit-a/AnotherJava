package interface_example.remote;

public class RemoteControlExample {

	public static void main(String[] args) {

		
//		RemoteControl rc;
//		rc = new Television();
//		
//		rc.turnOn();
//		rc.setVolume(8);
//		rc.turnOff();
//		
//		System.out.println();
//		
//		rc = new Audio();
//		
//		rc.turnOn();
//		rc.setVolume(3);
//		rc.turnOff();
		
		MyRemote mr1 = new MyRemote();
		
		mr1.rc.turnOn();
		mr1.rc.setVolume(8);
		mr1.rc.turnOff();
		
		System.out.println();
		
		MyRemote mr2 = new MyRemote(new Audio());
		
		mr2.rc.setVolume(3);
		mr2.rc.turnOff();
		
		System.out.println();
		
		MyRemote mr3 = new MyRemote();
		
		mr3.tvMethod(new Television());
		
		mr1.rc.setVolume(8);
		mr1.rc.turnOff();
		
		
		
	}

}
