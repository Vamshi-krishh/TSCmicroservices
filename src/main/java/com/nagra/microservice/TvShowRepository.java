package com.nagra.microservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {
}
