package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ComplaintDTO;
import org.example.model.Complaint;
import org.example.repository.ComplaintRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;


    private static final String COMPLAINT_CREATE_TOPIC = "complaint_create";



    public List<ComplaintDTO> getAllComplaints() {
        return complaintRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ComplaintDTO getComplaintById(Long id) {
        Complaint complaint = complaintRepository.findById(id).orElseThrow();
        return convertToDto(complaint);
    }

    public ComplaintDTO createComplaint(ComplaintDTO complaintDTO) throws JsonProcessingException {
        Complaint complaint = convertToEntity(complaintDTO);
        complaint = complaintRepository.save(complaint);



        //TODO: PUBLISH KAFKA EVENT FOR USER_CREATE
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("complaintId",complaint.getComplaintId());
        jsonObject.put("customer username",complaint.getCustomerUserName());
        jsonObject.put("restaurant name",complaint.getRestaurantUserName());
        jsonObject.put("description",complaint.getDescription());
        jsonObject.put("status",complaint.getStatus());
        jsonObject.put("response",complaint.getResponse());
        jsonObject.put("auditId", UUID.randomUUID().toString());
        kafkaTemplate.send(COMPLAINT_CREATE_TOPIC, objectMapper.writeValueAsString(jsonObject));

        return convertToDto(complaint);
    }

    public ComplaintDTO updateComplaint(Long id, ComplaintDTO complaintDTO) {
        Complaint existingComplaint = complaintRepository.findById(id).orElseThrow();
        existingComplaint.setDescription(complaintDTO.getDescription());
        existingComplaint.setResponse(complaintDTO.getResponse());
        existingComplaint.setStatus(complaintDTO.getStatus());
        complaintRepository.save(existingComplaint);
        return convertToDto(existingComplaint);
    }

    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }

    private ComplaintDTO convertToDto(Complaint complaint) {
        ComplaintDTO complaintDTO = new ComplaintDTO();
        complaintDTO.setComplaintId(complaint.getComplaintId());
        complaintDTO.setCustomerUserName(complaint.getCustomerUserName());
        complaintDTO.setRestaurantUserName(complaint.getRestaurantUserName());
        complaintDTO.setDescription(complaint.getDescription());
        complaintDTO.setStatus(complaint.getStatus());
        complaintDTO.setResponse(complaint.getResponse());
        return complaintDTO;
    }

    private Complaint convertToEntity(ComplaintDTO complaintDTO) {
        Complaint complaint = new Complaint();
        complaint.setCustomerUserName(complaintDTO.getCustomerUserName());
        complaint.setRestaurantUserName(complaintDTO.getRestaurantUserName());
        complaint.setDescription(complaintDTO.getDescription());
        complaint.setStatus(complaintDTO.getStatus());
        complaint.setResponse(complaintDTO.getResponse());
        return complaint;
    }

}