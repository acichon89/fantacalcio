package com.javangarda.fantacalcio.extras;

import javax.persistence.*;

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
	@Getter
	private T id;

	@Version
	@Getter
	private Long version;

	@CreatedDate
	@Getter
	@Column(name = "created_date_time")
	private DateTime createdDateTime;

	@LastModifiedDate
	@Getter
	@Column(name = "updated_date_time")
	private DateTime updatedDateTime;

	public DefaultEntity() {};

	public DefaultEntity (T id) {
		this.id=id;
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