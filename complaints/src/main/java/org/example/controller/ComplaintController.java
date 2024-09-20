package org.example.controller;

import org.example.dto.ComplaintDTO;
import org.example.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping
    public List<ComplaintDTO> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @GetMapping("/{id}")
    public ComplaintDTO getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    @PostMapping
    public String createComplaint(@RequestBody ComplaintDTO complaintDTO) {

        try {
            complaintService.createComplaint(complaintDTO);
            return "complaint registered succesfully";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/{id}")
    public ComplaintDTO updateComplaint(@PathVariable Long id, @RequestBody ComplaintDTO complaintDTO) {
        return complaintService.updateComplaint(id, complaintDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }
}