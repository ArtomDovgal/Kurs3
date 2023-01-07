package stu.cn.ua.service;

import org.springframework.stereotype.Service;
import stu.cn.ua.domain.Passenger;
import stu.cn.ua.domain.Transport;
import stu.cn.ua.domain.TransportCategory;
import stu.cn.ua.domain.enums.PassengerPrivileges;
import stu.cn.ua.domain.enums.VehicleType;
import stu.cn.ua.persistence.TransportCategoryRepository;
import stu.cn.ua.persistence.TransportRepository;

import java.util.*;

@Service
public class TransportCategoryService {
    private final TransportCategoryRepository transportCategoryRepository;
    private final TransportRepository transportRepository;

    public TransportCategoryService(TransportCategoryRepository transportCategoryRepository, TransportRepository transportRepository) {
        this.transportCategoryRepository = transportCategoryRepository;
        this.transportRepository = transportRepository;
    }
    public TransportCategory save(TransportCategory transportCategory)
    {
        return transportCategoryRepository.save(transportCategory);
    }
    public TransportCategory findTransportCategoryById(Long id)
    {
        return transportCategoryRepository.findTransportCategoryByTransportCategoryId(id);
    }
    public void deleteTransportCategoryById(Long id)
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
    public Set<TransportCategory> findAll()
    {
        return transportCategoryRepository.findAll();
    }

    public Map<VehicleType,Integer> countAmountOfTransportsOfEachVehicleType()
    {
        Integer bus=0,train=0,plane=0,ship=0;
        Map<VehicleType,Integer> result = new HashMap<>();
        VehicleType[] vehicleTypes=VehicleType.values();
        Set<TransportCategory> transportCategories= transportCategoryRepository.findAll();
        
        List<TransportCategory> transportCategoryList=new ArrayList<>(transportCategories.stream().toList());
        for(int i =0;i<transportCategoryList.size();i++)
        {
            for(int j =0;j<vehicleTypes.length;j++)
                switch (vehicleTypes[j]) {
                    case BUS:
                        if(transportCategoryList.get(i).getVehicleType()==vehicleTypes[j])
                        {
                            bus+=transportCategoryList.get(i).getTransports().size();
                        }
                        break;
                    case SHIP:
                        if(transportCategoryList.get(i).getVehicleType()==vehicleTypes[j])
                        {
                            ship+=transportCategoryList.get(i).getTransports().size();
                        }
                        break;
                    case TRAIN:
                        if(transportCategoryList.get(i).getVehicleType()==vehicleTypes[j])
                        {
                            train+=transportCategoryList.get(i).getTransports().size();
                        }
                        break;
                    case PLANE:
                        if(transportCategoryList.get(i).getVehicleType()==vehicleTypes[j])
                        {
                            plane+=transportCategoryList.get(i).getTransports().size();
                        }
                        break;
                }
        }
        Integer[] counts={bus,train,plane,ship};
        for(int i =0;i<vehicleTypes.length;i++)
        {
            result.put(vehicleTypes[i],counts[i]);
        }
        return result;
    }

}
