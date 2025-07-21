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
@Table(name = "eb_user")
public class User {
    
	@Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq")
    //@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
	@Column(name = "user_id")
    private Long user_id;

    @Column(name = "full_name")
    @NotBlank(message = "Name should not be blank")
    private String name;
    
    @Column(name = "user_username")
    @NotBlank(message = "Userame should not be blank")
    private String username;

    @Column(name = "user_password")
    @NotBlank(message = "Password should not be blank")
    private String password;

    @Column(name = "user_role")
    private String role;

    @Column(name = "is_locked")
    private boolean islocked;
    
    @Column(name = "failedAttempts")
    private int failedAttempts;
}
