package skills.examples.utils;
import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;
import skills.examples.data.model.*;
import skills.examples.data.serviceRequestModel.*;

public class Mapper {

    public static GroupRequest mapGroupToRequest(Group group) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        GroupRequest request = modelMapper.map(group, GroupRequest.class);
        return request;
    }

}
