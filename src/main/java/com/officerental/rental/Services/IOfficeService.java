package com.officerental.rental.Services;



import com.officerental.rental.models.Office;

import java.util.List;

public interface IOfficeService {
    List<Office> getAllOffices();
    Office getOfficeById(Long id);
    void rentOffice(Long id);
}
