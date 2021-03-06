package com.ticket.captain.ticket;

import com.ticket.captain.exception.NotFoundException;
import com.ticket.captain.ticket.dto.TicketCreateDto;
import com.ticket.captain.ticket.dto.TicketDto;
import com.ticket.captain.ticket.dto.TicketUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public List<TicketDto> findAll() {

        return ticketRepository.findAll().stream()
                .map(TicketDto::of)
                .collect(Collectors.toList());
    }

    public TicketDto findById(Long id) {
        return ticketRepository.findById(id)
                .map(TicketDto::of)
                .orElseThrow(NotFoundException::new);
    }

    public TicketDto add(TicketCreateDto create) {
        Ticket target = create.toEntity();
        Ticket created = ticketRepository.save(target);

        return TicketDto.of(created);
    }

    public void delete(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        ticketRepository.delete(ticket);
    }

    @Transactional
    public TicketDto update(Long id, TicketUpdateDto update) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        update.apply(ticket);

        return TicketDto.of(ticket);
    }
}
