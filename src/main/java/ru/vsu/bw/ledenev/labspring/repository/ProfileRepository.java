package ru.vsu.bw.ledenev.labspring.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.vsu.bw.ledenev.labspring.entity.Profile;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ProfileRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Profile> rowMapper = (rs, rowNum) -> {
        Profile profile = new Profile();
        profile.setId(UUID.fromString(rs.getString("id")));
        profile.setLogin(rs.getString("login"));
        profile.setPassword(rs.getString("password"));
        return profile;
    };

    public Profile save(Profile profile) {
        String sql = "INSERT INTO profiles (id, login, password) VALUES (?, ?, ?)";
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(sql, id, profile.getLogin(), profile.getPassword());
        profile.setId(id);
        return profile;
    }

    public Optional<Profile> findByLogin(String login) {
        String sql = "SELECT * FROM profiles WHERE login = ?";
        return jdbcTemplate.query(sql, rowMapper, login)
                .stream()
                .findFirst();
    }
}
