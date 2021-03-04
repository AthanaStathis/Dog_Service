package se.iths.springdemo.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springdemo.entities.*;
import se.iths.springdemo.mappers.DogMapper;
import se.iths.springdemo.repositories.DogRepository;
import se.iths.springdemo.dtos.DogDto;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class DogService implements Service{

    private final DogMapper dogMapper = new DogMapper();
    private DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }


    @Override
    public List<DogDto> getAllDogs() {
        return dogMapper.mapp(dogRepository.findAll());
    }


    @Override
    public Optional<DogDto> getOneDog(int id) {
        //return personRepository.findById(id);
        return dogMapper.mapp(dogRepository.findById(id));
    }


    @Override
    public DogDto createDog(DogDto dog) {
        if (dog.getName().isEmpty())
            throw new RuntimeException();
        //return personRepository.save(person);
        return dogMapper.mapp(dogRepository.save(dogMapper.mapp(dog)));
    }


    @Override
    public boolean deleteDog(int id) {
        if(dogRepository.findById(id).isPresent()) {
            dogRepository.deleteById(id);
            return true;
        } else
            return false;
    }


    @Override
    public DogDto replaceDog(int id, DogDto dogDto) {
        Optional<Dog> dog =  dogRepository.findById(id);
        if (dog.isPresent()) {
            Dog updatedDog = dog.get();
            updatedDog.setName(dogDto.getName());
            updatedDog.setType(dogDto.getType());
            return dogMapper.mapp(dogRepository.save(updatedDog));
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Dog with id: " + id + " not found.");
        }
    }


    @Override
    public DogDto updateDog(int id, DogName dogDto) {
        Optional<Dog> dog =  dogRepository.findById(id);
        if (dog.isPresent()) {
            Dog updatedDog = dog.get();
            if (dogDto.name != null)
                updatedDog.setType(dogDto.name);
            updatedDog.setName(dogDto.name);
            return dogMapper.mapp(dogRepository.save(updatedDog));
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Dog with id: " + id + " not found.");
        }
    }


    @Override
    public DogDto updateDog(int id, DogType dogDto) {
        Optional<Dog> dog =  dogRepository.findById(id);
        if (dog.isPresent()) {
            Dog updatedDog = dog.get();
            if (dogDto.type != null)
                updatedDog.setType(dogDto.type);
            updatedDog.setType(dogDto.type);
            return dogMapper.mapp(dogRepository.save(updatedDog));
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Dog with id: " + id + " not found.");
        }
    }


    @Override
    public DogDto updateDog(int id, DogWeight dogDto) {
        Optional<Dog> dog =  dogRepository.findById(id);
        if (dog.isPresent()) {
            Dog updatedDog = dog.get();
            updatedDog.setWeight(dogDto.weight);
            return dogMapper.mapp(dogRepository.save(updatedDog));
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Dog with id: " + id + " not found.");
        }
    }


    @Override
    public DogDto updateDog(int id, DogGender dogDto) {
        Optional<Dog> dog =  dogRepository.findById(id);
        if (dog.isPresent()) {
            Dog updatedDog = dog.get();
            if (dogDto.gender != null)
                updatedDog.setType(dogDto.gender);
            updatedDog.setType(dogDto.gender);
            return dogMapper.mapp(dogRepository.save(updatedDog));
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Dog with id: " + id + " not found.");
        }
    }

    @Override
    public List<DogDto> getDogGender(String gender) {
        return dogMapper.mapp(dogRepository.findAllByGender(gender));
    }

    @Override
    public List<DogDto> getDogByName(String name) {
        return dogMapper.mapp(dogRepository.findAllByName(name));
    }

    @Override
    public List<DogDto> getDogByType(String type) {
        return dogMapper.mapp(dogRepository.findAllByGender(type));
    }


}


