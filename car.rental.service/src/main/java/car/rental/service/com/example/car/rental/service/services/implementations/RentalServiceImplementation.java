package car.rental.service.com.example.car.rental.service.services.implementations;

import car.rental.service.com.example.car.rental.service.entities.Rental;
import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.exceptions.RentalNotFoundException;
import car.rental.service.com.example.car.rental.service.repositories.RentalRepository;
import car.rental.service.com.example.car.rental.service.services.interfaces.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RentalServiceImplementation implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public Optional <Rental> findById(Long id) {
        return rentalRepository.findById(id);
    }

    @Override
    public List <Rental> getAll() {
        return rentalRepository.findAll();
    }

    @Override
    public List <Rental> getAllByUser(User user) {
        return rentalRepository.getAllByUser(user);
    }

    @Override
    public void deleteById(Long id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public boolean exist(Long id) {
        return rentalRepository.findById(id).isPresent();
    }

    @Override
    public void save(Rental rental) {
        rentalRepository.save(rental);
    }

    @Override
    public void updateEndDate(Long rentalId, LocalDate newEndDate) {
        Optional <Rental> optionalRental = rentalRepository.findById(rentalId);

        if (optionalRental.isPresent()) {
            Rental rentalToUpdate = optionalRental.get();
            rentalToUpdate.setEndDate(newEndDate);
            rentalRepository.save(rentalToUpdate);
        } else {
            throw new RentalNotFoundException("Rental with ID " + rentalId + " not found");
        }
    }
}
