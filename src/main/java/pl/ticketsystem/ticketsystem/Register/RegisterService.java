package pl.ticketsystem.ticketsystem.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Account.AccountRepository;
import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Agency.AgencyRepository;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Client.ClientRepository;
import pl.ticketsystem.ticketsystem.Moderator.Moderator;
import pl.ticketsystem.ticketsystem.Moderator.ModeratorRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class RegisterService {
    private final AccountRepository accountRepository;
    private final ModeratorRepository moderatorRepository;
    private final ClientRepository clientRepository;
    private final AgencyRepository agencyRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleUserRepository roleUserRepository;
    @Autowired
    public RegisterService(RoleUserRepository roleUserRepository,
                           AccountRepository accountRepository,
                           ModeratorRepository moderatorRepository,
                           ClientRepository clientRepository,
                           AgencyRepository agencyRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleUserRepository = roleUserRepository;
        this.accountRepository = accountRepository;
        this.moderatorRepository = moderatorRepository;
        this.clientRepository = clientRepository;
        this.agencyRepository = agencyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Moderator moderator) {
        if(!Objects.isNull(moderator.getUserNameModerator()) &&
                !Objects.isNull(moderator.getAccount())) {
            if(!Objects.isNull(moderator.getAccount().getEmailAccount()) &&
                    !Objects.isNull(moderator.getAccount().getAccountLogin()) &&
                    !Objects.isNull(moderator.getAccount().getPasswordAccount())) {
                    if(moderator.getAccount().getEmailAccount().indexOf('@') != -1) {
                        if(!moderatorRepository.existsByUserNameModerator(moderator.getUserNameModerator()) &&
                                !accountRepository.existsByAccountLogin(moderator.getAccount().getAccountLogin()) &&
                                !accountRepository.existsByEmailAccount(moderator.getAccount().getEmailAccount())) {
                            Set<RoleUser> roles = new HashSet<>();

                            RoleUser roleUser;
                            for(int i = 0; i < 3; i++) {
                                roleUser = roleUserRepository.findById(i).orElse(null);
                                roles.add(roleUser);
                            }

                            moderator.getAccount().setPasswordAccount(passwordEncoder.encode(moderator.getAccount().getPasswordAccount()));
                            accountRepository.save(moderator.getAccount());

                            if(accountRepository.existsByAccountLogin(moderator.getAccount().getAccountLogin())) {
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
                    !Objects.isNull(client.getAccount().getAccountLogin()) &&
                    !Objects.isNull(client.getAccount().getPasswordAccount())) {
                if(client.getAccount().getEmailAccount().indexOf('@') != -1) {
                    if(!clientRepository.existsByPhoneNumber(client.getPhoneNumber()) &&
                            !accountRepository.existsByAccountLogin(client.getAccount().getAccountLogin()) &&
                            !accountRepository.existsByEmailAccount(client.getAccount().getEmailAccount())) {
                        Set<RoleUser> roles = new HashSet<>();
                        RoleUser roleUser = roleUserRepository.findById(1).orElse(null);
                        roles.add(roleUser);

                        client.getAccount().setPasswordAccount(passwordEncoder.encode(client.getAccount().getPasswordAccount()));
                        accountRepository.save(client.getAccount());

                        if(accountRepository.existsByAccountLogin(client.getAccount().getAccountLogin())) {
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
                    !Objects.isNull(agency.getAccount().getAccountLogin()) &&
                    !Objects.isNull(agency.getAccount().getPasswordAccount())) {
                if(agency.getAccount().getEmailAccount().indexOf('@') != -1) {
                    if(!agencyRepository.existsByNIP(agency.getNIP()) &&
                            !accountRepository.existsByAccountLogin(agency.getAccount().getAccountLogin()) &&
                            !accountRepository.existsByEmailAccount(agency.getAccount().getEmailAccount())) {
                        Set<RoleUser> roles = new HashSet<>();
                        RoleUser roleUser = roleUserRepository.findById(2).orElse(null);
                        roles.add(roleUser);

                        agency.getAccount().setPasswordAccount(passwordEncoder.encode(agency.getAccount().getPasswordAccount()));
                        accountRepository.save(agency.getAccount());

                        if(accountRepository.existsByAccountLogin(agency.getAccount().getAccountLogin())) {
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
