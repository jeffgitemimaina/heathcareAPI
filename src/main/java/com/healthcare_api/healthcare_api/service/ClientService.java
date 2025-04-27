package com.healthcare_api.healthcare_api.service;

import com.healthcare_api.healthcare_api.dto.ClientDTO;
import com.healthcare_api.healthcare_api.dto.ProgramDTO;
import com.healthcare_api.healthcare_api.entity.Client;
import com.healthcare_api.healthcare_api.entity.Enrollment;
import com.healthcare_api.healthcare_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientDTO createClient(ClientDTO clientDTO) {
        if (clientDTO.getFirstName() == null || clientDTO.getLastName() == null) {
            throw new IllegalArgumentException("First name and last name are required");
        }
        Client client = new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setDob(clientDTO.getDob());
        client = clientRepository.save(client);

        ClientDTO result = new ClientDTO();
        result.setId(client.getId());
        result.setFirstName(client.getFirstName());
        result.setLastName(client.getLastName());
        result.setDob(client.getDob());
        return result;
    }

    public List<ClientDTO> searchClients(String query) {
        Long id = null;
        try {
            id = Long.parseLong(query);
        } catch (NumberFormatException ignored) {
        }
        return clientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrId(query, id)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ClientDTO getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return toDTO(client);
    }

    private ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setDob(client.getDob());
        List<ProgramDTO> programs = client.getEnrollments().stream()
                .map(Enrollment::getProgram)
                .map(program -> {
                    ProgramDTO programDTO = new ProgramDTO();
                    programDTO.setId(program.getId());
                    programDTO.setName(program.getName());
                    programDTO.setDescription(program.getDescription());
                    return programDTO;
                })
                .collect(Collectors.toList());
        dto.setPrograms(programs);
        return dto;
    }
}