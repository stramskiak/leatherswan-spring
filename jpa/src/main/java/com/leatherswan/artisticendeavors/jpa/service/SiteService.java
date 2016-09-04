package com.leatherswan.artisticendeavors.jpa.service;

import com.leatherswan.artisticendeavors.jpa.dto.SiteOptionDTO;
import com.leatherswan.artisticendeavors.jpa.exceptions.SiteOptionNotFoundException;
import com.leatherswan.artisticendeavors.jpa.model.SiteOption;
import org.springframework.transaction.annotation.Transactional;

/**
 * Modified by stramskiak
 */
public interface SiteService {

    SiteOption update(SiteOptionDTO siteOptionDTO) throws SiteOptionNotFoundException;

    @Transactional(readOnly = true)
    SiteOption findOptionByName(String name) throws SiteOptionNotFoundException;

}
