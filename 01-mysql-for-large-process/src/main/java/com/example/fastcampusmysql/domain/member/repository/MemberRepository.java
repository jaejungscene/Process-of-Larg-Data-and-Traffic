package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final private String TABLE = "Member";
    static final private RowMapper<Member> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Member
            .builder()
            .id(resultSet.getLong("id"))
            .email(resultSet.getString("email"))
            .nickname(resultSet.getString("nickname"))
            .birthday(resultSet.getObject("birthday", LocalDate.class))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();


    public Member save(Member member) {
        if (member.getId() == null) {
            return insert(member);
        }
        return update(member);
    }

    private Member insert(Member member){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        Long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return Member
                .builder()
                .id(id)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birthday(member.getBirthday())
                .createdAt(member.getCreatedAt())
                .build();

    }
    public Optional<Member> findById(Long id) {
        /**
         * select * from Member
         * where id = :id
         */
        var sql = String.format("SELECT * FROM %s WHERE id = :id", TABLE);
        var param = new MapSqlParameterSource().addValue("id", id);
        var member = namedParameterJdbcTemplate.queryForObject(sql, param, ROW_MAPPER);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        var sql = String.format("SELECT * FROM %s", TABLE);
        return namedParameterJdbcTemplate.query(sql, ROW_MAPPER);
    }

    public List<Member> findAllByIdIn(List<Long> ids) {
        if (ids.isEmpty())
            return List.of();
        var sql = String.format("SELECT * FROM %s WHERE id in (:ids)", TABLE);
        var params = new MapSqlParameterSource().addValue("ids", ids);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    private Member update(Member member) {
        //TODO: implemented
        var sql = String.format("UPDATE %s set email = :email, nickname = :nickname, birthday = :birthday WHERE id = :id", TABLE);
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        namedParameterJdbcTemplate.update(sql, params);
        return member;
    }
}
