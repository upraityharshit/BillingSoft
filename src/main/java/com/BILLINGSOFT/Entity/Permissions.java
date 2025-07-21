package com.BILLINGSOFT.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
@Table(name = "eb_permission")
public class Permissions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perm_seq")
    @SequenceGenerator(name = "perm_seq", sequenceName = "perm_seq", allocationSize = 1)
    @Column(name = "pid")
    private int pid;

    @Column(name = "user_username")
	private String username;

    @Column(name="menu_group")
    private String menuGroup;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "can_view")
    private boolean view;
    
    @Column(name = "can_create")
    private boolean create;
    
    @Column(name = "can_edit")
    private boolean edit;
    
    @Column(name = "can_delete")
    private boolean delete;
}
