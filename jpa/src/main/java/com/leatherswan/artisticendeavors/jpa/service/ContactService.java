package com.leatherswan.artisticendeavors.jpa.service;

import com.leatherswan.artisticendeavors.jpa.dto.*;
import com.leatherswan.artisticendeavors.jpa.exceptions.ContactNotFoundException;
import com.leatherswan.artisticendeavors.jpa.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    // region Contacts -------------------------------------- */

    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
    List<Contact> getContactsWithDetail();
    List<Contact> searchByLastName(String lastName);

    Contact add(ContactDTO added);
    Contact update(ContactDTO updated) throws ContactNotFoundException;

    Contact findContactById(Long ID) throws ContactNotFoundException;
    Contact getContactByEmail(String email);
    Contact getContactByIdWithDetail(Long ID);
    Contact deleteById(Long id) throws ContactNotFoundException;
    Contact removeHobby(ContactDTO updated, Long hobbyId) throws ContactNotFoundException;

    // endregion

    // region Contact Phones -------------------------------------- */

    List<ContactPhone> findContactPhonesByContactId(Long contactId);
    ContactPhone addContactPhone(ContactPhoneDTO contactPhoneDTO);
    ContactPhone findContactPhoneById(Long contactPhoneID);
    ContactPhone deleteContactPhoneById(Long contactPhoneId) throws ContactNotFoundException;



    // endregion

    // region Contact Bio -------------------------------------- */

    ContactBio findContactBioByContact(Contact contact);
    ContactBio addContactBio(ContactBioDTO contactBioDTO);
    ContactBio findContactBioById(Long contactBioId);
    ContactBio deleteContactBioById(Long contactBioId) throws ContactNotFoundException;


    // endregion

    // region Hobbies --------------------------------------- */

    Hobby addHobby(HobbyDTO hobbyDTO);
    Hobby updateHobbyTitle(HobbyDTO hobbyDTO) throws ContactNotFoundException;
    List<Hobby> findAllHobbies();
    Hobby findByHobbyTitle(String hobbyTitle);

    // endregion

    // region Contact Phones -------------------------------------- */

    List<Product> findContactProductByContactId(Long contactId);
    Product addContactProduct(ProductDTO productDTO);
    Product findContactProductById(Long contactProductID);
    Product deleteContactProductById(Long contactProductId) throws ContactNotFoundException;



    // endregion

}
