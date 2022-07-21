package homework;

public class Time0718 {

	private int hour;
	private int minute;
	private int second;

	public Time0718() {
	}

	public Time0718(int hour, int minute, int second) {
		this.hour = hour < 0 || hour > 23 ? 0 : hour;
		this.minute = minute < 0 || minute > 59 ? 0 : minute;
		this.second = second < 0 || second > 59 ? 0 : second;
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}

}
