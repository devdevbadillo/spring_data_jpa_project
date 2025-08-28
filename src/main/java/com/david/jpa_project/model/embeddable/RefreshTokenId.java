package com.david.jpa_project.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RefreshTokenId implements Serializable {
    private Long customerId;
    private Long accessTokenId;
}
