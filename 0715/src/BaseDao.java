import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class BaseDao{
	public static final String drivername = "com.mysql.jdbc.Driver";
	public static final String url="jdbc:mysql://127.0.0.1:3306/usinformation";
	public static final String username = "root";
	public static final String password = "root";
	PreparedStatement pst = null;
	Connection conn = null;
	public Connection getConnection() {
		try {
			// 1.��������
			Class.forName(drivername);
			// 2.��ȡ���ݿ����� ���Ӵ���Э��+����ip�Ͷ˿�+���ݿ����ƣ����û���������
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void closeResource() {
		try {
			if (pst != null)
				pst.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void executeUpdate(String sql,Object... params) {
		getConnection();
		try {
			// 3.����preparedStatement����
			pst = conn.prepareStatement(sql);
			//----���ò���-----
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
					pst.setObject(i+1, params[i]);
				}
			}
			// 4.ִ��sql���--���ؽ��������
			pst.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}
	public <T> List<T> executeQuery(String sql,Class<T> clazz,Object... params) {
		getConnection();
		List<T> data = null;
		try {
			pst = conn.prepareStatement(sql);
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
					pst.setObject(i+1, params[i]);
				}
			}
			ResultSet rs = pst.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();				//�ж��ٸ���
			List<String> columnnames = new ArrayList<>();
			for(int i=0;i<count;i++) {
				columnnames.add( metaData.getColumnName(i+1) ); //��ȡ����
			}
			data = new ArrayList<>();
			while(rs.next()) {
				T obj =(T) clazz.newInstance();
				for(int i=0;i<count;i++) {
					//����������ȡ�е�ֵ������obj��������
					String name = columnnames.get(i).toLowerCase();
					//System.out.println(name);
					Field f = clazz.getDeclaredField(name);
					f.setAccessible(true);
					String type = f.getType().getName();	//���Ե�������
					if( "int".equals(type) || "java.lang.Integer".equals(type) ) {
						int value = rs.getInt(name);
						f.set(obj, value);
					}else if( "float".equals(type) || "java.lang.Float".equals(type) ) {
						float value = rs.getFloat(name);
						f.set(obj, value);
					}else if( "double".equals(type) || "java.lang.Double".equals(type) ) {
						double value = rs.getDouble(name);
						f.set(obj, value);
					}else if( "boolean".equals(type) || "java.lang.Boolean".equals(type) ) {
						boolean value = rs.getBoolean(name);
						f.set(obj, value);
					}else if(  "java.lang.String".equals(type) ) {
						String value = rs.getString(name);
						f.set(obj, value);
					}else if( "java.util.Date".equals(type) ) {
						Date value = rs.getDate(name);
						f.set(obj, value);
					}	
				}
				data.add(obj);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}
		return null;
	}
	
}
