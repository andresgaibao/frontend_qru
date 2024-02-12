
package com.example.qru.repositories;

import com.example.qru.entities.Roll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RollRepository extends JpaRepository<Roll, String>{
    
}
