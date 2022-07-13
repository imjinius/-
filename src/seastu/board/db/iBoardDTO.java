package seastu.board.db;

import java.sql.Timestamp;

public class iBoardDTO {
	private int i_num;
	private String i_title;
	private String i_content;
	private String image;
	private int readcount;
	private Timestamp reg_date;
	private int category;
	
	
	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getI_num() {
		return i_num;
	}
	public void setI_num(int i_num) {
		this.i_num = i_num;
	}
	public String getI_title() {
		return i_title;
	}
	public void setI_title(String i_title) {
		this.i_title = i_title;
	}
	public String getI_content() {
		return i_content;
	}
	public void setI_content(String i_content) {
		this.i_content = i_content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "iBoardDTO [i_num=" + i_num + ", i_title=" + i_title + ", i_content=" + i_content + ", image=" + image
				+ ", readcount=" + readcount + ", reg_date=" + reg_date + ", category=" + category + "]";
	}
	
	

}
