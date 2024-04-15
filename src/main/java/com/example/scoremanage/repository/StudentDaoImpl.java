package com.example.scoremanage.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.scoremanage.form.GetForm;
import com.example.scoremanage.model.Student;
 
 
@Repository
public class StudentDaoImpl implements StudentDao {
     
    private final NamedParameterJdbcTemplate jdbcTemplate;
 
    @Autowired
    public StudentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
 
    public List<Student> findList(GetForm form) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * "
                + "FROM STUDENT "
                + "WHERE STUDENT.id = '1'");

 
     // パラメータ設定用Map
        Map<String, String> param = new HashMap<>();
        // パラメータが存在した場合、where句にセット
        if(form.getEnt_year() != null && form.getEnt_year() != "") {
            sqlBuilder.append(" AND c.cd = :cd");
            param.put("ent_year", form.getEnt_year());
        }
        if(form.getClass_num() != null && form.getClass_num() != "") {
            sqlBuilder.append(" AND TO_CHAR(d.date, 'YYYY/MM') = :date");
            param.put("class_num", form.getClass_num());
        }
        if(form.getIs_attend() != null && form.getIs_attend() != "") {
            sqlBuilder.append(" AND TO_CHAR(d.date, 'YYYY/MM') = :date");
            param.put("is_attend", form.getIs_attend());
        }
     
        String sql = sqlBuilder.toString();
        
        //タスク一覧をMapのListで取得
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
        //return用の空のListを用意
        List<Student> list = new ArrayList<Student>();
 
        //データをDiaryにまとめる
        for(Map<String, Object> result : resultList) {
        	Student student = new Student();
        	//student.setId((long) (result.get("id")));
        	student.setENT_YEAR((Integer)result.get("ent_year"));
        	student.setCLASS_NUM((String)result.get("class_num"));
        	student.setIS_ATTEND((Boolean)result.get("is_attend"));
            list.add(student);
        }
        return list;
    }
}