package pl.training.bank.service;

import org.modelmapper.ModelMapper;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class Mapper {

    public <Source, Destination> void map(Source source, Destination destination) {
        createMapper().map(source, destination);
    }

    public <Source, Destination> Destination map(Source source, Class<Destination> destinationType) {
        return createMapper().map(source, destinationType);
    }

    public <SourceType, DestinationType>List<DestinationType> map(List<SourceType> source, Class<DestinationType> destinationType) {
        ModelMapper modelMapper =  createMapper();
        return source.stream()
                .map(element -> modelMapper.map(element, destinationType))
                .collect(Collectors.toList());
    }

    private ModelMapper createMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

}
