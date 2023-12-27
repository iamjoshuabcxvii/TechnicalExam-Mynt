package com.job.technicalexam.util;

import com.job.technicalexam.model.DatabaseModel;
import com.job.technicalexam.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import static com.job.technicalexam.enums.RulesEnum.*;

@Service
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    DatabaseRepository databaseRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        DatabaseModel databaseModelOne = new DatabaseModel();
        DatabaseModel databaseModelTwo = new DatabaseModel();
        DatabaseModel databaseModelThree = new DatabaseModel();
        DatabaseModel databaseModelFour = new DatabaseModel();
        DatabaseModel databaseModelFive = new DatabaseModel();

        databaseModelOne.setRuleName(REJECT.name());
        databaseModelOne.setPriority(1);
        databaseModelOne.setMinWeight(50.0);

        databaseModelTwo.setRuleName(HEAVY_PARCEL.name());
        databaseModelTwo.setPriority(2);
        databaseModelTwo.setMinWeight(10.0);
        databaseModelTwo.setMaxWeight(50.0);
        databaseModelTwo.setPrice(20.0);

        databaseModelThree.setRuleName(LARGE_PARCEL.name());
        databaseModelThree.setPriority(5);
        databaseModelThree.setMinWeight(0.0);
        databaseModelThree.setMaxWeight(10.0);
        databaseModelThree.setMinVolume(2500.0);
        databaseModelThree.setPrice(0.05);

        databaseModelFour.setRuleName(MEDIUM_PARCEL.name());
        databaseModelFour.setPriority(4);
        databaseModelFour.setMinVolume(1500.0);
        databaseModelFour.setMaxVolume(2500.0);
        databaseModelFour.setPrice(0.04);

        databaseModelFive.setRuleName(SMALL_PARCEL.name());
        databaseModelFive.setPriority(3);
        databaseModelFive.setMinVolume(0.0);
        databaseModelFive.setMaxVolume(1500.0);
        databaseModelFive.setPrice(0.03);



        databaseRepository.save(databaseModelOne);
        databaseRepository.save(databaseModelTwo);
        databaseRepository.save(databaseModelThree);
        databaseRepository.save(databaseModelFour);
        databaseRepository.save(databaseModelFive);


    }


}
