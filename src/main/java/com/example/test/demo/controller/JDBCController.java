package com.example.test.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class JDBCController {
	@RequestMapping("/hello")
	public String index() {
		Student student = new Student("nick", "male", "11");
		insert(student);
		return "SS";
	}

	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://rm-uf6fa7e5745c1uj401o.mysql.rds.aliyuncs.com:3306/mydb1?useSSL=false&autoReconnect=true&failOverReadOnly=false&maxReconnects=10",
					"myadmin", "Gu4992597"); // 使用时，将上述字符串出现中文的地方根据中文提示替换成你对应的信息即可
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private static int insert(Student student) {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into students (Name,Sex,Age) values(?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getSex());
			pstmt.setString(3, student.getAge());
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	static class Student {
		private String Id;
		private String Name;
		private String Sex;
		private String Age;

		Student(String Name, String Sex, String Age) {
			this.Id = null; // default
			this.Name = Name;
			this.Sex = Sex;
			this.Age = Age;
		}

		public String getId() {
			return Id;
		}

		public void setId(String Id) {
			this.Id = Id;
		}

		public String getName() {
			return Name;
		}

		public void setName(String Name) {
			this.Name = Name;
		}

		public String getSex() {
			return Sex;
		}

		public void setSex(String Sex) {
			this.Sex = Sex;
		}

		public String getAge() {
			return Age;
		}

		public void setage(String Age) {
			this.Age = Age;
		}
	}
}
