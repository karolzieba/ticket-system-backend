package pl.ticketsystem.ticketsystem.Moderator;




import pl.ticketsystem.ticketsystem.Client.Client;


import javax.persistence.*;

@Entity
@Table(name = "moderator")
public class Moderator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModerator;
    private String userNameModerator;
    @OneToOne
    @JoinColumn(name="idClientFK", referencedColumnName = "idClient")
    private Client client;
}
