package grupp4;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeLap {
	private String date;
	private String time;

	DateTimeLap(String time) {
		this.time = time;
		setDate();

	}

	public void setDate() {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String formattedDate = sd.format(date);
		this.date = formattedDate;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

}
