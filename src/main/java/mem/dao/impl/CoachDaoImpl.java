package mem.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mem.dao.CoachDao;
import mem.vo.Coach;

public class CoachDaoImpl implements CoachDao {

	@Override
	public int insert(Coach co) {
		String sql = "INSERT INTO Coach VALUES(?,?,?,?,?,?)";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///ski", "root", "password");
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, co.getCoachID());
			ps.setInt(2, co.getUserID());
			ps.setString(3, co.getSkill());
			ps.setString(4, co.getBackground());
			ps.setString(5, co.getIntroduce());
			ps.setBytes(6, co.getLicense());
			return ps.executeUpdate(); // 影響0到N筆資料
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteByCoachID(Integer coachID) {
		String sql = "DELETE FROM Coach WHERE coachID = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///ski", "root", "password");
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, coachID);
			return ps.executeUpdate(); // 影響0到N筆資料
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateByCoachID(Coach co) {
		String sql = "UPDATE Coach\r\n" + "SET\r\n" + "userID = ?," + "skill = ?," + "background = ?,"
				+ "introduce = ?," + "license = ?\r\n" + "WHERE coachID = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///ski", "root", "password");
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, co.getUserID());
			ps.setString(2, co.getSkill());
			ps.setString(3, co.getBackground());
			ps.setString(4, co.getIntroduce());
			ps.setBytes(5, co.getLicense());
			ps.setInt(6, co.getCoachID());
			return ps.executeUpdate(); // 影響0到N筆資料
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Coach selectByCoachID(Integer coachID) {
		String sql = "SELECT * FROM Coach WHERE coachID = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///ski", "root", "password");
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, coachID);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Coach co = new Coach();
					co.setCoachID(rs.getInt("coachID"));
					co.setUserID(rs.getInt("userID"));
					co.setSkill(rs.getString("skill"));
					co.setBackground(rs.getString("background"));
					co.setIntroduce(rs.getString("introduce"));
					co.setLicense(rs.getBytes("license"));
					return co; // 回傳一個物件
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Coach> selectAll() {
		String sql = "SELECT * FROM Coach";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///ski", "root", "password");
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			List<Coach> list = new ArrayList<Coach>();
			while (rs.next()) {
				Coach co = new Coach();
				co.setCoachID(rs.getInt("coachID"));
				co.setUserID(rs.getInt("userID"));
				co.setSkill(rs.getString("skill"));
				co.setBackground(rs.getString("background"));
				co.setIntroduce(rs.getString("introduce"));
				co.setLicense(rs.getBytes("license"));
				list.add(co);
			}
			return list; // 回傳一個陣列
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// test
	public static void main(String[] args) {
		CoachDao dao = new CoachDaoImpl();

		Coach co1 = new Coach(1, 1, "Java", "Computer Science", "Hi", new byte[] { 1, 2, 3, 4 });

		Coach co2 = new Coach(2, 2, "C++", "Computer Science", "Hi", new byte[] { 1, 2, 3, 4 });

//		System.out.println("新增" + dao.insert(co1) + "筆資料");	
//		System.out.println("新增" + dao.insert(co2) + "筆資料");
//		System.out.println("刪除" + dao.deleteByCoachID(1) + "筆資料");
//		System.out.println("更新" + dao.updateByCoachID(co2) + "筆資料");
//		System.out.println(dao.selectByCoachID(2));

		for (Coach co : dao.selectAll()) {
			System.out.println(co.getSkill());
		}

	}
}
