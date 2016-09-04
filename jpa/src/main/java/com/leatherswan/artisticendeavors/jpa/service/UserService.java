package com.leatherswan.artisticendeavors.jpa.service;

import com.leatherswan.artisticendeavors.jpa.dto.RoleDTO;
import com.leatherswan.artisticendeavors.jpa.dto.UserDTO;
import com.leatherswan.artisticendeavors.jpa.model.Authority;
import com.leatherswan.artisticendeavors.jpa.model.CurrentUser;
import com.leatherswan.artisticendeavors.jpa.model.User;
import com.leatherswan.artisticendeavors.jpa.model.UserConnection;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserDTO form);

    User getUserByUsername(String username);

    @Transactional(readOnly = true)
    Collection<Authority> getRoles();

    List<User> getUsersWithDetail();

	boolean canAccessUser(CurrentUser currentUser, String username);

	UserConnection getUserConnectionByUserId(String userId);

    User update(UserDTO userDTO);

    @Transactional(readOnly = true)
    Optional<User> getUserByIdWithDetail(Long ID);

    Authority createAuthority(RoleDTO roleDTO);

    Authority updateAuthority(RoleDTO roleDTO);

    Authority getAuthorityById(Long id);

    void deleteAuthority(Authority authority, Collection<User> users);

    Collection<User> getUsersByAuthorityId(Long authorityId);

    User updateHasAvatar(Long userId, boolean hasAvatar);

}