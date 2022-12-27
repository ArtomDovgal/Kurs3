package stu.cn.ua.service;

import org.springframework.stereotype.Service;
import stu.cn.ua.domain.Transport;
import stu.cn.ua.domain.TransportCategory;
import stu.cn.ua.persistence.TransportCategoryRepository;
import stu.cn.ua.persistence.TransportRepository;

import java.util.Set;

@Service
public class TransportCategoryService {
    private final TransportCategoryRepository transportCategoryRepository;
    private final TransportRepository transportRepository;

    public TransportCategoryService(TransportCategoryRepository transportCategoryRepository, TransportRepository transportRepository) {
        this.transportCategoryRepository = transportCategoryRepository;
        this.transportRepository = transportRepository;
    }
    private TransportCategory save(TransportCategory transportCategory)
    {
        return transportCategoryRepository.save(transportCategory);
    }
    private TransportCategory findTransportCategoryById(Long id)
    {
        return transportCategoryRepository.findTransportCategoryByTransportCategoryId(id);
    }
    private void deleteTransportCategoryById(Long id)
    {
        TransportCategory transportCategory= transportCategoryRepository.findTransportCategoryByTransportCategoryId(id);
        if(transportCategory!=null)
        {
        Set<Transport> transports = transportCategory.getTransports();
        for(Transport transport:transports)
        {
            transportRepository.delete(transport);
        }
        transportCategory.getTransports().clear();
        transportCategoryRepository.delete(transportCategory);
        }
    }
    private Set<TransportCategory> findAll()
    {
        return transportCategoryRepository.findAll();
    }
}
