package mem.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mem.dao.MemDao;
import mem.vo.Mem;

public class MemDaoImpl implements MemDao {

	@Override
	public int insert(Mem mem) {
		String sql = "INSERT INTO Member VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///ski", "root", "password");
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, mem.getUserID());
			ps.setString(2, mem.getEmail());
			ps.setString(3, mem.getPassword());
			ps.setString(4, mem.getUserName());
			ps.setString(5, mem.getNickName());
			ps.setString(6, mem.getGender());
			ps.setTimestamp(7, mem.getBirthDate());
			ps.setString(8, mem.getPersonID());
			ps.setString(9, mem.getPhone());
			ps.setString(10, mem.getAddress());
			ps.setString(11, mem.getLevel());
			ps.setBytes(12, mem.getPhoto());
			ps.setString(13, mem.getRole());
			ps.setString(14, mem.getUserStatus());
			return ps.executeUpdate(); // 影響0到N筆資料
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteByUserID(Integer userID) {
		String sql = "DELETE FROM Member WHERE userID = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///ski", "root", "password");
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, userID);
			return ps.executeUpdate(); // 影響0到N筆資料
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateByUserID(Mem mem) {
		String sql = "UPDATE Member\r\n" + "SET\r\n" + "email = ?," + "password = ?," + "userName = ?,"
				+ "nickName = ?," + "gender = ?," + "birthDate = ?," + "personID = ?," + "phone = ?," + "address = ?,"
				+ "level = ?," + "photo = ?," + "role = ?," + "userStatus = ?\r\n" + "WHERE userID = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///ski", "root", "password");
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, mem.getEmail());
			ps.setString(2, mem.getPassword());
			ps.setString(3, mem.getUserName());
			ps.setString(4, mem.getNickName());
			ps.setString(5, mem.getGender());
			ps.setTimestamp(6, mem.getBirthDate());
			ps.setString(7, mem.getPersonID());
			ps.setString(8, mem.getPhone());
			ps.setString(9, mem.getAddress());
			ps.setString(10, mem.getLevel());
			ps.setBytes(11, mem.getPhoto());
			ps.setString(12, mem.getRole());
			ps.setString(13, mem.getUserStatus());
			ps.setInt(14, mem.getUserID());
			return ps.executeUpdate(); // 影響0到N筆資料
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Mem selectByUserID(Integer userID) {
		String sql = "SELECT * FROM Member WHERE userID = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///ski", "root", "password");
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, userID);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Mem mem = new Mem();
					mem.setUserID(rs.getInt("userID"));
					mem.setEmail(rs.getString("email"));
					mem.setPassword(rs.getString("password"));
					mem.setUserName(rs.getString("userName"));
					mem.setNickName(rs.getString("nickName"));
					mem.setGender(rs.getString("gender"));
					mem.setBirthDate(rs.getTimestamp("birthDate"));
					mem.setPersonID(rs.getString("personID"));
					mem.setPhone(rs.getString("phone"));
					mem.setAddress(rs.getString("address"));
					mem.setLevel(rs.getString("level"));
					mem.setPhoto(rs.getBytes("photo"));
					mem.setRole(rs.getString("role"));
					mem.setUserStatus(rs.getString("userStatus"));
					return mem; // 回傳一個物件
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Mem> selectAll() {
		String sql = "SELECT * FROM Member";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///ski", "root", "password");
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			List<Mem> list = new ArrayList<Mem>();
			while (rs.next()) {
				Mem mem = new Mem();
				mem.setUserID(rs.getInt("userID"));
				mem.setEmail(rs.getString("email"));
				mem.setPassword(rs.getString("password"));
				mem.setUserName(rs.getString("userName"));
				mem.setNickName(rs.getString("nickName"));
				mem.setGender(rs.getString("gender"));
				mem.setBirthDate(rs.getTimestamp("birthDate"));
				mem.setPersonID(rs.getString("personID"));
				mem.setPhone(rs.getString("phone"));
				mem.setAddress(rs.getString("address"));
				mem.setLevel(rs.getString("level"));
				mem.setPhoto(rs.getBytes("photo"));
				mem.setRole(rs.getString("role"));
				mem.setUserStatus(rs.getString("userStatus"));
				list.add(mem);
			}
			return list; // 回傳一個陣列
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// test
	public static void main(String[] args) {
		MemDao dao = new MemDaoImpl();

		Mem mem1 = new Mem(1, "123@gail.com", "password", "John Doe", "JD", "male",
				Timestamp.valueOf("2023-04-30 00:00:00"), "A123456789", "0912345678", "123 Main St", "primary",
				new byte[] { 0, 1, 2, 3 }, "user", "active");

		Mem mem2 = new Mem(2, "456@gail.com", "password", "John Doe", "JD", "male",
				Timestamp.valueOf("2023-04-30 00:00:00"), "A123456789", "0912345678", "123 Main St", "primary",
				new byte[] { 0, 1, 2, 3 }, "user", "active");

//		System.out.println("新增" + dao.insert(mem1) + "筆資料");
//		System.out.println("新增" + dao.insert(mem2) + "筆資料");
//		System.out.println("刪除" + dao.deleteByUserID(1) + "筆資料");
//		System.out.println("更新" + dao.updateByUserID(mem2) + "筆資料");
//		System.out.println(dao.selectByUserID(2));

		for (Mem mem : dao.selectAll()) {
			System.out.println(mem.getUserName());
		}

	}
}