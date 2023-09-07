package ge.softlab.lessons.onlinebanking.entities;


import ge.softlab.lessons.onlinebanking.security.ApplicationUserManager;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_by")
    private Integer createdBy;

    @PrePersist
    public void preInsert(){
        createdBy = ApplicationUserManager.getCurrentUser().getId();
    }

    @PreUpdate
    public void oreUpdate(){

    }

}
