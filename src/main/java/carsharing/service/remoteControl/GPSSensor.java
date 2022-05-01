package carsharing.service.remoteControl;

import carsharing.dao.model.Car;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class GPSSensor implements RemoteSensor<String> {

    private List<String> streetsList = new LinkedList<>();

    @Override
    public String getIndicator(Car car) {
        streetsList.add("Folush");
        streetsList.add("Sovetskaya");
        streetsList.add("Devyatovka");
        streetsList.add("Solomovoy");
        return streetsList.get(new Random().nextInt(5));
    }

}
