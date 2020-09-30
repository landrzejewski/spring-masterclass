package pl.training.cloud.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

//https://deinum.biz/2019-02-13-Lombok-Data-Ojects-Arent-Entities/
//https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @GeneratedValue
    @Id
    protected Long id;

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        BaseEntity otherEntity = (BaseEntity) otherObject;
        return this.id != null && Objects.equals(id, otherEntity.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
