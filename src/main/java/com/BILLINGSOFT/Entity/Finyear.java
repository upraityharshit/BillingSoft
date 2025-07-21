package com.BILLINGSOFT.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "eb_finyear")
public class Finyear {

    @Id
    @Column(name= "id")
    private long id;

    @Column(name= "name")
    @NotBlank
    private String name;

    @Column(name= "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromdate;

    @Column(name= "to_date")
    @Temporal(TemporalType.DATE)
    private Date todate;

    @Column(name= "is_Current")
    private boolean current;

    @Column(name= "is_Active")
    private boolean active;

}
