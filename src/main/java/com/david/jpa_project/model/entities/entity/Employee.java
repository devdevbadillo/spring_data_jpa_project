package com.david.jpa_project.model.entities.entity;

import com.david.jpa_project.model.entities.enums.JobTitle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_EMPLOYEES", schema = "demo")
@NamedStoredProcedureQuery(
        name = "registerEmployee",
        procedureName = "demo.sp_register_employee",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_executor_user_id", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_last_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_job_title", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_user_id", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_status_code", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_message", type = String.class)
        }
)
public class Employee extends User {
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private JobTitle jobTitle;

    @OneToOne(mappedBy = "user")
    private Credential credential;

    @Basic(optional = false)
    private String userAudit;
}
