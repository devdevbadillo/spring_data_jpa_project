package com.david.jpa_project.services.utils.store_procedure;

import com.david.jpa_project.controller.dto.request.RequestCreateCustomerDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomerSPs {

    private final EntityManager entityManager;

    public StoredProcedureQuery createSPQueryForRegisterCustomer(){
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

    public StoredProcedureQuery setParametersForRegisterCustomer(RequestCreateCustomerDTO requestCreateCustomerDTO){
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
