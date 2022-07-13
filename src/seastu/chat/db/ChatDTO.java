package seastu.chat.db;

import java.sql.Timestamp;

public class ChatDTO {
	private int c_num;
	private int c_t_num; // 익명으로 쪽지를 주고받기 때문에 글번호를 통해서 쪽지들을 구별해줌
	private String sender;
	private String receiver;
	private String content;
	private int chat_ref; // 해당 거래의 첫 쪽지인지 답장인지 구별해주기 위함
	private Timestamp send_date;
	
	
	
	
	
	public int getC_t_num() {
		return c_t_num;
	}
	public void setC_t_num(int c_t_num) {
		this.c_t_num = c_t_num;
	}
	public int getChat_ref() {
		return chat_ref;
	}
	public void setChat_ref(int chat_ref) {
		this.chat_ref = chat_ref;
	}
	public Timestamp getSend_date() {
		return send_date;
	}
	public void setSend_date(Timestamp send_date) {
		this.send_date = send_date;
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		return "ChatDTO [c_num=" + c_num + ", c_t_num=" + c_t_num + ", sender=" + sender + ", receiver=" + receiver
				+ ", content=" + content + ", chat_ref=" + chat_ref + ", send_date=" + send_date + "]";
	}
	
	
	
}
