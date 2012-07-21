package zw.co.mitech.mtutor.util;

public class Rank {
	
	private long rank;
	private long points;
	private long prevRank;
	private long studentId;
	private String firstName;
	private String lastName;
	
	
	public long getRank() {
		return rank;
	}
	public void setRank(long rank) {
		this.rank = rank;
	}
	public long getPoints() {
		return points;
	}
	public void setPoints(long points) {
		this.points = points;
	}
	public long getPrevRank() {
		return prevRank;
	}
	public void setPrevRank(long prevRank) {
		this.prevRank = prevRank;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Rank [rank=" + rank + ", points=" + points + ", prevRank=" + prevRank + ", studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
