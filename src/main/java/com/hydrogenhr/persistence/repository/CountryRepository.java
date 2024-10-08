package com.hydrogenhr.persistence.repository;

import com.hydrogenhr.persistence.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:19 PM
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByNumCode(String s);
}
