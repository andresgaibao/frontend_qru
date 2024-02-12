
package com.example.qru.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
      @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    private String name;
    private String username;
    private String cc;
    private String email;
    private String password;
    private String password2;
    
    @ManyToOne
    private Roll roll;
    @OneToOne
    private Photos photos;
}
