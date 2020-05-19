package DB;

public class TopsubDTO {
	private int top_idx;
	private String topsubject;
	private int num;
	private String start;
	private String end;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	private String info;
	
	public int getTop_idx() {
		return top_idx;
	}
	public void setTop_idx(int top_idx) {
		this.top_idx = top_idx;
	}
	public String getTopsubject() {
		return topsubject;
	}
	public void setTopsubject(String topsubject) {
		this.topsubject = topsubject;
	}
}
