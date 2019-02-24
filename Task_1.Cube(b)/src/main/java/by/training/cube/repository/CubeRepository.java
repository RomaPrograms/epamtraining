package by.training.cube.repository;

import by.training.cube.entity.Cube;
import by.training.cube.entity.CubeData;
import by.training.cube.entity.Point;
import by.training.cube.entity.Parameter;
import by.training.cube.factory.CubeFactory;
import by.training.cube.finding.FindById;
import by.training.cube.finding.FindByName;
import by.training.cube.finding.FindBySquare;
import by.training.cube.finding.FindByVolume;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_1_X;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_1_Y;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_1_Z;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_2_X;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_2_Y;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_2_Z;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_3_X;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_3_Y;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_3_Z;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * contains repository with cubes and all information about them.
 */
final public class CubeRepository implements Repository {

    /**
     * repository object.
     */
    private static CubeRepository repository;
    /**
     * map with all cubes.
     */
    private Map<String, Cube> cubes = new HashMap<>();
    /**
     * list with all data about every cube.
     */
    private List<CubeData> cubesData = new ArrayList<>();
    /**
     * factory object.
     */
    private CubeFactory factory = new CubeFactory();

    /**
     * private constructor for class.
     */
    private CubeRepository() { }
    /**
     * function returns new repository if we create repository firstly.
     * @return repository
     */
    public static CubeRepository getRepository() {
        if (repository == null) {
            repository = new CubeRepository();
        }

        return repository;
    }

    /**
     * getting list with data about cubes.
     * @return list with data about cubes
     */
    public List<CubeData> getCubesData() {
        return cubesData;
    }

    /**
     * getting factory object.
     * @return factory
     */
    public CubeFactory getFactory() {
        return factory;
    }

    /**
     * setting factory object.
     * @param cubeFactory - factory of cube
     */
    public void setFactory(final CubeFactory cubeFactory) {
        this.factory = cubeFactory;
    }

    /**
     * setting cube data.
     * @param newCubesData - cube data
     */
    public void setCubesData(final List<CubeData> newCubesData) {
        this.cubesData = newCubesData;
    }

    /**
     * add new cube to repository.
     * @param cube - cube
     * @param id - id of cube
     * @param name - name of cube
     */
    @Override
    public void add(final Cube cube, final String id, final String name) {
        cubes.put(id, cube);
        cubesData.add(factory.create(id, name, cube));
    }

    /**
     * delete cube from repository.
     * @param id - id of cube
     */
    @Override
    public void delete(final String id) {
        for (int i = 0; i < cubes.size(); i++) {
            if (cubes.containsKey(id) && cubesData.get(i).getId().equals(id)) {
                cubes.remove(i);
                cubesData.remove(i);
            }
        }
    }

    /**
     * changes coordinate of some cube in repository.
     * @param id - cube id
     * @param coordinates - cube coordinate
     */
    @Override
    public void changeCoordinates(final String id,
                                  final List<Double> coordinates) {
        if (cubes.containsKey(id)) {
            Point point1 = new Point(coordinates.get(INDEX_OF_POINT_1_X),
                    coordinates.get(INDEX_OF_POINT_1_Y),
                    coordinates.get(INDEX_OF_POINT_1_Z));
            Point point2 = new Point(coordinates.get(INDEX_OF_POINT_2_X),
                    coordinates.get(INDEX_OF_POINT_2_Y),
                    coordinates.get(INDEX_OF_POINT_2_Z));
            Point point3 = new Point(coordinates.get(INDEX_OF_POINT_3_X),
                    coordinates.get(INDEX_OF_POINT_3_Y),
                    coordinates.get(INDEX_OF_POINT_3_Z));

            cubes.get(id).changeCoordinate(point1, point2, point3, id);
        }
    }

    /**
     * finds cubes by some special parameters.
     * @param parameter - special parameter
     * @param information - exact information
     * @return list with founded cubes
     */
    public List<Cube> find(final Parameter parameter,
                                final String information) {
        switch (parameter) {
            case ID:
                return new FindById().findById(cubes, cubesData, information);
            case NAME:
                return new FindByName().findByName(cubes, cubesData,
                        information);
            default:
                return new ArrayList<>();
        }
    }

    /**
     * finds cubes by some special parameters.
     * @param parameter - special parameter
     * @param information - exact information
     * @return list with founded cubes
     */
    public List<Cube> find(final Parameter parameter,
                                final Double information) {
        switch (parameter) {
            case VOLUME:
                return new FindByVolume().findByVolume(
                        cubes, cubesData, information);
            case SQUARE:
                return new FindBySquare().findBySquare(
                        cubes, cubesData, information);
            default:
                return new ArrayList<>();
        }
    }

    /**
     * sorts cubes by some special parameters.
     * @param parameter - special parameter
     * @return sorted list
     */
    public List<Cube> sort(final Parameter parameter) {
        Comparator<CubeData> comparator = null;
       List<Cube> arrayList = new ArrayList<>();

        switch (parameter) {
            case ID:
                comparator = Comparator.comparing(CubeData::getId);
//                comparator = (final CubeData object1, final CubeData object)
//                        -> object1.getId().compareTo(object2.getId());
                break;
            case NAME:
                comparator = Comparator.comparing(CubeData::getName);
//                comparator = (final CubeData object1, final CubeData object2)
//                          -> object1.getName().compareTo(object2.getName());
                break;
            case VOLUME:
                comparator = Comparator.comparing(CubeData::getVolume);
//                comparator = (final CubeData object1, final CubeData object2)
//                        -> Double.compare(object1.getVolume(),
//                                object2.getVolume());
                break;
            case SQUARE:
                comparator = Comparator.comparing(CubeData::getSquare);
//                comparator = (final CubeData object1, final CubeData object2)
//                        -> Double.compare(object1.getSquare(),
//                                object2.getSquare());
                break;
            default:
                comparator = null;
        }

        cubesData.sort(comparator);
        for (int i = 0; i < cubesData.size(); i++) {
            arrayList.add(cubes.get(cubesData.get(i).getId()));
        }

        return arrayList;
    }
}
