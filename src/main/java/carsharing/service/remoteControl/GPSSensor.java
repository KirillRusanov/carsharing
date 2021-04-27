package carsharing.service.remoteControl;

import carsharing.dao.model.Car;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class GPSSensor implements RemoteSensor<String> {

    private List<String> streetsList = List.of(
            "Folush",
            "Sovetskaya",
            "Devyatovka",
            "Solomovoy"
    );

    @Override
    public String getIndicator(Car car) {
        return streetsList.get(new Random().nextInt(5));
    }

}
