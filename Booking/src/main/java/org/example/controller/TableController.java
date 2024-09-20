package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.TablesDTO;
import org.example.repository.TablesRepository;
import org.example.service.TableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TableController {

    private final TablesRepository tablesRepository;
    private final TableService tableService;

    @PostMapping("addTable")
    public String addTable(@RequestBody TablesDTO tablesDTO){
        try{
            tableService.addTable(tablesDTO);

            return "table added successfully";
        }catch (Exception e){

            return e.getMessage();
        }

    }
}
