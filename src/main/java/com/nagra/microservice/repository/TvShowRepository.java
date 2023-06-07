package com.nagra.microservice.repository;

<<<<<<< HEAD
import com.nagra.microservice.entity.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
=======
import entity.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;

>>>>>>> 1fb768f9b744b845561c4381bdfda96a3c100935
public interface TvShowRepository extends JpaRepository<TvShow, Long> {
}
