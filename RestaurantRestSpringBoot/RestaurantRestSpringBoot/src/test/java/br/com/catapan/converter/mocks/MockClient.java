package br.com.catapan.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.catapan.data.model.Client;
import br.com.catapan.data.vo.v1.ClientVO;

public class MockClient {

    public Client mockEntity() {
    	return mockEntity(0);
    }
    
    public ClientVO mockVO() {
    	return mockVO(0);
    }
    
    public List<Client> mockEntityList() {
        List<Client> clients = new ArrayList<Client>();
        for (int i = 0; i < 14; i++) {
        	clients.add(mockEntity(i));
        }
        return clients;
    }

    public List<ClientVO> mockVOList() {
        List<ClientVO> clients = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
        	clients.add(mockVO(i));
        }
        return clients;
    }
    
    private Client mockEntity(Integer number) {
    	Client client = new Client();
    	client.setId(number.longValue());
    	client.setName("Name Test" + number);
        return client;
    }

    private ClientVO mockVO(Integer number) {
    	ClientVO client = new ClientVO();
    	client.setName("Name Test" + number);
        return client;
    }

}
