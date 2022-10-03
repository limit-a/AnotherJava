package interface_example.remote;

public class MyRemote {

	RemoteControl rc = new Television();

	MyRemote() {
	}

	MyRemote(RemoteControl rc) {
		this.rc = rc;
		rc.turnOn();
		rc.setVolume(5);
	}

	void audioMethod() {
		RemoteControl rc = new Audio();
		rc.turnOn();
		rc.setVolume(5);
	}

	void tvMethod(RemoteControl rc) {
		rc.turnOn();
		rc.setVolume(5);
	}

}
