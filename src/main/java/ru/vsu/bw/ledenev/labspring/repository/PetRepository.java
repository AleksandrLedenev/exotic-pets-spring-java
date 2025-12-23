package ru.vsu.bw.ledenev.labspring.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.bw.ledenev.labspring.entity.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PetRepository {

    private final JdbcTemplate jdbcTemplate;

    public PetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pet p = new Pet();
        p.setId(rs.getObject("id", UUID.class));
        p.setName(rs.getString("name"));
        p.setStrength(rs.getInt("strength"));
        p.setProfileId(UUID.fromString(rs.getString("profile_id")));
        return p;
    }

    public void createPet(Pet pet) {
        String sql = "INSERT INTO pets (id, name, strength, profile_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, pet.getId(), pet.getName(), pet.getStrength(), pet.getProfileId());
    }

    public Optional<Pet> findById(UUID id) {
        String sql = "SELECT * FROM pets WHERE id = ?";
        try {
            Pet pet = jdbcTemplate.queryForObject(sql, PetRepository::mapRow, id);
            return Optional.of(pet);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Pet> findByName(String namePart) {
        String sql = "SELECT * FROM pets WHERE name ILIKE ?";
        return jdbcTemplate.query(sql, PetRepository::mapRow, "%" + namePart + "%");
    }

    public void updatePet(Pet pet) {
        String sql = "UPDATE pets SET name = ?, strength = ?, profile_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, pet.getName(), pet.getStrength(), pet.getProfileId(), pet.getId());
    }

    public void deletePet(UUID id) {
        String sql = "DELETE FROM pets WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
