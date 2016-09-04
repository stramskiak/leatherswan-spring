package com.leatherswan.artisticendeavors.jpa.repository;

import com.leatherswan.artisticendeavors.jpa.model.Hobby;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface HobbyRepository extends CrudRepository<Hobby, Long> {

    Hobby findByHobbyTitleIgnoreCase(String hobbyTitle);
//    Contact findByEmail(String email);
}
