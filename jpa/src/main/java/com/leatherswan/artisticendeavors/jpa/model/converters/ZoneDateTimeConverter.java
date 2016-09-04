package com.leatherswan.artisticendeavors.jpa.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * The Converter adds the final ingredient to using Java 8 DateTime classes
 * like ZonedDateTime with Spring JPA Auditing and enjoy pretty data in MySQL,
 * H2 and other storage types.
 *
 * From v0.2.0 branch of NixMash Spring Examples Project on GitHub
 *
 * http://nixmash.com/java/spring-jpa-auditing-with-zoneddatetime-and-mysql/
 *
 */
@Converter(autoApply = true)
public class ZoneDateTimeConverter implements AttributeConverter<java.time.ZonedDateTime, java.sql.Timestamp> {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(ZonedDateTime entityValue) {
       return Timestamp.from(entityValue.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(java.sql.Timestamp databaseValue) {
        LocalDateTime localDateTime = databaseValue.toLocalDateTime();
        return localDateTime.atZone(ZoneId.systemDefault());
    }

}