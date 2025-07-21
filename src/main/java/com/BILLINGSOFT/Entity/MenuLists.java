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
@Table(name = "eb_menulists")
public class MenuLists {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perm_seq")
    @SequenceGenerator(name = "perm_seq", sequenceName = "perm_seq", allocationSize = 1)
    @Column(name = "menu_id")
    private int menuid;

    @Column(name="menu_group")
    private String menuGroup;

    @Column(name = "menu_name")
    private String menuName;
}
