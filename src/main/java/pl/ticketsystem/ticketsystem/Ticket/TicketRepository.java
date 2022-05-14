package pl.ticketsystem.ticketsystem.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Event.Event;


import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(nativeQuery = true, value = "select event.id_event, event.capacity_event, event.date_time_event, event.location_event, event.name_event, event.price_event, \n" +
            "agency.name_company\n" +
            "from event inner join ticket on event.id_event = ticket.id_eventfk inner join agency on event.id_agencyfk = agency.id_agency\n" +
            "inner join client on ticket.id_clientfk = client.id_client inner join account on client.account_fk = account.id_account where account.id_account = ?1")
    List<Object[]> getClientTicket(Long idUser);
    List<Ticket> getTicketsByClient_IdClient(Long idClient);
}
