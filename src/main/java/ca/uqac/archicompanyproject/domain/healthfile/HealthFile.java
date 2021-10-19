package ca.uqac.archicompanyproject.domain.healthfile;

import ca.uqac.archicompanyproject.domain.patient.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    private String medications;

    private String chronicConditions;

    private String emergencyContact;

    @OneToOne(mappedBy = "healthFile")
    @JsonBackReference
    private Patient patient;
}