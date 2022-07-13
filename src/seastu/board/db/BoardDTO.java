package seastu.board.db;

import java.sql.Timestamp;

public class BoardDTO {

	private int num;
	private String title;
	private String content;
	private String name;
	private String id;
	private String file;
	private int thumb;
	private Timestamp reg_date;
	private int readcount;
	private int replycount;
	private int bob;
	
	
	
	
	public int getBob() {
		return bob;
	}

	public void setBob(int bob) {
		this.bob = bob;
	}

	public int getReplycount() {
		return replycount;
	}

	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getThumb() {
		return thumb;
	}

	public void setThumb(int thumb) {
		this.thumb = thumb;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp timestamp) {
		reg_date = timestamp;
	}

	@Override
	public String toString() {
		return "BoardDTO [num=" + num + ", title=" + title + ", content=" + content + ", name=" + name + ", id=" + id
				+ ", file=" + file + ", thumb=" + thumb + ", reg_date=" + reg_date + ", readcount=" + readcount
				+ ", replycount=" + replycount + ", bob=" + bob + "]";
	}


	

	

}
