package br.com.catapan.adapter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.catapan.adapter.DozerConverter;
import br.com.catapan.converter.mocks.MockClient;
import br.com.catapan.data.model.Client;
import br.com.catapan.data.vo.v1.ClientVO;

public class DozerConverterClientTest {
	
    MockClient inputObject;

    @Before
    public void setUp() {
        inputObject = new MockClient();
    }

    @Test
    public void parseEntityToVOTest() {
        ClientVO output = DozerConverter.parseObject(inputObject.mockEntity(), ClientVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getKey());
        Assert.assertEquals("Name Test0", output.getName());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<ClientVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), ClientVO.class);
        ClientVO outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
        Assert.assertEquals("Name Test0", outputZero.getName());
        
        ClientVO outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
        Assert.assertEquals("Name Test7", outputSeven.getName());
        
        ClientVO outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        Assert.assertEquals("Name Test12", outputTwelve.getName());
    }

    @Test
    public void parseVOToEntityTest() {
        Client output = DozerConverter.parseObject(inputObject.mockVO(), Client.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("Name Test0", output.getName());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Client> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Client.class);
        Client outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("Name Test0", outputZero.getName());
        
        Client outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("Name Test7", outputSeven.getName());
        
        Client outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("Name Test12", outputTwelve.getName());
    }
}