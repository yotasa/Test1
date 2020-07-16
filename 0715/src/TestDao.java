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
		//查找student表中全数据
		//System.out.println(t1.selectAll());
		//按id查找student表中数据
		//System.out.println(t1.selectById("123456"));
		//插入学生
		/*
		 * Student st=new Student("何咏雷","851369","男","357986556","18042107");
		 * t1.addStudent(st);
		 */
		//更新学生信息
		/*
		 * Student dyx=t1.selectById("226321"); 
		 * dyx.setSex("女"); 
		 * t1.update(dyx);
		 */
		 //删除学生信息
		t1.delById("123456");
	}
}
