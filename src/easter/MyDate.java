package easter;

// Very basic class to represent dates
public class MyDate {
    private String month;
    private int day;
    public MyDate(String m, int d) {
	this.month = m;
	this.day = d;
    }
    public String toString() {
	return(month + " " + day);
    }
}
