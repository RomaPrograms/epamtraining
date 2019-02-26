package by.training.cube.observer;

import by.training.cube.action.CubeAction;
import by.training.cube.action.CubeEvent;
import by.training.cube.entity.CubeData;
import by.training.cube.entity.Point;
import by.training.cube.repository.CubeRepository;
import java.util.List;

/**
 * class get information about some changes in cube for correction
 * information about this cube in cubeData class.
 */
public class CubeObserver implements Observer {

    /**
     * function react to changes in cube.
     * @param event - event
     * @param id - id
     */
    public void handleEvent(final CubeEvent event, final String id) {
        CubeAction cubeAction = new CubeAction();

        Point point1 = event.getSource().getA();
        Point point2 = event.getSource().getB();

        List<CubeData> cubesData =
                CubeRepository.getRepository().getCubesData();
        for (int i = 0; i < cubesData.size(); i++) {
            if (cubesData.get(i).getId().equals(id)) {
                double side = cubeAction.calculateSide(point1, point2);
                cubesData.get(i).setSquare(cubeAction.calculateSquare(side));
                cubesData.get(i).setVolume(cubeAction.calculateVolume(side));
            }
        }
    }
}
