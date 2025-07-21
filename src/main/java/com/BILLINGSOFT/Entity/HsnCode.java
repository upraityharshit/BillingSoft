package com.BILLINGSOFT.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "eb_hsncode")
public class HsnCode {
    
    @Id
	@Column(name = "id")
    private long id;

    @Column(name = "hsncode")
    @NotBlank(message = "HSN CODE should not be blank")
    private String hsncode;

    @Column(name = "gst")
    private int gst;

    @Column(name = "description",length=2000)
    private String description;
}
