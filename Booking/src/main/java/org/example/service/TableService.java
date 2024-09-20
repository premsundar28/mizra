package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.dto.TablesDTO;
import org.example.model.Tables;
import org.example.repository.TablesRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TableService {

    private final TablesRepository tablesRepository;

    public void addTable(TablesDTO tablesDTO){
        Tables table = Tables.builder()
                .restaurantUsername(tablesDTO.getRestaurantUsername())
                .tableNumber(tablesDTO.getTableNumber())
                .status(tablesDTO.getStatus())
                .bookingId(tablesDTO.getBookingId())
                .build();

        tablesRepository.save(table);

    }
}
