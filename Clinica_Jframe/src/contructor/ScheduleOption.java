package contructor;

public class ScheduleOption {
	private int id;
	private String name;

	public ScheduleOption(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
