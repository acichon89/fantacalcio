package com.javangarda.fantacalcio.extras;

import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.dateandtime.joda.PersistentDateTime;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@TypeDef(defaultForType=DateTime.class, typeClass=PersistentDateTime.class)
@EntityListeners(value={AuditingEntityListener.class})
public abstract class DefaultEntity<T> implements Identifable<T> {

	@Id
	@Getter @Setter
	private T id;

	@Version
	@Getter @Setter
	private Long version;

	@CreatedDate
	@Getter
	private DateTime createdDateTime;

	@LastModifiedDate
	@Getter
	private DateTime updatedDateTime;

	public void merge(DefaultEntity<T> other, boolean ignoreNulls) {
		String[] nullProperties = ignoreNulls ? ObjectUtils.getNullPropertyNames(other) : null;
		BeanUtils.copyProperties(other, this, nullProperties);
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.getId() != null ? this.getId().hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof DefaultEntity<?>) || getClass() != object.getClass()){
			return false;
		}
		DefaultEntity<?> other = (DefaultEntity<?>) object;
		if (this.getId() == null || !this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [ID=" + id + "]";
	}

	
}