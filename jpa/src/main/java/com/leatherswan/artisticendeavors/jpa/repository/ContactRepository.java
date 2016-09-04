package com.leatherswan.artisticendeavors.jpa.repository;

import com.leatherswan.artisticendeavors.jpa.model.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
    Contact findByEmail(String email);

    @Query("select distinct c from Contact c")
    List<Contact> findAllContacts();

    @Query("select distinct c from Contact c " +
            "left join fetch c.contactPhones p " +
            "left join fetch c.contactBio b " +
            "left join fetch c.hobbies h " +
            "where c.contactId = ?1")
    Contact findByContactIdWithDetail(Long contactId);

    @Query("select distinct c from Contact c left join fetch " +
            "c.products p where p.id = ?1")
    public List<Contact> findByProductId(long productId);

    @Query("select distinct c from Contact c left join fetch " +
            "c.contactPhones p left join fetch c.hobbies h left join fetch c.contactBio b")
    List<Contact> findAllWithDetail();

    @Query("select c from Contact c where c.lastName like %:lastName%")
    List<Contact> searchByLastName(@Param("lastName") String lastName);

    List<Contact> findByLastNameIgnoreCaseContains(@Param("lastName") String lastName);
}
