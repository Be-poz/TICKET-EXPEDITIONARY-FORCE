package com.ticket.captain.festival;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.ticket.captain.util.DateTimeUtils.dateTimeOf;

@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;

    public FestivalService(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }

    @Transactional
    public Festival generate(Festival newFestival) {
        return festivalRepository.save(newFestival);
    }


}
