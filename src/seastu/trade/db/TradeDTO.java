package seastu.trade.db;

import java.sql.Timestamp;

public class TradeDTO {
	private int t_num;
	private String title;
	private String content;
	private String seller;
	private int status;
	private String publisher;
	private int book_price;
	private int price;
	private String file;
	private Timestamp reg_date;
	
	
	
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getBook_price() {
		return book_price;
	}
	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getT_num() {
		return t_num;
	}
	public void setT_num(int t_num) {
		this.t_num = t_num;
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
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "TradeDTO [t_num=" + t_num + ", title=" + title + ", content=" + content + ", seller=" + seller
				+ ", status=" + status + ", publisher=" + publisher + ", book_price=" + book_price + ", price=" + price
				+ ", file=" + file + ", reg_date=" + reg_date + "]";
	}
	
	
	
	
	
	
}
