package com.moonz.javapractice.CP_Practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetService {
    public static List<Major> fetchData() throws SQLException {
        String SQL_QUERY = "select * from major";
        List<Major> majors = null;
        try (Connection con = HikariCPDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );
             ResultSet rs = pst.executeQuery();) {
            majors = new ArrayList<>();
            Major major;
            while ( rs.next() ) {
                major = Major.builder()
                        .majName(rs.getString( "majname" ))
                        .majNo(rs.getInt( "majno" ))
                        .studentCnt(rs.getInt("studentcnt"))
                        .build();
                majors.add(major);
            }
        }
        return majors;
    }
}
