
public class Parking {
	private int veh_Number;
	private String entryTime;
	private String exitTime;
	private boolean available = true;
	public int getVeh_Number() {
		return veh_Number;
	}
	public void setVeh_Number(int veh_Number) {
		this.veh_Number = veh_Number;
	}
	public String getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	public String getExitTime() {
		return exitTime;
	}
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
