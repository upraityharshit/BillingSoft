package com.BILLINGSOFT.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "currentsession")
public class SessionFinYear {

    @Id
    @Column(name="year_id")
    private long yearid;

    @Column(name= "fin_name")
    private String finname;
    
}
