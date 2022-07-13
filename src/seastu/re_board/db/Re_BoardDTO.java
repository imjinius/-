package seastu.re_board.db;

import java.sql.Timestamp;

public class Re_BoardDTO {
	private int re_num;
	private int re_b_num;
	private String re_name;
	private String re_m_id;
	private String re_content;
	private Timestamp re_date;
	private int re_ref;
	private int re_seq;
	
	
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public int getRe_num() {
		return re_num;
	}
	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}
	public int getRe_b_num() {
		return re_b_num;
	}
	public void setRe_b_num(int re_b_num) {
		this.re_b_num = re_b_num;
	}
	public String getRe_name() {
		return re_name;
	}
	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}
	public String getRe_m_id() {
		return re_m_id;
	}
	public void setRe_m_id(String re_m_id) {
		this.re_m_id = re_m_id;
	}
	public Timestamp getRe_date() {
		return re_date;
	}
	public void setRe_date(Timestamp re_date) {
		this.re_date = re_date;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	@Override
	public String toString() {
		return "Re_BoardDTO [re_num=" + re_num + ", re_b_num=" + re_b_num + ", re_name=" + re_name + ", re_m_id="
				+ re_m_id + ", re_content=" + re_content + ", re_date=" + re_date + ", re_ref=" + re_ref + ", re_seq="
				+ re_seq + "]";
	}
	
	
}
