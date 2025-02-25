package com.officerental.rental.Services;

import com.officerental.rental.Reposetories.OfficeRepository;
import com.officerental.rental.models.Office;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService implements IOfficeService{
    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    @Override
    public Office getOfficeById(Long id) {
        return officeRepository.findById(id).orElseThrow(() -> new RuntimeException("Office not found"));
    }

    @Override
    public void rentOffice(Long id) {
        Office office = getOfficeById(id);
        if (!office.isRented()) {
            office.setRented(true);
            officeRepository.save(office);
        } else {
            throw new RuntimeException("Office is already rented");
        }
    }
}
