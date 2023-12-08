package com.study.studyspring.repository;

import com.study.studyspring.domain.Board;
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
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("idx");
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("name", member.getName());
//        parameters.put("id", member.getLoginId());
//        parameters.put("pwd", member.getPwd());
//
//        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        member.setId(key.longValue());

        jdbcTemplate.update("INSERT INTO member (idx, id, name , pwd) values(member_seq.NEXTVAL,?,?,?)", member.getLoginId(), member.getName(), member.getPwd());

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
        jdbcTemplate.update("UPDATE MEMBER SET files = ? WHERE name = ?", filePath, name);
    }

    @Override
    public void contentSave(String title, String content, String date, String name, String time) {
        List<Long> rs = jdbcTemplate.query("select idx from member where name = ?", memberRowMapperIdx(), name);
        Long idx = rs.get(0);
        jdbcTemplate.update("INSERT INTO content (title, content, dt, member_idx, study_time) values(?,?,?,?,?)", title, content, date, idx, time);
    }

    @Override
    public Optional<String> findFile(String name) {
        List<String> result = jdbcTemplate.query("select * from member where name = ? ",memberRowMapperImg(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Board> getBoardList(String name) {
        List<Long> rs = jdbcTemplate.query("select idx from member where name = ?", memberRowMapperIdx(), name);
        Long idx = rs.get(0);
        List<Board> result = jdbcTemplate.query("select * from content where member_idx = ?", boardRowMapper(), idx);
        return result;
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setName(rs.getString("name"));
            return member;
        };
    }
    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setIdx(rs.getInt("content_idx"));
            board.setDate(rs.getString("dt"));
            board.setTitle(rs.getString("title"));
            board.setTime(rs.getString("study_time"));
            return board;
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
    private RowMapper<Long> memberRowMapperIdx() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(Long.valueOf(rs.getString("idx")));
            return member.getId();
        };
    }
}
