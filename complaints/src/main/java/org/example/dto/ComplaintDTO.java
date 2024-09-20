package org.example.dto;

import lombok.Data;
import org.example.model.Status;

@Data
public class ComplaintDTO {

    private Long complaintId;

    private String customerUserName;

    private String restaurantUserName;

    private String description;

    private Status status;

    private String response;

}