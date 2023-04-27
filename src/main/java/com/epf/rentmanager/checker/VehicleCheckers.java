package com.epf.rentmanager.checker;


import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleCheckers {



public static boolean CharacteristicsCheck (Vehicle vehicle){

    return vehicle.getConstructeur()==null || vehicle.getConstructeur().length()==0 || vehicle.getModele()==null|| vehicle.getModele().length()==0 ;

}

    public static boolean SeatsCheck (Vehicle vehicle){

        return vehicle.getNb_places()<2 || vehicle.getNb_places()>9 ;

    }




}
