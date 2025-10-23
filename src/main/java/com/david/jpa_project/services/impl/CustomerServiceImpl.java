package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.dto.out.CreateCustomerOut;
import com.david.jpa_project.controller.dto.out.CustomerStatsOut;
import com.david.jpa_project.controller.dto.request.RequestCreateCustomerDTO;
import com.david.jpa_project.model.repositories.store_procedures.CustomerRepository;
import com.david.jpa_project.services.interfaces.ICustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final EntityManager entityManager;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, EntityManager entityManager) {
        this.customerRepository = customerRepository;
        this.entityManager = entityManager;
    }

    @Override
    public CustomerStatsOut getCustomerStats() {
        Map<String, Object> result = customerRepository.getCustomerStatistics();

        return new CustomerStatsOut(
                ((Number) result.get("total_customers")).longValue(),
                ((Number) result.get("enabled_customers")).longValue(),
                ((Number) result.get("verified_customers")).longValue()
        );
    }

    @Override
    public CreateCustomerOut createCustomer(RequestCreateCustomerDTO requestCreateCustomerDTO) {
        StoredProcedureQuery query = setParametersForRegisterCustomer(requestCreateCustomerDTO);

        query.execute();

        String message = (String) query.getOutputParameterValue("p_message");
        Long statusCode = (Long) query.getOutputParameterValue("p_status_code");

        if (statusCode == 409){
            throw new DataIntegrityViolationException(message);
        }

        Long userId = (Long) query.getOutputParameterValue("p_user_id");
        return new CreateCustomerOut(userId, message);
    }

    private StoredProcedureQuery createSPQueryForRegisterCustomer(){
        return entityManager
                .createStoredProcedureQuery("demo.sp_register_customer")
                .registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_last_name", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_phone_number", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_password", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_country", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_state", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_city", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_zip_code", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_street", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_status_code", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_message", String.class, ParameterMode.OUT);
    }

    private StoredProcedureQuery setParametersForRegisterCustomer(RequestCreateCustomerDTO requestCreateCustomerDTO){
        return createSPQueryForRegisterCustomer()
                .setParameter("p_name", requestCreateCustomerDTO.customer().name())
                .setParameter("p_last_name", requestCreateCustomerDTO.customer().lastname())
                .setParameter("p_phone_number", requestCreateCustomerDTO.customer().phoneNumber())
                .setParameter("p_email", requestCreateCustomerDTO.customer().email())
                .setParameter("p_password", requestCreateCustomerDTO.customer().password())
                .setParameter("p_country", requestCreateCustomerDTO.customer().address().country())
                .setParameter("p_state", requestCreateCustomerDTO.customer().address().state())
                .setParameter("p_city", requestCreateCustomerDTO.customer().address().city())
                .setParameter("p_zip_code", requestCreateCustomerDTO.customer().address().zipCode())
                .setParameter("p_street", requestCreateCustomerDTO.customer().address().street());
    }

}
