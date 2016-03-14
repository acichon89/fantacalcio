package com.javangarda.fantacalcio.util.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class DefaultEntity<T> implements Identifable<T> {

	@Id
	@GeneratedValue
	@Getter
	private T id;

	@Version
	@Getter @Setter
	private Long version;

	@Column
	@Getter
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdDateTime;

	@Column
	@Getter
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedDateTime;

	@PrePersist
	public void prePersist() {
		this.createdDateTime = DateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedDateTime = DateTime.now();
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.getId() != null ? this.getId().hashCode() : 0);

		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;

		DefaultEntity other = (DefaultEntity) object;
		if (this.getId() != other.getId()
				&& (this.getId() == null || !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [ID=" + id + "]";
	}

}
