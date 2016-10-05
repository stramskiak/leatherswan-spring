package com.leatherswan.artisticendeavors.jpa.repository;

import java.util.List;

import com.leatherswan.artisticendeavors.jpa.model.Contact;
import com.leatherswan.artisticendeavors.jpa.model.ContactBio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


/**
 * @author Anita
 *  "we should write class name in the query not the table name" (stacktrace)
 *  Crud uses Artist.class so queries should also...?
 */
@Service
public interface ContactBioRepository extends CrudRepository<ContactBio, Long> {

//    ContactBio findByContactId(long contact_id);
//    ContactBio findByContactBioId(long contact_bio_id);
    ContactBio findByContactBioId(Long id);
//    ContactBio findByContact_ContactId(Long id);

}
