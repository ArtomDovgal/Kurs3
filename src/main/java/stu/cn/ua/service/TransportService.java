package stu.cn.ua.service;

import org.springframework.stereotype.Service;
import stu.cn.ua.domain.Transport;
import stu.cn.ua.domain.TransportCategory;
import stu.cn.ua.persistence.TransportCategoryRepository;
import stu.cn.ua.persistence.TransportRepository;

import java.util.Set;

@Service
public class TransportService {
    private final TransportCategoryRepository transportCategoryRepository;
    private final TransportRepository transportRepository;

    public TransportService(TransportCategoryRepository transportCategoryRepository, TransportRepository transportRepository) {
        this.transportCategoryRepository = transportCategoryRepository;
        this.transportRepository = transportRepository;
    }
    private Transport save(Transport transport)
    {
     Long transportCategoryId= transport.getTransportCategory().getTransportCategoryId();
        if(transportCategoryId!=null) {
            TransportCategory transportCategory = transportCategoryRepository.
                    findTransportCategoryByTransportCategoryId(transportCategoryId);

            if(transportCategory!=null)
            {
                transportCategory.getTransports().add(transport);
                transportCategoryRepository.save(transportCategory);
            }
            else
                transport.setTransportCategory(null);
        }
        return transportRepository.save(transport);
    }
    private Transport findTransportById(Long id)
    {
        return transportRepository.findTransportByTransportId(id);
    }
    private void deleteTransportById(Long id)
    {
        Transport transportToDelete=transportRepository.findTransportByTransportId(id);
        if(transportToDelete!=null)
        {
            TransportCategory transportCategory= transportToDelete.getTransportCategory();
            transportCategory.getTransports().remove(transportToDelete);
        }
        transportRepository.deleteByTransportId(id);
    }
    private Set<Transport> findAll()
    {
        return transportRepository.findAll();
    }
}
