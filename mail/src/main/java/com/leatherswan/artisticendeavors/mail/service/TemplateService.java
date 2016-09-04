package com.leatherswan.artisticendeavors.mail.service;

import com.leatherswan.artisticendeavors.jpa.model.Post;

public interface TemplateService {

    String getRobotsTxt();

    String createPostHtml(Post post);
}
