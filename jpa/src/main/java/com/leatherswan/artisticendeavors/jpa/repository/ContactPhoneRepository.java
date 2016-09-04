package com.leatherswan.artisticendeavors.jpa.repository;

import com.leatherswan.artisticendeavors.jpa.model.ContactPhone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactPhoneRepository extends CrudRepository<ContactPhone, Long> {

    ContactPhone findByContactPhoneId(Long id);
    List<ContactPhone> findByContact_ContactId(Long id);

}
