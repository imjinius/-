package seastu.member.db;

import java.sql.Timestamp;

public class MemberDTO {
	
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String tel;
	private int zip_code;
	private String addr;
	private Timestamp reg_date;
	private int w_count;
	private int re_count;

	
	
	
	public int getW_count() {
		return w_count;
	}

	public void setW_count(int w_count) {
		this.w_count = w_count;
	}

	public int getRe_count() {
		return re_count;
	}

	public void setRe_count(int re_count) {
		this.re_count = re_count;
	}

	public int getZip_code() {
		return zip_code;
	}

	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", birth=" + birth + ", tel=" + tel
				+ ", zip_code=" + zip_code + ", addr=" + addr + ", reg_date=" + reg_date + ", w_count=" + w_count
				+ ", re_count=" + re_count + "]";
	}


}
