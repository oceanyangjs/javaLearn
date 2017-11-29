package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Hero;

public class HeroDao {
	//������ɾ�Ĳ�����ݿ��������
	
	public HeroDao() {
		try {
			Class.forName("com.mysql.jdbc.driver");
		}catch(ClassNotFoundException exception){
			exception.printStackTrace();
		}
	}
	
	//�������ݿ�
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc://mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","000000");
	}
	
	//�������ɾ�Ĳ鷽����������Ӧ
	//��ѯ��Ŀ
	public int getTotal() {
		int total = 0;
		try(Connection c = getConnection();Statement s = c.createStatement();) {
			String sql = "Select count(*) from hero";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("total is :" + total);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
	
	//����Ӣ��
	public void add(Hero hero) {
		String sql = "Insert into hero values(null,?,?,?)";
		try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, hero.name);
			ps.setFloat(2, hero.hp);
			ps.setInt(3, hero.damage);
			//��������ִ��
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				hero.id = id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//�޸�Ӣ��
	public void update(Hero hero) {
		String sql = "Update hero set name = ? ,hp = ?, damage = ? where id = ?";
		try (Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1,hero.name );
			ps.setFloat(1,hero.hp );
			ps.setInt(1,hero.damage );
			
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//ɾ��Ӣ��
	public void delete(int id) {
		try(Connection c = getConnection();Statement s = c.createStatement()) {
			String sql = "Delete from hero where id = " + id;
			s.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//��ѯĳ��Ӣ�۵���Ϣ
	public Hero get(int id) {
		Hero hero = null;
		try(Connection connection = getConnection();Statement s = connection.createStatement();) {
			String sql = "Select from hero where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				String name = rs.getString(2);
				float hp = rs.getFloat("hp");
				int damage = rs.getInt(4);
				
				hero.name = name;
				hero.hp = hp;
				hero.damage = damage;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hero;
	}
	
	public List<Hero> list() {
        return list(0, Short.MAX_VALUE);
    }
	
	//��ѯȫ��Ӣ�۵���Ϣ
	//������ҳ��Ϣ
	public List<Hero> list(int start,int count){
		List<Hero> heros = new ArrayList<Hero>();
		String sql = "Select * from hero order by id desc limit ?,?";
		try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Hero hero = new Hero();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float hp = rs.getFloat("hp");
				int damage = rs.getInt(4);
				
				hero.id = id;
				hero.name = name;
				hero.hp = hp;
				hero.damage = damage;
				
				heros.add(hero);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return heros;
		
	}
}
