package com.healthcare_api.healthcare_api.service;

import com.healthcare_api.healthcare_api.dto.ClientDTO;
import com.healthcare_api.healthcare_api.entity.Client;
import com.healthcare_api.healthcare_api.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private ClientDTO clientDTO;
    private Client client;

    @BeforeEach
    void setUp() {
        clientDTO = new ClientDTO();
        clientDTO.setFirstName("John");
        clientDTO.setLastName("Doe");
        clientDTO.setDob(LocalDate.of(1990, 1, 1));

        client = new Client();
        client.setId(1L);
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setDob(LocalDate.of(1990, 1, 1));
    }

    @Test
    void createClient_success() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDTO result = clientService.createClient(clientDTO);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals(1L, result.getId());
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void createClient_nullFirstName_throwsException() {
        clientDTO.setFirstName(null);

        assertThrows(IllegalArgumentException.class, () -> clientService.createClient(clientDTO));
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void searchClients_success() {
        when(clientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrId("john", "john", 1L))
                .thenReturn(Arrays.asList(client));

        List<ClientDTO> result = clientService.searchClients("john");

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(clientRepository).findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrId("john", "john", 1L);
    }

    @Test
    void getClient_success() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        ClientDTO result = clientService.getClient(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(clientRepository).findById(1L);
    }

    @Test
    void getClient_notFound_throwsException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clientService.getClient(1L));
        verify(clientRepository).findById(1L);
    }
}