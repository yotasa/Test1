public class Student {
		String name;
		String id;
		String sex;
		String idcard;
		String stnum;
		public Student() {
		}
		public Student(String name, String id, String sex, String idcard, String stnum) {
			super();
			this.name = name;
			this.id = id;
			this.sex = sex;
			this.idcard = idcard;
			this.stnum = stnum;
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
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getIdcard() {
			return idcard;
		}
		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}
		public String getStnum() {
			return stnum;
		}
		public void setStnum(String stnum) {
			this.stnum = stnum;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", id=" + id + ", sex=" + sex + ", idcard=" + idcard + ", stnum=" + stnum
					+ "]"+"\n";
		}
}
