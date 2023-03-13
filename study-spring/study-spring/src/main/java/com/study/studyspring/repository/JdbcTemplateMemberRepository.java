package com.study.studyspring.repository;

import com.study.studyspring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("idx");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        parameters.put("id", member.getLoginId());
        parameters.put("pwd", member.getPwd());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<String> findById(String id, String pwd) {
        List<String> result = jdbcTemplate.query("select * from member where id = ? and pwd = ?", memberRowMapperLogin(), id, pwd);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("Select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    @Override
    public void imgSave(String name, String filePath) {
        jdbcTemplate.update("UPDATE MEMBER SET file = ? WHERE name = ?", filePath, name);
    }

    @Override
    public Optional<String> findFile(String name) {
        List<String> result = jdbcTemplate.query("select * from member where name = ? ",memberRowMapperImg(), name);
        return result.stream().findAny();
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setName(rs.getString("name"));
            return member;
        };
    }
    private RowMapper<String> memberRowMapperLogin() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setName(rs.getString("name"));
            return member.getName();
        };
    }
    private RowMapper<String> memberRowMapperImg() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setFilepath(rs.getString("file"));
            return member.getFilepath();
        };
    }

}
