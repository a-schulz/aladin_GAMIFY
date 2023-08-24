package skills.examples.utils;
import org.modelmapper.ModelMapper;

import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import skills.examples.data.model.*;
import skills.examples.data.serviceRequestModel.*;

public class Mapper {

    public static SkillRequest mapSkillToRequest(Skill skill) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        SkillRequest request = modelMapper.map(skill, SkillRequest.class);
        return request;
    }

    public static GroupRequest mapGroupToRequest(Group group) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<Group, GroupRequest> propertyMap = modelMapper.createTypeMap(Group.class, GroupRequest.class);
        propertyMap.addMapping(Group::getId, GroupRequest::setSkillId);

        GroupRequest request = modelMapper.map(group, GroupRequest.class);
        return request;
    }

}
