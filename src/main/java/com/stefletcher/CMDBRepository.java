package com.stefletcher;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by 2ball on 12/6/2016.
 */
@Configuration
@EnableJpaRepositories
public interface CMDBRepository extends JpaRepository<ServiceDefinition, Long> {
    List<ServiceDefinition> findByLineOfBusiness(String lineOfBusiness);
    List<ServiceDefinition> findByServiceCode(String serviceCode);
}

@Entity
class ServiceDefinition {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    @Override
    public String toString() {
        return "ServiceDefinition{" +
                "id=" + id +
                ", serviceCode=" + serviceCode +
                ", lineOfBusiness=" + lineOfBusiness +
                '}';
    }

    public ServiceDefinition() {
    }

    public ServiceDefinition(String serviceCode, String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
        this.serviceCode = serviceCode;
    }


    private String serviceCode;
    private String lineOfBusiness;


}
