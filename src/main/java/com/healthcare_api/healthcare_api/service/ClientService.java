package com.healthcare_api.healthcare_api.service;


import com.healthcare_api.healthcare_api.dto.ClientDTO;
import com.healthcare_api.healthcare_api.dto.ProgramDTO;
import com.healthcare_api.healthcare_api.entity.Client;
import com.healthcare_api.healthcare_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setDob(clientDTO.getDob());
        client = clientRepository.save(client);
        clientDTO.setId(client.getId());
        return clientDTO;
    }

    public List<ClientDTO> searchClients(String query) {
        return clientRepository.searchClients(query).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO getClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(this::mapToDTO).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    private ClientDTO mapToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setDob(client.getDob());
        dto.setPrograms(client.getPrograms().stream()
                .map(program -> {
                    ProgramDTO programDTO = new ProgramDTO();
                    programDTO.setId(program.getId());
                    programDTO.setName(program.getName());
                    programDTO.setDescription(program.getDescription());
                    return programDTO;
                })
                .collect(Collectors.toList()));
        return dto;
    }
}