package com.javangarda.fantacalcio.commons.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DefaultEntity<T> implements Identifiable<T> {

    @Id
    @Getter @Setter
    private T id;

    public DefaultEntity() {}

    public DefaultEntity(T id){
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
