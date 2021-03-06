package com.leatherswan.artisticendeavors.jpa.repository;

import com.leatherswan.artisticendeavors.jpa.model.SiteOption;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Modified by stramskiak
 */
@Service
public interface SiteOptionRepository extends Repository<SiteOption, Long> {

    SiteOption findByNameIgnoreCase(String optionName) throws DataAccessException;

    Collection<SiteOption> findAll() throws DataAccessException;

    SiteOption save(SiteOption siteOption) throws DataAccessException;

}