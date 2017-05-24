package klicelab.persistence;

import klicelab.model.Experiment;

import java.util.List;

/**
 * Created by hasee on 2017/4/23.
 */

public interface ExperimentRepository {
    List<Experiment> getPagedExperiments(Long cutId, Integer pageSize);
}
