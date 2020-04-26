package OtelOtomasyon;
import java.sql.*;
public class Baglanti {  // Bu Class Üzerinden Diðer formlara veritabanýný çekiyoruz.
	static Connection cnn=null;
	static Statement myStat;
public static Connection baglan() {
	 try {
		cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/oteldb?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey&characterEncoding=UTF-8","root","");
		return cnn;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
 
}
