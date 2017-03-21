package com.javangarda.fantacalcio.commons.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(value={AuditingEntityListener.class})
public class VersionedDefaultEntity<T> extends DefaultEntity<T> {

    @Version
    private Long version;

    @CreatedDate
    private Instant createdDateTime;

    @LastModifiedDate
    private Instant updatedDateTime;

    public VersionedDefaultEntity() {
        super();
    }

    public VersionedDefaultEntity(T id){
        super(id);
    }
}
