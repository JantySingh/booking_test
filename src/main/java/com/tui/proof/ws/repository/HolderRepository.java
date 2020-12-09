package com.tui.proof.ws.repository;

import com.tui.proof.ws.model.Holder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Author: Janty
 */

@Repository
public interface HolderRepository extends JpaRepository<Holder, Integer> {

}
