import java.util.List;
public class TestDao extends BaseDao{
	public List<Student> selectAll(){
		return this.executeQuery("select * from student", Student.class);
	}
	public Student selectById(String id) {
		 List<Student> list=this.executeQuery("select * from student where id=?" , Student.class, id);
		 return list.get(0);
	}
	public void addStudent(Student stu) {
		String sql="insert into student values(?,?,?,?,?)";
		this.executeUpdate(sql, stu.getName(),stu.getId(),stu.getSex(),stu.getIdcard(),stu.getStnum());
	}
	public void update(Student stu) {
		String sql="update student set name=?,sex=?,idcard=?,stnum=? where id=?";
		this.executeUpdate(sql, stu.getName(),stu.getSex(),stu.getIdcard(),stu.getStnum(),stu.getId());
	}
	public void delById(String id) {
		String sql="delete from student where id=?";
		this.executeUpdate(sql, id);
	}
	public static void main(String[] args) {
		TestDao t1=new TestDao();
		//����student����ȫ����
		//System.out.println(t1.selectAll());
		//��id����student��������
		//System.out.println(t1.selectById("123456"));
		//����ѧ��
		/*
		 * Student st=new Student("��ӽ��","851369","��","357986556","18042107");
		 * t1.addStudent(st);
		 */
		//����ѧ����Ϣ
		/*
		 * Student dyx=t1.selectById("226321"); 
		 * dyx.setSex("Ů"); 
		 * t1.update(dyx);
		 */
		 //ɾ��ѧ����Ϣ
		t1.delById("123456");
	}
}
