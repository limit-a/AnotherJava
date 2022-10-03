package interface_example.remote;

public class Audio extends Electronics implements RemoteControl {

	Audio() {
		super();
	}

	@Override
	public void turnOn() {
		this.power = true;
		System.out.println("오디오를 켭니다.");
	}

	@Override
	public void turnOff() {
		this.power = false;
		System.out.println("오디오를 끕니다.");
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
		System.out.println("현재 오디오 볼륨 : " + this.volume);
	}
}
