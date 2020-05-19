package DB;

public class Subj_listDTO {
	private int num;
	private int idx;
	private String subject;
	private String kind;
	private String area;
	private String view;
	private int top_idx;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}


	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public int getTop_idx() {
		return top_idx;
	}
	public void setTop_idx(int top_idx) {
		this.top_idx = top_idx;
	}
}
