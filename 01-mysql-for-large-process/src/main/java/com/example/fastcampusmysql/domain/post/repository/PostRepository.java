package com.example.fastcampusmysql.domain.post.repository;

import com.example.fastcampusmysql.util.PageHelper;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
public class PostRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final private String TABLE = "Post";

    static final private RowMapper<Post> POST_ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Post
            .builder()
            .id(resultSet.getLong("id"))
            .memberId(resultSet.getLong("memberId"))
            .contents(resultSet.getString("contents"))
            .createdDate(resultSet.getObject("createdDate", LocalDate.class))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .likeCount(resultSet.getLong("likeCount"))
            .build();

    static final private RowMapper<DailyPostCount> DAILY_POST_COUNT_ROW_MAPPER = (ResultSet resultSet, int rowNum)
            -> new DailyPostCount(
                    resultSet.getLong("memberId"),
                    resultSet.getObject("createdDate", LocalDate.class),
                    resultSet.getLong("count")
            );

    public Page<Post> findAllByMemberIdForPagination(Long memberId, Pageable pageable) {
        var sql = String.format("""
                select *
                from %s
                where memberId = :memberId
                order by %s
                limit :size
                offset :offset
                """, TABLE, PageHelper.orderBy(pageable.getSort()));

        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId)
                .addValue("size", pageable.getPageSize())
                .addValue("offset", pageable.getOffset());

        var posts = namedParameterJdbcTemplate.query(sql, params, POST_ROW_MAPPER);
        return new PageImpl(posts, pageable, getCount(memberId));
    }

    public Optional<Post> findById(Long postId, Boolean requiredLock){

        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE id=:id
                """, TABLE);
        if(requiredLock){
            sql += "FOR UPDATE";
        }
        var params = new MapSqlParameterSource()
                .addValue("id", postId);
        return Optional.of(namedParameterJdbcTemplate.queryForObject(
                sql, params, POST_ROW_MAPPER
        ));
    }

    public List<Post> findAllByInId(List<Long> ids){
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE id IN (:ids)
                """, TABLE);

        var params = new MapSqlParameterSource()
                .addValue("ids", ids);
        return namedParameterJdbcTemplate.query(sql, params, POST_ROW_MAPPER);
    }

    public List<Post> findAllByMemberIdAndOrderByIdDesc(Long memberId, Long size) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE memberId = :memberId
                ORDER BY id DESC
                LIMIT :size
                """, TABLE);

        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId)
                .addValue("size", size);
        return namedParameterJdbcTemplate.query(sql, params, POST_ROW_MAPPER);
    }

    public List<Post> findAllByInMemberIdAndOrderByIdDesc(List<Long> memberIds, Long size) {
        if (memberIds.isEmpty()) {
            return List.of();
        }
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE memberId IN (:memberIds)
                ORDER BY id DESC
                limit :size
                """, TABLE);

        var params = new MapSqlParameterSource()
                .addValue("memberIds", memberIds)
                .addValue("size", size);
        return namedParameterJdbcTemplate.query(sql, params, POST_ROW_MAPPER);
    }

    public List<Post> findAllByLessThanIdAndMemberIdAndOrderBYIdDesc(Long id, Long memberId, Long size) {
        var sql = String.format("""
                select *
                from %s
                where memberId = :memberId and id < :id
                ORDER BY id DESC
                limit :size
                """, TABLE);

        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("memberId", memberId)
                .addValue("size", size);
        return namedParameterJdbcTemplate.query(sql, params, POST_ROW_MAPPER);
    }

    public List<Post> findAllByLessThanIdAndInMemberIdAndOrderBYIdDesc(Long id, List<Long> memberIds, Long size) {
        if (memberIds.isEmpty()) {
            return List.of();
        }
        var sql = String.format("""
                select *
                from %s
                where memberId in (:memberIds) and id < :id
                ORDER BY id DESC
                limit :size
                """, TABLE);

        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("memberIds", memberIds)
                .addValue("size", size);
        return namedParameterJdbcTemplate.query(sql, params, POST_ROW_MAPPER);
    }

    private Long getCount(Long memberId) {
        var sql = String.format("""
                SELECT count(id)
                FROM %s
                WHERE memberId = :memberId
                """, TABLE);
        var params = new MapSqlParameterSource().addValue("memberId", memberId);
        return namedParameterJdbcTemplate.queryForObject(sql, params, Long.class);
    }

    public Post save(Post post) {
        if(post.getId() == null)
            return insert(post);
        return update(post);
//        throw new UnsupportedOperationException("Post doesn't support Id renewal.");
    }

    public void bulkInsert(List<Post> posts) {
        var sql = String.format("""
                INSERT INTO %s (memberId, contents, createdDate, createdAt)
                VALUES (:memberId, :contents, :createdDate, :createdAt)
                """, TABLE);
        SqlParameterSource[] params = posts
                .stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }

    private Post insert(Post post) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
        Long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return Post.builder()
                .id(id)
                .memberId(post.getMemberId())
                .contents(post.getContents())
                .createdDate(post.getCreatedDate())
                .createdAt(post.getCreatedAt())
                .build();
    }


    public List<DailyPostCount> groupByCreatedDateOnMember(DailyPostCountRequest request) {
        var sql = String.format(
                """
                SELECT createdDate, memberId, count(id) AS count
                FROM %s
                WHERE memberId = :memberId and createdDate between :firstDate and :lastDate
                GROUP BY memberId, createdDate
                """, TABLE);
        var param = new BeanPropertySqlParameterSource(request);
        return namedParameterJdbcTemplate.query(sql, param, DAILY_POST_COUNT_ROW_MAPPER);
    }


    public List<Post> findAllWithLimit() {
        var sql = String.format("SELECT * FROM %s LIMIT 5", TABLE);
        return namedParameterJdbcTemplate.query(sql, POST_ROW_MAPPER);
    }

    private Post update(Post post){
        var sql = String.format("""
                UPDATE %s SET
                memberId = :memberId,
                contents = :contents,
                createdDate = :createdDate,
                createdAt= :createdAt,
                likeCount = :likeCount
                WHERE id = :id
                """, TABLE);
        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
        namedParameterJdbcTemplate.update(sql, params);
        return post;
    }
}
