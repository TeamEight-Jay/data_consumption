package com.recommendation.data_consumption.service.Implementation;

import com.recommendation.data_consumption.entity.UserMappingEntity;
import com.recommendation.data_consumption.repository.UserCorrelationRepository;
import com.recommendation.data_consumption.service.UserCorrelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserCorrelationServiceImpl implements UserCorrelationService {
    @Autowired
    UserCorrelationRepository userCorrelationRepository;

    @Override
    public void addValue(String userId, String otherUserId,double value) {
        UserMappingEntity userRow=userCorrelationRepository.findOne(userId);
        if(userRow!=null)
        {
            double currentValue=userRow.getMappedUsers().getOrDefault(otherUserId,0.0);
            currentValue+=value;
            userRow.getMappedUsers().put(otherUserId,currentValue);
        }
        else
        {
            userRow=new UserMappingEntity();
            userRow.setUserId(userId);
            userRow.setMappedUsers(new HashMap<String, Double>());
            userRow.getMappedUsers().put(otherUserId,value);
        }
        userCorrelationRepository.save(userRow);
    }

    @Override
    public UserMappingEntity getUserMapping(String userId) {
        return userCorrelationRepository.findOne(userId);
    }
}
