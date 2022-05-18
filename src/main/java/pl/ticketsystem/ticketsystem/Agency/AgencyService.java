package pl.ticketsystem.ticketsystem.Agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AgencyService {
    private final AgencyRepository agencyRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AgencyService(AgencyRepository agencyRepository, PasswordEncoder passwordEncoder) {
        this.agencyRepository = agencyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void updateAgency(long idAgency, Agency agency) {
        if(agencyRepository.existsById(idAgency)) {
            Agency updatedAgency = agencyRepository.findById(idAgency).orElseThrow(() -> new IllegalStateException("Agency with this ID does not exist!"));

            if(!Objects.isNull(agency.getAccount().getUsername())) {
                updatedAgency.getAccount().setUsername(agency.getAccount().getUsername());
            }

            if(!Objects.isNull(agency.getAccount().getEmailAccount())) {
                updatedAgency.getAccount().setEmailAccount(agency.getAccount().getEmailAccount());
            }

            if(!Objects.isNull(agency.getAccount().getPassword())) {
                updatedAgency.getAccount().setPassword(passwordEncoder.encode(agency.getAccount().getPassword()));
            }

            if(!Objects.isNull(agency.getNameCompany())) {
                updatedAgency.setNameCompany(agency.getNameCompany());
            }

            if(!Objects.isNull(agency.getNIP())) {
                updatedAgency.setNIP(agency.getNIP());
            }

            if(!Objects.isNull(agency.getNumberPhone())) {
                updatedAgency.setNumberPhone(agency.getNumberPhone());
            }

            agencyRepository.save(updatedAgency);
        }
        else {
            throw new IllegalStateException("Agency with this ID does not exist!");
        }
    }
}
