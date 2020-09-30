package com.ticket.captain.festival;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface FestivalRepository extends JpaRepository<Festival, Long> {
}
