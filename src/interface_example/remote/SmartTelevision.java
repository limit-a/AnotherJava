package interface_example.remote;

public class SmartTelevision extends Electronics
		implements RemoteControl, Searchable {

	SmartTelevision() {
		super();
	}

	@Override
	public void turnOn() {
		this.power = true;
		System.out.println("스마트 TV를 켭니다.");
	}

	@Override
	public void turnOff() {
		this.power = false;
		System.out.println("스마트 TV를 끕니다.");
	}

	@Override
	public void setVolume(int volume) {
		if (volume > RemoteControl.MAX_VOLUME) {
			this.setVolume(RemoteControl.MIN_VOLUME);
		} else if (volume < RemoteControl.MIN_VOLUME) {
			this.setVolume(RemoteControl.MAX_VOLUME);
		} else {
			this.volume = volume;
		}
		System.out.println("현재 스마트 TV 볼륨 : " + this.volume);
	}

	@Override
	public void search(String url) {

		System.out.println(url + "을 검색합니다.");

	}

}
