package klicelab.persistence;

import klicelab.model.Experiment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2017/4/23.
 */
@Repository
public class ExperimentRepositoryImpl implements ExperimentRepository {
    @Override
    public List<Experiment> getPagedExperiments(Long cutId, Integer pageSize) {
        ArrayList<Experiment> experiments = new ArrayList<Experiment>(10);
        for(int i=0;i<10;i++){
            Experiment experiment = new Experiment();
            experiment.setId((long)i);
            experiment.setName("实验"+i);
            experiments.add(experiment);
        }
        return experiments;
    }
}
