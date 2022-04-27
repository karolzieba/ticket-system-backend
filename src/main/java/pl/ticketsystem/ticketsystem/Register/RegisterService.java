package pl.ticketsystem.ticketsystem.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Account.AccountRepository;
import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Agency.AgencyRepository;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Client.ClientRepository;
import pl.ticketsystem.ticketsystem.Moderator.Moderator;
import pl.ticketsystem.ticketsystem.Moderator.ModeratorRepository;
import pl.ticketsystem.ticketsystem.Role.Role;

import java.util.Objects;

@Service
public class RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final ModeratorRepository moderatorRepository;
    private final ClientRepository clientRepository;
    private final AgencyRepository agencyRepository;

    @Autowired
    public RegisterService(PasswordEncoder passwordEncoder, AccountRepository accountRepository,
                           ModeratorRepository moderatorRepository,
                           ClientRepository clientRepository,
                           AgencyRepository agencyRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.moderatorRepository = moderatorRepository;
        this.clientRepository = clientRepository;
        this.agencyRepository = agencyRepository;
    }

    public void register(Moderator moderator) {
        if(!Objects.isNull(moderator.getUserNameModerator()) &&
                !Objects.isNull(moderator.getAccount())) {
            if(!Objects.isNull(moderator.getAccount().getEmailAccount()) &&
                    !Objects.isNull(moderator.getAccount().getUsername()) &&
                    !Objects.isNull(moderator.getAccount().getPassword())) {
                    if(moderator.getAccount().getEmailAccount().indexOf('@') != -1) {
                        if(!moderatorRepository.existsByUserNameModerator(moderator.getUserNameModerator()) &&
                                !accountRepository.existsByUsername(moderator.getAccount().getUsername()) &&
                                !accountRepository.existsByEmailAccount(moderator.getAccount().getEmailAccount())) {

                            moderator.getAccount().setRole(Role.MODERATOR);
                            moderator.getAccount().setPassword(passwordEncoder.encode(moderator.getAccount().getPassword()));

                            accountRepository.save(moderator.getAccount());

                            if(accountRepository.existsByUsername(moderator.getAccount().getUsername())) {
                                moderatorRepository.save(moderator);
                                System.out.println("Success!");
                            }
                            else {
                                System.out.println("The account has not been added!");
                            }
                        }
                        else {
                            System.out.println("A moderator name or account with this data already exists!");
                        }
                    }
                    else {
                        System.out.println("Given email does not have @!");
                    }
            }
            else {
                System.out.println("One of the fields is empty!");
            }
        }
        else {
            System.out.println("Account or moderator name is empty!");
        }
    }

    public void register(Client client) {
        if(!Objects.isNull(client.getNameUser()) &&
                !Objects.isNull(client.getSurName()) &&
                !Objects.isNull(client.getDateOfBirth()) &&
                !Objects.isNull(client.getPhoneNumber()) &&
                !Objects.isNull(client.getAccount())) {
            if(!Objects.isNull(client.getAccount().getEmailAccount()) &&
                    !Objects.isNull(client.getAccount().getUsername()) &&
                    !Objects.isNull(client.getAccount().getPassword())) {
                if(client.getAccount().getEmailAccount().indexOf('@') != -1) {
                    if(!clientRepository.existsByPhoneNumber(client.getPhoneNumber()) &&
                            !accountRepository.existsByUsername(client.getAccount().getUsername()) &&
                            !accountRepository.existsByEmailAccount(client.getAccount().getEmailAccount())) {


                        client.getAccount().setRole(Role.CLIENT);
                        client.getAccount().setPassword(passwordEncoder.encode(client.getAccount().getPassword()));

                        accountRepository.save(client.getAccount());

                        if(accountRepository.existsByUsername(client.getAccount().getUsername())) {
                            clientRepository.save(client);
                            System.out.println("Success!");
                        }
                        else {
                            System.out.println("The account has not been added!");
                        }
                    }
                    else {
                        System.out.println("An account or any client field with this data already exists!");
                    }
                }
                else {
                    System.out.println("Given email does not have @!");
                }
            }
            else {
                System.out.println("One of the fields is empty!");
            }
        }
        else {
            System.out.println("Account or any client field is empty!");
        }
    }

    public void register(Agency agency) {
        if(!Objects.isNull(agency.getNameCompany()) &&
                !Objects.isNull(agency.getNIP()) &&
                !Objects.isNull(agency.getNumberPhone())) {
            if(!Objects.isNull(agency.getAccount().getEmailAccount()) &&
                    !Objects.isNull(agency.getAccount().getUsername()) &&
                    !Objects.isNull(agency.getAccount().getPassword())) {
                if(agency.getAccount().getEmailAccount().indexOf('@') != -1) {
                    if(!agencyRepository.existsByNIP(agency.getNIP()) &&
                            !accountRepository.existsByUsername(agency.getAccount().getUsername()) &&
                            !accountRepository.existsByEmailAccount(agency.getAccount().getEmailAccount())) {

                        agency.getAccount().setRole(Role.AGENCY);
                        agency.getAccount().setPassword(passwordEncoder.encode(agency.getAccount().getPassword()));
                        accountRepository.save(agency.getAccount());

                        if(accountRepository.existsByUsername(agency.getAccount().getUsername())) {
                            agencyRepository.save(agency);
                            System.out.println("Success!");
                        }
                        else {
                            System.out.println("The account has not been added!");
                        }
                    }
                    else {
                        System.out.println("An account or any agency field with this data already exists!");
                    }
                }
                else {
                    System.out.println("Given email does not have @!");
                }
            }
            else {
                System.out.println("One of the fields is empty!");
            }
        }
        else {
            System.out.println("Account or any agency field is empty!");
        }
    }
}
